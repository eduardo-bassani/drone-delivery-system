import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EntregaNaoPerecivel extends Entrega {
    private String descricaoMateriais;

    public EntregaNaoPerecivel(int numero, String descricao, LocalDate data, double peso, Localizacao origem, Localizacao destino, Cliente cliente, String descricaoMateriais) {
        super(numero, descricao, data, peso, origem, destino, cliente);
        this.descricaoMateriais = descricaoMateriais;
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

    public String toCsv() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "2;" + numero + ";" + descricao + ";" + data.format(formato) + ";" + peso + ";" + cliente.getEmail() + ";" + origem.getCodigo() + ";" + destino.getCodigo() + ";" + descricaoMateriais;
    }

    public String toString() {
        return "[cliente=" + cliente + ", data=" + data + ", descricao=" + descricao + ", descricaoMateriais=" + descricaoMateriais
                + ", destino=" + destino + ", drone=" + drone + ", numero=" + numero + ", origem=" + origem + ", peso=" + peso
                + ", situacao=" + situacao + ", valor=" + calculaValor() + "]";
    }
}
