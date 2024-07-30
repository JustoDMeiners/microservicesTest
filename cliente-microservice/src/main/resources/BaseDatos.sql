CREATE TABLE IF NOT EXISTS persona (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         nombre VARCHAR(255),
                         genero VARCHAR(255),
                         edad INT,
                         identificacion VARCHAR(255),
                         direccion VARCHAR(255),
                         telefono VARCHAR(255),
                         estado BOOLEAN,
                         tipo_persona VARCHAR(31) NOT NULL,
                         contraseña VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS cliente (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         nombre VARCHAR(255),
                         genero VARCHAR(255),
                         edad INT,
                         identificacion VARCHAR(255),
                         direccion VARCHAR(255),
                         telefono VARCHAR(255),
                         tipo_persona VARCHAR(255),
                         cliente_id VARCHAR(255),
                         contraseña VARCHAR(255),
                         estado BOOLEAN
);


