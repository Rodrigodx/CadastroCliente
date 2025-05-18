CREATE TABLE usuario (
    id SERIAL PRIMARY KEY,
    login VARCHAR(255) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    data_atual_senha DATE NOT NULL,
    role VARCHAR(30),
    cliente_id INTEGER,
    CONSTRAINT cliente_fk FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);
