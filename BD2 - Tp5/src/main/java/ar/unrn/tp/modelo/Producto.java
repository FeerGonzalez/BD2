package ar.unrn.tp.modelo;

import java.util.Objects;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "producto")
public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Column(unique = true)
	private int codigo;
	private String descripcion;
	@Enumerated(EnumType.STRING)
	private Categoria categoria;
	private String marca;
	private float precio;
	@Version
	private Long version;
	
	
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
	/*
	public Long getId() {
		return id;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}
	*/
	public String getCategoria() {
		return categoria.name();
	}
	/*

	public float getPrecio() {
		return precio;
	}
	
	public String getMarca() {
		return marca;
	}
	*/
	public void setCodigo(String codigo) {
		this.codigo = Integer.parseInt(codigo);
	}

	/*
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	*/
	public void setCategoria(String categoria) {
		this.categoria = Categoria.valueOf(categoria);
	}
	/*
	public void setMarca(String marca) {
		this.marca = marca;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	 */

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
