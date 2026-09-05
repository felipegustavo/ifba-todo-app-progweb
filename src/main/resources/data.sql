-- Executado depois que o Hibernate cria as tabelas e sequências.
-- O registro de controle impede que a carga seja repetida neste banco.
CREATE TABLE IF NOT EXISTS app_carga_inicial (
    nome VARCHAR(100) PRIMARY KEY
);

-- Usa a sequência da entidade para não conflitar com futuros cadastros.
-- A verificação por nome também preserva status de bancos já existentes.
INSERT INTO tb_status (id, nome)
SELECT NEXT VALUE FOR tb_status_seq, 'Para Fazer'
WHERE NOT EXISTS (SELECT 1 FROM app_carga_inicial WHERE nome = 'status_iniciais')
  AND NOT EXISTS (SELECT 1 FROM tb_status WHERE nome = 'Para Fazer');

INSERT INTO tb_status (id, nome)
SELECT NEXT VALUE FOR tb_status_seq, 'Fazendo'
WHERE NOT EXISTS (SELECT 1 FROM app_carga_inicial WHERE nome = 'status_iniciais')
  AND NOT EXISTS (SELECT 1 FROM tb_status WHERE nome = 'Fazendo');

INSERT INTO tb_status (id, nome)
SELECT NEXT VALUE FOR tb_status_seq, 'Feito'
WHERE NOT EXISTS (SELECT 1 FROM app_carga_inicial WHERE nome = 'status_iniciais')
  AND NOT EXISTS (SELECT 1 FROM tb_status WHERE nome = 'Feito');

INSERT INTO app_carga_inicial (nome)
SELECT 'status_iniciais'
WHERE NOT EXISTS (SELECT 1 FROM app_carga_inicial WHERE nome = 'status_iniciais');
