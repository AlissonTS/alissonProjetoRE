package br.ufsm.ceesp.ceee.model;

import br.com.mega.silas.algoritmos.IMarcacaoMapa;
import br.com.mega.silas.algoritmos.InformacoesSilas;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;

/**
 * Created by Mega on 31/07/14.
 */

@InformacoesSilas(nomeTipoPropriedade = "OUTTrechoRede")
public class TrechoRede implements INavegavel,Serializable {

    private Long id;
    private String codigoAlfanumerico;
    private Map<String, MedidaEletrica> medidasEletricas = new LinkedHashMap<String, MedidaEletrica>();

    class ComparatorMedidas implements Comparator<MedidaEletrica>, Serializable {
        @Override
        public int compare(MedidaEletrica o1, MedidaEletrica o2) {
            MedidaEletrica.TipoMedida t1 = MedidaEletrica.TipoMedida.mapValues.get(o1.getNome());
            MedidaEletrica.TipoMedida t2 = MedidaEletrica.TipoMedida.mapValues.get(o2.getNome());
            return t1.compareTo(t2);
        }
    }

    private Collection<MedidaEletrica> medidasEletricasExibir = new TreeSet<MedidaEletrica>(new ComparatorMedidas());
    private IMarcacaoMapa marcacaoMapa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTCodigoString")
    public String getCodigoAlfanumerico() {
        return codigoAlfanumerico;
    }

