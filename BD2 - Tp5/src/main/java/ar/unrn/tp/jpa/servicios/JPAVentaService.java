package ar.unrn.tp.jpa.servicios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ar.unrn.tp.dto.VentaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import ar.unrn.tp.api.ClienteService;
import ar.unrn.tp.api.ProductoService;
import ar.unrn.tp.api.VentaService;
import ar.unrn.tp.modelo.Carrito;
import ar.unrn.tp.modelo.Cliente;
import ar.unrn.tp.modelo.DescuentoCompra;
import ar.unrn.tp.modelo.DescuentoProducto;
import ar.unrn.tp.modelo.Producto;
import ar.unrn.tp.modelo.Tarjeta;
import ar.unrn.tp.modelo.Venta;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class JPAVentaService implements VentaService {
	private final EntityManager em;
    private final ClienteService clienteService;
    private final ProductoService productoService;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
	
	public JPAVentaService(EntityManager em, ClienteService clienteService, ProductoService productoService, RedisTemplate<String, Object> redisTemplate) {
        this.em = em;
        this.clienteService = clienteService;
        this.productoService = productoService;

        this.redisTemplate = redisTemplate;
    }

	@Override
	public void realizarVenta(Long idCliente, List<Long> productos, Long idTarjeta) {
        Cliente cliente = clienteService.buscarCliente(idCliente);

        List<Producto> listaProductos = productos.stream()
                .map(idProducto -> em.find(Producto.class, idProducto))
                .collect(Collectors.toList());

        Tarjeta tarjeta = em.find(Tarjeta.class, idTarjeta);

        Carrito carrito = new Carrito(cliente);
        listaProductos.forEach(carrito::agregarProducto);

        List<DescuentoCompra> descuentosCompra = em.createQuery("SELECT d FROM DescuentoCompra d", DescuentoCompra.class).getResultList();

        List<DescuentoProducto> descuentosProductos = em.createQuery("SELECT d FROM DescuentoProducto d", DescuentoProducto.class).getResultList();


        for (DescuentoProducto descuentoProducto : descuentosProductos) {
            carrito.agregarDescuentoDeProducto(descuentoProducto);
        }

        for (DescuentoCompra descuentoCompra : descuentosCompra) {
            carrito.agregarDescuentoDeCompra(descuentoCompra);
        }

        Venta venta = carrito.realizarCompra(tarjeta);
        venta.setNumeroVenta(generarNumeroVenta());
        em.persist(venta);

        String key = "ultimas-ventas:" + cliente.getId();
        List<VentaDTO> anteriores = (List<VentaDTO>) redisTemplate.opsForValue().get(key);
        if (anteriores == null) anteriores = new ArrayList<>();

        anteriores.add(0, new VentaDTO(venta));

        if (anteriores.size() > 3) {
            anteriores = anteriores.subList(0, 3);
        }

        redisTemplate.opsForValue().set(key, anteriores);
	}

	@Override
	public float calcularMonto(List<Long> productos, Long idTarjeta) {
		Tarjeta tarjeta = em.find(Tarjeta.class, idTarjeta);

		List<Producto> listaProductos = new ArrayList<>();
		
		for (Long long1 : productos) {
			listaProductos.add(productoService.buscarProducto(long1));
		}

        Carrito carrito = new Carrito();
        listaProductos.forEach(carrito::agregarProducto);

        List<DescuentoCompra> descuentosCompra = em.createQuery(
                        "SELECT d FROM DescuentoCompra d WHERE :hoy BETWEEN d.fechaInicio AND d.fechaFin",
                        DescuentoCompra.class)
                .setParameter("hoy", LocalDate.now())
                .getResultList();

        List<DescuentoProducto> descuentosProductos = em.createQuery(
                        "SELECT d FROM DescuentoProducto d WHERE :hoy BETWEEN d.fechaInicio AND d.fechaFin",
                        DescuentoProducto.class)
                .setParameter("hoy", LocalDate.now())
                .getResultList();


        for (DescuentoProducto descuentoProducto : descuentosProductos) {
			carrito.agregarDescuentoDeProducto(descuentoProducto);
		}
        
        for (DescuentoCompra descuentoCompra : descuentosCompra) {
			carrito.agregarDescuentoDeCompra(descuentoCompra);
		}

        return carrito.calcularTotal(tarjeta);
	}

	@Override
	public List<Venta> listarVentas() {
		 return em.createQuery("SELECT v FROM Venta v", Venta.class).getResultList();
	}

    public List<VentaDTO> obtenerUltimasVentas(Long clienteId) {
        String key = "ultimas-ventas:" + clienteId;

        @SuppressWarnings("unchecked")
        List<VentaDTO> ventas = (List<VentaDTO>) redisTemplate.opsForValue().get(key);
        if (ventas != null) {
            return ventas;
        }

        // Si no está en cache, buscar en la BD
        List<Venta> ultimasVentas = em.createQuery(
                        "SELECT v FROM Venta v WHERE v.cliente.id = :id ORDER BY v.fecha DESC", Venta.class)
                .setParameter("id", clienteId)
                .setMaxResults(3)
                .getResultList();

        List<VentaDTO> ventaDTOs = ultimasVentas.stream()
                .map(VentaDTO::new)
                .toList();

        redisTemplate.opsForValue().set(key, ventaDTOs);

        return ventaDTOs;
    }

    private String generarNumeroVenta() {
        int anioActual = LocalDate.now().getYear();

        // Buscar la cantidad de ventas realizadas este año
        Long cantidadVentasEsteAnio = em.createQuery(
                        "SELECT COUNT(v) FROM Venta v WHERE YEAR(v.fecha) = :anio", Long.class)
                .setParameter("anio", anioActual)
                .getSingleResult();

        // N empieza en 1, así que se suma 1 a la cantidad actual
        long numero = cantidadVentasEsteAnio + 1;

        return numero + "-" + anioActual;
    }
}
