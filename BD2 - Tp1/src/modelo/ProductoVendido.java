package modelo;

public class ProductoVendido {
	private Producto producto;
	private float precio;
	
	public ProductoVendido(Producto producto, float precio) {
		this.producto = producto;
		this.precio = precio;
	}

	private Producto getProducto() {
		return producto;
	}

	private float getPrecio() {
		return precio;
	}
	
	
}
