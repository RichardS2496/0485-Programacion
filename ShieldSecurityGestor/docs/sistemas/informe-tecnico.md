---
# Informe Técnico de Entorno de Ejecución
## ShieldSecurity Gestor
---

## 1. Tipo de sistema donde se ejecuta

**Tipo elegido: PC de usuario (escritorio)**

ShieldSecurity Gestor es una aplicación de escritorio desarrollada con JavaFX. Su naturaleza es local — no requiere navegador, no expone servicios web y accede directamente a una base de datos MariaDB instalada en la misma máquina o en red local.

**Justificación:**

- La interfaz gráfica JavaFX está diseñada para ejecutarse en un entorno de escritorio
- El volumen de datos es gestionable desde una sola máquina
- No requiere acceso concurrente masivo de usuarios
- El coste de infraestructura es mínimo al no necesitar servidor externo
  **Alternativa descartada:**

Servidor remoto — innecesario para el alcance actual de la aplicación y añadiría complejidad sin beneficio real.

---

## 2. Requisitos de hardware

| Componente     | Mínimo                                           | Recomendado                                       |
| -------------- | ------------------------------------------------ | ------------------------------------------------- |
| CPU            | Intel Core i3 / AMD Ryzen 3 (2 núcleos, 1.8 GHz) | Intel Core i5 / AMD Ryzen 5 (4 núcleos, 2.5 GHz+) |
| RAM            | 4 GB                                             | 8 GB                                              |
| Almacenamiento | 500 MB libres                                    | 2 GB libres                                       |
| Pantalla       | 1024x768                                         | 1920x1080                                         |
| Red            | No requerida (uso local)                         | LAN si la BD es remota                            |

**Justificación:**

- La JVM de Java consume memoria en el arranque (~200-300 MB)
- JavaFX renderiza interfaz gráfica, lo que requiere algo de CPU y GPU básica
- La base de datos MariaDB necesita espacio para datos y logs
- El resto del almacenamiento corresponde al JDK, Maven y el propio proyecto

---

## 3. Sistema operativo recomendado

**Sistema operativo principal: Windows 10 / Windows 11 (64 bits)**

| Parámetro      | Valor                              |
| -------------- | ---------------------------------- |
| SO principal   | Windows 10 Pro / Windows 11 Pro    |
| Versión mínima | Windows 10 (build 1903 o superior) |
| Arquitectura   | x64 obligatorio                    |
| Alternativa    | Ubuntu 22.04 LTS o superior        |

**¿Por qué Windows?**

- El proyecto fue desarrollado y probado en Windows 11
- JavaFX tiene soporte nativo completo en Windows
- MariaDB tiene instalador oficial para Windows
- Es el entorno más habitual en empresas donde se usaría esta aplicación

**¿Por qué no macOS?**

- Compatible técnicamente, pero requiere configuración adicional de JavaFX en Apple Silicon (M1/M2)

---

## 4. Instalación del entorno

Sigue estos pasos en orden. Están pensados para alguien que parte de cero.

### Paso 1 — Instalar Java JDK 24

1. Ve a https://www.oracle.com/java/technologies/downloads/
2. Descarga **JDK 24** para Windows x64 (instalador .exe)
3. Ejecuta el instalador y sigue los pasos
4. Verifica la instalación abriendo una terminal (CMD o PowerShell):

   ```
   java -version
   ```

   Debe mostrar: `java version "24.x.x"`

5. Configura la variable de entorno `JAVA_HOME`:
   - Panel de control → Sistema → Variables de entorno
   - Nueva variable del sistema: `JAVA_HOME` = ruta de instalación del JDK (por ejemplo: `C:\Program Files\Java\jdk-24`)
   - Añade `%JAVA_HOME%\bin` al `Path`

### Paso 2 — Instalar Maven

