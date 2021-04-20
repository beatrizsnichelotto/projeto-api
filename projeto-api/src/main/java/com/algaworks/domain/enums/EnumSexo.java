package com.algaworks.osworks.domain.enums;

public enum EnumSexo {
	
    MASCULINO("Masculino"),
    FEMININO("Feminino");
    
    EnumSexo(String descricao) {
        this.descricao = descricao;
    }
    
    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return this.descricao;
    }
    
    
}
