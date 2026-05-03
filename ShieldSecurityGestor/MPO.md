## Arquitectura del proyecto

El proyecto sigue la arquitectura **MVC (Modelo - Vista - Controlador)** combinada con el patrón **DAO (Data Access Object)**:

```
┌─────────────┐     ┌─────────────────┐     ┌─────────────┐
│    Vista    │────▶│   Controlador   │────▶│     DAO     │
│   (.fxml)   │◀────│  (Controller)   │◀────│  (queries)  │
└─────────────┘     └─────────────────┘     └──────┬──────┘
                                                   │
                    ┌──────────────────────────────▼──────┐
                    │           Base de datos             │
                    │              (MariaDB)              │
                    └─────────────────────────────────────┘
```

- **Modelo** — clases en `model/` que representan las entidades del negocio
- **Vista** — archivos `.fxml` en `resources/` que definen la interfaz gráfica
- **Controlador** — clases en `controller/` que gestionan la lógica de la UI
- **DAO** — clases en `dao/` que encapsulan todas las consultas a la base de datos
- **Database** — clases en `database/` que gestionan la conexión

---

## Diagrama de clases simplificado

```
                ┌─────────────────┐
                │   <<abstract>>  │
                │     Usuario     │
                │─────────────────│
                │ - id: int       │
                │ - nombre        │
                │ - apellido      │
                │ - correo        │
                │ - password      │
                │ - activo        │
                │─────────────────│
                │ + getPerfil()   │
                └────────┬────────┘
                         │ extends
        ┌────────────────┼────────────────┐
        │                │                │
 ┌──────▼───────┐ ┌──────▼──────┐ ┌───────▼────────┐
 │    Master    │ │Administrador│ │ ClienteUsuario │
 │──────────────│ │─────────────│ │────────────────│
 │+ getPerfil() │ │+getPerfil() │ │ - cif          │
 └──────────────┘ └─────────────┘ │ - empresa      │
                                  │────────────────│
                                  │ + getPerfil()  │
                                  └────────────────┘

 ┌──────────────┐       ┌───────────────┐
 │   LoginDAO   │       │  DBConnection │
 │──────────────│       │───────────────│
 │ + login()    │──────▶│+getConnection │
 └──────────────┘       └───────────────┘

 ┌──────────────┐   ┌──────────────┐   ┌──────────────┐
 │  UsuarioDAO  │   │  ContratoDAO │   │  FacturaDAO  │
 │──────────────│   │──────────────│   │──────────────│
 │+crearCliente │   │+obtenerPor   │   │+obtenerPor   │
 │+obtenerTodos │   │  Usuario()   │   │  Usuario()   │
 │+eliminar     │   │+obtenerTodos │   │+obtenerTodas │
 └──────────────┘   └──────────────┘   └──────────────┘
```

---

## Parte correspondiente al MPO

### Mejoras de Programación Orientada a Objetos aplicadas

#### 1. Herencia y clase abstracta

La clase `Usuario` es **abstracta** y define el método `getPerfil()` como abstracto. Cada subclase (`Master`, `Administrador`, `ClienteUsuario`) lo implementa de forma diferente. Esto obliga a que todo usuario tenga un perfil definido sin permitir instanciar un usuario genérico.

```java
public abstract class Usuario {
    public abstract String getPerfil();
}

public class Master extends Usuario {
    public String getPerfil() { return "MASTER"; }
}
```

#### 2. Polimorfismo

El `LoginDAO` devuelve siempre un `Usuario`, pero en tiempo de ejecución puede ser un `Master`, `Administrador` o `ClienteUsuario`. El `LoginController` usa `getPerfil()` para decidir qué vista abrir sin saber el tipo concreto:

```java
Usuario encontrado = loginDAO.login(correo, password);
switch (encontrado.getPerfil()) {
    case "MASTER"        -> // abrir vista master
    case "ADMINISTRADOR" -> // abrir vista admin
    case "CLIENTE"       -> // abrir vista cliente
}
```

> [!WARNING]
> En este punto es importante resaltar que no eh podido implementar el apartado de Master para lo que quería por tiempo, sin embargo, representa una
> oportunidad de mejora a futuro ya que sigue siendo factible y la estructura que he gestionado me lo permite.

#### 3. Patrón Singleton

`DBConnection` garantiza que solo existe una única conexión a la base de datos durante toda la ejecución de la aplicación:

```java
public static Connection getConnection() {
    if (connection == null) {
        createConnection();
    }
    return connection;
}
```

#### 4. Patrón DAO

Cada entidad tiene su propio DAO que encapsula todas las operaciones con la base de datos. Los controladores nunca escriben SQL directamente — siempre delegan en el DAO correspondiente. Esto separa responsabilidades y hace el código más mantenible.

#### 5. Patrón MVC con JavaFX

La separación entre vista (FXML), controlador (Java) y modelo (clases) permite modificar la interfaz sin tocar la lógica, y cambiar la lógica sin tocar la interfaz.

#### 6. Encapsulación con Lombok

Se usa la librería Lombok para generar automáticamente getters, setters y constructores mediante anotaciones (`@Getter`, `@Setter`, `@NoArgsConstructor`), reduciendo código repetitivo y manteniendo los atributos privados.

---
