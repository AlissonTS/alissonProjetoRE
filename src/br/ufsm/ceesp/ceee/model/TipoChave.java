package br.ufsm.ceesp.ceee.model;

import br.com.mega.silas.algoritmos.InformacoesSilas;

import java.io.Serializable;

/**
 * Created by Eduardo on 10/05/2016.
 */

@InformacoesSilas(nomeTipoPropriedade = "OUTtipoChave")
public class TipoChave implements Serializable {
    private Long id;
    private String tipo;
    private String codigo;
    private Double limiteElofusivelSGF;
    private Double limiteElofusivel;

    @InformacoesSilas(nomeTipoPropriedade = "OUTlimiteEloFusivelSGF")
    public Double getLimiteEloFusivelSGF() {
        return limiteElofusivelSGF;
    }

    public void setLimiteEloFusivelSGF(Double limiteElofusivelSGF) {
        this.limiteElofusivelSGF = limiteElofusivelSGF;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTlimiteEloFusivel")
    public Double getLimiteEloFusivel() {
        return limiteElofusivel;
    }

    public void setLimiteEloFusivel(Double limiteElofusivel) {
        this.limiteElofusivel = limiteElofusivel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTSubTipo")
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTCodigoString")
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public static enum CodigoMnemonicoDispositivoProtecao {FULB, SELB, CHSF, FUSI, CHAR, DISJ}
}
