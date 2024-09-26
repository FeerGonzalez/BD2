package tp1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

import modelo.Carrito;
import modelo.Cliente;
import modelo.DescuentoCompra;
import modelo.DescuentoProducto;
import modelo.Producto;
import modelo.Tarjeta;

public class VentasTest {
	@Test
	public void calcularMontoTotalConDescuentosCaducados() {
		Cliente cliente = new Cliente("Fernando", "Gonzalez", "12345678", "fer@gmail.com");
		Tarjeta tarjeta = new Tarjeta("1000000000000000", "MemeCard", 0, true);
		
		cliente.agregarTarjeta(tarjeta);
		
		Carrito carrito = new Carrito(cliente);
		
		carrito.agregarProducto(new Producto("1", "Es una remera deportiva", "Ropa_Deportiva", 100, "Comarca"));
		carrito.agregarProducto(new Producto("1", "Es un calzado", "Calzado", 50, "Acme"));
		
		carrito.agregarDescuentoDeCompra(new DescuentoCompra(LocalDate.now().minusDays(5), LocalDate.now().minusDays(2), "MemeCard", 8));
		carrito.agregarDescuentoDeProducto(new DescuentoProducto(LocalDate.now().minusDays(5), LocalDate.now().minusDays(2), "Comarca", 5));
		
		
		assertEquals(150, carrito.calcularTotal());
	}
	
	@Test
	public void calcularMontoTotalConDescuentoProductoAcme() {
		Cliente cliente = new Cliente("Fernando", "Gonzalez", "12345678", "fer@gmail.com");
		Tarjeta tarjeta = new Tarjeta("1000000000000000", "MemeCard", 0, true);
		
		cliente.agregarTarjeta(tarjeta);
		
		Carrito carrito = new Carrito(cliente);
		
		carrito.agregarProducto(new Producto("1", "Es una remera deportiva", "Ropa_Deportiva", 100, "Comarca"));
		carrito.agregarProducto(new Producto("1", "Es un calzado", "Calzado", 50, "Acme"));
		
		carrito.agregarDescuentoDeCompra(new DescuentoCompra(LocalDate.now().minusDays(5), LocalDate.now().minusDays(2), "MemeCard", 8));
		carrito.agregarDescuentoDeProducto(new DescuentoProducto(LocalDate.now().minusDays(1), LocalDate.now().plusDays(2), "Acme", 5));
		
		assertEquals(147.5, carrito.calcularTotal());
	}
	
	@Test
	public void calcularMontoTotalConDescuentoTipoPago() {
		Cliente cliente = new Cliente("Fernando", "Gonzalez", "12345678", "fer@gmail.com");
		Tarjeta tarjeta = new Tarjeta("1000000000000000", "MemeCard", 0, true);
		
		cliente.agregarTarjeta(tarjeta);
		
		Carrito carrito = new Carrito(cliente);
		
		carrito.agregarProducto(new Producto("1", "Es una remera deportiva", "Ropa_Deportiva", 100, "Comarca"));
		carrito.agregarProducto(new Producto("1", "Es un calzado", "Calzado", 50, "Acme"));
		
		carrito.agregarDescuentoDeCompra(new DescuentoCompra(LocalDate.now().minusDays(1), LocalDate.now().plusDays(2), "MemeCard", 8));
		carrito.agregarDescuentoDeProducto(new DescuentoProducto(LocalDate.now().minusDays(5), LocalDate.now().minusDays(2), "Acme", 5));
		
		assertEquals(138, carrito.calcularTotal(cliente.getTarjetas().get(0)));
	}
	
	@Test
	public void calcularMontoTotal() {
		Cliente cliente = new Cliente("Fernando", "Gonzalez", "12345678", "fer@gmail.com");
		Tarjeta tarjeta = new Tarjeta("1000000000000000", "MemeCard", 0, true);
		
		cliente.agregarTarjeta(tarjeta);
		
		Carrito carrito = new Carrito(cliente);
		
		carrito.agregarProducto(new Producto("1", "Es una remera deportiva", "Ropa_Deportiva", 100, "Comarca"));
		carrito.agregarProducto(new Producto("1", "Es un calzado", "Calzado", 50, "Acme"));
		
		carrito.agregarDescuentoDeCompra(new DescuentoCompra(LocalDate.now().minusDays(1), LocalDate.now().plusDays(2), "MemeCard", 8));
		carrito.agregarDescuentoDeProducto(new DescuentoProducto(LocalDate.now().minusDays(1), LocalDate.now().plusDays(2), "Comarca", 5));
		
		assertEquals(133.39999389648438, carrito.calcularTotal(cliente.getTarjetas().get(0)));
	}
	
