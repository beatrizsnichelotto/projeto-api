package com.algaworks.osworks.domain.service;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.algaworks.osworks.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.osworks.domain.exception.NegocioException;
import com.algaworks.osworks.domain.model.Conta;
import com.algaworks.osworks.domain.model.Pessoa;
import com.algaworks.osworks.domain.model.StatusConta;
import com.algaworks.osworks.domain.repository.ContaRepository;
import com.algaworks.osworks.domain.repository.PessoaRepository;

@Service
public class CadastroContaService {

	@Autowired
	private ContaRepository contaRepository;

	@Autowired
	private PessoaRepository pessoaRepository;

	//CRIAR
	
	public Conta criar(Conta conta) {
		Pessoa pessoa = pessoaRepository.findById(conta.getPessoa().getId())

				.orElseThrow(() -> new NegocioException("Pessoa não encontrada"));

		/* conta.setNome(pessoa.getNome()); */
		conta.setPessoa(pessoa);
		conta.setDataLancamento(OffsetDateTime.now());
		conta.setStatus(StatusConta.ABERTA);

		if (dataMenorHoje(conta.getDataVencimento()) == false) {
			throw new NegocioException("Data vencimento não pode ser menor que data de lançamento!");
		}
		return contaRepository.save(conta);
	}
	
	//EXCLUIR

	public ResponseEntity<Conta> excluir(Long contaId) {
		if (isAberta(contaId) == true) {
			throw new NegocioException("Essa conta está em ABERTO! Finalize para poder excluir.");
		}
		if (!existePorId(contaId)) {
			return ResponseEntity.notFound().build();
		}
		contaRepository.deleteById(contaId);
		return ResponseEntity.noContent().build();
	}

	private boolean existePorId(Long contaId) {
		return contaRepository.existsById(contaId);
	}

	public boolean dataMenorHoje(Date dataVencimento) {
		Date dataAtual = new Date();

		if (dataVencimento.before(dataAtual)) {
			return false;
		}
		return true;
	}
	
	//FINALIZAR

	public void finalizar(Long contaId) {
		Conta conta = buscar(contaId);
		conta.finalizar();

		contaRepository.save(conta);
	}
	
	//BUSCAR

	private Conta buscar(Long contaId) {
		Conta conta = contaRepository.findById(contaId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Conta não encontrada"));
		return conta;
	}

	public boolean isAberta(Long contaId) {
		Conta conta = buscar(contaId);
		if (conta.getStatus().equals(StatusConta.ABERTA)) {
			return true;
		}
		return false;
	}
	
	//LISTAS

	public List<Conta> listarAberta() {
		return contaRepository.findAllByStatus(StatusConta.ABERTA);
	}

	public List<Conta> listarFinalizada() {
		return contaRepository.findAllByStatus(StatusConta.FINALIZADA);
	}

	public List<Conta> listarPessoaAberta(Long pessoaId) {
		Pessoa pessoa = pessoaRepository.findById(pessoaId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Pessoa não encontrada"));

		return contaRepository.findAllByStatusAndPessoa(StatusConta.ABERTA, pessoa);
	}
	
	public List<Conta> listarPessoaFinalizada(Long pessoaId) {
		Pessoa pessoa = pessoaRepository.findById(pessoaId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Pessoa não encontrada"));

		return contaRepository.findAllByStatusAndPessoa(StatusConta.FINALIZADA, pessoa);
	}
}
