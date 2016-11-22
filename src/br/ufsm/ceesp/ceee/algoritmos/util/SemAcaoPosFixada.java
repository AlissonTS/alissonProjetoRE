package br.ufsm.ceesp.ceee.algoritmos.util;


import br.ufsm.ceesp.ceee.model.INavegavel;

/**
 * Created with IntelliJ IDEA.
 * User: Rafael
 * Date: 28/02/14
 * Time: 17:41
 * To change this template use File | Settings | File Templates.
 */
public class SemAcaoPosFixada implements AcaoPosFixada {

    public static final SemAcaoPosFixada INSTANCE = new SemAcaoPosFixada();

    private SemAcaoPosFixada() { }

    public Object executePos(INavegavel n, INavegavel anterior, Object params) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Object operaResultados(Object resultadoParcial, Object resultadoRamo) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

}
