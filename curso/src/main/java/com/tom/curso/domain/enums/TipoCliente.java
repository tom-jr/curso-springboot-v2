package com.tom.curso.domain.enums;

public enum TipoCliente {

    PESSOA_FISICA(1, "Pessoa física"), PESSOA_JURIDICA(2, "Pessoa juridica");

    // attributes

    private Integer cod;
    private String descricao;

    // constructors

    private TipoCliente(Integer cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }


    //methods
    public static TipoCliente integerToEnum(Integer t){
        for (TipoCliente tipo: TipoCliente.values() ){
            if(tipo.getCod().equals(t)){
                return tipo;
        }
    }
        throw new IllegalArgumentException("valor invalido, value: " + t);
    }

    // getters
    public Integer getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;

    }

}
