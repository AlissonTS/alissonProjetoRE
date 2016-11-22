package br.ufsm.ceesp.ceee.algoritmos.util;


import br.ufsm.ceesp.ceee.model.INavegavel;

/**
 * Created by IntelliJ IDEA.
 * User: milbradt
 * Date: 03/06/2005
 * Time: 08:56:45
 * To change this template use File | Settings | File Templates.
 */
public interface AcaoPreFixada {

    /**
     * Ação que é executada no momento anterior a navegação dos filhos do
     * nó n na árvore.
     * @param n nó em que se está e onde a ação terá efeito.
     * @param anterior nó que foi visitado anteriormente.
     * @param params parâmetros passados pelo nó pai.
     * @return parâmetros a serem passados aos filhos
     */
    Object executePre(INavegavel n, INavegavel anterior, Object params);
}
