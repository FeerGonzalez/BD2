package ar.unrn.tp.web;

import java.util.List;

import ar.unrn.tp.dto.DescuentoCompraDTO;
import ar.unrn.tp.dto.DescuentoDTO;
import ar.unrn.tp.dto.DescuentoProductoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.unrn.tp.jpa.servicios.JPADescuentoService;
import ar.unrn.tp.modelo.Descuento;

@RestController
@RequestMapping("/descuentos")
public class DescuentoController {
	private final JPADescuentoService descuentoService;

	public DescuentoController(JPADescuentoService descuentoService) {
	    this.descuentoService = descuentoService;
	}

	@PostMapping("/crear-descuento-compra")
	public ResponseEntity<String> crearDescuentoSobreTotal(@RequestBody DescuentoCompraDTO descuentoCompraDTO) {
		try {
			descuentoService.crearDescuentoSobreTotal(
					descuentoCompraDTO.getTipoTarjeta(),
					descuentoCompraDTO.getFechaInicio(),
					descuentoCompraDTO.getFechaFin(),
					descuentoCompraDTO.getPorcentaje()
			);
			return ResponseEntity.ok("Descuento sobre el total creado exitosamente");
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping("/crear-descuento-producto")
	public ResponseEntity<String> crearDescuentoSobreProducto(@RequestBody DescuentoProductoDTO descuentoProductoDTO) {
		try {
			descuentoService.crearDescuentoSobreProducto(
					descuentoProductoDTO.getMarcaProducto(),
					descuentoProductoDTO.getFechaInicio(),
					descuentoProductoDTO.getFechaFin(),
					descuentoProductoDTO.getPorcentaje()
			);
			return ResponseEntity.ok("Descuento sobre producto creado exitosamente");
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}



	@GetMapping("/")
	public List<Descuento> obtenerDescuentos() {
	    return descuentoService.listarDescuentosActivos();
	}
}
