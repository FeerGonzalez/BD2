package modelo;

import java.util.Objects;

public class Producto {
	private int codigo;
	private String descripcion;
	private Categoria categoria;
	private String marca;
	private float precio;
	
	
	public Producto(String codigo, String descripcion, String categoria, float precio, String marca){
		verificarCampo(codigo.toString());
		verificarCampo(descripcion);
		verificarCampo(categoria);
		verificarPrecioValido(precio);
		verificarCampo(marca);
		
		this.codigo = Integer.parseInt(codigo);
		this.descripcion = descripcion;
		this.categoria = Categoria.valueOf(categoria);
		this.precio = precio;
		this.marca = marca;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getCategoria() {
		return categoria.name();
	}

	public float getPrecio() {
		return precio;
	}
	
	public String getMarca() {
		return marca;
	}
	
	private void verificarCampo(String campo) {
		Objects.requireNonNull(campo);
		if(campo.isBlank() || campo.isEmpty()) {
			throw new RuntimeException("El campo no puede estar vacio");
		}
	}
	
	private void verificarPrecioValido(float precio) {
		verificarCampo(((Float) precio).toString());
		if(precio < 0) {
			throw new RuntimeException("El precio no puede ser menor a 0");
		}
	}
	
}
