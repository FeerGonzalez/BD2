package ar.unrn.tp.jpa.servicios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ar.unrn.tp.dto.DescuentoCompraDTO;
import ar.unrn.tp.dto.DescuentoGenericoDTO;
import ar.unrn.tp.dto.DescuentoProductoDTO;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import ar.unrn.tp.api.DescuentoService;
import ar.unrn.tp.dto.DescuentoDTO;
import ar.unrn.tp.modelo.Descuento;
import ar.unrn.tp.modelo.DescuentoCompra;
import ar.unrn.tp.modelo.DescuentoProducto;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class JPADescuentoService implements DescuentoService {

	@PersistenceContext
	private final EntityManager em;
	
	public JPADescuentoService(EntityManager em) {
        this.em = em;
    }

	@Override
	public void crearDescuentoSobreTotal(String marcaTarjeta, LocalDate fechaDesde, LocalDate fechaHasta, float porcentaje) {
		Descuento descuento = new DescuentoCompra(fechaDesde, fechaHasta, marcaTarjeta, porcentaje);
		em.persist(descuento);

	}

	@Override
	public void crearDescuentoSobreProducto(String marcaProducto, LocalDate fechaDesde, LocalDate fechaHasta, float porcentaje) {
		Descuento descuento = new DescuentoProducto(fechaDesde, fechaHasta, marcaProducto, porcentaje);
		em.persist(descuento);
		
	}

	@Override
	public List<DescuentoGenericoDTO> listarDescuentosActivos() {
        List<Descuento> listaDeDescuentos = em.createQuery("SELECT d FROM Descuento d", Descuento.class).getResultList();
        List<Descuento> listaDeDescuentosActivos = new ArrayList<>();

		List<DescuentoGenericoDTO> listaDescuentosActivosDTO = new ArrayList<>();

        for (Descuento descuento : listaDeDescuentos) {
			if(descuento.estaActiva()) {
				listaDeDescuentosActivos.add(descuento);
			}
		}

		for (Descuento descuento : listaDeDescuentosActivos){
			if (descuento instanceof DescuentoCompra) {
				listaDescuentosActivosDTO.add(new DescuentoGenericoDTO((DescuentoCompra) descuento));
			}

			if (descuento instanceof DescuentoProducto) {
				listaDescuentosActivosDTO.add(new DescuentoGenericoDTO((DescuentoProducto) descuento));
			}
		}

        return listaDescuentosActivosDTO;
	}

	@Override
	public List<DescuentoCompraDTO> listarDescuentosActivosSobreCompra() {
		List<DescuentoCompra> listaDeDescuentos = em.createQuery(
				"SELECT d FROM DescuentoCompra d", DescuentoCompra.class).getResultList();
		List<DescuentoCompraDTO> listaDeDescuentosActivos = new ArrayList<>();

		for (DescuentoCompra descuento : listaDeDescuentos) {
			if(descuento.estaActiva()){
				listaDeDescuentosActivos.add(new DescuentoCompraDTO(descuento.getId(),descuento.getFechaInicio(),descuento.getFechaFin(),descuento.getPorcentaje(),descuento.getTarjeta()));
			}

		}

		return listaDeDescuentosActivos;
	}

	@Override
	public List<DescuentoProductoDTO> listarDescuentosActivosSobreProducto() {
		List<DescuentoProducto> listaDeDescuentos = em.createQuery(
				"SELECT d FROM DescuentoProducto d", DescuentoProducto.class).getResultList();
		List<DescuentoProductoDTO> listaDeDescuentosActivos = new ArrayList<>();

		for (DescuentoProducto descuento : listaDeDescuentos) {
			if(descuento.estaActiva()){
				listaDeDescuentosActivos.add(new DescuentoProductoDTO(descuento.getId(), descuento.getFechaInicio(),descuento.getFechaFin(),descuento.getPorcentaje(),descuento.getMarca()));
			}

		}

		return listaDeDescuentosActivos;
	}

	@Override
	public List<DescuentoDTO> listarDescuentosActivosDTO() {
		/*List<Descuento> listaDeDescuentosActivos = listarDescuentosActivos();
		List<DescuentoDTO> listaDeDescuentosActivosDTO = new ArrayList<>();
		
		for (Descuento descuento : listaDeDescuentosActivos) {
			listaDeDescuentosActivosDTO.add(new DescuentoDTO(descuento.getId(), descuento.getFechaInicio(), descuento.getFechaFin(), descuento.getPorcentaje(), descuento.toString()));
		}
		
		return listaDeDescuentosActivosDTO;

		 */
		return null;
	}

}
