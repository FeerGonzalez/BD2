package modelo;

import java.util.Objects;

public class Cliente {
	private String nombre;
	private String apellido;
	private int dni;
	private String email;
	//private List<Tarjeta> tarjetas;
	
	public Cliente(String nombre, String apellido, Integer dni, String email) {
		verificarCampo(nombre);
		verificarCampo(apellido);
		verificarCampo(dni.toString());
		checkEmail(email);
		
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.email = email;
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
		email.matches(regex);
	}
}
