package ar.unrn.tp.api;

import java.time.LocalDate;
import java.util.List;

import ar.unrn.tp.dto.DescuentoDTO;
import ar.unrn.tp.modelo.Descuento;
import ar.unrn.tp.modelo.DescuentoCompra;
import ar.unrn.tp.modelo.DescuentoProducto;

public interface DescuentoService {
	// validar que las fechas no se superpongan
	void crearDescuentoSobreTotal(String marcaTarjeta, LocalDate fechaDesde,
	LocalDate fechaHasta, float porcentaje);

	 // validar que las fechas no se superpongan
	void crearDescuentoSobreProducto(String marcaProducto, LocalDate fechaDesde, LocalDate fechaHasta, float porcentaje);
	
	List<Descuento> listarDescuentosActivos();

	List<DescuentoCompra> listarDescuentosActivosSobreCompra();

	List<DescuentoProducto> listarDescuentosActivosSobreProducto();

	List<DescuentoDTO> listarDescuentosActivosDTO();
}

