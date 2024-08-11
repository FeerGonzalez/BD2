package modelo;

public class DescuentoCompra implements Descuento{
	
	@Override
	public float aplicarDescuento(float precio) {
		float descuento = (float) (precio * 0.08);
		return 0;
	}

}
