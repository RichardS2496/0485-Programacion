package org.example.shieldsecuritygestor.model.usuario;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@NoArgsConstructor

public class ClienteUsuario extends Usuario{

    private String cif;
    private String empresa;

    public ClienteUsuario(int id, String nombre, String apellido, String correo, String password, String telefono, boolean activo, String cif, String empresa){
        super(id, nombre, apellido, correo, password, telefono, activo);
        this.cif=cif;
        this.empresa=empresa;
    }



    public String getPerfil(){
        return "CLIENTE";
    }
}
