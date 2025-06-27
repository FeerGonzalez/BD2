package ar.unrn.tp.jpa.servicios;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.OptimisticLockException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;

import ar.unrn.tp.api.ProductoService;
import ar.unrn.tp.dto.ProductoDTO;
import ar.unrn.tp.modelo.Producto;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class JPAProductoService implements ProductoService {

    @PersistenceContext
	private final EntityManager em;
	
	public JPAProductoService(EntityManager em) {
        this.em = em;
    }

	@Override
	public void crearProducto(String codigo, String descripcion, float precio, String IdCategoria, String marca) {
        Producto producto = new Producto(codigo, descripcion, IdCategoria, precio, marca);
        em.persist(producto);
        /*EntityTransaction tx = em.getTransaction();
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

		 */
		
	}

	@Override
	public void modificarProducto(Long idProducto, String codigo, String descripcion, float precio, String IdCategoria, String marca, Long version) {
        Producto producto = em.find(Producto.class, idProducto);
        if (!producto.getVersion().equals(version)) {
            throw new OptimisticLockException("El producto fue modificado por otro usuario. Por favor, actualiza la página.");
        }

        producto.setCodigo(codigo);
        producto.setDescripcion(descripcion);
        producto.setPrecio(precio);
        producto.setCategoria(IdCategoria);
        producto.setMarca(marca);
        em.merge(producto);
        /*EntityTransaction tx = em.getTransaction();
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

		 */
		
	}
	
	@Override
	public List<Producto> listarProductos() {
		return em.createQuery("SELECT p FROM Producto p", Producto.class).getResultList();
	}

	@Override
	public Producto buscarProducto(long idProducto) {
		return em.createQuery("SELECT p FROM Producto p WHERE p.id = :id", Producto.class).setParameter("id", idProducto).getSingleResult();
	}
	
	@Override
	public List<ProductoDTO> listarProductosDTO() {
		List<Producto> listaProductos = listarProductos();
		List<ProductoDTO> listaProductosDTO = new ArrayList<>();
		for (Producto producto : listaProductos) {
			listaProductosDTO.add(new ProductoDTO(producto.getId(), producto.getCodigo(), producto.getDescripcion(),
    				   producto.getCategoria(), producto.getMarca(), producto.getPrecio()));
		}
		
		return listaProductosDTO;
	}
	
	@Override
    public ProductoDTO buscarProductoDTO(long idProducto) {
       Producto producto = buscarProducto(idProducto);
       return new ProductoDTO(producto.getId(), producto.getCodigo(), producto.getDescripcion(),
               producto.getCategoria(), producto.getMarca(), producto.getPrecio());
    }

	@Override
	public float CalcularPrecioProductos(List<ProductoDTO> lista){
		float precioTotal=0;

		for(ProductoDTO producto :lista){
			precioTotal += producto.getPrecio();
		}

		return precioTotal;
	}

}