package ar.unrn.tp.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "venta")
public class Venta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private LocalDate fecha;
	@ManyToOne
	private Cliente cliente;
	@OneToMany(cascade = CascadeType.PERSIST)
	private List<ProductoVendido> listaProductos = new ArrayList<>();
	private float montoTotal;
	@Column(unique = true)
	private String numeroVenta;

	public Venta(){

	}
	
	public Venta(Cliente cliente, List<ProductoVendido> lista, float monto) {
		verificarListaDeProductos(lista);
		
		this.fecha = LocalDate.now();
		this.cliente = cliente;
		this.listaProductos = lista;
		this.montoTotal = monto;
	}
	/*
	public Venta(Cliente cliente, List<ProductoVendido> lista, float monto, String numeroVenta) {
		verificarListaDeProductos(lista);

		this.fecha = LocalDate.now();
		this.cliente = cliente;
		this.listaProductos = lista;
		this.montoTotal = monto;
		this.numeroVenta = numeroVenta;
	}
	*/

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
		if(lista.isEmpty()) {
			throw new RuntimeException("La lista de productos debe tener al menos un producto");
		}
	}
	
	
}
