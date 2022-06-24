INSERT INTO public.estado(
	id, ativo, dataalteracao, datacadastro, version, nome, uf)
	VALUES (1, true, null, NOW(), 0, 'Tocantins', 'TO');

INSERT INTO public.cidade(
	id, ativo, dataalteracao, datacadastro, version, nome, estado_id)
	VALUES (1, true, null, NOW(), 0, 'Palmas', 1);

INSERT INTO public.endereco(
	id, ativo, dataalteracao, datacadastro, version, bairro, cep, complemento, numero, rua, cidade_id)
	VALUES (1, true, null, NOW(), 0, 'Plano Diretor Norte', 77001-032, null, 18, '103 Norte',1);

INSERT INTO public.pessoa(
	id, ativo, dataalteracao, datacadastro, version, email, telefone, endereco_id)
	VALUES (1, true, null, NOW(), 0, 'lumyth.br@gmail.com', '63992651081', 1);

INSERT INTO public.pessoafisica(
	cpf, datanascimento, nome, sexo, sobrenome, id)
	VALUES ('077.912.301-86', '2002-06-16', 'Luan', 1, 'Coêlho de Souza', 1);

INSERT INTO public.funcionario(
	id, ativo, dataalteracao, datacadastro, version, cargo, salario, senha, pessoafisica_id)
	VALUES (1, true, null, NOW(), 0, 1, 1200, null, 1);