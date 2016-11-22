package br.ufsm.ceesp.ceee.model;

import br.com.mega.silas.algoritmos.InformacoesSilas;
import br.ufsm.ceesp.ceee.algoritmos.impl.FluxoPotenciaTrifasico;

import java.io.Serializable;
import java.util.*;

/**
 * Created by Mega on 31/07/14.
 */

@InformacoesSilas(nomeTipoPropriedade = "OUTmedidasEletricas")
public class MedidaEletrica implements Serializable {

    private String nome;
    private Serializable valor;
    private String unidade;
    private boolean mostra = true;
    private Date timestamp;

    private Double valorA;
    private Double valorB;
    private Double valorC;
    private Double valorResumo;

    @InformacoesSilas(nomeTipoPropriedade = "OUTresumo")
    public Double getValorResumo() {
        return valorResumo;
    }

    public void setValorResumo(Double valorResumo) {
        this.valorResumo = valorResumo;
    }

    public static class TipoMedida implements Comparable,Serializable {

        public static TipoMedida MODULO_TENSAO_PU = new TipoMedida(FluxoPotenciaTrifasico.MODULO_TENSAO_PU, 0, Transformador.class, Barra.class, TrechoRede.class);
        public static TipoMedida MODULO_TENSAO = new TipoMedida(FluxoPotenciaTrifasico.MODULO_TENSAO, 1, Transformador.class, Barra.class, TrechoRede.class);
        public static TipoMedida PERC_CARREGAMENTO = new TipoMedida(FluxoPotenciaTrifasico.PERC_CARREGAMENTO, 2, TrechoRede.class);
        public static TipoMedida CORRENTE = new TipoMedida(FluxoPotenciaTrifasico.CORRENTE, 3, TrechoRede.class, Chave.class, Transformador.class);
        public static TipoMedida POTENCIA_ATIVA = new TipoMedida(FluxoPotenciaTrifasico.POTENCIA_ATIVA, 5, TrechoRede.class, Chave.class, Transformador.class);
        public static TipoMedida POTENCIA_REATIVA = new TipoMedida(FluxoPotenciaTrifasico.POTENCIA_REATIVA, 8, TrechoRede.class, Chave.class, Transformador.class);

        public static Collection<TipoMedida> values = new ArrayList<TipoMedida>();
        public static Map<String, TipoMedida> mapValues = new HashMap<String, TipoMedida>();

        static {
            values.add(CORRENTE);
            values.add(POTENCIA_ATIVA);
            values.add(POTENCIA_REATIVA);
            values.add(MODULO_TENSAO);
            values.add(MODULO_TENSAO_PU);
            values.add(PERC_CARREGAMENTO);

            mapValues.put(CORRENTE.nome, CORRENTE);
            mapValues.put(POTENCIA_ATIVA.nome, POTENCIA_ATIVA);
            mapValues.put(POTENCIA_REATIVA.nome, POTENCIA_REATIVA);
            mapValues.put(MODULO_TENSAO.nome, MODULO_TENSAO);
            mapValues.put(MODULO_TENSAO_PU.nome, MODULO_TENSAO_PU);
            mapValues.put(PERC_CARREGAMENTO.nome, PERC_CARREGAMENTO);
        }



        private String nome;
        private Integer ordem;
        private Class[] classesExibir;

        private TipoMedida(String nome, int ordem, Class... classesExibir) {
            this.nome = nome;
            this.ordem = ordem;
            this.classesExibir = classesExibir;
        }

        @Override
        public int compareTo(Object o) {
            return ordem.compareTo(((TipoMedida) o).ordem);
        }
    }

    public static boolean isExibir(MedidaEletrica medidaEletrica, Class classe) {
        for (TipoMedida m : TipoMedida.values) {
            if (medidaEletrica.getNome().equals(m.nome)) {
                for (Class cl : m.classesExibir) {
                    if (cl.equals(classe)) return true;
                }
                return false;
            }
        }
        return false;
    }

    public MedidaEletrica() {
    }

    public MedidaEletrica(String nome, Serializable valor, String unidade, boolean mostra) {
        this();
        this.nome = nome.intern();
        if (valor != null) {
            if (valor.getClass().isArray()) {
                Double[] arr = (Double[]) valor;
                valorA = arr[0];
                valorB = arr[1];
                valorC = arr[2];
            } else if (valor instanceof List) {
                List l = (List) valor;
                valorA = (Double) l.get(0);
                valorB = (Double) l.get(1);
                valorC = (Double) l.get(2);
            }
        }
        this.valor = valor;
        this.unidade = unidade;
        this.mostra = mostra;
    }

    public MedidaEletrica(String nome, Serializable valor, String unidade, boolean mostra, Date timestamp) {
        this(nome, valor, unidade, mostra);
        this.timestamp = timestamp;
    }

