package br.ufsm.ceesp.ceee.algoritmos.impl;

import br.ufsm.ceesp.ceee.model.Chave;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by politecnico on 21/11/2016.
 */
public class DadosAjusteCarga {

    public Chave medidor;
    public Double[] difP;
    public Double[] difQ;
    public Double[] difIAt;
    public Double[] difIReat;

    public Double[] totalP;
    public Double[] totalQ;
    public Double[] totalIAt;
    public Double[] totalIReat;
    public Double[] difDistribuidaAt = new Double[] { 0.0, 0.0, 0.0 };
    public Double[] difDistribuidaReat = new Double[] { 0.0, 0.0, 0.0 };
    public Double[] difDistribuidaP = new Double[] { 0.0, 0.0, 0.0 };
    public Double[] difDistribuidaQ = new Double[] { 0.0, 0.0, 0.0 };
    private NumberFormat DF = new DecimalFormat("#0.00");
    public Double iMedido;
    public Double iCalculado;
    public Double[] PQMedido;
    public Double[] PQCalculado;

    @Override
    public String toString() {
        if (difIAt != null) {
            return "Medidor Chave " + medidor.getTipoChave().getTipo() + " " + medidor.getCodigoAlfanumerico() + ": <br />" +
                    "IMedido = " + DF.format(iMedido) + ", ICalculado = " + DF.format(iCalculado) + "<br />" +
                    "TotalIAt =[" + DF.format(totalIAt[0]) + ", " + DF.format(totalIAt[1]) + ", " + DF.format(totalIAt[2]) + "]<br />" +
                    "TotalIReat =[" + DF.format(totalIReat[0]) + ", " + DF.format(totalIReat[1]) + ", " + DF.format(totalIReat[2]) + "]<br />" +
                    "DifIAt =[" + DF.format(difIAt[0]) + ", " + DF.format(difIAt[1]) + ", " + DF.format(difIAt[2]) + "]<br />" +
                    "DifIReat =[" + DF.format(difIReat[0]) + ", " + DF.format(difIReat[1]) + ", " + DF.format(difIReat[2]) + "]<br />" +
                    "DistrAt =[" + DF.format(difDistribuidaAt[0]) + ", " + DF.format(difDistribuidaAt[1]) + ", " + DF.format(difDistribuidaAt[2]) + "]<br />" +
                    "DistrReat =[" + DF.format(difDistribuidaReat[0]) + ", " + DF.format(difDistribuidaReat[1]) + ", " + DF.format(difDistribuidaReat[2]) + "]<br /><br />";
        } else {
            return "Medidor Chave " + medidor.getTipoChave().getTipo() + " " + medidor.getCodigoAlfanumerico() + ": <br />" +
                    "PQMedido = " + DF.format(PQMedido[0]) + " " + DF.format(PQMedido[1]) + ", PQCalculado = " + DF.format(PQCalculado[0]) + " " + DF.format(PQCalculado[1]) + "<br />" +
                    "TotalP =[" + DF.format(totalP[0]) + ", " + DF.format(totalP[1]) + ", " + DF.format(totalP[2]) + "]<br />" +
                    "TotalQ =[" + DF.format(totalQ[0]) + ", " + DF.format(totalQ[1]) + ", " + DF.format(totalQ[2]) + "]<br />" +
                    "DifP =[" + DF.format(difP[0]) + ", " + DF.format(difP[1]) + ", " + DF.format(difP[2]) + "]<br />" +
                    "DifQ =[" + DF.format(difQ[0]) + ", " + DF.format(difQ[1]) + ", " + DF.format(difQ[2]) + "]<br />" +
                    "DistrP =[" + DF.format(difDistribuidaP[0]) + ", " + DF.format(difDistribuidaP[1]) + ", " + DF.format(difDistribuidaP[2]) + "]<br />" +
                    "DistrQ =[" + DF.format(difDistribuidaQ[0]) + ", " + DF.format(difDistribuidaQ[1]) + ", " + DF.format(difDistribuidaQ[2]) + "]<br /><br />";
        }
    }

}
