package br.ufsm.ceesp.ceee.model.importador;

/**
 * Created by cpol on 27/09/2016.
 */
public class Regulador {

        private Long id;
        private TrechoRede trecho;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public TrechoRede getTrecho() {
            return trecho;
        }

        public void setTrecho(TrechoRede trecho) {
            this.trecho = trecho;
        }

}
