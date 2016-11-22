package br.ufsm.ceesp.ceee.algoritmos.impl;

import br.com.mega.silas.algoritmos.IProgresso;
import br.com.mega.silas.algoritmos.implementacao.Algoritmo;
import br.com.mega.silas.algoritmos.implementacao.ParametroAlgoritmo;
import br.com.mega.silas.algoritmos.implementacao.TipoParametroAlgoritmo;
import br.ufsm.ceesp.ceee.algoritmos.util.AcaoPosFixada;
import br.ufsm.ceesp.ceee.algoritmos.util.Navegador;
import br.ufsm.ceesp.ceee.algoritmos.util.SemAcaoPreFixada;
import br.ufsm.ceesp.ceee.model.*;

import java.util.*;

/**
 * Created by Rafael on 13/03/2015.
 */
public class CalculoIndicadoresConfiabilidade extends Algoritmo {

    public static final String VERSAO = "Release 1.0-220916";
    private Map<Alimentador, Long> totalConsumidoresAL = new HashMap<Alimentador, Long>();
    private Map<Alimentador, Long> totalConsumidoresConj = new HashMap<Alimentador, Long>();
    private long totalConsumidoresAES = 0;

    @Override
    public void preExecucao(List<Object> list, List<Map<String, ParametroAlgoritmo>> list1, IProgresso iProgresso) {

    }

    @Override
    public List<Object> executar(List<Object> collection, List<Map<String, ParametroAlgoritmo>> parametros, IProgresso iProgresso) {
        List<Alimentador> alimentadores = new ArrayList<Alimentador>();
        for (Object o : collection) {
            alimentadores.add((Alimentador) o);
        }
        int i = 0;
        for (Map<String, ParametroAlgoritmo> map : parametros) {
            Alimentador al = alimentadores.get(i);
            if ((map.get("Nº Cons. Al.") == null || map.get("Nº Cons. Al.").getValor() == null) && al.getNumConsumidores() == null) {
                erros.add("É preciso executar o Fluxo de Potência antes do Cálculo dos Indicadores de Confiabilidade.");
                return collection;
            }
            if (map.get("Nº Cons. Al.") == null || map.get("Nº Cons. Al.").getValor() == null) {
                totalConsumidoresAL.put(al, al.getNumConsumidores().longValue());
            } else {
                totalConsumidoresAL.put(al, (Long) map.get("Nº Cons. Al.").getValor());
            }
            totalConsumidoresConj.put(al, (map.get("Nº Cons. Conj.").getValor() == null ? 0 : (Long) map.get("Nº Cons. Conj.").getValor()));
            totalConsumidoresAES = (Long) map.get("Nº Cons. AES").getValor();
            i++;
        }
        preExecucao(alimentadores);
        i = 0;
        for (Alimentador alimentador : alimentadores) {
            Map<String, ParametroAlgoritmo> map = parametros.get(i++);
            Map<String, Double> mapTaxaFalhas = mapTaxaFalhasAlimentador.get(alimentador);
            if (mapTaxaFalhas == null) {
                CalculaDistanciasChaves algoritmoDistancias = new CalculaDistanciasChaves(map);
                Navegador.percorreArvore(alimentador.getTrechoRedeInicial(), null, SemAcaoPreFixada.INSTANCE, algoritmoDistancias, true, erros);
                mapTaxaFalhas = algoritmoDistancias.getMapTaxaFalhas();
                mapTaxaFalhasAlimentador.put(alimentador, mapTaxaFalhas);
            }
            CalculaValoresChaves algoritmoValores = new CalculaValoresChaves(mapTaxaFalhas);
            Double[] result = (Double[]) Navegador.percorreArvore(alimentador.getTrechoRedeInicial(), null, SemAcaoPreFixada.INSTANCE, algoritmoValores, true, erros);
            ResultadosAlimentador resultadosAlimentador = alimentador.getResultadosAlimentador();
            if (resultadosAlimentador == null) {
                resultadosAlimentador = new ResultadosAlimentador();
                resultadosAlimentador.setAlimentador(alimentador);
                alimentador.setResultadosAlimentador(resultadosAlimentador);
            }
            resultadosAlimentador.setDataDiagnostico(new Date());
            resultadosAlimentador.setDec(result[2] / totalConsumidoresAL.get(alimentador));
            resultadosAlimentador.setFec(result[3] / totalConsumidoresAL.get(alimentador));
            resultadosAlimentador.setDecConjunto(result[2] / totalConsumidoresConj.get(alimentador));
            resultadosAlimentador.setFecConjunto(result[3] / totalConsumidoresConj.get(alimentador));
            resultadosAlimentador.setDecAES(result[2] / this.totalConsumidoresAES);
            resultadosAlimentador.setFecAES(result[3] / this.totalConsumidoresAES);
            resultadosAlimentador.setDecHistAES(alimentador.getConfiabilidade().getDecHistoricoConjunto() * ((double) this.totalConsumidoresConj.get(alimentador) / (double) this.totalConsumidoresAES));
            resultadosAlimentador.setFecHistAES(alimentador.getConfiabilidade().getFecHistoricoConjunto() * ((double) this.totalConsumidoresConj.get(alimentador) / (double) this.totalConsumidoresAES));
            resultadosAlimentador.setEns(result[4]);
        }
        getSucessos().add(VERSAO);
        return null;
    }

    @Override
    public void posExecucao(List<Object> list, List<Map<String, ParametroAlgoritmo>> list1, IProgresso iProgresso) {

    }

