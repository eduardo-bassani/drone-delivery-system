import java.util.ArrayList;

public class Cliente {
    private String nome;
    private Localizacao endereco;
    private String email;
    private String senha;
    private ArrayList<Entrega> entregas;

    public Cliente(String nome, Localizacao endereco, String email, String senha) {
        this.nome = nome;
        this.endereco = endereco;
        this.email = email;
        this.senha = senha;
        this.entregas = new ArrayList<Entrega>();
    }

    public String getNome() {
        return nome;
    }

    public Localizacao getEndereco() {
        return endereco;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public ArrayList<Entrega> getEntregas() {
        return entregas;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEndereco(Localizacao endereco) {
        this.endereco = endereco;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean adicionarEntrega(Entrega entrega) {
        return entregas.add(entrega);
    }
}
