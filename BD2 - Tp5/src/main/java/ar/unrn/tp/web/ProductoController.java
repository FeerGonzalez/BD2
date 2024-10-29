package ar.unrn.tp.web;

import ar.unrn.tp.modelo.Producto;
import ar.unrn.tp.jpa.servicios.JPAProductoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final JPAProductoService productoService;

    public ProductoController(JPAProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public List<Producto> obtenerProductos() {
        return productoService.listarProductos();
    }
}