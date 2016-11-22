package br.ufsm.ceesp.ceee.model;

import br.com.mega.silas.algoritmos.InformacoesSilas;

import java.io.Serializable;

/**
 * Created by politecnico on 23/07/2015.
 */
@InformacoesSilas(nomeTipoPropriedade = "OUTcustoModular")
public class CustoModular implements Serializable {

    private String condutor;
    private String tipoRedeEquipamento;
    private String tipoConstrucao;
    private String local;
    private Float custoModular;
    private Condutor propriedadesCondutor;

    public Condutor getPropriedadesCondutor() {
        return propriedadesCondutor;
    }

    public void setPropriedadesCondutor(Condutor propriedadesCondutor) {
        this.propriedadesCondutor = propriedadesCondutor;
    }

    public enum TipoRedeEquipamento {

        REDE_AEREA_NUA("Rede aérea nua"), REDE_COMPACTA("Rede aérea compacta"), EQUIPAMENTO("Equipamento"),
        COMPLEMENTO_FASE_MONO("Complemento Fase Mono para Tri"), COMPLEMENTO_FASE_BI("Complemento Fase Bi para Tri");

        private String nome;

        TipoRedeEquipamento(String nome) {
            this.nome = nome;
        }

        @Override
        public String toString() {
            return nome;
        }
    };

    public enum TipoLocal {

        URBANO("Urbano"), RURAL("Rural"), AMBOS("Urbano/Rural");

        private String nome;

        TipoLocal(String nome) {
            this.nome = nome;
        }

        @Override
        public String toString() {
            return nome;
        }
    }

    public enum TipoConstrucao {

        CONSTRUCAO("Construção"), REFORCO("Reforço"), NOVO("Novo"), DESLOCAMENTO("Deslocamento");

        private String nome;

        TipoConstrucao(String nome) {
            this.nome = nome;
        }


        @Override
        public String toString() {
            return nome;
        }
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTcondutor")
    public String getCondutor() {
        return condutor;
    }

    public void setCondutor(String condutor) {
        this.condutor = condutor;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTtipoRedeEquipamento")
    public String getTipoRedeEquipamento() {
        return tipoRedeEquipamento;
    }

    public void setTipoRedeEquipamento(String tipoRedeEquipamento) {
        this.tipoRedeEquipamento = tipoRedeEquipamento;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTtipoConstrucao")
    public String getTipoConstrucao() {
        return tipoConstrucao;
    }

    public void setTipoConstrucao(String tipoConstrucao) {
        this.tipoConstrucao = tipoConstrucao;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTlocal")
    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTvalorCustoModular")
    public Float getCustoModular() {
        return custoModular;
    }

    public void setCustoModular(Float custoModular) {
        this.custoModular = custoModular;
    }
}
