package ar.unrn.tp.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClienteDTO {
	private Long id;
    private String nombre;
    private String apellido;
    private String dni;
    private String email;
    private List<TarjetaDTO> tarjetas;

	public ClienteDTO() {}

    public ClienteDTO(String nombre, String apellido, String dni, String email) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.email = email;
	}

	public ClienteDTO(Long id, String nombre, String apellido, String dni, String email) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.email = email;

	}
	/*
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<TarjetaDTO> getTarjetas() {
		return tarjetas;
	}
	public void setTarjetas(List<TarjetaDTO> tarjetas) {
		this.tarjetas = tarjetas;
	}

	 */
    
    
}
