import java.time.LocalDate;

public class EntregaNaoPerecivel extends Entrega {
    private String descricaoMateriais;

    public EntregaNaoPerecivel(int numero, LocalDate data, double peso, Localizacao origem, Localizacao destino, Cliente cliente, int tempoValidade) {
        super(numero, data, peso, origem, destino, cliente);
        this.descricaoMateriais = "";
    }

    public String getDescricaoMateriais() {
        return descricaoMateriais;
    }

    public void setDescricaoMateriais(String descricaoMateriais) {
        this.descricaoMateriais = descricaoMateriais;
    }

    @Override
    public double calculaValor() {
        return 20.0 * peso + 30.0 * distancia();
    }
}
