package ar.unrn.tp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DescuentoProductoDTO extends DescuentoDTO {
    private String marcaProducto;

    public DescuentoProductoDTO(Long id, LocalDate fechaInicio, LocalDate fechaFin, float porcentaje, String marcaProducto) {
        super(id, fechaInicio, fechaFin, porcentaje);
        this.marcaProducto = marcaProducto;
    }
}
