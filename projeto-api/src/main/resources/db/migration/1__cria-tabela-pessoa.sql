CREATE TABLE pessoa (
	
	id bigint not null auto_increment ,
	nome varchar(60) not null,
	cpf varchar(20) not null,
	data_nascimento date not null,
	telefone varchar(20) not null,
	sexo varchar(10) not null,
	ativo int not null,

	primary key (id)

	);