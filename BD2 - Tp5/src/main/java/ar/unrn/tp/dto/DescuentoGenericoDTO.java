package ar.unrn.tp.dto;

import ar.unrn.tp.modelo.DescuentoCompra;
import ar.unrn.tp.modelo.DescuentoProducto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DescuentoGenericoDTO {
	private Long id;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	private float porcentaje;
	private String tipo;
	private String tarjeta;
	private String marca;

	public DescuentoGenericoDTO(DescuentoCompra d) {
		this.id = d.getId();
		this.fechaInicio = d.getFechaInicio();
		this.fechaFin = d.getFechaFin();
		this.porcentaje = d.getPorcentaje();
		this.tipo = "Compra";
		this.tarjeta = d.getTarjeta().toString();
	}

	public DescuentoGenericoDTO(DescuentoProducto d) {
		this.id = d.getId();
		this.fechaInicio = d.getFechaInicio();
		this.fechaFin = d.getFechaFin();
		this.porcentaje = d.getPorcentaje();
		this.tipo = "Producto";
		this.marca = d.getMarca();
	}

}
