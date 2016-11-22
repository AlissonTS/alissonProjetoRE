package br.ufsm.ceesp.ceee.algoritmos.impl;

import br.com.mega.silas.algoritmos.IProgresso;
import br.com.mega.silas.algoritmos.InformacoesSilas;
import br.com.mega.silas.algoritmos.implementacao.Algoritmo;
import br.com.mega.silas.algoritmos.implementacao.ParametroAlgoritmo;
import br.com.mega.silas.algoritmos.implementacao.TipoParametroAlgoritmo;
import br.ufsm.ceesp.ceee.algoritmos.util.*;
import br.ufsm.ceesp.ceee.model.*;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Rafael
 * Date: 07/08/14
 * Time: 13:59
 * To change this template use File | Settings | File Templates.
 */
public class FluxoPotenciaTrifasico extends Algoritmo {

    public static final String VERSAO = "Release 1.0-220916";

    //atributos criados para este algoritmo
    private static final double VALOR_PERMISSIVEL = 0.01;
    public static final String MODULO_TENSAO = "Módulo da Tensão";
    public static final String MODULO_TENSAO_PU = "Módulo da Tensão (p.u.)";
    public static final String MODULO_TENSAO_INICIO = "Módulo da Tensão no nó inicial do condutor";
    public static final String MODULO_TENSAO_FIM = "Módulo da Tensão no nó final do condutor";
    private static final String ANGULO_TENSAO = "Ângulo de Tensão";
    private static final String ANGULO_FATOR_POTENCIA = "Ângulo do Fator de Potência";
    public static final String CORRENTE_ATIVA = "Corrente Ativa";
    public static final String CORRENTE_REATIVA = "Corrente Reativa";
    public static final String CONSUMIDORES_ACUM = "Consumidores Acumulados";
    public static final String CORRENTE = "Corrente";
    private static final String QUEDA_TENSAO = "Queda de Tensão";
    public static final String POTENCIA_ATIVA = "Potência Ativa";
    public static final String POTENCIA_REATIVA = "Potência Reativa";
    public static final Double FATOR_POTENCIA_TRAFOS = 0.92D;
    private static final String LETRA_FASE_1 = "D";
    private static final String LETRA_FASE_2 = "E";
    private static final String LETRA_FASE_3 = "F";
    private static Double VALOR_TENSAO_ALIMENTADOR = new Double(13.8);
    private static final double VALOR_RAIZ_DE_TRES = Math.sqrt(3.0);
    public static final String PERDA_ATIVA = "Perda de Pot. Ativa";
    public static final String PERDA_REATIVA = "Perda de Pot. Reativa";
    private static final String ANGULO_CORRENTE = "Ângulo da Corrente";
    private static final String QUEDA_ATIVA = "Queda de Tensao Ativa";
    private static final String QUEDA_REATIVA = "Queda de Tensao Reativa";
    public static final String POTENCIA = "Potência";
    private static final Double PORCENTAGEM_TESTE_CARREGAMENTO_TRAFO = 0.3;
    public static final String DEMANDA_ATIVA = "Demanda Ativa";
    public static final String DEMANDA_REATIVA = "Demanda Reativa";
    public static final String PERC_CARREGAMENTO = "Carregamento";
    public static final String PERC_QUEDA_TENSAO = "Queda de Tensão (primário)";


    private Map<Alimentador, Double> PAl = new HashMap<Alimentador, Double>();
    private Map<Alimentador, Double> QAl = new HashMap<Alimentador, Double>();

    private Map<Alimentador, Double> iAtAl = new HashMap<Alimentador, Double>();
    private Map<Alimentador, Double> iReAl = new HashMap<Alimentador, Double>();
    private boolean naoAjustarCargas = false;

    //TEMPORARIO (parâmetros de medição)

    public enum TipoCargas {
        ET, EP, ET_EP;

        public static TipoCargas fromString(String s) {
            if (s == null) return null;
            if (s.trim().equals("0")) {
                return ET;
            }
            if (s.trim().equals("1")) {
                return EP;
            }
            if (s.trim().equals("2")) {
                return ET_EP;
            }
            if (s.trim().equals("ET")) {
                return ET;
            }
            if (s.trim().equals("EP")) {
                return EP;
            }
            if (s.trim().equals("ET_EP")) {
                return ET_EP;
            }
            return null;
        }
    }

    private class AjusteCarga {

        String tipoAjuste;

        Double correnteP1;
        Double correnteP2;
        Double correnteP3;
        Double correnteP4;

        Double getCorrenteMedida(int patamar) {
            if (patamar == 1) {
                if (correnteP1 != null && correnteP1 > 0.0 )
                {
                    return correnteP1;
                }
            } else if (patamar == 2) {
                if (correnteP2 != null && correnteP2 > 0.0 ) {
                    return correnteP2;
                }
            } else if (patamar == 3) {
                if (correnteP3 != null && correnteP3 > 0.0) {
                    return correnteP3;
                }
            } else if (patamar == 4) {
                if (correnteP4 != null && correnteP4 > 0.0) {
                    return correnteP4;
                }
            }
            return null;
        }

        Double potAtivaP1;
        Double potAtivaP2;
        Double potAtivaP3;
        Double potAtivaP4;

        Double potReativaP1;
        Double potReativaP2;
        Double potReativaP3;
        Double potReativaP4;

        public Double[] getPQMedido(int patamar) {
            if (patamar == 1) {
                if (potAtivaP1 != null && Math.abs(potAtivaP1) > 0.0 && potReativaP1 != null && Math.abs(potReativaP1) > 0.0) {
                    return new Double[]{potAtivaP1, potReativaP1};
                }
            } else if (patamar == 2) {
                if (potAtivaP2 != null && Math.abs(potAtivaP2) > 0.0 && potReativaP2 != null && Math.abs(potReativaP2) > 0.0) {
                    return new Double[]{potAtivaP2, potReativaP2};
                }
            } else if (patamar == 3) {
                if (potAtivaP3 != null && Math.abs(potAtivaP3) > 0.0 && potReativaP3 != null && Math.abs(potReativaP3) > 0.0) {
                    return new Double[]{potAtivaP3, potReativaP3};
                }
            } else if (patamar == 4) {
                if (potAtivaP4 != null && Math.abs(potAtivaP4) > 0.0 && potReativaP4 != null && Math.abs(potReativaP4) > 0.0) {
                    return new Double[]{potAtivaP4, potReativaP4};
                }
            }
            return null;
        }

    }

    private Map<Alimentador, AjusteCarga> ajustesAlimentador = new HashMap<Alimentador, AjusteCarga>();


    public FluxoPotenciaTrifasico() {
    }

    @Override
    public List<TipoParametroAlgoritmo> getTiposParametros() {
        LinkedList<TipoParametroAlgoritmo> linkedList = new LinkedList<TipoParametroAlgoritmo>();

        TipoParametroAlgoritmo<Integer> patamar = new TipoParametroAlgoritmo<Integer>();
        patamar.setClasseTipoValor(Integer.class);
        patamar.setTipoValorParametro(TipoParametroAlgoritmo.TipoValorParametro.INTEIRO);
        patamar.setChaveConsulta("Patamar");
        patamar.setTipoEscopo(TipoParametroAlgoritmo.TIPO_ESCOPO_GERAL);
        patamar.setNome("Patamar");
        patamar.setIdTipoPropriedadeEntidade("OUTpatamarCarga");
        linkedList.add(patamar);

        TipoParametroAlgoritmo<String> cenario = new TipoParametroAlgoritmo<String>();
        cenario.setClasseTipoValor(String.class);
        cenario.setTipoValorParametro(TipoParametroAlgoritmo.TipoValorParametro.ALFANUMERICO);
        cenario.setChaveConsulta("Cenário");
        cenario.setTipoEscopo(TipoParametroAlgoritmo.TIPO_ESCOPO_GERAL);
        List<String> cenarios = new ArrayList<String>();
        for (Cenario cen : Cenario.values()) {
            cenarios.add(cen.toString());
        }
        cenario.setOpcoesValores(cenarios);
        cenario.setNome("Cenário");
        linkedList.add(cenario);

        TipoParametroAlgoritmo<Boolean> limparVariaveis = new TipoParametroAlgoritmo<Boolean>();
        limparVariaveis.setClasseTipoValor(Boolean.class);
        limparVariaveis.setTipoValorParametro(TipoParametroAlgoritmo.TipoValorParametro.BOOLEANO);
        limparVariaveis.setChaveConsulta("Forçar Limpeza");
        limparVariaveis.setTipoEscopo(TipoParametroAlgoritmo.TIPO_ESCOPO_GERAL);
        limparVariaveis.setNome("");
        limparVariaveis.setValorPadrao("Sim");
        linkedList.add(limparVariaveis);

        TipoParametroAlgoritmo<Double> pP1 = new TipoParametroAlgoritmo<Double>();
        pP1.setClasseTipoValor(Double.class);
        pP1.setTipoValorParametro(TipoParametroAlgoritmo.TipoValorParametro.DUPLA_PRECISAO);
        pP1.setChaveConsulta("Pot. Ativa (kW) P1");
        pP1.setNome("Pot. Ativa (kW) P1");
        pP1.setCampoValorPadrao("OUTajusteCarga.OUTpotenciaAtivaP1");
        linkedList.add(pP1);

        TipoParametroAlgoritmo<Double> pP2 = new TipoParametroAlgoritmo<Double>();
        pP2.setClasseTipoValor(Double.class);
        pP2.setTipoValorParametro(TipoParametroAlgoritmo.TipoValorParametro.DUPLA_PRECISAO);
        pP2.setChaveConsulta("Pot. Ativa (kW) P2");
        pP2.setNome("Pot. Ativa (kW) P2");
        pP2.setCampoValorPadrao("OUTajusteCarga.OUTpotenciaAtivaP2");
        linkedList.add(pP2);

        TipoParametroAlgoritmo<Double> pP3 = new TipoParametroAlgoritmo<Double>();
        pP3.setClasseTipoValor(Double.class);
        pP3.setTipoValorParametro(TipoParametroAlgoritmo.TipoValorParametro.DUPLA_PRECISAO);
        pP3.setChaveConsulta("Pot. Ativa (kW) P3");
        pP3.setNome("Pot. Ativa (kW) P3");
        pP3.setCampoValorPadrao("OUTajusteCarga.OUTpotenciaAtivaP3");
        linkedList.add(pP3);

        TipoParametroAlgoritmo<Double> pP4 = new TipoParametroAlgoritmo<Double>();
        pP4.setClasseTipoValor(Double.class);
        pP4.setTipoValorParametro(TipoParametroAlgoritmo.TipoValorParametro.DUPLA_PRECISAO);
        pP4.setChaveConsulta("Pot. Ativa (kW) P4");
        pP4.setNome("Pot. Ativa (kW) P4");
        pP4.setCampoValorPadrao("OUTajusteCarga.OUTpotenciaAtivaP4");
        linkedList.add(pP4);

        TipoParametroAlgoritmo<Double> qP1 = new TipoParametroAlgoritmo<Double>();
        qP1.setClasseTipoValor(Double.class);
        qP1.setTipoValorParametro(TipoParametroAlgoritmo.TipoValorParametro.DUPLA_PRECISAO);
        qP1.setChaveConsulta("Pot. Reativa (kVAr) P1");
        qP1.setNome("Pot. Reativa (kVAr) P1");
        qP1.setCampoValorPadrao("OUTajusteCarga.OUTpotenciaReativaP1");
        linkedList.add(qP1);

        TipoParametroAlgoritmo<Double> qP2 = new TipoParametroAlgoritmo<Double>();
        qP2.setClasseTipoValor(Double.class);
        qP2.setTipoValorParametro(TipoParametroAlgoritmo.TipoValorParametro.DUPLA_PRECISAO);
        qP2.setChaveConsulta("Pot. Reativa (kVAr) P2");
        qP2.setNome("Pot. Reativa (kVAr) P2");
        qP2.setCampoValorPadrao("OUTajusteCarga.OUTpotenciaReativaP2");
        linkedList.add(qP2);

        TipoParametroAlgoritmo<Double> qP3 = new TipoParametroAlgoritmo<Double>();
        qP3.setClasseTipoValor(Double.class);
        qP3.setTipoValorParametro(TipoParametroAlgoritmo.TipoValorParametro.DUPLA_PRECISAO);
        qP3.setChaveConsulta("Pot. Reativa (kVAr) P3");
        qP3.setNome("Pot. Reativa (kVAr) P3");
        qP3.setCampoValorPadrao("OUTajusteCarga.OUTpotenciaReativaP3");
        linkedList.add(qP3);

        TipoParametroAlgoritmo<Double> qP4 = new TipoParametroAlgoritmo<Double>();
        qP4.setClasseTipoValor(Double.class);
        qP4.setTipoValorParametro(TipoParametroAlgoritmo.TipoValorParametro.DUPLA_PRECISAO);
        qP4.setChaveConsulta("Pot. Reativa (kVAr) P4");
        qP4.setNome("Pot. Reativa (kVAr) P4");
        qP4.setCampoValorPadrao("OUTajusteCarga.OUTpotenciaReativaP4");
        linkedList.add(qP4);


        TipoParametroAlgoritmo<Double> correnteP1 = new TipoParametroAlgoritmo<Double>();
        correnteP1.setClasseTipoValor(Double.class);
        correnteP1.setTipoValorParametro(TipoParametroAlgoritmo.TipoValorParametro.DUPLA_PRECISAO);
        correnteP1.setChaveConsulta("Corrente (A) P1");
        correnteP1.setNome("Corrente (A) P1");
        correnteP1.setCampoValorPadrao("OUTajusteCarga.OUTcorrenteP1");
        linkedList.add(correnteP1);

        TipoParametroAlgoritmo<Double> correnteP2 = new TipoParametroAlgoritmo<Double>();
        correnteP2.setClasseTipoValor(Double.class);
        correnteP2.setTipoValorParametro(TipoParametroAlgoritmo.TipoValorParametro.DUPLA_PRECISAO);
        correnteP2.setChaveConsulta("Corrente (A) P2");
        correnteP2.setNome("Corrente (A) P2");
        correnteP2.setCampoValorPadrao("OUTajusteCarga.OUTOUTcorrenteP2");
        linkedList.add(correnteP2);

        TipoParametroAlgoritmo<Double> correnteP3 = new TipoParametroAlgoritmo<Double>();
        correnteP3.setClasseTipoValor(Double.class);
        correnteP3.setTipoValorParametro(TipoParametroAlgoritmo.TipoValorParametro.DUPLA_PRECISAO);
        correnteP3.setChaveConsulta("Corrente (A) P3");
        correnteP3.setNome("Corrente (A) P3");
        correnteP3.setCampoValorPadrao("OUTajusteCarga.OUTOUTcorrenteP3");
        linkedList.add(correnteP3);

        TipoParametroAlgoritmo<Double> correnteP4 = new TipoParametroAlgoritmo<Double>();
        correnteP4.setClasseTipoValor(Double.class);
        correnteP4.setTipoValorParametro(TipoParametroAlgoritmo.TipoValorParametro.DUPLA_PRECISAO);
        correnteP4.setChaveConsulta("Corrente (A) P4");
        correnteP4.setNome("Corrente (A) P4");
        correnteP4.setCampoValorPadrao("OUTajusteCarga.OUTOUTcorrenteP4");
        linkedList.add(correnteP4);

        TipoParametroAlgoritmo<String> ajusteDemanda = new TipoParametroAlgoritmo<String>();
        ajusteDemanda.setClasseTipoValor(String.class);
        ajusteDemanda.setTipoValorParametro(TipoParametroAlgoritmo.TipoValorParametro.ALFANUMERICO);
        ajusteDemanda.setChaveConsulta("Ajuste Demanda");
        ajusteDemanda.setNome("Ajuste Demanda");
        List<String> tiposDemanda = new ArrayList<String>();
        //tiposDemanda.add("Padrão do Medidor");
        tiposDemanda.add("ET");
        tiposDemanda.add("EP");
        tiposDemanda.add("ET_EP");
        ajusteDemanda.setOpcoesValores(tiposDemanda);
        ajusteDemanda.setCampoValorPadrao("OUTajusteCarga.OUTcalculoDemanda");
        linkedList.add(ajusteDemanda);

        return linkedList;
    }

    private Map<Integer, Collection<ClasseConsumidor>> categoriasConsumo = null;
    private Map<Long, ClasseConsumidor> mapClassesConsumidor = null;

    private Collection<ClasseConsumidor> classesConsumidor;

    @InformacoesSilas(nomeTipoPropriedade = "OUTcategoriaConsumo")
    public Collection<ClasseConsumidor> getClassesConsumidor() {
        return classesConsumidor;
    }

    private Collection<PatamarDeCarga> patamaresDeCarga = null;

    @InformacoesSilas(nomeTipoPropriedade = "OUTpatamarCarga")
    public Collection<PatamarDeCarga> getPatamaresDeCarga() {
        return patamaresDeCarga;
    }

    public PatamarDeCarga getPatamar(int patamar) {
        for (PatamarDeCarga pat : patamaresDeCarga) {
            if (pat.getPatamar().equals(patamar)) {
                return pat;
            }
        }
        return null;
    }

    public void setPatamaresDeCarga(Collection<PatamarDeCarga> patamaresDeCarga) {
        this.patamaresDeCarga = patamaresDeCarga;
    }

    /**private Map<Integer, PatamarDeCarga> patamaresDeCarga = null;

     //@InformacoesSilas(nomeTipoPropriedade = "OUTpatamarCarga", campoIndiceMapa = "OUTpatamar")
     public Map<Integer, PatamarDeCarga> getPatamaresDeCarga() {
     return patamaresDeCarga;
     }

     public void setPatamaresDeCarga(Map<Integer, PatamarDeCarga> patamaresDeCarga) {
     this.patamaresDeCarga = patamaresDeCarga;
     }*/

    public void setClassesConsumidor(Collection<ClasseConsumidor> classesConsumidor) {
        this.classesConsumidor = classesConsumidor;
        if (classesConsumidor != null) {
            categoriasConsumo = new LinkedHashMap<Integer, Collection<ClasseConsumidor>>();
            mapClassesConsumidor = new HashMap<Long, ClasseConsumidor>();
            for (ClasseConsumidor classeConsumidor : classesConsumidor) {
                if (classeConsumidor.getNumero() >=0 && classeConsumidor.getNumero() <=4) {
                    Collection<ClasseConsumidor> categoria = categoriasConsumo.get(classeConsumidor.getNumero() + 1);
                    if (categoria == null) {
                        categoria = new HashSet<ClasseConsumidor>();
                        categoriasConsumo.put(classeConsumidor.getNumero() + 1, categoria);
                    }
                    categoria.add(classeConsumidor);
                }
                mapClassesConsumidor.put(classeConsumidor.getCodigo(), classeConsumidor);
            }
        }
    }

    private static String tipoDia = "Dia Útil";
    private static Integer indiceHorario = 0;
    private Integer patamarAtual = 1;
    private Integer anoProjecaoAtual = 0;
    private boolean verificaDivergencia = true;
    private boolean alteraInterface = true;

    public boolean isAlteraInterface() {
        return alteraInterface;
    }

    public void setAlteraInterface(boolean alteraInterface) {
        this.alteraInterface = alteraInterface;
    }

    public boolean isVerificaDivergencia() {
        return verificaDivergencia;
    }

    public void setVerificaDivergencia(boolean verificaDivergencia) {
        this.verificaDivergencia = verificaDivergencia;
    }

