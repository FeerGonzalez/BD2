package ar.unrn.tp.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DescuentoCompraDTO extends DescuentoDTO{
    private String tipoTarjeta;
    public DescuentoCompraDTO(Long id, LocalDate fechaInicio, LocalDate fechaFin, float porcentaje, String tipoTarjeta) {
        super(id, fechaInicio, fechaFin, porcentaje);
        this.tipoTarjeta = tipoTarjeta;
    }
}
