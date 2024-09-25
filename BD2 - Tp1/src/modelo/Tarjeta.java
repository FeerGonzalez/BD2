package modelo;

public class Tarjeta {
	private int id;
	private	TipoTarjeta tipo;
	private float saldo;
	private boolean estado;
	
	public Tarjeta(int id, String tipo, float saldo, boolean estado) {
		this.id = id;
		this.tipo = TipoTarjeta.valueOf(tipo);
		this.saldo = saldo;
		this.estado = estado;
	}

	private int getId() {
		return id;
	}

	public String getTipo() {
		return tipo.toString();
	}

	private float getSaldo() {
		return saldo;
	}

	private boolean isEstado() {
		return estado;
	}
	
	
}