    public Integer getAnoProjecaoAtual() {
        return anoProjecaoAtual;
    }

    private Map<Alimentador, List<Double[]>> demandasAl = new HashMap<Alimentador, List<Double[]>>();

    @Override
    public List<Object> executar(List<Object> alimentadores, List<Map<String, ParametroAlgoritmo>> parametros, IProgresso iProgresso) {
        int i = 0;
        try {
            for (Object alimentador : alimentadores) {
                executar((Alimentador) alimentador, parametros.get(i), iProgresso);
                i++;
            }
        } catch (Exception e) { }
        getSucessos().add(VERSAO);
        return null;
    }

    @Override
    public void posExecucao(List<Object> list, List<Map<String, ParametroAlgoritmo>> list1, IProgresso iProgresso) {

    }

    private static DecimalFormat nf = (DecimalFormat) NumberFormat.getInstance(new Locale("pt", "BR"));

    static {
        nf.applyPattern("0.00");
    }

    private void setaPQ(INavegavel navegavel) {
        List<Double[]> l = new ArrayList<Double[]>();
        MedidaEletrica att = navegavel.getMedidaEletrica(POTENCIA_ATIVA);
        PAl.put(navegavel.getAlimentador(), att.getValorA() + att.getValorB() + att.getValorC());
        l.add(new Double[]{att.getValorA(), att.getValorB(), att.getValorC()});

        att = navegavel.getMedidaEletrica(POTENCIA_REATIVA);
        QAl.put(navegavel.getAlimentador(), att.getValorA() + att.getValorB() + att.getValorC());
        l.add(new Double[]{att.getValorA(), att.getValorB(), att.getValorC()});

        att = navegavel.getMedidaEletrica(CORRENTE_ATIVA);
        iAtAl.put(navegavel.getAlimentador(), att.getValorA() + att.getValorB() + att.getValorC());
        att = navegavel.getMedidaEletrica(CORRENTE_REATIVA);
        iReAl.put(navegavel.getAlimentador(), att.getValorA() + att.getValorB() + att.getValorC());

        demandasAl.put(navegavel.getAlimentador(), l);
    }

    private void limpaDados(Collection<Alimentador> alimentadores) {
        erros.clear();
        for (Alimentador alimentador : alimentadores) {
            limpaDados(alimentador);
        }
    }

    private void limpaDados(Alimentador alimentador) {
        PAl.put(alimentador, 0.0);
        QAl.put(alimentador, 0.0);
        iAtAl.put(alimentador, 0.0);
        iReAl.put(alimentador, 0.0);

        for (Barra barra : alimentador.getBarras()) {
            if (forçarLimpezaEstado) {
                barra.getMedidasEletricas().clear();
                barra.getMedidasEletricasExibir().clear();
            }

            barra.removerMedidaEletrica(MODULO_TENSAO);
            barra.removerMedidaEletrica(MODULO_TENSAO_FIM);
            barra.removerMedidaEletrica(MODULO_TENSAO_INICIO);
            barra.removerMedidaEletrica(ANGULO_TENSAO);
            barra.removerMedidaEletrica(ANGULO_FATOR_POTENCIA);
            barra.removerMedidaEletrica(CORRENTE_ATIVA);
            barra.removerMedidaEletrica(CORRENTE_REATIVA);
            barra.removerMedidaEletrica(CORRENTE);
            barra.removerMedidaEletrica(QUEDA_TENSAO);
            barra.removerMedidaEletrica(POTENCIA);
            barra.removerMedidaEletrica(POTENCIA_ATIVA);
            barra.removerMedidaEletrica(POTENCIA_REATIVA);
            barra.removerMedidaEletrica(PERDA_ATIVA);
            barra.removerMedidaEletrica(PERDA_REATIVA);
            barra.removerMedidaEletrica(ANGULO_CORRENTE);
            barra.removerMedidaEletrica(QUEDA_ATIVA);
            barra.removerMedidaEletrica(QUEDA_REATIVA);
            barra.removerMedidaEletrica(CONSUMIDORES_ACUM);
            //barra.removerMedidaEletrica(PERC_CARREGAMENTO);
            barra.removerMedidaEletrica(PERC_QUEDA_TENSAO);
            if (forçarLimpezaEstado && barra.getTransformador() != null) {
                barra.getTransformador().getMedidasEletricas().clear();
            }
        }
        for (TrechoRede a : alimentador.getTrechosRede()) {
            if (forçarLimpezaEstado) {
                a.getMedidasEletricasExibir().clear();
                a.getMedidasEletricas().clear();
            }
            a.removerMedidaEletrica(MODULO_TENSAO);
            a.removerMedidaEletrica(MODULO_TENSAO_FIM);
            a.removerMedidaEletrica(MODULO_TENSAO_INICIO);
            a.removerMedidaEletrica(ANGULO_TENSAO);
            a.removerMedidaEletrica(ANGULO_FATOR_POTENCIA);
            a.removerMedidaEletrica(CORRENTE_ATIVA);
            a.removerMedidaEletrica(CORRENTE_REATIVA);
            a.removerMedidaEletrica(CORRENTE);
            a.removerMedidaEletrica(QUEDA_TENSAO);
            a.removerMedidaEletrica(POTENCIA);
            a.removerMedidaEletrica(POTENCIA_ATIVA);
            a.removerMedidaEletrica(POTENCIA_REATIVA);
            a.removerMedidaEletrica(PERDA_ATIVA);
            a.removerMedidaEletrica(PERDA_REATIVA);
            a.removerMedidaEletrica(ANGULO_CORRENTE);
            a.removerMedidaEletrica(QUEDA_ATIVA);
            a.removerMedidaEletrica(QUEDA_REATIVA);
            a.removerMedidaEletrica(CONSUMIDORES_ACUM);
            a.removerMedidaEletrica(PERC_CARREGAMENTO);
            a.removerMedidaEletrica(PERC_QUEDA_TENSAO);
        }
        if (!naoAjustarCargas || corrigeCargas) {
            for (Transformador t : alimentador.getTransformadores()) {
                t.setDifIAtivaCorrecao(null);
                t.setDifIReativaCorrecao(null);
            }
        }

    }

    static Collection<String> erros = new ArrayList<String>();
    Collection<String> alertas = new ArrayList<String>();
    Collection<String> sucessos = new ArrayList<String>();

    @Override
    public Collection<String> getErros() {
        return erros;
    }

    @Override
    public Collection<String> getAlertas() {
        return alertas;
    }

    @Override
    public Collection<String> getSucessos() {
        return sucessos;
    }

    @Override
    public void preExecucao(List<Object> list, List<Map<String, ParametroAlgoritmo>> list1, IProgresso iProgresso) {

    }

    public void setNaoAjustarCargas(boolean naoAjustarCargas) {
        this.naoAjustarCargas = naoAjustarCargas;
    }

    public boolean isNaoAjustarCargas() {
        return naoAjustarCargas;
    }

    private boolean corrigeCargas = true;

    public boolean isCorrigeCargas() {
        return corrigeCargas;
    }

    public void setCorrigeCargas(boolean corrigeCargas) {
        this.corrigeCargas = corrigeCargas;
    }

    class CalculaRelatorioPosExecucao implements AcaoPreFixada {

        double pPerdas = 0.0, qPerdas = 0.0;
        double piorCarreg = 0.0;
        double piorQueda = 0.0;

        double somaDist = 0.0;
        double somaCarregXDist = 0.0;

        @Override
        public Object executePre(INavegavel n, INavegavel anterior, Object params) {
            if (n instanceof Barra) {
                Barra b = (Barra) n;
                if (b.getTransformador() != null && b.getMedidaEletrica(MODULO_TENSAO) != null) {
                    Double[] tensao = b.getMedidaEletrica(MODULO_TENSAO).getValorArray();
                    double Vnom = b.getTensaoNominal();
                    Double[] queda = new Double[] {
                            (Vnom - tensao[0]) * 100.0 / Vnom,
                            (Vnom - tensao[1]) * 100.0 / Vnom,
                            (Vnom - tensao[2]) * 100.0 / Vnom
                    };
                    MedidaEletrica medida = new MedidaEletrica(PERC_QUEDA_TENSAO, queda, "%", true);
                    b.adicionaMedidaEletrica(PERC_QUEDA_TENSAO, medida);
                    double maxQueda = Math.max(Math.max(queda[0], queda[1]), queda[2]);
                    if (maxQueda > piorQueda) {
                        piorQueda = maxQueda;
                    }
                    b.getTransformador().setQuedaTensao(maxQueda);
                    if (b.getTransformador().getMarcacaoMapa() != null && b.getAlimentador().getMarcacaoMapa() != null) {
                        b.getTransformador().getMarcacaoMapa().setAtualizar(true);
                        b.getAlimentador().getMarcacaoMapa().setAtualizar(true);
                    }
                }
            } else if (n instanceof TrechoRede && n.getMedidaEletrica(CORRENTE) != null) {
                TrechoRede trecho = (TrechoRede) n;
                int cap = trecho.getCapacidade();
                Double[] corrente = trecho.getMedidaEletrica(CORRENTE).getValorArray();
                Double[] carreg = new Double[] {
                        (corrente[0] / cap) * 100.0,
                        (corrente[1] / cap) * 100.0,
                        (corrente[2] / cap) * 100.0
                };
                if (n.getMedidaEletrica(PERDA_ATIVA) != null) {
                    pPerdas += n.getMedidaEletrica(PERDA_ATIVA).getValorSomado();
                    qPerdas += n.getMedidaEletrica(PERDA_REATIVA).getValorSomado();
                }
                MedidaEletrica medidaEletrica = new MedidaEletrica(PERC_CARREGAMENTO, carreg, "%", true);
                medidaEletrica.setValorResumo(medidaEletrica.getValorMaximo());
                trecho.adicionaMedidaEletrica(PERC_CARREGAMENTO, medidaEletrica);
                double maxCarreg = Math.max(Math.max(carreg[0], carreg[1]), carreg[2]);
                if (maxCarreg > 100) {
                    somaDist += trecho.getComprimentoKm();
                    somaCarregXDist += maxCarreg * trecho.getComprimentoKm();
                }

                if (maxCarreg > piorCarreg) {
                    piorCarreg = maxCarreg;
                }
                if (trecho.getMarcacaoMapa() != null) {
                    if (maxCarreg < 80.0) {
                        trecho.getMarcacaoMapa().setCorMarcacao("green");
                    } else if (maxCarreg >= 80.0 && maxCarreg <= 100.0) {
                        trecho.getMarcacaoMapa().setCorMarcacao("yellow");
                    } else {
//                        if (trecho.getComprimento() < 2.0) {
//                            //trecho.getMarcacaoMapa().setEspessuraLinha(50);
//                            //trecho.getMarcacaoMapa().setAtualizar(true);
//                            trecho.getMarcacaoMapa().setCorMarcacao("red");
//                            alertas.add("Provável erro de cadastro: " + trecho.toString());
//                        } else {
                        trecho.getMarcacaoMapa().setCorMarcacao("red");
//                        }
                    }
                    trecho.getMarcacaoMapa().setAtualizar(true);
                }
            }
            return null;
        }

        private double getDesvioPadrao(Alimentador alimentador) {
            double media = somaCarregXDist / somaDist;
            double var = 0.0;
            int qtdAmostras = 0;
            for (TrechoRede trechoRede : alimentador.getTrechosRede()) {
                if (trechoRede.getMedidaEletrica(PERC_CARREGAMENTO) != null) {
                    double carregamento = trechoRede.getMedidaEletrica(PERC_CARREGAMENTO).getValorMaximo();
                    if (carregamento > 100) {
                        var += Math.pow(carregamento - media, 2.0);
                        qtdAmostras++;
                    }
                }
            }
            return Math.sqrt(var / qtdAmostras);
        }

        private Double destacaCarregamentoForaVariancia(Alimentador alimentador) {
            if (!isAlteraInterface()) return null;
            double media = somaCarregXDist / somaDist;
            double var = getDesvioPadrao(alimentador);
            boolean destacou = false;
            for (TrechoRede trechoRede : alimentador.getTrechosRede()) {
                if (trechoRede.getMedidaEletrica(PERC_CARREGAMENTO) != null) {
                    double carregamento = trechoRede.getMedidaEletrica(PERC_CARREGAMENTO).getValorMaximo();
                    if (carregamento > 100.0 && carregamento > ((3 * var) + media)) {
                        alertas.add("Provável erro de cadastro: " + trechoRede.toString());
                        trechoRede.getMarcacaoMapa().setEspessuraLinha(50);
                        trechoRede.getMarcacaoMapa().setAtualizar(true);
                        destacou = true;
                    }
                }
            }
            return destacou ? ((2 * var) + media) : null;
        }

        public double getPotenciaDistribuida(Alimentador alimentador) {
            double pal, qal;
            pal = PAl.get(alimentador);
            qal = QAl.get(alimentador);
            return Math.sqrt(pal * pal + qal * qal);
        }

        public double getPercentualPerdas(Alimentador alimentador) {
            return Math.sqrt(pPerdas * pPerdas + qPerdas * qPerdas) * 100.0 / getPotenciaDistribuida(alimentador);
        }

        public double getPerdasKVA() {
            return Math.sqrt(pPerdas * pPerdas + qPerdas * qPerdas);
        }

    }

    private boolean permiteAlsDiferentes = true;
    private boolean forçarLimpezaEstado = false;
    private boolean divergente = false;

    public boolean isDivergente() {
        return divergente;
    }

    public boolean isPermiteAlsDiferentes() {
        return permiteAlsDiferentes;
    }

    public void setPermiteAlsDiferentes(boolean permiteAlsDiferentes) {
        this.permiteAlsDiferentes = permiteAlsDiferentes;
    }

    public void executar(Alimentador elemento, Map<String, ParametroAlgoritmo> parametros, IProgresso progresso) {
        ParametroAlgoritmo param = parametros.get("Patamar");
        this.patamarAtual = (Integer) param.getValor();
        param = parametros.get("Ano");
        this.anoProjecaoAtual = param != null && param.getValor() != null ? ((Integer) param.getValor()) : 0;
        param = parametros.get("Cenário");
        String cenario = "NORMAL";
        if (param != null) {
            cenario = param.getValor().toString();
        }
        if (parametros.get("Forçar Limpeza") != null && parametros.get("Forçar Limpeza").getValor() != null) {
            forçarLimpezaEstado = (Boolean) parametros.get("Forçar Limpeza").getValor();
        }
        boolean divergente = false;
        CalculaTensao.divergente = false;
        Collection<Alimentador> alimentadores = new ArrayList<Alimentador>();
        alimentadores.add(elemento);
        limpaDados(alimentadores);
        setaAjustesDemanda(elemento, parametros);
        preExecucao(elemento, getPatamar(patamarAtual), Cenario.valueOf(cenario));
        for (Alimentador alimentador : alimentadores) {
            //TODO: tentar inferir a unidade de medida, ou ver um jeito de configurar isso no software.
            VALOR_TENSAO_ALIMENTADOR = alimentador.getTransformadorSE().getTensaoNominalSecundaria();
            if (alimentador.getTransformadorSE().getTensao(patamarAtual) != null)
            {
                VALOR_TENSAO_ALIMENTADOR = alimentador.getTransformadorSE().getTensao(patamarAtual);
            }
            this.divergente = calculaFluxoPotenciaAlimentador(alimentador);

            setaConsumidoresAlimentador(alimentador);
            if (!divergente && !naoAjustarCargas && corrigeCargas) {
                divergente = corrigeCargasAlimentador(alimentador, getPatamar(patamarAtual), Cenario.valueOf(cenario), false);
            }

            CalculaRelatorioPosExecucao relatorioPosExecucaoAl = new CalculaRelatorioPosExecucao();
            Navegador.percorreArvore(alimentador.getTrechoRedeInicial(), null, relatorioPosExecucaoAl, SemAcaoPosFixada.INSTANCE, permiteAlsDiferentes, getErros());

            ResultadosAlimentador resultadosAlimentador = new ResultadosAlimentador();
            resultadosAlimentador.setAlimentador(alimentador);
            alimentador.setResultadosAlimentador(resultadosAlimentador);
            resultadosAlimentador.setDataDiagnostico(new Date());
            resultadosAlimentador.setPatamar(this.patamarAtual);
            resultadosAlimentador.setPiorCarregamento(relatorioPosExecucaoAl.piorCarreg);
            resultadosAlimentador.setMaiorQuedaTensao(relatorioPosExecucaoAl.piorQueda);
            resultadosAlimentador.setPotSaidaAlimentador(relatorioPosExecucaoAl.getPotenciaDistribuida(alimentador) / 1000.0);
            resultadosAlimentador.setPerdas(relatorioPosExecucaoAl.getPercentualPerdas(alimentador));
            resultadosAlimentador.setPerdaskVA(relatorioPosExecucaoAl.getPerdasKVA());
            for (String alerta : getAlertas()) {
                resultadosAlimentador.setMensagemAlerta(resultadosAlimentador.getMensagemAlerta() + "<br />" + alerta);
            }
            if (resultadosAlimentador.getMensagemAlerta() != null && resultadosAlimentador.getMensagemAlerta().length() >= 1000) {
                resultadosAlimentador.setMensagemAlerta(resultadosAlimentador.getMensagemAlerta().substring(0, 999));
            }
            transformaTensaoDeFase(alimentador);
            Double carregamForaVar = relatorioPosExecucaoAl.destacaCarregamentoForaVariancia(alimentador);
            if (carregamForaVar != null) {
                alertas.add("Carregamentos maiores do que " + carregamForaVar + " foram destacados.");
            }
        }
        NumberFormat NF = new DecimalFormat("#,##0.00");
        if (divergente && isVerificaDivergencia()) {
            erros.add("Fluxo divergente.");
        } else {
            for (Alimentador al : alimentadores) {
                sucessos.add("[" + al.getNome() + "] Pior Carregamento: " + NF.format(al.getResultadosAlimentador().getPiorCarregamento()) + "%");
                sucessos.add("[" + al.getNome() + "] Maior Queda de Tensão: " + NF.format(al.getResultadosAlimentador().getMaiorQuedaTensao()) + "%");
                sucessos.add("[" + al.getNome() + "] Potência na saída do AL: " + NF.format(al.getResultadosAlimentador().getPotSaidaAlimentador()) + " MVA");
                sucessos.add("[" + al.getNome() + "] Perdas no AL: " + NF.format(al.getResultadosAlimentador().getPerdas()) + "%");
            }
        }
    }

    private boolean possuiMedidorSE(Alimentador alimentador, int patamar) {
        if (!naoAjustarCargas && corrigeCargas && ajustesAlimentador.get(alimentador) != null) {
            AjusteCarga ac = ajustesAlimentador.get(alimentador);
            return ac.getPQMedido(patamar) != null || ac.getCorrenteMedida(patamar) != null;
        }
        return false;
    }

