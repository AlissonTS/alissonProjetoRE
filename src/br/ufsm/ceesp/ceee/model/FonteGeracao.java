package br.ufsm.ceesp.ceee.model;

import br.com.mega.silas.algoritmos.IMarcacaoMapa;
import br.com.mega.silas.algoritmos.InformacoesSilas;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Rafael
 * Date: 07/08/14
 * Time: 14:42
 * To change this template use File | Settings | File Templates.
 */
@InformacoesSilas(nomeTipoPropriedade = "OUTFonteGeracao")
public class FonteGeracao implements Serializable {

    private Long id;
    private Double potenciaAtiva;
    private Double potenciaReativa = 0.0;
    private IMarcacaoMapa marcacaoMapa;
    private Barra barra;

    @InformacoesSilas(nomeTipoPropriedade = "OUTBarra")
    public Barra getBarra() {
        return barra;
    }

    public void setBarra(Barra barra) {
        this.barra = barra;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTPotencia")
    public Double getPotenciaAtiva() {
        return potenciaAtiva;
    }

    public void setPotenciaAtiva(Double potenciaAtiva) {
        this.potenciaAtiva = potenciaAtiva;
    }

    public Double getPotenciaReativa() {
        return potenciaReativa;
    }

    public void setPotenciaReativa(Double potenciaReativa) {
        this.potenciaReativa = potenciaReativa;
    }

    public IMarcacaoMapa getMarcacaoMapa() {
        return marcacaoMapa;
    }

    public void setMarcacaoMapa(IMarcacaoMapa marcacaoMapa) {
        this.marcacaoMapa = marcacaoMapa;
    }

    public INavegavel getINavegavelAssociado() {
        return getBarra();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public IMarcacaoMapa novaMarcacaoMapa(IMarcacaoMapa novaMarcacaoMapa) {
        novaMarcacaoMapa.setTitulo("Nova Geração Distribuída");
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
        novaMarcacaoMapa.setNomeIcone("carregar-arquivo.htm?id=666&abrir=true");
        return novaMarcacaoMapa;
    }

}
