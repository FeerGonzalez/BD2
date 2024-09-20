package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cliente {
	private String nombre;
	private String apellido;
	private int dni;
	private String email;
	private List<String> tarjetas;
	
	public Cliente(String nombre, String apellido, String dni, String email) {
		verificarCampo(nombre);
		verificarCampo(apellido);
		verificarCampo(dni.toString());
		checkEmail(email);
		
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = Integer.parseInt(dni);
		this.email = email;
		this.tarjetas = new ArrayList<>();
	}
	
	public Cliente(String nombre, String apellido, Integer dni, String email, List<String> tarjetas) {
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
	
	public void agregarTarjeta(String tarjeta) {
		tarjetas.add(tarjeta);
	}
	
	private String getNombre() {
		return nombre;
	}

	private String getApellido() {
		return apellido;
	}

	private int getDni() {
		return dni;
	}

	private String getEmail() {
		return email;
	}

	public List<String> getTarjetas() {
		return tarjetas;
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
