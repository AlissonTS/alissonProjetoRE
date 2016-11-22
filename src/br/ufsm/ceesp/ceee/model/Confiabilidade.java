package br.ufsm.ceesp.ceee.model;

import br.com.mega.silas.algoritmos.InformacoesSilas;

import java.io.Serializable;

/**
 * Created by Rafael on 13/03/2015.
 */

@InformacoesSilas(nomeTipoPropriedade = "OUTconfiabilidade")
public class Confiabilidade implements Serializable {
    private Long id;
    private Integer totalFalhasAlimentador;
    private Integer totalFalhasReligador;
    private Integer totalFalhasChaveFusivel;
    private Integer totalFalhasRepetidora;

    private Double duracaoMediaAlimentador;
    private Double duracaoMediaReligador;
    private Double duracaoMediaChaveFusivel;
    private Double duracaoMediaRepetidora;
    private Double decHistoricoConjunto;
    private Double fecHistoricoConjunto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTdecHistoricoConjunto")
    public Double getDecHistoricoConjunto() {
        return decHistoricoConjunto;
    }

    public void setDecHistoricoConjunto(Double decHistoricoConjunto) {
        this.decHistoricoConjunto = decHistoricoConjunto;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTfecHistoricoConjunto")
    public Double getFecHistoricoConjunto() {
        return fecHistoricoConjunto;
    }

    public void setFecHistoricoConjunto(Double fecHistoricoConjunto) {
        this.fecHistoricoConjunto = fecHistoricoConjunto;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTtaxasFalhasAlimentador.OUTtaxaFalhaTotal")
    public Integer getTotalFalhasAlimentador() {
        return totalFalhasAlimentador;
    }

    public void setTotalFalhasAlimentador(Integer totalFalhasAlimentador) {
        this.totalFalhasAlimentador = totalFalhasAlimentador;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTtaxasFalhasReligador.OUTtaxaFalhaTotal")
    public Integer getTotalFalhasReligador() {
        return totalFalhasReligador;
    }

    public void setTotalFalhasReligador(Integer totalFalhasReligador) {
        this.totalFalhasReligador = totalFalhasReligador;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTtaxasFalhasChaveFusivel.OUTtaxaFalhaTotal")
    public Integer getTotalFalhasChaveFusivel() {
        return totalFalhasChaveFusivel;
    }

    public void setTotalFalhasChaveFusivel(Integer totalFalhasChaveFusivel) {
        this.totalFalhasChaveFusivel = totalFalhasChaveFusivel;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTtaxasFalhasChaveFusivelRepetidora.OUTtaxaFalhaTotal")
    public Integer getTotalFalhasRepetidora() {
        return totalFalhasRepetidora;
    }

    public void setTotalFalhasRepetidora(Integer totalFalhasRepetidora) {
        this.totalFalhasRepetidora = totalFalhasRepetidora;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTtaxasFalhasAlimentador.OUTduracaoMedia")
    public Double getDuracaoMediaAlimentador() {
        return duracaoMediaAlimentador;
    }

    public void setDuracaoMediaAlimentador(Double duracaoMediaAlimentador) {
        this.duracaoMediaAlimentador = duracaoMediaAlimentador;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTtaxasFalhasReligador.OUTduracaoMedia")
    public Double getDuracaoMediaReligador() {
        return duracaoMediaReligador;
    }

    public void setDuracaoMediaReligador(Double duracaoMediaReligador) {
        this.duracaoMediaReligador = duracaoMediaReligador;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTtaxasFalhasChaveFusivel.OUTduracaoMedia")
    public Double getDuracaoMediaChaveFusivel() {
        return duracaoMediaChaveFusivel;
    }

    public void setDuracaoMediaChaveFusivel(Double duracaoMediaChaveFusivel) {
        this.duracaoMediaChaveFusivel = duracaoMediaChaveFusivel;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTtaxasFalhasChaveFusivelRepetidora.OUTduracaoMedia")
    public Double getDuracaoMediaRepetidora() {
        return duracaoMediaRepetidora;
    }

    public void setDuracaoMediaRepetidora(Double duracaoMediaRepetidora) {
        this.duracaoMediaRepetidora = duracaoMediaRepetidora;
    }
}
