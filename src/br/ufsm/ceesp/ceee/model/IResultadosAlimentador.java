package br.ufsm.ceesp.ceee.model;

import br.com.mega.silas.algoritmos.InformacoesSilas;

import java.util.Date;

/**
 * Created by politecnico on 20/07/2015.
 */
public interface IResultadosAlimentador {
    Date getDataDiagnostico();

    void setDataDiagnostico(Date dataDiagnostico);

    @InformacoesSilas(nomeTipoPropriedade = "OUTdec")
    Double getDec();

    void setDec(Double dec);

    @InformacoesSilas(nomeTipoPropriedade = "OUTfec")
    Double getFec();

    void setFec(Double fec);

    @InformacoesSilas(nomeTipoPropriedade = "OUTens")
    Double getEns();

    void setEns(Double ens);

    @InformacoesSilas(nomeTipoPropriedade = "OUTPiorCarregamento")
    Double getPiorCarregamento();

    void setPiorCarregamento(Double piorCarregamento);

    @InformacoesSilas(nomeTipoPropriedade = "OUTMaiorQuedaTensao")
    Double getMaiorQuedaTensao();

    void setMaiorQuedaTensao(Double maiorQuedaTensao);

    @InformacoesSilas(nomeTipoPropriedade = "OUTPotSaidaAlimentador")
    Double getPotSaidaAlimentador();

    void setPotSaidaAlimentador(Double potSaidaAlimentador);

    @InformacoesSilas(nomeTipoPropriedade = "OUTPatamarSimulado")
    Integer getPatamar();

    void setPatamar(Integer patamar);

    @InformacoesSilas(nomeTipoPropriedade = "OUTPerdasAlimentador")
    Double getPerdas();

    void setPerdas(Double perdas);

    @InformacoesSilas(nomeTipoPropriedade = "OUTMensagemAlerta")
    String getMensagemAlerta();

    void setMensagemAlerta(String mensagemAlerta);
}