    public CalculoIndicadoresConfiabilidade() {

        List<TipoParametroAlgoritmo> parametros = new ArrayList<TipoParametroAlgoritmo>();
        TipoParametroAlgoritmo tipo = new TipoParametroAlgoritmo();
        tipo.setChaveConsulta("falha (AL)");
        tipo.setClasseTipoValor(Integer.class);
        tipo.setTipoValorParametro(TipoParametroAlgoritmo.TipoValorParametro.INTEIRO);
        tipo.setNome("Total de Falhas (AL)");
        tipo.setCampoValorPadrao("OUTconfiabilidade.OUTtaxasFalhasAlimentador.OUTtaxaFalhaTotal");
        parametros.add(tipo);

        tipo = new TipoParametroAlgoritmo();
        tipo.setChaveConsulta("falha (RL)");
        tipo.setClasseTipoValor(Integer.class);
        tipo.setTipoValorParametro(TipoParametroAlgoritmo.TipoValorParametro.INTEIRO);
        tipo.setNome("Total de Falhas (RL)");
        tipo.setCampoValorPadrao("OUTconfiabilidade.OUTtaxasFalhasReligador.OUTtaxaFalhaTotal");
        parametros.add(tipo);

        tipo = new TipoParametroAlgoritmo();
        tipo.setChaveConsulta("falha (FR)");
        tipo.setClasseTipoValor(Integer.class);
        tipo.setTipoValorParametro(TipoParametroAlgoritmo.TipoValorParametro.INTEIRO);
        tipo.setCampoValorPadrao("OUTconfiabilidade.OUTtaxasFalhasChaveFusivelRepetidora.OUTtaxaFalhaTotal");
        tipo.setNome("Total de Falhas (FR)");
        parametros.add(tipo);

        tipo = new TipoParametroAlgoritmo();
        tipo.setChaveConsulta("falha (CF)");
        tipo.setClasseTipoValor(Integer.class);
        tipo.setTipoValorParametro(TipoParametroAlgoritmo.TipoValorParametro.INTEIRO);
        tipo.setCampoValorPadrao("OUTconfiabilidade.OUTtaxasFalhasChaveFusivel.OUTtaxaFalhaTotal");
        tipo.setNome("Total de Falhas (CF)");
        parametros.add(tipo);


        tipo = new TipoParametroAlgoritmo();
        tipo.setChaveConsulta("Dméd. (AL)");
        tipo.setClasseTipoValor(Double.class);
        tipo.setTipoValorParametro(TipoParametroAlgoritmo.TipoValorParametro.DUPLA_PRECISAO);
        tipo.setNome("Duração Média AL (h)");
        tipo.setCampoValorPadrao("OUTconfiabilidade.OUTtaxasFalhasAlimentador.OUTduracaoMedia");
        parametros.add(tipo);

        tipo = new TipoParametroAlgoritmo();
        tipo.setChaveConsulta("Dméd. (RL)");
        tipo.setClasseTipoValor(Double.class);
        tipo.setTipoValorParametro(TipoParametroAlgoritmo.TipoValorParametro.DUPLA_PRECISAO);
        tipo.setNome("Duração Média RL (h)");
        tipo.setCampoValorPadrao("OUTconfiabilidade.OUTtaxasFalhasReligador.OUTduracaoMedia");
        parametros.add(tipo);

        tipo = new TipoParametroAlgoritmo();
        tipo.setChaveConsulta("Dméd. (FR)");
        tipo.setClasseTipoValor(Double.class);
        tipo.setTipoValorParametro(TipoParametroAlgoritmo.TipoValorParametro.DUPLA_PRECISAO);
        tipo.setNome("Duração Média FR (h)");
        tipo.setCampoValorPadrao("OUTconfiabilidade.OUTtaxasFalhasChaveFusivelRepetidora.OUTduracaoMedia");
        parametros.add(tipo);

        tipo = new TipoParametroAlgoritmo();
        tipo.setChaveConsulta("Dméd. (CF)");
        tipo.setClasseTipoValor(Double.class);
        tipo.setTipoValorParametro(TipoParametroAlgoritmo.TipoValorParametro.DUPLA_PRECISAO);
        tipo.setNome("Duração Média CF (h)");
        tipo.setCampoValorPadrao("OUTconfiabilidade.OUTtaxasFalhasChaveFusivel.OUTduracaoMedia");
        parametros.add(tipo);

        tipo = new TipoParametroAlgoritmo();
        tipo.setChaveConsulta("Nº Cons. Al.");
        tipo.setClasseTipoValor(Long.class);
        tipo.setTipoValorParametro(TipoParametroAlgoritmo.TipoValorParametro.LONGO);
        tipo.setNome("Nº Cons. Al.");
        tipo.setCampoValorPadrao("OUTNumConsumidoresAlimentador");
        parametros.add(tipo);

        tipo = new TipoParametroAlgoritmo();
        tipo.setChaveConsulta("Nº Cons. Conj.");
        tipo.setClasseTipoValor(Long.class);
        tipo.setTipoValorParametro(TipoParametroAlgoritmo.TipoValorParametro.LONGO);
        tipo.setNome("Nº Cons. Conj.");
        tipo.setCampoValorPadrao("OUTquantidadeClientesConjunto");
        parametros.add(tipo);

        tipo = new TipoParametroAlgoritmo();
        tipo.setChaveConsulta("Nº Cons. AES");
        tipo.setClasseTipoValor(Long.class);
        tipo.setTipoValorParametro(TipoParametroAlgoritmo.TipoValorParametro.LONGO);
        tipo.setNome("Nº Cons. AES");
        tipo.setCampoValorPadrao("OUTquantidadeClientesAES");
        parametros.add(tipo);

        tipo = new TipoParametroAlgoritmo();
        tipo.setChaveConsulta("Transf. Manual");
        tipo.setClasseTipoValor(Double.class);
        tipo.setTipoValorParametro(TipoParametroAlgoritmo.TipoValorParametro.DUPLA_PRECISAO);
        tipo.setCampoValorPadrao("OUTtempoMedioTransferenciaManual");
        tipo.setNome("Tempo Médio de Transf. Manual (h)");
        parametros.add(tipo);

        tipo = new TipoParametroAlgoritmo();
        tipo.setChaveConsulta("Transf. Automática");
        tipo.setClasseTipoValor(Double.class);
        tipo.setTipoValorParametro(TipoParametroAlgoritmo.TipoValorParametro.DUPLA_PRECISAO);
        tipo.setCampoValorPadrao("OUTtempoMedioTransferenciaAutomatico");
        tipo.setNome("Tempo Médio de Transf. Automática (h)");
        parametros.add(tipo);

        setTiposParametros(parametros);
    }

