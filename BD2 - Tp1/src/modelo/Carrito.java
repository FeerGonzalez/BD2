package modelo;

import java.util.ArrayList;
import java.util.List;

public class Carrito {
	private List<Producto> listaDeProductos;
	private Cliente cliente;
	private List<DescuentoCompra> listaDescuentosSobreCompra;
	private List<DescuentoProducto> listaDescuentosSobreProductos;
	
	public Carrito(Cliente cliente) {
		this.listaDeProductos = new ArrayList<>();
		this.cliente = cliente;
		this.listaDescuentosSobreCompra = new ArrayList<>();
		this.listaDescuentosSobreProductos = new ArrayList<>();
	}
	
	public void agregarProducto(Producto producto) {
		this.listaDeProductos.add(producto);
	}
	
	public void eliminarProducto(Producto producto) {
		this.listaDeProductos.remove(producto);
	}
	
	public void agregarDescuentoDeProducto(DescuentoProducto descuento){
		listaDescuentosSobreProductos.add(descuento);
	}
	
	public void agregarDescuentoDeCompra(DescuentoCompra descuento){
		listaDescuentosSobreCompra.add(descuento);
	}
	
	public float calcularTotal() {
		float total = 0;
		
		for (Producto producto : listaDeProductos) {
			total += verificarAplicarDescuentoProducto(producto);
		}
		
		return total;
	}
	
	public float calcularTotal(String tarjeta) {
		float precio = calcularTotal();
		
		precio = verificarAplicarDescuentoCompra(precio, tarjeta);

		return precio;
	}
	
	private float verificarAplicarDescuentoProducto(Producto producto) {
		float precio = producto.getPrecio();
		
		for (DescuentoProducto descuento : listaDescuentosSobreProductos) {
			if(descuento.getMarca() == producto.getMarca()) {
				precio = descuento.aplicarDescuento(producto.getPrecio());
			}
		}
		
		return precio;
	}
	
	private float verificarAplicarDescuentoCompra(float precio, String tarjeta) {
		float precioConDescuento = precio;
		
		for (DescuentoCompra descuento : listaDescuentosSobreCompra) {
			if(descuento.getTarjeta() == tarjeta) {
				precioConDescuento = descuento.aplicarDescuento(precio);
			}
		}
		
		return precioConDescuento;
	}
	
}
