package org.example.shieldsecuritygestor.model.usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public abstract class Usuario {

    private String nombre, apellido, correo, password;
    private int edad;

    public abstract String getPerfil();

    @Override
    public String toString() {
        return nombre;
    }

}
