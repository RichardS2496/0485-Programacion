package org.example.shieldsecuritygestor.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class Servicio {
    private int id;
    private String nombre;
    private String descripcion;
    private boolean activo;


}