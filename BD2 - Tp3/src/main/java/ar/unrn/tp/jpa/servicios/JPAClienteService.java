package ar.unrn.tp.jpa.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import ar.unrn.tp.api.ClienteService;
import ar.unrn.tp.dto.TarjetaDTO;
import ar.unrn.tp.modelo.Cliente;
import ar.unrn.tp.modelo.Tarjeta;

@Service
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
            Cliente nuevoCliente = new Cliente(nombre, apellido, Integer.parseInt(dni), email);
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
            cliente.setDni(Integer.parseInt(dni));
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


	@Override
	public TarjetaDTO buscarTarjetaClienteDTO(Long idTarjeta) {
		Tarjeta tarjeta = buscarTarjetaCliente(idTarjeta);
		TarjetaDTO tarjetaDTO = new TarjetaDTO(tarjeta.getId(), tarjeta.getCodigo(), tarjeta.getTipo(), tarjeta.getSaldo(), tarjeta.isEstado());
		return tarjetaDTO;
	}
	
	@Override
	public List<TarjetaDTO> listarTarjetasDTO(Long idCliente) {
		 Cliente cliente = em.getReference(Cliente.class, idCliente);
		 List<TarjetaDTO> listaDeTarjetasDTO = new ArrayList<>();
	      for (Tarjeta tarjeta : cliente.getTarjetas()) {
			listaDeTarjetasDTO.add(new TarjetaDTO(tarjeta.getId(), tarjeta.getCodigo(), tarjeta.getTipo(), tarjeta.getSaldo(), tarjeta.isEstado()));
	      };
	      
	      return listaDeTarjetasDTO;
	}

}
