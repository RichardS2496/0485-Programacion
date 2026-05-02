package org.example.shieldsecuritygestor.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Contrato {
    private int idContrato;
    private int idUsuario;
    private int idPlan;
    private String fechaInicio;
    private String fechaFin;
    private double importeTotal;
    private String estado;
    private String fechaCreacion;
    private String nombrePlan;
}
