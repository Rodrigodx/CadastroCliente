CREATE TABLE telefone (
    id SERIAL PRIMARY KEY,
    tipo CHAR(1) NOT NULL CHECK (tipo IN ('C', 'R', 'L')),
    numero VARCHAR(11) NOT NULL,
    cliente_id INTEGER NOT NULL,
    CONSTRAINT fk_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id),
    CONSTRAINT uc_telefone UNIQUE (numero, cliente_id)
);

