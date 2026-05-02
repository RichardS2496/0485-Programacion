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

    public ClienteUsuario(String nombre, String apellido, String correo, String password, String cif, String empresa){
        super(nombre, apellido, correo, password);
        this.cif=cif;
        this.empresa=empresa;
    }



    public String getPerfil(){
        return "CLIENTE";
    }
}
