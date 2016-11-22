package br.ufsm.ceesp.ceee.model;

import br.com.mega.silas.algoritmos.InformacoesSilas;

import java.io.Serializable;

/**
 * Created by Rafael on 28/06/2016.
 */
@InformacoesSilas(nomeTipoPropriedade = "OUTcapacitorSerie")
public class CapacitorSerie implements Serializable {

    private Long id;
    private String codigoAlfanumerico;
    private Double reatanciaSeq1;
    private Double reatanciaSeq0;
    private Integer numCelulas;
    private Boolean ativado;
    private Barra barraFonte;
    private Barra barraAlvo;
    private transient TrechoRede trechoRede;

    public TrechoRede getTrechoRede() {
        return trechoRede;
    }

    public void setTrechoRede(TrechoRede trechoRede) {
        this.trechoRede = trechoRede;
    }

    @InformacoesSilas(campoEspelho = true, nomeTipoPropriedade = "OUTBarraFonte")
    public Barra getBarraFonte() {
        return barraFonte;
    }

    public void setBarraFonte(Barra barraFonte) {
        this.barraFonte = barraFonte;
    }

    @InformacoesSilas(campoEspelho = true, nomeTipoPropriedade = "OUTBarraAlvo")
    public Barra getBarraAlvo() {
        return barraAlvo;
    }

    public void setBarraAlvo(Barra barraAlvo) {
        this.barraAlvo = barraAlvo;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTAtivado")
    public Boolean getAtivado() {
        return ativado;
    }

    public void setAtivado(Boolean ativado) {
        this.ativado = ativado;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTcodigo")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTCodigoString")
    public String getCodigoAlfanumerico() {
        return codigoAlfanumerico;
    }

    public void setCodigoAlfanumerico(String codigoAlfanumerico) {
        this.codigoAlfanumerico = codigoAlfanumerico;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTquantidadeCelulas")
    public Integer getNumCelulas() {
        return numCelulas == null ? 5 : numCelulas;
    }

    public void setNumCelulas(Integer numCelulas) {
        this.numCelulas = numCelulas;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTx1")
    public Double getReatanciaSeq1() {
        return reatanciaSeq1;
    }

    public void setReatanciaSeq1(Double reatanciaSeq1) {
        this.reatanciaSeq1 = reatanciaSeq1;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTx0")
    public Double getReatanciaSeq0() {
        return reatanciaSeq0;
    }

    public void setReatanciaSeq0(Double reatanciaSeq0) {
        this.reatanciaSeq0 = reatanciaSeq0;
    }
}
