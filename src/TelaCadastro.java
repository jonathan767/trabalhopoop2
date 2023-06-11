import bd.BDAutenticacao;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TelaCadastro extends JFrame {
    private JTextField txtEmail;
    private JPasswordField txtSenha;
    private JButton btnRegistrar, btnVoltar;

    public TelaCadastro() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Tela de Cadastro");
        setSize(400, 400);
        setResizable(false);
        setLocationRelativeTo(null);

        Container container = getContentPane();
        container.setLayout(null);

        JLabel lblEmail = new JLabel("E-mail:");
        lblEmail.setBounds(50, 100, 60, 25);
        container.add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(120, 100, 200, 25);
        container.add(txtEmail);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(50, 150, 60, 25);
        container.add(lblSenha);

        txtSenha = new JPasswordField();
        txtSenha.setBounds(120, 150, 200, 25);
        container.add(txtSenha);

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(120, 200, 100, 30);
        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = txtEmail.getText();
                String senha = new String(txtSenha.getPassword());

                if (validarEmail(email)) {
                    boolean registrou = BDAutenticacao.cadastro(email, senha);
                    if (registrou) {
                        JOptionPane.showMessageDialog(rootPane, "Registrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                        abrirTelaLogin();
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Já existe uma conta com esse email!", "Email sendo utilizado!", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Email inválido!", "Erro de validação", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        container.add(btnRegistrar);

        btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(220, 200, 100, 30);
        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirTelaLogin();
            }
        });
        container.add(btnVoltar);

        setVisible(true);
    }

    private boolean validarEmail(String email) {
        if (email.contains("@") && email.endsWith(".com")) {
            return true;
        }
        return false;
    }

    private void abrirTelaLogin() {
        TelaLogin telaLogin = new TelaLogin();
        telaLogin.setVisible(true);
        dispose();
    }
}

