package ar.unrn.tp.dto;

public class TarjetaDTO {
	private Long Id;
	private String codigo;
	private	String tipo;
	private float saldo;
	private boolean estado;
	
	public TarjetaDTO(Long id, String codigo, String tipo, float saldo, boolean estado) {
		this.Id = id;
		this.codigo = codigo;
		this.tipo = tipo;
		this.saldo = saldo;
		this.estado = estado;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	@Override
    public String toString() {
        return codigo + tipo;
    }
}
