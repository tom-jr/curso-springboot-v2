package com.tom.curso.domain;

import javax.persistence.Entity;

import com.tom.curso.domain.enums.EstadoPagamento;
@Entity
public class PagamentoComCartao extends Pagamento {
    private static final long serialVersionUID = 1L;
    // attributes

    private Integer numeroDeParcelas;

    // cosntructors

    public PagamentoComCartao() {
    }

    public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numeroDeParcelas) {
        super(id, estado, pedido);
        this.numeroDeParcelas = numeroDeParcelas;
    }

    // getters and setters

    public Integer getNumeroDeParcelas() {
        return numeroDeParcelas;
    }

    public void setNumeroDeParcelas(Integer numeroDeParcelas) {
        this.numeroDeParcelas = numeroDeParcelas;
    }

}
