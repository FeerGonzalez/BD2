package ar.unrn.tp.jpa.servicios;

import java.util.ArrayList;
import java.util.List;

import ar.unrn.tp.dto.ClienteDTO;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import ar.unrn.tp.api.ClienteService;
import ar.unrn.tp.dto.TarjetaDTO;
import ar.unrn.tp.modelo.Cliente;
import ar.unrn.tp.modelo.Tarjeta;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class JPAClienteService implements ClienteService {
    @PersistenceContext
	private final EntityManager em;

    public JPAClienteService(EntityManager em) {
        this.em = em;
    }

	@Override
	public void crearCliente(String nombre, String apellido, String dni, String email) {
        Cliente nuevoCliente = new Cliente(nombre, apellido, Integer.parseInt(dni), email);
        em.persist(nuevoCliente);
	}

	@Override
	public void modificarCliente(Long idCliente, String nombre, String apellido, String dni, String email) {
        Cliente cliente = em.find(Cliente.class, idCliente);
        if (cliente == null) {
            throw new IllegalArgumentException("No se encontró un cliente con el ID proporcionado");
        }

        if (cliente.getDni() == Integer.parseInt(dni) ||
                !em.createQuery("SELECT c FROM Cliente c WHERE c.dni = :dni AND c.id != :id")
                .setParameter("dni", dni)
                .setParameter("id", idCliente)
                .getResultList()
                .isEmpty()) {
            throw new IllegalArgumentException("Ya existe otro cliente con ese DNI");
        }

        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setDni(Integer.parseInt(dni));
        cliente.setEmail(email);
        em.merge(cliente);
	}

	@Override
	public void agregarTarjeta(Long idCliente, String nro, String tipo) {
        Cliente cliente = em.find(Cliente.class, idCliente);
        Tarjeta nuevaTarjeta = new Tarjeta(nro, tipo);
        cliente.agregarTarjeta(nuevaTarjeta);
        em.merge(cliente);
		
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
    public ClienteDTO buscarClienteDTO(Long idCliente) {
		Cliente cliente = buscarCliente(idCliente);

		return new ClienteDTO(cliente.getId(), cliente.getNombre(),cliente.getApellido(), cliente.getDni().toString(), cliente.getEmail(), listarTarjetasDTO(idCliente) );
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
