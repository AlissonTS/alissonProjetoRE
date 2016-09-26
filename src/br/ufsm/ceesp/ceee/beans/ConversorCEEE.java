package br.ufsm.ceesp.ceee.beans;

import br.ufsm.ceesp.ceee.model.*;
import br.ufsm.ceesp.ceee.util.GeradorData;
import br.ufsm.ceesp.ceee.util.LeitorCSV;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;


public class ConversorCEEE {

    public static void main(String[] args) throws Exception {
        InputStream arquivo = null;
        File f = null;
        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(new JFrame());
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            f = fc.getSelectedFile();
            //This is where a real application would open the file.
            arquivo = new FileInputStream(f);
        }


        LeitorCSV csv = new LeitorCSV(arquivo);

        String[] retorno = null;

        ArrayList<Alimentador> al = new ArrayList<Alimentador>();
        ArrayList<Subestacao> sub = new ArrayList<Subestacao>();
        ArrayList<TrechoRede> trecho = new ArrayList<TrechoRede>();
        ArrayList<Barra> barra = new ArrayList<Barra>();

        ArrayList<Long> br = null;

        retorno = csv.nextLine(true);

        /*for(String ret: retorno){
            System.out.print(ret+ " ");
        } */

        int contador=0;
        int laco = 0;

        Alimentador alimentador = null;
        Subestacao subestacao = null;

        while(retorno != null && laco<=20) {

            retorno = csv.nextLine(true);

            if (retorno.length > 0) {
                if(subestacao == null){
                    subestacao = new Subestacao();
                    subestacao.setNome(retorno[0]);
                }

                if (alimentador == null) {
                    alimentador = new Alimentador();
                    alimentador.setNome(retorno[0] + "-" + retorno[1]);
                }
                Barra.ParCoordenadas coord_Fonte = new Barra.ParCoordenadas(new Float(retorno[2].replace(',', '.')), new Float(retorno[3].replace(',', '.')));
                Barra.ParCoordenadas coord_Alvo = new Barra.ParCoordenadas(new Float(retorno[4].replace(',', '.')), new Float(retorno[5].replace(',', '.')));

                Barra barraFonte = alimentador.getBarras().get(coord_Fonte);
                if (barraFonte == null) {
                    barraFonte = new Barra();
                    barraFonte.setId(Barra.getNextId());
                    barraFonte.setParCoordenadas(coord_Fonte);
                    alimentador.getBarras().put(coord_Fonte, barraFonte);
                }

                Barra barraAlvo = alimentador.getBarras().get(coord_Alvo);
                if (barraAlvo == null) {
                    barraAlvo = new Barra();
                    barraAlvo.setId(Barra.getNextId());
                    barraAlvo.setParCoordenadas(coord_Alvo);
                    alimentador.getBarras().put(coord_Alvo, barraAlvo);
                }

                TrechoRede trehoRede = new TrechoRede();
                trehoRede.setBarraInicial(barraFonte);
                trehoRede.setBarraFinal(barraAlvo);
                trehoRede.setId(TrechoRede.getNextId());
                trehoRede.setComprimento(new Float(retorno[14].replace(',', '.')));
                trehoRede.setFases(retorno[9]);
                trehoRede.setQtdFases(new Integer(retorno[10]));
                String material = "";
                if (retorno[11].trim().equals("A")) {
                    material = "CA";
                } else if (retorno[11].trim().equals("S")) {
                    material = "CAA";
                } else if (retorno[11].trim().equals("C")) {
                    material = "CC";
                } else if (retorno[11].trim().equals("Z")) {
                    material = "CAZ";
                }
                trehoRede.setCondutor(trehoRede.getQtdFases() + "#" + retorno[12] + material);
                alimentador.getTrechos().put(trehoRede.getId(), trehoRede);

                if(contador==0) {
                    al.add(alimentador);
                    sub.add(subestacao);
                    contador++;
                }

                trecho.add(trehoRede); // ADD Trecho
                laco++;

                if(br == null){ // Lista de IDs das barras
                    br = new ArrayList<Long>();
                    br.add(barraFonte.getId());
                    br.add(barraAlvo.getId());
                    barra.add(barraFonte);
                    barra.add(barraAlvo);
                }
                else{
                    // Verificação se Barra já está adicionada

                    int contF=0;
                    int contA=0;
                    for(int i=0; i<br.size(); i++){
                        if(barraFonte.getId()==br.get(i).longValue()) {
                            contF++;
                        }
                    }
                    for(int i=0; i<br.size(); i++){
                        if(barraAlvo.getId()==br.get(i).longValue()){
                            contA++;
                        }
                    }
                    if(contF==0){
                        br.add(barraFonte.getId());
                        barra.add(barraFonte);
                    }
                    if(contA==0){
                        br.add(barraAlvo.getId());
                        barra.add(barraAlvo);
                    }
                }

            }
        }

