package br.ufsm.ceesp.ceee.algoritmos.util;

import br.ufsm.ceesp.ceee.model.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: milbradt
 * Date: 03/06/2005
 * Time: 10:05:19
 * To change this template use File | Settings | File Templates.
 */
public class Navegador {

    private static Collection<String> navegados = new LinkedList<String>();

    /**
     * Método que navega sobre uma árvore a partir da raiz e executa ações pós e
     * pré fixadas em cada nó da árvore.
     *
     * @param source raiz da árvore
     * @param params parâmetro iniciais de navegaçao. Pode ser null se a açao prefixada aceitar.
     * @param pre    ação pré-fixada, não pode ser nula
     * @param pos    ação pós-fixada, não pode ser nula
     * @param erros
     * @return o resultado das ações pós fixadas.
     */
    public static Object percorreArvore(INavegavel source, Object params, AcaoPreFixada pre, AcaoPosFixada pos, Collection<String> erros) {
        return percorreArvore(source, params, pre, pos, false, erros);
    }

    private static boolean verboso = false;

    public static boolean isVerboso() {
        return verboso;
    }

    public static void setVerboso(boolean verboso) {
        Navegador.verboso = verboso;
    }

    /**
     * Método que navega sobre uma árvore a partir da raiz e executa ações pós e
     * pré fixadas em cada nó da árvore.
     *
     * @param source raiz da árvore
     * @param params parâmetro iniciais de navegaçao. Pode ser null se a açao prefixada aceitar.
     * @param pre    ação pré-fixada, não pode ser nula
     * @param pos    ação pós-fixada, não pode ser nula
     * @param permiteAlsDiferentes    indica se será permitido navegar por diferentes alimentadores
     * @param erros
     * @return o resultado das ações pós fixadas.
     */
    public static Object percorreArvore(INavegavel source,
                                        Object params,
                                        AcaoPreFixada pre,
                                        AcaoPosFixada pos,
                                        boolean permiteAlsDiferentes,
                                        Collection<String> erros)
    {
        navegados = new LinkedHashSet<String>();
        params = pre.executePre(source, null, params);
        Collection<INavegavel> destinos = source.getDestinos(null);
        Object result = null;
        if (verboso) {
            System.err.println("\n\nNavegando alimentador " + source.getAlimentador().getNome());
        }
        try {
            for (INavegavel n : destinos) {
                Object rRamo = navega(source, n, params, pre, pos, new HashMap<Barra, INavegavel>(), source.getIdentificacaoAlimentador(), permiteAlsDiferentes, erros, 0);
                result = pos.operaResultados(result, rRamo);
            }
            return pos.executePos(source, null, result);
        } catch (Exception e) {
            e.printStackTrace();
            StringWriter strW = new StringWriter();
            e.printStackTrace(new PrintWriter(strW));
            erros.add(strW.toString().replaceAll("\n", "<br />"));
            throw new IllegalStateException(e);
        }
    }

    public static Object navega(INavegavel source,
                                INavegavel load,
                                Object params,
                                AcaoPreFixada pre,
                                AcaoPosFixada pos,
                                Map<Barra, INavegavel> navegou,
                                String alimentador,
                                boolean permiteAlsDiferentes,
                                Collection<String> erros,
                                int profundidade) {
        params = pre.executePre(load, source, params);
        if (verboso) {
            if (load instanceof Barra) {
                Barra b = (Barra) load;
                System.err.print(b.getCodigo() + ", ");
            } else if (load instanceof ChaveSocorro) {
                ChaveSocorro chaveSocorro = (ChaveSocorro) load;
                System.err.print("SC" + chaveSocorro.getId() + ", ");
            } else if (load instanceof TrechoRede) {
                TrechoRede trechoRede = (TrechoRede) load;
                System.err.print("[" + trechoRede.getCodigo() + "], ");
            }
        }
        Object result = null;
        for (INavegavel n : getDestinos(source, load, navegou, alimentador, permiteAlsDiferentes, erros)) {
            Object rRamo = navega(load, n, params, pre, pos, navegou, alimentador, permiteAlsDiferentes, erros, profundidade++);
            result = pos.operaResultados(result, rRamo);
        }
        if (profundidade >= 1000000) {
            erros.add("Profundidade > do que 1000000. Provável loop infinito.");
            return result;
        }
        return pos.executePos(load, source, result);
    }

    private static List<INavegavel> EMPTY = new ArrayList<INavegavel>(0);