    public void preExecucao(Object o) {
        Collection<Alimentador> alimentadores = new ArrayList<Alimentador>();
        if (o instanceof Alimentador) {
            alimentadores.add((Alimentador) o);
        } else if (o instanceof Collection) {
            alimentadores.addAll((Collection) o);
        }
        for (Alimentador alimentador : alimentadores) {
            Navegador.montaArvoreNavegacaoAlimentador(alimentador);
        }
    }

    public static final String DISTANCIA_ACUMULADA_PROTECAO = "Distância Acumulada Equip. Proteção";
    public static final String CONSUMIDORES_ACUMULADOS_PROTECAO = "Num. Consumidores à Jusante";
    public static final String DEC = "DEC Conjunto";
    public static final String FEC = "FEC Conjunto";
    public static final String ENS = "ENS";

    private boolean armazenaDECNos = false;

    private Map<INavegavel, Double[]> nosDistDEC;

    public boolean isArmazenaDECNos() {
        return armazenaDECNos;
    }

    public void setArmazenaDECNos(boolean armazenaDECNos) {
        this.armazenaDECNos = armazenaDECNos;
        if (armazenaDECNos) {
            nosDistDEC = new HashMap<INavegavel, Double[]>();
        }
    }

    //private long totalConsumidores = 0;

    private class CalculaValoresChaves implements AcaoPosFixada {

        private Map<String, Double> taxasDeFalha;

        private CalculaValoresChaves(Map<String, Double> params) {
            this.taxasDeFalha = params;
        }

