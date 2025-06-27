package ar.unrn.tp.modelo;

import java.time.LocalDate;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@DiscriminatorValue("Compra")
@Entity
public class DescuentoCompra extends Descuento{
	@Enumerated(EnumType.STRING)
	private TipoTarjeta tarjeta;

	public DescuentoCompra(){

	}

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
	
	@Override
	public String toString() {
		return "Es un descuento para la tarjeta: " + tarjeta;
	}

}
