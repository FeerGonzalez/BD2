package modelo;

import java.time.LocalDate;
import java.util.List;

public class Venta {
	private LocalDate fecha;
	private Cliente cliente;
	private List<ProductoVendido> listaProductos;
	private float montoTotal;
	
	public Venta(Cliente cliente, List<ProductoVendido> lista, float monto) {
		this.fecha = LocalDate.now();
		this.cliente = cliente;
		this.listaProductos = lista;
		this.montoTotal = monto;
	}
}
