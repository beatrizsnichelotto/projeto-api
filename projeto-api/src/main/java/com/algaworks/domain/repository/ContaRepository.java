package com.algaworks.osworks.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.algaworks.osworks.domain.model.Conta;
import com.algaworks.osworks.domain.model.Pessoa;
import com.algaworks.osworks.domain.model.StatusConta;

public interface ContaRepository extends CrudRepository<Conta, Long> {

	@Query("select c from Conta c where c.pessoa = :pessoa")
	public List<Conta> listarPorPessoa(@Param("pessoa") Pessoa pessoa);

	public List<Conta> findByDescricaoContainingIgnoreCase(String descricao);

	public List<Conta> findAllByPessoa(Pessoa pessoa);
	
	public List<Conta> findAllByStatusAndPessoa(StatusConta status, Pessoa pessoa);
	
	public List<Conta> findAllByStatus(StatusConta status);

	public List<Conta> findAllByStatusAndPessoa(StatusConta aberta, Optional<Pessoa> pessoa);



}