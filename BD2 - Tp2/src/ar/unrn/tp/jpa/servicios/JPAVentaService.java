package ar.unrn.tp.jpa.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

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

public class JPAVentaService implements VentaService {
	private final EntityManager em;
    private final ClienteService clienteService;
    private final ProductoService productoService;
	
	public JPAVentaService(EntityManager em, ClienteService clienteService, ProductoService productoService) {
        this.em = em;
        this.clienteService = clienteService;
        this.productoService = productoService;
    }

	@Override
	public void realizarVenta(Long idCliente, List<Long> productos, Long idTarjeta) {
		EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            Cliente cliente = clienteService.buscarCliente(idCliente);

            List<Producto> listaProductos = productos.stream()
                    .map(idProducto -> em.find(Producto.class, idProducto))
                    .collect(Collectors.toList());

            Tarjeta tarjeta = em.find(Tarjeta.class, idTarjeta);

            Carrito carrito = new Carrito(cliente);
            listaProductos.forEach(carrito::agregarProducto);

            List<DescuentoCompra> descuentosCompra = em.createQuery("SELECT d FROM Descuento d", DescuentoCompra.class).getResultList();
            List<DescuentoProducto> descuentosProductos = em.createQuery("SELECT d FROM Descuento d", DescuentoProducto.class).getResultList();
            
            for (DescuentoProducto descuentoProducto : descuentosProductos) {
				carrito.agregarDescuentoDeProducto(descuentoProducto);
			}
            
            for (DescuentoCompra descuentoCompra : descuentosCompra) {
				carrito.agregarDescuentoDeCompra(descuentoCompra);
			}

            Venta venta = carrito.realizarCompra(tarjeta);
            em.persist(venta);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new RuntimeException(e);
        } finally {
           if (em != null && em.isOpen()) {
                em.close();
            }
        }
		
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
        
        List<DescuentoCompra> descuentosCompra = em.createQuery("SELECT d FROM Descuento d WHERE d.activo = true", DescuentoCompra.class).getResultList();
        List<DescuentoProducto> descuentosProductos = em.createQuery("SELECT d FROM Descuento d WHERE d.activo = true", DescuentoProducto.class).getResultList();
        
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

}
