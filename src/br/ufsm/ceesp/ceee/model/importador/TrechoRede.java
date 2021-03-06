package br.ufsm.ceesp.ceee.model.importador;

/**
 * Created by politecnico on 22/09/2016.
 */
public class TrechoRede {

    private Long id;
    private Barra barraInicial;
    private Barra barraFinal;
    private Float comprimento;
    private String condutor;
    private String fases;
    private Integer qtdFases;
    private Chave chave;
    private Regulador regulador;

    public String getFases() {
        return fases;
    }

    public void setFases(String fases) {
        this.fases = fases;
    }

    public Integer getQtdFases() {
        return qtdFases;
    }

    public void setQtdFases(Integer qtdFases) {
        this.qtdFases = qtdFases;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Barra getBarraInicial() {
        return barraInicial;
    }

    public void setBarraInicial(Barra barraInicial) {
        this.barraInicial = barraInicial;
    }

    public Barra getBarraFinal() {
        return barraFinal;
    }

    public void setBarraFinal(Barra barraFinal) {
        this.barraFinal = barraFinal;
    }

    public Float getComprimento() {
        return comprimento;
    }

    public void setComprimento(Float comprimento) {
        this.comprimento = comprimento;
    }

    public String getCondutor() {
        return condutor;
    }

    public void setCondutor(String condutor) {
        this.condutor = condutor;
    }

    private static long ID = 1;

    public static long getNextId() {
        return ID++;
    }

    public Chave getChave() {
        return chave;
    }

    public void setChave(Chave chave) {
        this.chave = chave;
    }

    public Regulador getRegulador() {
        return regulador;
    }

    public void setRegulador(Regulador regulador) {
        this.regulador = regulador;
    }
}
