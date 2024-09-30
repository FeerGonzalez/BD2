package ar.unrn.tp.dto;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class VentaDTO {
	@NotNull
	private Long idCliente;
	@NotEmpty
	private List<Long> listaDeProductos;
	@NotNull
    private Long idTarjeta;
    
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
    
    

}
