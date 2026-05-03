package org.example.shieldsecuritygestor.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class Incidencia {
    private int idIncidencia;
    private String titulo;
    private String descripcion;
    private String severidad;
    private String estado;
    private String fechaApertura;
    private String fechaCierre;
}

