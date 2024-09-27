package ar.unrn.tp.jpa.servicios;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import ar.unrn.tp.api.ClienteService;
import ar.unrn.tp.api.ProductoService;
import ar.unrn.tp.api.VentaService;
import ar.unrn.tp.modelo.Carrito;
import ar.unrn.tp.modelo.Cliente;
import ar.unrn.tp.modelo.Descuento;
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

            List<Descuento> descuentos = em.createQuery("SELECT d FROM Descuento d", Descuento.class).getResultList();

            Venta venta = carrito.realizarCompra(tarjeta);
            em.persist(venta);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new RuntimeException(e);
        } finally {
          /*  if (em != null && em.isOpen()) {
                em.close();
            }*/
        }
		
	}

	@Override
	public float calcularMonto(List<Long> productos, Long idTarjeta) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Venta> ventas() {
		// TODO Auto-generated method stub
		return null;
	}

}