    public MedidaEletrica(MedidaEletrica attr) {
        this(attr.getNome(), (Serializable) attr.getValor(), attr.getUnidade(), attr.getMostra(), attr.getTimestamp());
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTvalorA")
    public Double getValorA() {
        return valorA;
    }

    public void setValorA(Double valorA) {
        this.valorA = valorA;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTvalorB")
    public Double getValorB() {
        return valorB;
    }

    public void setValorB(Double valorB) {
        this.valorB = valorB;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTvalorC")
    public Double getValorC() {
        return valorC;
    }

    public void setValorC(Double valorC) {
        this.valorC = valorC;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTnome")
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome.intern();
    }

    public Serializable getValor() {
        return valor;
    }

    public Double getValorSomado() {
        return (getValorA() == null ? 0.0 : getValorA())  + (getValorB() == null ? 0.0 : getValorB()) + (getValorC() == null ? 0.0 : getValorC());
    }

    public Double getValorMinimo() {
        return Math.min((getValorA() == null ? Double.MAX_VALUE : getValorA()), Math.min((getValorB() == null ? Double.MAX_VALUE : getValorB()), (getValorC() == null ? Double.MAX_VALUE : getValorC())));
    }

    public Double getValorMaximo() {
        return Math.max((getValorA() == null ? 0.0 : getValorA()), Math.max((getValorB() == null ? 0.0 : getValorB()), (getValorC() == null ? 0.0 : getValorC())));
    }

    public Double getValorMedio() {
        return getValorSomado() / 3.0;
    }

    public Double[] getValorArray() {
        return new Double[]{ valorA, valorB, valorC};
    }

    public void setValor(Serializable valor) {
        if (valor.getClass().isArray()) {
            Double[] arr = (Double[]) valor;
            valorA = arr[0];
            valorB = arr[1];
            valorC = arr[2];
        }
        this.valor = valor;
    }

    public void setValorArray(Double[] valor) {
        if (valor.length >= 1) {
            valorA = valor[0];
        }
        if (valor.length >= 2) {
            valorB = valor[1];
        }
        if (valor.length >= 3) {
            valorC = valor[2];
        }
        this.valor = valor;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTunidade")
    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public boolean getMostra() {
        return mostra;
    }

    public void setMostra(boolean mostra) {
        this.mostra = mostra;
    }

    @InformacoesSilas(nomeTipoPropriedade = "OUTdataCalculo")
    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MedidaEletrica that = (MedidaEletrica) o;

        if (nome != null ? !nome.equals(that.nome) : that.nome != null) return false;
        if (unidade != null ? !unidade.equals(that.unidade) : that.unidade != null) return false;
        if (valorA != null ? !valorA.equals(that.valorA) : that.valorA != null) return false;
        if (valorB != null ? !valorB.equals(that.valorB) : that.valorB != null) return false;
        if (valorC != null ? !valorC.equals(that.valorC) : that.valorC != null) return false;
        return valorResumo != null ? valorResumo.equals(that.valorResumo) : !(that.valorResumo != null);

    }

    @Override
    public int hashCode() {
        int result = nome != null ? nome.hashCode() : 0;
        result = 31 * result + (unidade != null ? unidade.hashCode() : 0);
        result = 31 * result + (valorA != null ? valorA.hashCode() : 0);
        result = 31 * result + (valorB != null ? valorB.hashCode() : 0);
        result = 31 * result + (valorC != null ? valorC.hashCode() : 0);
        result = 31 * result + (valorResumo != null ? valorResumo.hashCode() : 0);
        return result;
    }

    public Object clone() {
        MedidaEletrica MedidaEletrica = new MedidaEletrica(this);
        return MedidaEletrica;
    }

    public MedidaEletrica copia() {
        MedidaEletrica attr = (MedidaEletrica) clone();
        return attr;
    }

    public String toString() {
        if (valor instanceof Number[] || valorA != null || valorB != null || valorC != null) {
            StringBuffer buf = new StringBuffer((getNome() != null ? getNome().concat(": ") : "").concat("[A="));
            buf.append(valorA == null ? "0.0" : valorA.toString());
            buf.append(" B=").append(valorB == null ? "0.0" : valorB);
            buf.append(" C=").append(valorC == null ? 0.0 : valorC).append("] ").append(unidade);
            return buf.toString();
        } else if (valor instanceof Double) {
            return valor + " " + unidade;
        } else {
            return getValor().toString() + " " + unidade;
        }
    }

    public int compareTo(Object o) {
        if (valor instanceof Double) {
            return ((Double) valor).compareTo((Double) o);
        }
        return valor.toString().compareTo((String) o);  //To change body of implemented methods use File | Settings | File Templates.
    }

}
