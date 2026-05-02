package org.example.shieldsecuritygestor.database;

public interface SchemaDB {

    //Tablas

    String TABLE_USUARIOS = "usuario";


    // Columnas

    String COL_ID_USUARIO= "id_usuario";
    String COL_NOMBRE_USUARIO = "nombre_usuario";
    String COL_APELLIDO_USUARIO = "apellido_usuario";
    String COL_EMAIL="email";
    String COL_PASSWORD = "password";
    String COL_CIF = "cif";
    String COL_NOMBRE_EMPRESA="nombre_empresa";
    String COL_ID_ROL = "id_rol";
    String COL_ACTIVO = "activo";
    String TABLE_CONTRATO = "contrato";
    String TABLE_FACTURA = "factura";
    String COL_ID_CONTRATO = "id_contrato";
    String TABLE_PLAN = "plan";
    String COL_NOMBRE_PLAN = "nombre_plan";


}
