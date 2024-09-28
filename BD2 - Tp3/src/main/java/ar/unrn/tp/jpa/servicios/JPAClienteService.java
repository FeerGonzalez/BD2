package ar.unrn.tp.jpa.servicios;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import ar.unrn.tp.api.ClienteService;
import ar.unrn.tp.modelo.Cliente;
import ar.unrn.tp.modelo.Tarjeta;

public class JPAClienteService implements ClienteService {
	private final EntityManager em;

    public JPAClienteService(EntityManager em) {
        this.em = em;
    }


	@Override
	public void crearCliente(String nombre, String apellido, String dni, String email) {
		EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Cliente nuevoCliente = new Cliente(nombre, apellido, dni, email);
            em.persist(nuevoCliente);
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
	public void modificarCliente(Long idCliente, String nombre, String apellido, String dni, String email) {
		EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Cliente cliente = em.getReference(Cliente.class, idCliente);
            cliente.setNombre(nombre);
            cliente.setApellido(apellido);
            cliente.setDni(dni);
            cliente.setEmail(email);
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
	public void agregarTarjeta(Long idCliente, String nro, String marca) {
		EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Cliente cliente = em.find(Cliente.class, idCliente);
            Tarjeta nuevaTarjeta = new Tarjeta(nro, marca);
            cliente.agregarTarjeta(nuevaTarjeta);
            em.merge(cliente);
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
	public List<Tarjeta> listarTarjetas(Long idCliente) {
		 Cliente cliente = em.getReference(Cliente.class, idCliente);

	     return cliente.getTarjetas();
	}
	
	@Override
    public Cliente buscarCliente(Long idCliente) {
        return em.find(Cliente.class, idCliente);
    }

    @Override
    public Tarjeta buscarTarjetaCliente(Long idTarjeta) {
        return em.getReference(Tarjeta.class, idTarjeta);
    }

}
