package br.ufsm.ceesp.ceee.model;

import br.com.mega.silas.algoritmos.IMarcacaoMapa;
import br.com.mega.silas.algoritmos.InformacoesSilas;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Mega on 01/08/14.
 */

@InformacoesSilas(nomeTipoPropriedade = "OUTDemandaPorPeriodo")
public class DemandaPorPeriodo implements Serializable {

    private Long id;
    private Map<String, MedidaEletrica> medidasEletricas = new LinkedHashMap<String, MedidaEletrica>();
    private IMarcacaoMapa marcacaoMapa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTmedidasEletricas")
    public Map<String, MedidaEletrica> getMedidasEletricas() {
        return medidasEletricas;
    }


    public void setMedidasEletricas(Map<String, MedidaEletrica> medidasEletricas) {
        this.medidasEletricas = medidasEletricas;
    }


    public MedidaEletrica adicionaMedidaEletrica(String nomeMedida, MedidaEletrica medidaEletrica) {
        return medidasEletricas.put(nomeMedida, medidaEletrica);
    }


    public MedidaEletrica removerMedidaEletrica(MedidaEletrica medidaEletrica) {
        return medidasEletricas.remove(medidaEletrica);
    }


    public MedidaEletrica getMedidaEletrica(String nomeMedida) {
        return medidasEletricas.get(nomeMedida);
    }

    public IMarcacaoMapa getMarcacaoMapa() {
        return marcacaoMapa;
    }

    public void setMarcacaoMapa(IMarcacaoMapa marcacaoMapa) {
        this.marcacaoMapa = marcacaoMapa;
    }

    private Consumo consumo;
    private Integer classeDemanda;
    private Double demandaAtivaFaseD;
    private Double demandaAtivaFaseE;
    private Double demandaAtivaFaseF;
    private Double demandaReativaFaseD;
    private Double demandaReativaFaseE;
    private Double demandaReativaFaseF;

    @InformacoesSilas(nomeTipoPropriedade = "OUTConsumo")
    public Consumo getConsumo() {
        return consumo;
    }

    public void setConsumo(Consumo consumo) {
        this.consumo = consumo;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTClasseDemanda")
    public Integer getClasseDemanda() {
        return classeDemanda;
    }

    @InformacoesSilas
    public void setClasseDemanda(Integer classeDemanda) {
        this.classeDemanda = classeDemanda;
    }

    @InformacoesSilas
    public Double getDemandaAtivaFaseD() {
        return demandaAtivaFaseD;
    }

    public void setDemandaAtivaFaseD(Double demandaAtivaFaseD) {
        this.demandaAtivaFaseD = demandaAtivaFaseD;
    }

    @InformacoesSilas
    public Double getDemandaAtivaFaseE() {
        return demandaAtivaFaseE;
    }

    public void setDemandaAtivaFaseE(Double demandaAtivaFaseE) {
        this.demandaAtivaFaseE = demandaAtivaFaseE;
    }

    @InformacoesSilas
    public Double getDemandaAtivaFaseF() {
        return demandaAtivaFaseF;
    }

    public void setDemandaAtivaFaseF(Double demandaAtivaFaseF) {
        this.demandaAtivaFaseF = demandaAtivaFaseF;
    }

    @InformacoesSilas
    public Double getDemandaReativaFaseD() {
        return demandaReativaFaseD;
    }

    public void setDemandaReativaFaseD(Double demandaReativaFaseD) {
        this.demandaReativaFaseD = demandaReativaFaseD;
    }

    @InformacoesSilas
    public Double getDemandaReativaFaseE() {
        return demandaReativaFaseE;
    }

    public void setDemandaReativaFaseE(Double demandaReativaFaseE) {
        this.demandaReativaFaseE = demandaReativaFaseE;
    }

    @InformacoesSilas
    public Double getDemandaReativaFaseF() {
        return demandaReativaFaseF;
    }

    public void setDemandaReativaFaseF(Double demandaReativaFaseF) {
        this.demandaReativaFaseF = demandaReativaFaseF;
    }
}
