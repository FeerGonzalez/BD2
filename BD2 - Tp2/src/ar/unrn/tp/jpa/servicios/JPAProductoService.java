package ar.unrn.tp.jpa.servicios;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import ar.unrn.tp.api.ProductoService;
import ar.unrn.tp.modelo.Producto;

public class JPAProductoService implements ProductoService {
	private final EntityManager em;
	
	public JPAProductoService(EntityManager em) {
        this.em = em;
    }

	@Override
	public void crearProducto(String codigo, String descripcion, float precio, String IdCategoría, String marca) {
		EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Producto producto = new Producto(codigo, descripcion, IdCategoría, precio, marca);
            em.persist(producto);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new RuntimeException(e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
		
	}

	@Override
	public void modificarProducto(Long idProducto, String codigo, String descripcion, float precio, String IdCategoría, String marca) {
		EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Producto producto = em.getReference(Producto.class, idProducto);
            producto.setCodigo(codigo);
            producto.setDescripcion(descripcion);
            producto.setPrecio(precio);
            producto.setCategoria(IdCategoría);
            producto.setMarca(marca);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new RuntimeException(e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
		
	}

	@Override
	public List<Producto> listarProductos() {
		return em.createQuery("SELECT p FROM Producto p", Producto.class).getResultList();
	}

}
