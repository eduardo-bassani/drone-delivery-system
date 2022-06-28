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

    public boolean adicionarEntrega(Entrega entrega) {
        return entregas.add(entrega);
    }

    public String toCsv() {
        return identificador + ";" + cargaMaxima + ";" + autonomiaKm + ";" + base.getCodigo();
    }

    public String toString() {
        return "[autonomiaKm=" + autonomiaKm + ", base=" + base + ", cargaMaxima=" + cargaMaxima
                + ", identificador=" + identificador + "]";
    }
}
