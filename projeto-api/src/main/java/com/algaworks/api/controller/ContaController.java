package com.algaworks.osworks.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
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

import com.algaworks.osworks.api.model.ContaIdInput;
import com.algaworks.osworks.api.model.ContaResumoModel;
import com.algaworks.osworks.domain.model.Conta;
import com.algaworks.osworks.domain.model.StatusConta;
import com.algaworks.osworks.domain.repository.ContaRepository;
import com.algaworks.osworks.domain.service.CadastroContaService;

@RestController
@RequestMapping("/contas")
public class ContaController {

	@Autowired
	private CadastroContaService cadastroConta;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ContaRepository contaRepository;

	// LISTAS

	@GetMapping
	public List<Conta> listar() {
		return (List<Conta>) contaRepository.findAll();
	}

	@GetMapping("/aberta")
	public List<Conta> listarAberta() {
		return contaRepository.findAllByStatus(StatusConta.ABERTA);
	}

	@GetMapping("/finalizada")
	public List<Conta> listarFinalizada() {
		return contaRepository.findAllByStatus(StatusConta.FINALIZADA);
	}

	@GetMapping("/aberta/{pessoaId}")
	public List<Conta> listarPessoaAberta(@Valid @PathVariable Long pessoaId) {
		List<Conta> conta = cadastroConta.listarPessoaAberta(pessoaId);

		return conta;
	}

	@GetMapping("/finalizada/{pessoaId}")
	public List<Conta> listarPessoaFinalizada(@Valid @PathVariable Long pessoaId) {
		List<Conta> conta = cadastroConta.listarPessoaFinalizada(pessoaId);

		return conta;
	}
	
	//BUSCAR CONTA
	
	@GetMapping("/{contaId}")
	public ResponseEntity<Conta> buscar(@PathVariable Long contaId) {
		Optional<Conta> conta = contaRepository.findById(contaId);

		if (conta.isPresent()) {
			return ResponseEntity.ok(conta.get());
		}

		return ResponseEntity.notFound().build();
	}
	
	//ADICIONAR CONTA

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Conta adicionar(@Valid @RequestBody Conta conta) {
		return cadastroConta.criar(conta);

	}
	
	//ATUALIZAR CONTAS

	@PutMapping("/{contaId}")
	public ResponseEntity<Conta> atualizar(@Valid @PathVariable Long contaId, @RequestBody Conta conta) {

		if (!contaRepository.existsById(contaId)) {
			return ResponseEntity.notFound().build();

		}
		conta.setId(contaId);
		conta = cadastroConta.criar(conta);

		return ResponseEntity.ok(conta);
	}
	
	//EXCLUIR CONTAS

	@DeleteMapping("/{contaId}")
	public ResponseEntity<Conta> remover(@PathVariable Long contaId) {
		return cadastroConta.excluir(contaId);

	}
	
	//FINALIZAR CONTAS

	@PutMapping("/{contaId}/finalizar")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void finalizar(@PathVariable Long contaId) {
		cadastroConta.finalizar(contaId);
	}

	@SuppressWarnings("unused")
	private ContaResumoModel toModel(Conta conta) {
		return modelMapper.map(conta, ContaResumoModel.class);
	}

	@SuppressWarnings("unused")
	private Conta toEntity(ContaIdInput contaIdInput) {
		return modelMapper.map(contaIdInput, Conta.class);
	}
}
