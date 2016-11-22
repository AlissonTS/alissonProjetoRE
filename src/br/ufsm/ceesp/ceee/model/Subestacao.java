package br.ufsm.ceesp.ceee.model;

import br.com.mega.silas.algoritmos.IMarcacaoMapa;
import br.com.mega.silas.algoritmos.InformacoesSilas;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * Created by Mega on 31/07/14.
 */

@InformacoesSilas(nomeTipoPropriedade = "PSMsubestacao")
public class Subestacao implements Serializable {

    private Long id;
    private Map<String, MedidaEletrica> medidasEletricas = new LinkedHashMap<String, MedidaEletrica>();
    private IMarcacaoMapa marcacaoMapa;

    private String nome;
    private String matricula;
    private Collection<TransformadorSE> transformadoresSE = new LinkedHashSet<TransformadorSE>();
    private Map<String, Alimentador> alimentadores = new LinkedHashMap<String, Alimentador>();

    private Collection<ChaveSocorro> chaveSocorros = new LinkedHashSet<ChaveSocorro>();

    @InformacoesSilas(nomeTipoPropriedade = "OUTChaveSocorro", valorDependende = true)
    public Collection<ChaveSocorro> getChaveSocorros() {
        return chaveSocorros;
    }

    public void setChaveSocorros(Collection<ChaveSocorro> chaveSocorros) {
        this.chaveSocorros = chaveSocorros;
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

    @InformacoesSilas
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @InformacoesSilas
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    @InformacoesSilas(valorDependende = true, nomeTipoPropriedade = "OUTTransformadorSE")
    public Collection<TransformadorSE> getTransformadoresSE() {
        return transformadoresSE;
    }

    public void setTransformadoresSE(Collection<TransformadorSE> transformadoresSE) {
        this.transformadoresSE = transformadoresSE;
     /*   alimentadores.clear();
        for (TransformadorSE transformadorSE : transformadoresSE) {
            for (Alimentador alimentador: transformadorSE.getAlimentadores().values()) {
                alimentadores.put(alimentador.getId().toString(), alimentador);
            }
        }*/
    }

    @InformacoesSilas(valorDependende = true, nomeTipoPropriedade = "PSMalimentador", traduzir = false)
    public Map<String, Alimentador> getAlimentadores() {
        return this.alimentadores;
    }

    public void setAlimentadores(Map<String, Alimentador> alimentadores) {
        this.alimentadores = alimentadores;
    }
}
