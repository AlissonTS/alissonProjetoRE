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

        // Funcionando em CSV e TXT

        LeitorCSV csv = new LeitorCSV(arquivo);

        String[] retorno = null;

        ArrayList<Alimentador> al = new ArrayList<Alimentador>();
        ArrayList<Subestacao> sub = new ArrayList<Subestacao>();
        ArrayList<TrechoRede> trecho = new ArrayList<TrechoRede>();
        ArrayList<Barra> barra = new ArrayList<Barra>();
        ArrayList<Transformador> tr = new ArrayList<Transformador>();
        ArrayList<Chave> cha = new ArrayList<Chave>();
        ArrayList<Regulador> reg = new ArrayList<Regulador>();
        ArrayList<Capacitor> capacitor = new ArrayList<Capacitor>();

        ArrayList<Long> br = null;

        retorno = csv.nextLine(true);
        retorno = csv.nextLine(true);

        /*for(String ret: retorno){
            System.out.print(ret+ " ");
        } */

        int contador=0;
        int laco = 0;

        Alimentador alimentador = null;
        Subestacao subestacao = null;

        System.out.println("Carregando Linhas...");
        do{
            if (retorno.length > 0) {

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

                if(subestacao == null){
                    subestacao = new Subestacao();
                    subestacao.setNome(retorno[0]);
                    subestacao.setBarra(barraFonte);
                }

                Barra barraAlvo = alimentador.getBarras().get(coord_Alvo);
                if (barraAlvo == null) {
                    barraAlvo = new Barra();
                    barraAlvo.setId(Barra.getNextId());
                    barraAlvo.setParCoordenadas(coord_Alvo);
                    alimentador.getBarras().put(coord_Alvo, barraAlvo);


                    String instal_trafo = retorno[24];
                    if(instal_trafo.length()>0){
                        Transformador trafo = new Transformador();
                        trafo.setBarra(barraAlvo);

                        trafo.setId(new Long(instal_trafo));
                        barraAlvo.setTransformador(trafo);

                        tr.add(trafo);
                    }

                    String capac = retorno[27];
                    if(capac.length()>0){
                        Capacitor cp = new Capacitor();

                        cp.setBarra(barraAlvo);

                        cp.setID(new Long(capac));
                        barraAlvo.setCapacitor(cp);

                        capacitor.add(cp);
                    }
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

                String ch = retorno[25].replace(" ", "").trim();
                if(ch.length()>0){
                    Chave chave = new Chave();
                    chave.setId(ch);
                    chave.setTrecho(trehoRede);

                    trehoRede.setChave(chave);

                    cha.add(chave);
                }

                String re = retorno[26];
                if(re.length()>0){
                    Regulador regulador = new Regulador();
                    regulador.setId(new Long (re));
                    regulador.setTrecho(trehoRede);

                    trehoRede.setRegulador(regulador);

                    reg.add(regulador);
                }

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
            retorno = csv.nextLine(true);
        }while(retorno != null);

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
            fw.write(sub.get(0).getID()+";\t ;\t; "+sub.get(0).getNome()+";\t "+
                    sub.get(0).getBarra().getParCoordenadas().getLatitude()+";\t"+sub.get(0).getBarra().getParCoordenadas().getLongitude() +";\t ;\t;\r\n");

            fw.write("TRAFO_SE;\r\n");
            fw.write("1;\t;\t ;\t ;\t ;\t ;\t13.800; \t ;\t ;\t ;\t ;\t ;\t "+sub.get(0).getID()+";\r\n");

            fw.write("CIRCUITO;\r\n");
            fw.write(al.get(0).getNome()+";\t\t ;\t\t ;\t\t "+sub.get(0).getID()+";\t\t 1;\t\t 13.000;\t\t ;\r\n");

            //LISTA DE BARRAS

            fw.write("BARRA;\r\n");

            for(int i=0; i<barra.size(); i++){
                fw.write(barra.get(i).getId()+";\t\t "+barra.get(i).getParCoordenadas().getLatitude()+"; "
                        +barra.get(i).getParCoordenadas().getLongitude()+"; \r\n");
            }

            //

            // LISTA DE TRECHOS

            fw.write("TRECHO;\r\n");

            for(int i=0; i<trecho.size(); i++){
                fw.write(trecho.get(i).getId()+";\t ;\t ;\t ;\t "+trecho.get(i).getBarraInicial().getId()+"; "
                        +trecho.get(i).getBarraFinal().getId()+";\t ;\t ;\t "
                        +trecho.get(i).getCondutor()+";\t ;\t ;\t "+trecho.get(i).getQtdFases()+"; "
                        +trecho.get(i).getComprimento()+"; ; \r\n");
            }
            //

            fw.write("CAPACITOR;\r\n");
            for(int i=0; i<capacitor.size(); i++){
                fw.write(capacitor.get(i).getID()+";  ;  ;  ; "+tr.get(i).getBarra().getId()+";  ; 1.200; 1.200; 1.200; 1.200;\r\n");
            }
            //

            fw.write("INSTAL_TRAFO;\r\n");
            for(int i=0; i<tr.size(); i++){
                fw.write(tr.get(i).getId()+";  ;  ;  ; "+tr.get(i).getBarra().getId()+";  ;  ;  ;  ;  ;  ;  ;  ;\r\n");
            }
            //

            fw.write("CHAVE;\r\n");
            for(int i=0; i<cha.size(); i++){
                fw.write(cha.get(i).getId()+";  ;  ; "+cha.get(i).getTrecho().getId()+"; FU;  ;  ;  ;  ;  ;  ;  ;  ;  ;  ;\r\n");
            }
            //

            fw.write("REGULADOR;\r\n");
            for(int i=0; i<reg.size(); i++){
                fw.write(reg.get(i).getId()+";  ;  ;  ; "+reg.get(i).getTrecho().getId()+";  ;  ;  ;  ;  ;  ;\r\n");
            }

            //
            fw.write("END;\r\n");

            fw.close();
            System.out.println("Arquivo preenchido..");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
