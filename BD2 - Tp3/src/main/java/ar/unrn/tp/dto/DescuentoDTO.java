package ar.unrn.tp.dto;

import java.time.LocalDate;

public class DescuentoDTO {
	private Long id;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	private float porcentaje;
	private String tipo;
	
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
	
	
}
