package br.ufsm.ceesp.ceee.model;

/**
 * Created by politecnico on 28/09/2016.
 */
public interface Seccionalizador {

    Barra getBarraInicial();
    Barra getBarraFinal();
    String getNomeDispositivoProtecao();
    boolean isTelecomandavel();
    String getModoOperacao();
    Integer getEstadoAberturaAtual();
    void setEstadoAberturaAtual(Integer estado);

}
