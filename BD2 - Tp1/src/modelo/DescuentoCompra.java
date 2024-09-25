package modelo;

import java.time.LocalDate;
import java.util.Objects;

public class DescuentoCompra implements Descuento{
	private TipoTarjeta tarjeta;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	private float porcentaje;

	public DescuentoCompra(LocalDate fechaInicio, LocalDate fechaFin, String tarjeta, float porcentaje) {
		verificarFecha(fechaInicio, fechaFin);
		verificarCampo(tarjeta);
		verificarCampo(((Float) porcentaje).toString());
		
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.tarjeta = TipoTarjeta.valueOf(tarjeta);
		this.porcentaje = porcentaje;
	}
	
	@Override
	public float aplicarDescuento(float precio) {
		
		float precioConDescuento = precio;
		
		if(estaActiva()) {
			float descuento = precio * (this.porcentaje / 100);
			
			precioConDescuento = precio - descuento;
		}
		
		return precioConDescuento;
	}

	@Override
	public boolean estaActiva() {
		LocalDate hoy = LocalDate.now();
        return !hoy.isBefore(fechaInicio) && !hoy.isAfter(fechaFin);
	}
	
	public String getTarjeta() {
		return tarjeta.toString();
	}

	private void verificarFecha(LocalDate fechaInicio, LocalDate fechaFin) {
		Objects.requireNonNull(fechaInicio);
		Objects.requireNonNull(fechaFin);
		
		if(fechaInicio.isAfter(fechaFin)) {
			throw new RuntimeException("La fecha de inicio no puede ser despues que la de fin");
		}
		
	}
	
	private void verificarCampo(String campo) {
		Objects.requireNonNull(campo);
		if(campo.isBlank() || campo.isEmpty()) {
			throw new RuntimeException("El campo no puede estar vacio");
		}
	}

}
