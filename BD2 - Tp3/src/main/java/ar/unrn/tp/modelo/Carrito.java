package ar.unrn.tp.modelo;

import java.util.ArrayList;
import java.util.List;

public class Carrito {
	private List<Producto> listaDeProductos;
	private Cliente cliente;
	private List<DescuentoCompra> listaDescuentosSobreCompra;
	private List<DescuentoProducto> listaDescuentosSobreProductos;
	
	public Carrito() {
		this.listaDeProductos = new ArrayList<>();
		this.listaDescuentosSobreCompra = new ArrayList<>();
		this.listaDescuentosSobreProductos = new ArrayList<>();
	}
	
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
	
	public float calcularTotal(Tarjeta tarjeta) {
		float precio = calcularTotal();
		
		precio = verificarAplicarDescuentoCompra(precio, tarjeta);

		return precio;
	}
	
	public Venta realizarCompra(Tarjeta tarjeta) {
		List<ProductoVendido> listaProductos = new ArrayList<>();
		
		for (Producto producto : listaDeProductos) {
			listaProductos.add(new ProductoVendido(producto, verificarAplicarDescuentoProducto(producto)));
		}
		
		Venta venta = new Venta(this.cliente, listaProductos, calcularTotal(tarjeta));
		
		return venta;
	}
	
	private float verificarAplicarDescuentoProducto(Producto producto) {
		float precio = producto.getPrecio();
		
		for (DescuentoProducto descuento : listaDescuentosSobreProductos) {
			if(descuento.verificarMarcaProductoValida(producto)) {
				precio = descuento.aplicarDescuento(producto.getPrecio());
			}
		}
		
		return precio;
	}
	
	private float verificarAplicarDescuentoCompra(float precio, Tarjeta tarjeta) {
		float precioConDescuento = precio;
		
		for (DescuentoCompra descuento : listaDescuentosSobreCompra) {
			if(descuento.getTarjeta() == tarjeta.getTipo()) {
				precioConDescuento = descuento.aplicarDescuento(precio);
			}
		}
		
		return precioConDescuento;
	}
	
}