        @Override
        public Object executePos(INavegavel n, INavegavel anterior, Object params) {
            Double[] ret = (Double[]) params;
            if (ret == null) {
                ret = new Double[] { 0.0, 0.0, 0.0, 0.0, 0.0 };
            }
            if (n instanceof ChaveSocorro) {
                ChaveSocorro chaveSocorro = (ChaveSocorro) n;
                if (chaveSocorro.getEstadoAberturaAtual().equals(ChaveSocorro.ESTADO_FECHADA)) {
                    if (chaveSocorro.getNomeDispositivoProtecao().toUpperCase().contains("FUL") || chaveSocorro.getNomeDispositivoProtecao().toUpperCase().contains("FUS")) {
                        MedidaEletrica attP = chaveSocorro.getMedidaEletrica(FluxoPotenciaTrifasico.POTENCIA_ATIVA);
                        Double p = attP == null ? 0.0 : attP.getValorSomado();

                        double sigma = this.taxasDeFalha.get("falha (CF)") != null ? this.taxasDeFalha.get("falha (CF)") : 0.0;
                        double dmed = this.taxasDeFalha.get("Dméd. (CF)") != null ? this.taxasDeFalha.get("Dméd. (CF)") : 0.0;
                        double dec_semdiv = (sigma * ret[0] * ret[1] * dmed);
                        double fec_semdiv = (sigma * ret[0] * ret[1]);
                        double ens = (sigma * ret[0] * p * dmed) / 1000;
                        if (armazenaDECNos) {
                            nosDistDEC.put(chaveSocorro, new Double[] { ret[0], dec_semdiv });
                        }
                        ret[0] = 0.0;
                        ret[2] += dec_semdiv;
                        ret[3] += fec_semdiv;
                        ret[4] += ens;
                        chaveSocorro.adicionaMedidaEletrica(DEC, new MedidaEletrica(DEC, new Double[] { dec_semdiv / CalculoIndicadoresConfiabilidade.this.totalConsumidoresConj.get(chaveSocorro.getAlimentador()), 0.0, 0.0 }, "hora/ano", true));
                        chaveSocorro.adicionaMedidaEletrica(FEC, new MedidaEletrica(FEC, new Double[] { fec_semdiv / CalculoIndicadoresConfiabilidade.this.totalConsumidoresConj.get(chaveSocorro.getAlimentador()), 0.0, 0.0 }, "interrupções/ano", true));
                        chaveSocorro.adicionaMedidaEletrica(ENS, new MedidaEletrica(ENS, new Double[] { ens, 0.0, 0.0 }, "MWh/ano", true));

                    } else if (chaveSocorro.getNomeDispositivoProtecao().toUpperCase().contains("REL")) {
                        MedidaEletrica attP = chaveSocorro.getMedidaEletrica(FluxoPotenciaTrifasico.POTENCIA_ATIVA);
                        Double p = attP == null ? 0.0 : attP.getValorSomado();
                        double sigma = this.taxasDeFalha.get("falha (RL)") != null ? this.taxasDeFalha.get("falha (RL)") : 0.0;
                        double dmed = this.taxasDeFalha.get("Dméd. (RL)") != null ? this.taxasDeFalha.get("Dméd. (RL)") : 0.0;

                        BuscaPontosNotaveis buscaPontosNotaveis = new BuscaPontosNotaveis();
                        List<Chave> pontosNotaveis = (List<Chave>) Navegador.navega(anterior, n, null, SemAcaoPreFixada.INSTANCE, buscaPontosNotaveis, new HashMap<Barra, INavegavel>(), n.getIdentificacaoAlimentador(), true, erros, 0);

                        long totalConsumidoresPontosNot = 0;
                        double cargaPNs = 0.0;
                        if (pontosNotaveis != null) {
                            //pontosNotaveis.remove(ch.getChave());
                            for (Chave pn : pontosNotaveis) {
                                totalConsumidoresPontosNot += pn.getConsumidores();
                                attP = pn.getTrechoRede().getMedidaEletrica(FluxoPotenciaTrifasico.POTENCIA_ATIVA);
                                if (attP != null) {
                                    cargaPNs += attP.getValorSomado();
                                }
                            }
                        }

                        double tmpManual = this.taxasDeFalha.get("Transf. Manual") != null ? this.taxasDeFalha.get("Transf. Manual") : 0.0;
                        double tmpAutomatica = this.taxasDeFalha.get("Transf. Automática") != null ? this.taxasDeFalha.get("Transf. Automática") : 0.0;

                        double dec_semdiv = (sigma * ret[0] * (ret[1] - totalConsumidoresPontosNot) * dmed);
                        double fec_semdiv = (sigma * ret[0] * ret[1]);
                        double ens = (sigma * ret[0] * (p - cargaPNs) * dmed) / 1000;
                        if (totalConsumidoresPontosNot > 0 || cargaPNs > 0.0) {
                            for (Chave pn : pontosNotaveis) {
                                attP = pn.getTrechoRede().getMedidaEletrica(FluxoPotenciaTrifasico.POTENCIA_ATIVA);
                                double ppn = 0.0;
                                if (attP != null) {
                                    ppn = attP.getValorSomado();
                                }
                                if (pn.isTelecomandavel()) {
                                    dec_semdiv += (sigma * ret[0] * pn.getConsumidores() * tmpAutomatica);
                                    ens += (sigma * ret[0] * ppn * tmpAutomatica) / 1000;
                                } else {
                                    dec_semdiv += (sigma * ret[0] * pn.getConsumidores() * tmpManual);
                                    ens += (sigma * ret[0] * ppn * tmpManual) / 1000;
                                }
                            }
                        }
                        if (armazenaDECNos) {
                            nosDistDEC.put(chaveSocorro, new Double[] { ret[0], dec_semdiv });
                        }
                        ret[0] = 0.0;
                        ret[2] += dec_semdiv;
                        ret[3] += fec_semdiv;
                        ret[4] += ens;
                        chaveSocorro.adicionaMedidaEletrica(DEC, new MedidaEletrica(DEC, new Double[] { dec_semdiv / CalculoIndicadoresConfiabilidade.this.totalConsumidoresConj.get(chaveSocorro.getAlimentador()), 0.0, 0.0 }, "hora/ano", true));
                        chaveSocorro.adicionaMedidaEletrica(FEC, new MedidaEletrica(FEC, new Double[] { fec_semdiv / CalculoIndicadoresConfiabilidade.this.totalConsumidoresConj.get(chaveSocorro.getAlimentador()), 0.0, 0.0 }, "interrupções/ano", true));
                        chaveSocorro.adicionaMedidaEletrica(ENS, new MedidaEletrica(ENS, new Double[] { ens, 0.0, 0.0 }, "MWh/ano", true));
                    }
                }
            } else if (n instanceof TrechoRede) {
                TrechoRede a = (TrechoRede) n;
                if (!a.isObraNova()) {
                    ret[0] += a.getComprimentoKm();
                }
                if (a.getChave() != null) {
                    a.getChave().setConsumidores(ret[1].longValue());
                    if (a.getChave().getTipoChave().getTipo().contains("Chave Fusivel")) {
                        MedidaEletrica attP = a.getMedidaEletrica(FluxoPotenciaTrifasico.POTENCIA_ATIVA);
                        Double p = attP == null ? 0.0 : attP.getValorSomado();
                        Double sigma = mapTaxaFalhasAlimentador.get(a.getAlimentador()) != null ? mapTaxaFalhasAlimentador.get(a.getAlimentador()).get("falha (CF)") : this.taxasDeFalha.get("falha (CF)");
                        Double dmed = mapTaxaFalhasAlimentador.get(a.getAlimentador()) != null ? mapTaxaFalhasAlimentador.get(a.getAlimentador()).get("Dméd. (CF)") : this.taxasDeFalha.get("Dméd. (CF)");
                        sigma = (sigma != null ? sigma : 0.0);
                        dmed = (dmed != null ? dmed : 0.0);

                        double dec_semdiv = (sigma * ret[0] * ret[1] * dmed);
                        double fec_semdiv = (sigma * ret[0] * ret[1]);
                        double ens = (sigma * ret[0] * p * dmed) / 1000;
                        if (armazenaDECNos) {
                            nosDistDEC.put(a, new Double[] { ret[0], dec_semdiv });
                        }
                        ret[0] = 0.0;
                        ret[2] += dec_semdiv;
                        ret[3] += fec_semdiv;
                        ret[4] += ens;
                        a.adicionaMedidaEletrica(DEC, new MedidaEletrica(DEC, new Double[] { dec_semdiv / CalculoIndicadoresConfiabilidade.this.totalConsumidoresConj.get(a.getAlimentador()), 0.0, 0.0 }, "hora/ano", true));
                        a.adicionaMedidaEletrica(FEC, new MedidaEletrica(FEC, new Double[] { fec_semdiv / CalculoIndicadoresConfiabilidade.this.totalConsumidoresConj.get(a.getAlimentador()), 0.0, 0.0 }, "interrupções/ano", true));
                        a.adicionaMedidaEletrica(ENS, new MedidaEletrica(ENS, new Double[] { ens, 0.0, 0.0 }, "MWh/ano", true));
                    } else if (a.getChave().getTipoChave().getTipo().toUpperCase().contains("RELIGADOR")) {
                        MedidaEletrica attP = a.getMedidaEletrica(FluxoPotenciaTrifasico.POTENCIA_ATIVA);
                        Double p = attP == null ? 0.0 : attP.getValorSomado();
                        Double sigma = mapTaxaFalhasAlimentador.get(a.getAlimentador()) != null ? mapTaxaFalhasAlimentador.get(a.getAlimentador()).get("falha (RL)") : this.taxasDeFalha.get("falha (RL)");
                        Double dmed = mapTaxaFalhasAlimentador.get(a.getAlimentador()) != null ? mapTaxaFalhasAlimentador.get(a.getAlimentador()).get("Dméd. (RL)") : this.taxasDeFalha.get("Dméd. (RL)");
                        sigma = (sigma != null ? sigma : 0.0);
                        dmed = (dmed != null ? dmed : 0.0);

                        BuscaPontosNotaveis buscaPontosNotaveis = new BuscaPontosNotaveis();
                        List<Chave> pontosNotaveis = (List<Chave>) Navegador.navega(anterior, n, null, SemAcaoPreFixada.INSTANCE, buscaPontosNotaveis, new HashMap<Barra, INavegavel>(), n.getIdentificacaoAlimentador(), true, erros, 0);

                        long totalConsumidoresPontosNot = 0;
                        double cargaPNs = 0.0;
                        if (pontosNotaveis != null) {
                            pontosNotaveis.remove(a.getChave());
                            for (Chave pn : pontosNotaveis) {
                                totalConsumidoresPontosNot += pn.getConsumidores();
                                attP = pn.getTrechoRede().getMedidaEletrica(FluxoPotenciaTrifasico.POTENCIA_ATIVA);
                                if (attP != null) {
                                    cargaPNs += attP.getValorSomado();
                                }
                            }
                        }

                        Double tmpManual = mapTaxaFalhasAlimentador.get(a.getAlimentador()) != null ? mapTaxaFalhasAlimentador.get(a.getAlimentador()).get("Transf. Manual") : this.taxasDeFalha.get("Transf. Manual");
                        Double tmpAutomatica = mapTaxaFalhasAlimentador.get(a.getAlimentador()) != null ? mapTaxaFalhasAlimentador.get(a.getAlimentador()).get("Transf. Automática") : this.taxasDeFalha.get("Transf. Automática");
                        tmpManual = (tmpManual != null ? tmpManual : 0.0);
                        tmpAutomatica = (tmpAutomatica != null ? tmpAutomatica : 0.0);

                        double dec_semdiv = (sigma * ret[0] * (ret[1] - totalConsumidoresPontosNot) * dmed);
                        double fec_semdiv = (sigma * ret[0] * ret[1]);
                        double ens = (sigma * ret[0] * (p - cargaPNs) * dmed) / 1000;
                        if (totalConsumidoresPontosNot > 0 || cargaPNs > 0.0) {
                            for (Chave pn : pontosNotaveis) {
                                attP = pn.getTrechoRede().getMedidaEletrica(FluxoPotenciaTrifasico.POTENCIA_ATIVA);
                                double ppn = 0.0;
                                if (attP != null) {
                                    ppn = attP.getValorSomado();
                                }
                                if (pn.isTelecomandavel()) {
                                    dec_semdiv += (sigma * ret[0] * pn.getConsumidores() * tmpAutomatica);
                                    ens += (sigma * ret[0] * ppn * tmpAutomatica) / 1000;
                                } else {
                                    dec_semdiv += (sigma * ret[0] * pn.getConsumidores() * tmpManual);
                                    ens += (sigma * ret[0] * ppn * tmpManual) / 1000;
                                }
                            }
                        }
                        if (armazenaDECNos) {
                            nosDistDEC.put(a, new Double[] { ret[0], dec_semdiv });
                        }
                        ret[0] = 0.0;
                        ret[2] += dec_semdiv;
                        ret[3] += fec_semdiv;
                        ret[4] += ens;
                        a.adicionaMedidaEletrica(DEC, new MedidaEletrica(DEC, new Double[] { dec_semdiv / CalculoIndicadoresConfiabilidade.this.totalConsumidoresConj.get(a.getAlimentador()), 0.0, 0.0 }, "hora/ano", true));
                        a.adicionaMedidaEletrica(FEC, new MedidaEletrica(FEC, new Double[] { fec_semdiv / CalculoIndicadoresConfiabilidade.this.totalConsumidoresConj.get(a.getAlimentador()), 0.0, 0.0 }, "interrupções/ano", true));
                        a.adicionaMedidaEletrica(ENS, new MedidaEletrica(ENS, new Double[] { ens, 0.0, 0.0 }, "MWh/ano", true));
                    } else if (a.getChave().getTipoChave().getTipo().toUpperCase().contains("REPETIDOR")) {
                        MedidaEletrica attP = a.getMedidaEletrica(FluxoPotenciaTrifasico.POTENCIA_ATIVA);
                        Double p = attP == null ? 0.0 : attP.getValorSomado();
                        Double sigma = mapTaxaFalhasAlimentador.get(a.getAlimentador()) != null ? mapTaxaFalhasAlimentador.get(a.getAlimentador()).get("falha (FR)") : this.taxasDeFalha.get("falha (FR)");
                        Double dmed = mapTaxaFalhasAlimentador.get(a.getAlimentador()) != null ? mapTaxaFalhasAlimentador.get(a.getAlimentador()).get("Dméd. (FR)") : this.taxasDeFalha.get("Dméd. (FR)");
                        sigma = (sigma != null ? sigma : 0.0);
                        dmed = (dmed != null ? dmed : 0.0);

                        double dec_semdiv = (sigma * ret[0] * ret[1] * dmed);
                        double fec_semdiv = (sigma * ret[0] * ret[1]);
                        double ens = (sigma * ret[0] * p * dmed) / 1000;
                        if (armazenaDECNos) {
                            nosDistDEC.put(a, new Double[] { ret[0], dec_semdiv });
                        }
                        ret[0] = 0.0;
                        ret[2] += dec_semdiv;
                        ret[3] += fec_semdiv;
                        ret[4] += ens;
                        a.adicionaMedidaEletrica(DEC, new MedidaEletrica(DEC, new Double[] { dec_semdiv / CalculoIndicadoresConfiabilidade.this.totalConsumidoresConj.get(a.getAlimentador()), 0.0, 0.0 }, "hora/ano", true));
                        a.adicionaMedidaEletrica(FEC, new MedidaEletrica(FEC, new Double[] { fec_semdiv / CalculoIndicadoresConfiabilidade.this.totalConsumidoresConj.get(a.getAlimentador()), 0.0, 0.0 }, "interrupções/ano", true));
                        a.adicionaMedidaEletrica(ENS, new MedidaEletrica(ENS, new Double[] { ens, 0.0, 0.0 }, "MWh/ano", true));
                    } else if (a.getChave().getTipoChave().getTipo().toUpperCase().contains("DISJUNTOR")) {
                        MedidaEletrica attP = a.getMedidaEletrica(FluxoPotenciaTrifasico.POTENCIA_ATIVA);
                        Double p = attP == null ? 0.0 : attP.getValorSomado();

                        Double sigma = mapTaxaFalhasAlimentador.get(a.getAlimentador()) != null ? mapTaxaFalhasAlimentador.get(a.getAlimentador()).get("falha (AL)") : this.taxasDeFalha.get("falha (AL)");
                        Double dmed = mapTaxaFalhasAlimentador.get(a.getAlimentador()) != null ? mapTaxaFalhasAlimentador.get(a.getAlimentador()).get("Dméd. (AL)") : this.taxasDeFalha.get("Dméd. (AL)");
                        sigma = (sigma != null ? sigma : 0.0);
                        dmed = (dmed != null ? dmed : 0.0);

                        BuscaPontosNotaveis buscaPontosNotaveis = new BuscaPontosNotaveis();
                        List<Chave> pontosNotaveis = (List<Chave>) Navegador.navega(anterior, n, null, SemAcaoPreFixada.INSTANCE, buscaPontosNotaveis, new HashMap<Barra, INavegavel>(), n.getIdentificacaoAlimentador(), true, erros, 0);

                        long totalConsumidoresPontosNot = 0;
                        double cargaPNs = 0.0;
                        if (pontosNotaveis != null) {
                            pontosNotaveis.remove(a.getChave());
                            for (Chave pn : pontosNotaveis) {
                                totalConsumidoresPontosNot += pn.getConsumidores();
                                attP = pn.getTrechoRede().getMedidaEletrica(FluxoPotenciaTrifasico.POTENCIA_ATIVA);
                                if (attP != null) {
                                    cargaPNs += attP.getValorSomado();
                                }
                            }
                        }

                        Double tmpManual = mapTaxaFalhasAlimentador.get(a.getAlimentador()) != null ? mapTaxaFalhasAlimentador.get(a.getAlimentador()).get("Transf. Manual") : this.taxasDeFalha.get("Transf. Manual");
                        Double tmpAutomatica = mapTaxaFalhasAlimentador.get(a.getAlimentador()) != null ? mapTaxaFalhasAlimentador.get(a.getAlimentador()).get("Transf. Automática") : this.taxasDeFalha.get("Transf. Automática");
                        tmpManual = (tmpManual != null ? tmpManual : 0.0);
                        tmpAutomatica = (tmpAutomatica != null ? tmpAutomatica : 0.0);

                        double dec_semdiv = (sigma * ret[0] * (ret[1] - totalConsumidoresPontosNot) * dmed);
                        double fec_semdiv = (sigma * ret[0] * ret[1]);
                        double ens = (sigma * ret[0] * (p - cargaPNs) * dmed) / 1000;
                        if (totalConsumidoresPontosNot > 0 || cargaPNs > 0.0) {
                            for (Chave pn : pontosNotaveis) {
                                attP = pn.getTrechoRede().getMedidaEletrica(FluxoPotenciaTrifasico.POTENCIA_ATIVA);
                                double ppn = 0.0;
                                if (attP != null) {
                                    ppn = attP.getValorSomado();
                                }
                                if (pn.isTelecomandavel()) {
                                    dec_semdiv += (sigma * ret[0] * pn.getConsumidores() * tmpAutomatica);
                                    ens += (sigma * ret[0] * ppn * tmpAutomatica) / 1000;
                                } else {
                                    dec_semdiv += (sigma * ret[0] * pn.getConsumidores() * tmpManual);
                                    ens += (sigma * ret[0] * ppn * tmpManual) / 1000;
                                }
                            }
                        }
                        if (armazenaDECNos) {
                            nosDistDEC.put(a, new Double[] { ret[0], dec_semdiv });
                        }
                        ret[0] = 0.0;
                        ret[2] += dec_semdiv;
                        ret[3] += fec_semdiv;
                        ret[4] += ens;
                        a.adicionaMedidaEletrica(DEC, new MedidaEletrica(DEC, new Double[] { dec_semdiv / CalculoIndicadoresConfiabilidade.this.totalConsumidoresConj.get(a.getAlimentador()), 0.0, 0.0 }, "hora/ano", true));
                        a.adicionaMedidaEletrica(FEC, new MedidaEletrica(FEC, new Double[] { fec_semdiv / CalculoIndicadoresConfiabilidade.this.totalConsumidoresConj.get(a.getAlimentador()), 0.0, 0.0 }, "interrupções/ano", true));
                        a.adicionaMedidaEletrica(ENS, new MedidaEletrica(ENS, new Double[] { ens, 0.0, 0.0 }, "MWh/ano", true));
                    }
                }
            } else if (n instanceof Barra) {
                Barra v = (Barra) n;
                if (v.getTransformador() != null) {
                    Transformador trafo = v.getTransformador();
                    ret[1] += (double) trafo.getNumConsumidores();
                }
            }
            return ret;
        }

