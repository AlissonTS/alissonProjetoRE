package br.ufsm.ceesp.ceee.model;

import br.com.mega.silas.algoritmos.IMarcacaoMapa;
import br.com.mega.silas.algoritmos.InformacoesSilas;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Mega on 01/08/14.
 */

@InformacoesSilas(nomeTipoPropriedade = "OUTConsumoPorClasseConsumidor")
public class ConsumoPorClasseConsumidor implements Serializable {

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
    private Integer classeConsumidor;
    private Integer quantidadeConsumidores;
    private Double consumoTotal;

    @InformacoesSilas(nomeTipoPropriedade = "OUTConsumo")
    public Consumo getConsumo() {
        return consumo;
    }

    public void setConsumo(Consumo consumo) {
        this.consumo = consumo;
    }

    @InformacoesSilas
    public Integer getClasseConsumidor() {
        return classeConsumidor;
    }

    public void setClasseConsumidor(Integer classeConsumidor) {
        this.classeConsumidor = classeConsumidor;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTNumeroConsumidores")
    public Integer getQuantidadeConsumidores() {
        return quantidadeConsumidores;
    }

    public void setQuantidadeConsumidores(Integer quantidadeConsumidores) {
        this.quantidadeConsumidores = quantidadeConsumidores;
    }

    @InformacoesSilas
    public Double getConsumoTotal() {
        return consumoTotal;
    }

    public void setConsumoTotal(Double consumoTotal) {
        this.consumoTotal = consumoTotal;
    }
}
