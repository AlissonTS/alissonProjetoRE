package br.ufsm.ceesp.ceee.model;

/**
 * Created by politecnico on 22/09/2016.
 */
public class Barra {

    private Long id;
    private ParCoordenadas parCoordenadas;
    private Transformador transformador;
    private Capacitor capacitor;

    public ParCoordenadas getParCoordenadas() {
        return parCoordenadas;
    }

    public void setParCoordenadas(ParCoordenadas parCoordenadas) {
        this.parCoordenadas = parCoordenadas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Transformador getTransformador() {
        return transformador;
    }

    public void setTransformador(Transformador transformador) {
        this.transformador = transformador;
    }

    public Capacitor getCapacitor() {
        return capacitor;
    }

    public void setCapacitor(Capacitor capacitor) {
        this.capacitor = capacitor;
    }


    public static class ParCoordenadas {

        public ParCoordenadas(Float latitude, Float longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        private Float latitude;
        private Float longitude;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ParCoordenadas that = (ParCoordenadas) o;

            if (!latitude.equals(that.latitude)) return false;
            return longitude.equals(that.longitude);

        }

        @Override
        public int hashCode() {
            int result = latitude.hashCode();
            result = 31 * result + longitude.hashCode();
            return result;
        }

        public Float getLatitude() {
            return latitude;
        }

        public void setLatitude(Float latitude) {
            this.latitude = latitude;
        }

        public Float getLongitude() {
            return longitude;
        }

        public void setLongitude(Float longitude) {
            this.longitude = longitude;
        }
    }

    private static long ID = 1;

    public static long getNextId() {
        return ID++;
    }

}
