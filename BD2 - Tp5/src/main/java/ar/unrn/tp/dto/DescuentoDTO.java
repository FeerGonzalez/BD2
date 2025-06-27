package ar.unrn.tp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class DescuentoDTO {
	private Long id;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	private float porcentaje;

	/*
	public DescuentoDTO(Long id, LocalDate fechaInicio, LocalDate fechaFin, float porcentaje, String tipo) {
		this.id = id;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.porcentaje = porcentaje;
		this.tipo = tipo;
	}

	public Long getId() {
		return id;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public float getPorcentaje() {
		return porcentaje;
	}

	public String getTipo() {
		return tipo;
	}


	 */
	
}
