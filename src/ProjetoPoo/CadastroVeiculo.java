package ProjetoPoo;

import ProjetoPoo.CadastrarProprietario;
import ProjetoPoo.TelaBoasVindas;
import bd.BDProprietario;
import bd.BDVeiculo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.text.MaskFormatter;

public class CadastroVeiculo extends JFrame {

    public JLabel lblplaca, lblNome, lblmodelo, lblTipo, lblano;
    public JTextField txtNome, txtplaca, txtmodelo;
    public JFormattedTextField ftxtano;
    public JComboBox<String> cmbTipo;
    public JComboBox<String> cmbProprietario;
    public JButton btnEnviar, btnVolt2, btnVoltar;

    private final String[] tiposVeiculos = { "Carro", "Moto" };
    private String modelo;

    public CadastroVeiculo() throws ParseException, SQLException {
        setLayout(null);

        lblNome = new JLabel("Nome:");
        txtNome = new JTextField();
        lblplaca = new JLabel("Placa:");
        txtplaca = new JTextField();
        lblano = new JLabel("Ano do veiculo:");
        ftxtano = new JFormattedTextField(new MaskFormatter("####"));

        lblmodelo = new JLabel("Modelo:");
        txtmodelo = new JTextField();
        lblTipo = new JLabel("Tipo do veiculo:");
        cmbTipo = new JComboBox<>(tiposVeiculos);
        cmbProprietario = new JComboBox<>(new BDProprietario().getProprietarios());
        btnEnviar = new JButton("Enviar");
        btnVolt2 = new JButton("Voltar");
        btnVoltar = new JButton("Tela Inicial");

        btnVolt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                try {
                    new CadastrarProprietario();
                } catch (ParseException ex) {
                    Logger.getLogger(CadastroVeiculo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        btnEnviar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    cliqueBtnEnviar();
                } catch (ParseException ex) {
                    Logger.getLogger(CadastroVeiculo.class.getName()).log(Level.SEVERE, null, ex);
                    
                }
            }
        });

        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaBoasVindas telaBoasVindas = new TelaBoasVindas();
                telaBoasVindas.setVisible(true);
                dispose();
            }
        });

        lblNome.setBounds(10, 10, 200, 25);
        lblplaca.setBounds(10, 220, 200, 25);
        txtplaca.setBounds(80, 220, 200, 25);
        txtNome.setBounds(120, 10, 200, 25);
        lblmodelo.setBounds(10, 60, 200, 25);
        txtmodelo.setBounds(120, 60, 200, 25);
        ftxtano.setBounds(100, 50, 200, 25);
        lblTipo.setBounds(10, 110, 200, 25);
        cmbTipo.setBounds(120, 110, 200, 25);
        cmbProprietario.setBounds(170, 260, 200, 25);
        btnEnviar.setBounds(310, 320, 100, 70);
        btnVolt2.setBounds(160, 320, 100, 70);
        ftxtano.setBounds(129, 170, 200, 25);
        lblano.setBounds(10, 170, 200, 25);
        btnVoltar.setBounds(235, 400, 100, 40);

        getContentPane().add(lblNome);
        getContentPane().add(lblplaca);
        getContentPane().add(txtplaca);
        getContentPane().add(ftxtano);
        getContentPane().add(txtNome);
        getContentPane().add(lblmodelo);
        getContentPane().add(txtmodelo);
        getContentPane().add(ftxtano);
        getContentPane().add(lblTipo);
        getContentPane().add(cmbTipo);
        getContentPane().add(cmbProprietario);
        getContentPane().add(btnEnviar);
        getContentPane().add(btnVolt2);
        getContentPane().add(lblano);
        getContentPane().add(btnVoltar);

        setSize(600, 600);
        setTitle("Veículos");
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void cliqueBtnEnviar() throws ParseException {
        String nome = txtNome.getText(),
                ano = ftxtano.getText(),
                placa = txtplaca.getText(),
                tipo = cmbTipo.getSelectedItem().toString(),
                proprietario = cmbProprietario.getSelectedItem().toString();
        modelo = txtmodelo.getText();

        int id_proprietario = Integer.parseInt(proprietario.split("[.]")[0]);

        System.out.println("Nome: " + nome);
        System.out.println("Ano: " + ano);
        System.out.println("Tipo: " + tipo);
        System.out.println("Placa: " + placa);
        System.out.println("Modelo: " + modelo);

        try (PrintWriter pw = new PrintWriter(new File("usuarios"))) {
            pw.println("Nome: " + nome);
            pw.println("Ano: " + ano);
            pw.println("Placa: " + placa);
            pw.println("Tipo: " + tipo);

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não existe");
        }

        try {
            BDVeiculo bdVeiculo = new BDVeiculo();
            bdVeiculo.insereVeiculo(modelo, nome, placa, Integer.parseInt(ano), tipo, id_proprietario);
        } catch (Exception e) {
            System.out.println(e);
        }
        
        
       this.dispose();
        new TelaBoasVindas();
    }
}
