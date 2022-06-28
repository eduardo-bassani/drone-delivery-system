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
        return "1;" + numero + ";" + descricao + ";" + data.format(formato) + ";" + peso + ";" + cliente.getEmail() + ";" + origem.getCodigo() + ";" + destino.getCodigo() + ";" + validade.format(formato);
    }

    public String toString() {
        return "[cliente=" + cliente + ", data=" + data + ", descricao=" + descricao + ", destino=" + destino
                + ", drone=" + drone + ", numero=" + numero + ", origem=" + origem + ", peso=" + peso + ", situacao="
                + situacao + ", validade=" + validade + ", valor=" + calculaValor() + "]";
    }
}
