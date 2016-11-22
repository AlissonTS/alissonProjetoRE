package br.ufsm.ceesp.ceee.algoritmos.util;

/**
 * Created by IntelliJ IDEA.
 * User: Rafael
 * Date: 14/06/11
 * Time: 20:47
 * To change this template use File | Settings | File Templates.
 */
public interface AcaoPreFixadaParalela extends AcaoPreFixada {

    long getTempoEspera();

    void setTempoEspera(long tempo);

    void addTempoEspera(long tempo);


}
