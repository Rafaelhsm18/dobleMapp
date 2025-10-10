-- Creación y selección de la Base de Datos
CREATE DATABASE IF NOT EXISTS doblem 
CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE doblem;

-- =================================================================
-- 1. NÚCLEO DE SEGURIDAD Y PERSONAL
-- =================================================================
CREATE TABLE Empleados (
    ID_Empleado INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL, Apellidos VARCHAR(150) NOT NULL, Cargo VARCHAR(100), Activo BOOLEAN DEFAULT TRUE,
    creado_por VARCHAR(100), fecha_creacion DATETIME, modificado_por VARCHAR(100), fecha_modificacion DATETIME
) ENGINE=InnoDB;

CREATE TABLE Usuarios (
    ID_Usuario INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE, password VARCHAR(255) NOT NULL, activo BOOLEAN NOT NULL DEFAULT TRUE, ID_Empleado INT UNIQUE,
    FOREIGN KEY (ID_Empleado) REFERENCES Empleados(ID_Empleado)
) ENGINE=InnoDB;

CREATE TABLE Roles ( ID_Rol INT AUTO_INCREMENT PRIMARY KEY, nombre VARCHAR(50) NOT NULL UNIQUE ) ENGINE=InnoDB;
CREATE TABLE Usuarios_Roles ( ID_Usuario INT NOT NULL, ID_Rol INT NOT NULL, PRIMARY KEY (ID_Usuario, ID_Rolusuarios), FOREIGN KEY (ID_Usuario) REFERENCES Usuarios(ID_Usuario), FOREIGN KEY (ID_Rol) REFERENCES Roles(ID_Rol) ) ENGINE=InnoDB;

-- =================================================================
-- 2. GESTIÓN DE SUMINISTROS Y MATERIAS PRIMAS
-- =================================================================
CREATE TABLE Proveedores (
    ID_Proveedor INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(255) NOT NULL, CIF VARCHAR(50) UNIQUE, Contacto VARCHAR(255), Direccion TEXT,
    creado_por VARCHAR(100), fecha_creacion DATETIME, modificado_por VARCHAR(100), fecha_modificacion DATETIME
) ENGINE=InnoDB;

CREATE TABLE MateriasPrimas (
    ID_MateriaPrima INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(255) NOT NULL UNIQUE, Descripcion TEXT,
    creado_por VARCHAR(100), fecha_creacion DATETIME, modificado_por VARCHAR(100), fecha_modificacion DATETIME
) ENGINE=InnoDB;

CREATE TABLE Composicion_Proveedor (
    ID_Proveedor INT NOT NULL, ID_MateriaPrima_Suministrada INT NOT NULL, ID_MateriaPrima_Componente INT NOT NULL, Porcentaje DECIMAL(5, 2) NOT NULL,
    PRIMARY KEY (ID_Proveedor, ID_MateriaPrima_Suministrada, ID_MateriaPrima_Componente),
    FOREIGN KEY (ID_Proveedor) REFERENCES Proveedores(ID_Proveedor), FOREIGN KEY (ID_MateriaPrima_Suministrada) REFERENCES MateriasPrimas(ID_MateriaPrima), FOREIGN KEY (ID_MateriaPrima_Componente) REFERENCES MateriasPrimas(ID_MateriaPrima)
) ENGINE=InnoDB;

CREATE TABLE Lotes_Proveedor (
    ID_LoteProveedor INT AUTO_INCREMENT PRIMARY KEY,
    ID_MateriaPrima INT NOT NULL, ID_Proveedor INT NOT NULL, CodigoLote VARCHAR(100) NOT NULL,
    CantidadRecibida DECIMAL(10, 3) NOT NULL, UnidadMedida VARCHAR(20) NOT NULL, FechaRecepcion DATE NOT NULL, FechaCaducidad DATE,
    FOREIGN KEY (ID_MateriaPrima) REFERENCES MateriasPrimas(ID_MateriaPrima), FOREIGN KEY (ID_Proveedor) REFERENCES Proveedores(ID_Proveedor),
    creado_por VARCHAR(100), fecha_creacion DATETIME, modificado_por VARCHAR(100), fecha_modificacion DATETIME
) ENGINE=InnoDB;

