package br.ufsm.ceesp.ceee.model;

import java.util.Collection;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Rafael
 * Date: 07/08/14
 * Time: 14:13
 * To change this template use File | Settings | File Templates.
 */
public interface INavegavel {

    Collection<INavegavel> getDestinos(INavegavel origem);

    Collection<INavegavel> getDestinos();

    void setDestinos(Collection<INavegavel> destinos);

    INavegavel getOrigem();

    void setOrigem(INavegavel origem);

    String getIdentificacaoAlimentador();

    Map<String, MedidaEletrica> getMedidasEletricas();

    void setMedidasEletricas(Map<String, MedidaEletrica> medidasEletricas);

    MedidaEletrica adicionaMedidaEletrica(String nomeMedida, MedidaEletrica medidaEletrica);

    MedidaEletrica removerMedidaEletrica(String medidaEletrica);

    MedidaEletrica getMedidaEletrica(String nomeMedida);

    Alimentador getAlimentador();

    boolean getNavegado();

    void setMarcadoCiclo();

    boolean isMarcadoCiclo();

    Double getTensaoNominal();

}
