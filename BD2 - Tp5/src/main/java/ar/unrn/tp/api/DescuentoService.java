package ar.unrn.tp.api;

import java.time.LocalDate;
import java.util.List;

import ar.unrn.tp.dto.DescuentoCompraDTO;
import ar.unrn.tp.dto.DescuentoGenericoDTO;
import ar.unrn.tp.dto.DescuentoProductoDTO;
import ar.unrn.tp.dto.DescuentoDTO;

public interface DescuentoService {
	// validar que las fechas no se superpongan
	void crearDescuentoSobreTotal(String marcaTarjeta, LocalDate fechaDesde,
	LocalDate fechaHasta, float porcentaje);

	 // validar que las fechas no se superpongan
	void crearDescuentoSobreProducto(String marcaProducto, LocalDate fechaDesde, LocalDate fechaHasta, float porcentaje);
	
	List<DescuentoGenericoDTO> listarDescuentosActivos();

	List<DescuentoCompraDTO> listarDescuentosActivosSobreCompra();

	List<DescuentoProductoDTO> listarDescuentosActivosSobreProducto();

	List<DescuentoDTO> listarDescuentosActivosDTO();
}

