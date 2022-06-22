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

    public boolean realizarLogin() {
        String email, senha;
        System.out.print("Email: ");
        email = sc.nextLine();
        System.out.print("Senha: ");
        senha = sc.nextLine();
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

    public boolean cadastrarLocalizacao() {
        boolean validade;
        int codigo = 0;
        String logradouro;
        double latitude = 0.0, longitude = 0.0;
        System.out.print("Digite o código: ");
        do {
            try {
                validade = true;
                codigo = sc.nextInt();
                while (codigo <= 0) {
                    System.out.print("Digite um valor maior que zero: ");
                    codigo = sc.nextInt();
                }
            } catch (Exception e) {
                sc.nextLine();
                System.out.print("Digite um valor inteiro: ");
                validade = false;
            }
        } while (validade = false);
        if (localizacaoExistente(codigo)) {
            System.out.println("Localização com este código já registrada.");
            return false;
        }
        sc.nextLine();
        System.out.print("Digite o nome do logradouro: ");
        logradouro = sc.nextLine();
        System.out.print("Digite a latitude: ");
        do {
            try {
                validade = true;
                latitude = sc.nextDouble();
            } catch (Exception e) {
                sc.nextLine();
                System.out.print("Digite um valor inteiro ou decimal: ");
                validade = false;
            }
        } while (validade = false);
        sc.nextLine();
        System.out.print("Digite a longitude: ");
        do {
            try {
                validade = true;
                longitude = sc.nextDouble();
            } catch (Exception e) {
                sc.nextLine();
                System.out.print("Digite um valor inteiro ou decimal: ");
                validade = false;
            }
        } while (validade = false);
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

    public boolean cadastrarDrone() {
        boolean validade;
        int identificador = 0, codigo = 0;
        double cargaMaxima = 0.0, autonomiaKm = 0.0;
        Localizacao base;
        System.out.print("Digite o identificador: ");
        do {
            try {
                validade = true;
                identificador = sc.nextInt();
                while (identificador <= 0) {
                    System.out.print("Digite um valor maior que zero: ");
                    identificador = sc.nextInt();
                }
            } catch (Exception e) {
                sc.nextLine();
                System.out.print("Digite um valor inteiro: ");
                validade = false;
            }
        } while (validade = false);
        if (droneExistente(identificador)) {
            System.out.println("Drone com este identificador já registrado.");
            return false;
        }
        sc.nextLine();
        System.out.print("Digite a carga máxima (em quilos): ");
        do {
            try {
                validade = true;
                cargaMaxima = sc.nextDouble();
                while (cargaMaxima <= 0) {
                    System.out.print("Digite um valor maior que zero: ");
                    cargaMaxima = sc.nextDouble();
                }
            } catch (Exception e) {
                sc.nextLine();
                System.out.print("Digite um valor inteiro ou decimal: ");
                validade = false;
            }
        } while (validade = false);
        sc.nextLine();
        System.out.print("Digite a autonomia (em quilômetros): ");
        do {
            try {
                validade = true;
                autonomiaKm = sc.nextDouble();
                while (autonomiaKm <= 0) {
                    System.out.print("Digite um valor maior que zero: ");
                    autonomiaKm = sc.nextDouble();
                }
            } catch (Exception e) {
                sc.nextLine();
                System.out.print("Digite um valor inteiro ou decimal: ");
                validade = false;
            }
        } while (validade = false);
        sc.nextLine();
        System.out.print("Digite o código da localização da base: ");
        do {
            try {
                validade = true;
                codigo = sc.nextInt();
                while (codigo <= 0) {
                    System.out.print("Digite um valor maior que zero: ");
                    codigo = sc.nextInt();
                }
            } catch (Exception e) {
                sc.nextLine();
                System.out.print("Digite um valor inteiro: ");
                validade = false;
            }
        } while (validade = false);
        base = buscarLocalizacao(codigo);
        if (base == null) {
            System.out.println("Local não encontrado. Cadastre a localização da base.");
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

    public boolean cadastrarCliente() {
        boolean validade;
        String nome, email, senha;
        Localizacao endereco;
        int codigo = 0;
        Cliente novoCliente;
        System.out.print("Digite o nome: ");
        nome = sc.nextLine();
        System.out.print("Digite o código da localização de seu endereço: ");
        do {
            try {
                validade = true;
                codigo = sc.nextInt();
                while (codigo <= 0) {
                    System.out.print("Digite um valor maior que zero: ");
                    codigo = sc.nextInt();
                }
            } catch (Exception e) {
                sc.nextLine();
                System.out.print("Digite um valor inteiro: ");
                validade = false;
            }
        } while (validade = false);
        endereco = buscarLocalizacao(codigo);
        if (endereco == null) {
            System.out.println("Local não encontrado. Cadastre a localização do endereco.");
            return false;
        }
        sc.nextLine();
        System.out.print("Digite o email: ");
        email = sc.nextLine();
        if (clienteExistente(email)) {
            System.out.println("Cliente com este email já registrado.");
            return false;
        }
        System.out.print("Digite a senha: ");
        senha = sc.nextLine();
        novoCliente = new Cliente(nome, endereco, email, senha);
        clientes.add(novoCliente);
        System.out.print("Cliente cadastrado com sucesso.\nDados: " + novoCliente);
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

    public boolean consultarEntregas() {
        if (cliente.getEntregas().isEmpty()) {
            System.out.println("Não foi encontrado nenhuma entrega.");
            return false;
        } else {
            System.out.println(cliente.consultarEntregas());
            return true;
        }
    }

    public boolean consultarCobrancaMensal() {
        boolean validade;
        int ano = 0, mes = 0;
        System.out.print("Digite o ano: ");
        do {
            try {
                validade = true;
                ano = sc.nextInt();
                while (ano < 1500 && ano > 3000) {
                    System.out.print("Digite um número entre 1500 e 3000: ");
                    ano = sc.nextInt();
                }
            } catch (Exception e) {
                sc.nextLine();
                System.out.print("Digite um valor inteiro: ");
                validade = false;
            }
        } while (validade = false);
        sc.nextLine();
        System.out.print("Digite o mes: ");
        do {
            try {
                validade = true;
                mes = sc.nextInt();
                while (mes < 1 && mes > 12) {
                    System.out.print("Digite um número entre 1 e 12: ");
                    mes = sc.nextInt();
                }
            } catch (Exception e) {
                sc.nextLine();
                System.out.print("Digite um valor inteiro: ");
                validade = false;
            }
        } while (validade = false);
        if (cliente.consultarCobrancaMensal(ano, mes) == null) {
            System.out.println("Não foi encontrado nenhuma entrega no período.");
            return false;
        } else {
            System.out.println(cliente.consultarCobrancaMensal(ano, mes));
            return true;
        }
    }
}
