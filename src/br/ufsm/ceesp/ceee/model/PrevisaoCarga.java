package br.ufsm.ceesp.ceee.model;

import br.com.mega.silas.algoritmos.InformacoesSilas;

import java.io.Serializable;

/**
 * Created by Rafael on 24/02/2015.
 */
@InformacoesSilas(nomeTipoPropriedade = "OUTpotenciaSaida")
public class PrevisaoCarga implements Serializable {

    private Double P_A;
    private Double P_B;
    private Double P_C;

    private Double Q_A;
    private Double Q_B;
    private Double Q_C;

    @InformacoesSilas(nomeTipoPropriedade = "OUTpotenciaAtivaD")
    public Double getP_A() {
        return P_A;
    }

    public void setP_A(Double p_A) {
        P_A = p_A;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTpotenciaAtivaE")
    public Double getP_B() {
        return P_B;
    }

    public void setP_B(Double p_B) {
        P_B = p_B;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTpotenciaAtivaF")
    public Double getP_C() {
        return P_C;
    }

    public void setP_C(Double p_C) {
        P_C = p_C;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTpotenciaReativaD")
    public Double getQ_A() {
        return Q_A;
    }

    public void setQ_A(Double q_A) {
        Q_A = q_A;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTpotenciaReativaE")
    public Double getQ_B() {
        return Q_B;
    }

    public void setQ_B(Double q_B) {
        Q_B = q_B;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTpotenciaReativaF")
    public Double getQ_C() {
        return Q_C;
    }

    public void setQ_C(Double q_C) {
        Q_C = q_C;
    }
}
