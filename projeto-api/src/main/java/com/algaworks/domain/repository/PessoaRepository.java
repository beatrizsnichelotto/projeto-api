package com.algaworks.osworks.domain.repository;


import java.util.Date;

import org.springframework.data.repository.CrudRepository;

import com.algaworks.osworks.domain.model.Pessoa;

public interface PessoaRepository extends CrudRepository<Pessoa, Long> {

	public Pessoa findByCpf(String cpf);
	
	public Pessoa findByDataNascimento(Date dataNascimento);
}