-- =================================================================
-- 3. DEFINICIÓN DE PRODUCTOS Y PROCESOS
-- =================================================================
CREATE TABLE TiposProducto ( ID_TipoProducto INT AUTO_INCREMENT PRIMARY KEY, Nombre VARCHAR(100) NOT NULL UNIQUE ) ENGINE=InnoDB;
CREATE TABLE Etapas_Maestro ( ID_Etapa INT AUTO_INCREMENT PRIMARY KEY, Nombre_Etapa VARCHAR(255) NOT NULL UNIQUE, Descripcion_General TEXT ) ENGINE=InnoDB;
CREATE TABLE Productos (
    ID_Producto INT AUTO_INCREMENT PRIMARY KEY,
    ID_TipoProducto INT, Nombre VARCHAR(255) NOT NULL UNIQUE, Descripcion TEXT, PrecioVenta DECIMAL(10, 2),
    FOREIGN KEY (ID_TipoProducto) REFERENCES TiposProducto(ID_TipoProducto),
    creado_por VARCHAR(100), fecha_creacion DATETIME, modificado_por VARCHAR(100), fecha_modificacion DATETIME
) ENGINE=InnoDB;

CREATE TABLE Formulas (
    ID_Formula INT AUTO_INCREMENT PRIMARY KEY,
    ID_Producto INT NOT NULL, Nombre VARCHAR(255) NOT NULL, CantidadProducida DECIMAL(10, 3) NOT NULL, UnidadProducida VARCHAR(20) NOT NULL, Activa BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (ID_Producto) REFERENCES Productos(ID_Producto),
    creado_por VARCHAR(100), fecha_creacion DATETIME, modificado_por VARCHAR(100), fecha_modificacion DATETIME
) ENGINE=InnoDB;

CREATE TABLE Ingredientes_Formula (
    ID_Formula INT NOT NULL, ID_MateriaPrima INT NOT NULL, Cantidad DECIMAL(10, 4) NOT NULL, UnidadMedida VARCHAR(20) NOT NULL,
    PRIMARY KEY (ID_Formula, ID_MateriaPrima),
    FOREIGN KEY (ID_Formula) REFERENCES Formulas(ID_Formula), FOREIGN KEY (ID_MateriaPrima) REFERENCES MateriasPrimas(ID_MateriaPrima)
) ENGINE=InnoDB;

CREATE TABLE Plantillas_Proceso (
    ID_Producto INT NOT NULL, ID_Etapa INT NOT NULL, Orden INT NOT NULL, Instrucciones_Estandar TEXT NOT NULL,
    PRIMARY KEY (ID_Producto, ID_Etapa),
    FOREIGN KEY (ID_Producto) REFERENCES Productos(ID_Producto), FOREIGN KEY (ID_Etapa) REFERENCES Etapas_Maestro(ID_Etapa)
) ENGINE=InnoDB;

-- =================================================================
-- 4. CONTROL DE PRODUCCIÓN, CALIDAD E INVENTARIO FINAL
-- =================================================================
CREATE TABLE Lotes_Producto (
    ID_Lote INT AUTO_INCREMENT PRIMARY KEY,
    ID_Producto INT NOT NULL, Codigo_Lote_Interno VARCHAR(100) NOT NULL UNIQUE, Fecha_Creacion DATE NOT NULL,
    Cantidad_Teorica DECIMAL(10, 3) NOT NULL, Cantidad_Producida_Real DECIMAL(10, 3), Cantidad_Disponible DECIMAL(10, 3) DEFAULT 0, Estado VARCHAR(50) DEFAULT 'En Proceso',
    FOREIGN KEY (ID_Producto) REFERENCES Productos(ID_Producto),
    creado_por VARCHAR(100), modificado_por VARCHAR(100), fecha_modificacion DATETIME
) ENGINE=InnoDB;

CREATE TABLE Registro_Etapas_Lote (
    ID_Registro_Etapa INT AUTO_INCREMENT PRIMARY KEY,
    ID_Lote INT NOT NULL, ID_Etapa INT NOT NULL, ID_Empleado INT, Fecha_Completado DATETIME, Estado VARCHAR(50) DEFAULT 'Pendiente', Observaciones TEXT,
    FOREIGN KEY (ID_Lote) REFERENCES Lotes_Producto(ID_Lote), FOREIGN KEY (ID_Etapa) REFERENCES Etapas_Maestro(ID_Etapa), FOREIGN KEY (ID_Empleado) REFERENCES Empleados(ID_Empleado),
    creado_por VARCHAR(100), fecha_creacion DATETIME, modificado_por VARCHAR(100), fecha_modificacion DATETIME
) ENGINE=InnoDB;

CREATE TABLE Incidencias_Lote (
    ID_Incidencia INT AUTO_INCREMENT PRIMARY KEY,
    ID_Lote INT NOT NULL, ID_Empleado_Reporta INT, Fecha_Reporte DATETIME NOT NULL, Descripcion TEXT NOT NULL, Resuelta BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (ID_Lote) REFERENCES Lotes_Producto(ID_Lote), FOREIGN KEY (ID_Empleado_Reporta) REFERENCES Empleados(ID_Empleado),
    creado_por VARCHAR(100), fecha_creacion DATETIME, modificado_por VARCHAR(100), fecha_modificacion DATETIME
) ENGINE=InnoDB;

