package br.ufsm.ceesp.ceee.model;

/**
 * Created by cpol on 27/09/2016.
 */
public class Chave {

            private String id;
            private TrechoRede trecho;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public TrechoRede getTrecho() {
                return trecho;
            }

            public void setTrecho(TrechoRede trecho) {
                this.trecho = trecho;
            }
}