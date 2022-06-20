import java.time.LocalDate;

public class EntregaPerecivel extends Entrega {
    private int tempoValidade;

    public EntregaPerecivel(int numero, LocalDate data, double peso, Localizacao origem, Localizacao destino, Cliente cliente, int tempoValidade) {
        super(numero, data, peso, origem, destino, cliente);
        this.tempoValidade = tempoValidade;
    }

    public int getTempoValidade() {
        return tempoValidade;
    }

    public void setTempoValidade(int tempoValidade) {
        this.tempoValidade = tempoValidade;
    }

    @Override
    public double calculaValor() {
        return (20.0 * peso + 30.0 * distancia()) * 1.1;
    }
}
