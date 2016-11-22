package br.ufsm.ceesp.ceee.model.importador;

/**
 * Created by cpol on 29/09/2016.
 */
public class Capacitor {

        private Long ID;
        private Barra barra;

        public Long getID() {
            return ID;
        }

        public void setID(Long ID) {
            this.ID = ID;
        }

        public Barra getBarra() {
            return barra;
        }

        public void setBarra(Barra barra) {
            this.barra = barra;
        }
}
