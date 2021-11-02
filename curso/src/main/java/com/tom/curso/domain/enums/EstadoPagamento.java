package com.tom.curso.domain.enums;

public enum EstadoPagamento {

    PENDENTE(1, "Pendente"), QUITADO(2, "Quitado"), CANCELADO(3, "Cancelado");

    // attributes

    private Integer cod;

    private String descricao;

    private EstadoPagamento(Integer cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    // methods

    public static EstadoPagamento fromIntegerToEnum(Integer cod) {
        if (cod.equals(null)) {
            return null;
        }
        
        for (EstadoPagamento estadoPagamento : EstadoPagamento.values()) {
            if (cod.equals(estadoPagamento.getCod())) {
                return estadoPagamento;
            }
        }
        throw new IllegalArgumentException("Codigo inválido");

    }

    // getters
    public Integer getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

}
