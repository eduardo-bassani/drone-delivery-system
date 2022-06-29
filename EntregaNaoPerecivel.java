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
        return "2;" + numero + ";" + descricao + ";" + data.format(formato) + ";" + peso + ";" + origem.getCodigo() + ";" + destino.getCodigo() + ";" + situacao + ";" + drone.getIdentificador() + ";" + cliente.getEmail() + ";" + descricaoMateriais;
    }

    public String toString() {
        return "Número: " + numero + "\nDescrição: " + descricao + "\nData: " + data + "\nPeso: "
            + peso + "\nLocalização de origem: " + origem + "\nLocalização de destino: " + destino
            + "\nSituação: " + situacao + "\nDrone: " + drone + "\nCliente: " + cliente + "\nDescrição dos Materiais: " + descricaoMateriais + "\nValor: " + calculaValor();
    }
}
