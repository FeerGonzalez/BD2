package ar.unrn.tp.jpa.servicios;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import ar.unrn.tp.api.DescuentoService;
import ar.unrn.tp.modelo.Descuento;
import ar.unrn.tp.modelo.DescuentoCompra;
import ar.unrn.tp.modelo.DescuentoProducto;

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

}
