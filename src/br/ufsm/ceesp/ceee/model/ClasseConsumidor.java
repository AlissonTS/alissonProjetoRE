package br.ufsm.ceesp.ceee.model;

import br.com.mega.silas.algoritmos.InformacoesSilas;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Eduardo on 15/01/2015.
 */
@InformacoesSilas(nomeTipoPropriedade = "OUTcategoriaConsumo")
public class ClasseConsumidor implements Serializable {
    private Long id;
    private Long codigo;
    private String nome;
    private Double limiteInferior;
    private Double limiteSuperior;
    private Integer numero;
    private Integer abrangencia;
    private Map<Integer, CurvaTipica> curvasTipicas = new LinkedHashMap<Integer, CurvaTipica>();

    @InformacoesSilas(nomeTipoPropriedade = "OUTabrangencia")
    public Integer getAbrangencia() {
        return abrangencia;
    }

    public void setAbrangencia(Integer abrangencia) {
        this.abrangencia = abrangencia;
    }

    public Double getWe() {
        int num = 0;
        double sum = 0.0;
        for (CurvaTipica curva : curvasTipicas.values()) {
            num++;
            sum += curva.getConsumoNormalizado();
        }
        if (num != 24) throw new IllegalArgumentException("curva típica sem os 24 valores");
        return sum;
    }

    public Double getDMed() {
        return getWe() / 24.0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTcodigo")
    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTnumeroCategoria")
    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTnome")
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTlimiteInferior")
    public Double getLimiteInferior() {
        return limiteInferior;
    }

    public void setLimiteInferior(Double limiteInferior) {
        this.limiteInferior = limiteInferior;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTlimiteSuperior")
    public Double getLimiteSuperior() {
        return limiteSuperior;
    }

    public void setLimiteSuperior(Double limiteSuperior) {
        this.limiteSuperior = limiteSuperior;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTcurvaTipica", valorDependende = true, campoIndiceMapa = "OUThora")
    public Map<Integer, CurvaTipica> getCurvasTipicas() {
        return curvasTipicas;
    }

    public void setCurvasTipicas(Map<Integer, CurvaTipica> curvasTipicas) {
        this.curvasTipicas = curvasTipicas;
    }
}
