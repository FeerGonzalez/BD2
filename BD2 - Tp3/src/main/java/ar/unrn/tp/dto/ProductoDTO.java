package ar.unrn.tp.dto;

public class ProductoDTO {
    private Long id;
	private int codigo;
	private String descripcion;
	private String categoria;
	private String marca;
	private float precio;
	
	public ProductoDTO(Long id, int codigo, String descripcion, String categoria, String marca, float precio) {
		this.id = id;
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.categoria = categoria;
		this.marca = marca;
		this.precio = precio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	@Override
    public String toString() {
        return descripcion;
    }
	
	
}
