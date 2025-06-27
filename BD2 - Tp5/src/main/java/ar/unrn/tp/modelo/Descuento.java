package ar.unrn.tp.modelo;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_descuento")
@Entity
public abstract class Descuento {
	@Id
    @GeneratedValue
    private Long id;
	protected LocalDate fechaInicio;
	protected LocalDate fechaFin;
	protected float porcentaje;

	public Descuento(){

	}

	public Descuento(LocalDate fechaInicio, LocalDate fechaFin, float porcentaje) {
		verificarFecha(fechaInicio, fechaFin);
		verificarCampo(((Float) porcentaje).toString());
		
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.porcentaje = porcentaje;
	}
	
	public abstract float aplicarDescuento(float precio);
	
	public boolean estaActiva() {
		LocalDate hoy = LocalDate.now();
        return !hoy.isBefore(fechaInicio) && !hoy.isAfter(fechaFin);
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
	
	protected void verificarFecha(LocalDate fechaInicio, LocalDate fechaFin) {
		Objects.requireNonNull(fechaInicio);
		Objects.requireNonNull(fechaFin);
		
		if(fechaInicio.isAfter(fechaFin)) {
			throw new RuntimeException("La fecha de inicio no puede ser despues que la de fin");
		}
		
	}
	
	protected void verificarCampo(String campo) {
		Objects.requireNonNull(campo);
		if(campo.isBlank() || campo.isEmpty()) {
			throw new RuntimeException("El campo no puede estar vacio");
		}
	}
	
}
