package com.algaworks.osworks.api.model;

import java.math.BigDecimal;
import java.util.Date;

import com.algaworks.osworks.domain.enums.EnumTipoConta;
import com.algaworks.osworks.domain.model.Pessoa;
import com.algaworks.osworks.domain.model.StatusConta;


public class ContaResumoModel {

	private BigDecimal valor;
	private Date dataVencimento;
	private String descricao;
	private Pessoa pessoa;
	private EnumTipoConta tipo;
	private StatusConta status; 

	
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public EnumTipoConta getTipo() {
		return tipo;
	}
	public void setTipo(EnumTipoConta tipo) {
		this.tipo = tipo;
	}
	public StatusConta getStatus() {
		return status;
	}
	public void setStatus(StatusConta status) {
		this.status = status;
	}
	
	
	
}
