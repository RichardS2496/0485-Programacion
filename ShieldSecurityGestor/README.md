# ShieldSecurity Gestor

Aplicación de escritorio desarrollada en Java con JavaFX para la gestión interna de una empresa de ciberseguridad. Permite administrar clientes, contratos, facturas, incidencias y servicios, con acceso diferenciado según el rol del usuario.

---

## Cómo se ejecuta

El profesor puede ingresar con las credenciales que se muestran en la base de datos, aunque se facilitan de igual manera a continuación:

- Usuario Administrador (Correo: admin@admin.com / Contraseña: admin)
- Usuario Cliente (Correo: it@bancoatlas.es / Contraseña: cliente123)

### Requisitos previos

- Java 24
- Maven
- MariaDB corriendo en `localhost:3308`
- Base de datos llamada `shieldsecurity` con las tablas correspondientes

### Pasos

1. Clona o descarga el proyecto
2. Configura las credenciales de la base de datos en `DBConnection.java`:
   ```java
   String user = "root";
   String pass = "";
   ```
3. Ejecuta el proyecto con Maven:
   ```
   mvn clean javafx:run
   ```
   O directamente desde IntelliJ IDEA ejecutando `HelloApplication.java`

---

## Funcionalidades

### Autenticación

- Login con correo y contraseña
- Acceso restringido según rol del usuario (Administrador, Cliente)
- Usuarios inactivos no pueden iniciar sesión

### Panel Cliente

- Ver contratos contratados con nombre del plan, fechas e importe
- Ver facturas asociadas a sus contratos
- Ver incidencias abiertas o cerradas relacionadas con sus contratos
- Cerrar sesión

### Panel Administrador

- Ver listado completo de clientes registrados
- Ver todas las facturas del sistema
- Ver todas las incidencias del sistema
- Ver catálogo de servicios activos
- Añadir nuevos clientes con validación de campos
- Eliminar clientes (solo disponible al seleccionar uno)
- Cerrar sesión

### Panel Master (No se logró implementar, pero representa una oportunidad de mejora a futuro)

- Acceso completo al sistema (estructura preparada para expansión)

---

## Entidades que gestiona

| Entidad    | Descripción                                                  |
| ---------- | ------------------------------------------------------------ |
| Usuario    | Personas con acceso al sistema, clasificadas por rol         |
| Cliente    | Usuarios con rol de cliente, vinculados a una empresa y CIF  |
| Contrato   | Acuerdo entre un cliente y la empresa, asociado a un plan    |
| Plan       | Tipo de servicio contratado con nombre y precio              |
| Factura    | Documento de cobro asociado a un contrato                    |
| Incidencia | Problema o solicitud reportada por un cliente                |
| Servicio   | Servicio ofrecido por la empresa (vigilancia, alarmas, etc.) |

---

## Uso de la base de datos

Toda la persistencia de datos se gestiona mediante **JDBC** conectado a una base de datos **MariaDB**. Se aplica el patrón **DAO (Data Access Object)** para separar la lógica de acceso a datos del resto de la aplicación.

### Clases DAO

| Clase           | Responsabilidad                                           |
| --------------- | --------------------------------------------------------- |
| `DBConnection`  | Gestiona la conexión única a la base de datos (Singleton) |
| `LoginDAO`      | Autentica al usuario y determina su rol                   |
| `UsuarioDAO`    | Crear, listar y eliminar clientes                         |
| `ContratoDAO`   | Consultar contratos por usuario o todos                   |
| `FacturaDAO`    | Consultar facturas por usuario o todas                    |
| `IncidenciaDAO` | Consultar incidencias por usuario o todas                 |
| `ServicioDAO`   | Listar servicios activos                                  |

### Tablas utilizadas

`usuario`, `contrato`, `factura`, `incidencia`, `plan`, `servicio`

---

## Tecnologías utilizadas

- Java 24
- JavaFX 21
- JDBC con MariaDB Connector
- Lombok
- Maven
