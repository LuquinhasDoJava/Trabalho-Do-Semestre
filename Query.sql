CREATE DATABASE locadora;
GO
USE locadora;
GO
CREATE TABLE vestido(
id			  INT				      NOT NULL IDENTITY(1111,11),
cor			  VARCHAR(50)		  NULL,
tamanho		CHAR(4)			    NULL,
estilo		VARCHAR(100)    NULL,
material	VARCHAR(70)	    NULL,
preco		DECIMAL(6,2)	    NULL,
observacao	VARCHAR(100)  NULL,
PRIMARY KEY (id)
);

CREATE TABLE cliente(
id			INT				    NOT NULL IDENTITY(1000 ,10),
nome		VARCHAR(70)		NULL,
cpf			CHAR(11)		  NULL,
email		VARCHAR(100)	NULL,
telefone	BIGINT			NULL,
dataNas		DATE			  NOT NULL CHECK (DATEDIFF(YEAR,dataNas, GETDATE())> 17);,
PRIMARY KEY (id)
);


CREATE TABLE aluguel(
cliente_id	INT					    NOT NULL,
vestido_id	INT					    NOT NULL,
dataInicio	DATE				    NOT NULL DEFAULT NOW(),
dataFim		DATE				      NOT NULL,
precoTotal	DECIMAL(6,2)		NOT NULL,
situacao	BINARY(1)			    NOT NULL DEFAULT 1	,
PRIMARY KEY (cliente_id, vestido_id, dataInicio),
FOREIGN KEY (cliente_id) REFERENCES cliente(id),
FOREIGN KEY (vestido_id) REFERENCES vestido(id)
);
ALTER TABLE aluguel
ADD CONSTRAINT check_dataInicio
CHECK (dataInicio>dataFim);

SELECT * FROM aluguel;
SELECT * FROM vestido;
SELECT * FROM cliente;
