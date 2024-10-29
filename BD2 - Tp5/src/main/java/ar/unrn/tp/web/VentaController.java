package ar.unrn.tp.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.unrn.tp.dto.VentaDTO;
import ar.unrn.tp.jpa.servicios.JPAVentaService;

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
}
