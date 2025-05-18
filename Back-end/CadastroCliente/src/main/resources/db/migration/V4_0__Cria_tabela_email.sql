CREATE TABLE email (
    id SERIAL PRIMARY KEY,
    email VARCHAR(100) NOT NULL,
    cliente_id INTEGER NOT NULL,
    CONSTRAINT fk_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id),
    CONSTRAINT uc_email UNIQUE (email, cliente_id)
);