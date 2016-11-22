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
     * A��o que � executada no momento anterior a navega��o dos filhos do
     * n� n na �rvore.
     * @param n n� em que se est� e onde a a��o ter� efeito.
     * @param anterior n� que foi visitado anteriormente.
     * @param params par�metros passados pelo n� pai.
     * @return par�metros a serem passados aos filhos
     */
    Object executePre(INavegavel n, INavegavel anterior, Object params);
}
