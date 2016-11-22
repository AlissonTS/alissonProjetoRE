package br.ufsm.ceesp.ceee.model;

import br.com.mega.silas.algoritmos.IMarcacaoMapa;
import br.com.mega.silas.algoritmos.InformacoesSilas;

import java.io.Serializable;
import java.util.*;

/**
 * Created by Mega on 31/07/14.
 */

@InformacoesSilas(nomeTipoPropriedade = "PSMalimentador")
public class Alimentador implements Serializable {

    private Double tensaoNominalSecundaria;
    private Double comprimentoAcumuladoPrimario;
    private String nome;
    private TransformadorSE transformadorSE;
    private Subestacao subestacao;
    private Collection<TrechoRede> trechosRede = new LinkedHashSet<TrechoRede>();
    private Collection<Barra> barras = new LinkedHashSet<Barra>();
    private Collection<Transformador> transformadores = new LinkedHashSet<Transformador>();
    private Collection<Regulador> reguladores = new LinkedHashSet<Regulador>();
    private Collection<Chave> chaves = new LinkedHashSet<Chave>();
    private Collection<Capacitor> capacitores = new LinkedHashSet<Capacitor>();
    private Collection<FonteGeracao> fontesGeracaoDistribuida = new LinkedHashSet<FonteGeracao>();
    private Collection<Consumo> consumos = new LinkedHashSet<Consumo>();
    private TrechoRede trechoRedeInicial = null;
    private Double percentualImpedanciaConstante;
    private Double percentualCorrenteConstante;
    private boolean montouArvore = false;
    private Map<Integer, PrevisaoCarga> previsaoCargaPatamar;
    private Confiabilidade confiabilidade;
    private Integer numConsumidores;
    private ResultadosAlimentador resultadosAlimentador;
    private Integer piorPatamar;
    private Map<Integer, ResultadosAlimentador> diagnosticos = new LinkedHashMap<Integer, ResultadosAlimentador>();
    private Collection<SolucaoPlanejamento> solucoesPlanejamento = new ArrayList<SolucaoPlanejamento>();
    private Collection<Capacitor> capacitoresInstalados = new ArrayList<Capacitor>();
    private Collection<Regulador> reguladoresInstalados = new ArrayList<Regulador>();
    private Collection<String> sequenciaManobras = new ArrayList<String>();
    private String conjunto;
    private Long quantClientesConjunto;
    private Long quantClientesAES;
    private Double taxaCrescimentoAno1;
    private Double taxaCrescimentoAno2;
    private Double taxaCrescimentoAno3;
    private Double taxaCrescimentoAno4;
    private Double taxaCrescimentoAno5;
    private String tipoCargasCrescimento;
    private Collection<CapacitorSerie> capacitoresSerie = new ArrayList<CapacitorSerie>();
    private Collection<ElevadorRebaixador> elevadoresRebaixadores = new ArrayList<ElevadorRebaixador>();

    @InformacoesSilas(valorDependende = true, nomeTipoPropriedade = "OUTtransformadorElevadorRebaixador")
    public Collection<ElevadorRebaixador> getElevadoresRebaixadores() {
        return elevadoresRebaixadores;
    }

    public void setElevadoresRebaixadores(Collection<ElevadorRebaixador> elevadoresRebaixadores) {
        this.elevadoresRebaixadores = elevadoresRebaixadores;
    }

    @InformacoesSilas(valorDependende = true, nomeTipoPropriedade = "OUTcapacitorSerie")
    public Collection<CapacitorSerie> getCapacitoresSerie() {
        return capacitoresSerie;
    }

