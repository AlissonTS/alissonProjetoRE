package br.ufsm.ceesp.ceee.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by cpol on 23/09/2016.
 */
public class GeradorData { //Gerador de Data

        private String dataFormatada;

        private String horaFormatada;
        Date data = new Date();

        public String getDataFormatada() {
            return dataFormatada;
        }

        public void setDataFormatada(String dataFormatada) {
            this.dataFormatada = dataFormatada;
        }

        public String getHoraFormatada() {
            return horaFormatada;
        }

        public void setHoraFormatada(String horaFormatada) {
            this.horaFormatada = horaFormatada;
        }

        public String dataFormatada(){
            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
            data = Calendar.getInstance().getTime(); // Ou qualquer outra forma que tem
            dataFormatada = formatador.format(data);

            return dataFormatada;
        }

        public String horaFormatada() {
            SimpleDateFormat formatador = new SimpleDateFormat("HH:mm:ss");
            data = Calendar.getInstance().getTime(); // Ou qualquer outra forma que tem
            horaFormatada = formatador.format(data);

            return horaFormatada;
        }

    public String dataFormatadaReferencia(){
        SimpleDateFormat formatador = new SimpleDateFormat("MM/yyyy");
        data = Calendar.getInstance().getTime(); // Ou qualquer outra forma que tem
        dataFormatada = formatador.format(data);

        return dataFormatada;
    }
}
