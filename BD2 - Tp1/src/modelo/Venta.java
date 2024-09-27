package modelo;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Venta {
	private LocalDate fecha;
	private Cliente cliente;
	private List<ProductoVendido> listaProductos;
	private float montoTotal;
	
	public Venta(Cliente cliente, List<ProductoVendido> lista, float monto) {
		verificarListaDeProductos(lista);
		
		this.fecha = LocalDate.now();
		this.cliente = cliente;
		this.listaProductos = lista;
		this.montoTotal = monto;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public List<ProductoVendido> getListaProductos() {
		return listaProductos;
	}

	public float getMontoTotal() {
		return montoTotal;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cliente, fecha, listaProductos, montoTotal);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Venta other = (Venta) obj;
		return Objects.equals(cliente, other.cliente) && Objects.equals(fecha, other.fecha)
				&& Objects.equals(listaProductos, other.listaProductos)
				&& Float.floatToIntBits(montoTotal) == Float.floatToIntBits(other.montoTotal);
	}
	
	private void verificarListaDeProductos(List<ProductoVendido> lista) {
		if(lista.size() < 1) {
			throw new RuntimeException("La lista de productos debe tener al menos un producto");
		}
	}
	
	
}
