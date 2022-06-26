import javax.swing.*;
import java.awt.*;

public class InterfaceGrafica extends JFrame {
    CardLayout cardLayout;
    int ano, mes, codigo, identificador, base, endereco;
    boolean validade, mesValido;
    String logradouro, nome, email, senha;
    double latitude, longitude, cargaMaxima, autonomiaKm;
    JPanel painelCadastrarCliente, painelCadastrarDrone, painelCadastrarLocalizacao, painelPrincipal, painelLogin, painelCliente, painelConsultarEntregas, painelConsultarCobrancaForm, painelConsultarCobranca, painelAdministrador, painelConsultarTodasEntregas;
    JLabel rotuloSenhaCadastroInvalido, rotuloSenhaCadastro, rotuloEmailCadastroInvalido, rotuloEmailCadastro, rotuloEnderecoInvalido, rotuloEndereco, rotuloNomeInvalido, rotuloNome, rotuloCadastrarDroneStatus, rotuloBaseInvalido, rotuloBase, rotuloAutonomiaInvalido, rotuloAutonomia, rotuloCargaMaximaInvalido, rotuloCargaMaxima, rotuloIdentificadorInvalido, rotuloIdentificador, rotuloCadastrarLocalStatus, rotuloLongitudeInvalido, rotuloLatitudeInvalido, rotuloLatitude, rotuloLongitude, rotuloLogradouro, rotuloCodigoLocalInvalido, rotuloCodigoLocal, rotuloTituloTodasEntregas, rotuloEmail, rotuloSenha, rotuloMensagem, rotuloTituloEntregas, rotuloAno, rotuloMes, rotuloAnoInvalido, rotuloMesInvalido, rotuloTituloEntregasCobrancaMensal;
    JTextField campoTextoEmailCadastro, campoTextoEndereco, campoTextoNome, campoTextoBase, campoTextoAutonomia, campoTextoCargaMaxima, campoTextoIdentificador, campoTextoLongitude, campoTextoLatitude, campoTextoLogradouro, campoTextoCodigoLocal, campoTextoEmail, campoTextoAno, campoTextoMes;
    JPasswordField campoTextoSenhaCadastro, campoSenha;
    JButton botaoCadastrarCliente, botaoVoltarCadastrarCliente, botaoCadastrarDrone, botaoCadastrarLocal, botaoVoltarCadastrarDrone, botaoVoltarConsultarTodasEntregas, botaoVoltarConsultarCobrancaForm, botaoVoltarCadastrarLocal, botaoSairAdmin, botaoSimularCarga, botaoConsultarTodasEntregas, botaoCadastroEntrega, botaoCadastroCliente, botaoCadastroDrone, botaoCadastroLocal, botaoEntrar, botaoConsultarEntregas, botaoConsultarCobranca, botaoVoltarConsultarEntregas, botaoBuscarCobranca, botaoVoltarFormConsultarCobranca, botaoSairCliente;
    JTextArea areaTextoCadastrarClienteDados, areaTextoEntregas, areaTextoCobrancaMensal, areaTextoTodasEntregas;
    JScrollPane cadastrarClienteDados, entregas, entregasCobrancaMensal;