    private void setaAjustesDemanda(Alimentador alimentador, Map<String, ParametroAlgoritmo> parametros) {
        AjusteCarga aj = new AjusteCarga();
        ParametroAlgoritmo p = parametros.get("Ajuste Demanda");
        if (p == null) {
            aj.tipoAjuste = alimentador.getTipoAjuste();
            aj.potAtivaP1 = alimentador.getpPatamar1();
            aj.potAtivaP2 = alimentador.getpPatamar2();
            aj.potAtivaP3 = alimentador.getpPatamar3();
            aj.potAtivaP4 = alimentador.getpPatamar4();

            aj.potReativaP1 = alimentador.getqPatamar1();
            aj.potReativaP2 = alimentador.getqPatamar2();
            aj.potReativaP3 = alimentador.getqPatamar3();
            aj.potReativaP4 = alimentador.getqPatamar4();

            aj.correnteP1 = alimentador.getiPatamar1();
            aj.correnteP2 = alimentador.getiPatamar2();
            aj.correnteP3 = alimentador.getiPatamar3();
            aj.correnteP4 = alimentador.getiPatamar4();
        } else {
            aj.tipoAjuste = (String) p.getValor();

            p = parametros.get("Corrente (A) P1");
            aj.correnteP1 = (Double) p.getValor();
            p = parametros.get("Corrente (A) P2");
            aj.correnteP2 = (Double) p.getValor();
            p = parametros.get("Corrente (A) P3");
            aj.correnteP3 = (Double) p.getValor();
            p = parametros.get("Corrente (A) P4");
            aj.correnteP4 = (Double) p.getValor();

            p = parametros.get("Pot. Ativa (kW) P1");
            aj.potAtivaP1 = (Double) p.getValor();
            p = parametros.get("Pot. Ativa (kW) P2");
            aj.potAtivaP2 = (Double) p.getValor();
            p = parametros.get("Pot. Ativa (kW) P3");
            aj.potAtivaP3 = (Double) p.getValor();
            p = parametros.get("Pot. Ativa (kW) P4");
            aj.potAtivaP4 = (Double) p.getValor();

            p = parametros.get("Pot. Reativa (kVAr) P1");
            aj.potReativaP1 = (Double) p.getValor();
            p = parametros.get("Pot. Reativa (kVAr) P2");
            aj.potReativaP2 = (Double) p.getValor();
            p = parametros.get("Pot. Reativa (kVAr) P3");
            aj.potReativaP3 = (Double) p.getValor();
            p = parametros.get("Pot. Reativa (kVAr) P4");
            aj.potReativaP4 = (Double) p.getValor();
        }

        ajustesAlimentador.put(alimentador, aj);
    }

    private void setaConsumidoresAlimentador(Alimentador alimentador) {
        int numConsumidores = 0;
        for (Transformador trafo : alimentador.getTransformadores()) {
            numConsumidores += trafo.getNumConsumidores();
        }
        alimentador.setNumConsumidores(numConsumidores);
    }

    private boolean corrigeCargasAlimentador(Alimentador alimentador, PatamarDeCarga patamarAtual, Cenario cenario, boolean apenasAl) {
        if (alimentador.getTipoAjuste() == null) return false;
        if (apenasAl && ajustesAlimentador.get(alimentador).getPQMedido(patamarAtual.getPatamar()) == null) return false;
        if (!apenasAl) {
            //corrigeCargasAlimentador(alimentador, patamarAtual, cenario, true);
        }
        double maxDif = 0.0;
        int nIteracoes = 0;
        Chave med = null;
        EncontraMedidores encontraMedidores = new EncontraMedidores(patamarAtual.getPatamar());
        if (apenasAl) {
            encontraMedidores.medidores.clear();
            encontraMedidores.medidores.add(alimentador.getTrechoRedeInicial().getChave());
        } else {
            Navegador.percorreArvore(alimentador.getTrechoRedeInicial(), null, SemAcaoPreFixada.INSTANCE, encontraMedidores, permiteAlsDiferentes, getErros());
        }
        if (!apenasAl && encontraMedidores.medidores.isEmpty()) return false;
        do {
            maxDif = 0.0;
            for (Chave medidor : encontraMedidores.medidores) {
                Double[] PQMedido = null;
                Double iMedido = null;
                if (medidor.getTipoChave().getTipo().contains("DISJ")) {
                    PQMedido = ajustesAlimentador.get(alimentador).getPQMedido(patamarAtual.getPatamar());
                    iMedido = ajustesAlimentador.get(alimentador).getCorrenteMedida(patamarAtual.getPatamar());
                } else {
                    iMedido = medidor.getIMedido(patamarAtual.getPatamar());
                }
                if (PQMedido != null) {
                    List<Double[]> l = demandasAl.get(alimentador);

                    Double[] PCalculado = l.get(0);
                    Double[] QCalculado = l.get(1);

                    double difP = PQMedido[0] - (PCalculado[0] + PCalculado[1] + PCalculado[2]);
                    double difQ = PQMedido[1] - (QCalculado[0] + QCalculado[1] + QCalculado[2]);
                    if (Math.max(Math.abs(difP) / PQMedido[0], Math.abs(difQ) / PQMedido[1]) > maxDif) {
                        maxDif = Math.max(Math.abs(difP) / PQMedido[0], Math.abs(difQ) / PQMedido[1]);
                        med = medidor;
                    }
                } else if (iMedido != null) {
                    Double[] iCalculado = medidor.getTrechoRede().getMedidaEletrica(CORRENTE).getValorArray();
                    Double maxICalculado = Math.max(Math.max(iCalculado[0], iCalculado[1]), iCalculado[2]);
                    if (Math.abs(iMedido - maxICalculado) / iMedido > maxDif) {
                        maxDif = Math.abs(iMedido - maxICalculado) / iMedido;
                        med = medidor;
                    }
                }
            }
            if (maxDif < 0.03) {
                break;
            }
            if (!apenasAl) {
                getAlertas().add("<br />[AJUSTE] Iniciando iteração " + nIteracoes);
            }
            for (Chave medidor : encontraMedidores.medidores) {
                TipoCargas tipoAjuste = null;
                if (ajustesAlimentador.get(alimentador) != null &&
                        ajustesAlimentador.get(alimentador).tipoAjuste != null &&
                        !ajustesAlimentador.get(alimentador).tipoAjuste.equalsIgnoreCase("Padrão do Medidor")) {
                    tipoAjuste = TipoCargas.fromString(ajustesAlimentador.get(alimentador).tipoAjuste);
                } else {
                    if (medidor.getTipoChave().getTipo().contains("DISJ")) {
                            tipoAjuste = TipoCargas.fromString(alimentador.getTipoAjuste());
                    } else {
                        tipoAjuste = TipoCargas.fromString(medidor.getTipoAjusteDemanda());
                    }
                }
                CalculoSomatorios calculoSomatorios = new CalculoSomatorios(tipoAjuste, patamarAtual.getPatamar(), apenasAl);
                Navegador.percorreArvore(alimentador.getTrechoRedeInicial(), null, SemAcaoPreFixada.INSTANCE, calculoSomatorios, permiteAlsDiferentes, getErros());
                if (precisaCorrigirETEP(alimentador.getTrechoRedeInicial(), this.patamarAtual)) {
                    if (tipoAjuste != TipoCargas.ET_EP) {
                        getAlertas().add("[AJUSTE] Diferença muito grande para ajustar apenas em cargas " +
                                tipoAjuste.toString() + ", alterando ajuste para ET_EP.");
                        tipoAjuste = TipoCargas.ET_EP;
                        calculoSomatorios = new CalculoSomatorios(tipoAjuste, patamarAtual.getPatamar(), apenasAl);
                        Navegador.percorreArvore(alimentador.getTrechoRedeInicial(), null, SemAcaoPreFixada.INSTANCE, calculoSomatorios, permiteAlsDiferentes, getErros());
                    } else {
                        getAlertas().add("[AJUSTE] Diferença muito grande para ajustar nas cargas que são competência do medidor.");
                    }
                }
                AjusteCargas ajusteCargas = new AjusteCargas(patamarAtual.getPatamar(), tipoAjuste, medidor, apenasAl);
                Navegador.percorreArvore(alimentador.getTrechoRedeInicial(), null, ajusteCargas, SemAcaoPosFixada.INSTANCE, permiteAlsDiferentes, getErros());
                if (medidor.getAjusteCarga() != null && !apenasAl) {
                    getAlertas().add(medidor.getAjusteCarga().toString());
                }
                calculaFluxoPotenciaAlimentador(alimentador);
            }
            nIteracoes++;
        } while (nIteracoes < 4);

        if (nIteracoes >= 4 && maxDif >= 0.01 && !apenasAl) {
            for (Transformador transformador : alimentador.getTransformadores()) {
                transformador.setDifIAtivaCorrecao(null);
                transformador.setDifIReativaCorrecao(null);
            }
            ajustarCargaTransformadores(alimentador, patamarAtual, cenario);
            calculaFluxoPotenciaAlimentador(alimentador);
            if (isMedidor(alimentador.getTrechoRedeInicial().getChave(), patamarAtual.getPatamar())) {
                getAlertas().add("Correção de cargas não convergiu. Correção feita apenas pelas medições no alimentador...");
                corrigeCargasAlimentador(alimentador, patamarAtual, cenario, true);
            } else {
                getAlertas().add("Não convergiu. Alcançou a precisão de " + nf.format(maxDif * 100) + "% no medidor " +
                        med.getTipoChave().getTipo() + " " + med.getCodigo() + " em " + nIteracoes + " iterações.");
            }
        } else {
            getAlertas().add("Corrigiu cargas e conseguiu alcançar a precisão de " + nf.format(maxDif * 100) + "% no medidor " +
                    med.getTipoChave().getTipo() + " " + med.getCodigo() + " em " + nIteracoes + " iterações.");
        }
        return false;
    }

    private boolean precisaCorrigirETEP(TrechoRede trechoRedeInicial, int patamar) {
        Double p = null, q = null;
        Double[] totalP = null, totalQ = null;
        if (trechoRedeInicial.getChave() != null && isMedidor(trechoRedeInicial.getChave(), patamar)) {
            Chave chave = trechoRedeInicial.getChave();
            if (chave.getTipoChave().getTipo().contains("DISJ")) {
                Double[] med = ajustesAlimentador.get(trechoRedeInicial.getAlimentador()).getPQMedido(patamar);
                if (med != null) {
                    p = med[0];
                    q = med[1];
                    totalP = trechoRedeInicial.getAlimentador().getpTotalCalculado();
                    totalQ = trechoRedeInicial.getAlimentador().getqTotalCalculado();
                }
            }

        }
        Double[] PCalculado = trechoRedeInicial.getMedidaEletrica(POTENCIA_ATIVA).getValorArray();
        Double[] QCalculado = trechoRedeInicial.getMedidaEletrica(POTENCIA_REATIVA).getValorArray();

        double somaPCalculado = PCalculado[0] + PCalculado[1] + PCalculado[2];
        double somaQCalculado = QCalculado[0] + QCalculado[1] + QCalculado[2];

        Double[] PMedidoArr = new Double[]{(p * (PCalculado[0] / somaPCalculado)), (p * (PCalculado[1] / somaPCalculado)), (p * (PCalculado[2] / somaPCalculado))};
        Double[] QMedidoArr = new Double[]{(q * (QCalculado[0] / somaQCalculado)), (q * (QCalculado[1] / somaQCalculado)), (q * (QCalculado[2] / somaQCalculado))};

        Double[] difP = new Double[3];
        Double[] difQ = new Double[3];

        difP[0] = PMedidoArr[0] - PCalculado[0];
        difP[1] = PMedidoArr[1] - PCalculado[1];
        difP[2] = PMedidoArr[2] - PCalculado[2];

        difQ[0] = QMedidoArr[0] - QCalculado[0];
        difQ[1] = QMedidoArr[1] - QCalculado[1];
        difQ[2] = QMedidoArr[2] - QCalculado[2];

        if ((difP[0] < 0 || difP[1] < 0 || difP[2] < 0) &&
                (Math.abs(difP[0]) > totalP[0] ||
                        Math.abs(difP[1]) > totalP[1] ||
                        Math.abs(difP[2]) > totalP[2]))
        {
            return true;
        }
        return false;
    }

    private boolean isMedidor(Chave chave, int patamar) {
        if (chave.getTipoChave().getTipo().contains("DISJ")) {
            return ajustesAlimentador.get(chave.getTrechoRede().getAlimentador()).getPQMedido(patamar) != null;
        } else {
            return chave.isMedidor(patamar);
        }
    }

    private class EncontraMedidores implements AcaoPosFixada {

        private int patamar;

        public EncontraMedidores(int pat) {
            this.patamar = pat;
        }

        private Collection<Chave> medidores = new ArrayList<Chave>();

        @Override
        public Object executePos(INavegavel n, INavegavel anterior, Object params) {
            if (n instanceof TrechoRede && ((TrechoRede) n).getChave() != null && isMedidor(((TrechoRede) n).getChave(), this.patamar)) {
                medidores.add(((TrechoRede) n).getChave());
            }
            return null;
        }

        @Override
        public Object operaResultados(Object resultadoParcial, Object resultadoRamo) {
            return null;
        }

    }

    private class CalculoSomatorios implements AcaoPosFixada {

        public CalculoSomatorios(TipoCargas tipoCargasCorrigir, int pat) {
            this.tipoCargasCorrigir = tipoCargasCorrigir;
            this.patamar = pat;
        }

        public CalculoSomatorios(TipoCargas tipoCargasCorrigir, int pat, boolean correcaoInicialAl) {
            this.tipoCargasCorrigir = tipoCargasCorrigir;
            this.patamar = pat;
            this.correcaoInicial = correcaoInicialAl;
        }

        private TipoCargas tipoCargasCorrigir;
        private int patamar;
        private boolean correcaoInicial = false;

        @Override
        public Object executePos(INavegavel n, INavegavel anterior, Object params) {
            Double[] soma = (Double[]) params;
            if (soma == null) {
                soma = new Double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
            }
            if (n instanceof Barra) {
                Barra b = (Barra) n;
                if (b.getTransformador() != null) {
                    if (tipoCargasCorrigir == TipoCargas.ET_EP || b.getTransformador().getTipoEstacao().equals(tipoCargasCorrigir.toString())) {
                        soma[0] += b.getTransformador().getDemandaAtivaA();
                        soma[1] += b.getTransformador().getDemandaAtivaB();
                        soma[2] += b.getTransformador().getDemandaAtivaC();

                        soma[3] += b.getTransformador().getDemandaReativaA();
                        soma[4] += b.getTransformador().getDemandaReativaB();
                        soma[5] += b.getTransformador().getDemandaReativaC();

                        Double[] iAti = b.getMedidaEletrica(CORRENTE_ATIVA).getValorArray();
                        Double[] iReati = b.getMedidaEletrica(CORRENTE_REATIVA).getValorArray();
                        soma[6] += iAti[0];
                        soma[7] += iAti[1];
                        soma[8] += iAti[2];
                        soma[9] += iReati[0];
                        soma[10] += iReati[1];
                        soma[11] += iReati[2];
                    }
                }
            } else if (n instanceof TrechoRede && ((TrechoRede) n).getElevadorRebaixador() != null &&
                    ((TrechoRede) n).getElevadorRebaixador().getAtivado() != null && ((TrechoRede) n).getElevadorRebaixador().getAtivado())
            {
                soma[6] = soma[6] * ((TrechoRede) n).getElevadorRebaixador().getRelTransformacao();
                soma[7] = soma[7] * ((TrechoRede) n).getElevadorRebaixador().getRelTransformacao();
                soma[8] = soma[8] * ((TrechoRede) n).getElevadorRebaixador().getRelTransformacao();
                soma[9] = soma[9] * ((TrechoRede) n).getElevadorRebaixador().getRelTransformacao();
                soma[10] = soma[10] * ((TrechoRede) n).getElevadorRebaixador().getRelTransformacao();
                soma[11] = soma[11] * ((TrechoRede) n).getElevadorRebaixador().getRelTransformacao();
            } else if (n instanceof TrechoRede && ((TrechoRede) n).getChave() != null && isMedidor(((TrechoRede) n).getChave(), this.patamar)) {
                Chave chave = ((TrechoRede) n).getChave();
                if (chave.getTipoChave().getTipo().contains("DISJ")) {
                    chave.getTrechoRede().getAlimentador().setpTotalCalculado(new Double[] { soma[0], soma[1], soma[2] });
                    chave.getTrechoRede().getAlimentador().setqTotalCalculado(new Double[] { soma[3], soma[4], soma[5] });
                    chave.getTrechoRede().getAlimentador().setiAtivaCalculada(new Double[]{soma[6], soma[7], soma[8]});
                    chave.getTrechoRede().getAlimentador().setiReativaCalculada(new Double[]{soma[9], soma[10], soma[11]});
                } else {
                    chave.setiAtivaCalculada(new Double[]{soma[6], soma[7], soma[8]});
                    chave.setiReativaCalculada(new Double[]{soma[9], soma[10], soma[11]});
                    if (!correcaoInicial) {
                        soma = new Double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
                    }
                }
            }
            return soma;
        }

        @Override
        public Object operaResultados(Object resultadoParcial, Object resultadoRamo) {
            Double[] opResParcial = (Double[]) resultadoParcial;
            if (opResParcial == null) {
                opResParcial = new Double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
            }
            Double[] opResRamo = (Double[]) resultadoRamo;
            if (opResRamo == null) {
                opResRamo = new Double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
            }
            return new Double[] {
                    opResParcial[0] + opResRamo[0], opResParcial[1] + opResRamo[1], opResParcial[2] + opResRamo[2],
                    opResParcial[3] + opResRamo[3], opResParcial[4] + opResRamo[4], opResParcial[5] + opResRamo[5],
                    opResParcial[6] + opResRamo[6], opResParcial[7] + opResRamo[7], opResParcial[8] + opResRamo[8],
                    opResParcial[9] + opResRamo[9], opResParcial[10] + opResRamo[10], opResParcial[11] + opResRamo[11]};
        }
    }

    private class AjusteCargas implements AcaoPreFixada {

        private int patamar;
        private TipoCargas tipoCargasCorrigir;
        private Chave medidor;
        private boolean correcaoInicialAl = false;

        public AjusteCargas(int patamarAtual, TipoCargas tipoCargasCorrigir, Chave medidor) {
            this.patamar = patamarAtual;
            this.tipoCargasCorrigir = tipoCargasCorrigir;
            this.medidor = medidor;
        }

        public AjusteCargas(int patamarAtual, TipoCargas tipoCargasCorrigir, Chave medidor, boolean correcaoInicialAl) {
            this.patamar = patamarAtual;
            this.tipoCargasCorrigir = tipoCargasCorrigir;
            this.medidor = medidor;
            this.correcaoInicialAl = correcaoInicialAl;
        }

