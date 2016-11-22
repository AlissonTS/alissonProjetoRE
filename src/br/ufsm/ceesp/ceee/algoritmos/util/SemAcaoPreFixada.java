package br.ufsm.ceesp.ceee.algoritmos.util;

import br.ufsm.ceesp.ceee.model.INavegavel;

/**
 * Created with IntelliJ IDEA.
 * User: Rafael
 * Date: 28/02/14
 * Time: 17:41
 * To change this template use File | Settings | File Templates.
 */
public class SemAcaoPreFixada implements AcaoPreFixada {

    public static final SemAcaoPreFixada INSTANCE = new SemAcaoPreFixada();

    private SemAcaoPreFixada() { }

    public Object executePre(INavegavel n, INavegavel anterior, Object params) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

}
