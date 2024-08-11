package modelo;

import java.util.Objects;

public class Producto {
	private int id;
	private String descripcion;
	private String categoria;
	private float precio;
	
	public Producto(Integer id, String descripcion, String categoria, Float precio){
		verificarCampo(id.toString());
		verificarCampo(descripcion);
		verificarCampo(categoria);
		verificarCampo(precio.toString());
		
		this.id = id;
		this.descripcion = descripcion;
		this.categoria = categoria;
		this.precio = precio;
	}
	
	private void verificarCampo(String campo) {
		Objects.requireNonNull(campo);
		if(campo.isBlank() || campo.isEmpty()) {
			throw new RuntimeException("El campo no puede estar vacio");
		}
	}

	public int getId() {
		return id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getCategoria() {
		return categoria;
	}

	public float getPrecio() {
		return precio;
	}
	
	
}
