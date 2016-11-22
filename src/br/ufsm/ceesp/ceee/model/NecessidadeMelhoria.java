package br.ufsm.ceesp.ceee.model;

import br.com.mega.silas.algoritmos.InformacoesSilas;

import java.io.Serializable;

/**
 * Parâmetros para indicar a partir de quando que uma determinada rede necessitará de melhorias.
 * Created by politecnico on 23/06/2015.
 */
@InformacoesSilas(nomeTipoPropriedade = "OUTnecessidadeMelhoria")
public class NecessidadeMelhoria implements Serializable {

    private Double carregamentoRedeSuperior;
    private Double nivelTensaoInferior;

    @InformacoesSilas(nomeTipoPropriedade = "OUTcarregamentoRedeSuperior")
    public Double getCarregamentoRedeSuperior() {
        return carregamentoRedeSuperior;
    }

    public void setCarregamentoRedeSuperior(Double carregamentoRedeSuperior) {
        this.carregamentoRedeSuperior = carregamentoRedeSuperior;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTniveisTensaoInferior")
    public Double getNivelTensaoInferior() {
        return nivelTensaoInferior;
    }

    public void setNivelTensaoInferior(Double nivelTensaoInferior) {
        this.nivelTensaoInferior = nivelTensaoInferior;
    }
}
