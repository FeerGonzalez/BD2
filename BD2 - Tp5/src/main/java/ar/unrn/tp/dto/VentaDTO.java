package ar.unrn.tp.dto;

import java.time.LocalDate;
import java.util.List;

import ar.unrn.tp.modelo.Venta;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VentaDTO {
	private Long id;
	private Long idCliente;
	private List<Long> listaDeProductos;
	private float montoTotal;
	private LocalDate fecha;
	private String numeroVenta;
	private Long idTarjeta;

	public VentaDTO(){

	}

	public VentaDTO(Venta venta) {
		this.id = venta.getId();
		this.idCliente = venta.getCliente().getId();
		this.listaDeProductos = venta.getListaProductos().stream()
				.map(pv -> pv.getProducto().getId())
				.toList();;
		this.montoTotal = venta.getMontoTotal();
		this.fecha = venta.getFecha();
		this.numeroVenta = venta.getNumeroVenta();
	}
    /*
    public VentaDTO(Long idCliente, List<Long> productos, Long idTarjeta) {
    	this.idCliente = idCliente;
    	this.listaDeProductos = productos;
    	this.idTarjeta = idTarjeta;
    }
    
	public Long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	public List<Long> getProductos() {
		return listaDeProductos;
	}
	public void setProductos(List<Long> productos) {
		this.listaDeProductos = productos;
	}
	public Long getIdTarjeta() {
		return idTarjeta;
	}
	public void setIdTarjeta(Long idTarjeta) {
		this.idTarjeta = idTarjeta;
	}

     */
    
    

}
