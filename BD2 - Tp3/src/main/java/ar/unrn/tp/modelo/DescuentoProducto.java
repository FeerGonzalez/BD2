package ar.unrn.tp.modelo;

import java.time.LocalDate;

import jakarta.persistence.DiscriminatorValue;

@DiscriminatorValue("Producto")
public class DescuentoProducto extends Descuento{
	private String marca;

	public DescuentoProducto(LocalDate fechaInicio, LocalDate fechaFin, String marca, float porcentaje) {
		super(fechaInicio, fechaFin, porcentaje);
		verificarCampo(marca);
		
		this.marca = marca;
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
	
	public boolean verificarMarcaProductoValida(Producto producto) {
		if(producto.getMarca() == this.marca) {
			return true;
		}
		
		return false;
	}

	public String getMarca() {
		return marca;
	}

}
