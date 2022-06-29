import java.util.ArrayList;

public class Drone {
    private int identificador;
    private double cargaMaxima;
    private double autonomiaKm;
    private Localizacao base;
    private ArrayList<Entrega> entregas;

    public Drone(int identificador, double cargaMaxima, double autonomiaKm, Localizacao base) {
        this.identificador = identificador;
        this.cargaMaxima = cargaMaxima;
        this.autonomiaKm = autonomiaKm;
        this.base = base;
        this.entregas = new ArrayList<Entrega>();
    }

    public int getIdentificador() {
        return identificador;
    }

    public double getCargaMaxima() {
        return cargaMaxima;
    }

    public double getAutonomiaKm() {
        return autonomiaKm;
    }

    public Localizacao getBase() {
        return base;
    }

    public ArrayList<Entrega> getEntregas() {
        return entregas;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public void setCargaMaxima(double cargaMaxima) {
        this.cargaMaxima = cargaMaxima;
    }

    public void setAutonomiaKm(double autonomiaKm) {
        this.autonomiaKm = autonomiaKm;
    }

    public void setBase(Localizacao base) {
        this.base = base;
    }

    public double distancia(Localizacao l) {
        double lat1 = base.getLatitude(); 
        double lon1 = base.getLongitude();
        double lat2 = l.getLatitude();
        double lon2 = l.getLongitude();
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);
        double a = Math.pow(Math.sin(dLat / 2), 2) +
                   Math.pow(Math.sin(dLon / 2), 2) *
                   Math.cos(lat1) *
                   Math.cos(lat2);
        double rad = 6371;
        double c = 2 * Math.asin(Math.sqrt(a));
        return rad * c;
    }

    public boolean adicionarEntrega(Entrega entrega) {
        return entregas.add(entrega);
    }

    public String toCsv() {
        return identificador + ";" + cargaMaxima + ";" + autonomiaKm + ";" + base.getCodigo();
    }

    public String toString() {
        return "[Autonomia: " + autonomiaKm + ", Localização da Base: " + base + ", Carga maxima: " + cargaMaxima
                + ", Identificador: " + identificador + "]";
    }
}
