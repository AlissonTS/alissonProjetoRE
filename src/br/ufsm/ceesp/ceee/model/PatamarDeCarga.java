package br.ufsm.ceesp.ceee.model;

import br.com.mega.silas.algoritmos.InformacoesSilas;

import java.io.Serializable;

/**
 * Created by Rafael on 13/02/2015.
 */
@InformacoesSilas(nomeTipoPropriedade = "OUTpatamarCarga")
public class PatamarDeCarga implements Serializable {

    private Integer patamar;
    private Integer horaInicio;
    private Integer horaFim;
    private Integer horaCalculo;

    @InformacoesSilas(nomeTipoPropriedade = "OUTpatamar")
    public Integer getPatamar() {
        return patamar;
    }

    public void setPatamar(Integer patamar) {
        this.patamar = patamar;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUThoraInicial")
    public Integer getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Integer horaInicio) {
        this.horaInicio = horaInicio;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUThoraFinal")
    public Integer getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(Integer horaFim) {
        this.horaFim = horaFim;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUThoraCalculo")
    public Integer getHoraCalculo() {
        return horaCalculo;
    }

    public void setHoraCalculo(Integer horaCalculo) {
        this.horaCalculo = horaCalculo;
    }
}