CREATE TABLE Stock_ProductoTerminado (
    ID_Stock INT AUTO_INCREMENT PRIMARY KEY,
    ID_Lote INT NOT NULL UNIQUE, ID_Producto INT NOT NULL, Cantidad_Inicial DECIMAL(10, 3) NOT NULL, Cantidad_Actual DECIMAL(10, 3) NOT NULL, Fecha_Entrada DATETIME NOT NULL, Ubicacion VARCHAR(100),
    FOREIGN KEY (ID_Lote) REFERENCES Lotes_Producto(ID_Lote), FOREIGN KEY (ID_Producto) REFERENCES Productos(ID_Producto),
    creado_por VARCHAR(100), fecha_creacion DATETIME, modificado_por VARCHAR(100), fecha_modificacion DATETIME
) ENGINE=InnoDB;
CREATE TABLE Movimientos_Stock (
    ID_Movimiento INT AUTO_INCREMENT PRIMARY KEY,
    ID_Stock INT NOT NULL, Tipo_Movimiento VARCHAR(50) NOT NULL, Cantidad DECIMAL(10, 3) NOT NULL, Fecha_Movimiento DATETIME NOT NULL, ID_Empleado INT, Descripcion TEXT,
    FOREIGN KEY (ID_Stock) REFERENCES Stock_ProductoTerminado(ID_Stock), FOREIGN KEY (ID_Empleado) REFERENCES Empleados(ID_Empleado),
    creado_por VARCHAR(100), fecha_creacion DATETIME, modificado_por VARCHAR(100), fecha_modificacion DATETIME
) ENGINE=InnoDB;

-- =================================================================
-- 5. GESTIÓN DE EQUIPAMIENTO Y MANTENIMIENTO
-- =================================================================
CREATE TABLE Equipos (
    ID_Equipo INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(255) NOT NULL, Descripcion TEXT, Fecha_Adquisicion DATE,
    Estado VARCHAR(50) NOT NULL DEFAULT 'Operativo' COMMENT 'Ej: Operativo, En Mantenimiento, Averiado',
    creado_por VARCHAR(100), fecha_creacion DATETIME, modificado_por VARCHAR(100), fecha_modificacion DATETIME
) ENGINE=InnoDB;

CREATE TABLE Mantenimientos_Equipo (
    ID_Mantenimiento INT AUTO_INCREMENT PRIMARY KEY,
    ID_Equipo INT NOT NULL, ID_Empleado_Realiza INT, Fecha_Mantenimiento DATETIME NOT NULL,
    Tipo_Mantenimiento VARCHAR(100) COMMENT 'Ej: Preventivo, Correctivo, Calibración', Descripcion TEXT NOT NULL,
    FOREIGN KEY (ID_Equipo) REFERENCES Equipos(ID_Equipo), FOREIGN KEY (ID_Empleado_Realiza) REFERENCES Empleados(ID_Empleado),
    creado_por VARCHAR(100), fecha_creacion DATETIME, modificado_por VARCHAR(100), fecha_modificacion DATETIME
) ENGINE=InnoDB;

USE doblem;
-- Primero, eliminamos la columna incorrecta que creaba el conflicto
ALTER TABLE Lotes_Producto DROP COLUMN Fecha_Creacion;

-- Después, añadimos la columna correcta que necesita la auditoría
ALTER TABLE Lotes_Producto ADD COLUMN fecha_creacion DATETIME NULL AFTER Codigo_Lote_Interno;


ALTER TABLE Ingredientes_Formula DROP PRIMARY KEY;
ALTER TABLE Ingredientes_Formula ADD COLUMN id INT AUTO_INCREMENT PRIMARY KEY FIRST;


-- Primero, eliminamos la clave foránea antigua
ALTER TABLE Plantillas_Proceso DROP FOREIGN KEY plantillas_proceso_ibfk_1;

-- Luego, cambiamos la columna
ALTER TABLE Plantillas_Proceso CHANGE COLUMN ID_Producto ID_TipoProducto INT NOT NULL;

-- Finalmente, creamos la nueva clave foránea apuntando a TiposProducto
ALTER TABLE Plantillas_Proceso ADD CONSTRAINT fk_plantilla_tipoproducto 
FOREIGN KEY (ID_TipoProducto) REFERENCES TiposProducto(ID_TipoProducto);

ALTER TABLE Plantillas_Proceso DROP COLUMN Instrucciones_Estandar;

ALTER TABLE Registro_Etapas_Lote ADD COLUMN confirmado BOOLEAN DEFAULT FALSE;


