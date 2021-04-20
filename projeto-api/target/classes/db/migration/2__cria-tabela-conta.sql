CREATE TABLE conta (
	
	id bigint not null auto_increment ,
	pessoa_id bigint not null,
	data_lancamento datetime null,
	valor decimal(19, 2) not null,
	data_pagamento date  null,
	data_vencimento date null,
	descricao varchar(255) not null,
	tipo varchar(55) null,

	primary key (id)

	);
	
	alter table conta add constraint fk_conta_pessoa
	foreign key (pessoa_id) references pessoa (id);
