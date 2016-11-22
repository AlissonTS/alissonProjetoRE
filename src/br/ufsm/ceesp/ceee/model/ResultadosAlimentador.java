package br.ufsm.ceesp.ceee.model;

import br.com.mega.silas.algoritmos.InformacoesSilas;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by politecnico on 28/04/2015.
 */
@InformacoesSilas(nomeTipoPropriedade = "OUTResultadosAlimentador")
public class ResultadosAlimentador implements IResultadosAlimentador,Serializable {

    private Alimentador alimentador;
    private Double dec;
    private Double fec;
    private Double ens;
    private Double decConjunto;
    private Double fecConjunto;
    private Double decAES;
    private Double fecAES;
    private Double piorCarregamento;
    private Double maiorQuedaTensao;
    private Double potSaidaAlimentador;
    private Integer patamar;
    private Double perdas;
    private String mensagemAlerta;

    private Double extensaoAlimentador;
    private Double extensaoTroncal;
    private Integer qtdReguladores;
    private Integer qtdReguladoresSerie;
    private Integer qtdCapacitores;
    private Double potenciaCapacitiva;
    private Integer anoEstudo;
    private Double tensaoSaidaSE;
    private Double demandaAtivaAl;
    private Double demandaReativaAl;
    private Double correnteSaidaAl;
    private Double maiorCorrente;
    private Double carregSaidaAl;
    private String caboTrechoSaida;
    private Double maiorCarregamentoTronco;
    private String caboMaiorCarregamentoTronco;
    private Double extensaoTrechoCarregTroncal;
    private Double piorCarreg;
    private String caboPiorCarreg;
    private Double tensaoMinimaPU;
    private Double tensaoMinimaKV;
    private Double perdaAtivaPerc;
    private Double perdaAtivaMW;
    private Integer maiorQtdClientesJusanteTrechoCarregado;
    private Integer qtdClientesTensaoPrecaria;
    private Integer qtdClientesTensaoCritica;
    private Integer qtdEPTensaoPrecaria;
    private Integer qtdEPTensaoCritica;
    private Date dataDiagnostico;
    private Double decHistAES;
    private Double fecHistAES;
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTdecHistoricoAES")
    public Double getDecHistAES() {
        return decHistAES;
    }

