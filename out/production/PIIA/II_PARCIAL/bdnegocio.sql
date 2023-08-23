CREATE DATABASE BDNegocio;

USE BDNegocio;

CREATE TABLE producto (
    codigoProducto INT AUTO_INCREMENT PRIMARY KEY,
    nombreProducto VARCHAR(255),
    precioUnitario DECIMAL(10, 2),
    cantidadProducto INT,
    fechaVencimiento DATE
);
SELECT * FROM producto;

