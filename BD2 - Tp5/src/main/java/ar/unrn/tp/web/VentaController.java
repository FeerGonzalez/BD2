package ar.unrn.tp.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.unrn.tp.dto.VentaDTO;
import ar.unrn.tp.jpa.servicios.JPAVentaService;

import java.util.List;

@RestController
@RequestMapping("/ventas")
public class VentaController {
	private final JPAVentaService ventaService;
	
	public VentaController(JPAVentaService ventasService) {
        this.ventaService = ventasService;
    }
	
	@PostMapping("/calcularmonto")
    public double calcularMonto(@RequestBody VentaDTO request) {
        return ventaService.calcularMonto(request.getListaDeProductos(), request.getIdTarjeta());
    }

    @PostMapping("/realizarcompra")
    public ResponseEntity<String> realizarCompra(@RequestBody VentaDTO request) {
    	ventaService.realizarVenta(request.getIdCliente(), request.getListaDeProductos(), request.getIdTarjeta());
        return ResponseEntity.ok("La compra se realizo exitosamente");
    }

    @GetMapping("/ultimas-ventas/{clienteId}")
    public ResponseEntity<List<VentaDTO>> obtenerUltimasVentas(@PathVariable Long clienteId) {
        try {
            List<VentaDTO> ventas = ventaService.obtenerUltimasVentas(clienteId);
            return ResponseEntity.ok(ventas);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
