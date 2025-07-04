package ar.unrn.tp.modelo;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tarjeta")
public class Tarjeta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private String codigo;
	@Enumerated(EnumType.STRING)
	private	TipoTarjeta tipo;
	private float saldo;
	private boolean estado;

	public Tarjeta(){

	}
	
	public Tarjeta (String codigo, String tipo) {
		verificarCodigoValido(codigo);
		verificarCampo(tipo);
		
		this.codigo = codigo;
		this.tipo = TipoTarjeta.valueOf(tipo);
		this.saldo = 0;
		this.estado = true;
	}
	
	public Tarjeta (String codigo, String tipo, float saldo) {
		verificarCodigoValido(codigo);
		verificarCampo(tipo);
		
		this.codigo = codigo;
		this.tipo = TipoTarjeta.valueOf(tipo);
		this.saldo = saldo;
		this.estado = true;
	}
	
	public Tarjeta(String codigo, String tipo, float saldo, boolean estado) {
		verificarCodigoValido(codigo);
		
		this.codigo = codigo;
		this.tipo = TipoTarjeta.valueOf(tipo);
		this.saldo = saldo;
		this.estado = estado;
	}
	/*
	public Long getId() {
		return Id;
	}

	public String getCodigo() {
		return codigo;
	}
	*/
	public String getTipo() {
		return tipo.toString();
	}
	/*
	public float getSaldo() {
		return saldo;
	}

	public boolean isEstado() {
		return estado;
	}

	 */
	
	private void verificarCodigoValido(String codigo) {
		if(codigo.length() != 16) {
			throw new RuntimeException("El codigo de la tarjeta no puede ser menor o mayor a 16 digitos");
		}
	}
	
	private void verificarCampo(String campo) {
		Objects.requireNonNull(campo);
		if(campo.isBlank() || campo.isEmpty()) {
			throw new RuntimeException("El campo no puede estar vacio");
		}
	}
	
	
}
