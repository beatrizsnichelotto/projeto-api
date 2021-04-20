package com.algaworks.osworks.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.osworks.domain.model.Pessoa;
import com.algaworks.osworks.domain.repository.PessoaRepository;
import com.algaworks.osworks.domain.service.CadastroPessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

	@Autowired
	private CadastroPessoaService cadastroPessoa;

	@Autowired
	private PessoaRepository pessoaRepository;
	
	//LISTAR PESSOAS
	
	@GetMapping
	public List<Pessoa> listar() {
		return (List<Pessoa>) cadastroPessoa.listar();
	}
	
	//BUSCAR PESSOA
	
	@GetMapping("/{pessoaId}")
	public ResponseEntity<Pessoa> buscar(@Valid @PathVariable Long pessoaId) {
		Optional<Pessoa> pessoa = cadastroPessoa.buscarPorId(pessoaId);

		if (pessoa.isPresent()) {
			return ResponseEntity.ok(pessoa.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	
	//ATUALIZAR PESSOA
	
	@PutMapping("/{pessoaId}")
	public ResponseEntity<Pessoa> atualizar(@Valid @PathVariable Long pessoaId, @RequestBody Pessoa pessoa) {

		if (!pessoaRepository.existsById(pessoaId)) {
			return ResponseEntity.notFound().build();

		}
		pessoa.setId(pessoaId);
		pessoa = cadastroPessoa.salvar(pessoa);
		return ResponseEntity.ok(pessoa);
	}
	
	//EXCLUIR PESSOA
	
	@DeleteMapping("/{pessoaId}")
	public ResponseEntity<Void> remover(@PathVariable Long pessoaId) {
		if (!pessoaRepository.existsById(pessoaId)) {
			return ResponseEntity.notFound().build();
		}

		cadastroPessoa.excluir(pessoaId);

		return ResponseEntity.noContent().build();

	}

}