package br.ufsm.ceesp.ceee.model;

import br.com.mega.silas.algoritmos.IMarcacaoMapa;
import br.com.mega.silas.algoritmos.InformacoesSilas;

import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.TreeSet;


/**
 * Created by Mega on 31/07/14.
 */

@InformacoesSilas(nomeTipoPropriedade = "OUTTransformador")
public class Transformador implements Serializable {

    public INavegavel getINavegavelAssociado() {
        return getBarra();
    }

    private Long id;
    private String codigoAlfanumerico;
    private IMarcacaoMapa marcacaoMapa;
    private Boolean ativado;
    private String tipoEstacao;
    private String tipoLigacao;
    private Double potenciaNominal;
    private Double tensaoNominalSecundaria;
    private String fases;
    private String tipoMedicao;
    private Barra barra;
    private Double quedaTensao;
    private transient Double[] tempP;
    private transient Double[] tempQ;
    private transient Integer tempNumConsumidores;
    private Integer anoSimulado;
    private Integer patamarSimulado;
    private Collection<ClienteCEEE> clientesCEEE;

    public Collection<ClienteCEEE> getClientesCEEE() {
        return clientesCEEE;
    }

    public void setClientesCEEE(Collection<ClienteCEEE> clientesCEEE) {
        this.clientesCEEE = clientesCEEE;
    }

    class ComparatorMedidas implements Comparator<MedidaEletrica>, Serializable {
        @Override
        public int compare(MedidaEletrica o1, MedidaEletrica o2) {
            MedidaEletrica.TipoMedida t1 = MedidaEletrica.TipoMedida.mapValues.get(o1.getNome());
            MedidaEletrica.TipoMedida t2 = MedidaEletrica.TipoMedida.mapValues.get(o2.getNome());
            return t1.compareTo(t2);
        }
    }

    private Collection<MedidaEletrica> medidasEletricas = new TreeSet<MedidaEletrica>(new ComparatorMedidas());
    private Double[] difIAtivaCorrecao;
    private Double[] difIReativaCorrecao;

    public Double[] getDifIAtivaCorrecao() {
        return difIAtivaCorrecao;
    }

    public void setDifIAtivaCorrecao(Double[] difIAtivaCorrecao) {
        this.difIAtivaCorrecao = difIAtivaCorrecao;
    }

    public Double[] getDifIReativaCorrecao() {
        return difIReativaCorrecao;
    }

