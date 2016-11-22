package br.ufsm.ceesp.ceee.model.importador;

/**
 * Created by politecnico on 22/09/2016.
 */
public class Transformador {

    private Long id;
    private Barra barra;
    private Float potenciaNominal;
    private Double p;
    private Double q;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Barra getBarra() {
        return barra;
    }

    public void setBarra(Barra barra) {
        this.barra = barra;
    }

    public Float getPotenciaNominal() {
        return potenciaNominal;
    }

    public void setPotenciaNominal(Float potenciaNominal) {
        this.potenciaNominal = potenciaNominal;
    }

    public Double getP() {
        return p;
    }

    public void setP(Double p) {
        this.p = p;
    }

    public Double getQ() {
        return q;
    }

    public void setQ(Double q) {
        this.q = q;
    }

    private static long ID = 1;

    public static long getNextId() {
        return ID++;
    }
}
