INSERT INTO cozinhas (id,nome) values (1,'Tailandesa')
INSERT INTO cozinhas (id,nome) values (2,'Indiana')
INSERT INTO cozinhas (id,nome) values (3,'Italiana')
INSERT INTO cozinhas (id,nome) values (4,'Brasileira')

INSERT INTO restaurante (nome, taxa_frete, cozinha_id) values ('Tonynho Bar', 7.49,1)
INSERT INTO restaurante (nome, taxa_frete, cozinha_id) values ('Casa Tropical', 2.95,1)
INSERT INTO restaurante (nome, taxa_frete, cozinha_id) values ('Tuk Tuk comida Indiana', 4.45,2)

INSERT INTO estado (nome) values ('Pernambuco')
INSERT INTO estado (nome) values ('Paraiba')
INSERT INTO estado (nome) values ('Sergipe')

INSERT INTO cidade (nome, estado_id) values ('Recife', 1)
INSERT INTO cidade (nome, estado_id) values ('Camaragibe', 1)
INSERT INTO cidade (nome, estado_id) values ('Rio Tinto', 2)
INSERT INTO cidade (nome, estado_id) values ('Mamanguape', 2)
INSERT INTO cidade (nome, estado_id) values ('Aracaju', 3)
INSERT INTO cidade (nome, estado_id) values ('lagarto', 3)

INSERT INTO forma_pagamento (descricao) values ('Credito')
INSERT INTO forma_pagamento (descricao) values ('Debito')
INSERT INTO forma_pagamento (descricao) values ('Dinheiro')

INSERT INTO permissao (descricao, nome) values ('Tem acesso privilegiado', 'ADM')
INSERT INTO permissao (descricao, nome) values ('Tem acesso básico', 'USUARIO')

INSERT INTO restaurante_forma_pagamento (restaurante_id, restaurante_forma_pagamento_id) values (1,2),(2,1),(3,1),(2,2)

