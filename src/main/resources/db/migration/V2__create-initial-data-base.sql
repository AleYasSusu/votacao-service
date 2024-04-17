CREATE SEQUENCE pauta_seq;
CREATE SEQUENCE sessao_seq;
CREATE SEQUENCE voto_seq;

CREATE TABLE pauta
(
    id   BIGINT DEFAULT nextval('pauta_seq') PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);

CREATE TABLE session
(
    id               BIGINT DEFAULT nextval('sessao_seq') PRIMARY KEY,
    data_inicio      TIMESTAMP NOT NULL,
    minutos_validade BIGINT    NOT NULL,
    pauta_id         BIGINT    NOT NULL,
    FOREIGN KEY (pauta_id) REFERENCES pauta (id)
);

CREATE TABLE vote
(
    id         BIGINT DEFAULT nextval('voto_seq') PRIMARY KEY,
    cpf        VARCHAR(255) NOT NULL,
    escolha    VARCHAR(255) NOT NULL,
    session_id BIGINT       NOT NULL,
    FOREIGN KEY (session_id) REFERENCES session (id)
);
