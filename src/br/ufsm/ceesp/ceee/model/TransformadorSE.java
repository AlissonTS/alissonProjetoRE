package br.ufsm.ceesp.ceee.model;

import br.com.mega.silas.algoritmos.IMarcacaoMapa;
import br.com.mega.silas.algoritmos.InformacoesSilas;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Mega on 31/07/14.
 */

@InformacoesSilas(nomeTipoPropriedade = "OUTTransformadorSE")
public class TransformadorSE implements Serializable {

    private Long id;
    private Map<String, MedidaEletrica> medidasEletricas = new LinkedHashMap<String, MedidaEletrica>();
    private IMarcacaoMapa marcacaoMapa;

    private Double tensaoPUp1;
    private Double tensaoPUp2;
    private Double tensaoPUp3;
    private Double tensaoPUp4;

    @InformacoesSilas(nomeTipoPropriedade = "OUTtensoesReferencia.OUTtensaoP1")
    public Double getTensaoPUp1() {
        return tensaoPUp1;
    }

    public void setTensaoPUp1(Double tensaoPUp1) {
        this.tensaoPUp1 = tensaoPUp1;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTtensoesReferencia.OUTtensaoP2")
    public Double getTensaoPUp2() {
        return tensaoPUp2;
    }

    public void setTensaoPUp2(Double tensaoPUp2) {
        this.tensaoPUp2 = tensaoPUp2;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTtensoesReferencia.OUTtensaoP3")
    public Double getTensaoPUp3() {
        return tensaoPUp3;
    }

    public void setTensaoPUp3(Double tensaoPUp3) {
        this.tensaoPUp3 = tensaoPUp3;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTtensoesReferencia.OUTtensaoP4")
    public Double getTensaoPUp4() {
        return tensaoPUp4;
    }

    public void setTensaoPUp4(Double tensaoPUp4) {
        this.tensaoPUp4 = tensaoPUp4;
    }

    public Double getTensao(int patamar) {
        if (patamar == 1 && getTensaoPUp1() != null) {
            return getTensaoPUp1() * getTensaoNominalSecundaria();
        }
        if (patamar == 2 && getTensaoPUp2() != null) {
            return getTensaoPUp2() * getTensaoNominalSecundaria();
        }
        if (patamar == 3 && getTensaoPUp3() != null) {
            return getTensaoPUp3() * getTensaoNominalSecundaria();
        }
        if (patamar == 4 && getTensaoPUp4() != null) {
            return getTensaoPUp4() * getTensaoNominalSecundaria();
        }
        return null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTmedidasEletricas")
    public Map<String, MedidaEletrica> getMedidasEletricas() {
        return medidasEletricas;
    }


    public void setMedidasEletricas(Map<String, MedidaEletrica> medidasEletricas) {
        this.medidasEletricas = medidasEletricas;
    }


    public MedidaEletrica adicionaMedidaEletrica(String nomeMedida, MedidaEletrica medidaEletrica) {
        return medidasEletricas.put(nomeMedida, medidaEletrica);
    }


    public MedidaEletrica removerMedidaEletrica(MedidaEletrica medidaEletrica) {
        return medidasEletricas.remove(medidaEletrica);
    }


    public MedidaEletrica getMedidaEletrica(String nomeMedida) {
        return medidasEletricas.get(nomeMedida);
    }

    public IMarcacaoMapa getMarcacaoMapa() {
        return marcacaoMapa;
    }

    public void setMarcacaoMapa(IMarcacaoMapa marcacaoMapa) {
        this.marcacaoMapa = marcacaoMapa;
    }

    private Double potenciaNominal;
    private Double PotenciaNominalVentilada;
    private Double tensaoNominalPrimaria;
    private Double tensaoNominalSecundaria;
    private Double tensaoNominalOperacao;
    private Double r0;
    private Double r1;
    private Double x0;
    private Double x1;
    private Subestacao subestacao;

    @InformacoesSilas
    public Double getPotenciaNominal() {
        return potenciaNominal;
    }

    public void setPotenciaNominal(Double potenciaNominal) {
        this.potenciaNominal = potenciaNominal;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTPotenciaVentilada")
    public Double getPotenciaNominalVentilada() {
        return PotenciaNominalVentilada;
    }

    public void setPotenciaNominalVentilada(Double potenciaNominalVentilada) {
        PotenciaNominalVentilada = potenciaNominalVentilada;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTtensaoPrimariaNominal")
    public Double getTensaoNominalPrimaria() {
        return tensaoNominalPrimaria;
    }

    public void setTensaoNominalPrimaria(Double tensaoNominalPrimaria) {
        this.tensaoNominalPrimaria = tensaoNominalPrimaria;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTtensaoSecundariaNominal")
    public Double getTensaoNominalSecundaria() {
        return tensaoNominalSecundaria;
    }

    public void setTensaoNominalSecundaria(Double tensaoNominalSecundaria) {
        this.tensaoNominalSecundaria = tensaoNominalSecundaria;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTTensaoOperacaoSecundaria")
    public Double getTensaoNominalOperacao() {
        return tensaoNominalOperacao;
    }

    public void setTensaoNominalOperacao(Double tensaoNominalOperacao) {
        this.tensaoNominalOperacao = tensaoNominalOperacao;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTr0")
    public Double getR0() {
        return r0;
    }

    public void setR0(Double r0) {
        this.r0 = r0;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTr1")
    public Double getR1() {
        return r1;
    }

    public void setR1(Double r1) {
        this.r1 = r1;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTx0")
    public Double getX0() {
        return x0;
    }

    public void setX0(Double x0) {
        this.x0 = x0;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTx1")
    public Double getX1() {
        return x1;
    }

    public void setX1(Double x1) {
        this.x1 = x1;
    }

    @InformacoesSilas(nomeTipoPropriedade = "PSMsubestacao")
    public Subestacao getSubestacao() {
        return subestacao;
    }

    public void setSubestacao(Subestacao subestacao) {
        this.subestacao = subestacao;
    }
}
