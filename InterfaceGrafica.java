import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class InterfaceGrafica extends JFrame {
    CardLayout cardLayout;
    int origem, destino, ano, mes, codigo, identificador, base, endereco, numero;
    boolean validade, mesValido;
    LocalDate data, dataValidade;
    Cliente cliente;
    String descricaoMateriais, descricao, logradouro, nome, email, senha;
    double peso, latitude, longitude, cargaMaxima, autonomiaKm;
    JPanel painelCadastrarNaoPerecivel, painelCadastrarPerecivel, painelCadastrarEntregaOpcao, painelCadastrarCliente, painelCadastrarDrone, painelCadastrarLocalizacao, painelPrincipal, painelLogin, painelCliente, painelConsultarEntregas, painelConsultarCobrancaForm, painelConsultarCobranca, painelAdministrador, painelConsultarTodasEntregas;
    JLabel rotuloDescricaoMateriaisInvalido, rotuloDroneNaoPerecivel, rotuloDroneNaoPerecivelInvalido, rotuloDescricaoMateriais, rotuloClienteNaoPerecivelInvalido, rotuloClienteNaoPerecivel, rotuloDestinoNaoPerecivelInvalido, rotuloDestinoNaoPerecivel, rotuloOrigemNaoPerecivelInvalido, rotuloOrigemNaoPerecivel, rotuloPesoNaoPerecivelInvalido, rotuloPesoNaoPerecivel, rotuloDescricaoNaoPerecivel, rotuloNumeroNaoPerecivelInvalido, rotuloNumeroNaoPerecivel, rotuloDronePerecivelInvalido, rotuloClientePerecivelInvalido, rotuloDronePerecivel, rotuloValidadePerecivelInvalido, rotuloValidadePerecivel, rotuloClientePerecivel, rotuloDestinoPerecivelInvalido, rotuloDestinoPerecivel, rotuloOrigemPerecivelInvalido, rotuloOrigemPerecivel, rotuloPesoPerecivelInvalido, rotuloPesoPerecivel, rotuloDescricaoPerecivel, rotuloNumeroPerecivelInvalido, rotuloNumeroPerecivel, rotuloSenhaCadastroInvalido, rotuloSenhaCadastro, rotuloEmailCadastroInvalido, rotuloEmailCadastro, rotuloEnderecoInvalido, rotuloEndereco, rotuloNomeInvalido, rotuloNome, rotuloCadastrarDroneStatus, rotuloBaseInvalido, rotuloBase, rotuloAutonomiaInvalido, rotuloAutonomia, rotuloCargaMaximaInvalido, rotuloCargaMaxima, rotuloIdentificadorInvalido, rotuloIdentificador, rotuloCadastrarLocalStatus, rotuloLongitudeInvalido, rotuloLatitudeInvalido, rotuloLatitude, rotuloLongitude, rotuloLogradouro, rotuloCodigoLocalInvalido, rotuloCodigoLocal, rotuloTituloTodasEntregas, rotuloEmail, rotuloSenha, rotuloMensagem, rotuloTituloEntregas, rotuloAno, rotuloMes, rotuloAnoInvalido, rotuloMesInvalido, rotuloTituloEntregasCobrancaMensal;
    JTextField campoTextoDescricaoMateriais, campoTextoDestinoNaoPerecivel, campoTextoOrigemNaoPerecivel, campoTextoPesoNaoPerecivel, campoTextoDescricaoNaoPerecivel, campoTextoNumeroNaoPerecivel, campoTextoValidadePerecivel, campoTextoDestinoPerecivel, campoTextoOrigemPerecivel, campoTextoPesoPerecivel, campoTextoDescricaoPerecivel, campoTextoNumeroPerecivel, campoTextoEmailCadastro, campoTextoEndereco, campoTextoNome, campoTextoBase, campoTextoAutonomia, campoTextoCargaMaxima, campoTextoIdentificador, campoTextoLongitude, campoTextoLatitude, campoTextoLogradouro, campoTextoCodigoLocal, campoTextoEmail, campoTextoAno, campoTextoMes;
    JPasswordField campoTextoSenhaCadastro, campoSenha;
    JButton botaoCadastrarEntregaNaoPerecivel, botaoSelecionarDroneNaoPerecivel, botaoVoltarCadastrarNaoPerecivel, botaoCadastrarEntrega, botaoSelecionarDronePerecivel, botaoVoltarCadastrarPerecivel, botaoCadastroPerecivel, botaoCadastroNaoPerecivel, botaoVoltarCadastrarEntregaOpcao, botaoCadastrarCliente, botaoVoltarCadastrarCliente, botaoCadastrarDrone, botaoCadastrarLocal, botaoVoltarCadastrarDrone, botaoVoltarConsultarTodasEntregas, botaoVoltarConsultarCobrancaForm, botaoVoltarCadastrarLocal, botaoSairAdmin, botaoSimularCarga, botaoConsultarTodasEntregas, botaoCadastroEntrega, botaoCadastroCliente, botaoCadastroDrone, botaoCadastroLocal, botaoEntrar, botaoConsultarEntregas, botaoConsultarCobranca, botaoVoltarConsultarEntregas, botaoBuscarCobranca, botaoVoltarFormConsultarCobranca, botaoSairCliente;
    JTextArea areaTextoCadastrarEntregaNaoPerecivelDados, areaTextoCadastrarEntregaDados, areaTextoCadastrarClienteDados, areaTextoEntregas, areaTextoCobrancaMensal, areaTextoTodasEntregas;
    JScrollPane cadastrarEntregaNaoPerecivelDados, cadastrarEntregaDados, cadastrarClienteDados, entregas, entregasCobrancaMensal;
    JComboBox caixaOpcoesDroneNaoPerecivel, caixaOpcoesClienteNaoPerecivel, caixaOpcoesCliente, caixaOpcoesDrone;
    EntregaPerecivel entregaPerecivel;
    EntregaNaoPerecivel entregaNaoPerecivel;
    Drone drone;

    public InterfaceGrafica(ACMEDrones acmeDrones) {
        super();

        setTitle("ACME Drones");
        setLocation(new Point(500, 300));
        setSize(new Dimension(800, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                acmeDrones.salvarDados();
            }
        });

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
            cardLayout.show(painelPrincipal, "painelCadastrarEntregaOpcao");
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

        // Painel Cadastrar Entrega

        painelCadastrarEntregaOpcao = new JPanel();
        painelCadastrarEntregaOpcao.setLayout(new BoxLayout(painelCadastrarEntregaOpcao, BoxLayout.PAGE_AXIS));

        botaoCadastroPerecivel = new JButton("Cadastrar entrega perecível");
        botaoCadastroPerecivel.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelCadastrarEntregaOpcao.add(botaoCadastroPerecivel);

        botaoCadastroNaoPerecivel = new JButton("Cadastrar entrega não perecível");
        botaoCadastroNaoPerecivel.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelCadastrarEntregaOpcao.add(botaoCadastroNaoPerecivel);

        botaoVoltarCadastrarEntregaOpcao = new JButton("Voltar");
        botaoVoltarCadastrarEntregaOpcao.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelCadastrarEntregaOpcao.add(botaoVoltarCadastrarEntregaOpcao);

        painelPrincipal.add(painelCadastrarEntregaOpcao, "painelCadastrarEntregaOpcao");

        botaoVoltarCadastrarEntregaOpcao.addActionListener(e -> {
            cardLayout.show(painelPrincipal, "painelAdministrador");
        });

        botaoCadastroPerecivel.addActionListener(e -> {
            String[] emailClientes = new String[acmeDrones.quantidadeClientes()];
            for (int i = 0; i < acmeDrones.quantidadeClientes(); i++) {
                emailClientes[i] = acmeDrones.getClientes().get(i).getEmail();
            }
            painelCadastrarPerecivel.remove(caixaOpcoesCliente);
            caixaOpcoesCliente = new JComboBox<String>(emailClientes);
            caixaOpcoesCliente.setBounds(300, 325, 300, 20);
            painelCadastrarPerecivel.add(caixaOpcoesCliente);
            cadastrarEntregaDados.setVisible(false);
            botaoSelecionarDronePerecivel.setVisible(true);
            rotuloNumeroPerecivel.setVisible(true);
            campoTextoNumeroPerecivel.setVisible(true);
            campoTextoNumeroPerecivel.setText("");
            rotuloNumeroPerecivelInvalido.setVisible(false);
            rotuloDescricaoPerecivel.setVisible(true);
            campoTextoDescricaoPerecivel.setVisible(true);
            campoTextoDescricaoPerecivel.setText("");
            rotuloPesoPerecivel.setVisible(true);
            campoTextoPesoPerecivel.setVisible(true);
            campoTextoPesoPerecivel.setText("");
            rotuloPesoPerecivelInvalido.setVisible(false);
            rotuloOrigemPerecivel.setVisible(true);
            campoTextoOrigemPerecivel.setVisible(true);
            campoTextoOrigemPerecivel.setText("");
            rotuloOrigemPerecivelInvalido.setVisible(false);
            rotuloDestinoPerecivel.setVisible(true);
            campoTextoDestinoPerecivel.setVisible(true);
            campoTextoDestinoPerecivel.setText("");
            rotuloDestinoPerecivelInvalido.setVisible(false);
            rotuloClientePerecivel.setVisible(true);
            caixaOpcoesCliente.setVisible(true);
            rotuloClientePerecivelInvalido.setVisible(false);
            rotuloValidadePerecivel.setVisible(true);
            campoTextoValidadePerecivel.setVisible(true);
            campoTextoValidadePerecivel.setText("");
            rotuloValidadePerecivelInvalido.setVisible(false);
            botaoSelecionarDronePerecivel.setVisible(true);
            rotuloDronePerecivel.setVisible(false);
            caixaOpcoesDrone.removeAll();
            caixaOpcoesDrone.setVisible(false);
            botaoCadastrarEntrega.setVisible(false);
            areaTextoCadastrarEntregaDados.setVisible(false);
            rotuloDronePerecivelInvalido.setVisible(false);
            cardLayout.show(painelPrincipal, "painelCadastrarPerecivel");
        });

        botaoCadastroNaoPerecivel.addActionListener(e -> {
            String[] emailClientes = new String[acmeDrones.quantidadeClientes()];
            for (int i = 0; i < acmeDrones.quantidadeClientes(); i++) {
                emailClientes[i] = acmeDrones.getClientes().get(i).getEmail();
            }
            painelCadastrarNaoPerecivel.remove(caixaOpcoesClienteNaoPerecivel);
            caixaOpcoesClienteNaoPerecivel = new JComboBox<String>(emailClientes);
            caixaOpcoesClienteNaoPerecivel.setBounds(300, 325, 300, 20);
            painelCadastrarNaoPerecivel.add(caixaOpcoesClienteNaoPerecivel);
            cadastrarEntregaNaoPerecivelDados.setVisible(false);
            botaoSelecionarDroneNaoPerecivel.setVisible(true);
            rotuloNumeroNaoPerecivel.setVisible(true);
            campoTextoNumeroNaoPerecivel.setVisible(true);
            campoTextoNumeroNaoPerecivel.setText("");
            rotuloNumeroNaoPerecivelInvalido.setVisible(false);
            rotuloDescricaoNaoPerecivel.setVisible(true);
            campoTextoDescricaoNaoPerecivel.setVisible(true);
            campoTextoDescricaoNaoPerecivel.setText("");
            rotuloPesoNaoPerecivel.setVisible(true);
            campoTextoPesoNaoPerecivel.setVisible(true);
            campoTextoPesoNaoPerecivel.setText("");
            rotuloPesoNaoPerecivelInvalido.setVisible(false);
            rotuloOrigemNaoPerecivel.setVisible(true);
            campoTextoOrigemNaoPerecivel.setVisible(true);
            campoTextoOrigemNaoPerecivel.setText("");
            rotuloOrigemNaoPerecivelInvalido.setVisible(false);
            rotuloDestinoNaoPerecivel.setVisible(true);
            campoTextoDestinoNaoPerecivel.setVisible(true);
            campoTextoDestinoNaoPerecivel.setText("");
            rotuloDestinoNaoPerecivelInvalido.setVisible(false);
            rotuloClienteNaoPerecivel.setVisible(true);
            caixaOpcoesClienteNaoPerecivel.setVisible(true);
            rotuloClienteNaoPerecivelInvalido.setVisible(false);
            rotuloDescricaoMateriais.setVisible(true);
            campoTextoDescricaoMateriais.setVisible(true);
            campoTextoDescricaoMateriais.setText("");
            rotuloDescricaoMateriaisInvalido.setVisible(false);
            botaoSelecionarDroneNaoPerecivel.setVisible(true);
            rotuloDroneNaoPerecivel.setVisible(false);
            caixaOpcoesDroneNaoPerecivel.removeAll();
            caixaOpcoesDroneNaoPerecivel.setVisible(false);
            botaoCadastrarEntregaNaoPerecivel.setVisible(false);
            areaTextoCadastrarEntregaNaoPerecivelDados.setVisible(false);
            rotuloDroneNaoPerecivelInvalido.setVisible(false);
            cardLayout.show(painelPrincipal, "painelCadastrarNaoPerecivel");
        });

        // Painel Cadastrar Entrega Perecível

        painelCadastrarPerecivel = new JPanel();
        painelCadastrarPerecivel.setLayout(null);

        botaoVoltarCadastrarPerecivel = new JButton("Voltar");
        botaoVoltarCadastrarPerecivel.setBounds(40, 20, 90, 25);
        painelCadastrarPerecivel.add(botaoVoltarCadastrarPerecivel);

        rotuloNumeroPerecivel = new JLabel("Número");
        rotuloNumeroPerecivel.setBounds(300, 58, 300, 20);
        painelCadastrarPerecivel.add(rotuloNumeroPerecivel);

        campoTextoNumeroPerecivel = new JTextField();
        campoTextoNumeroPerecivel.setBounds(300, 78, 193, 28);
        painelCadastrarPerecivel.add(campoTextoNumeroPerecivel);

        rotuloNumeroPerecivelInvalido = new JLabel();
        rotuloNumeroPerecivelInvalido.setBounds(493, 78, 200, 20);
        rotuloNumeroPerecivelInvalido.setForeground(Color.RED);
        rotuloNumeroPerecivelInvalido.setVisible(false);
        painelCadastrarPerecivel.add(rotuloNumeroPerecivelInvalido);

        rotuloDescricaoPerecivel = new JLabel("Descrição");
        rotuloDescricaoPerecivel.setBounds(300, 108, 300, 20);
        painelCadastrarPerecivel.add(rotuloDescricaoPerecivel);

        campoTextoDescricaoPerecivel = new JTextField();
        campoTextoDescricaoPerecivel.setBounds(300, 128, 193, 28);
        painelCadastrarPerecivel.add(campoTextoDescricaoPerecivel);

        rotuloPesoPerecivel = new JLabel("Peso");
        rotuloPesoPerecivel.setBounds(300, 158, 300, 20);
        painelCadastrarPerecivel.add(rotuloPesoPerecivel);

        campoTextoPesoPerecivel = new JTextField();
        campoTextoPesoPerecivel.setBounds(300, 177, 193, 28);
        painelCadastrarPerecivel.add(campoTextoPesoPerecivel);

        rotuloPesoPerecivelInvalido = new JLabel("Digite um peso válido.");
        rotuloPesoPerecivelInvalido.setBounds(493, 177, 200, 20);
        rotuloPesoPerecivelInvalido.setForeground(Color.RED);
        rotuloPesoPerecivelInvalido.setVisible(false);
        painelCadastrarPerecivel.add(rotuloPesoPerecivelInvalido);

        rotuloOrigemPerecivel = new JLabel("Código de sua origem");
        rotuloOrigemPerecivel.setBounds(300, 205, 300, 20);
        painelCadastrarPerecivel.add(rotuloOrigemPerecivel);

        campoTextoOrigemPerecivel = new JTextField();
        campoTextoOrigemPerecivel.setBounds(300, 225, 193, 28);
        painelCadastrarPerecivel.add(campoTextoOrigemPerecivel);

        rotuloOrigemPerecivelInvalido = new JLabel();
        rotuloOrigemPerecivelInvalido.setBounds(493, 225, 300, 20);
        rotuloOrigemPerecivelInvalido.setForeground(Color.RED);
        rotuloOrigemPerecivelInvalido.setVisible(false);
        painelCadastrarPerecivel.add(rotuloOrigemPerecivelInvalido);

        rotuloDestinoPerecivel = new JLabel("Código de seu destino");
        rotuloDestinoPerecivel.setBounds(300, 255, 300, 20);
        painelCadastrarPerecivel.add(rotuloDestinoPerecivel);

        campoTextoDestinoPerecivel = new JTextField();
        campoTextoDestinoPerecivel.setBounds(300, 275, 193, 28);
        painelCadastrarPerecivel.add(campoTextoDestinoPerecivel);

        rotuloDestinoPerecivelInvalido = new JLabel();
        rotuloDestinoPerecivelInvalido.setBounds(493, 275, 300, 20);
        rotuloDestinoPerecivelInvalido.setForeground(Color.RED);
        rotuloDestinoPerecivelInvalido.setVisible(false);
        painelCadastrarPerecivel.add(rotuloDestinoPerecivelInvalido);

        rotuloClientePerecivel = new JLabel("Cliente");
        rotuloClientePerecivel.setBounds(300, 305, 300, 20);
        painelCadastrarPerecivel.add(rotuloClientePerecivel);

        caixaOpcoesCliente = new JComboBox();
        caixaOpcoesCliente.setBounds(300, 325, 300, 20);
        painelCadastrarPerecivel.add(caixaOpcoesCliente);

        rotuloClientePerecivelInvalido = new JLabel("Selecione um cliente.");
        rotuloClientePerecivelInvalido.setBounds(600, 325, 193, 28);
        rotuloClientePerecivelInvalido.setForeground(Color.RED);
        rotuloClientePerecivelInvalido.setVisible(false);
        painelCadastrarPerecivel.add(rotuloClientePerecivelInvalido);

        rotuloValidadePerecivel = new JLabel("Validade (dia/mês/ano)");
        rotuloValidadePerecivel.setBounds(300, 355, 300, 20);
        painelCadastrarPerecivel.add(rotuloValidadePerecivel);

        campoTextoValidadePerecivel = new JTextField();
        campoTextoValidadePerecivel.setBounds(300, 375, 193, 28);
        painelCadastrarPerecivel.add(campoTextoValidadePerecivel);

        rotuloValidadePerecivelInvalido = new JLabel("Digite uma data válida.");
        rotuloValidadePerecivelInvalido.setBounds(493, 375, 300, 20);
        rotuloValidadePerecivelInvalido.setForeground(Color.RED);
        rotuloValidadePerecivelInvalido.setVisible(false);
        painelCadastrarPerecivel.add(rotuloValidadePerecivelInvalido);

        botaoSelecionarDronePerecivel = new JButton("Selecionar drone");
        botaoSelecionarDronePerecivel.setBounds(300, 405, 250, 25);
        painelCadastrarPerecivel.add(botaoSelecionarDronePerecivel);

        rotuloDronePerecivelInvalido = new JLabel("Nenhum drone com capacidade para a entrega.");
        rotuloDronePerecivelInvalido.setBounds(300, 435, 300, 20);
        rotuloDronePerecivelInvalido.setForeground(Color.RED);
        rotuloDronePerecivelInvalido.setVisible(false);
        painelCadastrarPerecivel.add(rotuloDronePerecivelInvalido);

        rotuloDronePerecivel = new JLabel("Drone");
        rotuloDronePerecivel.setBounds(300, 405, 300, 20);
        painelCadastrarPerecivel.add(rotuloDronePerecivel);

        caixaOpcoesDrone = new JComboBox();
        caixaOpcoesDrone.setBounds(300, 425, 300, 20);
        painelCadastrarPerecivel.add(caixaOpcoesDrone);

        botaoCadastrarEntrega = new JButton("Cadastrar");
        botaoCadastrarEntrega.setBounds(300, 455, 250, 25);
        painelCadastrarPerecivel.add(botaoCadastrarEntrega);

        areaTextoCadastrarEntregaDados = new JTextArea();
        areaTextoCadastrarEntregaDados.setEditable(false);
        cadastrarEntregaDados = new JScrollPane(areaTextoCadastrarEntregaDados);
        cadastrarEntregaDados.setBounds(100, 100, 600, 400);
        cadastrarEntregaDados.setVisible(false);
        painelCadastrarPerecivel.add(cadastrarEntregaDados);

        painelPrincipal.add(painelCadastrarPerecivel, "painelCadastrarPerecivel");

        botaoVoltarCadastrarPerecivel.addActionListener(e -> {
            cardLayout.show(painelPrincipal, "painelCadastrarEntregaOpcao");
        });

        botaoSelecionarDronePerecivel.addActionListener(e -> {
            rotuloNumeroPerecivelInvalido.setVisible(false);
            rotuloDronePerecivel.setVisible(false);
            caixaOpcoesDrone.setVisible(false);
            botaoCadastrarEntrega.setVisible(false);
            rotuloValidadePerecivelInvalido.setVisible(false);
            rotuloDronePerecivelInvalido.setVisible(false);
            validade = true;
            try {
                numero = Integer.parseInt(campoTextoNumeroPerecivel.getText());
                if (numero < 0) {
                    rotuloNumeroPerecivelInvalido.setText("Digite um número válido.");
                    rotuloNumeroPerecivelInvalido.setVisible(true);
                    validade = false;
                } else {
                    rotuloNumeroPerecivelInvalido.setVisible(false);
                }
            } catch (Exception exc) {
                rotuloNumeroPerecivelInvalido.setText("Digite um número válido.");
                rotuloNumeroPerecivelInvalido.setVisible(true);
                validade = false;
            }
            descricao = campoTextoDescricaoPerecivel.getText();
            data = LocalDate.now();
            try {
                peso = Double.parseDouble(campoTextoPesoPerecivel.getText());
                if (peso < 0) {
                    rotuloPesoPerecivelInvalido.setVisible(true);
                    validade = false;
                } else {
                    rotuloPesoPerecivelInvalido.setVisible(false);
                }
            } catch (Exception exc) {
                rotuloPesoPerecivelInvalido.setVisible(true);
                validade = false;
            }
            try {
                origem = Integer.parseInt(campoTextoOrigemPerecivel.getText());
                if (acmeDrones.localizacaoExistente(origem) == false) {
                    rotuloOrigemPerecivelInvalido.setText("Localização com código não cadastrada.");
                    rotuloOrigemPerecivelInvalido.setVisible(true);
                    validade = false;
                } else {
                    rotuloOrigemPerecivelInvalido.setVisible(false);
                }
            } catch (Exception exc) {
                rotuloOrigemPerecivelInvalido.setText("Digite um número válido.");
                rotuloOrigemPerecivelInvalido.setVisible(true);
                validade = false;
            }
            try {
                destino = Integer.parseInt(campoTextoDestinoPerecivel.getText());
                if (acmeDrones.localizacaoExistente(destino) == false) {
                    rotuloDestinoPerecivelInvalido.setText("Localização com código não cadastrada.");
                    rotuloDestinoPerecivelInvalido.setVisible(true);
                    validade = false;
                } else {
                    rotuloDestinoPerecivelInvalido.setVisible(false);
                }
            } catch (Exception exc) {
                rotuloDestinoPerecivelInvalido.setText("Digite um número válido.");
                rotuloDestinoPerecivelInvalido.setVisible(true);
                validade = false;
            }
            if (caixaOpcoesCliente.getItemCount() == 0) {
                rotuloClientePerecivelInvalido.setVisible(true);
                validade = false;
            } else {
                cliente = acmeDrones.buscarCliente((String) caixaOpcoesCliente.getSelectedItem());
                rotuloClientePerecivelInvalido.setVisible(false);
            }
            try {
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                dataValidade = LocalDate.parse(campoTextoValidadePerecivel.getText(), formato);
            } catch (Exception exc) {
                rotuloValidadePerecivelInvalido.setVisible(true);
                validade = false;
            }
            if (validade == true) {
                if (acmeDrones.entregaExistente(numero)) {
                    rotuloNumeroPerecivelInvalido.setText("Número já cadastrado.");
                    rotuloNumeroPerecivelInvalido.setVisible(true);
                } else {
                    entregaPerecivel = new EntregaPerecivel(numero, descricao, data, peso, acmeDrones.buscarLocalizacao(origem), acmeDrones.buscarLocalizacao(destino), cliente, dataValidade);
                    if (acmeDrones.dronesCapacitados(entregaPerecivel).isEmpty()) {
                        rotuloDronePerecivelInvalido.setVisible(true);
                    } else {
                        botaoSelecionarDronePerecivel.setVisible(false);
                        rotuloDronePerecivel.setVisible(true);
                        caixaOpcoesDrone.setVisible(true);
                        botaoCadastrarEntrega.setVisible(true);
                        String[] identificadorDrones = new String[acmeDrones.dronesCapacitados(entregaPerecivel).size()];
                        for (int i = 0; i < identificadorDrones.length; i++) {
                            identificadorDrones[i] = Integer.toString(acmeDrones.dronesCapacitados(entregaPerecivel).get(i).getIdentificador());
                        }
                        painelCadastrarPerecivel.remove(caixaOpcoesDrone);
                        caixaOpcoesDrone = new JComboBox<String>(identificadorDrones);
                        caixaOpcoesDrone.setBounds(300, 425, 300, 20);
                        painelCadastrarPerecivel.add(caixaOpcoesDrone);
                    }
                }
            }
        });

        botaoCadastrarEntrega.addActionListener(e -> {
            drone = acmeDrones.buscarDrone(Integer.parseInt((String) caixaOpcoesDrone.getSelectedItem()));
            entregaPerecivel.setDrone(drone);
            acmeDrones.cadastrarEntregaPerecivel(entregaPerecivel);
            drone.adicionarEntrega(entregaPerecivel);
            rotuloNumeroPerecivel.setVisible(false);
            campoTextoNumeroPerecivel.setVisible(false);
            rotuloDescricaoPerecivel.setVisible(false);
            campoTextoDescricaoPerecivel.setVisible(false);
            rotuloPesoPerecivel.setVisible(false);
            campoTextoPesoPerecivel.setVisible(false);
            rotuloOrigemPerecivel.setVisible(false);
            campoTextoOrigemPerecivel.setVisible(false);
            rotuloDestinoPerecivel.setVisible(false);
            campoTextoDestinoPerecivel.setVisible(false);
            rotuloClientePerecivel.setVisible(false);
            caixaOpcoesCliente.setVisible(false);
            rotuloValidadePerecivel.setVisible(false);
            campoTextoValidadePerecivel.setVisible(false);
            rotuloDronePerecivel.setVisible(false);
            caixaOpcoesDrone.setVisible(false);
            botaoCadastrarEntrega.setVisible(false);
            areaTextoCadastrarEntregaDados.setText("Entrega cadastrada com sucesso. Dados:\n\n" + entregaPerecivel);
            cadastrarEntregaDados.setVisible(true);
        });

        // Painel Cadastrar Entrega Não Perecível

        painelCadastrarNaoPerecivel = new JPanel();
        painelCadastrarNaoPerecivel.setLayout(null);

        botaoVoltarCadastrarNaoPerecivel = new JButton("Voltar");
        botaoVoltarCadastrarNaoPerecivel.setBounds(40, 20, 90, 25);
        painelCadastrarNaoPerecivel.add(botaoVoltarCadastrarNaoPerecivel);

        rotuloNumeroNaoPerecivel = new JLabel("Número");
        rotuloNumeroNaoPerecivel.setBounds(300, 58, 300, 20);
        painelCadastrarNaoPerecivel.add(rotuloNumeroNaoPerecivel);

        campoTextoNumeroNaoPerecivel = new JTextField();
        campoTextoNumeroNaoPerecivel.setBounds(300, 78, 193, 28);
        painelCadastrarNaoPerecivel.add(campoTextoNumeroNaoPerecivel);

        rotuloNumeroNaoPerecivelInvalido = new JLabel();
        rotuloNumeroNaoPerecivelInvalido.setBounds(493, 78, 200, 20);
        rotuloNumeroNaoPerecivelInvalido.setForeground(Color.RED);
        rotuloNumeroNaoPerecivelInvalido.setVisible(false);
        painelCadastrarNaoPerecivel.add(rotuloNumeroNaoPerecivelInvalido);

        rotuloDescricaoNaoPerecivel = new JLabel("Descrição");
        rotuloDescricaoNaoPerecivel.setBounds(300, 108, 300, 20);
        painelCadastrarNaoPerecivel.add(rotuloDescricaoNaoPerecivel);

        campoTextoDescricaoNaoPerecivel = new JTextField();
        campoTextoDescricaoNaoPerecivel.setBounds(300, 128, 193, 28);
        painelCadastrarNaoPerecivel.add(campoTextoDescricaoNaoPerecivel);

        rotuloPesoNaoPerecivel = new JLabel("Peso");
        rotuloPesoNaoPerecivel.setBounds(300, 158, 300, 20);
        painelCadastrarNaoPerecivel.add(rotuloPesoNaoPerecivel);

        campoTextoPesoNaoPerecivel = new JTextField();
        campoTextoPesoNaoPerecivel.setBounds(300, 177, 193, 28);
        painelCadastrarNaoPerecivel.add(campoTextoPesoNaoPerecivel);

        rotuloPesoNaoPerecivelInvalido = new JLabel("Digite um peso válido.");
        rotuloPesoNaoPerecivelInvalido.setBounds(493, 177, 200, 20);
        rotuloPesoNaoPerecivelInvalido.setForeground(Color.RED);
        rotuloPesoNaoPerecivelInvalido.setVisible(false);
        painelCadastrarNaoPerecivel.add(rotuloPesoNaoPerecivelInvalido);

        rotuloOrigemNaoPerecivel = new JLabel("Código de sua origem");
        rotuloOrigemNaoPerecivel.setBounds(300, 205, 300, 20);
        painelCadastrarNaoPerecivel.add(rotuloOrigemNaoPerecivel);

        campoTextoOrigemNaoPerecivel = new JTextField();
        campoTextoOrigemNaoPerecivel.setBounds(300, 225, 193, 28);
        painelCadastrarNaoPerecivel.add(campoTextoOrigemNaoPerecivel);

        rotuloOrigemNaoPerecivelInvalido = new JLabel();
        rotuloOrigemNaoPerecivelInvalido.setBounds(493, 225, 300, 20);
        rotuloOrigemNaoPerecivelInvalido.setForeground(Color.RED);
        rotuloOrigemNaoPerecivelInvalido.setVisible(false);
        painelCadastrarNaoPerecivel.add(rotuloOrigemNaoPerecivelInvalido);

        rotuloDestinoNaoPerecivel = new JLabel("Código de seu destino");
        rotuloDestinoNaoPerecivel.setBounds(300, 255, 300, 20);
        painelCadastrarNaoPerecivel.add(rotuloDestinoNaoPerecivel);

        campoTextoDestinoNaoPerecivel = new JTextField();
        campoTextoDestinoNaoPerecivel.setBounds(300, 275, 193, 28);
        painelCadastrarNaoPerecivel.add(campoTextoDestinoNaoPerecivel);

        rotuloDestinoNaoPerecivelInvalido = new JLabel();
        rotuloDestinoNaoPerecivelInvalido.setBounds(493, 275, 300, 20);
        rotuloDestinoNaoPerecivelInvalido.setForeground(Color.RED);
        rotuloDestinoNaoPerecivelInvalido.setVisible(false);
        painelCadastrarNaoPerecivel.add(rotuloDestinoNaoPerecivelInvalido);

        rotuloClienteNaoPerecivel = new JLabel("Cliente");
        rotuloClienteNaoPerecivel.setBounds(300, 305, 300, 20);
        painelCadastrarNaoPerecivel.add(rotuloClienteNaoPerecivel);

        caixaOpcoesClienteNaoPerecivel = new JComboBox();
        caixaOpcoesClienteNaoPerecivel.setBounds(300, 325, 300, 20);
        painelCadastrarNaoPerecivel.add(caixaOpcoesClienteNaoPerecivel);

        rotuloClienteNaoPerecivelInvalido = new JLabel("Selecione um cliente.");
        rotuloClienteNaoPerecivelInvalido.setBounds(600, 325, 193, 28);
        rotuloClienteNaoPerecivelInvalido.setForeground(Color.RED);
        rotuloClienteNaoPerecivelInvalido.setVisible(false);
        painelCadastrarNaoPerecivel.add(rotuloClienteNaoPerecivelInvalido);

        rotuloDescricaoMateriais = new JLabel("Descrição dos materiais");
        rotuloDescricaoMateriais.setBounds(300, 355, 300, 20);
        painelCadastrarNaoPerecivel.add(rotuloDescricaoMateriais);

        campoTextoDescricaoMateriais = new JTextField();
        campoTextoDescricaoMateriais.setBounds(300, 375, 193, 28);
        painelCadastrarNaoPerecivel.add(campoTextoDescricaoMateriais);

        rotuloDescricaoMateriaisInvalido = new JLabel("Digite uma descrição.");
        rotuloDescricaoMateriaisInvalido.setBounds(493, 375, 300, 20);
        rotuloDescricaoMateriaisInvalido.setForeground(Color.RED);
        rotuloDescricaoMateriaisInvalido.setVisible(false);
        painelCadastrarNaoPerecivel.add(rotuloDescricaoMateriaisInvalido);

        botaoSelecionarDroneNaoPerecivel = new JButton("Selecionar drone");
        botaoSelecionarDroneNaoPerecivel.setBounds(300, 405, 250, 25);
        painelCadastrarNaoPerecivel.add(botaoSelecionarDroneNaoPerecivel);

        rotuloDroneNaoPerecivelInvalido = new JLabel("Nenhum drone com capacidade para a entrega.");
        rotuloDroneNaoPerecivelInvalido.setBounds(300, 435, 300, 20);
        rotuloDroneNaoPerecivelInvalido.setForeground(Color.RED);
        rotuloDroneNaoPerecivelInvalido.setVisible(false);
        painelCadastrarNaoPerecivel.add(rotuloDroneNaoPerecivelInvalido);

        rotuloDroneNaoPerecivel = new JLabel("Drone");
        rotuloDroneNaoPerecivel.setBounds(300, 405, 300, 20);
        painelCadastrarNaoPerecivel.add(rotuloDroneNaoPerecivel);

        caixaOpcoesDroneNaoPerecivel = new JComboBox();
        caixaOpcoesDroneNaoPerecivel.setBounds(300, 425, 300, 20);
        painelCadastrarNaoPerecivel.add(caixaOpcoesDroneNaoPerecivel);

        botaoCadastrarEntregaNaoPerecivel = new JButton("Cadastrar");
        botaoCadastrarEntregaNaoPerecivel.setBounds(300, 455, 250, 25);
        painelCadastrarNaoPerecivel.add(botaoCadastrarEntregaNaoPerecivel);

        areaTextoCadastrarEntregaNaoPerecivelDados = new JTextArea();
        areaTextoCadastrarEntregaNaoPerecivelDados.setEditable(false);
        cadastrarEntregaNaoPerecivelDados = new JScrollPane(areaTextoCadastrarEntregaNaoPerecivelDados);
        cadastrarEntregaNaoPerecivelDados.setBounds(100, 100, 600, 400);
        cadastrarEntregaNaoPerecivelDados.setVisible(false);
        painelCadastrarNaoPerecivel.add(cadastrarEntregaNaoPerecivelDados);

        painelPrincipal.add(painelCadastrarNaoPerecivel, "painelCadastrarNaoPerecivel");

        botaoVoltarCadastrarNaoPerecivel.addActionListener(e -> {
            cardLayout.show(painelPrincipal, "painelCadastrarEntregaOpcao");
        });

        botaoSelecionarDroneNaoPerecivel.addActionListener(e -> {
            rotuloNumeroNaoPerecivelInvalido.setVisible(false);
            rotuloDroneNaoPerecivel.setVisible(false);
            caixaOpcoesDroneNaoPerecivel.setVisible(false);
            botaoCadastrarEntregaNaoPerecivel.setVisible(false);
            rotuloDroneNaoPerecivelInvalido.setVisible(false);
            validade = true;
            try {
                numero = Integer.parseInt(campoTextoNumeroNaoPerecivel.getText());
                if (numero < 0) {
                    rotuloNumeroNaoPerecivelInvalido.setText("Digite um número válido.");
                    rotuloNumeroNaoPerecivelInvalido.setVisible(true);
                    validade = false;
                } else {
                    rotuloNumeroNaoPerecivelInvalido.setVisible(false);
                }
            } catch (Exception exc) {
                rotuloNumeroNaoPerecivelInvalido.setText("Digite um número válido.");
                rotuloNumeroNaoPerecivelInvalido.setVisible(true);
                validade = false;
            }
            descricao = campoTextoDescricaoNaoPerecivel.getText();
            data = LocalDate.now();
            try {
                peso = Double.parseDouble(campoTextoPesoNaoPerecivel.getText());
                if (peso < 0) {
                    rotuloPesoNaoPerecivelInvalido.setVisible(true);
                    validade = false;
                } else {
                    rotuloPesoNaoPerecivelInvalido.setVisible(false);
                }
            } catch (Exception exc) {
                rotuloPesoNaoPerecivelInvalido.setVisible(true);
                validade = false;
            }
            try {
                origem = Integer.parseInt(campoTextoOrigemNaoPerecivel.getText());
                if (acmeDrones.localizacaoExistente(origem) == false) {
                    rotuloOrigemNaoPerecivelInvalido.setText("Localização com código não cadastrada.");
                    rotuloOrigemNaoPerecivelInvalido.setVisible(true);
                    validade = false;
                } else {
                    rotuloOrigemNaoPerecivelInvalido.setVisible(false);
                }
            } catch (Exception exc) {
                rotuloOrigemNaoPerecivelInvalido.setText("Digite um número válido.");
                rotuloOrigemNaoPerecivelInvalido.setVisible(true);
                validade = false;
            }
            try {
                destino = Integer.parseInt(campoTextoDestinoNaoPerecivel.getText());
                if (acmeDrones.localizacaoExistente(destino) == false) {
                    rotuloDestinoNaoPerecivelInvalido.setText("Localização com código não cadastrada.");
                    rotuloDestinoNaoPerecivelInvalido.setVisible(true);
                    validade = false;
                } else {
                    rotuloDestinoNaoPerecivelInvalido.setVisible(false);
                }
            } catch (Exception exc) {
                rotuloDestinoNaoPerecivelInvalido.setText("Digite um número válido.");
                rotuloDestinoNaoPerecivelInvalido.setVisible(true);
                validade = false;
            }
            if (caixaOpcoesClienteNaoPerecivel.getItemCount() == 0) {
                rotuloClienteNaoPerecivelInvalido.setVisible(true);
                validade = false;
            } else {
                cliente = acmeDrones.buscarCliente((String) caixaOpcoesClienteNaoPerecivel.getSelectedItem());
                rotuloClienteNaoPerecivelInvalido.setVisible(false);
            }
            descricaoMateriais = campoTextoDescricaoMateriais.getText();
            if (descricaoMateriais.isEmpty()) {
                rotuloDescricaoMateriaisInvalido.setVisible(true);
                validade = false;
            } else {
                rotuloDescricaoMateriaisInvalido.setVisible(false);
            }
            if (validade == true) {
                if (acmeDrones.entregaExistente(numero)) {
                    rotuloNumeroNaoPerecivelInvalido.setText("Número já cadastrado.");
                    rotuloNumeroNaoPerecivelInvalido.setVisible(true);
                } else {
                    entregaNaoPerecivel = new EntregaNaoPerecivel(numero, descricao, data, peso, acmeDrones.buscarLocalizacao(origem), acmeDrones.buscarLocalizacao(destino), cliente, descricaoMateriais);
                    if (acmeDrones.dronesCapacitados(entregaNaoPerecivel).isEmpty()) {
                        rotuloDroneNaoPerecivelInvalido.setVisible(true);
                    } else {
                        botaoSelecionarDroneNaoPerecivel.setVisible(false);
                        rotuloDroneNaoPerecivel.setVisible(true);
                        caixaOpcoesDroneNaoPerecivel.setVisible(true);
                        botaoCadastrarEntregaNaoPerecivel.setVisible(true);
                        String[] identificadorDrones = new String[acmeDrones.dronesCapacitados(entregaNaoPerecivel).size()];
                        for (int i = 0; i < identificadorDrones.length; i++) {
                            identificadorDrones[i] = Integer.toString(acmeDrones.dronesCapacitados(entregaNaoPerecivel).get(i).getIdentificador());
                        }
                        painelCadastrarNaoPerecivel.remove(caixaOpcoesDroneNaoPerecivel);
                        caixaOpcoesDroneNaoPerecivel = new JComboBox<String>(identificadorDrones);
                        caixaOpcoesDroneNaoPerecivel.setBounds(300, 425, 300, 20);
                        painelCadastrarNaoPerecivel.add(caixaOpcoesDroneNaoPerecivel);
                    }
                }
            }
        });

        botaoCadastrarEntregaNaoPerecivel.addActionListener(e -> {
            drone = acmeDrones.buscarDrone(Integer.parseInt((String) caixaOpcoesDroneNaoPerecivel.getSelectedItem()));
            entregaNaoPerecivel.setDrone(drone);
            acmeDrones.cadastrarEntregaNaoPerecivel(entregaNaoPerecivel);
            drone.adicionarEntrega(entregaNaoPerecivel);
            rotuloNumeroNaoPerecivel.setVisible(false);
            campoTextoNumeroNaoPerecivel.setVisible(false);
            rotuloDescricaoNaoPerecivel.setVisible(false);
            campoTextoDescricaoNaoPerecivel.setVisible(false);
            rotuloPesoNaoPerecivel.setVisible(false);
            campoTextoPesoNaoPerecivel.setVisible(false);
            rotuloOrigemNaoPerecivel.setVisible(false);
            campoTextoOrigemNaoPerecivel.setVisible(false);
            rotuloDestinoNaoPerecivel.setVisible(false);
            campoTextoDestinoNaoPerecivel.setVisible(false);
            rotuloClienteNaoPerecivel.setVisible(false);
            caixaOpcoesClienteNaoPerecivel.setVisible(false);
            rotuloDescricaoMateriais.setVisible(false);
            campoTextoDescricaoMateriais.setVisible(false);
            rotuloDroneNaoPerecivel.setVisible(false);
            caixaOpcoesDroneNaoPerecivel.setVisible(false);
            botaoCadastrarEntregaNaoPerecivel.setVisible(false);
            areaTextoCadastrarEntregaNaoPerecivelDados.setText("Entrega cadastrada com sucesso. Dados:\n\n" + entregaNaoPerecivel);
            cadastrarEntregaNaoPerecivelDados.setVisible(true);
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
                ano = Integer.parseInt(campoTextoAno.getText());
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
                mes = Integer.parseInt(campoTextoMes.getText());
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
