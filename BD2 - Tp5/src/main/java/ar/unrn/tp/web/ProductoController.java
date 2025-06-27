package ar.unrn.tp.web;

import ar.unrn.tp.dto.ClienteDTO;
import ar.unrn.tp.dto.ProductoDTO;
import ar.unrn.tp.modelo.Producto;
import ar.unrn.tp.jpa.servicios.JPAProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    /*
    @PostMapping("/precio-total")
    public ResponseEntity<Float> calcularPrecioTotal(@RequestBody List<Long> idsProductos) {
        try {
            // Convertimos los IDs a ProductoDTO
            List<ProductoDTO> productos = new ArrayList<>();
            for (Long id : idsProductos) {
                ProductoDTO dto = productoService.buscarProductoDTO(id);
                productos.add(dto);
            }

            float total = productoService.CalcularPrecioProductos(productos);
            return ResponseEntity.ok(total);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(0f);
        }
    }
    */

}