import java.util.ArrayList;
import java.util.Scanner;

public class ACMEDrones {
    Scanner sc = new Scanner(System.in);
    private ArrayList<Localizacao> localizacoes;
    private ArrayList<Drone> drones;
    private ArrayList<Cliente> clientes;
    private ArrayList<Entrega> entregas;
    private Cliente cliente;
    private boolean administrador;
    
    public ACMEDrones() {
        localizacoes = new ArrayList<Localizacao>();
        drones = new ArrayList<Drone>();
        clientes = new ArrayList<Cliente>();
        entregas = new ArrayList<Entrega>();
        cliente = null;
        administrador = false;
    }

    public boolean isAdmin() {
        return administrador;
    }

    public boolean realizarLogin(String email, String senha) {
        if (email.equals("1") && senha.equals("1")) {
            administrador = true;
            cliente = null;
            return true;
        }
        for (Cliente c : clientes) {
            if (c.getEmail().equals(email) && c.getSenha().equals(senha)) {
                administrador = false;
                cliente = c;
                return true;
            }
        }
        administrador = false;
        cliente = null;
        return false;
    }

    public boolean cadastrarLocalizacao(int codigo, String logradouro, double latitude, double longitude) {
        if (localizacaoExistente(codigo)) {
            return false;
        }
        localizacoes.add(new Localizacao(codigo, logradouro, latitude, longitude));
        return true;
    }

    public boolean localizacaoExistente(int codigo) {
        for (Localizacao l : localizacoes) {
            if (l.getCodigo() == codigo) {
                return true;
            }
        }
        return false;
    }

    public Localizacao buscarLocalizacao(int codigo) {
        for (Localizacao l : localizacoes) {
            if (l.getCodigo() == codigo) {
                return l;
            }
        }
        return null;
    }

    public boolean cadastrarDrone(int identificador, double cargaMaxima, double autonomiaKm, Localizacao base) {
        if (droneExistente(identificador)) {
            return false;
        }
        drones.add(new Drone(identificador, cargaMaxima, autonomiaKm, base));
        return true;
    }

    public boolean droneExistente(int identificador) {
        for (Drone d : drones) {
            if (d.getIdentificador() == identificador) {
                return true;
            }
        }
        return false;
    }

    public Drone buscarDrone(int identificador) {
        for (Drone d : drones) {
            if (d.getIdentificador() == identificador) {
                return d;
            }
        }
        return null;
    }

    public boolean cadastrarCliente(String nome, Localizacao endereco, String email, String senha) {
        if (clienteExistente(email)) {
            return false;
        }
        clientes.add(new Cliente(nome, endereco, email, senha));
        return true;
    }

    public boolean clienteExistente(String email) {
        for (Cliente c : clientes) {
            if (c.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public Cliente buscarCliente(String email) {
        for (Cliente c : clientes) {
            if (c.getEmail().equals(email)) {
                return c;
            }
        }
        return null;
    }

    public String consultarTodasEntregas() {
        if (entregas.isEmpty()) {
            return "Não foi encontrado nenhuma entrega.";
        } else {
            String lista = "";
            for (Entrega e : entregas) {
                lista += e + "\n\n";
            }
            return lista;
        }
    }

    public String consultarEntregas() {
        if (cliente.getEntregas().isEmpty()) {
            return "Não foi encontrado nenhuma entrega.";
        } else {
            return cliente.consultarEntregas();
        }
    }

    public String consultarCobrancaMensal(int ano, int mes) {
        if (cliente.consultarCobrancaMensal(ano, mes) == null) {
            return "Não foi encontrado nenhuma entrega no período.";
        } else {
            return cliente.consultarCobrancaMensal(ano, mes);
        }
    }
}
