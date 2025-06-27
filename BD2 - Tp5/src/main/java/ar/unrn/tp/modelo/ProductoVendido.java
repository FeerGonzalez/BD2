package ar.unrn.tp.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class ProductoVendido {
	@Id
    @GeneratedValue
    private Long id;
	@ManyToOne
	private Producto producto;
	private float precio;

	public ProductoVendido(){

	}
	
	public ProductoVendido(Producto producto, float precio) {
		this.producto = producto;
		this.precio = precio;
	}
	/*
	private Producto getProducto() {
		return producto;
	}

	private float getPrecio() {
		return precio;
	}

	 */
	
	
}
