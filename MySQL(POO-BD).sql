CREATE DATABASE locadora;
USE locadora;

CREATE TABLE vestido(
id			INT				NOT NULL AUTO_INCREMENT,
cor			VARCHAR(50)		NULL,
tamanho		CHAR(4)			NULL,
estilo		VARCHAR(100)	NULL,
material	VARCHAR(100)		NULL,
situacao	INT 			NOT NULL DEFAULT 4,
preco		DECIMAL(6,2)	NULL,
observacao	VARCHAR(250)	NULL,
PRIMARY KEY (id)
);
ALTER TABLE vestido
AUTO_INCREMENT = 1111 ;
SET @@auto_increment_increment = 11 ;

CREATE TABLE cliente(
id			INT				NOT NULL AUTO_INCREMENT,
nome		VARCHAR(70)		NULL,
cpf			CHAR(11)		NULL,
email		VARCHAR(100)	NULL,
telefone	CHAR(11)		NULL,
dataNas		DATE			NULL,
PRIMARY KEY (id)
);
ALTER TABLE cliente
AUTO_INCREMENT = 1000;
SET @@auto_increment_increment = 10;


CREATE TABLE aluguel(
cliente_id	INT				NOT NULL,
vestido_id	INT				NOT NULL,
dataInicio	DATE			NOT NULL DEFAULT (NOW()),
dataFim		DATE			NULL,
precoTotal	DECIMAL(6,2)	NULL,
status		BOOLEAN			NULL DEFAULT TRUE,
PRIMARY KEY (cliente_id, vestido_id, dataInicio),
FOREIGN KEY (cliente_id) REFERENCES cliente(id),
FOREIGN KEY (vestido_id) REFERENCES vestido(id)
);
ALTER TABLE aluguel
ADD CONSTRAINT check_dataInicio
CHECK (dataInicio<dataFim);

INSERT INTO vestido (cor, tamanho, material, situacao ,preco, observacao) VALUES
('Vermelho', 'GG', 'Algodão', 1,199.90, 'Vestido casual para o dia a dia'),
('Preto', 'XL', 'Poliéster', 1,229.00, 'Ideal para eventos formais'),
('Azul Marinho', 'GGXL', 'Veludo',1,299.99, 'Elegante, ideal para festas'),
('Branco', 'P', 'Linho', 1,1179.90,'Fresco e leve para o verão'),
('Rosa Claro', 'M', 'Seda', 1,349.00,'Para ocasiões especiais'),
('Preto', 'G', 'Crepe',1, 259.90, 'Clássico, ótimo para o trabalho'),
('Verde Oliva', 'PP', 'Jeans',1, 159.00, 'Casual, com toque moderno'),
('Nude', 'G', 'Renda',1, 399.90, 'Para casamentos ou eventos formais'),
('Amarelo', 'M', 'Malha',1, 120.00, 'Confortável, ideal para o dia a dia'),
('Lilás', 'GG', 'Tricoline',1, 199.90, 'Leve e confortável para o verão');

INSERT INTO vestido (cor, tamanho, material, situacao ,preco, observacao) VALUES
('Rosa Choque', 'PP', 'Algodão', 1,199.90, 'Vestido casual para o dia a dia');


INSERT INTO cliente (nome, cpf, email, telefone, dataNas) VALUES
('João Silva', '12345678901', 'joao.silva@email.com', 11987654321, '1990-03-15'),
('Maria Oliveira', '23456789012', 'maria.oliveira@email.com', 21987654321, '1985-07-22'),
('Pedro Santos', '34567890123', 'pedro.santos@email.com', 31987654321, '1992-12-05'),
('Ana Costa', '45678901234', 'ana.costa@email.com', 41987654321, '1990-06-10'),
('Lucas Almeida', '56789012345', 'lucas.almeida@email.com', 51987654321, '1988-08-18'),
('Carla Pereira', '67890123456', 'carla.pereira@email.com', 61987654321, '1980-11-02'),
('Ricardo Souza', '78901234567', 'ricardo.souza@email.com', 71987654321, '1995-02-28'),
('Juliana Fernandes', '89012345678', 'juliana.fernandes@email.com', 81987654321, '1993-04-07'),
('Fernanda Lima', '90123456789', 'fernanda.lima@email.com', 91987654321, '1997-01-14'),
('Marcos Rodrigues', '01234567890', 'marcos.rodrigues@email.com', 11987654321, '1986-09-20');

INSERT INTO aluguel (cliente_id, vestido_id,dataInicio,dataFim,precoTotal) VALUES
(1051,1161,'2024-08-03','2024-08-08',159.00),
(1051,1171,'2024-10-24','2024-10-30',349.00),
(1071,1191,'2021-05-07','2021-05-10',400.50),
(1091,1171,'2024-08-03','2024-08-08',199.90),
(1071,1201,'2018-04-20','2024-11-29',200.00);

SELECT * FROM cliente;
SELECT * FROM vestido;
SELECT * FROM aluguel;

