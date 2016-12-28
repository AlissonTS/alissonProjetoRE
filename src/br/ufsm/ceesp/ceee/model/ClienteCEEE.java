package br.ufsm.ceesp.ceee.model;

/**
 * Created by Rafael on 28/12/2016.
 */
public class ClienteCEEE {

    private Long codigo;
    private Transformador transformador;
    private Double[] p24;
    private Double[] q24;
    private String tipo;
    private String subTipo;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Transformador getTransformador() {
        return transformador;
    }

    public void setTransformador(Transformador transformador) {
        this.transformador = transformador;
    }

    public Double[] getP24() {
        return p24;
    }

    public void setP24(Double[] p24) {
        this.p24 = p24;
    }

    public Double[] getQ24() {
        return q24;
    }

    public void setQ24(Double[] q24) {
        this.q24 = q24;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSubTipo() {
        return subTipo;
    }

    public void setSubTipo(String subTipo) {
        this.subTipo = subTipo;
    }
}
