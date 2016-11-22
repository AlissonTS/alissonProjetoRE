package br.ufsm.ceesp.ceee.model;

import br.com.mega.silas.algoritmos.InformacoesSilas;

import java.io.Serializable;

/**
 * Parametros que regem a criação de soluções de planejamento quando a rede exige melhorias.
 * Created by politecnico on 19/06/2015.
 */
@InformacoesSilas(nomeTipoPropriedade = "OUTcriterioPlanejamento")
public class CriteriosPlanejamento implements Serializable {

    private Double limiteMaximoCarregamento;
    private Double limiteInferiorTensao;
    private Integer limiteBancosCapacitores;
    private Integer limiteReguladoresTensao;

    @InformacoesSilas(nomeTipoPropriedade = "OUTlimiteMaximoCarregamento")
    public Double getLimiteMaximoCarregamento() {
        return limiteMaximoCarregamento;
    }

    public void setLimiteMaximoCarregamento(Double limiteMaximoCarregamento) {
        this.limiteMaximoCarregamento = limiteMaximoCarregamento;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTlimiteInferiorTensao")
    public Double getLimiteInferiorTensao() {
        return limiteInferiorTensao;
    }

    public void setLimiteInferiorTensao(Double limiteInferiorTensao) {
        this.limiteInferiorTensao = limiteInferiorTensao;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTlimiteBancosCapacitores")
    public Integer getLimiteBancosCapacitores() {
        return limiteBancosCapacitores;
    }

    public void setLimiteBancosCapacitores(Integer limiteBancosCapacitores) {
        this.limiteBancosCapacitores = limiteBancosCapacitores;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTlimiteReguladoresTensao")
    public Integer getLimiteReguladoresTensao() {
        return limiteReguladoresTensao;
    }

    public void setLimiteReguladoresTensao(Integer limiteReguladoresTensao) {
        this.limiteReguladoresTensao = limiteReguladoresTensao;
    }
}