        @Override
        public Object operaResultados(Object resultadoParcial, Object resultadoRamo) {
            Double[] arr1 = (Double[]) resultadoParcial;
            Double[] arr2 = (Double[]) resultadoRamo;
            if (arr1 == null) arr1 = new Double[] { 0.0, 0.0, 0.0, 0.0, 0.0 };
            if (arr2 == null) arr2 = new Double[] { 0.0, 0.0, 0.0, 0.0, 0.0 };
            return new Double[] { arr1[0] + arr2[0], arr1[1] + arr2[1] , arr1[2] + arr2[2], arr1[3] + arr2[3], arr1[4] + arr2[4] };
        }
    }

    private class BuscaPontosNotaveis implements AcaoPosFixada {

        @Override
        public Object executePos(INavegavel n, INavegavel anterior, Object params) {

            if (n instanceof TrechoRede && ((TrechoRede) n).getChave() != null) {
                Chave chave = ((TrechoRede) n).getChave();
                if (chave.getPontoNotavel() != null && chave.getPontoNotavel()) {
                    params = new ArrayList<Chave>();
                    ((List<Chave>) params).add(chave);
                }
            }
            return params;
        }

        @Override
        public Object operaResultados(Object resultadoParcial, Object resultadoRamo) {
            if (resultadoParcial != null && resultadoRamo != null) {
                List<Chave> p1 = (List<Chave>) resultadoParcial;
                List<Chave> p2 = (List<Chave>) resultadoRamo;
                p1.addAll(p2);
                return p1;
            } else if (resultadoParcial == null) {
                return resultadoRamo;
            } else {
                return resultadoParcial;
            }
        }
    }

