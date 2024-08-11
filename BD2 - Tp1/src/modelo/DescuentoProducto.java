package modelo;

import java.time.LocalDate;
import java.util.List;

public class DescuentoProducto implements Descuento{
	private List<Producto> listaDeProductos;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;

	@Override
	public float aplicarDescuento(float precio) {
		// TODO Auto-generated method stub
		return 0;
	}

}
