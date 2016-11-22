package br.ufsm.ceesp.ceee.model;

import br.com.mega.silas.algoritmos.InformacoesSilas;
import br.ufsm.ceesp.ceee.algoritmos.impl.CalculoIndicadoresConfiabilidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by politecnico on 05/05/2016.
 */
@InformacoesSilas(nomeTipoPropriedade = "OUTmedidasConfiabilidade")
public class MedidaConfiabilidade implements Serializable {

    private String nome;
    private String unidade;
    private Double valor;

    private static Collection<String> NOMES_MEDIDAS = new ArrayList<String>();

    static {
        NOMES_MEDIDAS.add(CalculoIndicadoresConfiabilidade.DEC);
        NOMES_MEDIDAS.add(CalculoIndicadoresConfiabilidade.FEC);
        NOMES_MEDIDAS.add(CalculoIndicadoresConfiabilidade.ENS);
        NOMES_MEDIDAS.add(CalculoIndicadoresConfiabilidade.CONSUMIDORES_ACUMULADOS_PROTECAO);
        NOMES_MEDIDAS.add(CalculoIndicadoresConfiabilidade.DISTANCIA_ACUMULADA_PROTECAO);
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTnome")
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTunidade")
    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTvalorConfiabilidade")
    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public static boolean isMedidaConfiabilidade(MedidaEletrica medidaEletrica) {
        for (String n : NOMES_MEDIDAS) {
            if (n.equals(medidaEletrica.getNome())) return true;
        }
        return false;
    }
}