    private class CalculaDistanciasChaves implements AcaoPosFixada {

        private Map<String, ParametroAlgoritmo> paramsAlgoritmo;
        private Map<String, Double> distanciasEquipamentos = new HashMap<String, Double>();


        private CalculaDistanciasChaves(Map<String, ParametroAlgoritmo> params) {
            this.paramsAlgoritmo = params;
        }

        @Override
        public Object executePos(INavegavel n, INavegavel anterior, Object params) {
            Double[] ret = (Double[]) params;
            if (ret == null) {
                ret = new Double[] { 0.0, 0.0 };
            }
            if (n instanceof TrechoRede) {
                TrechoRede a = (TrechoRede) n;
                ret[0] += a.getComprimentoKm();
                if (a.getChave() != null) {
                    if (a.getChave().getTipoChave().getTipo().contains("Chave Fusivel")) {
                        a.adicionaMedidaEletrica(DISTANCIA_ACUMULADA_PROTECAO, new MedidaEletrica(DISTANCIA_ACUMULADA_PROTECAO, new Double[] { ret[0], 0.0, 0.0 }, "km", true));
                        a.adicionaMedidaEletrica(CONSUMIDORES_ACUMULADOS_PROTECAO, new MedidaEletrica(CONSUMIDORES_ACUMULADOS_PROTECAO, new Double[] { ret[1], 0.0, 0.0 }, "consumidores", true));

                        if (distanciasEquipamentos.get("Chave Fusivel") == null) {
                            distanciasEquipamentos.put("Chave Fusivel", 0.0);
                        }
                        distanciasEquipamentos.put("Chave Fusivel", distanciasEquipamentos.get("Chave Fusivel") + ret[0]);

                        ret[0] = 0.0;
                    } else if (a.getChave().getTipoChave().getTipo().toUpperCase().contains("RELIGADOR")) {
                        a.adicionaMedidaEletrica(DISTANCIA_ACUMULADA_PROTECAO, new MedidaEletrica(DISTANCIA_ACUMULADA_PROTECAO, new Double[] { ret[0], 0.0, 0.0 }, "km", true));
                        a.adicionaMedidaEletrica(CONSUMIDORES_ACUMULADOS_PROTECAO, new MedidaEletrica(CONSUMIDORES_ACUMULADOS_PROTECAO, new Double[] { ret[1], 0.0, 0.0 }, "consumidores", true));

                        if (distanciasEquipamentos.get("RELIGADOR") == null) {
                            distanciasEquipamentos.put("RELIGADOR", 0.0);
                        }
                        distanciasEquipamentos.put("RELIGADOR", distanciasEquipamentos.get("RELIGADOR") + ret[0]);

                        ret[0] = 0.0;
                    } else if (a.getChave().getTipoChave().getTipo().toUpperCase().contains("REPETIDOR")) {
                        a.adicionaMedidaEletrica(DISTANCIA_ACUMULADA_PROTECAO, new MedidaEletrica(DISTANCIA_ACUMULADA_PROTECAO, new Double[] { ret[0], 0.0, 0.0 }, "km", true));
                        a.adicionaMedidaEletrica(CONSUMIDORES_ACUMULADOS_PROTECAO, new MedidaEletrica(CONSUMIDORES_ACUMULADOS_PROTECAO, new Double[] { ret[1], 0.0, 0.0 }, "consumidores", true));

                        if (distanciasEquipamentos.get("REPETIDOR") == null) {
                            distanciasEquipamentos.put("REPETIDOR", 0.0);
                        }
                        distanciasEquipamentos.put("REPETIDOR", distanciasEquipamentos.get("REPETIDOR") + ret[0]);

                        ret[0] = 0.0;
                    } else if (a.getChave().getTipoChave().getTipo().toUpperCase().contains("DISJUNTOR")) {
                        a.adicionaMedidaEletrica(DISTANCIA_ACUMULADA_PROTECAO, new MedidaEletrica(DISTANCIA_ACUMULADA_PROTECAO, new Double[] { ret[0], 0.0, 0.0 }, "km", true));
                        a.adicionaMedidaEletrica(CONSUMIDORES_ACUMULADOS_PROTECAO, new MedidaEletrica(CONSUMIDORES_ACUMULADOS_PROTECAO, new Double[] { ret[1], 0.0, 0.0 }, "consumidores", true));

                        if (distanciasEquipamentos.get("DISJUNTOR") == null) {
                            distanciasEquipamentos.put("DISJUNTOR", 0.0);
                        }
                        distanciasEquipamentos.put("DISJUNTOR", distanciasEquipamentos.get("DISJUNTOR") + ret[0]);

                        ret[0] = 0.0;
                    }
                }
            } else if (n instanceof Barra) {
                Barra v = (Barra) n;
                if (v.getTransformador() != null) {
                    Transformador trafo = v.getTransformador();
                    ret[1] += (double) trafo.getNumConsumidores();
                }
            }
            return ret;
        }

