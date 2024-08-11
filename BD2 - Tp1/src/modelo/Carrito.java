package modelo;

import java.util.ArrayList;
import java.util.List;

public class Carrito {
	private List<Producto> listaDeProductos;
	private Cliente cliente;
	
	public Carrito(Cliente cliente) {
		this.listaDeProductos = new ArrayList<>();
		this.cliente = cliente;
	}
	
	public void agregarAlCarrito(Producto producto) {
		this.listaDeProductos.add(producto);
	}
	
}
