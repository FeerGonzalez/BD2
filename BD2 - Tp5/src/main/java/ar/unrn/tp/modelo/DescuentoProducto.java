package ar.unrn.tp.modelo;

import java.time.LocalDate;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@DiscriminatorValue("Producto")
@Entity
public class DescuentoProducto extends Descuento{
	private String marca;

	public DescuentoProducto(){

	}

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


	@Override
	public String toString() {
		return "Es un descuento para la marca: " + marca;
	}
	
	

}
