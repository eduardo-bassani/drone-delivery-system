import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        carregarLocalizacoes();
        carregarDrones();
        carregarClientes();
        carregarEntregas();
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public int quantidadeClientes() {
        return clientes.size();
    }

    public boolean carregarLocalizacoes() {
        try (BufferedReader br = new BufferedReader(new FileReader("localizacoes.csv"))) {
            String linha, logradouro;
            int codigo;
            double latitude, longitude;
            while ((linha = br.readLine()) != null) {
                String[] campos = linha.split(";");
                if (campos.length == 4) {
                    codigo = Integer.parseInt(campos[0]);
                    logradouro = campos[1];
                    latitude = Double.parseDouble(campos[2]);
                    longitude = Double.parseDouble(campos[3]);
                    localizacoes.add(new Localizacao(codigo, logradouro, latitude, longitude));
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean carregarDrones() {
        try (BufferedReader br = new BufferedReader(new FileReader("drones.csv"))) {
            String linha;
            int identificador, codigoBase;
            double cargaMaxima, autonomiaKm;
            while ((linha = br.readLine()) != null) {
                String[] campos = linha.split(";");
                if (campos.length == 4) {
                    identificador = Integer.parseInt(campos[0]);
                    cargaMaxima = Double.parseDouble(campos[1]);
                    autonomiaKm = Double.parseDouble(campos[2]);
                    codigoBase = Integer.parseInt(campos[3]);
                    drones.add(new Drone(identificador, cargaMaxima, autonomiaKm, buscarLocalizacao(codigoBase)));
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean carregarClientes() {
        try (BufferedReader br = new BufferedReader(new FileReader("clientes.csv"))) {
            String linha, nome, email, senha;
            int codigoLocalizacao;
            while ((linha = br.readLine()) != null) {
                String[] campos = linha.split(";");
                if (campos.length == 4) {
                    nome = campos[0];
                    codigoLocalizacao = Integer.parseInt(campos[1]);
                    email = campos[2];
                    senha = campos[3];
                    clientes.add(new Cliente(nome, buscarLocalizacao(codigoLocalizacao), email, senha));
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean carregarEntregas() {
        try (BufferedReader br = new BufferedReader(new FileReader("entregas.csv"))) {
            String linha, descricao;
            int tipo, numero;
            Localizacao origem, destino;
            LocalDate data, validade;
            double peso;
            String descricaoMateriais, situacao;
            Cliente cliente;
            Drone drone;
            while ((linha = br.readLine()) != null) {
                String[] campos = linha.split(";");
                if (campos.length == 11) {
                    tipo = Integer.parseInt(campos[0]);
                    numero = Integer.parseInt(campos[1]);
                    descricao = campos[2];
                    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    data = LocalDate.parse(campos[3], formato);
                    peso = Double.parseDouble(campos[4]);
                    origem = buscarLocalizacao(Integer.parseInt(campos[5]));
                    destino = buscarLocalizacao(Integer.parseInt(campos[6]));
                    situacao = campos[7];
                    drone = buscarDrone(Integer.parseInt(campos[8]));
                    cliente = buscarCliente(campos[9]);
                    if (tipo == 1) {
                        validade = LocalDate.parse(campos[10], formato);
                        EntregaPerecivel entregaPerecivel = new EntregaPerecivel(numero, descricao, data, peso, origem, destino, cliente, validade);
                        entregaPerecivel.setSituacao(situacao);
                        entregaPerecivel.setDrone(drone);
                        entregas.add(entregaPerecivel);
                        cliente.adicionarEntrega(entregaPerecivel);
                        drone.adicionarEntrega(entregaPerecivel);
                    } else if (tipo == 2) {
                        descricaoMateriais = campos[10];
                        EntregaNaoPerecivel entregaNaoPerecivel = new EntregaNaoPerecivel(numero, descricao, data, peso, origem, destino, cliente, descricaoMateriais);
                        entregaNaoPerecivel.setSituacao(situacao);
                        entregaNaoPerecivel.setDrone(drone);
                        entregas.add(entregaNaoPerecivel);
                        cliente.adicionarEntrega(entregaNaoPerecivel);
                        drone.adicionarEntrega(entregaNaoPerecivel);
                    }
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean salvarDados() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("localizacoes.csv"))) {
            for (Localizacao l : localizacoes) {
                bw.write(l.toCsv() + "\n");
            }
        } catch (Exception e) {
            return false;
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("drones.csv"))) {
            for (Drone d : drones) {
                bw.write(d.toCsv() + "\n");
            }
        } catch (Exception e) {
            return false;
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("clientes.csv"))) {
            for (Cliente c : clientes) {
                bw.write(c.toCsv() + "\n");
            }
        } catch (Exception e) {
            return false;
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("entregas.csv"))) {
            for (Entrega e : entregas) {
                bw.write(e.toCsv() + "\n");
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public ArrayList<Drone> dronesCapacitados(Entrega entrega) {
        ArrayList<Drone> dronesCapacitados = new ArrayList<Drone>();
        for (Drone d : drones) {
            if (entrega.getPeso() <= d.getCargaMaxima()) {
                if ((d.distancia(entrega.getOrigem()) + entrega.distancia() + d.distancia(entrega.getDestino())) <= d.getAutonomiaKm()) {
                    dronesCapacitados.add(d);
                }
            }
        }
        return dronesCapacitados;
    }

    public boolean isAdmin() {
        return administrador;
    }

    public boolean realizarLogin(String email, String senha) {
        if (email.equals("administracao@mail.com") && senha.equals("admin123")) {
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

    public boolean cadastrarEntregaPerecivel(int numero, String descricao, LocalDate data, double peso, Localizacao origem, Localizacao destino, Cliente cliente, LocalDate validade) {
        EntregaPerecivel entrega = new EntregaPerecivel(numero, descricao, data, peso, origem, destino, cliente, validade);
        if (entregaExistente(numero) || dronesCapacitados(entrega).isEmpty()) {
            return false;
        }
        entregas.add(entrega);
        cliente.adicionarEntrega(entrega);
        return true;
    }

    public void cadastrarEntregaPerecivel(EntregaPerecivel entrega) {
        entregas.add(entrega);
        entrega.getCliente().adicionarEntrega(entrega);
    }

    public boolean cadastrarEntregaNaoPerecivel(int numero, String descricao, LocalDate data, double peso, Localizacao origem, Localizacao destino, Cliente cliente, String descricaoMateriais) {
        EntregaNaoPerecivel entrega = new EntregaNaoPerecivel(numero, descricao, data, peso, origem, destino, cliente, descricaoMateriais);
        if (entregaExistente(numero) || dronesCapacitados(entrega).isEmpty()) {
            return false;
        }
        entregas.add(entrega);
        cliente.adicionarEntrega(entrega);
        return true;
    }

    public void cadastrarEntregaNaoPerecivel(EntregaNaoPerecivel entrega) {
        entregas.add(entrega);
        entrega.getCliente().adicionarEntrega(entrega);
    }

    public boolean entregaExistente(int numero) {
        for (Entrega e : entregas) {
            if (e.getNumero() == numero) {
                return true;
            }
        }
        return false;
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
