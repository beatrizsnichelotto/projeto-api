package com.algaworks.osworks.api.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.algaworks.osworks.domain.enums.EnumTipoConta;
import com.algaworks.osworks.domain.model.StatusConta;

public class ContaIdInput {

	@NotBlank
	private String descricao;
	
	@NotNull
	private BigDecimal valor;
	
	
	private Date dataVencimento;

	
	@Valid
	@NotNull
	private PessoaIdInput pessoa;
	
	private EnumTipoConta conta;
	
	private StatusConta status;
	
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public PessoaIdInput getPessoa() {
		return pessoa;
	}
	public void setPessoa(PessoaIdInput pessoa) {
		this.pessoa = pessoa;
	}
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public EnumTipoConta getConta() {
		return conta;
	}
	public void setConta(EnumTipoConta conta) {
		this.conta = conta;
	}
	public StatusConta getStatus() {
		return status;
	}
	public void setStatus(StatusConta status) {
		this.status = status;
	}
	
	

}
