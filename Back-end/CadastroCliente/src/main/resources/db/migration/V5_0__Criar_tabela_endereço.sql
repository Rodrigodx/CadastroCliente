CREATE TABLE endereco (
    id SERIAL PRIMARY KEY,
    cep VARCHAR(8),
    logradouro VARCHAR(100),
    bairro VARCHAR(50),
    cidade VARCHAR(50),
    uf CHAR(2),
    complemento VARCHAR(100),
    cliente_id INTEGER NOT NULL,
    CONSTRAINT fk_endereco_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id),
    CONSTRAINT uc_endereco UNIQUE (logradouro, cliente_id)
);
