package br.ufsm.ceesp.ceee.model;

import br.com.mega.silas.algoritmos.InformacoesSilas;

import java.io.Serializable;

/**
 * Created by Eduardo on 12/05/2016.
 */

@InformacoesSilas(nomeTipoPropriedade = "OUTCaboCondutor")
public class Condutor implements Serializable {
    private Long id;
    private String nome;
    private Integer capacidade;
    private Double r0;
    private Double x0;
    private Double r1;
    private Double x1;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTnome")
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTcapacidadeCondutor")
    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTr0")
    public Double getR0() {
        return r0;
    }

    public void setR0(Double r0) {
        this.r0 = r0;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTx0")
    public Double getX0() {
        return x0;
    }

    public void setX0(Double x0) {
        this.x0 = x0;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTr1")
    public Double getR1() {
        return r1;
    }

    public void setR1(Double r1) {
        this.r1 = r1;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTx1")
    public Double getX1() {
        return x1;
    }

    public void setX1(Double x1) {
        this.x1 = x1;
    }
}
