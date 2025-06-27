package ar.unrn.tp.web;


import java.util.List;

import ar.unrn.tp.modelo.Cliente;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.unrn.tp.dto.ClienteDTO;
import ar.unrn.tp.dto.TarjetaDTO;
import ar.unrn.tp.jpa.servicios.JPAClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	private final JPAClienteService clienteService;
	
	public ClienteController(JPAClienteService clienteService) {
	   this.clienteService = clienteService;
	}

    @GetMapping("traer-cliente/{idCliente}")
    public ClienteDTO traerClienteId(@PathVariable Long idCliente){
        return clienteService.buscarClienteDTO(idCliente);
    }

	@PostMapping("/crear")
	public ResponseEntity<String> crearCliente(@RequestBody ClienteDTO clienteDTO) {
	    try {
	        clienteService.crearCliente(clienteDTO.getNombre(), clienteDTO.getApellido(), clienteDTO.getDni(), clienteDTO.getEmail());
	        return ResponseEntity.ok("Cliente creado exitosamente");
	    } catch (IllegalArgumentException e) {
	        return ResponseEntity.badRequest().body(e.getMessage());
	    }
	}
	
	@PutMapping("/modificar/{id}")
    public ResponseEntity<String> modificarCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
        try {
            clienteService.modificarCliente(id, clienteDTO.getNombre(), clienteDTO.getApellido(), clienteDTO.getDni(), clienteDTO.getEmail());
            return ResponseEntity.ok("Cliente modificado exitosamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
	
	@PutMapping("/agregar-tarjeta/{idCliente}")
    public ResponseEntity<String> agregarTarjeta(
            @PathVariable Long idCliente,
            @RequestBody TarjetaDTO tarjetaDTO) {
        try {
            clienteService.agregarTarjeta(idCliente, tarjetaDTO.getCodigo(), tarjetaDTO.getTipo());
            return ResponseEntity.ok("Tarjeta agregada exitosamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/buscar-tarjetas/{idCliente}")
    public List<TarjetaDTO> obtenerTarjetas(@PathVariable Long idCliente) {
        return clienteService.listarTarjetasDTO(idCliente);
    }
}
