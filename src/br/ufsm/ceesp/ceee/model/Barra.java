package br.ufsm.ceesp.ceee.model;

import br.com.mega.silas.algoritmos.IMarcacaoMapa;
import br.com.mega.silas.algoritmos.InformacoesSilas;

import java.io.Serializable;
import java.util.*;

/**
 * Created by Mega on 31/07/14.
 */

@InformacoesSilas(nomeTipoPropriedade = "OUTBarra")
public class Barra implements INavegavel, Serializable {

    class ComparatorMedidas implements Comparator<MedidaEletrica>, Serializable {
        @Override
        public int compare(MedidaEletrica o1, MedidaEletrica o2) {
            MedidaEletrica.TipoMedida t1 = MedidaEletrica.TipoMedida.mapValues.get(o1.getNome());
            MedidaEletrica.TipoMedida t2 = MedidaEletrica.TipoMedida.mapValues.get(o2.getNome());
            return t1.compareTo(t2);
        }
    }

    private Long id;
    private Collection<MedidaEletrica> medidasEletricasExibir = new TreeSet<MedidaEletrica>(new ComparatorMedidas());
    private Map<String, MedidaEletrica> medidasEletricas = new LinkedHashMap<String, MedidaEletrica>();
    private IMarcacaoMapa marcacaoMapa;
    private transient Double tensaoNominal;

    public Double getTensaoNominal() {
        return tensaoNominal;
    }

    public void setTensaoNominal(Double tensaoNominal) {
        this.tensaoNominal = tensaoNominal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTmedidasEletricas", campoIndiceMapa = "OUTnome")
    public Collection<MedidaEletrica> getMedidasEletricasExibir() {
        return medidasEletricasExibir;
    }

    public void setMedidasEletricasExibir(Collection<MedidaEletrica> medidasEletricasExibir) {
        if (medidasEletricasExibir != null) {
            for (MedidaEletrica m : medidasEletricasExibir) {
                if (MedidaEletrica.isExibir(m, Barra.class)) {
                    this.medidasEletricasExibir.add(m);
                }
            }
        } else {
            this.medidasEletricasExibir.clear();
        }
    }


    public Map<String, MedidaEletrica> getMedidasEletricas() {
        return medidasEletricas;
    }


    public void setMedidasEletricas(Map<String, MedidaEletrica> medidasEletricas) {
        this.medidasEletricas = medidasEletricas;
    }


    public MedidaEletrica adicionaMedidaEletrica(String nomeMedida, MedidaEletrica medidaEletrica) {
        if (MedidaEletrica.isExibir(medidaEletrica, Barra.class)) {
            medidasEletricasExibir.remove(medidaEletrica);
            medidasEletricasExibir.add(medidaEletrica);
        }
        if (getTransformador() != null && MedidaEletrica.isExibir(medidaEletrica, Transformador.class)) {
            getTransformador().getMedidasEletricas().remove(medidaEletrica);
            getTransformador().getMedidasEletricas().add(medidaEletrica);
        }
        return medidasEletricas.put(nomeMedida, medidaEletrica);
    }


    public MedidaEletrica removerMedidaEletrica(String medidaEletrica) {
        MedidaEletrica med = medidasEletricas.remove(medidaEletrica);
        if (med != null && MedidaEletrica.isExibir(med, Barra.class)) {
            medidasEletricasExibir.remove(med);
        }
        return med;
    }


    public MedidaEletrica getMedidaEletrica(String nomeMedida) {
        return medidasEletricas.get(nomeMedida) != null ? medidasEletricas.get(nomeMedida) : findMedidaEletricaExibir(nomeMedida);
    }

    public MedidaEletrica findMedidaEletricaExibir(String nome) {
        for (MedidaEletrica m : medidasEletricasExibir) {
            if (m.getNome().equals(nome)) return m;
        }
        return null;
    }

    public IMarcacaoMapa getMarcacaoMapa() {
        return marcacaoMapa;
    }

    public void setMarcacaoMapa(IMarcacaoMapa marcacaoMapa) {
        this.marcacaoMapa = marcacaoMapa;
    }

    private Double x;
    private Double y;
    private Capacitor capacitor;
    private Transformador transformador;
    private Alimentador alimentador;
    private FonteGeracao fonteGeracao;
    private ChaveSocorro chaveSocorro;

    public ChaveSocorro getChaveSocorro() {
        return chaveSocorro;
    }

    public void setChaveSocorro(ChaveSocorro chaveSocorro) {
        this.chaveSocorro = chaveSocorro;
    }

    private Collection<INavegavel> destinos = new LinkedHashSet<INavegavel>();
    private INavegavel origem;

    private Long codigo;

    @InformacoesSilas(nomeTipoPropriedade = "OUTcodigo")
    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    @InformacoesSilas
    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    @InformacoesSilas
    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTCapacitor")
    public Capacitor getCapacitor() {
        return capacitor;
    }

    public void setCapacitor(Capacitor capacitor) {
        this.capacitor = capacitor;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTTransformador")
    public Transformador getTransformador() {
        return transformador;
    }

    public void setTransformador(Transformador transformador) {
        this.transformador = transformador;
    }

    @InformacoesSilas(nomeTipoPropriedade = "PSMalimentador")
    public Alimentador getAlimentador() {
        return alimentador;
    }

    public void setAlimentador(Alimentador alimentador) {
        this.alimentador = alimentador;
    }


    public Collection<INavegavel> getDestinos(INavegavel origem) {
        Collection<INavegavel> dest = new HashSet<INavegavel>(destinos);
        if (getChaveSocorro() != null && !(origem instanceof ChaveSocorro)) {
            dest.add(chaveSocorro);
        }
        if (origem != null) {
            dest.remove(origem);
        }
        return dest;
    }


    public Collection<INavegavel> getDestinos() {
        return destinos;
    }


    public void setDestinos(Collection<INavegavel> destinos) {
        this.destinos = destinos;
    }


    public INavegavel getOrigem() {
        return origem;
    }


    public void setOrigem(INavegavel origem) {
        this.origem = origem;
    }


    public String getIdentificacaoAlimentador() {
        return getAlimentador() != null ? getAlimentador().getId().toString() : null;
    }

    public FonteGeracao getFonteGeracao() {
        return fonteGeracao;
    }

    public void setFonteGeracao(FonteGeracao fonteGeracao) {
        this.fonteGeracao = fonteGeracao;
    }


    public boolean getNavegado() {
        return getOrigem() != null;
    }

    private boolean marcadoCiclo = false;

    @Override
    public void setMarcadoCiclo() {
        this.marcadoCiclo = true;
    }

    @Override
    public boolean isMarcadoCiclo() {
        return this.marcadoCiclo;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Barra ");
        sb.append(codigo).append(" (Alimentador ").append(getAlimentador().getNome()).append(")");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Barra)) return false;
        return getId().equals(((Barra) o).getId());
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
