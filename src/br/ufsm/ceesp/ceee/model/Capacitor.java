package br.ufsm.ceesp.ceee.model;

import br.com.mega.silas.algoritmos.IMarcacaoMapa;
import br.com.mega.silas.algoritmos.InformacoesSilas;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Mega on 31/07/14.
 */

@InformacoesSilas(nomeTipoPropriedade = "OUTCapacitor")
public class Capacitor implements Serializable {

    private Long id;
    private Map<String, MedidaEletrica> medidasEletricas = new LinkedHashMap<String, MedidaEletrica>();
    private IMarcacaoMapa marcacaoMapa;
    private String codigoAlfanumerico;

    @InformacoesSilas(nomeTipoPropriedade = "OUTCodigoString")
    public String getCodigoAlfanumerico() {
        return codigoAlfanumerico;
    }

    public void setCodigoAlfanumerico(String codigoAlfanumerico) {
        this.codigoAlfanumerico = codigoAlfanumerico;
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

    private Boolean operando;
    private Double tensaoNominalPrimaria;
    private Double potenciaReativaNominalPatamar1;
    private Double potenciaReativaNominalPatamar2;
    private Double potenciaReativaNominalPatamar3;
    private Double potenciaReativaNominalPatamar4;
    private Barra barraBkp;
    private Barra barra;
    private Integer anoSimulado;
    private Integer patamarSimulado;

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

    @InformacoesSilas
    public Boolean getOperando() {
        return operando;
    }

    public void setOperando(Boolean operando) {
        this.operando = operando;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTtensaoPrimariaNominal")
    public Double getTensaoNominalPrimaria() {
        return tensaoNominalPrimaria;
    }

    public void setTensaoNominalPrimaria(Double tensaoNominalPrimaria) {
        this.tensaoNominalPrimaria = tensaoNominalPrimaria;
    }

    @InformacoesSilas
    public Double getPotenciaReativaNominalPatamar1() {
        return potenciaReativaNominalPatamar1;
    }

    public void setPotenciaReativaNominalPatamar1(Double potenciaReativaNominalPatamar1) {
        this.potenciaReativaNominalPatamar1 = potenciaReativaNominalPatamar1;
    }

    @InformacoesSilas
    public Double getPotenciaReativaNominalPatamar2() {
        return potenciaReativaNominalPatamar2;
    }

    public void setPotenciaReativaNominalPatamar2(Double potenciaReativaNominalPatamar2) {
        this.potenciaReativaNominalPatamar2 = potenciaReativaNominalPatamar2;
    }

    @InformacoesSilas
    public Double getPotenciaReativaNominalPatamar3() {
        return potenciaReativaNominalPatamar3;
    }

    public void setPotenciaReativaNominalPatamar3(Double potenciaReativaNominalPatamar3) {
        this.potenciaReativaNominalPatamar3 = potenciaReativaNominalPatamar3;
    }

    @InformacoesSilas
    public Double getPotenciaReativaNominalPatamar4() {
        return potenciaReativaNominalPatamar4;
    }

    public void setPotenciaReativaNominalPatamar4(Double potenciaReativaNominalPatamar4) {
        this.potenciaReativaNominalPatamar4 = potenciaReativaNominalPatamar4;
    }

    public Double getPotenciaReativa(int patamar) {
        switch (patamar) {
            case 1:
                return getPotenciaReativaNominalPatamar1();
            case 2:
                return getPotenciaReativaNominalPatamar2();
            case 3:
                return getPotenciaReativaNominalPatamar3();
            case 4:
                return getPotenciaReativaNominalPatamar4();
            default:
                return getPotenciaReativaNominalPatamar1();
        }
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTBarra")
    public Barra getBarra() {
        return barra;
    }

    public void setBarra(Barra barra) {
        this.barra = barra;
    }

    public Barra getBarraBkp() {
        return barraBkp;
    }

    public void setBarraBkp(Barra barraBkp) {
        this.barraBkp = barraBkp;
    }

    public INavegavel getINavegavelAssociado() {
        return getBarra();
    }

    public IMarcacaoMapa novaMarcacaoMapa(IMarcacaoMapa novaMarcacaoMapa) {
        novaMarcacaoMapa.setTitulo("Novo Capacitor");
        novaMarcacaoMapa.setTipoMarcacao(IMarcacaoMapa.TIPO_APRESENTACAO_PONTO);
        for (INavegavel n : getBarra().getDestinos()) {
            TrechoRede trechoRede = (TrechoRede) n;
            if (trechoRede.getBarraFonte() == getBarra()) {
                IMarcacaoMapa m = trechoRede.getMarcacaoMapa().getMarcacoesLinha().get(0);
                novaMarcacaoMapa.setLatitude(m.getLatitude());
                novaMarcacaoMapa.setLongitude(m.getLongitude());
                break;
            } else if (trechoRede.getBarraAlvo() == getBarra()) {
                IMarcacaoMapa m = trechoRede.getMarcacaoMapa().getMarcacoesLinha().get(1);
                novaMarcacaoMapa.setLatitude(m.getLatitude());
                novaMarcacaoMapa.setLongitude(m.getLongitude());
                break;
            }
        }
        novaMarcacaoMapa.setNomeIcone("carregar-arquivo.htm?id=456&abrir=true");
        return novaMarcacaoMapa;
    }

}