1. Descarga Maven desde https://maven.apache.org/download.cgi (elige el archivo `.zip` de la versión binaria)
2. Descomprime en `C:\Program Files\Maven\`
3. Configura variables de entorno:
   - Nueva variable: `MAVEN_HOME` = `C:\Program Files\Maven\apache-maven-x.x.x`
   - Añade `%MAVEN_HOME%\bin` al `Path`
4. Verifica:
   ```
   mvn -version
   ```

### Paso 3 — Instalar MariaDB

1. Descarga MariaDB desde https://mariadb.org/download/
2. Durante la instalación:
   - Puerto: `3308` (o el que uses en `DBConnection.java`)
   - Establece una contraseña para el usuario `root`
3. Verifica que el servicio MariaDB esté corriendo:
   - Servicios de Windows → `MariaDB` → Estado: En ejecución

### Paso 4 — Crear la base de datos

1. Abre HeidiSQL o cualquier cliente de MariaDB
2. Conéctate con el usuario `root`
3. Crea la base de datos:

   ```sql
   CREATE DATABASE shieldsecurity;
   ```

4. Importa el script SQL con todas las tablas del proyecto

### Paso 5 — Configurar la aplicación

1. Clona o descarga el proyecto en tu equipo
2. Abre el archivo: `src/main/java/org/example/shieldsecuritygestor/database/DBConnection.java`
3. Ajusta las credenciales:

   ```java
   String user = "root";
   String pass = "";
   String database = "shieldsecurity";

   ```

### Paso 6 — Ejecutar la aplicación

Desde la carpeta raíz del proyecto:

```
mvn clean javafx:run
```

O desde IntelliJ IDEA: ejecutar `HelloApplication.java` directamente.

---

## 5. Usuarios, permisos y estructura

### Usuarios del sistema

| Rol           | Descripción                       | Acceso                                     |
| ------------- | --------------------------------- | ------------------------------------------ |
| Master        | Administrador supremo del sistema | Acceso total                               |
| Administrador | Gestión de clientes y operaciones | Clientes, facturas, incidencias, servicios |
| Cliente       | Usuario final de la empresa       | Solo sus propios datos                     |

Los usuarios se almacenan en la tabla `usuario` de la base de datos con la columna `id_rol` (1=Master, 2=Administrador, 3=Cliente) y `activo` para habilitar o deshabilitar el acceso.

### Usuarios del sistema operativo

| Usuario SO          | Rol                         | Permisos                                       |
| ------------------- | --------------------------- | ---------------------------------------------- |
| Administrador local | Instalación y mantenimiento | Control total                                  |
| Usuario estándar    | Ejecución de la aplicación  | Solo lectura/escritura en carpeta del proyecto |

### Estructura de carpetas

```
ShieldSecurityGestor/
├── src/                  → código fuente
│   ├── main/java/        → clases Java
│   └── main/resources/   → vistas FXML y assets
├── target/               → compilado por Maven (no editar)
├── docs/                 → documentación
│   └── sistemas/         → este informe
└── pom.xml               → dependencias Maven
```

### Copias de seguridad

- La base de datos debe respaldarse periódicamente con:

  ```
  mysqldump -u root -p shieldsecurity > backup_FECHA.sql
  ```

- Guardar los backups en una carpeta externa o unidad de red
- Ruta sugerida: `C:\Backups\ShieldSecurity\`

---

## 6. Mantenimiento básico

### ¿Qué actualizar y cada cuánto?

| Elemento           | Frecuencia                   | Acción                                   |
| ------------------ | ---------------------------- | ---------------------------------------- |
| JDK                | Cada 6 meses                 | Revisar nuevas versiones LTS             |
| MariaDB            | Cada 3-6 meses               | Aplicar parches de seguridad             |
| Dependencias Maven | Al detectar vulnerabilidades | Actualizar `pom.xml`                     |
| Contraseñas de BD  | Cada 6 meses                 | Cambiar y actualizar `DBConnection.java` |
| Backups de BD      | Semanal (mínimo)             | Script automático o manual               |

### ¿Qué revisar periódicamente?

- Espacio en disco disponible para la base de datos
- Logs de MariaDB en busca de errores
- Que el servicio MariaDB esté activo antes de lanzar la aplicación
- Usuarios inactivos que ya no deberían tener acceso

### ¿Qué hacer si falla?

| Síntoma                               | Causa probable            | Solución                           |
| ------------------------------------- | ------------------------- | ---------------------------------- |
| "Problemas para conectar" al arrancar | MariaDB no está corriendo | Iniciar el servicio MariaDB        |
| "Access denied"                       | Credenciales incorrectas  | Revisar `DBConnection.java`        |
| La app no arranca                     | JDK no configurado        | Verificar `JAVA_HOME` y `PATH`     |
| Pantalla en blanco al hacer login     | FXML no encontrado        | Verificar rutas en los controllers |

---

## 7. Evidencias

Las siguientes capturas demuestran que la aplicación se ejecuta correctamente en el entorno descrito.

Incluir en la carpeta `/docs/sistemas/capturas/`:

- `01_login.png` — pantalla de login
- `02_panel_cliente.png` — vista del cliente con datos cargados
- `03_panel_admin.png` — vista del administrador
- `04_añadir_cliente.png` — formulario de alta de cliente
- `05_consola_conexion.png` — consola mostrando "Conexión Exitosa"
  La consola al arrancar muestra:

```
Conexion Exitosa
```

Lo que confirma que la conexión con MariaDB se establece correctamente.

---