        public Map<String, Double> getMapTaxaFalhas() {
            Map<String, Double> map = new HashMap<String, Double>();

            map.put("Dméd. (CF)", (Double) paramsAlgoritmo.get("Dméd. (CF)").getValor());
            map.put("Dméd. (RL)", (Double) paramsAlgoritmo.get("Dméd. (RL)").getValor());
            map.put("Dméd. (FR)", (Double) paramsAlgoritmo.get("Dméd. (FR)").getValor());
            map.put("Dméd. (AL)", (Double) paramsAlgoritmo.get("Dméd. (AL)").getValor());

            map.put("Transf. Manual", (Double) paramsAlgoritmo.get("Transf. Manual").getValor());
            map.put("Transf. Automática", (Double) paramsAlgoritmo.get("Transf. Automática").getValor());

            if (distanciasEquipamentos.get("Chave Fusivel") == null) {
                map.put("falha (CF)", 0.0);
            } else {
                map.put("falha (CF)", ((Integer) paramsAlgoritmo.get("falha (CF)").getValor()) / distanciasEquipamentos.get("Chave Fusivel"));
            }

            if (distanciasEquipamentos.get("RELIGADOR") == null) {
                map.put("falha (RL)", 0.0);
            } else {
                map.put("falha (RL)", ((Integer) paramsAlgoritmo.get("falha (RL)").getValor()) / distanciasEquipamentos.get("RELIGADOR"));
            }

            if (distanciasEquipamentos.get("REPETIDOR") == null) {
                map.put("falha (FR)", 0.0);
            } else {
                map.put("falha (FR)", ((Integer) paramsAlgoritmo.get("falha (FR)").getValor()) / distanciasEquipamentos.get("REPETIDOR"));
            }

            if (distanciasEquipamentos.get("DISJUNTOR") == null) {
                map.put("falha (AL)", 0.0);
            } else {
                map.put("falha (AL)", ((Integer) paramsAlgoritmo.get("falha (AL)").getValor()) / distanciasEquipamentos.get("DISJUNTOR"));
            }

            return map;
        }

