CREATE TABLE IF NOT EXISTS empresas(
    id SERIAL PRIMARY KEY,
    nome VARCHAR(200) NOT NULL,
    CONSTRAINT nome_empresa_unique UNIQUE (nome)
)


