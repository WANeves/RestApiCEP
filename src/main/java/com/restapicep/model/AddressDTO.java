package com.restapicep.model;


import java.io.Serializable;

public class AddressDTO implements Serializable {

    private static final long serialVersionUID = 2682046985632747474L;

    private String cep;
    private String endereco;
    private String bairro;
    private String cidade;
    private String estado;

    public AddressDTO() {
    }

    public AddressDTO(Address address) {
        this.cep = address.getCep();
        this.endereco = address.getEndereco();
        this.bairro = address.getBairro();
        this.cidade = address.getCidade();
        this.estado = address.getEstado();

    }

    public String getCep() {
        return cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

}
