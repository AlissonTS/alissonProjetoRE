package br.ufsm.ceesp.ceee.model;

import br.com.mega.silas.algoritmos.InformacoesSilas;

import java.io.Serializable;

/**
 * Created by politecnico on 23/07/2015.
 */
@InformacoesSilas(nomeTipoPropriedade = "OUTsolucaoPlanejamento")
public class SolucaoPlanejamento implements Serializable {

    private Float custoTotal;
    private String solucao;
    private Integer ano;

    @InformacoesSilas(nomeTipoPropriedade = "OUTano")
    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTcustoSolucao")
    public Float getCustoTotal() {
        return custoTotal;
    }

    public void setCustoTotal(Float custoTotal) {
        this.custoTotal = custoTotal;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTdescricaoSolucao")
    public String getSolucao() {
        return solucao;
    }

    public void setSolucao(String solucao) {
        this.solucao = solucao;
    }

}