        @Override
        public Object executePre(INavegavel n, INavegavel anterior, Object params) {
            DadosAjusteCarga dados = (DadosAjusteCarga) params;
            if (n instanceof TrechoRede) {
                TrechoRede trecho = (TrechoRede) n;
                if (trecho.getChave() != null && isMedidor(trecho.getChave(), this.patamar)) {
                    Chave chave = trecho.getChave();
                    if (chave == medidor) {
                        dados = new DadosAjusteCarga();
                        Double p = null, q = null;
                        Double i = null;
                        if (chave.getTipoChave().getTipo().contains("DISJ")) {
                            Double[] med = ajustesAlimentador.get(trecho.getAlimentador()).getPQMedido(patamar);
                            i = ajustesAlimentador.get(trecho.getAlimentador()).getCorrenteMedida(patamar);
                            if (med != null) {
                                p = med[0];
                                q = med[1];
                                dados.totalP = trecho.getAlimentador().getpTotalCalculado();
                                dados.totalQ = trecho.getAlimentador().getqTotalCalculado();
                            } else if (i != null) {
                                dados.totalIAt = trecho.getAlimentador().getiAtivaCalculada();
                                dados.totalIReat = trecho.getAlimentador().getiReativaCalculada();
                            }
                        } else {
                            i = chave.getIMedido(patamar);
                            if (i != null) {
                                dados.totalIAt = chave.getiAtivaCalculada();
                                dados.totalIReat = chave.getiReativaCalculada();
                            }
                        }
                        dados.medidor = medidor;
                        medidor.setAjusteCarga(dados);
                        if (p != null && q != null) {

                            Double[] PCalculado = trecho.getMedidaEletrica(POTENCIA_ATIVA).getValorArray();
                            Double[] QCalculado = trecho.getMedidaEletrica(POTENCIA_REATIVA).getValorArray();

                            double somaPCalculado = PCalculado[0] + PCalculado[1] + PCalculado[2];
                            double somaQCalculado = QCalculado[0] + QCalculado[1] + QCalculado[2];

                            Double[] PMedidoArr = new Double[]{(p * (PCalculado[0] / somaPCalculado)), (p * (PCalculado[1] / somaPCalculado)), (p * (PCalculado[2] / somaPCalculado))};
                            Double[] QMedidoArr = new Double[]{(q * (QCalculado[0] / somaQCalculado)), (q * (QCalculado[1] / somaQCalculado)), (q * (QCalculado[2] / somaQCalculado))};

                            Double[] difP = new Double[3];
                            Double[] difQ = new Double[3];

                            difP[0] = PMedidoArr[0] - PCalculado[0];
                            difP[1] = PMedidoArr[1] - PCalculado[1];
                            difP[2] = PMedidoArr[2] - PCalculado[2];

                            difQ[0] = QMedidoArr[0] - QCalculado[0];
                            difQ[1] = QMedidoArr[1] - QCalculado[1];
                            difQ[2] = QMedidoArr[2] - QCalculado[2];

                            dados.difP = difP;
                            dados.difQ = difQ;

                            dados.PQMedido = new Double[] { p, q };
                            dados.PQCalculado = new Double[] { somaPCalculado, somaQCalculado };

                        } else if (i != null) {

                            Double[] difIAt = new Double[3];
                            Double[] difIReat = new Double[3];

                            Double[] iCalculado = trecho.getMedidaEletrica(CORRENTE).getValorArray();

                            Double maxCorrente = Math.max(Math.max(iCalculado[0], iCalculado[1]), iCalculado[2]);
                            Double percAjuste = i / maxCorrente;
                            Double[] iArr = new Double[]{iCalculado[0] * percAjuste, iCalculado[1] * percAjuste, iCalculado[2] * percAjuste};


                            Double[] iAtivoMedido = new Double[3];
                            Double[] iReativoMedido = new Double[3];
                            Double[] anguloCorrente = trecho.getMedidaEletrica(ANGULO_CORRENTE).getValorArray();
                            iAtivoMedido[0] = iArr[0] * Math.cos(anguloCorrente[0]);
                            iAtivoMedido[1] = iArr[1] * Math.cos(anguloCorrente[1]);
                            iAtivoMedido[2] = iArr[2] * Math.cos(anguloCorrente[2]);

                            iReativoMedido[0] = iArr[0] * Math.sin(anguloCorrente[0]);
                            iReativoMedido[1] = iArr[1] * Math.sin(anguloCorrente[1]);
                            iReativoMedido[2] = iArr[2] * Math.sin(anguloCorrente[2]);

                            Double[] iAtivoCalculado = trecho.getMedidaEletrica(CORRENTE_ATIVA).getValorArray();
                            Double[] iReativoCalculado = trecho.getMedidaEletrica(CORRENTE_REATIVA).getValorArray();

                            difIAt[0] = iAtivoMedido[0] - iAtivoCalculado[0];
                            difIAt[1] = iAtivoMedido[1] - iAtivoCalculado[1];
                            difIAt[2] = iAtivoMedido[2] - iAtivoCalculado[2];

                            difIReat[0] = iReativoMedido[0] - iReativoCalculado[0];
                            difIReat[1] = iReativoMedido[1] - iReativoCalculado[1];
                            difIReat[2] = iReativoMedido[2] - iReativoCalculado[2];

                            dados.difIAt = difIAt;
                            dados.difIReat = difIReat;

                            dados.iMedido = i;
                            dados.iCalculado = maxCorrente;

                        }
                    } else {
                        if (!correcaoInicialAl) {
                            dados = null;
                        }
                    }
                } else if (trecho.getElevadorRebaixador() != null && trecho.getElevadorRebaixador().getAtivado() != null &&
                        trecho.getElevadorRebaixador().getAtivado() && trecho.getElevadorRebaixador().getRelTransformacao() != null &&
                        dados != null && dados.difIAt != null && dados.difIReat != null) {
                    DadosAjusteCarga dadosNovo = new DadosAjusteCarga();
                    dadosNovo.difDistribuidaP = dados.difDistribuidaP;
                    dadosNovo.difDistribuidaQ = dados.difDistribuidaQ;
                    dadosNovo.difDistribuidaAt = dados.difDistribuidaAt;
                    dadosNovo.difDistribuidaReat = dados.difDistribuidaReat;
                    dadosNovo.difIAt = new Double[] {
                            dados.difIAt[0] / trecho.getElevadorRebaixador().getRelTransformacao(),
                            dados.difIAt[1] / trecho.getElevadorRebaixador().getRelTransformacao(),
                            dados.difIAt[2] / trecho.getElevadorRebaixador().getRelTransformacao()
                    };
                    dadosNovo.difIReat = new Double[] {
                            dados.difIReat[0] / trecho.getElevadorRebaixador().getRelTransformacao(),
                            dados.difIReat[1] / trecho.getElevadorRebaixador().getRelTransformacao(),
                            dados.difIReat[2] / trecho.getElevadorRebaixador().getRelTransformacao() } ;
                    dadosNovo.difP = dados.difP;
                    dadosNovo.difQ = dados.difQ;
                    dadosNovo.medidor = dados.medidor;
                    dadosNovo.totalIAt = dados.totalIAt;
                    dadosNovo.totalIReat = dados.totalIReat;
                    dadosNovo.totalP = dados.totalP;
                    dadosNovo.totalQ = dados.totalQ;
                    dadosNovo.iMedido = dados.iMedido;
                    dadosNovo.iCalculado = dados.iCalculado;
                    dadosNovo.PQMedido = dados.PQMedido;
                    dadosNovo.PQCalculado = dados.PQCalculado;
                    dados = dadosNovo;
                }
            }
            if (n instanceof Barra) {
                Barra v = (Barra) n;
                if (v.getTransformador() != null && dados != null) {
                    Transformador t = v.getTransformador();

                    if (dados.difIReat != null && dados.difIAt != null) {
                        Double[] difIat = new Double[] { 0.0, 0.0, 0.0 };
                        Double[] difIReat = new Double[] { 0.0, 0.0, 0.0 };
                        Double[] iAtiva = v.getMedidaEletrica(CORRENTE_ATIVA).getValorArray();
                        Double[] iReativa = v.getMedidaEletrica(CORRENTE_REATIVA).getValorArray();
                        if (tipoCargasCorrigir == TipoCargas.ET_EP || t.getTipoEstacao().equals(tipoCargasCorrigir.toString())) {
                            difIat[0] += dados.difIAt[0] * (iAtiva[0] / dados.totalIAt[0]);
                            difIat[1] += dados.difIAt[1] * (iAtiva[1] / dados.totalIAt[1]);
                            difIat[2] += dados.difIAt[2] * (iAtiva[2] / dados.totalIAt[2]);

                            difIReat[0] += dados.difIReat[0] * (iReativa[0] / dados.totalIReat[0]);
                            difIReat[1] += dados.difIReat[1] * (iReativa[1] / dados.totalIReat[1]);
                            difIReat[2] += dados.difIReat[2] * (iReativa[2] / dados.totalIReat[2]);
                        }

                        dados.difDistribuidaAt[0] += difIat[0];
                        dados.difDistribuidaAt[1] += difIat[1];
                        dados.difDistribuidaAt[2] += difIat[2];

                        dados.difDistribuidaReat[0] += difIReat[0];
                        dados.difDistribuidaReat[1] += difIReat[1];
                        dados.difDistribuidaReat[2] += difIReat[2];

                        if (t.getDifIAtivaCorrecao() != null) {
                            difIat[0] += t.getDifIAtivaCorrecao()[0];
                            difIat[1] += t.getDifIAtivaCorrecao()[1];
                            difIat[2] += t.getDifIAtivaCorrecao()[2];
                        }
                        t.setDifIAtivaCorrecao(difIat);

                        if (t.getDifIReativaCorrecao() != null) {
                            difIReat[0] += t.getDifIReativaCorrecao()[0];
                            difIReat[1] += t.getDifIReativaCorrecao()[1];
                            difIReat[2] += t.getDifIReativaCorrecao()[2];
                        }
                        t.setDifIReativaCorrecao(difIReat);

                    } else if (dados.difP != null && dados.difQ != null) {

                        if (tipoCargasCorrigir == TipoCargas.ET_EP || t.getTipoEstacao().equals(tipoCargasCorrigir.toString())) {

                            double dAtA = t.getDemandaAtivaA();
                            double dAtB = t.getDemandaAtivaB();
                            double dAtC = t.getDemandaAtivaC();

                            double dReatA = t.getDemandaReativaA();
                            double dReatB = t.getDemandaReativaB();
                            double dReatC = t.getDemandaReativaC();

                            double[] difP = new double[3];
                            double[] difQ = new double[3];

                            difP[0] = dados.difP[0] * (dAtA / dados.totalP[0]);
                            difP[1] = dados.difP[1] * (dAtB / dados.totalP[1]);
                            difP[2] = dados.difP[2] * (dAtC / dados.totalP[2]);

                            difQ[0] = dados.difQ[0] * (dReatA / dados.totalQ[0]);
                            difQ[1] = dados.difQ[1] * (dReatB / dados.totalQ[1]);
                            difQ[2] = dados.difQ[2] * (dReatC / dados.totalQ[2]);

                            t.setDemandaAtivaA(dAtA + difP[0]);
                            t.setDemandaAtivaB(dAtB + difP[1]);
                            t.setDemandaAtivaC(dAtC + difP[2]);

                            t.setDemandaReativaA(dReatA + difQ[0]);
                            t.setDemandaReativaB(dReatB + difQ[1]);
                            t.setDemandaReativaC(dReatC + difQ[2]);

                            dados.difDistribuidaP[0] += difP[0];
                            dados.difDistribuidaP[1] += difP[1];
                            dados.difDistribuidaP[2] += difP[2];

                            dados.difDistribuidaQ[0] += difQ[0];
                            dados.difDistribuidaQ[1] += difQ[1];
                            dados.difDistribuidaQ[2] += difQ[2];
                        }
                    }
                }
            }
            return dados;
        }
    }



    private boolean calculaFluxoPotenciaAlimentador(Alimentador alimentador) {
        TrechoRede source = alimentador.getTrechoRedeInicial();
        AcaoPreFixada dadosTr = new CalculaDadosTrafos(this.patamarAtual, this.anoProjecaoAtual);
        AcaoPosFixada calculaCorrente = new CalculaCorrente();
        CalculaTensao calculaTensao = new CalculaTensao(this.patamarAtual, this.anoProjecaoAtual);
        CalculaTensaoNominal calculaTensaoNominal = new CalculaTensaoNominal();
        int nIteracao = 0;
        try {
            do {
                calculaTensao.rodadenovo = false;
                double tensaoSub = VALOR_TENSAO_ALIMENTADOR;
                ObjTensao p = new ObjTensao();
                p.vNominal = alimentador.getTransformadorSE().getTensaoNominalSecundaria();
                p.vA = tensaoSub;
                p.vB = tensaoSub;
                p.vC = tensaoSub;
                p.anguloA = 0;
                p.anguloB = 0;
                p.anguloC = 0;
                if (nIteracao == 0) {
                    Navegador.percorreArvore(source, p, calculaTensaoNominal, SemAcaoPosFixada.INSTANCE, permiteAlsDiferentes, getErros());
                }
                Navegador.percorreArvore(source, null, dadosTr, calculaCorrente, permiteAlsDiferentes, getErros());
                Navegador.percorreArvore(source, p, calculaTensao, SemAcaoPosFixada.INSTANCE, permiteAlsDiferentes, getErros());
                nIteracao++;
                if (nIteracao == 1) {
                    calculaTensao.rodadenovo = true;
                }
            } while (calculaTensao.rodadenovo && nIteracao < 5 && !calculaTensao.divergente);
        } catch (IllegalStateException e) {
            return false;
        }
        setaPQ(source);
        return calculaTensao.divergente;
    }

    private void transformaTensaoDeFase(Alimentador alimentador) {
        double RAIZ_3 = Math.sqrt(3);
        for (Barra barra : alimentador.getBarras()) {
            if (barra.getMedidaEletrica(MODULO_TENSAO) != null) {
                MedidaEletrica tensao = barra.getMedidaEletrica(MODULO_TENSAO);
                Double[] arr = tensao.getValorArray();
                MedidaEletrica tensaoPU = new MedidaEletrica(MODULO_TENSAO_PU, new Double[] { arr[0] / barra.getTensaoNominal(),
                        arr[1] / barra.getTensaoNominal(),
                        arr[2] / barra.getTensaoNominal() }, "p.u.", true);
                tensaoPU.setValorResumo(tensaoPU.getValorMinimo());
                barra.adicionaMedidaEletrica(MODULO_TENSAO_PU, tensaoPU);
                tensao.setValor(new Double[] { arr[0] / RAIZ_3, arr[1] / RAIZ_3, arr[2] / RAIZ_3 });
                tensao.setValorResumo(tensao.getValorMinimo());
            }
        }
        for (TrechoRede trecho : alimentador.getTrechosRede()) {
            if (trecho.getMedidaEletrica(MODULO_TENSAO) != null) {
                MedidaEletrica tensao = trecho.getMedidaEletrica(MODULO_TENSAO);
                Double[] arr = tensao.getValorArray();
                tensao.setValor(new Double[] { arr[0] / RAIZ_3, arr[1] / RAIZ_3, arr[2] / RAIZ_3 });
                tensao.setValorResumo(tensao.getValorMinimo());

                tensao = trecho.getMedidaEletrica(MODULO_TENSAO_INICIO);
                if (tensao != null) {
                    arr = tensao.getValorArray();
                    tensao.setValor(new Double[]{arr[0] / RAIZ_3, arr[1] / RAIZ_3, arr[2] / RAIZ_3});
                }

                tensao = trecho.getMedidaEletrica(MODULO_TENSAO_FIM);
                if (tensao != null) {
                    arr = tensao.getValorArray();
                    tensao.setValor(new Double[]{arr[0] / RAIZ_3, arr[1] / RAIZ_3, arr[2] / RAIZ_3});
                }
            }
        }
    }

    @Override
    public String getNome() {
        return "Fluxo de Potência";
    }

    @Override
    public void setNome(String nome) {
    }

    @Override
    public String getDescricao() {
        return "Fluxo de Potência Trifásico";
    }

    @Override
    public void setDescricao(String descricao) {
    }

    public static class ObjTensao {

        double vNominal;
        double vA = 0.0;
        double vB = 0.0;
        double vC = 0.0;

        double anguloA = 0.0;
        double anguloB = 0.0;
        double anguloC = 0.0;

    }

    public static class CalculaTensaoNominal implements AcaoPreFixadaParalela {


        public Object executePre(INavegavel nav, INavegavel anterior, Object params) {
            ObjTensao oTensao = (ObjTensao) params;
            if (nav instanceof Barra) {
                ((Barra) nav).setTensaoNominal(oTensao.vNominal);
            } else if (nav instanceof TrechoRede) {
                TrechoRede condutor = (TrechoRede) nav;
                if (condutor.getElevadorRebaixador() != null && condutor.getElevadorRebaixador().getAtivado()) {
                    ObjTensao oTNovo = new ObjTensao();
                    double tensaoNominal = oTensao.vNominal >= 23 ? 13.8 : 23.1;
                    condutor.getElevadorRebaixador().setRelTransformacao(tensaoNominal / oTensao.vNominal);
                    oTNovo.vNominal = tensaoNominal;
                    oTensao = oTNovo;
                }
                condutor.setTensaoNominal(oTensao.vNominal);
            }
            return oTensao;
        }

        long tempoEspera = 0;

        @Override
        public long getTempoEspera() {
            return tempoEspera;
        }

        @Override
        public void setTempoEspera(long tempo) {
            tempoEspera = tempo;
        }

        @Override
        public void addTempoEspera(long tempo) {
            tempoEspera += tempo;
        }
    }


    /**
     * Mï¿½todo recursivo que percorre a ï¿½rvore de forma prï¿½-fixada
     * calculando a tensï¿½o em cada nï¿½ baseado na tensï¿½o de saï¿½da do
     * alimentador e nas quedas de tensï¿½o introduzidas pelos condutores.
     * param nav objeto da ï¿½rvore onde se vai navegar.
     * param tensao tensao naquele nï¿½
     */
    public static class CalculaTensao implements AcaoPreFixadaParalela {

        static boolean rodadenovo = false;
        static boolean divergente = false;
        private Integer patamar;
        private Integer ano;

        public CalculaTensao(Integer patamar, Integer ano) {
            this.patamar = patamar;
            this.ano = ano;
        }

