package ar.unrn.tp.api;

import java.util.List;

import ar.unrn.tp.modelo.Producto;

public interface ProductoService {
	//validar que sea una categoría existente y que codigo no se repita
	void crearProducto(String codigo, String descripcion, float precio, String IdCategoría, String marca); // Integer id, String descripcion, String categoria, float precio, String marca
	//validar que sea un producto existente
	void modificarProducto(Long idProducto, String codigo, String descripcion, float precio, String IdCategoría, String marca);
	 //Devuelve todos los productos
	 List<Producto> listarProductos();
}