package br.ufsm.ceesp.ceee.model;

import br.com.mega.silas.algoritmos.IMarcacaoMapa;
import br.com.mega.silas.algoritmos.InformacoesSilas;

import java.io.Serializable;
import java.util.*;

/**
 * Created by Mega on 01/08/14.
 */

@InformacoesSilas(nomeTipoPropriedade = "OUTConsumo")
public class Consumo implements Serializable {

    private Long id;
    private Map<String, MedidaEletrica> medidasEletricas = new LinkedHashMap<String, MedidaEletrica>();
    private IMarcacaoMapa marcacaoMapa;
    private String tipoConsumidor;

    @InformacoesSilas(nomeTipoPropriedade = "OUTTipoConsumidor")
    public String getTipoConsumidor() {
        return tipoConsumidor;
    }

    public void setTipoConsumidor(String tipoConsumidor) {
        this.tipoConsumidor = tipoConsumidor;
    }

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

    //Relações
    private Transformador transformador;
    private Collection<ConsumoPorClasseConsumidor> consumosPorClasseConsumidor = new LinkedHashSet<ConsumoPorClasseConsumidor>();
    private Map<Integer, DemandaPorPeriodo> demandasPorPeriodo = new HashMap<Integer, DemandaPorPeriodo>();
    private Alimentador alimentador;

    @InformacoesSilas(nomeTipoPropriedade = "OUTTransformador")
    public Transformador getTransformador() {
        return transformador;
    }

    public void setTransformador(Transformador transformador) {
        this.transformador = transformador;
    }

    @InformacoesSilas(valorDependende = true, nomeTipoPropriedade = "OUTConsumoPorClasseConsumidor")
    public Collection<ConsumoPorClasseConsumidor> getConsumosPorClasseConsumidor() {
        return consumosPorClasseConsumidor;
    }

    public void setConsumosPorClasseConsumidor(Collection<ConsumoPorClasseConsumidor> consumosPorClasseConsumidor) {
        this.consumosPorClasseConsumidor = consumosPorClasseConsumidor;
    }

    @InformacoesSilas(valorDependende = true, nomeTipoPropriedade = "OUTDemandaPorPeriodo", campoIndiceMapa = "OUTClasseDemanda")
    public Map<Integer, DemandaPorPeriodo> getDemandasPorPeriodo() {
        return demandasPorPeriodo;
    }

    public void setDemandasPorPeriodo(Map<Integer, DemandaPorPeriodo> demandasPorPeriodo) {
        this.demandasPorPeriodo = demandasPorPeriodo;
    }

    @InformacoesSilas(nomeTipoPropriedade = "PSMalimentador")
    public Alimentador getAlimentador() {
        return alimentador;
    }

    public void setAlimentador(Alimentador alimentador) {
        this.alimentador = alimentador;
    }
}