        public Object executePre(INavegavel nav, INavegavel anterior, Object params) {
            ObjTensao oTensao = (ObjTensao) params;
            if (nav instanceof Barra) {
                Barra v = (Barra) nav;
                //v.setTensaoNominal(oTensao.vNominal);
                if (v.getFonteGeracao() != null) {
                    MedidaEletrica att = new MedidaEletrica(MODULO_TENSAO, new Double[]{oTensao.vA, oTensao.vB, oTensao.vC}, "kV", true);
                    att.setValorResumo(att.getValorMinimo());
                    v.adicionaMedidaEletrica(MODULO_TENSAO, att);
                    att = new MedidaEletrica(ANGULO_TENSAO, new Double[]{oTensao.anguloA, oTensao.anguloB, oTensao.anguloC}, "°", true);
                    v.adicionaMedidaEletrica(ANGULO_TENSAO, att);
                } else {
                    MedidaEletrica att = v.getMedidaEletrica(MODULO_TENSAO);
                    if (att == null) {
                        att = new MedidaEletrica(MODULO_TENSAO, new Double[]{oTensao.vA, oTensao.vB, oTensao.vC}, "kV", true);
                        att.setValorResumo(att.getValorMinimo());
                        v.adicionaMedidaEletrica(MODULO_TENSAO, att);
                        if ((v.getTensaoNominal() - oTensao.vA) > VALOR_PERMISSIVEL) {
                            this.rodadenovo = true;
                        }
                    } else {
                        double VAntigo = att.getValorA();
                        if ((VAntigo - oTensao.vA) > VALOR_PERMISSIVEL) {
                            this.rodadenovo = true;
                        }
                        if (oTensao.vA < (0.6 * v.getTensaoNominal())) {
                            divergente = true;
                            //Debug.debug("Divervente: " + nav.toString() + " " + oTensao.vA);
                        }
                        if (oTensao.vB < (0.6 * v.getTensaoNominal())) {
                            divergente = true;
                            //Debug.debug("Divervente: " + nav.toString() + " " + oTensao.vA);
                        }
                        if (oTensao.vC < (0.6 * v.getTensaoNominal())) {
                            divergente = true;
                            //Debug.debug("Divervente: " + nav.toString() + " " + oTensao.vA);
                        }
                        att.setValor(new Double[]{oTensao.vA, oTensao.vB, oTensao.vC});
                        att.setValorResumo(att.getValorMinimo());
                    }
                    att = v.getMedidaEletrica(ANGULO_TENSAO);
                    if (att == null) {
                        att = new MedidaEletrica(ANGULO_TENSAO, new Double[]{oTensao.anguloA, oTensao.anguloB, oTensao.anguloC}, "°", true);
                        v.adicionaMedidaEletrica(ANGULO_TENSAO, att);
                    } else {
                        att.setValor(new Double[]{oTensao.anguloA, oTensao.anguloB, oTensao.anguloC});
                    }
                }
            } else if (nav instanceof TrechoRede) {
                TrechoRede condutor = (TrechoRede) nav;
                if (condutor.getRegulador() != null) {
                    Regulador regulador = condutor.getRegulador();
                    regulador.setAnoSimulado(ano);
                    regulador.setPatamarSimulado(patamar);
                    MedidaEletrica attIAtiva = condutor.getMedidaEletrica(CORRENTE_ATIVA);
                    MedidaEletrica attIReativa = condutor.getMedidaEletrica(CORRENTE_REATIVA);
                    MedidaEletrica attP = condutor.getMedidaEletrica(POTENCIA_ATIVA);
                    MedidaEletrica attQ = condutor.getMedidaEletrica(POTENCIA_REATIVA);
                    if (attIAtiva != null && attIReativa != null && attP != null && attQ != null) {
                        try {
                            double tensaoReferencia = regulador.getTensao(this.patamar);
                            ObjTensao oTNovo = new ObjTensao();
                            oTNovo.vNominal = oTensao.vNominal;
                            oTNovo.vA = (tensaoReferencia > oTensao.vA * 1.1 ? oTensao.vA * 1.1 : tensaoReferencia);
                            oTNovo.vB = (tensaoReferencia > oTensao.vB * 1.1 ? oTensao.vB * 1.1 : tensaoReferencia);
                            oTNovo.vC = (tensaoReferencia > oTensao.vC * 1.1 ? oTensao.vC * 1.1 : tensaoReferencia);
                            oTNovo.anguloA = oTensao.anguloA;
                            oTNovo.anguloB = oTensao.anguloB;
                            oTNovo.anguloC = oTensao.anguloC;
                            condutor.getRegulador().setRelacaoTransformacao(new Double[] { oTNovo.vA / oTensao.vA, oTNovo.vB / oTensao.vB, oTNovo.vC / oTensao.vC});
                            oTensao = oTNovo;
                        } catch (Exception e) {
                            ObjTensao oTNovo = new ObjTensao();
                            double tensaoNominal = oTensao.vNominal;
                            oTNovo.vNominal = oTensao.vNominal;
                            oTNovo.vA = (tensaoNominal > oTensao.vA * 1.1 ? oTensao.vA * 1.1 : tensaoNominal);
                            oTNovo.vB = (tensaoNominal > oTensao.vB * 1.1 ? oTensao.vB * 1.1 : tensaoNominal);
                            oTNovo.vC = (tensaoNominal > oTensao.vC * 1.1 ? oTensao.vC * 1.1 : tensaoNominal);
                            oTNovo.anguloA = oTensao.anguloA;
                            oTNovo.anguloB = oTensao.anguloB;
                            oTNovo.anguloC = oTensao.anguloC;
                            condutor.getRegulador().setRelacaoTransformacao(new Double[] { oTNovo.vA / oTensao.vA, oTNovo.vB / oTensao.vB, oTNovo.vC / oTensao.vC});
                            oTensao = oTNovo;
                        }
                    }
                    MedidaEletrica att = condutor.getMedidaEletrica(MODULO_TENSAO);
                    if (att == null) {
                        att = new MedidaEletrica(MODULO_TENSAO, new Double[]{oTensao.vA, oTensao.vB, oTensao.vC}, "kV", true);
                        att.setValorResumo(att.getValorMinimo());
                        condutor.adicionaMedidaEletrica(MODULO_TENSAO, att);
                    } else {
                        att.setValor(new Double[]{oTensao.vA, oTensao.vB, oTensao.vC});
                        att.setValorResumo(att.getValorMinimo());
                    }
                    att = condutor.getMedidaEletrica(ANGULO_TENSAO);
                    if (att == null) {
                        att = new MedidaEletrica(ANGULO_TENSAO, new Double[]{oTensao.anguloA, oTensao.anguloB, oTensao.anguloC}, "°", true);
                        condutor.adicionaMedidaEletrica(ANGULO_TENSAO, att);
                    } else {
                        att.setValor(new Double[]{oTensao.anguloA, oTensao.anguloB, oTensao.anguloC});
                    }
                } else if (condutor.getElevadorRebaixador() != null && condutor.getElevadorRebaixador().getAtivado()) {
                    ObjTensao oTNovo = new ObjTensao();
                    double tensaoNominal = oTensao.vNominal >= 23 ? 13.8 : 23.1;
                    condutor.getElevadorRebaixador().setRelTransformacao(tensaoNominal / oTensao.vNominal);
                    oTNovo.vNominal = tensaoNominal;
                    oTNovo.vA = oTensao.vA * condutor.getElevadorRebaixador().getRelTransformacao();
                    oTNovo.vB = oTensao.vB * condutor.getElevadorRebaixador().getRelTransformacao();
                    oTNovo.vC = oTensao.vC * condutor.getElevadorRebaixador().getRelTransformacao();
                    oTNovo.anguloA = oTensao.anguloA;
                    oTNovo.anguloB = oTensao.anguloB;
                    oTNovo.anguloC = oTensao.anguloC;
                    oTensao = oTNovo;
                }
                if (condutor.getChave() != null) {
                    condutor.getChave().setAnoSimulado(ano);
                    condutor.getChave().setPatamarSimulado(patamar);
                }
                //condutor.setTensaoNominal(oTensao.vNominal);
                if (condutor.isCondutor()) {
                    condutor.setAnoSimulado(ano);
                    condutor.setPatamarSimulado(patamar);
                    MedidaEletrica att = condutor.getMedidaEletrica(MODULO_TENSAO_INICIO);
                    if (att == null) {
                        att = new MedidaEletrica(MODULO_TENSAO_INICIO, new Double[]{oTensao.vA, oTensao.vB, oTensao.vC}, "kV", false);
                        condutor.adicionaMedidaEletrica(MODULO_TENSAO_INICIO, att);
                        if ((condutor.getTensaoNominal() - oTensao.vA) > VALOR_PERMISSIVEL) {
                            this.rodadenovo = true;
                        }
                    } else {
                        double VAntigo = att.getValorA();
                        if ((VAntigo - oTensao.vA) > VALOR_PERMISSIVEL) {
                            this.rodadenovo = true;
                        }
                        if (oTensao.vA < (0.6 * condutor.getTensaoNominal())) {
                            divergente = true;
                            //Debug.debug("Divervente: Aresta " + nav.toString() + " A: " + oTensao.vA);
                        }
                        if (oTensao.vB < (0.6 * condutor.getTensaoNominal())) {
                            divergente = true;
                            //Debug.debug("Divervente: Aresta " + nav.toString() + " B: " + oTensao.vB);
                        }
                        if (oTensao.vC < (0.6 * condutor.getTensaoNominal())) {
                            divergente = true;
                            //Debug.debug("Divervente: Aresta " + nav.toString() + " C: " + oTensao.vC);
                        }
                        att.setValor(new Double[]{oTensao.vA, oTensao.vB, oTensao.vC});
                        att.setValorResumo(att.getValorMinimo());
                    }
                    att = condutor.getMedidaEletrica(MODULO_TENSAO);
                    if (att == null) {
                        att = new MedidaEletrica(MODULO_TENSAO, new Double[]{oTensao.vA, oTensao.vB, oTensao.vC}, "kV", true);
                        att.setValorResumo(att.getValorMinimo());
                        condutor.adicionaMedidaEletrica(MODULO_TENSAO, att);
                    } else {
                        att.setValor(new Double[]{oTensao.vA, oTensao.vB, oTensao.vC});
                        att.setValorResumo(att.getValorMinimo());
                    }
                    att = condutor.getMedidaEletrica(ANGULO_TENSAO);
                    if (att == null) {
                        att = new MedidaEletrica(ANGULO_TENSAO, new Double[]{oTensao.anguloA, oTensao.anguloB, oTensao.anguloC}, "°", true);
                        condutor.adicionaMedidaEletrica(ANGULO_TENSAO, att);
                    } else {
                        att.setValor(new Double[]{oTensao.anguloA, oTensao.anguloB, oTensao.anguloC});
                    }
                    try {
                        MedidaEletrica quedaAtiva = condutor.getMedidaEletrica(QUEDA_ATIVA);
                        MedidaEletrica quedaReativa = condutor.getMedidaEletrica(QUEDA_REATIVA);
                        double vAtivoA = oTensao.vA * Math.cos(oTensao.anguloA);
                        double vReativoA = oTensao.vA * Math.sin(oTensao.anguloA);
                        double vAtivoB = oTensao.vB * Math.cos(oTensao.anguloB);
                        double vReativoB = oTensao.vB * Math.sin(oTensao.anguloB);
                        double vAtivoC = oTensao.vC * Math.cos(oTensao.anguloC);
                        double vReativoC = oTensao.vC * Math.sin(oTensao.anguloC);

                        double qAtivaA = quedaAtiva.getValorA();
                        double qAtivaB = quedaAtiva.getValorB();
                        double qAtivaC = quedaAtiva.getValorC();

                        double qReativaA = quedaReativa.getValorA();
                        double qReativaB = quedaReativa.getValorB();
                        double qReativaC = quedaReativa.getValorC();

                        vAtivoA = vAtivoA - qAtivaA;
                        vReativoA = vReativoA - qReativaA;
                        vAtivoB = vAtivoB - qAtivaB;
                        vReativoB = vReativoB - qReativaB;
                        vAtivoC = vAtivoC - qAtivaC;
                        vReativoC = vReativoC - qReativaC;

                        ObjTensao oTNovo = new ObjTensao();

                        oTNovo.vNominal = oTensao.vNominal;
                        oTNovo.vA = Math.sqrt((vAtivoA * vAtivoA) + (vReativoA * vReativoA));
                        oTNovo.vB = Math.sqrt((vAtivoB * vAtivoB) + (vReativoB * vReativoB));
                        oTNovo.vC = Math.sqrt((vAtivoC * vAtivoC) + (vReativoC * vReativoC));

                        oTNovo.anguloA = Math.atan(vReativoA / vAtivoA);
                        oTNovo.anguloB = Math.atan(vReativoB / vAtivoB);
                        oTNovo.anguloC = Math.atan(vReativoC / vAtivoC);

                        oTensao = oTNovo;
                    } catch (Exception e) {
                        //e.printStackTrace();
                        System.out.println();
                    }
                    att = condutor.getMedidaEletrica(MODULO_TENSAO_FIM);
                    if (att == null) {
                        att = new MedidaEletrica(MODULO_TENSAO_FIM, new Double[]{oTensao.vA, oTensao.vB, oTensao.vC}, "kV", false);
                        condutor.adicionaMedidaEletrica(MODULO_TENSAO_FIM, att);
                    } else {
                        att.setValor(new Double[]{oTensao.vA, oTensao.vB, oTensao.vC});
                    }
                }
            }
            if (Double.isNaN(oTensao.vA) || Double.isNaN(oTensao.vB) || Double.isNaN(oTensao.vC)) {
                erros.add("tensão é NaN");
            }
            if (Double.isInfinite(oTensao.vA) || Double.isInfinite(oTensao.vB) || Double.isInfinite(oTensao.vC)) {
                erros.add("tensão é infinita");
            }
            return oTensao;
        }

        long tempoEspera = 0;

        @Override
        public long getTempoEspera() {
            return tempoEspera;
        }

        @Override
        public void setTempoEspera(long tempo) {
            tempoEspera = tempo;
        }

        @Override
        public void addTempoEspera(long tempo) {
            tempoEspera += tempo;
        }
    }

    private Double[] calculaCorrenteAtivaReativa(Transformador trafo, Double[] p, Double[] q) {
        Double[] ret = new Double[6];

        Double[] tensao = trafo.getINavegavelAssociado().getMedidaEletrica(MODULO_TENSAO).getValorArray();
        Double[] anguloTensao = trafo.getINavegavelAssociado().getMedidaEletrica(ANGULO_TENSAO).getValorArray();
        Double[] anguloFatorPotencia = trafo.getINavegavelAssociado().getMedidaEletrica(ANGULO_FATOR_POTENCIA).getValorArray();

        double[] d = new double[3];

        d[0] = Math.sqrt(Math.pow(p[0], 2.0) +
                Math.pow(q[0], 2))
                / (tensao[0] / VALOR_RAIZ_DE_TRES);
        //corrente ativa
        ret[0] = d[0] * Math.cos(anguloTensao[0] - anguloFatorPotencia[0]);

        d[1] = Math.sqrt(Math.pow(p[1], 2.0) +
                Math.pow(q[1], 2))
                / (tensao[1] / VALOR_RAIZ_DE_TRES);
        //corrente ativa
        ret[1] = d[1] * Math.cos(anguloTensao[1] - anguloFatorPotencia[1]);
        if (p[2] == null || q[2] == null || tensao[2] == null) {
            System.out.println("null aqui!");
        }
        d[2] = Math.sqrt(Math.pow(p[2], 2.0) +
                Math.pow(q[2], 2))
                / (tensao[2] / VALOR_RAIZ_DE_TRES);
        //corrente ativa
        ret[2] = d[2] * Math.cos(anguloTensao[2] - anguloFatorPotencia[2]);

        //corrente reativa
        ret[3] = d[0] * Math.sin(anguloTensao[0] - anguloFatorPotencia[0]);
        ret[4] = d[1] * Math.sin(anguloTensao[1] - anguloFatorPotencia[1]);
        ret[5] = d[2] * Math.sin(anguloTensao[2] - anguloFatorPotencia[2]);

        return ret;
    }

    public static class CalculaDadosTrafos implements AcaoPreFixadaParalela {

        private int patamar = 1;
        private int ano;

        public CalculaDadosTrafos(int patamar, int ano) {
            this.patamar = patamar;
            this.ano = ano;
        }

        public Object executePre(INavegavel nav, INavegavel anterior, Object params) {
            if (nav instanceof Barra) {
                Barra v = (Barra) nav;
                if (v.getTransformador() != null) {
                    dadosDosTransformadores(v.getTransformador());
                } else if (v.getCapacitor() != null) {
                    v.getCapacitor().setBarra(v);
                    dadosDosCapacitores(v.getCapacitor());
                } else if (v.getFonteGeracao() instanceof FonteGeracao) {
                    v.getFonteGeracao().setBarra(v);
                    dadosDosGeradores(v.getFonteGeracao());
                }
            }
            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }

