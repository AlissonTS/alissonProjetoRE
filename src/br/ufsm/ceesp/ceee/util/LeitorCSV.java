package br.ufsm.ceesp.ceee.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by politecnico on 04/03/2016.
 */
public class LeitorCSV {

    private InputStream arquivo;
    private char token = ';';

    public char getToken() {
        return token;
    }

    public void setToken(char token) {
        this.token = token;
    }

    public LeitorCSV() {
    }

    public LeitorCSV(InputStream arquivo) {
        this.arquivo = arquivo;
    }

    public LeitorCSV(InputStream arquivo, char token) {
        this.arquivo = arquivo;
        this.token = token;
    }

    public LeitorCSV(char token) {
        this.token = token;
    }

    public InputStream getArquivo() {
        return arquivo;
    }

    public void setArquivo(InputStream arquivo) {
        this.arquivo = arquivo;
    }

    /**
     * Retorna a proxima linha
     *
     * @param linhaVazia se quiser uma linha vazia
     * @return String[0] se linhavazia == true senao retorna null
     * @throws IOException
     */
    public String[] nextLine(boolean linhaVazia) throws IOException {
        try {
            if (arquivo == null) {
                throw new IllegalStateException("InputStream ainda n�o carregado. Use o m�todo setArquivo() antes;");
            }
            List l = new LinkedList();
            String s = "";
            boolean escapeado = false;
            int i = 0;
            for (i = arquivo.read(); i != -1 && i != '\n'; i = arquivo.read()) {
                if (i == token && !escapeado) {
                    l.add(s);
                    s = "";
                } else if (i == '"') {
                    escapeado = !escapeado;
                } else {
                    s += (char) i;
                }
            }
            if (s.trim().length() > 0) {
                l.add(s.trim());
            }
            if (!linhaVazia && l.size() == 0) {
                return null;
            } else if (l.size() == 0 && linhaVazia && i != -1) {
                return (String[]) l.toArray(new String[l.size()]);
            } else if (l.size() > 0) {
                return (String[]) l.toArray(new String[l.size()]);
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public String[] nextLine() throws IOException {
        return nextLine(false);
    }
}
