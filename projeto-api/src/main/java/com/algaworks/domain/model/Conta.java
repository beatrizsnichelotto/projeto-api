package com.algaworks.osworks.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.algaworks.osworks.domain.enums.EnumTipoConta;
import com.algaworks.osworks.domain.exception.NegocioException;

@Entity
public class Conta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private OffsetDateTime dataLancamento;

	private BigDecimal valor;
	
	private OffsetDateTime dataPagamento;
	
	private Date dataVencimento;
	
	private String descricao;
	
	/* private String nome; */
	
	@ManyToOne
	private Pessoa pessoa;
	
	@Enumerated(EnumType.STRING)
	private EnumTipoConta tipo;

	@Enumerated(EnumType.STRING)
	private StatusConta status;

	public void finalizar() {
		if (StatusConta.FINALIZADA.equals(getStatus())) {
			throw new NegocioException("Essa conta já foi paga!");
		}
		setStatus(StatusConta.FINALIZADA);
		setDataPagamento(OffsetDateTime.now());
	}

	public StatusConta getStatusConta() {
		return status;
	}

	public void setStatusConta(StatusConta status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OffsetDateTime getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(OffsetDateTime dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public OffsetDateTime getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(OffsetDateTime dataPagamento) {
		this.dataPagamento = dataPagamento;
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

	/* public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	} */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conta other = (Conta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
