package br.ufsm.ceesp.ceee.model;

import br.com.mega.silas.algoritmos.IMarcacaoMapa;
import br.com.mega.silas.algoritmos.InformacoesSilas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mega on 31/07/14.
 */

@InformacoesSilas(nomeTipoPropriedade = "OUTRegulador")
public class Regulador implements Serializable {

    private Long id;
    private IMarcacaoMapa marcacaoMapa;
    private Double iMaxima = null;
    private Double anguloFatorPotenciaMaximo = null;
    private Double tensaoReferencia;
    protected List<Map<String, Map<String, Number>>> simulacao = null;
    private static final Double RAIZ_TRES = Math.sqrt(3);

    private Double tensaoPUp1;
    private Double tensaoPUp2;
    private Double tensaoPUp3;
    private Double tensaoPUp4;
    private String codigoAlfanumerico;
    private Double[] relacaoTransformacao;

    public Double[] getRelacaoTransformacao() {
        return relacaoTransformacao;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTCodigoString")
    public String getCodigoAlfanumerico() {
        return codigoAlfanumerico;
    }

    public void setCodigoAlfanumerico(String codigoAlfanumerico) {
        this.codigoAlfanumerico = codigoAlfanumerico;
    }

    public Double getTensao(int patamar) {
        if (patamar == 1 && getTensaoPUp1() != null) {
            return getTensaoPUp1() * getAlimentador().getTransformadorSE().getTensaoNominalSecundaria();
        }
        if (patamar == 2 && getTensaoPUp2() != null) {
            return getTensaoPUp2() * getAlimentador().getTransformadorSE().getTensaoNominalSecundaria();
        }
        if (patamar == 3 && getTensaoPUp3() != null) {
            return getTensaoPUp3() * getAlimentador().getTransformadorSE().getTensaoNominalSecundaria();
        }
        if (patamar == 4 && getTensaoPUp4() != null) {
            return getTensaoPUp4() * getAlimentador().getTransformadorSE().getTensaoNominalSecundaria();
        }
        return null;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTtensoesReferencia.OUTtensaoP1")
    public Double getTensaoPUp1() {
        return tensaoPUp1;
    }

    public void setTensaoPUp1(Double tensaoPUp1) {
        this.tensaoPUp1 = tensaoPUp1;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTtensoesReferencia.OUTtensaoP2")
    public Double getTensaoPUp2() {
        return tensaoPUp2;
    }

    public void setTensaoPUp2(Double tensaoPUp2) {
        this.tensaoPUp2 = tensaoPUp2;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTtensoesReferencia.OUTtensaoP3")
    public Double getTensaoPUp3() {
        return tensaoPUp3;
    }

    public void setTensaoPUp3(Double tensaoPUp3) {
        this.tensaoPUp3 = tensaoPUp3;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTtensoesReferencia.OUTtensaoP4")
    public Double getTensaoPUp4() {
        return tensaoPUp4;
    }

    public void setTensaoPUp4(Double tensaoPUp4) {
        this.tensaoPUp4 = tensaoPUp4;
    }

    public Double getTensaoReferencia() {
        return tensaoReferencia;
    }

    public void setTensaoReferencia(Double tensaoReferencia) {
        this.tensaoReferencia = tensaoReferencia;
    }

    private void calculaMaximos(Double iAtiva, Double iReativa) {
        Double i = Math.sqrt((iAtiva * iAtiva) + (iReativa * iReativa));
        if (iMaxima == null || i > iMaxima) {
            iMaxima = i;
        }
        Double anguloFatorPotencia = Math.acos(iAtiva / i);
        if (anguloFatorPotenciaMaximo == null || anguloFatorPotenciaMaximo < anguloFatorPotencia) {
            anguloFatorPotenciaMaximo = anguloFatorPotencia;
        }
    }

    private Double calculaTensaoEntrada(Double moduloTensao) {
        return moduloTensao / getTensaoNominalPrimaria();
    }

    private Double calculaGanhoMaximo() {
        Long ur = calculaUR();
        Long ux = calculaUX();
        return (ur * (iMaxima / correnteNominal) * Math.cos(anguloFatorPotenciaMaximo)) + (ux * (iMaxima / correnteNominal) * Math.sin(anguloFatorPotenciaMaximo));
    }

    private Double calculaTensaoReferencia() {
        if (tensaoReferencia != null) return tensaoReferencia;
        if (classeTensao == 15.0) {
            return Math.floor(tensaoMaxima / (rtp / 1000.0) - Math.floor(calculaGanhoMaximo()));
        } else {
            return Math.floor((tensaoMaxima / RAIZ_TRES) / (rtp / 1000.0) - Math.floor(calculaGanhoMaximo()));
        }
    }

    private Double calculaVariacaoPorTap() {
        if (tipoLigacao.equals("Delta")) {
            return 15.0 / (numPosicoes / 2);
        } else {
            return 10.0 / (numPosicoes / 2);
        }
    }

    public double getTensaoSaida(String tipoDia, Integer indiceHorario, double tensaoEntrada, double iAtiva, double iReativa, double P, double Q) {
        calculaTap(tipoDia, indiceHorario, iAtiva, iReativa, P, Q, tensaoEntrada);
        return getTensaoSaida(tensaoEntrada);
    }

    public Double getTensaoSaida(Double moduloTensao) {
        if (!getAtivado()) return moduloTensao;
        Double tensaoEntrada = calculaTensaoEntrada(moduloTensao);
        Double pu = (1 + (getPosicaoAtual() * calculaVariacaoPorTap()) / 100) * tensaoEntrada;
        return getTensaoNominalPrimaria() * pu;
    }

    public Integer calculaTap(String tipoDia, Integer indiceHorario, Double iAtiva, Double iReativa, Double P, Double Q, Double moduloTensao) {
        calculaMaximos(iAtiva, iReativa);
        Long UR = calculaUR();
        Long UX = calculaUX();
        Double umPU = calcula1Pu();
        Double tensaoEntrada = calculaTensaoEntrada(moduloTensao);
        Double tensaoReferencia = calculaTensaoReferencia();
        Double variacaoPorTap = calculaVariacaoPorTap();
        Double dTap = ((tensaoReferencia + (UR * iAtiva / correnteNominal) + (UX * iReativa / correnteNominal)) -
                (tensaoEntrada * umPU)) / variacaoPorTap;
        Integer tap = Double.valueOf(Math.floor(dTap)).intValue();
        if (tap > (numPosicoes / 2)) tap = numPosicoes / 2;
        if (tap < -(numPosicoes / 2)) tap = -(numPosicoes / 2);
        if (getAtivado()) {
            setPosicaoAtual(tap);
        }
        if (indiceHorario < 24 && simulacao != null) {
            Double pu = (1 + (getPosicaoAtual() * calculaVariacaoPorTap()) / 100) * tensaoEntrada;
            Double V = getTensaoNominalPrimaria() * pu;
            Double i = Math.sqrt(iAtiva * iAtiva + iReativa * iReativa);
            Map<String, Map<String, Number>> mapTipoDia = simulacao.get(indiceHorario);
            Map<String, Number> mapSimula = mapTipoDia.get(tipoDia);
            if (mapSimula == null) {
                mapSimula = new HashMap<String, Number>();
                mapTipoDia.put(tipoDia, mapSimula);
            }
            Double potencia = Math.sqrt(P * P + Q * Q);
            mapSimula.put("tensao_entrada", moduloTensao);
            mapSimula.put("tensao_saida", V);
            mapSimula.put("tap", getPosicaoAtual());
            mapSimula.put("corrente", i);
            mapSimula.put("potencia", potencia);
        }
        return tap;
    }

    private int posicaoAtual = 0;

    public int getPosicaoAtual() {
        return posicaoAtual;
    }

    public void setPosicaoAtual(int posicaoAtual) {
        numeroOperacoes += Math.abs(posicaoAtual - this.posicaoAtual);
        this.posicaoAtual = posicaoAtual;
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

    public INavegavel getINavegavelAssociado() {
        return getTrechoRede();
    }

    private Double calcula1Pu() {
        if (classeTensao == 15.0) {
            return Math.floor((13.8 / rtp) * 1000);
        } else {
            return Math.floor((23.1 / RAIZ_TRES / rtp) * 1000);
        }
    }

    public Boolean ativado;
    public Double passo;
    private Integer tipo;
    private Integer tipoLigacao;
    private Double potenciaNominalPorFase;
    private Double tensaoNominalPrimaria;
    private Double correnteNominal;
    private Integer numPosicoes;
    private Boolean permiteInversao;
    private Double classeTensao = 15.0;
    private Integer anoSimulado;
    private Integer patamarSimulado;

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

    public Double getCorrenteNominal() {
        return correnteNominal;
    }

    public void setCorrenteNominal(Double correnteNominal) {
        this.correnteNominal = correnteNominal;
    }

    private TrechoRede trechoRede;
    private TrechoRede trechoRedeBkp;
    private Alimentador alimentador;

    public TrechoRede getTrechoRedeBkp() {
        return trechoRedeBkp;
    }

    public void setTrechoRedeBkp(TrechoRede trechoRedeBkp) {
        this.trechoRedeBkp = trechoRedeBkp;
    }

    public IMarcacaoMapa novaMarcacaoMapa(IMarcacaoMapa novaMarcacaoMapa) {
        novaMarcacaoMapa.setTitulo("Novo Regulador de Tensão");
        novaMarcacaoMapa.setTipoMarcacao(IMarcacaoMapa.TIPO_APRESENTACAO_PONTO);

        IMarcacaoMapa m = trechoRede.getMarcacaoMapa().getMarcacoesLinha().get(0);
        novaMarcacaoMapa.setLatitude(m.getLatitude());
        novaMarcacaoMapa.setLongitude(m.getLongitude());

        novaMarcacaoMapa.setNomeIcone("carregar-arquivo.htm?id=443&abrir=true");
        return novaMarcacaoMapa;
    }

    @InformacoesSilas
    public Boolean getAtivado() {
        return ativado;
    }

    public void setAtivado(Boolean ativado) {
        this.ativado = ativado;
    }

    @InformacoesSilas
    public Double getPasso() {
        return passo;
    }

    public void setPasso(Double passo) {
        this.passo = passo;
    }

    @InformacoesSilas
    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTTipoLigacaoInteiro")
    public Integer getTipoLigacao() {
        return tipoLigacao;
    }

    public void setTipoLigacao(Integer tipoLigacao) {
        this.tipoLigacao = tipoLigacao;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTPotenciaAtivaNominalPorFase")
    public Double getPotenciaNominalPorFase() {
        return potenciaNominalPorFase;
    }

    public void setPotenciaNominalPorFase(Double potenciaNominalPorFase) {
        this.potenciaNominalPorFase = potenciaNominalPorFase;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTtensaoPrimariaNominal")
    public Double getTensaoNominalPrimaria() {
        return tensaoNominalPrimaria;
    }

    public void setTensaoNominalPrimaria(Double tensaoNominalPrimaria) {
        this.tensaoNominalPrimaria = tensaoNominalPrimaria;
        if (tensaoNominalPrimaria == 23.1) {
            classeTensao = 25.0;
        } else {
            classeTensao = 15.0;
        }
    }

    public Integer getNumPosicoes() {
        return numPosicoes;
    }

    public void setNumPosicoes(Integer numPosicoes) {
        this.numPosicoes = numPosicoes;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTTrechoRede")
    public TrechoRede getTrechoRede() {
        return trechoRede;
    }

    public void setTrechoRede(TrechoRede trechoRede) {
        this.trechoRede = trechoRede;
    }

    public Alimentador getAlimentador() {
        return getTrechoRede() == null ? null : getTrechoRede().getAlimentador();
    }

    @InformacoesSilas
    public Boolean getPermiteInversao() {
        return permiteInversao;
    }

    public void setPermiteInversao(Boolean permiteInversao) {
        this.permiteInversao = permiteInversao;
    }

    private Long UX = null;

    public Long getUX() {
        if (UX == null) return calculaUX();
        return UX;
    }

    public Long getUR() {
        if (UR == null) return calculaUR();
        return UR;
    }

    public void setUX(Long UX) {
        this.UX = UX;
    }

    private Double tensaoMaxima = 14.15;
    private Double tensaoMinima = 13.8;
    private Integer numeroOperacoes = 0;


    public void preparaExecucao(Double tensaoMinima, Double tensaoMaxima) {
        this.tensaoMinima = tensaoMinima;
        this.tensaoMaxima = tensaoMaxima;
        this.iMaxima = null;
        this.anguloFatorPotenciaMaximo = null;
        UR = null;
        UX = null;
        this.numeroOperacoes = 0;
        this.tensaoReferencia = null;
        //encontraZonaTap();
        simulacao = new ArrayList<Map<String, Map<String, Number>>>();
        for (int i = 0; i < 24; i++) {
            simulacao.add(new HashMap<String, Map<String, Number>>());
        }
    }

    public Double getRtp() {
        return rtp;
    }

    public void setRtp(Double rtp) {
        this.rtp = rtp;
    }

    private Double rtp = 120.0;

    private Long calculaUX() {
        if (UX != null) return UX;
        if (classeTensao == 15.0) {
            return Math.round(((tensaoMaxima - tensaoMinima) / (rtp / 1000.0)) / ((iMaxima / correnteNominal) * 1 / (Math.sin(anguloFatorPotenciaMaximo))));
        } else {
            return Math.round(((tensaoMaxima - tensaoMinima) / (rtp * RAIZ_TRES / 1000.0)) / ((iMaxima / correnteNominal) * 1 / (Math.sin(anguloFatorPotenciaMaximo))));
        }
    }

    private Long UR = null;

    public void setUR(Long UR) {
        this.UR = UR;
    }

    private Long calculaUR() {
        if (UR != null) return UR;
        if (classeTensao == 15.0) {
            return Math.round(((tensaoMaxima - tensaoMinima) / (rtp / 1000.0)) / ((iMaxima / correnteNominal) * 1 / (Math.cos(anguloFatorPotenciaMaximo))));
        } else {
            return Math.round(((tensaoMaxima - tensaoMinima) / (rtp * RAIZ_TRES / 1000.0)) / ((iMaxima / correnteNominal) * 1 / (Math.cos(anguloFatorPotenciaMaximo))));
        }
    }


    public void setRelacaoTransformacao(Double[] relacaoTransformacao) {
        this.relacaoTransformacao = relacaoTransformacao;
    }
}