    public void setCapacitoresSerie(Collection<CapacitorSerie> capacitoresSerie) {
        this.capacitoresSerie = capacitoresSerie;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTtaxasCrescimento.OUTtaxaAno1")
    public Double getTaxaCrescimentoAno1() {
        return taxaCrescimentoAno1;
    }

    public void setTaxaCrescimentoAno1(Double taxaCrescimentoAno1) {
        this.taxaCrescimentoAno1 = taxaCrescimentoAno1;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTtaxasCrescimento.OUTtaxaAno2")
    public Double getTaxaCrescimentoAno2() {
        return taxaCrescimentoAno2;
    }

    public void setTaxaCrescimentoAno2(Double taxaCrescimentoAno2) {
        this.taxaCrescimentoAno2 = taxaCrescimentoAno2;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTtaxasCrescimento.OUTtaxaAno3")
    public Double getTaxaCrescimentoAno3() {
        return taxaCrescimentoAno3;
    }

    public void setTaxaCrescimentoAno3(Double taxaCrescimentoAno3) {
        this.taxaCrescimentoAno3 = taxaCrescimentoAno3;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTtaxasCrescimento.OUTtaxaAno4")
    public Double getTaxaCrescimentoAno4() {
        return taxaCrescimentoAno4;
    }

    public void setTaxaCrescimentoAno4(Double taxaCrescimentoAno4) {
        this.taxaCrescimentoAno4 = taxaCrescimentoAno4;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTtaxasCrescimento.OUTtaxaAno5")
    public Double getTaxaCrescimentoAno5() {
        return taxaCrescimentoAno5;
    }

    public void setTaxaCrescimentoAno5(Double taxaCrescimentoAno5) {
        this.taxaCrescimentoAno5 = taxaCrescimentoAno5;
    }

    public Double getTaxaCrescimento(int ano) {
        switch (ano) {
            case 1: return getTaxaCrescimentoAno1();
            case 2: return getTaxaCrescimentoAno2();
            case 3: return getTaxaCrescimentoAno3();
            case 4: return getTaxaCrescimentoAno4();
            case 5: return getTaxaCrescimentoAno5();
        }
        return 0.0;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTtaxasCrescimento.OUTcalculoDemanda")
    public String getTipoCargasCrescimento() {
        return tipoCargasCrescimento;
    }

    public void setTipoCargasCrescimento(String tipoCargasCrescimento) {
        this.tipoCargasCrescimento = tipoCargasCrescimento;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTquantidadeClientesConjunto")
    public Long getQuantClientesConjunto() {
        return quantClientesConjunto;
    }

    public void setQuantClientesConjunto(Long quantClientesConjunto) {
        this.quantClientesConjunto = quantClientesConjunto;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTquantidadeClientesAES")
    public Long getQuantClientesAES() {
        return quantClientesAES;
    }

    public void setQuantClientesAES(Long quantClientesAES) {
        this.quantClientesAES = quantClientesAES;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTconjunto")
    public String getConjunto() {
        return conjunto;
    }

    public void setConjunto(String conjunto) {
        this.conjunto = conjunto;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTSequenciaManobras")
    public Collection<String> getSequenciaManobras() {
        return sequenciaManobras;
    }

    public void setSequenciaManobras(Collection<String> sequenciaManobras) {
        this.sequenciaManobras = sequenciaManobras;
    }

    private Double pPatamar1;
    private Double qPatamar1;
    private Double pPatamar2;
    private Double qPatamar2;
    private Double pPatamar3;
    private Double qPatamar3;
    private Double pPatamar4;
    private Double qPatamar4;
    private String tipoAjuste;

    @InformacoesSilas(nomeTipoPropriedade = "OUTajusteCarga.OUTcalculoDemanda")
    public String getTipoAjuste() {
        return tipoAjuste;
    }

    public void setTipoAjuste(String tipoAjuste) {
        this.tipoAjuste = tipoAjuste;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTajusteCarga.OUTpotenciaAtivaP1")
    public Double getpPatamar1() {
        return pPatamar1;
    }

    public void setpPatamar1(Double pPatamar1) {
        this.pPatamar1 = pPatamar1;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTajusteCarga.OUTpotenciaReativaP1")
    public Double getqPatamar1() {
        return qPatamar1;
    }

    public void setqPatamar1(Double qPatamar1) {
        this.qPatamar1 = qPatamar1;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTajusteCarga.OUTpotenciaAtivaP2")
    public Double getpPatamar2() {
        return pPatamar2;
    }

    public void setpPatamar2(Double pPatamar2) {
        this.pPatamar2 = pPatamar2;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTajusteCarga.OUTpotenciaReativaP2")
    public Double getqPatamar2() {
        return qPatamar2;
    }

    public void setqPatamar2(Double qPatamar2) {
        this.qPatamar2 = qPatamar2;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTajusteCarga.OUTpotenciaAtivaP3")
    public Double getpPatamar3() {
        return pPatamar3;
    }

    public void setpPatamar3(Double pPatamar3) {
        this.pPatamar3 = pPatamar3;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTajusteCarga.OUTpotenciaReativaP3")
    public Double getqPatamar3() {
        return qPatamar3;
    }

    public void setqPatamar3(Double qPatamar3) {
        this.qPatamar3 = qPatamar3;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTajusteCarga.OUTpotenciaAtivaP4")
    public Double getpPatamar4() {
        return pPatamar4;
    }

    public void setpPatamar4(Double pPatamar4) {
        this.pPatamar4 = pPatamar4;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTajusteCarga.OUTpotenciaReativaP4")
    public Double getqPatamar4() {
        return qPatamar4;
    }

    public void setqPatamar4(Double qPatamar4) {
        this.qPatamar4 = qPatamar4;
    }

    public Double[] getPQMedido(int patamar) {
        if (patamar == 1) {
            if (pPatamar1 != null && Math.abs(pPatamar1) > 0.0 && qPatamar1 != null && Math.abs(qPatamar1) > 0.0) {
                return new Double[]{pPatamar1, qPatamar1};
            }
        } else if (patamar == 2) {
            if (pPatamar2 != null && Math.abs(pPatamar2) > 0.0 && qPatamar2 != null && Math.abs(qPatamar2) > 0.0) {
                return new Double[]{pPatamar2, qPatamar2};
            }
        } else if (patamar == 3) {
            if (pPatamar3 != null && Math.abs(pPatamar3) > 0.0 && qPatamar3 != null && Math.abs(qPatamar3) > 0.0) {
                return new Double[]{pPatamar3, qPatamar3};
            }
        } else if (patamar == 4) {
            if (pPatamar4 != null && Math.abs(pPatamar4) > 0.0 && qPatamar4 != null && Math.abs(qPatamar4) > 0.0) {
                return new Double[]{pPatamar4, qPatamar4};
            }
        }
        return null;
    }

    private Double iPatamar1;
    private Double iPatamar2;
    private Double iPatamar3;
    private Double iPatamar4;

    @InformacoesSilas(nomeTipoPropriedade = "OUTajusteCarga.OUTcorrenteP1")
    public Double getiPatamar1() {
        return iPatamar1;
    }

    public void setiPatamar1(Double iPatamar1) {
        this.iPatamar1 = iPatamar1;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTajusteCarga.OUTOUTcorrenteP2")
    public Double getiPatamar2() {
        return iPatamar2;
    }

    public void setiPatamar2(Double iPatamar2) {
        this.iPatamar2 = iPatamar2;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTajusteCarga.OUTOUTcorrenteP3")
    public Double getiPatamar3() {
        return iPatamar3;
    }

    public void setiPatamar3(Double iPatamar3) {
        this.iPatamar3 = iPatamar3;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTajusteCarga.OUTOUTcorrenteP4")
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

    private Double[] pTotalCalculado;
    private Double[] qTotalCalculado;

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

    public Double[] getpTotalCalculado() {
        return pTotalCalculado;
    }

    public void setpTotalCalculado(Double[] pTotalCalculado) {
        this.pTotalCalculado = pTotalCalculado;
    }

    public Double[] getqTotalCalculado() {
        return qTotalCalculado;
    }

    public void setqTotalCalculado(Double[] qTotalCalculado) {
        this.qTotalCalculado = qTotalCalculado;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTsolucaoPlanejamento")
    public Collection<SolucaoPlanejamento> getSolucoesPlanejamento() {
        return solucoesPlanejamento;
    }

    public void setSolucoesPlanejamento(Collection<SolucaoPlanejamento> solucoesPlanejamento) {
        this.solucoesPlanejamento = solucoesPlanejamento;
    }

    public Collection<Regulador> getReguladoresInstalados() {
        return reguladoresInstalados;
    }

    public void setReguladoresInstalados(Collection<Regulador> reguladoresInstalados) {
        this.reguladoresInstalados = reguladoresInstalados;
    }

    public Collection<Capacitor> getCapacitoresInstalados() {
        return capacitoresInstalados;
    }

    public void setCapacitoresInstalados(Collection<Capacitor> capacitoresInstalados) {
        this.capacitoresInstalados = capacitoresInstalados;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTdiagnosticos", campoIndiceMapa = "OUTprojecaoAnos")
    public Map<Integer, ResultadosAlimentador> getDiagnosticos() {
        return diagnosticos;
    }

    public void setDiagnosticos(Map<Integer, ResultadosAlimentador> diagnosticos) {
        this.diagnosticos = diagnosticos;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTResultadosAlimentador")
    public ResultadosAlimentador getResultadosAlimentador() {
        return resultadosAlimentador;
    }

    public void setResultadosAlimentador(ResultadosAlimentador resultadosAlimentador) {
        this.resultadosAlimentador = resultadosAlimentador;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTNumConsumidoresAlimentador")
    public Integer getNumConsumidores() {
        return numConsumidores;
    }

    public void setNumConsumidores(Integer numConsumidores) {
        this.numConsumidores = numConsumidores;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTconfiabilidade")
    public Confiabilidade getConfiabilidade() {
        return confiabilidade;
    }

    public void setConfiabilidade(Confiabilidade confiabilidade) {
        this.confiabilidade = confiabilidade;
    }

//    @InformacoesSilas(nomeTipoPropriedade = "OUTpotenciaSaida", campoIndiceMapa = "OUTpatamar")
    public Map<Integer, PrevisaoCarga> getPrevisaoCargaPatamar() {
        return previsaoCargaPatamar;
    }

    public void setPrevisaoCargaPatamar(Map<Integer, PrevisaoCarga> previsaoCargaPatamar) {
        this.previsaoCargaPatamar = previsaoCargaPatamar;
    }

    public TrechoRede getTrechoRedeInicial() {
        if (trechoRedeInicial == null) {
            encontraESetaTrechoRedeInicial();
        }
        if (trechoRedeInicial == null) {
            throw new IllegalStateException("Trecho inicial não encontrado para o alimentador: " + this.toString());
        } else {
            return trechoRedeInicial;
        }
    }

    private void encontraESetaTrechoRedeInicial() {
        for (Chave chave : getChaves()) {
            if (chave.getTipoChave().getCodigo().trim().equals(TipoChave.CodigoMnemonicoDispositivoProtecao.DISJ.toString())) {
                setTrechoRedeInicial(chave.getTrechoRede());
                break;
            }
        }
    }

    public void setTrechoRedeInicial(TrechoRede trechoRedeInicial) {
        this.trechoRedeInicial = trechoRedeInicial;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTcorrenteConstante")
    public Double getPercentualCorrenteConstante() {
        return percentualCorrenteConstante;
    }

    public void setPercentualCorrenteConstante(Double percentualCorrenteConstante) {
        this.percentualCorrenteConstante = percentualCorrenteConstante;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTimpedanciaConstante")
    public Double getPercentualImpedanciaConstante() {
        return percentualImpedanciaConstante;
    }

    public void setPercentualImpedanciaConstante(Double percentualImpedanciaConstante) {
        this.percentualImpedanciaConstante = percentualImpedanciaConstante;
    }

    @InformacoesSilas(valorDependende = true, nomeTipoPropriedade = "OUTTrechoRede")
    public Collection<TrechoRede> getTrechosRede() {
        return trechosRede;
    }

    public void setTrechosRede(Collection<TrechoRede> trechosRede) {
        this.trechosRede = trechosRede;
    }

    @InformacoesSilas(valorDependende = true, nomeTipoPropriedade = "OUTBarra")
    public Collection<Barra> getBarras() {
        return barras;
    }

    public void setBarras(Collection<Barra> barras) {
        this.barras = barras;
    }

    @InformacoesSilas(valorDependende = true, nomeTipoPropriedade = "OUTTransformador")
    public Collection<Transformador> getTransformadores() {
        return transformadores;
    }

    public void setTransformadores(Collection<Transformador> transformadores) {
        this.transformadores = transformadores;
    }

    @InformacoesSilas(valorDependende = true, nomeTipoPropriedade = "OUTRegulador")
    public Collection<Regulador> getReguladores() {
        return reguladores;
    }

    public void setReguladores(Collection<Regulador> reguladores) {
        this.reguladores = reguladores;
    }

    @InformacoesSilas(valorDependende = true, nomeTipoPropriedade = "OUTSeccionador")
    public Collection<Chave> getChaves() {
        return chaves;
    }

    public void setChaves(Collection<Chave> chaves) {
        this.chaves = chaves;
    }

    @InformacoesSilas(valorDependende = true, nomeTipoPropriedade = "OUTCapacitor")
    public Collection<Capacitor> getCapacitores() {
        return capacitores;
    }

    public void setCapacitores(Collection<Capacitor> capacitores) {
        this.capacitores = capacitores;
    }

//    @InformacoesSilas(valorDependende = true, nomeTipoPropriedade = "OUTFonteGeracao")
    public Collection<FonteGeracao> getFontesGeracaoDistribuida() {
        return fontesGeracaoDistribuida;
    }

    public void setFontesGeracaoDistribuida(Collection<FonteGeracao> fontesGeracaoDistribuida) {
        this.fontesGeracaoDistribuida = fontesGeracaoDistribuida;
    }

    @InformacoesSilas(valorDependende = true, nomeTipoPropriedade = "OUTConsumo")
    public Collection<Consumo> getConsumos() {
        return consumos;
    }

    public void setConsumos(Collection<Consumo> consumos) {
        this.consumos = consumos;
    }

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

    @InformacoesSilas(nomeTipoPropriedade = "OUTClasseTensao")
    public Double getTensaoNominalSecundaria() {
        return tensaoNominalSecundaria;
    }

    public void setTensaoNominalSecundaria(Double tensaoNominalSecundaria) {
        this.tensaoNominalSecundaria = tensaoNominalSecundaria;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTnome")
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getComprimentoAcumuladoPrimario() {
        return comprimentoAcumuladoPrimario;
    }

    public void setComprimentoAcumuladoPrimario(Double comprimentoAcumuladoPrimario) {
        this.comprimentoAcumuladoPrimario = comprimentoAcumuladoPrimario;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTTransformadorSE")
    public TransformadorSE getTransformadorSE() {
        return transformadorSE;
    }

    public void setTransformadorSE(TransformadorSE transformadorSE) {
        this.transformadorSE = transformadorSE;
    }

    @InformacoesSilas(nomeTipoPropriedade = "PSMsubestacao")
    public Subestacao getSubestacao() {
        return subestacao;
    }

    public void setSubestacao(Subestacao subestacao) {
        this.subestacao = subestacao;
    }

    //TODO: Ver na AES qual é a primeira barra do alimentador.

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Alimentador{");
        sb.append("id='").append(id).append('\'');
        sb.append(", nome='").append(nome).append('\'');
        sb.append(", tensaoNominalSecundaria=").append(tensaoNominalSecundaria).append('\'');
        sb.append('}');
        return getNome();
    }

    public boolean getMontouArvore() {
        return montouArvore;
    }

    public void setMontouArvore(boolean montouArvore) {
        this.montouArvore = montouArvore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Alimentador)) return false;

        Alimentador that = (Alimentador) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public void setPiorPatamar(Integer piorPatamar) {
        this.piorPatamar = piorPatamar;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTpiorPatamar")
    public Integer getPiorPatamar() {
        return piorPatamar;
    }

}
