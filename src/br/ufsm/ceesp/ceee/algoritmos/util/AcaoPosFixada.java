package br.ufsm.ceesp.ceee.algoritmos.util;


import br.ufsm.ceesp.ceee.model.INavegavel;

/**
 * Created by IntelliJ IDEA.
 * User: milbradt
 * Date: 03/06/2005
 * Time: 09:22:23
 * To change this template use File | Settings | File Templates.
 */
public interface AcaoPosFixada {

    /**
     * A��o que � executada num momento posterior a navega��o dos filhos do n� n
     * na �rvore, ou seja, no retorno da navega��o.
     * @param n n� em que se est� e onde a a��o ter� efeito.
     * @param anterior n�s que foi visitado anteriormente.
     * @param params resultado das a��es pos-fiadas executadas nos filhos deste n�.
     * @return resultado desta a��o p�s-fixada que ser� passado ao pai deste n�.
     */
    Object executePos(INavegavel n, INavegavel anterior, Object params);

    /**
     * Opera os dois resultados de irm�os na �rvore
     * @param resultadoParcial resultado parcial, que pode ser nulo
     * @param resultadoRamo resultado de um dos filhos, que ser� operado com o resultado parcial
     * @return o resultado desta opera�ao entre os operandos
     */
    Object operaResultados(Object resultadoParcial, Object resultadoRamo);

}