        /**
         * Método que calcula corrente ativa e reativa, corrente e angulo da corrente,
         * ângulo da potencia e angulo da tensï¿½o e seta os atributos derivados no transformador.
         *
         * @param comp tranformador que se quer calcular
         */
        private void dadosDosTransformadores(Transformador comp) {
            comp.setAnoSimulado(ano);
            comp.setPatamarSimulado(patamar);

            //item 3
            MedidaEletrica att = comp.getINavegavelAssociado().getMedidaEletrica(MODULO_TENSAO);
            if (att == null) {
                att = new MedidaEletrica(MODULO_TENSAO, new Double[]{comp.getBarra().getTensaoNominal(), comp.getBarra().getTensaoNominal(), comp.getBarra().getTensaoNominal()}, "kV", true);
                att.setValorResumo(att.getValorMinimo());
                comp.getINavegavelAssociado().adicionaMedidaEletrica(att.getNome(), att);
            }
            double tensaoA = att.getValorA();
            double tensaoB = att.getValorB();
            double tensaoC = att.getValorC();

            att = comp.getINavegavelAssociado().getMedidaEletrica(ANGULO_TENSAO);
            if (att == null) {
                att = new MedidaEletrica(ANGULO_TENSAO, new Double[]{0.0, 0.0, 0.0}, "°", true);
                comp.getINavegavelAssociado().adicionaMedidaEletrica(att.getNome(), att);
            }
            double anguloTensaoA = att.getValorA();
            double anguloTensaoB = att.getValorB();
            double anguloTensaoC = att.getValorC();


            //Definindo Demanda Ativa e Reativa de acordo com o nï¿½vel de tensï¿½o e perfil das cargas
            double pA, pB, pC;
            double qA, qB, qC;
            Double tensaoNominal = comp.getBarra().getTensaoNominal();
            double percImpConst = comp.getBarra().getAlimentador().getPercentualImpedanciaConstante() == null ? 0.0 : comp.getBarra().getAlimentador().getPercentualImpedanciaConstante();
            double percCorrenteConst = comp.getBarra().getAlimentador().getPercentualCorrenteConstante() == null ? 0.0 : comp.getBarra().getAlimentador().getPercentualCorrenteConstante();

            pA = ((100 - percCorrenteConst - percImpConst) +
                    (percCorrenteConst * tensaoA / tensaoNominal) +
                    (percImpConst * Math.pow(tensaoA / tensaoNominal, 2.0))
            ) * (comp.getDemandaAtivaA() == null ? 0.0 : comp.getDemandaAtivaA()) / 100.0;

            pB = ((100 - percCorrenteConst - percImpConst) +
                    (percCorrenteConst * tensaoB / tensaoNominal) +
                    (percImpConst * Math.pow(tensaoB / tensaoNominal, 2.0))
            ) * (comp.getDemandaAtivaB() == null ? 0.0 : comp.getDemandaAtivaB()) / 100.0;

            pC = ((100 - percCorrenteConst - percImpConst) +
                    (percCorrenteConst * tensaoC / tensaoNominal) +
                    (percImpConst * Math.pow(tensaoC / tensaoNominal, 2.0))
            ) * (comp.getDemandaAtivaC() == null ? 0.0 : comp.getDemandaAtivaC()) / 100.0;

            qA = ((100 - percCorrenteConst - percImpConst) +
                    (percCorrenteConst * tensaoA / tensaoNominal) +
                    (percImpConst * Math.pow(tensaoA / tensaoNominal, 2.0))
            ) * (comp.getDemandaReativaA() == null ? 0.0 : comp.getDemandaReativaA()) / 100.0;

            qB = ((100 - percCorrenteConst - percImpConst) +
                    (percCorrenteConst * tensaoB / tensaoNominal) +
                    (percImpConst * Math.pow(tensaoB / tensaoNominal, 2.0))
            ) * (comp.getDemandaReativaB() == null ? 0.0 : comp.getDemandaReativaB()) / 100.0;

            qC = ((100 - percCorrenteConst - percImpConst) +
                    (percCorrenteConst * tensaoC / tensaoNominal) +
                    (percImpConst * Math.pow(tensaoC / tensaoNominal, 2.0))
            ) * (comp.getDemandaReativaC() == null ? 0.0 : comp.getDemandaReativaC()) / 100.0;

            comp.getBarra().getMedidaEletrica(DEMANDA_ATIVA).setValorArray(new Double[] { pA, pB, pC });
            comp.getBarra().getMedidaEletrica(DEMANDA_REATIVA).setValorArray(new Double[] { qA, qB, qC });

            //fim item 3

            //item 4
            try {
                double anguloFatorPotenciaA = (pA == 0) ? 0 : Math.atan(qA / pA);
                double anguloFatorPotenciaB = (pB == 0) ? 0 : Math.atan(qB / pB);
                double anguloFatorPotenciaC = (pC == 0) ? 0 : Math.atan(qC / pC);
                att = new MedidaEletrica(ANGULO_FATOR_POTENCIA, new Double[]{anguloFatorPotenciaA, anguloFatorPotenciaB, anguloFatorPotenciaC}, "°", false);
                comp.getINavegavelAssociado().adicionaMedidaEletrica(att.getNome(), att);

                //fim item 4

                //item 5
            /*
              no item 5, precisa fazer o caso onde o no for do tipo banco de capacitor
            */
                double dA, dB, dC, iAtivaA, iAtivaB, iAtivaC, iReativaA, iReativaB, iReativaC;
                dA = Math.sqrt(Math.pow(pA, 2.0) +
                        Math.pow(qA, 2))
                        / (tensaoA / VALOR_RAIZ_DE_TRES);
                //corrente ativa
                iAtivaA = dA * Math.cos(anguloTensaoA - anguloFatorPotenciaA);

                dB = Math.sqrt(Math.pow(pB, 2.0) +
                        Math.pow(qB, 2))
                        / (tensaoB / VALOR_RAIZ_DE_TRES);
                //corrente ativa
                iAtivaB = dB * Math.cos(anguloTensaoB - anguloFatorPotenciaB);

                dC = Math.sqrt(Math.pow(pC, 2.0) +
                        Math.pow(qC, 2))
                        / (tensaoC / VALOR_RAIZ_DE_TRES);
                //corrente ativa
                iAtivaC = dC * Math.cos(anguloTensaoC - anguloFatorPotenciaC);
                if (comp.getDifIAtivaCorrecao() != null) {
                    iAtivaA += comp.getDifIAtivaCorrecao()[0];
                    iAtivaB += comp.getDifIAtivaCorrecao()[1];
                    iAtivaC += comp.getDifIAtivaCorrecao()[2];
                }

                //terminou o calculo corrente ativa e seta no componente transformador;
                att = new MedidaEletrica(CORRENTE_ATIVA, new Double[]{iAtivaA, iAtivaB, iAtivaC}, "A", false);
                att.setValorResumo(att.getValorMaximo());
                comp.getINavegavelAssociado().adicionaMedidaEletrica(att.getNome(), att);
                //pronto corrente ativa.

                //corrente reativa
                iReativaA = dA * Math.sin(anguloTensaoA - anguloFatorPotenciaA);
                iReativaB = dB * Math.sin(anguloTensaoB - anguloFatorPotenciaB);
                iReativaC = dC * Math.sin(anguloTensaoC - anguloFatorPotenciaC);
                if (comp.getDifIReativaCorrecao() != null) {
                    iReativaA += comp.getDifIReativaCorrecao()[0];
                    iReativaB += comp.getDifIReativaCorrecao()[1];
                    iReativaC += comp.getDifIReativaCorrecao()[2];
                }

                att = new MedidaEletrica(CORRENTE_REATIVA, new Double[]{iReativaA, iReativaB, iReativaC}, "A", false);
                att.setValorResumo(att.getValorMaximo());
                comp.getINavegavelAssociado().adicionaMedidaEletrica(att.getNome(), att);

                //calcula e seta a corrente
                double dCorrenteA = Math.sqrt((iAtivaA * iAtivaA) + (iReativaA * iReativaA));
                double dCorrenteB = Math.sqrt((iAtivaB * iAtivaB) + (iReativaB * iReativaB));
                double dCorrenteC = Math.sqrt((iAtivaC * iAtivaC) + (iReativaC * iReativaC));
                att = new MedidaEletrica(CORRENTE, new Double[]{dCorrenteA, dCorrenteB, dCorrenteC}, "A", true);
                att.setValorResumo(att.getValorMaximo());
                comp.getINavegavelAssociado().adicionaMedidaEletrica(att.getNome(), att);

                double anguloCorrenteA = (iAtivaA == 0.0) ? Math.toRadians(90.0) : Math.atan(iReativaA / iAtivaA);
                double anguloCorrenteB = (iAtivaB == 0.0) ? Math.toRadians(90.0) : Math.atan(iReativaB / iAtivaB);
                double anguloCorrenteC = (iAtivaC == 0.0) ? Math.toRadians(90.0) : Math.atan(iReativaC / iAtivaC);
                att = new MedidaEletrica(ANGULO_CORRENTE, new Double[]{anguloCorrenteA, anguloCorrenteB, anguloCorrenteC}, "º", true);
                comp.getINavegavelAssociado().adicionaMedidaEletrica(ANGULO_CORRENTE, att);
                if (Double.isNaN(dCorrenteA) || Double.isNaN(dCorrenteB) || Double.isNaN(dCorrenteC)) {
                    erros.add("Corrente do trafo é NaN.");
                }
                if (Double.isInfinite(dCorrenteA) || Double.isInfinite(dCorrenteB) || Double.isInfinite(dCorrenteC)) {
                    erros.add("Corrente do trafo " + comp.getId() + " é infinita.");
                }
                CalculaCorrente.calculaPotenciaAtivaReativa(comp.getBarra());
            } catch (IllegalStateException e) {
                throw e;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println();
            }
        }

        /**
         * Método recursivo que percorre a arvore em profundidade
         * calculando o somatório das correntes. Antes de se aprofundar
         * na arvore calcula as correntes e angulos dos transformadores.
         * param nav objeto navegavel correspondente ao nó da arvore
         * return um array de doubles com iAtiva, iReativa, demandaAtiv
         * a e demandaReativa nesta ordem
         * para o nó em questão
         */

        private void dadosDosCapacitores(Capacitor capacitor) {
            capacitor.setAnoSimulado(ano);
            capacitor.setPatamarSimulado(patamar);
            MedidaEletrica att = capacitor.getINavegavelAssociado().getMedidaEletrica(POTENCIA_ATIVA);
            if (att == null) {
                att = new MedidaEletrica(POTENCIA_ATIVA, new Double[]{0.0, 0.0, 0.0}, "kW", true);
                att.setValorResumo(att.getValorSomado());
                capacitor.getINavegavelAssociado().adicionaMedidaEletrica(POTENCIA_ATIVA, att);
            }
            att = capacitor.getINavegavelAssociado().getMedidaEletrica(MODULO_TENSAO);
            if (att == null) {
                att = new MedidaEletrica(MODULO_TENSAO, new Double[]{capacitor.getBarra().getTensaoNominal(), capacitor.getBarra().getTensaoNominal(), capacitor.getBarra().getTensaoNominal()}, "kV", true);
                att.setValorResumo(att.getValorMinimo());
                capacitor.getINavegavelAssociado().adicionaMedidaEletrica(att.getNome(), att);
            }
            double tensaoA = att.getValorA();
            double tensaoB = att.getValorB();
            double tensaoC = att.getValorC();
            Double tensaoSE = capacitor.getBarra().getTensaoNominal();
            if (tensaoSE == null) tensaoSE = tensaoA;
            double QgA = 0;
            double QgB = 0;
            double QgC = 0;
            Double potenciaNominal = capacitor.getPotenciaReativa(this.patamar);
            if (potenciaNominal == null) {
                potenciaNominal = 0.0;
            }
            try {
                QgA = ((potenciaNominal / 3.0) * Math.pow((tensaoA / tensaoSE), 2.0));
                QgB = ((potenciaNominal / 3.0) * Math.pow((tensaoB / tensaoSE), 2.0));
                QgC = ((potenciaNominal / 3.0) * Math.pow((tensaoC / tensaoSE), 2.0));
                att = capacitor.getINavegavelAssociado().getMedidaEletrica(POTENCIA_REATIVA);
            } catch (NullPointerException npe) {
                npe.printStackTrace();
                System.out.println();
            }
            if (att == null) {
                att = new MedidaEletrica(POTENCIA_REATIVA, new Double[]{QgA, QgB, QgC}, "kVAr", true);
                att.setValorResumo(att.getValorSomado());
                capacitor.getINavegavelAssociado().adicionaMedidaEletrica(POTENCIA_REATIVA, att);
                att = new MedidaEletrica(POTENCIA, new Double[]{QgA, QgB, QgC}, "kVA", true);
                att.setValorResumo(att.getValorSomado());
                capacitor.getINavegavelAssociado().adicionaMedidaEletrica(POTENCIA, att);
            } else {
                att.setValor(new Double[]{QgA, QgB, QgC});
                att.setValorResumo(att.getValorSomado());
                att = capacitor.getINavegavelAssociado().getMedidaEletrica(POTENCIA);
                att.setValor(new Double[]{QgA, QgB, QgC});
                att.setValorResumo(att.getValorSomado());
            }
            att = capacitor.getINavegavelAssociado().getMedidaEletrica(CORRENTE_ATIVA);
            if (att == null) {
                att = new MedidaEletrica(CORRENTE_ATIVA, new Double[]{0.0, 0.0, 0.0}, "A", false);
                att.setValorResumo(att.getValorSomado());
                capacitor.getINavegavelAssociado().adicionaMedidaEletrica(CORRENTE_ATIVA, att);
            }
            MedidaEletrica anguloTensao = capacitor.getINavegavelAssociado().getMedidaEletrica(ANGULO_TENSAO);
            if (anguloTensao == null) {
                anguloTensao = new MedidaEletrica(ANGULO_TENSAO, new Double[]{0.0, 0.0, 0.0}, "", true);
            }

            double iReativaA = (QgA / (tensaoA / VALOR_RAIZ_DE_TRES)) * Math.sin(anguloTensao.getValorA() + Math.toRadians(90.0));
            double iReativaB = (QgB / (tensaoB / VALOR_RAIZ_DE_TRES)) * Math.sin(anguloTensao.getValorB() + Math.toRadians(90.0));
            double iReativaC = (QgC / (tensaoC / VALOR_RAIZ_DE_TRES)) * Math.sin(anguloTensao.getValorC() + Math.toRadians(90.0));

            if (Double.isNaN(iReativaB) || Double.isNaN(iReativaB) || Double.isNaN(iReativaB)) {
                erros.add("Corrente do capacitor é NaN.");
            }

            if (Double.isInfinite(iReativaB) || Double.isInfinite(iReativaB) || Double.isInfinite(iReativaB)) {
                erros.add("Corrente do capacitor é infinita.");
            }

            att = capacitor.getINavegavelAssociado().getMedidaEletrica(CORRENTE_REATIVA);
            if (att == null) {
                att = new MedidaEletrica(CORRENTE_REATIVA, new Double[]{iReativaA, iReativaB, iReativaC}, "A", false);
                att.setValorResumo(att.getValorSomado());
                capacitor.getINavegavelAssociado().adicionaMedidaEletrica(CORRENTE_REATIVA, att);
                att = new MedidaEletrica(CORRENTE, new Double[]{iReativaA, iReativaB, iReativaC}, "A", true);
                att.setValorResumo(att.getValorSomado());
                capacitor.getINavegavelAssociado().adicionaMedidaEletrica(CORRENTE, att);
            } else {
                att.setValor(new Double[]{iReativaA, iReativaB, iReativaC});
                att.setValorResumo(att.getValorSomado());
                att = capacitor.getINavegavelAssociado().getMedidaEletrica(CORRENTE);
                att.setValor(new Double[]{iReativaA, iReativaB, iReativaC});
                att.setValorResumo(att.getValorSomado());
            }
        }

        /**
         * Mï¿½todo recursivo que percorre a ï¿½rvore em profundidade
         * calculando o somatï¿½rio das correntes. Antes de se aprofundar
         * na ï¿½rvore calcula as correntes e angulos dos transformadores.
         * param nav objeto navegavel correspondente ao nï¿½ da ï¿½rvore
         * return um array de doubles com iAtiva, iReativa, demandaAtiva e demandaReativa nesta ordem
         * para o nï¿½ em questï¿½o
         */
        private void dadosDosGeradores(FonteGeracao gerador) {
            MedidaEletrica attU = gerador.getINavegavelAssociado().getMedidaEletrica(MODULO_TENSAO);
            if (attU == null) {
                attU = new MedidaEletrica(MODULO_TENSAO, new Double[]{gerador.getBarra().getTensaoNominal(), gerador.getBarra().getTensaoNominal(), gerador.getBarra().getTensaoNominal()}, "kV", true);
                attU.setValorResumo(attU.getValorMinimo());
                gerador.getINavegavelAssociado().adicionaMedidaEletrica(attU.getNome(), attU);
            }

            double tensaoA = attU.getValorA();
            double tensaoB = attU.getValorB();
            double tensaoC = attU.getValorC();

            double potAtivaA = -gerador.getPotenciaAtiva() / 3.0;
            double potAtivaB = -gerador.getPotenciaAtiva() / 3.0;
            double potAtivaC = -gerador.getPotenciaAtiva() / 3.0;

            double potReativaA = -gerador.getPotenciaReativa() / 3.0;
            double potReativaB = -gerador.getPotenciaReativa() / 3.0;
            double potReativaC = -gerador.getPotenciaReativa() / 3.0;

            double anguloFatorPotenciaA = (potAtivaA == 0) ? 0 : Math.toRadians(180) - Math.atan(potReativaA / potAtivaA);
            double anguloFatorPotenciaB = (potAtivaA == 0) ? 0 : Math.toRadians(180) - Math.atan(potReativaB / potAtivaB);
            double anguloFatorPotenciaC = (potAtivaC == 0) ? 0 : Math.toRadians(180) - Math.atan(potReativaC / potAtivaC);


            MedidaEletrica attP = gerador.getINavegavelAssociado().getMedidaEletrica(POTENCIA_ATIVA);
            if (attP == null) {
                attP = new MedidaEletrica(POTENCIA_ATIVA, new Double[]{potAtivaA, potAtivaB, potAtivaC}, "kW", true);
                attP.setValorResumo(attP.getValorSomado());
                gerador.getINavegavelAssociado().adicionaMedidaEletrica(POTENCIA_ATIVA, attP);
            } else {
                attP.setValor(new Double[]{potAtivaA, potAtivaB, potAtivaC});
                attP.setValorResumo(attP.getValorSomado());
            }

            MedidaEletrica attQ = gerador.getINavegavelAssociado().getMedidaEletrica(POTENCIA_REATIVA);
            if (attQ == null) {
                attQ = new MedidaEletrica(POTENCIA_REATIVA, new Double[]{potReativaA, potReativaB, potReativaC}, "kVAr", true);
                attQ.setValorResumo(attQ.getValorSomado());
                gerador.getINavegavelAssociado().adicionaMedidaEletrica(POTENCIA_REATIVA, attQ);
            } else {
                attQ.setValor(new Double[]{potReativaA, potReativaB, potReativaC});
                attQ.setValorResumo(attQ.getValorSomado());
            }

            Double potTotalA = -Math.sqrt((potAtivaA * potAtivaA) + (potReativaA * potReativaA));
            Double potTotalB = -Math.sqrt((potAtivaB * potAtivaB) + (potReativaB * potReativaB));
            Double potTotalC = -Math.sqrt((potAtivaC * potAtivaC) + (potReativaC * potReativaC));

            MedidaEletrica attS = gerador.getINavegavelAssociado().getMedidaEletrica(POTENCIA);
            if (attS == null) {
                attS = new MedidaEletrica(POTENCIA, new Double[]{potTotalA, potTotalB, potTotalC}, "kVA", true);
                attS.setValorResumo(attS.getValorSomado());
                gerador.getINavegavelAssociado().adicionaMedidaEletrica(POTENCIA, attS);
            } else {
                attS.setValor(new Double[]{potTotalA, potTotalB, potTotalC});
                attS.setValorResumo(attS.getValorSomado());
            }

            MedidaEletrica attAngU = gerador.getINavegavelAssociado().getMedidaEletrica(ANGULO_TENSAO);
            if (attAngU == null) {
                attAngU = new MedidaEletrica(MODULO_TENSAO, new Double[]{0.0, 0.0, 0.0}, "kV", true);
                gerador.getINavegavelAssociado().adicionaMedidaEletrica(attAngU.getNome(), attAngU);
            }
            double anguloTensaoA = attAngU.getValorA();
            double anguloTensaoB = attAngU.getValorB();
            double anguloTensaoC = attAngU.getValorC();
            double iAtivaA = Math.sqrt(
                    Math.pow(potAtivaA, 2) + Math.pow(potReativaA, 2))
                    / (tensaoA / VALOR_RAIZ_DE_TRES)
                    * Math.cos(anguloTensaoA - anguloFatorPotenciaA);
            double iAtivaB = Math.sqrt(
                    Math.pow(potAtivaB, 2) + Math.pow(potReativaB, 2))
                    / (tensaoB / VALOR_RAIZ_DE_TRES)
                    * Math.cos(anguloTensaoB - anguloFatorPotenciaB);
            double iAtivaC = Math.sqrt(
                    Math.pow(potAtivaC, 2) + Math.pow(potReativaC, 2))
                    / (tensaoC / VALOR_RAIZ_DE_TRES)
                    * Math.cos(anguloTensaoC - anguloFatorPotenciaC);

            MedidaEletrica attIAt = gerador.getINavegavelAssociado().getMedidaEletrica(CORRENTE_ATIVA);
            if (attIAt == null) {
                attIAt = new MedidaEletrica(CORRENTE_ATIVA, new Double[]{iAtivaA, iAtivaB, iAtivaC}, "A", false);
                attIAt.setValorResumo(attIAt.getValorMaximo());
                gerador.getINavegavelAssociado().adicionaMedidaEletrica(CORRENTE_ATIVA, attIAt);
            } else {
                attIAt.setValor(new Double[]{iAtivaA, iAtivaB, iAtivaC});
                attIAt.setValorResumo(attIAt.getValorMaximo());
            }


            double iReativaA = Math.sqrt(
                    Math.pow(potAtivaA, 2) + Math.pow(potReativaA, 2))
                    / (tensaoA / VALOR_RAIZ_DE_TRES)
                    * Math.sin(anguloTensaoA - anguloFatorPotenciaA);
            double iReativaB = Math.sqrt(
                    Math.pow(potAtivaB, 2) + Math.pow(potReativaB, 2))
                    / (tensaoB / VALOR_RAIZ_DE_TRES)
                    * Math.sin(anguloTensaoB - anguloFatorPotenciaB);
            double iReativaC = Math.sqrt(
                    Math.pow(potAtivaC, 2) + Math.pow(potReativaC, 2))
                    / (tensaoC / VALOR_RAIZ_DE_TRES)
                    * Math.sin(anguloTensaoC - anguloFatorPotenciaC);

            MedidaEletrica attIReat = gerador.getINavegavelAssociado().getMedidaEletrica(CORRENTE_REATIVA);
            if (attIReat == null) {
                attIReat = new MedidaEletrica(CORRENTE_REATIVA, new Double[]{iReativaA, iReativaB, iReativaC}, "A", false);
                attIReat.setValorResumo(attIReat.getValorMaximo());
                gerador.getINavegavelAssociado().adicionaMedidaEletrica(CORRENTE_REATIVA, attIReat);
            } else {
                attIReat.setValor(new Double[]{iReativaA, iReativaB, iReativaC});
                attIReat.setValorResumo(attIReat.getValorMaximo());
            }

            //calcula e seta a corrente
            MedidaEletrica attI = gerador.getINavegavelAssociado().getMedidaEletrica(CORRENTE);
            double iA = -Math.sqrt((iAtivaA * iAtivaA) + (iReativaA * iReativaA));
            double iB = -Math.sqrt((iAtivaB * iAtivaB) + (iReativaB * iReativaB));
            double iC = -Math.sqrt((iAtivaC * iAtivaC) + (iReativaC * iReativaC));
            if (attI == null) {
                attI = new MedidaEletrica(CORRENTE, new Double[]{iA, iB, iC}, "A", true);
                attI.setValorResumo(attI.getValorMaximo());
                gerador.getINavegavelAssociado().adicionaMedidaEletrica(CORRENTE, attI);
            } else {
                attI.setValor(new Double[]{iA, iB, iC});
                attI.setValorResumo(attI.getValorMaximo());
            }

            if (Double.isNaN(iA) || Double.isNaN(iB) || Double.isNaN(iB)) {
                erros.add("Corrente do gerador é NaN.");
            }

            if (Double.isInfinite(iA) || Double.isInfinite(iB) || Double.isInfinite(iB)) {
                erros.add("Corrente do gerador é infinita.");
            }

            MedidaEletrica attAngI = gerador.getINavegavelAssociado().getMedidaEletrica(ANGULO_CORRENTE);
            double anguloCorrenteA = (iAtivaA == 0.0) ? Math.toRadians(90.0) : Math.atan(iReativaA / iAtivaA);
            double anguloCorrenteB = (iAtivaB == 0.0) ? Math.toRadians(90.0) : Math.atan(iReativaB / iAtivaB);
            double anguloCorrenteC = (iAtivaC == 0.0) ? Math.toRadians(90.0) : Math.atan(iReativaC / iAtivaC);
            if (attAngI == null) {
                attAngI = new MedidaEletrica(ANGULO_CORRENTE, new Double[]{anguloCorrenteA, anguloCorrenteB, anguloCorrenteC}, "°", true);
                gerador.getINavegavelAssociado().adicionaMedidaEletrica(ANGULO_CORRENTE, attAngI);
            } else {
                attAngI.setValor(new Double[]{anguloCorrenteA, anguloCorrenteB, anguloCorrenteC});
            }
        }

