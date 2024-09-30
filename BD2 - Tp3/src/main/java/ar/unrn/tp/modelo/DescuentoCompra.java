package ar.unrn.tp.modelo;

import java.time.LocalDate;

import jakarta.persistence.DiscriminatorValue;

@DiscriminatorValue("Compra")
public class DescuentoCompra extends Descuento{
	private TipoTarjeta tarjeta;

	public DescuentoCompra(LocalDate fechaInicio, LocalDate fechaFin, String tarjeta, float porcentaje) {
		super(fechaInicio, fechaFin, porcentaje);
		verificarCampo(tarjeta);
		
		this.tarjeta = TipoTarjeta.valueOf(tarjeta);
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
	
	public String getTarjeta() {
		return tarjeta.toString();
	}

}
