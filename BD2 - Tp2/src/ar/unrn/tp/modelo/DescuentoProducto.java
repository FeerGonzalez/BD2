package ar.unrn.tp.modelo;

import java.time.LocalDate;

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
	
	/*

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
	}*/

}
