package ar.unrn.tp.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Cliente {
	@Id
    @GeneratedValue
    private Long id;
	private String nombre;
	private String apellido;
	private int dni;
	private String email;
	@OneToMany(cascade = CascadeType.PERSIST)
	private List<Tarjeta> tarjetas;
	@OneToMany(cascade = CascadeType.PERSIST)
	private List<Venta> listaDeVentas; //Cambiar esto en el tp1 y tp2, sacarlo y revisar el test del tp 1
	
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
		this.listaDeVentas = new ArrayList<>();
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
		this.listaDeVentas = new ArrayList<>();
	}
	
	public void agregarTarjeta(Tarjeta tarjeta) {
		tarjetas.add(tarjeta);
	}
	
	public void agregarVenta(Venta venta) {
		listaDeVentas.add(venta);
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

	public List<Tarjeta> getTarjetas() {
		return tarjetas;
	}
	
	public List<Venta> getVentas() {
		return listaDeVentas;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public void setDni(String dni) {
		this.dni = Integer.parseInt(dni);
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTarjetas(List<Tarjeta> tarjetas) {
		this.tarjetas = tarjetas;
	}

	public void setListaDeVentas(List<Venta> listaDeVentas) {
		this.listaDeVentas = listaDeVentas;
	}

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
