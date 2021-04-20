package com.algaworks.osworks.domain.enums;

public enum EnumTipoConta {

	PAGAR("Conta a Pagar", 'D'), RECEBER("Conta a Receber", 'C');

	EnumTipoConta(String descricao, char sigla) {
		this.descricao = descricao;
		this.sigla = sigla;
	}

	private String descricao;

	private char sigla;

	public String getDescricao() {
		return descricao;
	}

	public char getSigla() {
		return sigla;
	}

	@Override
	public String toString() {
		return this.descricao;
	}

	public String getName() {
		return this.name();
	}
}
