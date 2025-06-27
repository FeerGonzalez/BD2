package ar.unrn.tp.main;

import java.util.Arrays;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import ar.unrn.tp.api.ClienteService;
import ar.unrn.tp.api.ProductoService;
import ar.unrn.tp.api.VentaService;
import ar.unrn.tp.jpa.servicios.JPAClienteService;
import ar.unrn.tp.jpa.servicios.JPAProductoService;
import ar.unrn.tp.jpa.servicios.JPAVentaService;
import ar.unrn.tp.modelo.Cliente;
import ar.unrn.tp.modelo.Producto;
import ar.unrn.tp.modelo.Tarjeta;
import ar.unrn.tp.modelo.Venta;

public class Main {

	public static void main(String[] args) {
            /*
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-objectdb");
        EntityManager em = emf.createEntityManager();

        ClienteService clienteService = new JPAClienteService(em);
        ProductoService productoService = new JPAProductoService(em);
        VentaService ventaService = new JPAVentaService(em, clienteService, productoService);
 
        Cliente cliente = new Cliente("Pedro", "Gutierrez", 12345678, "pedro@example.com");
        Tarjeta tarjeta = new Tarjeta("234 - 5678 - 9012 - 3456", "Visa", 50);
        
        cliente.agregarTarjeta(tarjeta);
        
        Producto producto1 = new Producto("A1", "es una remera", "Ropa_Deportiva", 100, "Comarca");
        Producto producto2 = new Producto("A2", "es un short", "Ropa_Deportiva", 50, "Comarca");

        em.getTransaction().begin();
        em.persist(cliente);
        em.persist(tarjeta);
        em.persist(producto1);
        em.persist(producto2);
        em.getTransaction().commit();

        List<Long> productosIds = Arrays.asList(producto1.getId(), producto2.getId());
        Long idTarjeta = tarjeta.getId();
        Long idCliente = cliente.getId();

        ventaService.realizarVenta(idCliente, productosIds, idTarjeta);

        float montoTotal = ventaService.calcularMonto(productosIds, idTarjeta);
        System.out.println("Monto total con los descuentos incluidos es de: " + montoTotal);

        // Listar todas las ventas realizadas
        List<Venta> ventasRealizadas = ventaService.listarVentas();
        ventasRealizadas.forEach(venta -> System.out.println("Venta realizada: " + venta));

        em.close();
        emf.close();

        */

	}

}
