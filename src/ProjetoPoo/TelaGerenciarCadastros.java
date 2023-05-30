package ProjetoPoo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class TelaGerenciarCadastros extends JFrame {
    private JLabel lblNome;
    private JTextField txtNome;
    private JButton btnModificar;
    private JButton btnDeletar;
    private JButton btnListar;
    private JButton btnVoltar;

    public TelaGerenciarCadastros() {
        initComponents();
        configureLayout();
        configureListeners();
    }

    private void initComponents() {
        lblNome = new JLabel("Nome:");
        txtNome = new JTextField();
        btnModificar = new JButton("Modificar");
        btnDeletar = new JButton("Deletar");
        btnListar = new JButton("Listar");
        btnVoltar = new JButton("Voltar");
    }

    private void configureLayout() {
        setLayout(null);
        setSize(300, 250);
        setTitle("Gerenciar Cadastros");

        lblNome.setBounds(10, 10, 100, 25);
        txtNome.setBounds(120, 10, 150, 25);
        btnModificar.setBounds(80, 50, 120, 30);
        btnDeletar.setBounds(80, 90, 120, 30);
        btnListar.setBounds(80, 130, 120, 30);
        btnVoltar.setBounds(80, 170, 120, 30);

        add(lblNome);
        add(txtNome);
        add(btnModificar);
        add(btnDeletar);
        add(btnListar);
        add(btnVoltar);
    }

    private void configureListeners() {
        btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = txtNome.getText();
                

                JOptionPane.showMessageDialog(null, "Cadastro modificado com sucesso!");
               
                txtNome.setText("");
            }
        });

        btnDeletar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = txtNome.getText();
                

                JOptionPane.showMessageDialog(null, "Cadastro deletado com sucesso!");
                
                txtNome.setText("");
            }
        });

        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obter a lista de todos os cadastros do banco de dados falta db

                // Exibir os cadastros falta dbconection
                String[] cadastros = { /* Cadastros obtidos do banco de dados */ };
                StringBuilder sb = new StringBuilder();
                for (String cadastro : cadastros) {
                    sb.append(cadastro).append("\n");
                }
                JOptionPane.showMessageDialog(null, sb.toString());
            }
        });

        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
