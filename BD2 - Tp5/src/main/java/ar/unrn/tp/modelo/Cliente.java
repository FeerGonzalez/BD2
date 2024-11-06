package ar.unrn.tp.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "cliente")
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String nombre;
	private String apellido;
	@Column(unique = true)
	private Integer dni;
	private String email;
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private List<Tarjeta> tarjetas;
	
	public Cliente(String nombre, String apellido, Integer dni, String email) {
		verificarCampo(nombre);
		verificarCampo(apellido);
		verificarCampo(dni.toString());
		checkEmail(email);
		
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.email = email;
		this.tarjetas = new ArrayList<>();
	}
	
	public Cliente(String nombre, String apellido, Integer dni, String email, List<Tarjeta> tarjetas) {
		verificarCampo(nombre);
		verificarCampo(apellido);
		verificarCampo(dni.toString());
		checkEmail(email);
		
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.email = email;
		this.tarjetas = tarjetas;
	}
	
	public void agregarTarjeta(Tarjeta tarjeta) {
		tarjetas.add(tarjeta);
	}
	/*
	public Long getId() {
		return id;
	}
	
	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public int getDni() {
		return dni;
	}

	public String getEmail() {
		return email;
	}

	public List<Tarjeta> getTarjetas() {
		return tarjetas;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTarjetas(List<Tarjeta> tarjetas) {
		this.tarjetas = tarjetas;
	}*/

	@Override
	public int hashCode() {
		return Objects.hash(apellido, dni, email, nombre, tarjetas);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(apellido, other.apellido) && dni == other.dni && Objects.equals(email, other.email)
				&& Objects.equals(nombre, other.nombre) && Objects.equals(tarjetas, other.tarjetas);
	}

	private void verificarCampo(String campo) {
		Objects.requireNonNull(campo);
		if(campo.isBlank() || campo.isEmpty()) {
			throw new RuntimeException("El campo no puede estar vacio");
		}
	}
	
	private void checkEmail(String email) {
		verificarCampo(email);
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		if(!email.matches(regex)) {
			throw new RuntimeException("El mail no es valido");
		}
	}
}
