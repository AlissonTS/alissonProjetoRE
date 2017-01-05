package br.ufsm.ceesp.ceee.model;

import br.com.mega.silas.algoritmos.InformacoesSilas;

/**
 * Created by Rafael on 28/12/2016.
 */
@InformacoesSilas(nomeTipoPropriedade = "OUTConsumid")
public class ClienteCEEE {

    private Long codigo;
    private String codigoAlfanumerico;
    private String descricao;
    private Transformador transformador;
    // private Double[] p24;
    // private Double[] q24;
    private String grupo;
    private String subGrupo;
    private String classe;
    private int categoria;
    private String tipoMedicao;
    private Double valorMedicao;
    private Double p0;
    private Double p1;
    private Double p2;
    private Double p3;
    private Double p4;
    private Double p5;
    private Double p6;
    private Double p7;
    private Double p8;
    private Double p9;
    private Double p10;
    private Double p11;
    private Double p12;
    private Double p13;
    private Double p14;
    private Double p15;
    private Double p16;
    private Double p17;
    private Double p18;
    private Double p19;
    private Double p20;
    private Double p21;
    private Double p22;
    private Double p23;
    private Double q0;
    private Double q1;
    private Double q2;
    private Double q3;
    private Double q4;
    private Double q5;
    private Double q6;
    private Double q7;
    private Double q8;
    private Double q9;
    private Double q10;
    private Double q11;
    private Double q12;
    private Double q13;
    private Double q14;
    private Double q15;
    private Double q16;
    private Double q17;
    private Double q18;
    private Double q19;
    private Double q20;
    private Double q21;
    private Double q22;
    private Double q23;


    @InformacoesSilas(nomeTipoPropriedade = "OUTcodigo")
    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTCodigoString")
    public String getCodigoAlfanumerico() {
        return codigoAlfanumerico;
    }

