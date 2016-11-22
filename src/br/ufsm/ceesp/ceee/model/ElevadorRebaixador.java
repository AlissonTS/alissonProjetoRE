package br.ufsm.ceesp.ceee.model;

import br.com.mega.silas.algoritmos.InformacoesSilas;

import java.io.Serializable;

/**
 * Created by politecnico on 01/07/2016.
 */
@InformacoesSilas(nomeTipoPropriedade = "OUTtransformadorElevadorRebaixador")
public class ElevadorRebaixador implements Serializable {

    private Long id;
    private String codigoAlfanumerico;
    private Boolean ativado;
    private Barra barraFonte;
    private Barra barraAlvo;
    private Double potenciaNominal;
    private Double tensaoPrimaria;
    private Double tensaoSecundaria;
    private transient TrechoRede trechoRede;
    private transient Double relTransformacao;

    public Double getRelTransformacao() {
        return relTransformacao;
    }

    public void setRelTransformacao(Double relTransformacao) {
        this.relTransformacao = relTransformacao;
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

    @InformacoesSilas(nomeTipoPropriedade = "OUTAtivado")
    public Boolean getAtivado() {
        return ativado != null && ativado;
    }

    public void setAtivado(Boolean ativado) {
        this.ativado = ativado;
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

    @InformacoesSilas(nomeTipoPropriedade = "OUTPotenciaNominal")
    public Double getPotenciaNominal() {
        return potenciaNominal;
    }

    public void setPotenciaNominal(Double potenciaNominal) {
        this.potenciaNominal = potenciaNominal;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTtensao")
    public Double getTensaoPrimaria() {
        return tensaoPrimaria;
    }

    public void setTensaoPrimaria(Double tensaoPrimaria) {
        this.tensaoPrimaria = tensaoPrimaria;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTTensaoOperacaoSecundaria")
    public Double getTensaoSecundaria() {
        return tensaoSecundaria;
    }

    public void setTensaoSecundaria(Double tensaoSecundaria) {
        this.tensaoSecundaria = tensaoSecundaria;
    }

    public TrechoRede getTrechoRede() {
        return trechoRede;
    }

    public void setTrechoRede(TrechoRede trechoRede) {
        this.trechoRede = trechoRede;
    }
}