        @Override
        public Object operaResultados(Object resultadoParcial, Object resultadoRamo) {
            Double[] arr1 = (Double[]) resultadoParcial;
            Double[] arr2 = (Double[]) resultadoRamo;
            if (arr1 == null) arr1 = new Double[] { 0.0, 0.0 };
            if (arr2 == null) arr2 = new Double[] { 0.0, 0.0 };
            return new Double[] { arr1[0] + arr2[0], arr1[1] + arr2[1] };
        }
    }

    private Map<Alimentador, Map<String, Double>> mapTaxaFalhasAlimentador = new HashMap<Alimentador, Map<String, Double>>();

    public Map<Alimentador, Map<String, Double>> getMapTaxaFalhasAlimentador() {
        return mapTaxaFalhasAlimentador;
    }

    public void setMapTaxaFalhasAlimentador(Map<Alimentador, Map<String, Double>> mapTaxaFalhasAlimentador) {
        this.mapTaxaFalhasAlimentador = mapTaxaFalhasAlimentador;
    }

    public Map<INavegavel, Double[]> getNosDistDEC() {
        return nosDistDEC;
    }

    public void setNosDistDEC(Map<INavegavel, Double[]> nosDistDEC) {
        this.nosDistDEC = nosDistDEC;
    }
}
