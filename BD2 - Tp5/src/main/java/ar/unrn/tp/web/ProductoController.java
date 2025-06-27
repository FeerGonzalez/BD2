package ar.unrn.tp.web;

import ar.unrn.tp.dto.ProductoDTO;
import ar.unrn.tp.exception.VersionException;
import ar.unrn.tp.jpa.servicios.JPAProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final JPAProductoService productoService;

    public ProductoController(JPAProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/traer-productos")
    public List<ProductoDTO> obtenerProductos() {
        return productoService.listarProductosDTO();
    }

    @PostMapping("/crear")
    public ResponseEntity<String> crearProducto(@RequestBody ProductoDTO productoDTO){
        try{
            Integer codigo = productoDTO.getCodigo();
            productoService.crearProducto(codigo.toString(),productoDTO.getDescripcion(),productoDTO.getPrecio(),productoDTO.getCategoria(),productoDTO.getMarca());
            return ResponseEntity.ok("Producto creado exitosamente");
        }catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/modificar/{id}")
    public ResponseEntity<String> modificarProducto(@PathVariable Long id, @RequestBody ProductoDTO productoDTO) {
        try {
            Integer codigo = productoDTO.getCodigo();
            productoService.modificarProducto(id, codigo.toString(), productoDTO.getDescripcion(), productoDTO.getPrecio(), productoDTO.getCategoria(), productoDTO.getMarca(), productoDTO.getVersion());
            return ResponseEntity.ok("Producto modificado exitosamente");
        } catch (VersionException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}