	@Test
	public void realizarPagoYGenerarVenta() {
		Cliente cliente = new Cliente("Fernando", "Gonzalez", "12345678", "fer@gmail.com");
		Tarjeta tarjeta = new Tarjeta("1000000000000000", "MemeCard", 0, true);
		
		cliente.agregarTarjeta(tarjeta);
		
		Carrito carrito = new Carrito(cliente);
		
		Producto producto1 = new Producto("1", "Es una remera deportiva", "Ropa_Deportiva", 100, "Comarca");
		Producto producto2 = new Producto("1", "Es un calzado", "Calzado", 50, "Acme");
		
		carrito.agregarProducto(producto1);
		carrito.agregarProducto(producto2);
		
		carrito.agregarDescuentoDeCompra(new DescuentoCompra(LocalDate.now().minusDays(1), LocalDate.now().plusDays(2), "MemeCard", 8));
		carrito.agregarDescuentoDeProducto(new DescuentoProducto(LocalDate.now().minusDays(1), LocalDate.now().plusDays(2), "Comarca", 5));
		
		carrito.realizarCompra(tarjeta);
		
		assertEquals(1, cliente.getVentas().size());
	}
	
	@Test
	public void verificarCreacionDeProductoSinCategoria() {
		String mensajeDeError = "";
		
		try {
			new Producto("1", "Es una remera deportiva", "", 18, "Comarca");
		}catch (Exception e) {
			mensajeDeError = e.getMessage();
		}
		
		/*
		Exception e = assertThrows(RuntimeException.class, () -> {
	        new Producto(1, "Es una remera deportiva", "", 18, "Comarca");
	    });*/
		
		assertEquals("El campo no puede estar vacio", mensajeDeError);
	}
	
	@Test
	public void verificarCreacionDeProductoSinDescripcion() {
		String mensajeDeError = "";
		
		try {
			new Producto("1", "", "Ropa_Deportiva", 18, "Comarca");
		}catch (Exception e) {
			mensajeDeError = e.getMessage();
		}
		
		assertEquals("El campo no puede estar vacio", mensajeDeError);
	}
	
	@Test
	public void verificarCreacionDeProductoSinPrecio() {
		String mensajeDeError = "";
		
		try {
			new Producto("1", "Es una remera deportiva", "Ropa_Deportiva", -1, "Comarca");
		}catch (Exception e) {
			mensajeDeError = e.getMessage();
		}
		
		assertEquals("El precio no puede ser menor a 0", mensajeDeError);
	}
	
	@Test
	public void verificarCreacionDeClienteSinDni() {
		String mensajeDeError = "";
		
		try {
			 new Cliente("Fernando", "Gonzalez", "", "fer@gmail.com");
		}catch (Exception e) {
			mensajeDeError = e.getMessage();
		}
		
		assertEquals("El campo no puede estar vacio", mensajeDeError);
	}
	
	@Test
	public void verificarCreacionDeClienteSinNombre() {
		String mensajeDeError = "";
		
		try {
			 new Cliente("", "Gonzalez", "12345678", "fer@gmail.com");
		}catch (Exception e) {
			mensajeDeError = e.getMessage();
		}
		
		assertEquals("El campo no puede estar vacio", mensajeDeError);
	}
	
	@Test
	public void verificarCreacionDeClienteSinApellido() {
		String mensajeDeError = "";
		
		try {
			 new Cliente("Fernando", "", "12345678", "fer@gmail.com");
		}catch (Exception e) {
			mensajeDeError = e.getMessage();
		}
		
		assertEquals("El campo no puede estar vacio", mensajeDeError);
	}
	
	@Test
	public void verificarCreacionDeClienteConMailValido() {
		String mensajeDeError = "";
		
		try {
			 new Cliente("Fernando", "Gonzalez", "12345678", "fer.com");
		}catch (Exception e) {
			mensajeDeError = e.getMessage();
		}
		
		assertEquals("El mail no es valido", mensajeDeError);
	}
	
	@Test
	public void verificarCreacionDescuentoProducto() {
		String mensajeDeError = "";
				
		try {
			new DescuentoProducto(LocalDate.now().plusDays(5), LocalDate.now().minusDays(2), "Comarca", 5);
		}catch (Exception e) {
			mensajeDeError = e.getMessage();
		}
		
		assertEquals("La fecha de inicio no puede ser despues que la de fin", mensajeDeError);
		
	}
	
	@Test
	public void verificarCreacionDescuentoCompra() {
		String mensajeDeError = "";
		
		try {
			new DescuentoCompra(LocalDate.now().plusDays(5), LocalDate.now().minusDays(2), "MemeCard", 8);
		}catch (Exception e) {
			mensajeDeError = e.getMessage();
		}
		
		assertEquals("La fecha de inicio no puede ser despues que la de fin", mensajeDeError);
	}
	
}
