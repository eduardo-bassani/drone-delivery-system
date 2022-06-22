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

    public String toString() {
        return "[cliente=" + cliente + ", data=" + data + ", descricao=" + descricao + ", destino=" + destino
                + ", drone=" + drone + ", numero=" + numero + ", origem=" + origem + ", peso=" + peso + ", situacao="
                + situacao + ", tempoValidade=" + tempoValidade + ", valor=" + calculaValor() + "]";
    }
}