        long tempoEspera = 0;

        @Override
        public long getTempoEspera() {
            return tempoEspera;
        }

        @Override
        public void setTempoEspera(long tempo) {
            tempoEspera = tempo;
        }

        @Override
        public void addTempoEspera(long tempo) {
            tempoEspera += tempo;
        }
    }

    public static class CalculaCorrente implements AcaoPosFixada {

        class ObjCorrente {

            double iAtA = 0.0;
            double iAtB = 0.0;
            double iAtC = 0.0;

            double iReatA = 0.0;
            double iReatB = 0.0;
            double iReatC = 0.0;

            double pA = 0.0;
            double pB = 0.0;
            double pC = 0.0;

            double qA = 0.0;
            double qB = 0.0;
            double qC = 0.0;

            int numConsumidores = 0;
        }

        public Object executePos(INavegavel nav, INavegavel anterior, Object params) {
            ObjCorrente oCorr = (ObjCorrente) params;
            if (oCorr == null) {
                oCorr = new ObjCorrente();
            }
            if (nav instanceof Barra) {
                Barra vertice = (Barra) nav;
                if (vertice.getCapacitor() != null || vertice.getFonteGeracao() != null || vertice.getTransformador() != null) {
                    //para pegar o valor das correntes deste transformador
                    MedidaEletrica iAtiva = vertice.getMedidaEletrica(CORRENTE_ATIVA);
                    MedidaEletrica iReativa = vertice.getMedidaEletrica(CORRENTE_REATIVA);
                    if (iAtiva != null) {
                        oCorr.iAtA += iAtiva.getValorA();
                        oCorr.iAtB += iAtiva.getValorB();
                        oCorr.iAtC += iAtiva.getValorC();
                    }
                    if (iReativa != null) {
                        oCorr.iReatA += iReativa.getValorA();
                        oCorr.iReatB += iReativa.getValorB();
                        oCorr.iReatC += iReativa.getValorC();
                    }
                    if (vertice.getTransformador() != null) {
                        Transformador t = vertice.getTransformador();
                        if (vertice.getMedidaEletrica(DEMANDA_ATIVA) == null) {
                            oCorr.pA += t.getDemandaAtivaA();
                            oCorr.pB += t.getDemandaAtivaB();
                            oCorr.pC += t.getDemandaAtivaC();
                        } else {
                            Double[] P = vertice.getMedidaEletrica(DEMANDA_ATIVA).getValorArray();
                            oCorr.pA += P[0];
                            oCorr.pB += P[1];
                            oCorr.pC += P[2];
                        }
                        if (vertice.getMedidaEletrica(DEMANDA_REATIVA) == null) {
                            oCorr.qA += t.getDemandaReativaA();
                            oCorr.qB += t.getDemandaReativaB();
                            oCorr.qC += t.getDemandaReativaC();
                        } else {
                            Double[] Q = vertice.getMedidaEletrica(DEMANDA_REATIVA).getValorArray();
                            oCorr.qA += Q[0];
                            oCorr.qB += Q[1];
                            oCorr.qC += Q[2];
                        }
                        oCorr.numConsumidores += t.getNumConsumidores();
                    } else if (vertice.getCapacitor() != null && vertice.getCapacitor().getMedidaEletrica(POTENCIA_REATIVA) != null) {
                        MedidaEletrica att = vertice.getMedidaEletrica(POTENCIA_REATIVA);
                        oCorr.qA += att.getValorA();
                        oCorr.qB += att.getValorB();
                        oCorr.qC += att.getValorC();
                    } else if (vertice.getFonteGeracao() != null) {
                        MedidaEletrica attP = vertice.getMedidaEletrica(POTENCIA_ATIVA);
                        oCorr.pA += attP.getValorA();
                        oCorr.pB += attP.getValorB();
                        oCorr.pC += attP.getValorC();
                        MedidaEletrica attQ = vertice.getMedidaEletrica(POTENCIA_REATIVA);
                        oCorr.qA += attQ.getValorA();
                        oCorr.qB += attQ.getValorB();
                        oCorr.qC += attQ.getValorC();
                    }
                } else {
                    //se for ramo
                    MedidaEletrica iAtiva = vertice.getMedidaEletrica(CORRENTE_ATIVA);
                    MedidaEletrica iReativa = vertice.getMedidaEletrica(CORRENTE_REATIVA);
                    if (iAtiva != null) {
                        iAtiva.setValor(new Double[]{oCorr.iAtA, oCorr.iAtB, oCorr.iAtC});
                        iAtiva.setValorResumo(iAtiva.getValorMaximo());
                    } else {
                        iAtiva = new MedidaEletrica(CORRENTE_ATIVA, new Double[]{oCorr.iAtA, oCorr.iAtB, oCorr.iAtC}, "A", false);
                        iAtiva.setValorResumo(iAtiva.getValorMaximo());
                        vertice.adicionaMedidaEletrica(CORRENTE_ATIVA, iAtiva);
                    }
                    if (iReativa != null) {
                        iReativa.setValor(new Double[]{oCorr.iReatA, oCorr.iReatB, oCorr.iReatC});
                        iReativa.setValorResumo(iReativa.getValorMaximo());
                    } else {
                        iReativa = new MedidaEletrica(CORRENTE_REATIVA, new Double[]{oCorr.iReatA, oCorr.iReatB, oCorr.iReatC}, "A", false);
                        iReativa.setValorResumo(iReativa.getValorMaximo());
                        vertice.adicionaMedidaEletrica(CORRENTE_REATIVA, iReativa);
                    }
                    double dCorrenteA = Math.sqrt((oCorr.iAtA * oCorr.iAtA) + (oCorr.iReatA * oCorr.iReatA));
                    double dCorrenteB = Math.sqrt((oCorr.iAtB * oCorr.iAtB) + (oCorr.iReatB * oCorr.iReatB));
                    double dCorrenteC = Math.sqrt((oCorr.iAtC * oCorr.iAtC) + (oCorr.iReatC * oCorr.iReatC));
                    MedidaEletrica att = new MedidaEletrica(CORRENTE, new Double[] { dCorrenteA, dCorrenteB, dCorrenteC }, "A", true);
                    att.setValorResumo(att.getValorMaximo());
                    vertice.adicionaMedidaEletrica(att.getNome(), att);
                    calculaAngulos(vertice, oCorr);
                    calculaPotenciaAtivaReativa(vertice);
                    MedidaEletrica nConsum = vertice.getMedidaEletrica(CONSUMIDORES_ACUM);
                    if (nConsum == null) {
                        nConsum = new MedidaEletrica(CONSUMIDORES_ACUM, new Double[] { (double) oCorr.numConsumidores, 0.0, 0.0 }, "n", false);
                        vertice.adicionaMedidaEletrica(CONSUMIDORES_ACUM, nConsum);
                    }
                }
                //se for condutor
            } else if (nav instanceof TrechoRede) {
                TrechoRede aresta = (TrechoRede) nav;
                if (aresta.getRegulador() != null && aresta.getRegulador().getRelacaoTransformacao() != null) {
                    oCorr.iAtA = oCorr.iAtA * aresta.getRegulador().getRelacaoTransformacao()[0];
                    oCorr.iAtB = oCorr.iAtB * aresta.getRegulador().getRelacaoTransformacao()[1];
                    oCorr.iAtC = oCorr.iAtC * aresta.getRegulador().getRelacaoTransformacao()[2];

                    oCorr.iReatA = oCorr.iReatA * aresta.getRegulador().getRelacaoTransformacao()[0];
                    oCorr.iReatB = oCorr.iReatB * aresta.getRegulador().getRelacaoTransformacao()[1];
                    oCorr.iReatC = oCorr.iReatC * aresta.getRegulador().getRelacaoTransformacao()[2];

                } else if (aresta.getElevadorRebaixador() != null && aresta.getElevadorRebaixador().getAtivado() != null &&
                        aresta.getElevadorRebaixador().getAtivado() && aresta.getElevadorRebaixador().getRelTransformacao() != null)
                {
                    oCorr.iAtA = oCorr.iAtA * aresta.getElevadorRebaixador().getRelTransformacao();
                    oCorr.iAtB = oCorr.iAtB * aresta.getElevadorRebaixador().getRelTransformacao();
                    oCorr.iAtC = oCorr.iAtC * aresta.getElevadorRebaixador().getRelTransformacao();

                    oCorr.iReatA = oCorr.iReatA * aresta.getElevadorRebaixador().getRelTransformacao();
                    oCorr.iReatB = oCorr.iReatB * aresta.getElevadorRebaixador().getRelTransformacao();
                    oCorr.iReatC = oCorr.iReatC * aresta.getElevadorRebaixador().getRelTransformacao();
                }

                MedidaEletrica iAtiva = aresta.getMedidaEletrica(CORRENTE_ATIVA);
                MedidaEletrica iReativa = aresta.getMedidaEletrica(CORRENTE_REATIVA);

                if (iAtiva == null) {
                    iAtiva = new MedidaEletrica(CORRENTE_ATIVA, new Double[]{oCorr.iAtA, oCorr.iAtB, oCorr.iAtC}, "A", false);
                    iAtiva.setValorResumo(iAtiva.getValorMaximo());
                    aresta.adicionaMedidaEletrica(CORRENTE_ATIVA, iAtiva);
                } else {
                    iAtiva.setValor(new Double[]{oCorr.iAtA, oCorr.iAtB, oCorr.iAtC});
                    iAtiva.setValorResumo(iAtiva.getValorMaximo());
                }

                if (iReativa == null) {
                    iReativa = new MedidaEletrica(CORRENTE_REATIVA, new Double[]{oCorr.iReatA, oCorr.iReatB, oCorr.iReatC}, "A", false);
                    iAtiva.setValorResumo(iAtiva.getValorMaximo());
                    aresta.adicionaMedidaEletrica(CORRENTE_REATIVA, iReativa);
                } else {
                    iReativa.setValor(new Double[]{oCorr.iReatA, oCorr.iReatB, oCorr.iReatC});
                    iAtiva.setValorResumo(iAtiva.getValorMaximo());
                }
                double dCorrenteA = Math.sqrt((oCorr.iAtA * oCorr.iAtA) + (oCorr.iReatA * oCorr.iReatA));
                double dCorrenteB = Math.sqrt((oCorr.iAtB * oCorr.iAtB) + (oCorr.iReatB * oCorr.iReatB));
                double dCorrenteC = Math.sqrt((oCorr.iAtC * oCorr.iAtC) + (oCorr.iReatC * oCorr.iReatC));

                MedidaEletrica att = new MedidaEletrica(CORRENTE, new Double[]{dCorrenteA, dCorrenteB, dCorrenteC}, "A", true);
                att.setValorResumo(att.getValorMaximo());
                aresta.adicionaMedidaEletrica(att.getNome(), att);
                calculaAngulos(aresta, oCorr);
                calculaQuedaTensao(aresta);
                calculaPotenciaAtivaReativa(aresta);
                calculaPerdaPotencia(aresta);
                MedidaEletrica nConsum = aresta.getMedidaEletrica(CONSUMIDORES_ACUM);
                if (nConsum == null) {
                    nConsum = new MedidaEletrica(CONSUMIDORES_ACUM, new Double[] { (double) oCorr.numConsumidores, 0.0, 0.0 }, "n", false);
                    aresta.adicionaMedidaEletrica(CONSUMIDORES_ACUM, nConsum);
                }

            }
            return oCorr;
        }

        public Object operaResultados(Object resultadoParcial, Object resultadoRamo) {
            if (resultadoParcial == null && resultadoRamo == null) {
                return new ObjCorrente();
            }
            if (resultadoParcial == null) {
                return resultadoRamo;
            }
            if (resultadoRamo == null) {
                return resultadoParcial;
            }
            ObjCorrente r1 = (ObjCorrente) resultadoParcial;
            ObjCorrente r2 = (ObjCorrente) resultadoRamo;
            ObjCorrente res = new ObjCorrente();

            res.iAtA = r1.iAtA + r2.iAtA;
            res.iAtB = r1.iAtB + r2.iAtB;
            res.iAtC = r1.iAtC + r2.iAtC;

            res.iReatA = r1.iReatA + r2.iReatA;
            res.iReatB = r1.iReatB + r2.iReatB;
            res.iReatC = r1.iReatC + r2.iReatC;

            res.pA = r1.pA + r2.pA;
            res.pB = r1.pB + r2.pB;
            res.pC = r1.pC + r2.pC;

            res.qA = r1.qA + r2.qA;
            res.qB = r1.qB + r2.qB;
            res.qC = r1.qC + r2.qC;

            res.numConsumidores = r1.numConsumidores + r2.numConsumidores;

            return res;
        }

        /**
         * calcula os angulos de fator de potencia, corrente e tensao
         *
         * @param a         aresta que esta sendo calculada
         * @param oCorrente informaï¿½ï¿½es de corrente e demanda calculados
         */
        private void calculaAngulos(INavegavel a, CalculaCorrente.ObjCorrente oCorrente) {
            double anguloFatorPotenciaA = (oCorrente.pA == 0.0) ? -Math.toRadians(90.0) : Math.atan(oCorrente.qA / oCorrente.pA);
            double anguloCorrenteA = (oCorrente.iAtA == 0.0) ? Math.toRadians(90.0) : (oCorrente.iAtA < 0.0) ? Math.toRadians(180) - (Math.atan(oCorrente.iReatA / oCorrente.iAtA)) : Math.atan(oCorrente.iReatA / oCorrente.iAtA);

            double anguloFatorPotenciaB = (oCorrente.pB == 0.0) ? -Math.toRadians(90.0) : Math.atan(oCorrente.qB / oCorrente.pB);
            double anguloCorrenteB = (oCorrente.iAtB == 0.0) ? Math.toRadians(90.0) : (oCorrente.iAtB < 0.0) ? Math.toRadians(180) - (Math.atan(oCorrente.iReatB / oCorrente.iAtB)) : Math.atan(oCorrente.iReatB / oCorrente.iAtB);

            double anguloFatorPotenciaC = (oCorrente.pC == 0.0) ? -Math.toRadians(90.0) : Math.atan(oCorrente.qC / oCorrente.pC);
            double anguloCorrenteC = (oCorrente.iAtC == 0.0) ? Math.toRadians(90.0) : (oCorrente.iAtC < 0.0) ? Math.toRadians(180) - (Math.atan(oCorrente.iReatC / oCorrente.iAtC)) : Math.atan(oCorrente.iReatC / oCorrente.iAtC);

            MedidaEletrica att = new MedidaEletrica(ANGULO_CORRENTE, new Double[]{anguloCorrenteA, anguloCorrenteB, anguloCorrenteC}, "°", true);
            a.adicionaMedidaEletrica(ANGULO_CORRENTE, att);
            att = new MedidaEletrica(ANGULO_FATOR_POTENCIA, new Double[]{anguloFatorPotenciaA, anguloFatorPotenciaB, anguloFatorPotenciaC}, "°", false);
            a.adicionaMedidaEletrica(ANGULO_FATOR_POTENCIA, att);
        }


