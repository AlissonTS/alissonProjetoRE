package br.ufsm.ceesp.ceee.model;

import br.com.mega.silas.algoritmos.IMarcacaoMapa;
import br.com.mega.silas.algoritmos.InformacoesSilas;

import java.io.Serializable;
import java.util.*;

/**
 * Created by Mega on 31/07/14.
 */

@InformacoesSilas(nomeTipoPropriedade = "OUTChaveSocorro")
public class ChaveSocorro implements INavegavel, Serializable, Seccionalizador {

    public static final Integer ESTADO_ABERTA = 0;
    public static final Integer ESTADO_FECHADA = 1;

    private Long id;
    private Long codigo;
    private Map<String, MedidaEletrica> medidasEletricas = new LinkedHashMap<String, MedidaEletrica>();
    private IMarcacaoMapa marcacaoMapa;
    private Integer anoSimulado;
    private Integer patamarSimulado;
    private Integer estadoAberturaAtual = ESTADO_ABERTA;
    private Map<Alimentador, Stack<Chave>> chavesReconfigAlimentador = new HashMap<Alimentador, Stack<Chave>>();
    private String codigAlfanumerico;
    private Chave chave;

    @InformacoesSilas(nomeTipoPropriedade = "OUTSeccionador")
    public Chave getChave() {
        return chave;
    }

    public void setChave(Chave chave) {
        this.chave = chave;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTCodigoString")
    public String getCodigAlfanumerico() {
        return codigAlfanumerico;
    }

    public void setCodigAlfanumerico(String codigAlfanumerico) {
        this.codigAlfanumerico = codigAlfanumerico;
    }

    public Map<Alimentador, Stack<Chave>> getChavesReconfigAlimentador() {
        return chavesReconfigAlimentador;
    }

    public void setChavesReconfigAlimentador(Map<Alimentador, Stack<Chave>> chavesReconfigAlimentador) {
        this.chavesReconfigAlimentador = chavesReconfigAlimentador;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTcodigo")
    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTEstadoAberturaAtualInteiro")
    public Integer getEstadoAberturaAtual() {
        return estadoAberturaAtual;
    }

    public void setEstadoAberturaAtual(Integer estadoAberturaAtual) {
        this.estadoAberturaAtual = estadoAberturaAtual;
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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChaveSocorro)) return false;

        ChaveSocorro that = (ChaveSocorro) o;

        return !(getId() != null ? !getId().equals(that.getId()) : that.getId() != null);

    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }

