-- Criar a tabela de missões primeiro, porque `tb_cadastro` depende dela
CREATE TABLE TB_MISSOES (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(255) NOT NULL,
dificuldade VARCHAR(255) NOT NULL
);


-- Criar a tabela de ninjas
CREATE TABLE TB_CADASTRO (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(255) NOT NULL,
img_url VARCHAR(255),
email VARCHAR(255) UNIQUE NOT NULL,
idade INT NOT NULL,
Missoes_id BIGINT, -- Chave estrangeira referenciando a tabela de missões
CONSTRAINT fk_missao FOREIGN KEY (Missoes_id) REFERENCES TB_MISSOES(id) ON DELETE SET NULL
);