package ar.unrn.tp.api;

import java.util.List;

import ar.unrn.tp.dto.VentaDTO;
import ar.unrn.tp.modelo.Venta;

public interface VentaService {
	//Crea una venta. El monto se calcula aplicando los descuentos a la fecha
	 // validaciones:
	// - debe ser un cliente existente
	// - la lista de productos no debe estar vac�a
	 // - La tarjeta debe pertenecer al cliente
	void realizarVenta(Long idCliente, List<Long> productos, Long idTarjeta);
	//Devuelve el monto total aplicando los descuentos al d�a de la fecha
	 // validar que no llegue una lista vac�a y la tarjeta exista
	float calcularMonto(List<Long> productos, Long idTarjeta);
	 //Devuelve todas las ventas realizadas
	List<Venta> listarVentas();

	List<VentaDTO> obtenerUltimasVentas(Long clienteId);
}