    @Override
    public Collection<INavegavel> getDestinos(INavegavel origem) {
        Collection<INavegavel> col = new HashSet<INavegavel>();
        if (getEstadoAberturaAtual().equals(ESTADO_ABERTA)) return col;
        if (getBarraInicial() != null && getBarraFinal() != null) {
            if (getBarraInicial().equals(origem)) {
                col.add(getBarraFinal());
            } else if (getBarraFinal().equals(origem)) {
                col.add(getBarraInicial());
            }
        }
        return col;
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
    public Collection<INavegavel> getDestinos() {
        Collection<INavegavel> col = new ArrayList<INavegavel>();
        if (getEstadoAberturaAtual().equals(ESTADO_ABERTA)) return col;
        if (getBarraFinal() != null) {
            col.add(getBarraFinal());
            return col;
        }
        if (getBarraInicial() != null) {
            col.add(getBarraInicial());
            return col;
        }
        return col;
    }

    public boolean isTelecomandavel() {
        return telecomando != null && telecomando;
    }


    @Override
    public void setDestinos(Collection<INavegavel> destinos) {

    }

    @Override
    public INavegavel getOrigem() {
        return getBarraInicial();
    }

    @Override
    public void setOrigem(INavegavel origem) {

    }

    @Override
    public String getIdentificacaoAlimentador() {
        if (getBarraInicial() != null) {
            return getBarraInicial().getIdentificacaoAlimentador();
        }
        if (getBarraFinal() != null) {
            return getBarraFinal().getIdentificacaoAlimentador();
        }
        return null;
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

    @Override
    public MedidaEletrica removerMedidaEletrica(String medidaEletrica) {
        return null;
    }


    public MedidaEletrica removerMedidaEletrica(MedidaEletrica medidaEletrica) {
        return medidasEletricas.remove(medidaEletrica);
    }


    public MedidaEletrica getMedidaEletrica(String nomeMedida) {
        return medidasEletricas.get(nomeMedida);
    }

    @Override
    public Alimentador getAlimentador() {
        if (getBarraInicial() != null) return getBarraInicial().getAlimentador();
        return getBarraFinal().getAlimentador();
    }

    public Alimentador getOutroAlimentador(Alimentador alimentador) {
        if (alimentador.equals(barraInicial.getAlimentador())) return barraFinal.getAlimentador();
        return barraInicial.getAlimentador();
    }

    @Override
    public boolean getNavegado() {
        return false;
    }

    public IMarcacaoMapa getMarcacaoMapa() {
        return marcacaoMapa;
    }

    public void setMarcacaoMapa(IMarcacaoMapa marcacaoMapa) {
        this.marcacaoMapa = marcacaoMapa;
    }

    public Alimentador alimentadorInicial;
    private Alimentador alimentadorFinal;
    private Barra barraInicial;
    private Barra barraFinal;
    private Subestacao subestacao;
    //Campos
    private String nomeDispositivoProtecao;
    private Double correnteNomininalDispositivoProtecao;
    private Boolean telecomando;
    private String modoOperacao;

    public Alimentador getAlimentadorInicial() {
        return alimentadorInicial;
    }

    public void setAlimentadorInicial(Alimentador alimentadorInicial) {
        this.alimentadorInicial = alimentadorInicial;
    }

    public Alimentador getAlimentadorFinal() {
        return alimentadorFinal;
    }

    public void setAlimentadorFinal(Alimentador alimentadorFinal) {
        this.alimentadorFinal = alimentadorFinal;
    }

    @InformacoesSilas(campoEspelho = true, nomeTipoPropriedade = "OUTBarraFonte", traduzir = false)
    public Barra getBarraInicial() {
        return barraInicial;
    }

    public void setBarraInicial(Barra barraInicial) {
        this.barraInicial = barraInicial;
    }

    @InformacoesSilas(campoEspelho = true, nomeTipoPropriedade = "OUTBarraAlvo", traduzir = false)
    public Barra getBarraFinal() {
        return barraFinal;
    }

    public void setBarraFinal(Barra barraFinal) {
        this.barraFinal = barraFinal;
    }

    @InformacoesSilas(nomeTipoPropriedade = "PSMsubestacao")
    public Subestacao getSubestacao() {
        return subestacao;
    }

    public void setSubestacao(Subestacao subestacao) {
        this.subestacao = subestacao;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTTipoDispositivoProtecao")
    public String getNomeDispositivoProtecao() {
        return nomeDispositivoProtecao;
    }

    public void setNomeDispositivoProtecao(String nomeDispositivoProtecao) {
        this.nomeDispositivoProtecao = nomeDispositivoProtecao;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTCorrenteNominalProtecao")
    public Double getCorrenteNomininalDispositivoProtecao() {
        return correnteNomininalDispositivoProtecao;
    }

    public void setCorrenteNomininalDispositivoProtecao(Double correnteNomininalDispositivoProtecao) {
        this.correnteNomininalDispositivoProtecao = correnteNomininalDispositivoProtecao;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTTelecomando")
    public Boolean getTelecomando() {
        return telecomando;
    }

    public void setTelecomando(Boolean telecomando) {
        this.telecomando = telecomando;
    }

    @Override
    public String toString() {
        if (getCodigAlfanumerico() != null) {
            return getCodigAlfanumerico();
        }
        return "Chave Socorro";
    }

    public String getModoOperacao() {
        return modoOperacao;
    }

    public void setModoOperacao(String modoOperacao) {
        this.modoOperacao = modoOperacao;
    }

    public Double getTensaoNominal() {
        return getBarraInicial().getTensaoNominal();
    }

    private transient ParAlimentadores parAlimentadores;

    public synchronized ParAlimentadores getParAlimentadores() {
        if (parAlimentadores == null) {
            parAlimentadores = new ParAlimentadores(getBarraInicial().getAlimentador(), getBarraFinal().getAlimentador());
        }
        return parAlimentadores;
    }

    public static class ParAlimentadores {
        private Alimentador al1;
        private Alimentador al2;

        public ParAlimentadores(Alimentador al1, Alimentador al2) {
            this.al1 = al1;
            this.al2 = al2;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ParAlimentadores that = (ParAlimentadores) o;

            if (al1.equals(that.al1) && al2.equals(that.al2)) return true;
            return al1.equals(that.al2) && al2.equals(that.al1);

        }

        @Override
        public int hashCode() {
            int result = al1.hashCode();
            result = 31 * (result + al2.hashCode());
            return result;
        }

        public String toString() {
            return al1.getNome() + "-" + al2.getNome();
        }
    }
}
