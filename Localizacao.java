public class Localizacao {
    private int codigo;
    private String logradouro;
    private double latitude;
    private double longitude;

    public Localizacao(int codigo, String logradouro, double latitude, double longitude) {
        this.codigo = codigo;
        this.logradouro = logradouro;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String toCsv() {
        return codigo + ";" + logradouro + ";" + latitude + ";" + longitude;
    }

    public String toString() {
        return "[Código: " + codigo + ", Latitude: " + latitude + ", Logradouro: " + logradouro
                + ", Longitude: " + longitude + "]";
    }
}