        /**
         * Calcula a queda de tensï¿½o gerada por um condutor
         *
         * @param aresta condutor que serï¿½ calculado
         */
        private void calculaQuedaTensao(TrechoRede aresta) {
            MedidaEletrica corrente = aresta.getMedidaEletrica(CORRENTE);
            double dCorrenteA = corrente.getValorA();
            double dCorrenteB = corrente.getValorB();
            double dCorrenteC = corrente.getValorC();
            //double xIndutivo = PPGEEUtil.IMPEDANCIAS_MUTUAS_ESTRUTURAS.get("N1");
            if ((aresta.getR1() == null || aresta.getR0() == null) && (aresta.getNomeCaboFase() == null || aresta.getNomeCaboFase().trim().isEmpty())) {
                aresta.setNomeCaboFase("");
                aresta.setComprimento(0.0);
            } else if (aresta.getR1() == null || aresta.getR0() == null) {
//                System.out.println("Condutor " + aresta.getNomeCaboFase() + " desconhecido.");
                erros.add("Condutor " + aresta.getNomeCaboFase() + " desconhecido ou faltando R0 ou R1");
                throw new IllegalArgumentException("Condutor " + aresta.getNomeCaboFase() + " desconhecido.");
            }
            if (aresta.getCapacitorSerie() != null && aresta.getCapacitorSerie().getAtivado() != null && aresta.getCapacitorSerie().getAtivado()) {
                CapacitorSerie caps = aresta.getCapacitorSerie();
                double zPorFase = -caps.getReatanciaSeq1() / caps.getNumCelulas();
                double quedaTensaoA = (VALOR_RAIZ_DE_TRES * dCorrenteA * zPorFase) / 1000.0;
                double quedaTensaoB = (VALOR_RAIZ_DE_TRES * dCorrenteB * zPorFase) / 1000.0;
                double quedaTensaoC = (VALOR_RAIZ_DE_TRES * dCorrenteC * zPorFase) / 1000.0;
                MedidaEletrica att = new MedidaEletrica(QUEDA_TENSAO, new Double[]{ quedaTensaoA, quedaTensaoB, quedaTensaoC }, "kV", false);
                aresta.adicionaMedidaEletrica(att.getNome(), att);
                double qAtivaA = 0.0, qAtivaB = 0.0, qAtivaC = 0.0;
                double qReativaA = 0.0, qReativaB = 0.0, qReativaC = 0.0;
                att = aresta.getMedidaEletrica(ANGULO_CORRENTE);
                double anguloCorrenteA = att.getValorA();
                double anguloCorrenteB = att.getValorB();
                double anguloCorrenteC = att.getValorC();
                qAtivaA = quedaTensaoA * Math.cos(anguloCorrenteA);
                qReativaA = quedaTensaoA * Math.sin(anguloCorrenteA);
                qAtivaB = quedaTensaoB * Math.cos(anguloCorrenteB);
                qReativaB = quedaTensaoB * Math.sin(anguloCorrenteB);
                qAtivaC = quedaTensaoC * Math.cos(anguloCorrenteC);
                qReativaC = quedaTensaoC * Math.sin(anguloCorrenteC);
                MedidaEletrica q = new MedidaEletrica(QUEDA_ATIVA, new Double[]{qAtivaA, qAtivaB, qAtivaC}, "", false);
                aresta.adicionaMedidaEletrica(QUEDA_ATIVA, q);
                q = new MedidaEletrica(QUEDA_REATIVA, new Double[]{qReativaA, qReativaB, qReativaC}, "", false);
                aresta.adicionaMedidaEletrica(QUEDA_REATIVA, q);
            } else {
                double z = Math.sqrt(Math.pow(aresta.getR1() * aresta.getComprimentoKm(), 2.0) + Math.pow((aresta.getX1()) * aresta.getComprimentoKm(), 2.0));
                double quedaTensaoA = (VALOR_RAIZ_DE_TRES * dCorrenteA * z) / 1000.0;
                double quedaTensaoB = (VALOR_RAIZ_DE_TRES * dCorrenteB * z) / 1000.0;
                double quedaTensaoC = (VALOR_RAIZ_DE_TRES * dCorrenteC * z) / 1000.0;
                MedidaEletrica att = new MedidaEletrica(QUEDA_TENSAO, new Double[]{quedaTensaoA, quedaTensaoB, quedaTensaoC}, "kV", false);
                aresta.adicionaMedidaEletrica(att.getNome(), att);
                double anguloImpedancia = Math.atan(aresta.getX1() / aresta.getR1());
                double qAtivaA = 0.0, qAtivaB = 0.0, qAtivaC = 0.0;
                double qReativaA = 0.0, qReativaB = 0.0, qReativaC = 0.0;
                if (!Double.isNaN(anguloImpedancia)) {
                    att = aresta.getMedidaEletrica(ANGULO_CORRENTE);
                    double anguloCorrenteA = att.getValorA();
                    double anguloCorrenteB = att.getValorB();
                    double anguloCorrenteC = att.getValorC();
                    qAtivaA = quedaTensaoA * Math.cos(anguloImpedancia + anguloCorrenteA);
                    qReativaA = quedaTensaoA * Math.sin(anguloImpedancia + anguloCorrenteA);
                    qAtivaB = quedaTensaoB * Math.cos(anguloImpedancia + anguloCorrenteB);
                    qReativaB = quedaTensaoB * Math.sin(anguloImpedancia + anguloCorrenteB);
                    qAtivaC = quedaTensaoC * Math.cos(anguloImpedancia + anguloCorrenteC);
                    qReativaC = quedaTensaoC * Math.sin(anguloImpedancia + anguloCorrenteC);
                }
                if (Double.isNaN(qAtivaA) || Double.isNaN(qAtivaB) || Double.isNaN(qAtivaC)) {
                    erros.add("Queda de tensão é NaN");
                } else if (qAtivaA == 0.0 && qAtivaB == 0.0 && qAtivaC == 0.0 && aresta.getComprimentoKm() > 0.0 &&
                        (dCorrenteA + dCorrenteB + dCorrenteC) > 0.0)
                {
                    System.out.println("Queda de tensão zero");
                }
                MedidaEletrica q = new MedidaEletrica(QUEDA_ATIVA, new Double[]{qAtivaA, qAtivaB, qAtivaC}, "", false);
                aresta.adicionaMedidaEletrica(QUEDA_ATIVA, q);
                q = new MedidaEletrica(QUEDA_REATIVA, new Double[]{qReativaA, qReativaB, qReativaC}, "", false);
                aresta.adicionaMedidaEletrica(QUEDA_REATIVA, q);
            }
        }

        /**
         * Calcula a potencia ativa e reativa de um condutor
         *
         * @param a condutor que serï¿½ calculado
         */
        public static void calculaPotenciaAtivaReativa(INavegavel a) {
            MedidaEletrica attAnguloCorrente = a.getMedidaEletrica(ANGULO_CORRENTE);
            MedidaEletrica attAnguloTensao = a.getMedidaEletrica(ANGULO_TENSAO);
            if (attAnguloTensao == null) {
                attAnguloTensao = new MedidaEletrica(ANGULO_TENSAO, new Double[]{0.0, 0.0, 0.0}, "°", true);
                a.adicionaMedidaEletrica(ANGULO_TENSAO, attAnguloTensao);
            }
            MedidaEletrica attCorrente = a.getMedidaEletrica(CORRENTE);
            MedidaEletrica attTensao = a.getMedidaEletrica(MODULO_TENSAO);
            if (attTensao == null) {
                attTensao = new MedidaEletrica(MODULO_TENSAO, new Double[]{a.getTensaoNominal(), a.getTensaoNominal(), a.getTensaoNominal()}, "kV", true);
                a.adicionaMedidaEletrica(MODULO_TENSAO, attTensao);
            }

            double anguloTensaoA = attAnguloTensao.getValorA();
            double anguloCorrenteA = attAnguloCorrente.getValorA();
            double iA = attCorrente.getValorA();
            double tensaoA = attTensao.getValorA();
            double zA = tensaoA * iA / VALOR_RAIZ_DE_TRES;
            double PA = zA * Math.cos(anguloTensaoA - anguloCorrenteA);
            double QA = zA * Math.sin(anguloTensaoA - anguloCorrenteA);
            if (Double.isNaN(PA) || Double.isInfinite(PA)) {
                PA = 0.0;
            }
            if (Double.isNaN(QA) || Double.isInfinite(QA)) {
                QA = 0.0;
            }

            double anguloTensaoB = attAnguloTensao.getValorB();
            double anguloCorrenteB = attAnguloCorrente.getValorB();
            double iB = attCorrente.getValorB();
            double tensaoB = attTensao.getValorB();
            double zB = tensaoB * iB / VALOR_RAIZ_DE_TRES;
            double PB = zB * Math.cos(anguloTensaoB - anguloCorrenteB);
            double QB = zB * Math.sin(anguloTensaoB - anguloCorrenteB);
            if (Double.isNaN(PB) || Double.isInfinite(PB)) {
                PB = 0.0;
            }
            if (Double.isNaN(QB) || Double.isInfinite(QB)) {
                QB = 0.0;
            }

            double anguloTensaoC = attAnguloTensao.getValorC();
            double anguloCorrenteC = attAnguloCorrente.getValorC();
            double iC = attCorrente.getValorC();
            double tensaoC = attTensao.getValorC();
            double zC = tensaoC * iC / VALOR_RAIZ_DE_TRES;
            double PC = zC * Math.cos(anguloTensaoC - anguloCorrenteC);
            double QC = zC * Math.sin(anguloTensaoC - anguloCorrenteC);
            if (Double.isNaN(PC) || Double.isInfinite(PC)) {
                PC = 0.0;
            }
            if (Double.isNaN(QC) || Double.isInfinite(QC)) {
                QC = 0.0;
            }

            MedidaEletrica potAtiva = new MedidaEletrica(POTENCIA_ATIVA, new Double[]{PA, PB, PC}, "kW", true);
            potAtiva.setValorResumo(potAtiva.getValorSomado());
            a.adicionaMedidaEletrica(POTENCIA_ATIVA, potAtiva);
            MedidaEletrica potReativa = new MedidaEletrica(POTENCIA_REATIVA, new Double[]{QA, QB, QC}, "kVAr", true);
            potReativa.setValorResumo(potReativa.getValorSomado());
            a.adicionaMedidaEletrica(POTENCIA_REATIVA, potReativa);
        }


        /**
         * Calcula as perdas de potencia ativa e reativa causada por um condutor
         *
         * @param aresta condutor que serï¿½ calculado
         */
        private void calculaPerdaPotencia(TrechoRede aresta) {
            MedidaEletrica att = aresta.getMedidaEletrica(CORRENTE);
            double iA = att.getValorA();
            double iB = att.getValorB();
            double iC = att.getValorC();
            double perdaAtivaA = ((iA * iA) * (aresta.getR1() * aresta.getComprimentoKm())) / 1000.0;
            double perdaReativaA = ((iA * iA) * (aresta.getX1() * aresta.getComprimentoKm())) / 1000.0;
            double perdaAtivaB = ((iB * iB) * (aresta.getR1() * aresta.getComprimentoKm())) / 1000.0;
            double perdaReativaB = ((iB * iB) * (aresta.getX1() * aresta.getComprimentoKm())) / 1000.0;
            double perdaAtivaC = ((iC * iC) * (aresta.getR1() * aresta.getComprimentoKm())) / 1000.0;
            double perdaReativaC = ((iC * iC) * (aresta.getX1() * aresta.getComprimentoKm())) / 1000.0;

            MedidaEletrica pAtiva = new MedidaEletrica(PERDA_ATIVA, new Double[]{perdaAtivaA, perdaAtivaB, perdaAtivaC}, "kW", false);
            aresta.adicionaMedidaEletrica(pAtiva.getNome(), pAtiva);
            MedidaEletrica pReativa = new MedidaEletrica(PERDA_REATIVA, new Double[]{perdaReativaA, perdaReativaB, perdaReativaC}, "kVAr", false);
            aresta.adicionaMedidaEletrica(pReativa.getNome(), pReativa);
        }
    }

    public void preExecucao(Object elemento, PatamarDeCarga patamarDeCarga, Cenario cenario) {
        montaArvoreAlimentadorEAjustaCargas(elemento, patamarDeCarga, cenario);
    }

    public void montaArvoreAlimentadorEAjustaCargas(Object elemento, PatamarDeCarga patamar, Cenario cenario) {
        Collection<Alimentador> alimentadores = new ArrayList<Alimentador>();
        if (elemento instanceof Alimentador) {
            alimentadores.add((Alimentador) elemento);
        } else if (elemento instanceof Collection) {
            alimentadores.addAll((Collection) elemento);
        }
        for (Alimentador alimentador : alimentadores) {
            Navegador.montaArvoreNavegacaoAlimentador(alimentador);
            if (!naoAjustarCargas) {
                ajustarCargaTransformadores(alimentador, patamar, cenario);
            }
        }
    }

    public ClasseConsumidor getClasseConsumidor(ConsumoPorClasseConsumidor consumoPorClasseConsumidor) {
        Collection<ClasseConsumidor> classeConsumidores = categoriasConsumo.get(consumoPorClasseConsumidor.getClasseConsumidor());
        ClasseConsumidor classeConsumidor = null;
        if (classeConsumidores != null) {
            Double consumoMedio = consumoPorClasseConsumidor.getConsumoTotal() / consumoPorClasseConsumidor.getQuantidadeConsumidores();
            for (ClasseConsumidor cc : classeConsumidores) {
                if (cc.getLimiteInferior() <= consumoMedio && (consumoMedio <= cc.getLimiteSuperior() || cc.getLimiteSuperior() == -1) && cc.getCodigo() < 100) {
                    classeConsumidor = cc;
                }
            }
        }
        return classeConsumidor;
    }

    public CurvaTipica getCurvaTipica(ConsumoPorClasseConsumidor consumoPorClasseConsumidor, Long hora) {
        ClasseConsumidor classeConsumidor = getClasseConsumidor(consumoPorClasseConsumidor);
        if (classeConsumidor != null) {
            return classeConsumidor.getCurvasTipicas().get(hora);
        }
        return null;
    }

    private int getParamar(int hora) {
        switch (hora) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                return 1;
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
                return 2;
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
                return 3;
            default:
                return 4;
        }
    }

    public enum Cenario {
        NORMAL, SMART_GRID_1, SMART_GRID_2, SMART_GRID_3;
    }

    public void ajustarCargaTransformadores(Alimentador alimentador, PatamarDeCarga patamar, Cenario cenario) {
        Random rnd = new Random(0);
        Double fatorReducao = 1.0;
        if (possuiMedidorSE(alimentador, patamar.getPatamar())) {
            fatorReducao = 0.1;
        }
        int numSG = 0;
        int total = 0;
        for (Transformador transformador : alimentador.getTransformadores()) {
            Collection<Consumo> consumosPorTrafo = transformador.getConsumos();
            int numeroConsumidoresConsumo = 0;
            double p = 0.0, q = 0.0;
            for (Consumo consumoTrafo : consumosPorTrafo) {
                if (consumoTrafo.getTipoConsumidor().equals("ET")) {
                    for (ConsumoPorClasseConsumidor consumoPorClasseConsumidor : consumoTrafo.getConsumosPorClasseConsumidor()) {
                        numeroConsumidoresConsumo += consumoPorClasseConsumidor.getQuantidadeConsumidores();
                        ClasseConsumidor classe = getClasseConsumidor(consumoPorClasseConsumidor);
                        if (classe != null) {
                            if (cenario != Cenario.NORMAL) {
                                Long codigo = classe.getCodigo();
                                if (cenario == Cenario.SMART_GRID_1) {
                                    codigo += 100;
                                } else if (cenario == Cenario.SMART_GRID_2) {
                                    codigo += 200;
                                } else if (cenario == Cenario.SMART_GRID_3) {
                                    codigo += 300;
                                }
                                ClasseConsumidor classeSG = mapClassesConsumidor.get(codigo);
                                if (classeSG != null && classeSG.getAbrangencia() != null) {
                                    double sorte = rnd.nextFloat();
                                    double abrang = classeSG.getAbrangencia() / 100.0;
                                    if (sorte < abrang) {
                                        classe = classeSG;
                                        numSG++;
                                    }
                                    total++;
                                }
                            }
                            int numDias = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH);
                            double wed = classe.getWe() * numDias;
                            double pClasse = (consumoPorClasseConsumidor.getConsumoTotal() / wed) * classe.getCurvasTipicas().get(patamar.getHoraCalculo()).getConsumoNormalizado();
                            double S = pClasse / 0.92;
                            double qClasse = Math.sqrt(Math.pow(S, 2.0) - Math.pow(pClasse, 2.0));
                            p += pClasse;
                            q += qClasse;
                        }
                    }
                } else if (consumoTrafo.getTipoConsumidor().equals("EP")) {
                    DemandaPorPeriodo demandaPorPeriodo = consumoTrafo.getDemandasPorPeriodo().get(patamar.getPatamar());
                    numeroConsumidoresConsumo++;
                    p += (demandaPorPeriodo.getDemandaAtivaFaseD() + demandaPorPeriodo.getDemandaAtivaFaseE() + demandaPorPeriodo.getDemandaAtivaFaseF());
                    q += (demandaPorPeriodo.getDemandaReativaFaseD() + demandaPorPeriodo.getDemandaReativaFaseE() + demandaPorPeriodo.getDemandaReativaFaseF());
                }
            }
            transformador.setNumConsumidores((float) numeroConsumidoresConsumo);
            if (ajustesAlimentador.get(alimentador) != null && ajustesAlimentador.get(alimentador).tipoAjuste != null &&
                    (transformador.getTipoEstacao().equals(ajustesAlimentador.get(alimentador).tipoAjuste) ||
                    ajustesAlimentador.get(alimentador).tipoAjuste.equals("ET_EP")))
            {
                p = p * fatorReducao;
                q = q * fatorReducao;
            }
            if (transformador.getFases() != null) {

                Double numFasesDouble = Double.valueOf(transformador.getFases().trim().length());
                if (numFasesDouble > 3D) {
                    numFasesDouble = 3D;
                }

                if (transformador.getFases().contains(LETRA_FASE_1)) {
                    transformador.setDemandaAtivaA(p / numFasesDouble);
                    transformador.setDemandaReativaA(q / numFasesDouble);
                } else {
                    transformador.setDemandaAtivaA(0.0);
                    transformador.setDemandaReativaA(0.0);
                }
                if (transformador.getFases().contains(LETRA_FASE_2)) {
                    transformador.setDemandaAtivaB(p / numFasesDouble);
                    transformador.setDemandaReativaB(q / numFasesDouble);
                } else {
                    transformador.setDemandaAtivaB(0.0);
                    transformador.setDemandaReativaB(0.0);
                }
                if (transformador.getFases().contains(LETRA_FASE_3)) {
                    transformador.setDemandaAtivaC(p / numFasesDouble);
                    transformador.setDemandaReativaC(q / numFasesDouble);
                } else {
                    transformador.setDemandaAtivaC(0.0);
                    transformador.setDemandaReativaC(0.0);
                }
                MedidaEletrica demandaAtiva = new MedidaEletrica(DEMANDA_ATIVA,
                        new Double[]{transformador.getDemandaAtivaA(), transformador.getDemandaAtivaB(), transformador.getDemandaAtivaC()},
                        "kW", true, new Date());
                demandaAtiva.setValorResumo(demandaAtiva.getValorSomado());
                MedidaEletrica demandaReativa = new MedidaEletrica(DEMANDA_REATIVA,
                        new Double[]{transformador.getDemandaReativaA(), transformador.getDemandaReativaB(), transformador.getDemandaReativaC()},
                        "kVAr", true, new Date());
                demandaReativa.setValorResumo(demandaReativa.getValorSomado());
                transformador.getBarra().adicionaMedidaEletrica(DEMANDA_ATIVA, demandaAtiva);
                transformador.getBarra().adicionaMedidaEletrica(DEMANDA_REATIVA, demandaReativa);
            } else {
                transformador.setDemandaAtivaA(0.0);
                transformador.setDemandaReativaA(0.0);
                transformador.setDemandaAtivaB(0.0);
                transformador.setDemandaReativaB(0.0);
                transformador.setDemandaAtivaC(0.0);
                transformador.setDemandaReativaC(0.0);

                MedidaEletrica demandaAtiva = new MedidaEletrica(DEMANDA_ATIVA,
                        new Double[]{ 0.0, 0.0, 0.0},
                        "kW", true, new Date());
                demandaAtiva.setValorResumo(0.0);
                MedidaEletrica demandaReativa = new MedidaEletrica(DEMANDA_REATIVA,
                        new Double[]{0.0, 0.0, 0.0},
                        "kVAr", true, new Date());
                demandaReativa.setValorResumo(0.0);
                transformador.getBarra().adicionaMedidaEletrica(DEMANDA_ATIVA, demandaAtiva);
                transformador.getBarra().adicionaMedidaEletrica(DEMANDA_REATIVA, demandaReativa);
            }
        }
        if (cenario != Cenario.NORMAL) {
            getSucessos().add("Alteradas " + numSG + "(total: " + total + ") curvas típicas para cenário " + cenario.toString());
        }
    }

    public static double getValorDemandaTrafo(Transformador transformador) {
        //TODO: Vai ter que ver o ajuste dos tranformadores que vï¿½o usar curvas tipicas de carga.
        //TODO: Pra teste por enquanto estamos somente assumindo carga de PORCENTAGEM_TESTE_CARREGAMENTO_TRAFO (30%) da potencia nominal do trafo.
        return PORCENTAGEM_TESTE_CARREGAMENTO_TRAFO * transformador.getPotenciaNominal();
    }
}
