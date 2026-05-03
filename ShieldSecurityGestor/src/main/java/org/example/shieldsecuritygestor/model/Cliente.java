package org.example.shieldsecuritygestor.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class Cliente {
    private int id_cliente;
    private String nombre, apellido, correo, empresa, cif, alta;
    private Boolean activo;
}