    public void setCodigoAlfanumerico(String codigoAlfanumerico) {
        this.codigoAlfanumerico = codigoAlfanumerico;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTTransformador")
    public Transformador getTransformador() {
        return transformador;
    }

    public void setTransformador(Transformador transformador) {
        this.transformador = transformador;
    }

    /* public Double[] getP24() {
        return p24;
    }

    public void setP24(Double[] p24) {
        this.p24 = p24;
    }

    public Double[] getQ24() {
        return q24;
    }

    public void setQ24(Double[] q24) {
        this.q24 = q24;
    }

    */

    @InformacoesSilas(nomeTipoPropriedade = "OUTGrupo")
    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTSubGrupo")
    public String getSubGrupo() {
        return subGrupo;
    }

    public void setSubGrupo(String subGrupo) {
        this.subGrupo = subGrupo;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTdescricao")
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTClasseConsumid")
    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTCategoriaConsumid")
    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTTipoMED")
    public String getTipoMedicao() {
        return tipoMedicao;
    }

    public void setTipoMedicao(String tipoMedicao) {
        this.tipoMedicao = tipoMedicao;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTValorMED")
    public Double getValorMedicao() {
        return valorMedicao;
    }

    public void setValorMedicao(Double valorMedicao) {
        this.valorMedicao = valorMedicao;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTP0")
    public Double getP0() {
        return p0;
    }

    public void setP0(Double p0) {
        this.p0 = p0;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTP1")
    public Double getP1() {
        return p1;
    }

    public void setP1(Double p1) {
        this.p1 = p1;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTP2")
    public Double getP2() {
        return p2;
    }

    public void setP2(Double p2) {
        this.p2 = p2;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTP3")
    public Double getP3() {
        return p3;
    }

    public void setP3(Double p3) {
        this.p3 = p3;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTP4")
    public Double getP4() {
        return p4;
    }

    public void setP4(Double p4) {
        this.p4 = p4;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTP5")
    public Double getP5() {
        return p5;
    }

    public void setP5(Double p5) {
        this.p5 = p5;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTP6")
    public Double getP6() {
        return p6;
    }

    public void setP6(Double p6) {
        this.p6 = p6;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTP7")
    public Double getP7() {
        return p7;
    }

    public void setP7(Double p7) {
        this.p7 = p7;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTP8")
    public Double getP8() {
        return p8;
    }

    public void setP8(Double p8) {
        this.p8 = p8;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTP9")
    public Double getP9() {
        return p9;
    }

    public void setP9(Double p9) {
        this.p9 = p9;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTP10")
    public Double getP10() {
        return p10;
    }

    public void setP10(Double p10) {
        this.p10 = p10;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTP11")
    public Double getP11() {
        return p11;
    }

    public void setP11(Double p11) {
        this.p11 = p11;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTP12")
    public Double getP12() {
        return p12;
    }

    public void setP12(Double p12) {
        this.p12 = p12;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTP13")
    public Double getP13() {
        return p13;
    }

    public void setP13(Double p13) {
        this.p13 = p13;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTP14")
    public Double getP14() {
        return p14;
    }

    public void setP14(Double p14) {
        this.p14 = p14;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTP15")
    public Double getP15() {
        return p15;
    }

    public void setP15(Double p15) {
        this.p15 = p15;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTP16")
    public Double getP16() {
        return p16;
    }

    public void setP16(Double p16) {
        this.p16 = p16;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTP17")
    public Double getP17() {
        return p17;
    }

    public void setP17(Double p17) {
        this.p17 = p17;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTP18")
    public Double getP18() {
        return p18;
    }

    public void setP18(Double p18) {
        this.p18 = p18;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTP19")
    public Double getP19() {
        return p19;
    }

    public void setP19(Double p19) {
        this.p19 = p19;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTP20")
    public Double getP20() {
        return p20;
    }

    public void setP20(Double p20) {
        this.p20 = p20;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTP21")
    public Double getP21() {
        return p21;
    }

    public void setP21(Double p21) {
        this.p21 = p21;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTP22")
    public Double getP22() {
        return p22;
    }

    public void setP22(Double p22) {
        this.p22 = p22;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTP23")
    public Double getP23() {
        return p23;
    }

    public void setP23(Double p23) {
        this.p23 = p23;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTQ0")
    public Double getQ0() {
        return q0;
    }

    public void setQ0(Double q0) {
        this.q0 = q0;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTQ1")
    public Double getQ1() {
        return q1;
    }

    public void setQ1(Double q1) {
        this.q1 = q1;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTQ2")
    public Double getQ2() {
        return q2;
    }

    public void setQ2(Double q2) {
        this.q2 = q2;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTQ3")
    public Double getQ3() {
        return q3;
    }

    public void setQ3(Double q3) {
        this.q3 = q3;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTQ4")
    public Double getQ4() {
        return q4;
    }

    public void setQ4(Double q4) {
        this.q4 = q4;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTQ5")
    public Double getQ5() {
        return q5;
    }

    public void setQ5(Double q5) {
        this.q5 = q5;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTQ6")
    public Double getQ6() {
        return q6;
    }

    public void setQ6(Double q6) {
        this.q6 = q6;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTQ7")
    public Double getQ7() {
        return q7;
    }

    public void setQ7(Double q7) {
        this.q7 = q7;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTQ8")
    public Double getQ8() {
        return q8;
    }

    public void setQ8(Double q8) {
        this.q8 = q8;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTQ9")
    public Double getQ9() {
        return q9;
    }

    public void setQ9(Double q9) {
        this.q9 = q9;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTQ10")
    public Double getQ10() {
        return q10;
    }

    public void setQ10(Double q10) {
        this.q10 = q10;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTQ11")
    public Double getQ11() {
        return q11;
    }

    public void setQ11(Double q11) {
        this.q11 = q11;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTQ12")
    public Double getQ12() {
        return q12;
    }

    public void setQ12(Double q12) {
        this.q12 = q12;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTQ13")
    public Double getQ13() {
        return q13;
    }

    public void setQ13(Double q13) {
        this.q13 = q13;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTQ14")
    public Double getQ14() {
        return q14;
    }

    public void setQ14(Double q14) {
        this.q14 = q14;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTQ15")
    public Double getQ15() {
        return q15;
    }

    public void setQ15(Double q15) {
        this.q15 = q15;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTQ16")
    public Double getQ16() {
        return q16;
    }

    public void setQ16(Double q16) {
        this.q16 = q16;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTQ17")
    public Double getQ17() {
        return q17;
    }

    public void setQ17(Double q17) {
        this.q17 = q17;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTQ18")
    public Double getQ18() {
        return q18;
    }

    public void setQ18(Double q18) {
        this.q18 = q18;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTQ19")
    public Double getQ19() {
        return q19;
    }

    public void setQ19(Double q19) {
        this.q19 = q19;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTQ20")
    public Double getQ20() {
        return q20;
    }

    public void setQ20(Double q20) {
        this.q20 = q20;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTQ21")
    public Double getQ21() {
        return q21;
    }

    public void setQ21(Double q21) {
        this.q21 = q21;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTQ22")
    public Double getQ22() {
        return q22;
    }

    public void setQ22(Double q22) {
        this.q22 = q22;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTQ23")
    public Double getQ23() {
        return q23;
    }

    public void setQ23(Double q23) {
        this.q23 = q23;
    }
}
