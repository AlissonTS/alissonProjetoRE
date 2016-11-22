package br.ufsm.ceesp.ceee.model;

import br.com.mega.silas.algoritmos.IMarcacaoMapa;
import br.com.mega.silas.algoritmos.InformacoesSilas;
import br.ufsm.ceesp.ceee.algoritmos.impl.DadosAjusteCarga;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Mega on 31/07/14.
 */

@InformacoesSilas(nomeTipoPropriedade = "OUTSeccionador")
public class Chave implements Serializable, Seccionalizador {

    public static final Integer ESTADO_ABERTA = 0;
    public static final Integer ESTADO_FECHADA = 1;
    private Long id;
    private Map<String, MedidaEletrica> medidasEletricas = new LinkedHashMap<String, MedidaEletrica>();
    private IMarcacaoMapa marcacaoMapa;
    private transient Double[] relacaoTransformacao;
    private transient DadosAjusteCarga ajusteCarga;
    private String matricula;
    private Alimentador alimentador;

    @InformacoesSilas(nomeTipoPropriedade = "PSMalimentador")
    public Alimentador getAlimentador() {
        return alimentador;
    }

    public void setAlimentador(Alimentador alimentador) {
        this.alimentador = alimentador;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTCodigoString")
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public DadosAjusteCarga getAjusteCarga() {
        return ajusteCarga;
    }

    public void setAjusteCarga(DadosAjusteCarga ajusteCarga) {
        this.ajusteCarga = ajusteCarga;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long codigo;

    @InformacoesSilas(nomeTipoPropriedade = "OUTcodigo")
    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
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

    private String codigoDispositivoProtecao;
    private Integer estadoAberturaAtual;
    private Double correnteNomininalDispositivoProtecao;
    private String subtipo;
    private Double eloFusivel;
    private Boolean telecomando;
    private Double correnteAtuacaoFase;
    private Double correnteAtuacaoTerra;
    private TrechoRede trechoRede;
    private Boolean pontoNotavel;
    private transient Long consumidores;
    private Integer anoSimulado;
    private Integer patamarSimulado;
    private TipoChave tipoChave;
    private String codigoAlfanumerico;

    private Double iPatamar1;
    private Double iPatamar2;
    private Double iPatamar3;
    private Double iPatamar4;
    private Map<String, MedidaConfiabilidade> medidasConfiabilidade = new TreeMap<String, MedidaConfiabilidade>();

    private String tipoAjusteDemanda;

    @InformacoesSilas(nomeTipoPropriedade = "OUTCodigoString")
    public String getCodigoAlfanumerico() {
        return codigoAlfanumerico;
    }

    public void setCodigoAlfanumerico(String codigoAlfanumerico) {
        this.codigoAlfanumerico = codigoAlfanumerico;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTtipoChave")
    public TipoChave getTipoChave() {
        return tipoChave;
    }

    public void setTipoChave(TipoChave tipoChave) {
        this.tipoChave = tipoChave;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTmedidasConfiabilidade", campoIndiceMapa = "OUTnome")
    public Map<String, MedidaConfiabilidade> getMedidasConfiabilidade() {
        return medidasConfiabilidade;
    }

    public void setMedidasConfiabilidade(Map<String, MedidaConfiabilidade> medidasConfiabilidade) {
        this.medidasConfiabilidade = medidasConfiabilidade;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTcalculoDemanda")
    public String getTipoAjusteDemanda() {
        return tipoAjusteDemanda;
    }

    public void setTipoAjusteDemanda(String tipoAjusteDemanda) {
        this.tipoAjusteDemanda = tipoAjusteDemanda;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTcorrenteP1")
    public Double getiPatamar1() {
        return iPatamar1;
    }

    public void setiPatamar1(Double iPatamar1) {
        this.iPatamar1 = iPatamar1;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTOUTcorrenteP2")
    public Double getiPatamar2() {
        return iPatamar2;
    }

    public void setiPatamar2(Double iPatamar2) {
        this.iPatamar2 = iPatamar2;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTOUTcorrenteP3")
    public Double getiPatamar3() {
        return iPatamar3;
    }

    public void setiPatamar3(Double iPatamar3) {
        this.iPatamar3 = iPatamar3;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTOUTcorrenteP4")
    public Double getiPatamar4() {
        return iPatamar4;
    }

    public void setiPatamar4(Double iPatamar4) {
        this.iPatamar4 = iPatamar4;
    }

    public Double getIMedido(int patamar) {
        if (patamar == 1) {
            if (iPatamar1 != null && iPatamar1 > 0.0 )
            {
                return iPatamar1;
            }
        } else if (patamar == 2) {
            if (iPatamar2 != null && iPatamar2 > 0.0 ) {
                return iPatamar2;
            }
        } else if (patamar == 3) {
            if (iPatamar3 != null && iPatamar3 > 0.0) {
                return iPatamar3;
            }
        } else if (patamar == 4) {
            if (iPatamar4 != null && iPatamar4 > 0.0) {
                return iPatamar4;
            }
        }
        return null;
    }

    private Double[] iAtivaCalculada;
    private Double[] iReativaCalculada;

    public Double[] getiAtivaCalculada() {
        return iAtivaCalculada;
    }

    public void setiAtivaCalculada(Double[] iAtivaCalculada) {
        this.iAtivaCalculada = iAtivaCalculada;
    }

    public Double[] getiReativaCalculada() {
        return iReativaCalculada;
    }

    public void setiReativaCalculada(Double[] iReativaCalculada) {
        this.iReativaCalculada = iReativaCalculada;
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

    public Long getConsumidores() {
        return consumidores;
    }

    public void setConsumidores(Long consumidores) {
        this.consumidores = consumidores;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTpontoNotavel")
    public Boolean getPontoNotavel() {
        return pontoNotavel;
    }

    public void setPontoNotavel(Boolean pontoNotavel) {
        this.pontoNotavel = pontoNotavel;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTEstadoAberturaAtualInteiro")
    public Integer getEstadoAberturaAtual() {
        return estadoAberturaAtual;
    }

    public void setEstadoAberturaAtual(Integer estadoAberturaAtual) {
        this.estadoAberturaAtual = estadoAberturaAtual;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTCorrenteNominalProtecao")
    public Double getCorrenteNomininalDispositivoProtecao() {
        return correnteNomininalDispositivoProtecao;
    }

    public void setCorrenteNomininalDispositivoProtecao(Double correnteNomininalDispositivoProtecao) {
        this.correnteNomininalDispositivoProtecao = correnteNomininalDispositivoProtecao;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTEloFusivelDouble")
    public Double getEloFusivel() {
        return eloFusivel;
    }

    public void setEloFusivel(Double eloFusivel) {
        this.eloFusivel = eloFusivel;
    }

    @Override
    public Barra getBarraInicial() {
        return getTrechoRede().getBarraFonte();
    }

    @Override
    public Barra getBarraFinal() {
        return getTrechoRede().getBarraAlvo();
    }

    @Override
    public String getNomeDispositivoProtecao() {
        return getTipoChave().getTipo();
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTTelecomando")
    public Boolean getTelecomando() {
        return telecomando;
    }

    public void setTelecomando(Boolean telecomando) {
        this.telecomando = telecomando;
    }

    @Override
    public String getModoOperacao() {
        return null;
    }

    public boolean isTelecomandavel() {
        return telecomando != null && telecomando;
    }

    @InformacoesSilas
    public Double getCorrenteAtuacaoFase() {
        return correnteAtuacaoFase;
    }

    public void setCorrenteAtuacaoFase(Double correnteAtuacaoFase) {
        this.correnteAtuacaoFase = correnteAtuacaoFase;
    }

    @InformacoesSilas
    public Double getCorrenteAtuacaoTerra() {
        return correnteAtuacaoTerra;
    }

    public void setCorrenteAtuacaoTerra(Double correnteAtuacaoTerra) {
        this.correnteAtuacaoTerra = correnteAtuacaoTerra;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTTrechoRede")
    public TrechoRede getTrechoRede() {
        return trechoRede;
    }

    public void setTrechoRede(TrechoRede trechoRede) {
        this.trechoRede = trechoRede;
    }

    public boolean isMedidor(int patamar) {
        if (getTipoChave().getTipo().contains("DISJUNTOR")) {
            return getTrechoRede().getAlimentador().getPQMedido(patamar) != null || getTrechoRede().getAlimentador().getIMedido(patamar) != null;
        } else {
            return getIMedido(patamar) != null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Chave)) return false;

        Chave chave = (Chave) o;

        return !(id != null ? !id.equals(chave.id) : chave.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        if (getCodigoAlfanumerico() != null) {
            return getCodigoAlfanumerico();
        }
        return "Chave";
    }

    public void setRelacaoTransformacao(Double[] relacaoTransformacao) {
        this.relacaoTransformacao = relacaoTransformacao;
    }

    public Double[] getRelacaoTransformacao() {
        return relacaoTransformacao;
    }
}
