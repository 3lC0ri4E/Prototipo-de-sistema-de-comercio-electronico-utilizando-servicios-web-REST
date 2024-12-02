-- Crear la base de datos si no existe
CREATE DATABASE IF NOT EXISTS ecommerce;

-- Seleccionar la base de datos
USE ecommerce;

-- Crear la tabla de artículos
CREATE TABLE IF NOT EXISTS articulos (
    id_articulo INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion TEXT NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    cantidad INT NOT NULL
);

-- Crear la tabla de fotos de artículos
CREATE TABLE IF NOT EXISTS fotos_articulos (
    id_foto INT AUTO_INCREMENT PRIMARY KEY,
    foto BLOB NOT NULL,  -- Almacena la imagen en formato binario
    id_articulo INT NOT NULL,
    FOREIGN KEY (id_articulo) REFERENCES articulos(id_articulo)
);

-- Crear la tabla del carrito de compras
CREATE TABLE IF NOT EXISTS carrito_compra (
    id_articulo INT NOT NULL,
    cantidad INT NOT NULL,
    PRIMARY KEY (id_articulo),
    FOREIGN KEY (id_articulo) REFERENCES articulos(id_articulo)
);
