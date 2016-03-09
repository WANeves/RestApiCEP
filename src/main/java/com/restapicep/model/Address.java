package com.restapicep.model;

import com.restapicep.util.Util;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Address {

    @Id
    @XmlElement
    private String cep;

    @XmlElement
    private String endereco;

    @XmlElement
    private String bairro;

    @XmlElement
    private String cidade;

    @XmlElement
    private String estado;

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return Util.format("#####-###", cep);
    }

    public void setCep(String cep) {
        this.cep = cep;
    }


    @Override
    public String toString() {
        return " cep=" + cep + ", endereco=" + endereco + ", bairro=" + bairro + ", cidade=" + cidade + ", estado=" + estado;
    }
}
