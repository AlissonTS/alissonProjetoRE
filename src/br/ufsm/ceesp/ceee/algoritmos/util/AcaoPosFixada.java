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
     * Ação que é executada num momento posterior a navegação dos filhos do nó n
     * na árvore, ou seja, no retorno da navegação.
     * @param n nó em que se está e onde a ação terá efeito.
     * @param anterior nós que foi visitado anteriormente.
     * @param params resultado das ações pos-fiadas executadas nos filhos deste nó.
     * @return resultado desta ação pós-fixada que será passado ao pai deste nó.
     */
    Object executePos(INavegavel n, INavegavel anterior, Object params);

    /**
     * Opera os dois resultados de irmãos na árvore
     * @param resultadoParcial resultado parcial, que pode ser nulo
     * @param resultadoRamo resultado de um dos filhos, que será operado com o resultado parcial
     * @return o resultado desta operaçao entre os operandos
     */
    Object operaResultados(Object resultadoParcial, Object resultadoRamo);

}
