INSERT INTO cozinhas (nome) values ('Tailandesa')
INSERT INTO cozinhas (nome) values ('Indiana')
INSERT INTO cozinhas (nome) values ('Italiana')
INSERT INTO cozinhas (nome) values ('Brasileira')

INSERT INTO estado (nome) values ('Pernambuco')
INSERT INTO estado (nome) values ('Paraiba')
INSERT INTO estado (nome) values ('Sergipe')

INSERT INTO cidade (nome, estado_id) values ('Recife', 1)
INSERT INTO cidade (nome, estado_id) values ('Camaragibe', 1)
INSERT INTO cidade (nome, estado_id) values ('Rio Tinto', 2)
INSERT INTO cidade (nome, estado_id) values ('Mamanguape', 2)
INSERT INTO cidade (nome, estado_id) values ('Aracaju', 3)
INSERT INTO cidade (nome, estado_id) values ('lagarto', 3)

-- , "38400-999", "Rua da avenida", "Centro", "582", "Perto da praca", 1
-- , endereco_cep, endereco_logradouro, endereco_bairro, endereco_numero, endereco_complemento, endereco_cidade_id

INSERT INTO restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cidade_id, endereco_bairro, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento) values ('Tonynho Bar', 7.49, 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 1, 'Centro', '38400-999', 'Rua da avenida', '582', 'Perto da praca')
INSERT INTO restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Casa Tropical', 2.95,1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP())
-- INSERT INTO restaurante (nome, taxa_frete, cozinha_id) values ('Tuk Tuk comida Indiana', 4.45,2)

INSERT INTO forma_pagamento (descricao) values ('Credito')
INSERT INTO forma_pagamento (descricao) values ('Debito')
INSERT INTO forma_pagamento (descricao) values ('Dinheiro')

INSERT INTO permissao (descricao, nome) values ('Tem acesso privilegiado', 'ADM')
INSERT INTO permissao (descricao, nome) values ('Tem acesso básico', 'USUARIO')

-- INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1,2),(2,1),(3,1),(2,2)

