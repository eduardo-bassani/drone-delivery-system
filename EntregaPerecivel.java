import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EntregaPerecivel extends Entrega {
    private LocalDate validade;

    public EntregaPerecivel(int numero, String descricao, LocalDate data, double peso, Localizacao origem, Localizacao destino, Cliente cliente, LocalDate validade) {
        super(numero, descricao, data, peso, origem, destino, cliente);
        this.validade = validade;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    @Override
    public double calculaValor() {
        return (20.0 * peso + 30.0 * distancia()) * 1.1;
    }

    public String toCsv() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "1;" + numero + ";" + descricao + ";" + data.format(formato) + ";" + peso + ";" + origem.getCodigo() + ";" + destino.getCodigo() + ";" + situacao + ";" + drone.getIdentificador() + ";" + cliente.getEmail() + ";" + validade.format(formato);
    }

    public String toString() {
        return "Número: " + numero + "\nDescrição: " + descricao + "\nData: " + data + "\nPeso: "
            + peso + "\nLocalização de origem: " + origem + "\nLocalização de destino: " + destino
            + "\nSituação: " + situacao + "\nDrone: " + drone + "\nCliente: " + cliente + "\nValidade: " + validade + "\nValor: " + calculaValor();
    }
}
