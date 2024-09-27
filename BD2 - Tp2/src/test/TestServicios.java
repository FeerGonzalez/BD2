package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import ar.unrn.tp.modelo.Cliente;
import ar.unrn.tp.modelo.Tarjeta;

public class TestServicios {
	@Test
    public void testPersistirCliente() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-objectdb");
        EntityManager em = emf.createEntityManager();


        Cliente cliente = new Cliente("Pedro", "Gutierrez", "00123456", "pedro@gmail.com");

        em.getTransaction().begin();
        em.persist(cliente);
        em.getTransaction().commit();

        assertNotNull(cliente);

        em.close();
        emf.close();
    }
	
	@Test
	public void testModificarCliente() {
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-objectdb");
	    EntityManager em = emf.createEntityManager();

	    Cliente cliente = new Cliente("Juan", "Perez", "00123457", "juan@gmail.com");

	    em.getTransaction().begin();
	    em.persist(cliente);
	    em.getTransaction().commit();

	    Long idCliente = cliente.getId(); 

	    em.getTransaction().begin();
	    Cliente clienteParaModificar = em.find(Cliente.class, idCliente);
	    clienteParaModificar.setNombre("Carlos");
	    clienteParaModificar.setApellido("Lopez");
	    clienteParaModificar.setDni("00123458");
	    clienteParaModificar.setEmail("carlos.lopez@gmail.com");
	    em.getTransaction().commit();

	    Cliente clienteModificado = em.find(Cliente.class, idCliente);
	    assertEquals("Carlos", clienteModificado.getNombre());
	    assertEquals("Lopez", clienteModificado.getApellido());
	    assertEquals("00123458", clienteModificado.getDni());
	    assertEquals("carlos.lopez@gmail.com", clienteModificado.getEmail());

	    em.close();
	    emf.close();
	}
	
	@Test
	public void testAgregarTarjeta() {
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-objectdb");
	    EntityManager em = emf.createEntityManager();

	    Cliente cliente = new Cliente("Maria", "Suarez", "00123459", "maria@gmail.com");

	    em.getTransaction().begin();
	    em.persist(cliente);
	    em.getTransaction().commit();

	    Long idCliente = cliente.getId();

	    em.getTransaction().begin();
	    Cliente clienteParaAgregarTarjeta = em.find(Cliente.class, idCliente);
	    Tarjeta tarjeta = new Tarjeta("123456789", "MemeCard");
	    clienteParaAgregarTarjeta.agregarTarjeta(tarjeta);
	    em.merge(clienteParaAgregarTarjeta);
	    em.getTransaction().commit();

	    Cliente clienteActualizado = em.find(Cliente.class, idCliente);
	    assertEquals(1, clienteActualizado.getTarjetas().size());
	    assertEquals("123456789", clienteActualizado.getTarjetas().get(0).getCodigo());
	    assertEquals("MemeCard", clienteActualizado.getTarjetas().get(0).getTipo());

	    em.close();
	    emf.close();
	}
	
	@Test
	public void testListarTarjetas() {
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-objectdb");
	    EntityManager em = emf.createEntityManager();

	    Cliente cliente = new Cliente("Ana", "Ramirez", "00123460", "ana@gmail.com");

	    em.getTransaction().begin();
	    em.persist(cliente);
	    em.getTransaction().commit();

	    Long idCliente = cliente.getId();

	    em.getTransaction().begin();
	    Cliente clienteParaAgregarTarjetas = em.find(Cliente.class, idCliente);
	    clienteParaAgregarTarjetas.agregarTarjeta(new Tarjeta("987654321", "MemeCard"));
	    clienteParaAgregarTarjetas.agregarTarjeta(new Tarjeta("112233445", "Comarca"));
	    em.merge(clienteParaAgregarTarjetas);
	    em.getTransaction().commit();

	    Cliente clienteActualizado = em.find(Cliente.class, idCliente);
	    List<Tarjeta> tarjetas = clienteActualizado.getTarjetas();
	    assertEquals(2, tarjetas.size());
	    assertEquals("987654321", tarjetas.get(0).getCodigo());
	    assertEquals("MemeCard", tarjetas.get(0).getTipo());
	    assertEquals("112233445", tarjetas.get(1).getCodigo());
	    assertEquals("Comarca", tarjetas.get(1).getTipo());

	    em.close();
	    emf.close();
	}

	@Test
	public void testBuscarCliente() {
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-objectdb");
	    EntityManager em = emf.createEntityManager();

	    Cliente cliente = new Cliente("Luis", "Garcia", "00123461", "luis@gmail.com");

	    em.getTransaction().begin();
	    em.persist(cliente);
	    em.getTransaction().commit();

	    Long idCliente = cliente.getId();

	    Cliente clienteEncontrado = em.find(Cliente.class, idCliente);
	    assertNotNull(clienteEncontrado);
	    assertEquals("Luis", clienteEncontrado.getNombre());
	    assertEquals("Garcia", clienteEncontrado.getApellido());

	    em.close();
	    emf.close();
	}

	@Test
	public void testBuscarTarjetaCliente() {
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-objectdb");
	    EntityManager em = emf.createEntityManager();

	    Cliente cliente = new Cliente("Sofia", "Martinez", "00123462", "sofia@gmail.com");
	    Tarjeta tarjeta = new Tarjeta("334455667", "Comarca");

	    em.getTransaction().begin();
	    cliente.agregarTarjeta(tarjeta);
	    em.persist(cliente);
	    em.getTransaction().commit();

	    Long idTarjeta = tarjeta.getId();

	    Tarjeta tarjetaEncontrada = em.find(Tarjeta.class, idTarjeta);
	    assertNotNull(tarjetaEncontrada);
	    assertEquals("334455667", tarjetaEncontrada.getCodigo());
	    assertEquals("Comarca", tarjetaEncontrada.getTipo());

	    em.close();
	    emf.close();
	}

}
