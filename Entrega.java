import java.time.LocalDate;

public abstract class Entrega {
    protected int numero;
    protected String descricao;
    protected LocalDate data;
    protected double peso;
    protected Localizacao origem;
    protected Localizacao destino;
    protected String situacao;
    protected Drone drone;
    protected Cliente cliente;

    public Entrega(int numero, LocalDate data, double peso, Localizacao origem, Localizacao destino, Cliente cliente) {
        this.numero = numero;
        this.descricao = "";
        this.data = data;
        this.peso = peso;
        this.origem = origem;
        this.destino = destino;
        this.situacao = "cadastrada";
        this.drone = null;
        this.cliente = cliente;
    }

    public int getNumero() {
        return numero;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDate getData() {
        return data;
    }

    public double getPeso() {
        return peso;
    }

    public Localizacao getOrigem() {
        return origem;
    }

    public Localizacao getDestino() {
        return destino;
    }

    public String getSituacao() {
        return situacao;
    }

    public Drone getDrone() {
        return drone;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setOrigem(Localizacao origem) {
        this.origem = origem;
    }

    public void setDestino(Localizacao destino) {
        this.destino = destino;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public abstract double calculaValor();

    public double distancia() {
        double lat1 = origem.getLatitude(); 
        double lon1 = origem.getLongitude();
        double lat2 = destino.getLatitude();
        double lon2 = destino.getLongitude();
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
}
