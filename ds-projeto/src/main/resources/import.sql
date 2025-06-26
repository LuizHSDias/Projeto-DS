INSERT INTO tb_usuario(nome, email, login, senha, nivel_acesso) VALUES ('Luiz Henrique', 'Luiz@gmail.com', 'Luiz', 'OD6jdnc@!', 'ADMIN');
INSERT INTO tb_usuario(nome, email, login, senha, nivel_acesso) VALUES ('Pedro Lucas', 'Pedro@gmail.com', 'Pedro', 'JJHH847&@', 'GESTOR');
INSERT INTO tb_usuario(nome, email, login, senha, nivel_acesso) VALUES ('Maria Helena', 'Maria@gmail.com','Maria', 'ODIDE535$', 'GESTOR');
INSERT INTO tb_usuario(nome, email, login, senha, nivel_acesso) VALUES ('José Germano', 'Jose@gmail.com', 'Jose',  'OD6fiUnc@!', 'GESTOR');

INSERT INTO tb_categoria(descricao, tipo) VALUES ('Contas residenciais', 'Fixa');
INSERT INTO tb_categoria(descricao, tipo) VALUES ('Moradia', 'Fixa');
INSERT INTO tb_categoria(descricao, tipo) VALUES ('Educação', 'Fixa');
INSERT INTO tb_categoria(descricao, tipo) VALUES ('Compras', 'Variável');
INSERT INTO tb_categoria(descricao, tipo) VALUES ('Lazer', 'Variável');

INSERT INTO tb_despesa(descricao, data_vencimento, data_pagamento, situacao, valor, id_usuario, id_categoria) VALUES ('Internet', '2025-06-05', '2025-06-05', 'paga', 1200.00, 1, 1);
INSERT INTO tb_despesa(descricao, data_vencimento, data_pagamento, situacao, valor, id_usuario, id_categoria) VALUES ('Conta de Água', '2025-06-10', '2025-06-09', 'paga', 85.75, 2, 2);
INSERT INTO tb_despesa(descricao, data_vencimento, data_pagamento, situacao, valor, id_usuario, id_categoria) VALUES ('Assinatura Netflix', '2025-06-20', '2025-06-21', 'pendente', 39.90, 3, 5);

INSERT INTO tb_receita(valor, id_categoria, id_usuario, data_entrada) VALUES (1500.00, 1, 3, '2025-06-11');
INSERT INTO tb_receita(valor, id_categoria, id_usuario, data_entrada) VALUES (600.00, 5, 1, '2025-06-09');
INSERT INTO tb_receita(valor, id_categoria, id_usuario, data_entrada) VALUES (2000.00, 4, 4, '2025-06-09');