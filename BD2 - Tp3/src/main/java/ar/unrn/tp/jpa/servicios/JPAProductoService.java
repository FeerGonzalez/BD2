package ar.unrn.tp.jpa.servicios;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import ar.unrn.tp.api.ProductoService;
import ar.unrn.tp.modelo.Producto;

public class JPAProductoService implements ProductoService {
	private final EntityManager em;
	
	public JPAProductoService(EntityManager em) {
        this.em = em;
    }

	@Override
	public void crearProducto(String codigo, String descripcion, float precio, String IdCategoria, String marca) {
		EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Producto producto = new Producto(codigo, descripcion, IdCategoria, precio, marca);
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
	public void modificarProducto(Long idProducto, String codigo, String descripcion, float precio, String IdCategoria, String marca) {
		EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Producto producto = em.getReference(Producto.class, idProducto);
            producto.setCodigo(codigo);
            producto.setDescripcion(descripcion);
            producto.setPrecio(precio);
            producto.setCategoria(IdCategoria);
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
	
	@Override
    public Producto buscarProducto(long idProducto) {
       return em.createQuery("SELECT p FROM Producto p WHERE p.id = :id", Producto.class).setParameter("id", idProducto).getSingleResult();
    }

}