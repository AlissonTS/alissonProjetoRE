package br.ufsm.ceesp.ceee.model;

/**
 * Created by Eduardo on 15/01/2015.
 */

import br.com.mega.silas.algoritmos.InformacoesSilas;

import java.io.Serializable;

@InformacoesSilas(nomeTipoPropriedade = "OUTcurvaTipica")
public class CurvaTipica implements Serializable {
    private Long id;
    private Integer hora;
    private Double consumoNormalizado;
    private ClasseConsumidor classeConsumidor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUThora")
    public Integer getHora() {
        return hora;
    }

    public void setHora(Integer hora) {
        this.hora = hora;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTconsumoNormalizado")
    public Double getConsumoNormalizado() {
        return consumoNormalizado;
    }

    public void setConsumoNormalizado(Double consumoNormalizado) {
        this.consumoNormalizado = consumoNormalizado;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTcategoriaConsumo")
    public ClasseConsumidor getClasseConsumidor() {
        return classeConsumidor;
    }

    public void setClasseConsumidor(ClasseConsumidor classeConsumidor) {
        this.classeConsumidor = classeConsumidor;
    }
}
