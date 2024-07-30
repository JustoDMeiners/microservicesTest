-- Script para crear las tablas Cuenta y Movimiento en H2
CREATE TABLE cuenta (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        numero_cuenta VARCHAR(255),
                        tipo_cuenta VARCHAR(255),
                        saldo_inicial DOUBLE,
                        estado BOOLEAN,
                        cliente_id BIGINT
);

CREATE TABLE movimiento (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            fecha DATE,
                            tipo_movimiento VARCHAR(255),
                            valor DOUBLE,
                            saldo DOUBLE,
                            cuenta_id BIGINT,
                            CONSTRAINT fk_cuenta FOREIGN KEY (cuenta_id) REFERENCES cuenta(id)
);

-- Insertando datos de ejemplo en la tabla cuenta
INSERT INTO cuenta (numero_cuenta, tipo_cuenta, saldo_inicial, estado, cliente_id) VALUES
                                                                                       ('478758', 'Ahorros', 2000, TRUE, 1),
                                                                                       ('225487', 'Corriente', 100, TRUE, 2),
                                                                                       ('495878', 'Ahorros', 0, TRUE, 3),
                                                                                       ('496825', 'Ahorros', 540, TRUE, 2);

-- Insertando datos de ejemplo en la tabla movimiento
INSERT INTO movimiento (fecha, tipo_movimiento, valor, saldo, cuenta_id) VALUES
                                                                             ('2022-10-02', 'Retiro', 575, 1425, 1),
                                                                             ('2022-02-10', 'Deposito', 600, 700, 2),
                                                                             ('2022-02-08', 'Deposito', 150, 150, 3),
                                                                             ('2022-02-08', 'Retiro', 540, 0, 4);