    public void setDecHistAES(Double decHistAES) {
        this.decHistAES = decHistAES;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTfecHistoricoAES")
    public Double getFecHistAES() {
        return fecHistAES;
    }

    public void setFecHistAES(Double fecHistAES) {
        this.fecHistAES = fecHistAES;
    }

    @InformacoesSilas(nomeTipoPropriedade = "PSMalimentador")
    public Alimentador getAlimentador() {
        return alimentador;
    }

    public void setAlimentador(Alimentador alimentador) {
        this.alimentador = alimentador;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTresultExtAlimentador")
    public Double getExtensaoAlimentador() {
        return extensaoAlimentador;
    }

    public void setExtensaoAlimentador(Double extensaoAlimentador) {
        this.extensaoAlimentador = extensaoAlimentador;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTresultExtTroncal")
    public Double getExtensaoTroncal() {
        return extensaoTroncal;
    }

    public void setExtensaoTroncal(Double extensaoTroncal) {
        this.extensaoTroncal = extensaoTroncal;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTresultQtdReguladores")
    public Integer getQtdReguladores() {
        return qtdReguladores;
    }

    public void setQtdReguladores(Integer qtdReguladores) {
        this.qtdReguladores = qtdReguladores;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTresultQtdReguladoresSerie")
    public Integer getQtdReguladoresSerie() {
        return qtdReguladoresSerie;
    }

    public void setQtdReguladoresSerie(Integer qtdReguladoresSerie) {
        this.qtdReguladoresSerie = qtdReguladoresSerie;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTresultQtdCapacitores")
    public Integer getQtdCapacitores() {
        return qtdCapacitores;
    }

    public void setQtdCapacitores(Integer qtdCapacitores) {
        this.qtdCapacitores = qtdCapacitores;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTresultPotCapacitiva")
    public Double getPotenciaCapacitiva() {
        return potenciaCapacitiva;
    }

    public void setPotenciaCapacitiva(Double potenciaCapacitiva) {
        this.potenciaCapacitiva = potenciaCapacitiva;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTresultAnoEstudo")
    public Integer getAnoEstudo() {
        return anoEstudo;
    }

    public void setAnoEstudo(Integer anoEstudo) {
        this.anoEstudo = anoEstudo;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTresultTensaoSaidaSE")
    public Double getTensaoSaidaSE() {
        return tensaoSaidaSE;
    }

    public void setTensaoSaidaSE(Double tensaoSaidaSE) {
        this.tensaoSaidaSE = tensaoSaidaSE;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTresultDemandaAtivaAl")
    public Double getDemandaAtivaAl() {
        return demandaAtivaAl;
    }

    public void setDemandaAtivaAl(Double demandaAtivaAl) {
        this.demandaAtivaAl = demandaAtivaAl;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTresultDemandaReativaAl")
    public Double getDemandaReativaAl() {
        return demandaReativaAl;
    }

    public void setDemandaReativaAl(Double demandaReativaAl) {
        this.demandaReativaAl = demandaReativaAl;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTresultCorrenteSaidaAl")
    public Double getCorrenteSaidaAl() {
        return correnteSaidaAl;
    }

    public void setCorrenteSaidaAl(Double correnteSaidaAl) {
        this.correnteSaidaAl = correnteSaidaAl;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTresultMaiorCorrenteSaida")
    public Double getMaiorCorrente() {
        return maiorCorrente;
    }

    public void setMaiorCorrente(Double maiorCorrente) {
        this.maiorCorrente = maiorCorrente;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTresultCarregSaida")
    public Double getCarregSaidaAl() {
        return carregSaidaAl;
    }

    public void setCarregSaidaAl(Double carregSaidaAl) {
        this.carregSaidaAl = carregSaidaAl;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTresultCaboTrechoSaida")
    public String getCaboTrechoSaida() {
        return caboTrechoSaida;
    }

    public void setCaboTrechoSaida(String caboTrechoSaida) {
        this.caboTrechoSaida = caboTrechoSaida;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTresultMaiorCarregTronco")
    public Double getMaiorCarregamentoTronco() {
        return maiorCarregamentoTronco;
    }

    public void setMaiorCarregamentoTronco(Double maiorCarregamentoTronco) {
        this.maiorCarregamentoTronco = maiorCarregamentoTronco;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTresultCaboMaiorCarregTronco")
    public String getCaboMaiorCarregamentoTronco() {
        return caboMaiorCarregamentoTronco;
    }

    public void setCaboMaiorCarregamentoTronco(String caboMaiorCarregamentoTronco) {
        this.caboMaiorCarregamentoTronco = caboMaiorCarregamentoTronco;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTresultExtTrechoCarregTronco")
    public Double getExtensaoTrechoCarregTroncal() {
        return extensaoTrechoCarregTroncal;
    }

    public void setExtensaoTrechoCarregTroncal(Double extensaoTrechoCarregTroncal) {
        this.extensaoTrechoCarregTroncal = extensaoTrechoCarregTroncal;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTresultPiorCarregamento")
    public Double getPiorCarreg() {
        return piorCarreg;
    }

    public void setPiorCarreg(Double piorCarreg) {
        this.piorCarreg = piorCarreg;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTresultCaboPiorCarregamento")
    public String getCaboPiorCarreg() {
        return caboPiorCarreg;
    }

    public void setCaboPiorCarreg(String caboPiorCarreg) {
        this.caboPiorCarreg = caboPiorCarreg;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTresultTensaoMinimaPU")
    public Double getTensaoMinimaPU() {
        return tensaoMinimaPU;
    }

    public void setTensaoMinimaPU(Double tensaoMinimaPU) {
        this.tensaoMinimaPU = tensaoMinimaPU;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTresultTensaoMinimaKV")
    public Double getTensaoMinimaKV() {
        return tensaoMinimaKV;
    }

    public void setTensaoMinimaKV(Double tensaoMinimaKV) {
        this.tensaoMinimaKV = tensaoMinimaKV;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTresultPerdaAtivaPerc")
    public Double getPerdaAtivaPerc() {
        return perdaAtivaPerc;
    }

    public void setPerdaAtivaPerc(Double perdaAtivaPerc) {
        this.perdaAtivaPerc = perdaAtivaPerc;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTresultPerdaAtivaMW")
    public Double getPerdaAtivaMW() {
        return perdaAtivaMW;
    }

    public void setPerdaAtivaMW(Double perdaAtivaMW) {
        this.perdaAtivaMW = perdaAtivaMW;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTresultQtdCliJusanteTrechoCarreg")
    public Integer getMaiorQtdClientesJusanteTrechoCarregado() {
        return maiorQtdClientesJusanteTrechoCarregado;
    }

    public void setMaiorQtdClientesJusanteTrechoCarregado(Integer maiorQtdClientesJusanteTrechoCarregado) {
        this.maiorQtdClientesJusanteTrechoCarregado = maiorQtdClientesJusanteTrechoCarregado;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTresultQtdCliTensaoPrecaria")
    public Integer getQtdClientesTensaoPrecaria() {
        return qtdClientesTensaoPrecaria;
    }

    public void setQtdClientesTensaoPrecaria(Integer qtdClientesTensaoPrecaria) {
        this.qtdClientesTensaoPrecaria = qtdClientesTensaoPrecaria;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTresultQtdCliTensaoCritica")
    public Integer getQtdClientesTensaoCritica() {
        return qtdClientesTensaoCritica;
    }

    public void setQtdClientesTensaoCritica(Integer qtdClientesTensaoCritica) {
        this.qtdClientesTensaoCritica = qtdClientesTensaoCritica;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTresultQtdEPTensaoPrecaria")
    public Integer getQtdEPTensaoPrecaria() {
        return qtdEPTensaoPrecaria;
    }

    public void setQtdEPTensaoPrecaria(Integer qtdEPTensaoPrecaria) {
        this.qtdEPTensaoPrecaria = qtdEPTensaoPrecaria;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTresultQtdEPTensaoCritica")
    public Integer getQtdEPTensaoCritica() {
        return qtdEPTensaoCritica;
    }

    public void setQtdEPTensaoCritica(Integer qtdEPTensaoCritica) {
        this.qtdEPTensaoCritica = qtdEPTensaoCritica;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTdecConjunto")
    public Double getDecConjunto() {
        return decConjunto;
    }

    public void setDecConjunto(Double decConjunto) {
        this.decConjunto = decConjunto;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTfecConjunto")
    public Double getFecConjunto() {
        return fecConjunto;
    }

    public void setFecConjunto(Double fecConjunto) {
        this.fecConjunto = fecConjunto;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTdecAES")
    public Double getDecAES() {
        return decAES;
    }

    public void setDecAES(Double decAES) {
        this.decAES = decAES;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTfecAES")
    public Double getFecAES() {
        return fecAES;
    }

    public void setFecAES(Double fecAES) {
        this.fecAES = fecAES;
    }

    @Override
    public Date getDataDiagnostico() {
        return dataDiagnostico;
    }

    @Override
    public void setDataDiagnostico(Date dataDiagnostico) {
        this.dataDiagnostico = dataDiagnostico;
    }

    @Override
    @InformacoesSilas(nomeTipoPropriedade = "OUTdec")
    public Double getDec() {
        return dec;
    }

    @Override
    public void setDec(Double dec) {
        this.dec = dec;
    }

    @Override
    @InformacoesSilas(nomeTipoPropriedade = "OUTfec")
    public Double getFec() {
        return fec;
    }

    @Override
    public void setFec(Double fec) {
        this.fec = fec;
    }

    @Override
    @InformacoesSilas(nomeTipoPropriedade = "OUTens")
    public Double getEns() {
        return ens;
    }

    @Override
    public void setEns(Double ens) {
        this.ens = ens;
    }

    @Override
    @InformacoesSilas(nomeTipoPropriedade = "OUTPiorCarregamento")
    public Double getPiorCarregamento() {
        return piorCarregamento;
    }

    @Override
    public void setPiorCarregamento(Double piorCarregamento) {
        this.piorCarregamento = piorCarregamento;
    }

    @Override
    @InformacoesSilas(nomeTipoPropriedade = "OUTMaiorQuedaTensao")
    public Double getMaiorQuedaTensao() {
        return maiorQuedaTensao;
    }

    public Double getMenorTensaoPU() {
        return (100.0 - maiorQuedaTensao) / 100.0;
    }

    @Override
    public void setMaiorQuedaTensao(Double maiorQuedaTensao) {
        this.maiorQuedaTensao = maiorQuedaTensao;
    }

    @Override
    @InformacoesSilas(nomeTipoPropriedade = "OUTPotSaidaAlimentador")
    public Double getPotSaidaAlimentador() {
        return potSaidaAlimentador;
    }

    @Override
    public void setPotSaidaAlimentador(Double potSaidaAlimentador) {
        this.potSaidaAlimentador = potSaidaAlimentador;
    }

    @Override
    @InformacoesSilas(nomeTipoPropriedade = "OUTPatamarSimulado")
    public Integer getPatamar() {
        return patamar;
    }

    @Override
    public void setPatamar(Integer patamar) {
        this.patamar = patamar;
    }

    @Override
    @InformacoesSilas(nomeTipoPropriedade = "OUTPerdasAlimentador")
    public Double getPerdas() {
        return perdas;
    }

    @Override
    public void setPerdas(Double perdas) {
        this.perdas = perdas;
    }

    private Double perdaskVA;

    public Double getPerdaskVA() {
        if (perdaskVA == null && perdas != null) {
            perdaskVA = potSaidaAlimentador * (perdas / 100.0);
        }
        return perdaskVA;
    }

    public void setPerdaskVA(Double perdaskVA) {
        this.perdaskVA = perdaskVA;
    }

    @Override
    @InformacoesSilas(nomeTipoPropriedade = "OUTMensagemAlerta")
    public String getMensagemAlerta() {
        return mensagemAlerta;
    }

    @Override
    public void setMensagemAlerta(String mensagemAlerta) {
        this.mensagemAlerta = mensagemAlerta;
    }

    public void resetDiagnostico() {
        this.extensaoAlimentador = 0.0;
        this.extensaoTroncal = 0.0;
        this.qtdReguladores = 0;
        this.qtdReguladoresSerie = 0;
        this.qtdCapacitores = 0;
        this.potenciaCapacitiva = 0.0;
        this.demandaAtivaAl = 0.0;
        this.demandaReativaAl = 0.0;
        this.correnteSaidaAl = 0.0;
        this.maiorCorrente = 0.0;
        this.carregSaidaAl = 0.0;
        this.maiorCarregamentoTronco = 0.0;
        this.extensaoTrechoCarregTroncal = 0.0;
        this.piorCarreg = 0.0;
        this.tensaoMinimaPU = 1.0;
        this.tensaoMinimaKV = Double.MAX_VALUE;
        this.perdaAtivaPerc = 0.0;
        this.perdaAtivaMW = 0.0;
        this.maiorQtdClientesJusanteTrechoCarregado = 0;
        this.qtdClientesTensaoPrecaria = 0;
        this.qtdClientesTensaoCritica = 0;
        this.qtdEPTensaoPrecaria = 0;
        this.qtdEPTensaoCritica = 0;
        this.dataDiagnostico = new Date();
    }
}