    public InterfaceGrafica(ACMEDrones acmeDrones) {
        super();

        setTitle("ACME Drones");
        setLocation(new Point(500, 300));
        setSize(new Dimension(800, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        cardLayout = new CardLayout();
        painelPrincipal = new JPanel();
        painelPrincipal.setLayout(cardLayout);
        add(painelPrincipal);

        // Painel Login

        painelLogin = new JPanel();
        painelLogin.setLayout(null);

        rotuloEmail = new JLabel("Email");
        rotuloEmail.setBounds(300, 158, 70, 20);
        painelLogin.add(rotuloEmail);

        campoTextoEmail = new JTextField();
        campoTextoEmail.setBounds(300, 177, 193, 28);
        painelLogin.add(campoTextoEmail);

        rotuloSenha = new JLabel("Senha");
        rotuloSenha.setBounds(300, 205, 70, 20);
        painelLogin.add(rotuloSenha);

        campoSenha = new JPasswordField();
        campoSenha.setBounds(300, 225, 193, 28);
        painelLogin.add(campoSenha);

        botaoEntrar = new JButton("Entrar");
        botaoEntrar.setBounds(300, 260, 90, 25);
        // botaoEntrar.setForeground(Color.WHITE);
        // botaoEntrar.setBackground(Color.BLACK);
        painelLogin.add(botaoEntrar);

        rotuloMensagem = new JLabel("Seu usuário ou senha estão incorretos.");
        rotuloMensagem.setBounds(300, 290, 280, 20);
        rotuloMensagem.setForeground(Color.RED);
        rotuloMensagem.setVisible(false);
        painelLogin.add(rotuloMensagem);

        painelPrincipal.add(painelLogin, "painelLogin");

        botaoEntrar.addActionListener(e -> {
            String email = campoTextoEmail.getText();
            String senha = String.valueOf(campoSenha.getPassword());
            if (acmeDrones.realizarLogin(email, senha)) {
                if (acmeDrones.isAdmin()) {
                    cardLayout.show(painelPrincipal, "painelAdministrador");
                } else {
                    cardLayout.show(painelPrincipal, "painelCliente");
                }
            } else {
                rotuloMensagem.setVisible(true);
            }
        });

        // Painel Administrador

        painelAdministrador = new JPanel();
        painelAdministrador.setLayout(new BoxLayout(painelAdministrador, BoxLayout.PAGE_AXIS));

        botaoCadastroLocal = new JButton("Cadastrar nova localização");
        botaoCadastroLocal.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelAdministrador.add(botaoCadastroLocal);

        botaoCadastroDrone = new JButton("Cadastrar novo drone");
        botaoCadastroDrone.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelAdministrador.add(botaoCadastroDrone);

        botaoCadastroCliente = new JButton("Cadastrar novo cliente");
        botaoCadastroCliente.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelAdministrador.add(botaoCadastroCliente);

        botaoCadastroEntrega = new JButton("Cadastrar nova entrega");
        botaoCadastroEntrega.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelAdministrador.add(botaoCadastroEntrega);

        botaoConsultarTodasEntregas = new JButton("Consultar todas as entregas");
        botaoConsultarTodasEntregas.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelAdministrador.add(botaoConsultarTodasEntregas);

        botaoSimularCarga = new JButton("Simular carga de dados");
        botaoSimularCarga.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelAdministrador.add(botaoSimularCarga);

        botaoSairAdmin = new JButton("Sair");
        botaoSairAdmin.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelAdministrador.add(botaoSairAdmin);

        painelPrincipal.add(painelAdministrador, "painelAdministrador");

        botaoCadastroLocal.addActionListener(e -> {
            cardLayout.show(painelPrincipal, "painelCadastrarLocalizacao");
        });

        botaoCadastroDrone.addActionListener(e -> {
            cardLayout.show(painelPrincipal, "painelCadastrarDrone");
        });

        botaoCadastroCliente.addActionListener(e -> {
            cardLayout.show(painelPrincipal, "painelCadastrarCliente");
        });

        botaoCadastroEntrega.addActionListener(e -> {
            cardLayout.show(painelPrincipal, "painelCadastrarEntrega");
        });

        botaoConsultarTodasEntregas.addActionListener(e -> {
            areaTextoTodasEntregas.setText(acmeDrones.consultarTodasEntregas());
            cardLayout.show(painelPrincipal, "painelConsultarTodasEntregas");
        });

        botaoSimularCarga.addActionListener(e -> {
            cardLayout.show(painelPrincipal, "painelSimularCarga");
        });

        botaoSairAdmin.addActionListener(e -> {
            campoTextoEmail.setText("");
            campoSenha.setText("");
            rotuloMensagem.setVisible(false);
            cardLayout.show(painelPrincipal, "painelLogin");
        });

        // Painel Cadastrar Localização

        painelCadastrarLocalizacao = new JPanel();
        painelCadastrarLocalizacao.setLayout(null);

        botaoVoltarCadastrarLocal = new JButton("Voltar");
        botaoVoltarCadastrarLocal.setBounds(40, 20, 90, 25);
        painelCadastrarLocalizacao.add(botaoVoltarCadastrarLocal);

        rotuloCodigoLocal = new JLabel("Código");
        rotuloCodigoLocal.setBounds(300, 158, 70, 20);
        painelCadastrarLocalizacao.add(rotuloCodigoLocal);

        campoTextoCodigoLocal = new JTextField();
        campoTextoCodigoLocal.setBounds(300, 177, 193, 28);
        painelCadastrarLocalizacao.add(campoTextoCodigoLocal);

        rotuloCodigoLocalInvalido = new JLabel();
        rotuloCodigoLocalInvalido.setBounds(493, 177, 200, 20);
        rotuloCodigoLocalInvalido.setForeground(Color.RED);
        rotuloCodigoLocalInvalido.setVisible(false);
        painelCadastrarLocalizacao.add(rotuloCodigoLocalInvalido);

        rotuloLogradouro = new JLabel("Logradouro");
        rotuloLogradouro.setBounds(300, 205, 200, 20);
        painelCadastrarLocalizacao.add(rotuloLogradouro);

        campoTextoLogradouro = new JTextField();
        campoTextoLogradouro.setBounds(300, 225, 193, 28);
        painelCadastrarLocalizacao.add(campoTextoLogradouro);

        rotuloLatitude = new JLabel("Latitude");
        rotuloLatitude.setBounds(300, 255, 200, 20);
        painelCadastrarLocalizacao.add(rotuloLatitude);

        campoTextoLatitude = new JTextField();
        campoTextoLatitude.setBounds(300, 275, 193, 28);
        painelCadastrarLocalizacao.add(campoTextoLatitude);

        rotuloLatitudeInvalido = new JLabel("Digite uma latitude válida.");
        rotuloLatitudeInvalido.setBounds(493, 275, 250, 20);
        rotuloLatitudeInvalido.setForeground(Color.RED);
        rotuloLatitudeInvalido.setVisible(false);
        painelCadastrarLocalizacao.add(rotuloLatitudeInvalido);

        rotuloLongitude = new JLabel("Longitude");
        rotuloLongitude.setBounds(300, 305, 200, 20);
        painelCadastrarLocalizacao.add(rotuloLongitude);

        campoTextoLongitude = new JTextField();
        campoTextoLongitude.setBounds(300, 325, 193, 28);
        painelCadastrarLocalizacao.add(campoTextoLongitude);

        rotuloLongitudeInvalido = new JLabel("Digite uma longitude válida.");
        rotuloLongitudeInvalido.setBounds(493, 325, 250, 20);
        rotuloLongitudeInvalido.setForeground(Color.RED);
        rotuloLongitudeInvalido.setVisible(false);
        painelCadastrarLocalizacao.add(rotuloLongitudeInvalido);

        botaoCadastrarLocal = new JButton("Cadastrar");
        botaoCadastrarLocal.setBounds(300, 365, 90, 25);
        painelCadastrarLocalizacao.add(botaoCadastrarLocal);

        rotuloCadastrarLocalStatus = new JLabel();
        rotuloCadastrarLocalStatus.setBounds(300, 365, 300, 20);
        painelCadastrarLocalizacao.add(rotuloCadastrarLocalStatus);

        painelPrincipal.add(painelCadastrarLocalizacao, "painelCadastrarLocalizacao");

        botaoVoltarCadastrarLocal.addActionListener(e -> {
            rotuloCadastrarLocalStatus.setVisible(false);;
            botaoCadastrarLocal.setVisible(true);
            campoTextoCodigoLocal.setText("");
            campoTextoLogradouro.setText("");
            campoTextoLatitude.setText("");
            campoTextoLongitude.setText("");
            rotuloCodigoLocalInvalido.setVisible(false);
            rotuloLatitudeInvalido.setVisible(false);
            rotuloLongitudeInvalido.setVisible(false);
            cardLayout.show(painelPrincipal, "painelAdministrador");
        });

        botaoCadastrarLocal.addActionListener(e -> {
            validade = true;
            try {
                codigo = Integer.parseInt(campoTextoCodigoLocal.getText());
                if (codigo < 0) {
                    rotuloCodigoLocalInvalido.setText("Digite um código válido.");
                    rotuloCodigoLocalInvalido.setVisible(true);
                    validade = false;
                } else {
                    rotuloCodigoLocalInvalido.setVisible(false);
                }
            } catch (Exception exc) {
                rotuloCodigoLocalInvalido.setText("Digite um código válido.");
                rotuloCodigoLocalInvalido.setVisible(true);
                validade = false;
            }
            logradouro = campoTextoLogradouro.getText();
            try {
                latitude = Double.parseDouble(campoTextoLatitude.getText());
                rotuloLatitudeInvalido.setVisible(false);
            } catch (Exception exc) {
                rotuloLatitudeInvalido.setVisible(true);
                validade = false;
            }
            try {
                longitude = Double.parseDouble(campoTextoLongitude.getText());
                rotuloLongitudeInvalido.setVisible(false);
            } catch (Exception exc) {
                rotuloLongitudeInvalido.setVisible(true);
                validade = false;
            }
            if (validade == true) {
                if (acmeDrones.cadastrarLocalizacao(codigo, logradouro, latitude, longitude)) {
                    rotuloCadastrarLocalStatus.setText("Localização cadastrada com sucesso.");
                    rotuloCadastrarLocalStatus.setVisible(true);
                    botaoCadastrarLocal.setVisible(false);
                    campoTextoCodigoLocal.setText("");
                    campoTextoLogradouro.setText("");
                    campoTextoLatitude.setText("");
                    campoTextoLongitude.setText("");
                } else {
                    rotuloCodigoLocalInvalido.setText("Código já cadastrada.");
                    rotuloCodigoLocalInvalido.setVisible(true);
                }
            }
        });

        // Painel Cadastrar Drone

        painelCadastrarDrone = new JPanel();
        painelCadastrarDrone.setLayout(null);

        botaoVoltarCadastrarDrone = new JButton("Voltar");
        botaoVoltarCadastrarDrone.setBounds(40, 20, 90, 25);
        painelCadastrarDrone.add(botaoVoltarCadastrarDrone);

        rotuloIdentificador = new JLabel("Identificador");
        rotuloIdentificador.setBounds(300, 158, 300, 20);
        painelCadastrarDrone.add(rotuloIdentificador);

        campoTextoIdentificador = new JTextField();
        campoTextoIdentificador.setBounds(300, 177, 193, 28);
        painelCadastrarDrone.add(campoTextoIdentificador);

        rotuloIdentificadorInvalido = new JLabel();
        rotuloIdentificadorInvalido.setBounds(493, 177, 200, 20);
        rotuloIdentificadorInvalido.setForeground(Color.RED);
        rotuloIdentificadorInvalido.setVisible(false);
        painelCadastrarDrone.add(rotuloIdentificadorInvalido);

        rotuloCargaMaxima = new JLabel("Carga máxima (em quilos)");
        rotuloCargaMaxima.setBounds(300, 205, 300, 20);
        painelCadastrarDrone.add(rotuloCargaMaxima);

        campoTextoCargaMaxima = new JTextField();
        campoTextoCargaMaxima.setBounds(300, 225, 193, 28);
        painelCadastrarDrone.add(campoTextoCargaMaxima);

        rotuloCargaMaximaInvalido = new JLabel("Digite um peso válida.");
        rotuloCargaMaximaInvalido.setBounds(493, 225, 300, 20);
        rotuloCargaMaximaInvalido.setForeground(Color.RED);
        rotuloCargaMaximaInvalido.setVisible(false);
        painelCadastrarDrone.add(rotuloCargaMaximaInvalido);

        rotuloAutonomia = new JLabel("Autonomia (em quilômetros)");
        rotuloAutonomia.setBounds(300, 255, 300, 20);
        painelCadastrarDrone.add(rotuloAutonomia);

        campoTextoAutonomia = new JTextField();
        campoTextoAutonomia.setBounds(300, 275, 193, 28);
        painelCadastrarDrone.add(campoTextoAutonomia);

        rotuloAutonomiaInvalido = new JLabel("Digite uma distância válida.");
        rotuloAutonomiaInvalido.setBounds(493, 275, 300, 20);
        rotuloAutonomiaInvalido.setForeground(Color.RED);
        rotuloAutonomiaInvalido.setVisible(false);
        painelCadastrarDrone.add(rotuloAutonomiaInvalido);

        rotuloBase = new JLabel("Código de sua base");
        rotuloBase.setBounds(300, 305, 300, 20);
        painelCadastrarDrone.add(rotuloBase);

        campoTextoBase = new JTextField();
        campoTextoBase.setBounds(300, 325, 193, 28);
        painelCadastrarDrone.add(campoTextoBase);

        rotuloBaseInvalido = new JLabel();
        rotuloBaseInvalido.setBounds(493, 325, 300, 20);
        rotuloBaseInvalido.setForeground(Color.RED);
        rotuloBaseInvalido.setVisible(false);
        painelCadastrarDrone.add(rotuloBaseInvalido);

        botaoCadastrarDrone = new JButton("Cadastrar");
        botaoCadastrarDrone.setBounds(300, 365, 90, 25);
        painelCadastrarDrone.add(botaoCadastrarDrone);

        rotuloCadastrarDroneStatus = new JLabel();
        rotuloCadastrarDroneStatus.setBounds(300, 365, 300, 20);
        painelCadastrarDrone.add(rotuloCadastrarDroneStatus);

        painelPrincipal.add(painelCadastrarDrone, "painelCadastrarDrone");

        botaoVoltarCadastrarDrone.addActionListener(e -> {
            rotuloCadastrarDroneStatus.setVisible(false);;
            botaoCadastrarDrone.setVisible(true);
            campoTextoIdentificador.setText("");
            campoTextoCargaMaxima.setText("");
            campoTextoAutonomia.setText("");
            campoTextoBase.setText("");
            rotuloIdentificadorInvalido.setVisible(false);
            rotuloCargaMaximaInvalido.setVisible(false);
            rotuloAutonomiaInvalido.setVisible(false);
            rotuloBaseInvalido.setVisible(false);
            cardLayout.show(painelPrincipal, "painelAdministrador");
        });

        botaoCadastrarDrone.addActionListener(e -> {
            validade = true;
            try {
                identificador = Integer.parseInt(campoTextoIdentificador.getText());
                if (identificador < 0) {
                    rotuloIdentificadorInvalido.setText("Digite um número válido.");
                    rotuloIdentificadorInvalido.setVisible(true);
                    validade = false;
                } else {
                    rotuloIdentificadorInvalido.setVisible(false);
                }
            } catch (Exception exc) {
                rotuloIdentificadorInvalido.setText("Digite um número válido.");
                rotuloIdentificadorInvalido.setVisible(true);
                validade = false;
            }
            try {
                cargaMaxima = Double.parseDouble(campoTextoCargaMaxima.getText());
                if (cargaMaxima < 0) {
                    rotuloCargaMaximaInvalido.setVisible(true);
                    validade = false;
                } else {
                    rotuloCargaMaximaInvalido.setVisible(false);
                }
            } catch (Exception exc) {
                rotuloCargaMaximaInvalido.setVisible(true);
                validade = false;
            }
            try {
                autonomiaKm = Double.parseDouble(campoTextoAutonomia.getText());
                if (autonomiaKm < 0) {
                    rotuloAutonomiaInvalido.setVisible(true);
                    validade = false;
                } else {
                    rotuloAutonomiaInvalido.setVisible(false);
                }
            } catch (Exception exc) {
                rotuloAutonomiaInvalido.setVisible(true);
                validade = false;
            }
            try {
                base = Integer.parseInt(campoTextoBase.getText());
                if (acmeDrones.localizacaoExistente(base) == false) {
                    rotuloBaseInvalido.setText("Localização com código não cadastrada.");
                    rotuloBaseInvalido.setVisible(true);
                    validade = false;
                } else {
                    rotuloBaseInvalido.setVisible(false);
                }
            } catch (Exception exc) {
                rotuloBaseInvalido.setText("Digite um número válido.");
                rotuloBaseInvalido.setVisible(true);
                validade = false;
            }
            if (validade == true) {
                if (acmeDrones.cadastrarDrone(identificador, cargaMaxima, autonomiaKm, acmeDrones.buscarLocalizacao(base))) {
                    rotuloCadastrarDroneStatus.setText("Drone cadastrado com sucesso.");
                    rotuloCadastrarDroneStatus.setVisible(true);
                    botaoCadastrarDrone.setVisible(false);
                    campoTextoIdentificador.setText("");
                    campoTextoCargaMaxima.setText("");
                    campoTextoAutonomia.setText("");
                    campoTextoBase.setText("");
                } else {
                    rotuloIdentificadorInvalido.setText("Identificador já cadastrada.");
                    rotuloIdentificadorInvalido.setVisible(true);
                }
            }
        });

        // Painel Cadastrar Cliente

        painelCadastrarCliente = new JPanel();
        painelCadastrarCliente.setLayout(null);

        botaoVoltarCadastrarCliente = new JButton("Voltar");
        botaoVoltarCadastrarCliente.setBounds(40, 20, 90, 25);
        painelCadastrarCliente.add(botaoVoltarCadastrarCliente);

        rotuloNome = new JLabel("Nome");
        rotuloNome.setBounds(300, 158, 300, 20);
        painelCadastrarCliente.add(rotuloNome);

        campoTextoNome = new JTextField();
        campoTextoNome.setBounds(300, 177, 193, 28);
        painelCadastrarCliente.add(campoTextoNome);

        rotuloNomeInvalido = new JLabel("Digite um nome válido.");
        rotuloNomeInvalido.setBounds(493, 177, 200, 20);
        rotuloNomeInvalido.setForeground(Color.RED);
        rotuloNomeInvalido.setVisible(false);
        painelCadastrarCliente.add(rotuloNomeInvalido);

        rotuloEndereco = new JLabel("Código de seu endereço");
        rotuloEndereco.setBounds(300, 205, 300, 20);
        painelCadastrarCliente.add(rotuloEndereco);

        campoTextoEndereco = new JTextField();
        campoTextoEndereco.setBounds(300, 225, 193, 28);
        painelCadastrarCliente.add(campoTextoEndereco);

        rotuloEnderecoInvalido = new JLabel();
        rotuloEnderecoInvalido.setBounds(493, 225, 300, 20);
        rotuloEnderecoInvalido.setForeground(Color.RED);
        rotuloEnderecoInvalido.setVisible(false);
        painelCadastrarCliente.add(rotuloEnderecoInvalido);

        rotuloEmailCadastro = new JLabel("Email");
        rotuloEmailCadastro.setBounds(300, 255, 300, 20);
        painelCadastrarCliente.add(rotuloEmailCadastro);

        campoTextoEmailCadastro = new JTextField();
        campoTextoEmailCadastro.setBounds(300, 275, 193, 28);
        painelCadastrarCliente.add(campoTextoEmailCadastro);

        rotuloEmailCadastroInvalido = new JLabel();
        rotuloEmailCadastroInvalido.setBounds(493, 275, 300, 20);
        rotuloEmailCadastroInvalido.setForeground(Color.RED);
        rotuloEmailCadastroInvalido.setVisible(false);
        painelCadastrarCliente.add(rotuloEmailCadastroInvalido);

        rotuloSenhaCadastro = new JLabel("Senha");
        rotuloSenhaCadastro.setBounds(300, 305, 300, 20);
        painelCadastrarCliente.add(rotuloSenhaCadastro);

        campoTextoSenhaCadastro = new JPasswordField();
        campoTextoSenhaCadastro.setBounds(300, 325, 193, 28);
        painelCadastrarCliente.add(campoTextoSenhaCadastro);

        rotuloSenhaCadastroInvalido = new JLabel("No mínimo 5 dígitos.");
        rotuloSenhaCadastroInvalido.setBounds(493, 325, 300, 20);
        rotuloSenhaCadastroInvalido.setForeground(Color.RED);
        rotuloSenhaCadastroInvalido.setVisible(false);
        painelCadastrarCliente.add(rotuloSenhaCadastroInvalido);

        botaoCadastrarCliente = new JButton("Cadastrar");
        botaoCadastrarCliente.setBounds(300, 365, 90, 25);
        painelCadastrarCliente.add(botaoCadastrarCliente);

        areaTextoCadastrarClienteDados = new JTextArea();
        areaTextoCadastrarClienteDados.setEditable(false);
        cadastrarClienteDados = new JScrollPane(areaTextoCadastrarClienteDados);
        cadastrarClienteDados.setBounds(100, 365, 600, 100);
        cadastrarClienteDados.setVisible(false);
        painelCadastrarCliente.add(cadastrarClienteDados);

        painelPrincipal.add(painelCadastrarCliente, "painelCadastrarCliente");

        botaoVoltarCadastrarCliente.addActionListener(e -> {
            cadastrarClienteDados.setVisible(false);;
            botaoCadastrarCliente.setVisible(true);
            campoTextoNome.setText("");
            campoTextoEndereco.setText("");
            campoTextoEmailCadastro.setText("");
            campoTextoSenhaCadastro.setText("");
            rotuloNomeInvalido.setVisible(false);
            rotuloEnderecoInvalido.setVisible(false);
            rotuloEmailCadastroInvalido.setVisible(false);
            rotuloSenhaCadastroInvalido.setVisible(false);
            cardLayout.show(painelPrincipal, "painelAdministrador");
        });

        botaoCadastrarCliente.addActionListener(e -> {
            validade = true;
            nome = campoTextoNome.getText();
            if (nome.isEmpty()) {
                rotuloNomeInvalido.setVisible(true);
                validade = false;
            } else {
                rotuloNomeInvalido.setVisible(false);
            }
            try {
                endereco = Integer.parseInt(campoTextoEndereco.getText());
                if (acmeDrones.localizacaoExistente(endereco) == false) {
                    rotuloEnderecoInvalido.setText("Localização com código não cadastrada.");
                    rotuloEnderecoInvalido.setVisible(true);
                    validade = false;
                } else {
                    rotuloEnderecoInvalido.setVisible(false);
                }
            } catch (Exception exc) {
                rotuloEnderecoInvalido.setText("Digite um número válido.");
                rotuloEnderecoInvalido.setVisible(true);
                validade = false;
            }
            email = campoTextoEmailCadastro.getText();
            if (email.isEmpty()) {
                rotuloEmailCadastroInvalido.setText("Digite um email válido.");
                rotuloEmailCadastroInvalido.setVisible(true);
                validade = false;
            } else {
                rotuloEmailCadastroInvalido.setVisible(false);
            }
            senha = String.valueOf(campoTextoSenhaCadastro.getPassword());
            if (senha.length() < 5) {
                rotuloSenhaCadastroInvalido.setVisible(true);
                validade = false;
            } else {
                rotuloSenhaCadastroInvalido.setVisible(false);
            }
            if (validade == true) {
                if (acmeDrones.cadastrarCliente(nome, acmeDrones.buscarLocalizacao(endereco), email, senha)) {
                    areaTextoCadastrarClienteDados.setText("Cliente cadastrado. Dados:\n" + acmeDrones.buscarCliente(email));
                    cadastrarClienteDados.setVisible(true);
                    botaoCadastrarCliente.setVisible(false);
                    campoTextoNome.setText("");
                    campoTextoEndereco.setText("");
                    campoTextoEmailCadastro.setText("");
                    campoTextoSenhaCadastro.setText("");
                } else {
                    rotuloEmailCadastroInvalido.setText("Email já cadastrada.");
                    rotuloEmailCadastroInvalido.setVisible(true);
                }
            }
        });

        // Painel Consultar Todas Entregas

        painelConsultarTodasEntregas = new JPanel();
        painelConsultarTodasEntregas.setLayout(null);

        botaoVoltarConsultarTodasEntregas = new JButton("Voltar");
        botaoVoltarConsultarTodasEntregas.setBounds(40, 20, 90, 25);
        painelConsultarTodasEntregas.add(botaoVoltarConsultarTodasEntregas);

        rotuloTituloTodasEntregas = new JLabel("Todas as entregas realizadas");
        rotuloTituloTodasEntregas.setBounds(50, 60, 200, 20);
        painelConsultarTodasEntregas.add(rotuloTituloTodasEntregas);

        areaTextoTodasEntregas = new JTextArea();
        areaTextoTodasEntregas.setEditable(false);
        entregas = new JScrollPane(areaTextoTodasEntregas);
        entregas.setBounds(50, 90, 700, 430); 
        painelConsultarTodasEntregas.add(entregas);

        painelPrincipal.add(painelConsultarTodasEntregas, "painelConsultarTodasEntregas");

        botaoVoltarConsultarTodasEntregas.addActionListener(e -> {
            cardLayout.show(painelPrincipal, "painelAdministrador");
        });

        // Painel Cliente

        painelCliente = new JPanel();
        painelCliente.setLayout(new BoxLayout(painelCliente, BoxLayout.PAGE_AXIS));

        botaoConsultarEntregas = new JButton("Consultar entregas");
        botaoConsultarEntregas.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelCliente.add(botaoConsultarEntregas);

        botaoConsultarCobranca = new JButton("Consultar cobrança mensal");
        botaoConsultarCobranca.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelCliente.add(botaoConsultarCobranca);

        botaoSairCliente = new JButton("Sair");
        botaoSairCliente.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelCliente.add(botaoSairCliente);

        painelPrincipal.add(painelCliente, "painelCliente");

        botaoConsultarEntregas.addActionListener(e -> {
            areaTextoEntregas.setText(acmeDrones.consultarEntregas());
            cardLayout.show(painelPrincipal, "painelConsultarEntregas");
        });

        botaoConsultarCobranca.addActionListener(e -> {
            cardLayout.show(painelPrincipal, "painelConsultarCobrancaForm");
        });

        botaoSairCliente.addActionListener(e -> {
            campoTextoEmail.setText("");
            campoSenha.setText("");
            rotuloMensagem.setVisible(false);
            cardLayout.show(painelPrincipal, "painelLogin");
        });

        // Painel Consultar Entregas

        painelConsultarEntregas = new JPanel();
        painelConsultarEntregas.setLayout(null);

        botaoVoltarConsultarEntregas = new JButton("Voltar");
        botaoVoltarConsultarEntregas.setBounds(40, 20, 90, 25);
        painelConsultarEntregas.add(botaoVoltarConsultarEntregas);

        rotuloTituloEntregas = new JLabel("Entregas");
        rotuloTituloEntregas.setBounds(50, 60, 70, 20);
        painelConsultarEntregas.add(rotuloTituloEntregas);

        areaTextoEntregas = new JTextArea();
        areaTextoEntregas.setEditable(false);
        entregas = new JScrollPane(areaTextoEntregas);
        entregas.setBounds(50, 90, 700, 430); 
        painelConsultarEntregas.add(entregas);

        painelPrincipal.add(painelConsultarEntregas, "painelConsultarEntregas");

        botaoVoltarConsultarEntregas.addActionListener(e -> {
            cardLayout.show(painelPrincipal, "painelCliente");
        });

        // Painel Formulário Consultar Cobrança Mensal

        painelConsultarCobrancaForm = new JPanel();
        painelConsultarCobrancaForm.setLayout(null);

        botaoVoltarConsultarCobrancaForm = new JButton("Voltar");
        botaoVoltarConsultarCobrancaForm.setBounds(40, 20, 90, 25);
        painelConsultarCobrancaForm.add(botaoVoltarConsultarCobrancaForm);

        rotuloAno = new JLabel("Ano");
        rotuloAno.setBounds(300, 158, 70, 20);
        painelConsultarCobrancaForm.add(rotuloAno);

        campoTextoAno = new JTextField();
        campoTextoAno.setBounds(300, 177, 193, 28);
        painelConsultarCobrancaForm.add(campoTextoAno);

        rotuloAnoInvalido = new JLabel("Digite um ano válido.");
        rotuloAnoInvalido.setBounds(493, 177, 200, 20);
        rotuloAnoInvalido.setForeground(Color.RED);
        rotuloAnoInvalido.setVisible(false);
        painelConsultarCobrancaForm.add(rotuloAnoInvalido);

        rotuloMes = new JLabel("Mês");
        rotuloMes.setBounds(300, 205, 70, 20);
        painelConsultarCobrancaForm.add(rotuloMes);

        campoTextoMes = new JTextField();
        campoTextoMes.setBounds(300, 225, 193, 28);
        painelConsultarCobrancaForm.add(campoTextoMes);

        rotuloMesInvalido = new JLabel("Digite um mes válido (valor numérico).");
        rotuloMesInvalido.setBounds(493, 225, 400, 20);
        rotuloMesInvalido.setForeground(Color.RED);
        rotuloMesInvalido.setVisible(false);
        painelConsultarCobrancaForm.add(rotuloMesInvalido);

        botaoBuscarCobranca = new JButton("Buscar");
        botaoBuscarCobranca.setBounds(300, 260, 90, 25);
        painelConsultarCobrancaForm.add(botaoBuscarCobranca);

        painelPrincipal.add(painelConsultarCobrancaForm, "painelConsultarCobrancaForm");

        botaoVoltarConsultarCobrancaForm.addActionListener(e -> {
            cardLayout.show(painelPrincipal, "painelCliente");
        });

        botaoBuscarCobranca.addActionListener(e -> {
            validade = true;
            try {
                int ano = Integer.parseInt(campoTextoAno.getText());
                if (ano < 0) {
                    rotuloAnoInvalido.setVisible(true);
                    validade = false;
                } else {
                    rotuloAnoInvalido.setVisible(false);
                }
            } catch (Exception exc) {
                rotuloAnoInvalido.setVisible(true);
                validade = false;
            }
            try {
                int mes = Integer.parseInt(campoTextoMes.getText());
                if (mes < 1 || mes > 12) {
                    rotuloMesInvalido.setVisible(true);
                    validade = false;
                } else {
                    rotuloMesInvalido.setVisible(false);
                }
            } catch (Exception exc) {
                rotuloMesInvalido.setVisible(true);
                validade = false;
            }
            if (validade == true) {
                areaTextoCobrancaMensal.setText(acmeDrones.consultarCobrancaMensal(ano, mes));
                cardLayout.show(painelPrincipal, "painelConsultarCobranca");
            }
        });

        // Painel Consultar Cobrança Mensal

        painelConsultarCobranca = new JPanel();
        painelConsultarCobranca.setLayout(null);

        botaoVoltarFormConsultarCobranca = new JButton("Voltar");
        botaoVoltarFormConsultarCobranca.setBounds(40, 20, 90, 25);
        painelConsultarCobranca.add(botaoVoltarFormConsultarCobranca);

        rotuloTituloEntregasCobrancaMensal = new JLabel("Entregas realizadas no período e cobrança mensal");
        rotuloTituloEntregasCobrancaMensal.setBounds(50, 60, 400, 20);
        painelConsultarCobranca.add(rotuloTituloEntregasCobrancaMensal);

        areaTextoCobrancaMensal = new JTextArea();
        areaTextoCobrancaMensal.setEditable(false);
        entregasCobrancaMensal = new JScrollPane(areaTextoCobrancaMensal);
        entregasCobrancaMensal.setBounds(50, 90, 700, 430); 
        painelConsultarCobranca.add(entregasCobrancaMensal);

        painelPrincipal.add(painelConsultarCobranca, "painelConsultarCobranca");

        botaoVoltarFormConsultarCobranca.addActionListener(e -> {
            campoTextoAno.setText("");
            campoTextoMes.setText("");
            cardLayout.show(painelPrincipal, "painelConsultarCobrancaForm");
        });
    }
}