        File saida = new File(f.getAbsolutePath() + ".ceee.exp");
        if(!saida.exists()) {
            saida.createNewFile();
            System.out.println("Arquivo criado..");
        }
        else{
            System.out.println("Arquivo Já criado..");
        }

        try {
            FileWriter fw = new FileWriter(saida);

            GeradorData gd = new GeradorData();
            String dataFormatada = gd.dataFormatada();
            String horaFormatada = gd.horaFormatada();
            String dataFormatadaReferencia = gd.dataFormatadaReferencia();

            fw.write("PRI;\r\n");
            fw.write(dataFormatada+" "+horaFormatada+"- v2.89;\r\n");
            fw.write("DATA DE REFERÊNCIA = "+dataFormatadaReferencia+";\r\n");

            fw.write("VER;\r\n4.0;\r\n\r\n");

            fw.write("SE;\r\n");
            fw.write(sub.get(0).getID()+"; ; ; "+sub.get(0).getNome()+" ; ; \r\n");

            fw.write("TRAFO_SE;\r\n");
            fw.write(" ; ; ; ; ; ; ; ; ; ; ; ; ;\r\n");

            fw.write("CIRCUITO;\r\n");
            fw.write(al.get(0).getNome()+"; ; ;"+sub.get(0).getID()+"; ; ; ;\r\n");

            // FOR COM LISTA DE BARRAS

            fw.write("BARRA;\r\n");

            for(int i=0; i<barra.size(); i++){
                fw.write(barra.get(i).getId()+"; "+barra.get(i).getParCoordenadas().getLatitude()+"; "
                        +barra.get(i).getParCoordenadas().getLongitude()+"; \r\n");
            }

            //

            // FOR COM LISTA DE TRECHOS

            fw.write("TRECHO;\r\n");

            for(int i=0; i<trecho.size(); i++){
                fw.write(trecho.get(i).getId()+"; ; ; ; "+trecho.get(i).getBarraInicial().getId()+"; "
                        +trecho.get(i).getBarraFinal().getId()+"; ; ; "
                        +trecho.get(i).getCondutor()+"; ; ; "+trecho.get(i).getQtdFases()+"; "
                        +trecho.get(i).getComprimento()+"; ; \r\n");
            }
            //

            fw.write("CAPACITOR;\r\n");
            fw.write("CAMPOS A DEFINIR;\r\n");

            fw.write("INSTAL_TRAFO;\r\n");
            fw.write("CAMPOS A DEFINIR;\r\n");

            fw.write("CHAVE;\r\n");
            fw.write("CAMPOS A DEFINIR;\r\n");

            fw.write("REGULADOR;\r\n");
            fw.write("CAMPOS A DEFINIR;\r\n");

            fw.write("CUST_CLASS;\r\n");
            fw.write("CAMPOS A DEFINIR;\r\n");

            fw.write("DMD;\r\n");
            fw.write("CAMPOS A DEFINIR;\r\n");

            fw.write("TRAFOL;\r\n");
            fw.write("CAMPOS A DEFINIR;\r\n");

            fw.write("CAPS;\r\n");
            fw.write("CAMPOS A DEFINIR;\r\n");

            fw.write("ET;\r\n");
            fw.write("BARRA BT;\r\n");
            fw.write("\r\n");

            fw.write("TRECHO_BT;\r\n");
            fw.write("\r\n");

            fw.write("PONTO_SERVICO_BT;\r\n");
            fw.write("\r\n");

            fw.write("CONSUMIDOR_BT;\r\n");
            fw.write("\r\n");

            fw.write("CONSUMIDOR_IP;\r\n");
            fw.write("\r\n");

            fw.write("CONSUMIDOR_MT;\r\n");
            fw.write("\r\n");

            fw.write("SOCO;\r\n");
            fw.write("\r\n");

            fw.write("END;\r\n");

            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