    public void setCodigoAlfanumerico(String codigoAlfanumerico) {
        this.codigoAlfanumerico = codigoAlfanumerico;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTmedidasEletricas", campoIndiceMapa = "OUTnome")
    public Collection<MedidaEletrica> getMedidasEletricasExibir() {
        return medidasEletricasExibir;
    }

    public void setMedidasEletricasExibir(Collection<MedidaEletrica> medidasEletricasExibir) {
        if (medidasEletricasExibir != null) {
            for (MedidaEletrica m : medidasEletricasExibir) {
                if (MedidaEletrica.isExibir(m, TrechoRede.class)) {
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
        if (MedidaEletrica.isExibir(medidaEletrica, TrechoRede.class)) {
            medidasEletricasExibir.remove(medidaEletrica);
            medidasEletricasExibir.add(medidaEletrica);
        }
        if (getChave() != null && MedidaConfiabilidade.isMedidaConfiabilidade(medidaEletrica)) {
            MedidaConfiabilidade mConf = new MedidaConfiabilidade();
            mConf.setNome(medidaEletrica.getNome());
            mConf.setUnidade(medidaEletrica.getUnidade());
            mConf.setValor(medidaEletrica.getValorA());
            getChave().getMedidasConfiabilidade().put(mConf.getNome(), mConf);
        }
        return medidasEletricas.put(nomeMedida, medidaEletrica);
    }


    public MedidaEletrica removerMedidaEletrica(String medidaEletrica) {
        MedidaEletrica med = medidasEletricas.remove(medidaEletrica);
        if (med != null && MedidaEletrica.isExibir(med, TrechoRede.class)) {
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


    //Relações
    private Barra barraFonte;
    private Barra barraAlvo;
    private Chave chave;
    private Regulador regulador;
    private Alimentador alimentador;
    //Campos
    private Long multiplicidadeCondutor;
    private String tipoTrecho;
    private String fases;
    private Double comprimento;
    private String nomeCaboFase = "";
    private String nomeCaboNeutro = "";
    private PropriedadesCondutor propriedadesCondutorFase = null;
    private boolean marcadoTronco = false;
    private Integer anoSimulado;
    private Integer patamarSimulado;
    private Condutor condutor;
    private transient CapacitorSerie capacitorSerie;
    private transient ElevadorRebaixador elevadorRebaixador;
    private transient Double tensaoNominal;

    public Double getTensaoNominal() {
        return tensaoNominal;
    }

    public void setTensaoNominal(Double tensaoNominal) {
        this.tensaoNominal = tensaoNominal;
    }

    public ElevadorRebaixador getElevadorRebaixador() {
        return elevadorRebaixador;
    }

    public void setElevadorRebaixador(ElevadorRebaixador elevadorRebaixador) {
        this.elevadorRebaixador = elevadorRebaixador;
    }

    public CapacitorSerie getCapacitorSerie() {
        return capacitorSerie;
    }

    public void setCapacitorSerie(CapacitorSerie capacitorSerie) {
        this.capacitorSerie = capacitorSerie;
    }

    private transient Condutor condutorOriginal;

    public void restabeleceCondutorOriginal() {
        if (condutorOriginal != null) {
            this.setCondutor(condutorOriginal);
        }
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTCaboCondutor")
    public Condutor getCondutor() {
        return condutor;
    }

    public void recondutora(Condutor condutor) {
        this.condutorOriginal = this.condutor;
        this.setCondutor(condutor);
    }

    public void setCondutor(Condutor condutor) {
        if (condutor != null) {
            try {
                PropriedadesCondutor prop = new PropriedadesCondutor(condutor.getNome(),
                        condutor.getR1(),
                        condutor.getX1(),
                        condutor.getR0(),
                        condutor.getX0(),
                        condutor.getCapacidade());
                this.propriedadesCondutorFase = prop;
            } catch (Exception e) {
                //e.printStackTrace();
                setNomeCaboFase(condutor.getNome());
            }
        }
        this.condutor = condutor;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTano")
    public Integer getAnoSimulado() {
        return anoSimulado;
    }

    public void setAnoSimulado(Integer anoSimulado) {
        this.anoSimulado = anoSimulado;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTPatamarSimulado")
    public Integer getPatamarSimulado() {
        return patamarSimulado;
    }

    public void setPatamarSimulado(Integer patamarSimulado) {
        this.patamarSimulado = patamarSimulado;
    }

    public boolean isMarcadoTronco() {
        return marcadoTronco;
    }

    public void setMarcadoTronco(boolean marcadoTronco) {
        this.marcadoTronco = marcadoTronco;
    }

    @InformacoesSilas(campoEspelho = true, nomeTipoPropriedade = "OUTBarraFonte")
    public Barra getBarraFonte() {
        return barraFonte;
    }

    public void setBarraFonte(Barra barraFonte) {
        this.barraFonte = barraFonte;
    }

    @InformacoesSilas(campoEspelho = true, nomeTipoPropriedade = "OUTBarraAlvo")
    public Barra getBarraAlvo() {
        return barraAlvo;
    }

    public void setBarraAlvo(Barra barraAlvo) {
        this.barraAlvo = barraAlvo;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTSeccionador")
    public Chave getChave() {
        return chave;
    }

    public void setChave(Chave chave) {
        this.chave = chave;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTRegulador")
    public Regulador getRegulador() {
        return regulador;
    }

    public void setRegulador(Regulador regulador) {
        this.regulador = regulador;
    }

    @InformacoesSilas(nomeTipoPropriedade = "PSMalimentador")
    public Alimentador getAlimentador() {
        return alimentador;
    }

    public void setAlimentador(Alimentador alimentador) {
        this.alimentador = alimentador;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTMultiplicidade")
    public Long getMultiplicidadeCondutor() {
        return multiplicidadeCondutor;
    }

    public void setMultiplicidadeCondutor(Long multiplicidadeCondutor) {
        this.multiplicidadeCondutor = multiplicidadeCondutor;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTTipoTrecho")
    public String getTipoTrecho() {
        return tipoTrecho;
    }

    public void setTipoTrecho(String tipoTrecho) {
        this.tipoTrecho = tipoTrecho;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTfases")
    public String getFases() {
        return fases;
    }

    public void setFases(String fases) {
        this.fases = fases;
    }

    @InformacoesSilas
    public Double getComprimento() {
        return comprimento;
    }

    public Double getComprimentoKm() {
        return getComprimento() == null ? 0.0 : getComprimento() / 1000.0;
    }

    public void setComprimento(Double comprimento) {
        this.comprimento = comprimento;
    }

    //@InformacoesSilas(nomeTipoPropriedade = "OUTCaboFase")
    public String getNomeCaboFase() {
        return nomeCaboFase;
    }

    public void setNomeCaboFase(String nomeCaboFase) {
        nomeCaboFase = nomeCaboFase.trim();
        if (nomeCaboFase != null && (!nomeCaboFase.equals(this.nomeCaboFase) || propriedadesCondutorFase == null)) {
            PropriedadesCondutor propriedadesCondutor = PropriedadesCondutor.getPropriedadesCondutor(nomeCaboFase);
            if (propriedadesCondutor == null) {
                nomeCaboFase = nomeCaboFase.replace(",", ".");
                propriedadesCondutor = PropriedadesCondutor.getPropriedadesCondutor(nomeCaboFase);
                if (propriedadesCondutor == null) {
                    System.err.println("Condutor não encontrado na tabela: " + nomeCaboFase);
                    setPropriedadesCondutorFase(null);
                } else {
                    setPropriedadesCondutorFase(propriedadesCondutor);
                }
            } else {
                setPropriedadesCondutorFase(propriedadesCondutor);
            }
        }
        this.nomeCaboFase = nomeCaboFase;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTCaboNeutro")
    public String getNomeCaboNeutro() {
        return nomeCaboNeutro;
    }

    public void setNomeCaboNeutro(String nomeCaboNeutro) {
        this.nomeCaboNeutro = nomeCaboNeutro;
    }

    public PropriedadesCondutor getPropriedadesCondutorFase() {
        return propriedadesCondutorFase;
    }

    public void setPropriedadesCondutorFase(PropriedadesCondutor propriedadesCondutorFase) {
        this.propriedadesCondutorFase = propriedadesCondutorFase;
    }

    public void desfazRecondutoramento() {
        if (condutorOriginal != null) {
            this.propriedadesCondutorFase = null;
            setCondutor(condutorOriginal);
            this.obraNova = false;
        }
    }

    private String fasesOriginal;
    private boolean obraNova = false;

    public boolean isObraNova() {
        return obraNova;
    }

    public void setObraNova(boolean obraNova) {
        this.obraNova = obraNova;
    }

    public void complementaFases() {
        this.fasesOriginal = getFases();
        this.obraNova = true;
        setFases("DEF");
    }

    public void restabeleceFasesOriginais() {
        this.obraNova = false;
        if (fasesOriginal != null) {
            setFases(this.fasesOriginal);
            setNomeCaboFase(getNomeCaboFase());
        }
    }



    // ---------------------


    public boolean isCondutor() {
        return getPropriedadesCondutorFase() == null ? false : getPropriedadesCondutorFase().getCapacidade() > 0;
    }

    public Double getR1() {
        return getPropriedadesCondutorFase() == null ? null : getPropriedadesCondutorFase().getR1();
    }

    public Double getR0() {
        return getPropriedadesCondutorFase() == null ? null : getPropriedadesCondutorFase().getR0();
    }

    public Double getX1() {
        return getPropriedadesCondutorFase() == null ? null : getPropriedadesCondutorFase().getX1();
    }

    public Double getX0() {
        return getPropriedadesCondutorFase() == null ? null : getPropriedadesCondutorFase().getX0();
    }

    public Integer getCapacidade() {
        return getPropriedadesCondutorFase() == null ? null : getPropriedadesCondutorFase().getCapacidade();
    }

    public String getIdentificacaoAlimentador() {
        return getAlimentador() == null ? "" : getAlimentador().getId().toString();
    }

    public Collection<INavegavel> getDestinos(INavegavel origem) {
        Collection<INavegavel> dest = new HashSet<INavegavel>();
        if (getChave() != null && getChave().getEstadoAberturaAtual().equals(Chave.ESTADO_ABERTA)) {
            return dest;
        }
        if (origem == null) {
            dest.add(this.barraAlvo);
            return dest;
        }
        if (this.barraFonte != null && !this.barraFonte.equals(origem)) {
            dest.add(this.barraFonte);
        } else if (this.barraAlvo != null && !this.barraAlvo.equals(origem)) {
            dest.add(this.barraAlvo);
        }
        return dest;
    }

    public Collection<INavegavel> getDestinos() {
        return getDestinos(null);
    }

    public void setDestinos(Collection<INavegavel> destinos) {
        this.barraAlvo = (Barra) destinos.iterator().next();
    }

    public INavegavel getOrigem() {
        return this.barraFonte;
    }

    public void setOrigem(INavegavel origem) {
        this.barraFonte = (Barra) origem;
    }

    private boolean foiVisitadoParaArrumar;

    public boolean getFoiVisitadoParaArrumar() {
        return foiVisitadoParaArrumar;
    }

    public void setFoiVisitadoParaArrumar(boolean foiVisitadoParaArrumar) {
        this.foiVisitadoParaArrumar = foiVisitadoParaArrumar;
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

    private Long codigo;

    @InformacoesSilas(nomeTipoPropriedade = "OUTcodigo")
    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("TrechoRede ").append(getCondutor().getNome());
        sb.append(" ").append(codigoAlfanumerico).append(" (").append(getAlimentador().getNome()).append(")");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TrechoRede)) return false;
        return getId().equals(((TrechoRede) o).getId());
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    private static long CODIGOS_FALSOS = -1;
    public static TrechoRede getTrechoFalso() {
        TrechoRede t = new TrechoRede();
        t.setId(CODIGOS_FALSOS--);
        Condutor c = new Condutor();
        c.setNome("");
        c.setR0(0.0);
        c.setR1(0.0);
        c.setX0(0.0);
        c.setX1(0.0);
        t.setCondutor(c);
        t.getCondutor().setCapacidade(Integer.MAX_VALUE);
        t.setComprimento(0.0);
        return t;
    }

    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        System.out.println("Gravando Trecho de Rede");
        out.defaultWriteObject();
    }

    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        System.out.println("Lendo Trecho de Rede");
        in.defaultReadObject();
    }
}
