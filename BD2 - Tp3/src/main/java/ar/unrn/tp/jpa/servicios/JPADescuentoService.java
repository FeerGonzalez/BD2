package ar.unrn.tp.jpa.servicios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import ar.unrn.tp.api.DescuentoService;
import ar.unrn.tp.dto.DescuentoDTO;
import ar.unrn.tp.modelo.Descuento;
import ar.unrn.tp.modelo.DescuentoCompra;
import ar.unrn.tp.modelo.DescuentoProducto;

@Service
public class JPADescuentoService implements DescuentoService {
	private final EntityManager em;
	
	public JPADescuentoService(EntityManager em) {
        this.em = em;
    }

	@Override
	public void crearDescuentoSobreTotal(String marcaTarjeta, LocalDate fechaDesde, LocalDate fechaHasta, float porcentaje) {
		  EntityTransaction tx = em.getTransaction();
	        try {
	            tx.begin();
	            Descuento descuento = new DescuentoCompra(fechaDesde, fechaHasta, marcaTarjeta, porcentaje);
	            em.persist(descuento);
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
	public void crearDescuento(String marcaProducto, LocalDate fechaDesde, LocalDate fechaHasta, float porcentaje) {
		EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Descuento descuento = new DescuentoProducto(fechaDesde, fechaHasta, marcaProducto, porcentaje);
            em.persist(descuento);
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
	public List<Descuento> listarDescuentosActivos() {
        List<Descuento> listaDeDescuentos = em.createQuery("SELECT d FROM Descuento d", Descuento.class).getResultList();
        List<Descuento> listaDeDescuentosActivos = new ArrayList<>();
        
        for (Descuento descuento : listaDeDescuentos) {
			if(descuento.estaActiva()) {
				listaDeDescuentosActivos.add(descuento);
			}
		}
        
        return listaDeDescuentosActivos;
	}

	@Override
	public List<DescuentoDTO> listarDescuentosActivosDTO() {
		List<Descuento> listaDeDescuentosActivos = listarDescuentosActivos();
		List<DescuentoDTO> listaDeDescuentosActivosDTO = new ArrayList<>();
		
		for (Descuento descuento : listaDeDescuentosActivos) {
			listaDeDescuentosActivosDTO.add(new DescuentoDTO(descuento.getId(), descuento.getFechaInicio(), descuento.getFechaFin(), descuento.getPorcentaje(), descuento.toString()));
		}
		
		return listaDeDescuentosActivosDTO;
	}

}
