package br.ufsm.ceesp.ceee.model.importador;

/**
 * Created by Alisson on 23/09/2016.
 */
public class Subestacao {

    private String nome;
    private Barra barra;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    private static long ID = 1;

    public static long getNextId() {
        return ID++;
    }

    public long getID(){
        return this.ID;
    }

    public void setID(long id){
        this.ID = id;
    }

    public Barra getBarra() {
        return barra;
    }

    public void setBarra(Barra barra) {
        this.barra = barra;
    }
}
