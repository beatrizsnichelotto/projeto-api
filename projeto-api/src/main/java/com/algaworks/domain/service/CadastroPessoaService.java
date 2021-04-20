package com.algaworks.osworks.domain.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.osworks.api.ValidaCpf.ValidaCpf;
import com.algaworks.osworks.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.osworks.domain.exception.NegocioException;
import com.algaworks.osworks.domain.model.Conta;
import com.algaworks.osworks.domain.model.Pessoa;
import com.algaworks.osworks.domain.model.StatusConta;
import com.algaworks.osworks.domain.repository.ContaRepository;
import com.algaworks.osworks.domain.repository.PessoaRepository;

@Service
public class CadastroPessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private ContaRepository contaRepository;

	//SALVAR CADASTRO PESSOA
	
	public Pessoa salvar(Pessoa pessoa) {
		Pessoa pessoaExistente = pessoaRepository.findByCpf(pessoa.getCpf());
		if (pessoaExistente != null && !pessoaExistente.equals(pessoa)) {
			throw new NegocioException("Esse CPF já está cadastrado. Utilize outro CPF para novo Cadastro!");
		}
		if (ValidaCpf.isCPF(pessoa.getCpf()) == false) {
			throw new NegocioException("Esse CPF não é válido! Insira um CPF válido!");
		}

		/*
		 * if (dataMenorHoje(pessoa.getDataNascimento()) == false) { throw new
		 * NegocioException("A data de nascimento não pode ser maior que a data atual");
		 * }
		 */

		if (validaSeMaiorIdade(pessoa.getDataNascimento()) == false) {
			throw new NegocioException("Não é possível cadastrar menores de 18 anos!");
		}

		return pessoaRepository.save(pessoa);

	}
	//VALIDA SE MAIOR IDADE
	
	@SuppressWarnings("deprecation")
	public boolean validaSeMaiorIdade(Date dataNascimentoPessoa) {
		Date dataAtual = new Date();
		Date dataNascimento = dataNascimentoPessoa;

		int anoAtual = dataAtual.getYear();
		Date dataValida = new Date();
		dataValida.setYear(anoAtual - 18);
		if (dataNascimento.before(dataValida)) {
			return true;

		}
		return true;

	}
	
	//DATA MASCIMENTO NÃO PODE SER MAIOR QUE DATA ATUAL
	
	public boolean dataMenorHoje(Date dataNascimento) {
		Date dataAtual = new Date();

		if (dataNascimento.after(dataAtual)) {
			return false;
		}
		return true;
	}
	
	//EXCLUIR
	
	public void excluir(Long pessoaId) {
		Pessoa pessoa = pessoaRepository.findById(pessoaId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Pessoa não encontrada"));
		deletaContasDaPessoa(pessoa);
		pessoaRepository.deleteById(pessoaId);

	}
	//LISTAR
	
	public List<Pessoa> listar() {
		return (List<Pessoa>) pessoaRepository.findAll();
	}
	
	//BUSCAR 
	
	public Optional<Pessoa> buscarPorId(Long pessoaId) {
		return pessoaRepository.findById(pessoaId);

	}
	
	//ADICIONAR PESSOA
	
	public Pessoa adicionar(Pessoa pessoa) {
		return pessoaRepository.save(pessoa);

	}
	
	//ATUALIZAR PESSOA

	public void atualizar(Long pessoaId) {
		pessoaRepository.existsById(pessoaId);
	}
	
	//EXCLUIR CONTAS PESSOA
	
	private void deletaContasDaPessoa(Pessoa pessoa) {
		List<Conta> contas = contaRepository.findAllByPessoa(pessoa);
		for (Conta conta : contas) {
			if (pessoaTemContaAberta(conta)) {
				throw new NegocioException("Essa pessoa ainda tem contas em aberto, você não pode exclui-la");
			}
			contaRepository.delete(conta);
		}
	}

	public boolean pessoaTemContaAberta(Conta conta) {
		if (conta.getStatus().equals(StatusConta.ABERTA)) {
			return true;
		}
		return false;
	}

	/**
	 * public boolean verificacpf(String cpf) { if (ValidaCpf.isCPF(cpf) == true) {
	 * return true; } else { return false; } }
	 **/

}