    public static Collection<INavegavel> getDestinos(
            INavegavel source,
            INavegavel load,
            Map<Barra, INavegavel> navegou,
            String alimentador,
            boolean permiteAlsDiferentes,
            Collection<String> erros)
    {
        if (load instanceof TrechoRede && ((TrechoRede) load).getChave() != null) {
            Chave ch = ((TrechoRede) load).getChave();
            if (ch.getEstadoAberturaAtual().equals(Chave.ESTADO_ABERTA)) {
                return EMPTY;
            }
        }
        if (!permiteAlsDiferentes && !alimentador.equals(load.getIdentificacaoAlimentador())) {
            String mensagem = "Navegavel " + load.toString() + " deveria ser do alimentador " + alimentador;
            erros.add(mensagem);
            return EMPTY;
            //throw new IllegalStateException(mensagem);
        }
        if (load instanceof Barra) {
            Barra v = (Barra) load;
            if (navegou.get(v) != null) {
                v.setMarcadoCiclo();
                INavegavel x = navegou.get(v);
                source.setMarcadoCiclo();
                String mensagem = "Ciclo detectado em: " + v.toString() + ", \n\tvindo de: " + source.toString() + "\n\t" +
                        "Na visita anterior tinha vindo de: \n\t\t" + x.toString();
                erros.add(mensagem);
                System.err.println(mensagem);
                throw new IllegalStateException(mensagem);
                //return EMPTY;
            }
            navegou.put(v, source);
        }
        String prefixoNavegado = navegados.isEmpty() ? "" : "->";
        navegados.add(prefixoNavegado + source);
        return load.getDestinos(source);
    }

    public static void montaArvoreNavegacaoAlimentador(Alimentador alimentador) {
        if (!alimentador.getMontouArvore()) {
            for (TrechoRede trecho : alimentador.getTrechosRede()) {
                trecho.getBarraAlvo().getDestinos().add(trecho);
                trecho.getBarraFonte().getDestinos().add(trecho);
            }
            for (ChaveSocorro chave : alimentador.getSubestacao().getChaveSocorros()) {
                if (chave.getBarraFinal() != null) {
                    chave.getBarraFinal().setChaveSocorro(chave);
                }
                if (chave.getBarraInicial() != null) {
                    chave.getBarraInicial().setChaveSocorro(chave);
                }
            }
            for (Capacitor capacitor : alimentador.getCapacitores()) {
                capacitor.getBarra().setCapacitor(capacitor);
            }
            for (CapacitorSerie capacitorSerie : alimentador.getCapacitoresSerie()) {
                if (capacitorSerie.getTrechoRede() == null) {
                    TrechoRede f = TrechoRede.getTrechoFalso();
                    capacitorSerie.setTrechoRede(f);
                    f.setAlimentador(capacitorSerie.getBarraAlvo().getAlimentador());
                    f.setBarraAlvo(capacitorSerie.getBarraAlvo());
                    f.setBarraFonte(capacitorSerie.getBarraFonte());
                    f.setCodigoAlfanumerico(capacitorSerie.getCodigoAlfanumerico());
                }
                capacitorSerie.getTrechoRede().setCapacitorSerie(capacitorSerie);
                capacitorSerie.getBarraAlvo().getDestinos().add(capacitorSerie.getTrechoRede());
                capacitorSerie.getBarraFonte().getDestinos().add(capacitorSerie.getTrechoRede());
            }
            for (ElevadorRebaixador elevadorRebaixador : alimentador.getElevadoresRebaixadores()) {
                if (elevadorRebaixador.getTrechoRede() == null) {
                    TrechoRede f = TrechoRede.getTrechoFalso();
                    elevadorRebaixador.setTrechoRede(f);
                    f.setAlimentador(elevadorRebaixador.getBarraAlvo().getAlimentador());
                    f.setBarraAlvo(elevadorRebaixador.getBarraAlvo());
                    f.setBarraFonte(elevadorRebaixador.getBarraFonte());
                    f.setCodigoAlfanumerico(elevadorRebaixador.getCodigoAlfanumerico());
                }
                elevadorRebaixador.getTrechoRede().setElevadorRebaixador(elevadorRebaixador);
                elevadorRebaixador.getBarraAlvo().getDestinos().add(elevadorRebaixador.getTrechoRede());
                elevadorRebaixador.getBarraFonte().getDestinos().add(elevadorRebaixador.getTrechoRede());
            }
            alimentador.setMontouArvore(true);
        }
    }


}