    public void setDifIReativaCorrecao(Double[] difIReativaCorrecao) {
        this.difIReativaCorrecao = difIReativaCorrecao;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTCodigoString")
    public String getCodigoAlfanumerico() {
        return codigoAlfanumerico;
    }

    public void setCodigoAlfanumerico(String codigoAlfanumerico) {
        this.codigoAlfanumerico = codigoAlfanumerico;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTmedidasEletricas")
    public Collection<MedidaEletrica> getMedidasEletricas() {
        return medidasEletricas;
    }

    public void setMedidasEletricas(Collection<MedidaEletrica> medidasEletricas) {
        if (medidasEletricas != null) {
            for (MedidaEletrica m : medidasEletricas) {
                if (MedidaEletrica.isExibir(m, Transformador.class)) {
                    this.medidasEletricas.add(m);
                }
            }
        } else {
            this.medidasEletricas.clear();
        }
    }


    @InformacoesSilas(nomeTipoPropriedade = "OUTano")
    public Integer getAnoSimulado() {
        return anoSimulado;
    }

    public void setAnoSimulado(Integer anoSimulado) {
        this.anoSimulado = anoSimulado;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTPatamarSimulado")
    public Integer getPatamarSimulado() {
        return patamarSimulado;
    }

    public void setPatamarSimulado(Integer patamarSimulado) {
        this.patamarSimulado = patamarSimulado;
    }

    public Double[] getTempP() {
        return tempP;
    }

    public void setTempP(Double[] tempP) {
        this.tempP = tempP;
    }

    public Double[] getTempQ() {
        return tempQ;
    }

    public void setTempQ(Double[] tempQ) {
        this.tempQ = tempQ;
    }

    public Integer getTempNumConsumidores() {
        return tempNumConsumidores;
    }

    public void setTempNumConsumidores(Integer tempNumConsumidores) {
        this.tempNumConsumidores = tempNumConsumidores;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTquedaTensao", traduzir = false, persistir = true)
    public Double getQuedaTensao() {
        return this.quedaTensao;
    }

    public void setQuedaTensao(Double quedaTensao) {
        this.quedaTensao = quedaTensao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public IMarcacaoMapa getMarcacaoMapa() {
        return marcacaoMapa;
    }

    public void setMarcacaoMapa(IMarcacaoMapa marcacaoMapa) {
        this.marcacaoMapa = marcacaoMapa;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTAtivado")
    public Boolean getAtivado() {
        return ativado;
    }

    public void setAtivado(Boolean ativado) {
        this.ativado = ativado;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTTipoTrafo")
    public String getTipoEstacao() {
        return tipoEstacao;
    }

    public void setTipoEstacao(String tipoEstacao) {
        this.tipoEstacao = tipoEstacao;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTTipoLigacao")
    public String getTipoLigacao() {
        return tipoLigacao;
    }

    public void setTipoLigacao(String tipoLigacao) {
        this.tipoLigacao = tipoLigacao;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTPotenciaNominal")
    public Double getPotenciaNominal() {
        return potenciaNominal;
    }

    public void setPotenciaNominal(Double potenciaNominal) {
        this.potenciaNominal = potenciaNominal;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTtensaoSecundariaNominal")
    public Double getTensaoNominalSecundaria() {
        return tensaoNominalSecundaria;
    }

    public void setTensaoNominalSecundaria(Double tensaoNominalSecundaria) {
        this.tensaoNominalSecundaria = tensaoNominalSecundaria;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTfases")
    public String getFases() {
        return fases;
    }

    public void setFases(String fases) {
        this.fases = fases;
    }

    private String fasesOriginal;

    public void complementaFases() {
        this.fasesOriginal = getFases();
        setFases("DEF");
        double demandaAtiva = (this.demandaAtivaA + this.demandaAtivaB + this.demandaAtivaC) / 3.0;
        double demandaReativa = (this.demandaReativaA + this.demandaReativaB + this.demandaReativaC) / 3.0;
        this.demandaAtivaA = demandaAtiva;
        this.demandaAtivaB = demandaAtiva;
        this.demandaAtivaC = demandaAtiva;
        this.demandaReativaA = demandaReativa;
        this.demandaReativaB = demandaReativa;
        this.demandaReativaC = demandaReativa;
    }

    public void restabeleceFasesOriginais() {
        if (this.fasesOriginal != null) {
            setFases(this.fasesOriginal);
        }
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTTipoMedicao")
    public String getTipoMedicao() {
        return tipoMedicao;
    }

    public void setTipoMedicao(String tipoMedicao) {
        this.tipoMedicao = tipoMedicao;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTBarra")
    public Barra getBarra() {
        return barra;
    }

    public void setBarra(Barra barra) {
        this.barra = barra;
    }

    private Double demandaAtivaA;
    private Double demandaReativaA;
    private Double demandaAtivaB;
    private Double demandaReativaB;
    private Double demandaReativaC;
    private Double demandaAtivaC;
    private Float numConsumidores = 0f;
    private Collection<Consumo> consumos = new LinkedHashSet<Consumo>();

    public Double getDemandaAtivaA() {
        return demandaAtivaA;
    }

    public void setDemandaAtivaA(Double demandaAtivaA) {
        this.demandaAtivaA = demandaAtivaA;
    }

    public Double getDemandaReativaA() {
        return demandaReativaA;
    }

    public void setDemandaReativaA(Double demandaReativaA) {
        this.demandaReativaA = demandaReativaA;
    }

    public Double getDemandaAtivaB() {
        return demandaAtivaB;
    }

    public void setDemandaAtivaB(Double demandaAtivaB) {
        this.demandaAtivaB = demandaAtivaB;
    }

    public Double getDemandaReativaB() {
        return demandaReativaB;
    }

    public void setDemandaReativaB(Double demandaReativaB) {
        this.demandaReativaB = demandaReativaB;
    }

    public Double getDemandaReativaC() {
        return demandaReativaC;
    }

    public void setDemandaReativaC(Double demandaReativaC) {
        this.demandaReativaC = demandaReativaC;
    }

    public Double getDemandaAtivaC() {
        return demandaAtivaC;
    }

    public void setDemandaAtivaC(Double demandaAtivaC) {
        this.demandaAtivaC = demandaAtivaC;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTNumConsumidoresTransformador")
    public Float getNumConsumidores() {
        return numConsumidores;
    }

    public void setNumConsumidores(Float numConsumidores) {
        this.numConsumidores = numConsumidores;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTConsumo", valorDependende = true)
    public Collection<Consumo> getConsumos() {
        return consumos;
    }

    public void setConsumos(Collection<Consumo> consumos) {
        this.consumos = consumos;
    }

    // -----------------------

}
