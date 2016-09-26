package br.ufsm.ceesp.ceee.model;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by politecnico on 22/09/2016.
 */
public class Alimentador {

    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    private Map<Barra.ParCoordenadas, Barra> barras = new HashMap<>();
    private Map<Long, TrechoRede> trechos = new TreeMap<>();
    private Map<Long, Transformador> transformador = new TreeMap<>();

    public Map<Barra.ParCoordenadas, Barra> getBarras() {
        return barras;
    }

    public void setBarras(Map<Barra.ParCoordenadas, Barra> barras) {
        this.barras = barras;
    }

    public Map<Long, TrechoRede> getTrechos() {
        return trechos;
    }

    public void setTrechos(Map<Long, TrechoRede> trechos) {
        this.trechos = trechos;
    }

    public Map<Long, Transformador> getTransformador() {
        return transformador;
    }

    public void setTransformador(Map<Long, Transformador> transformador) {
        this.transformador = transformador;
    }
}
