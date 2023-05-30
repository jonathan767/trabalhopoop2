import ProjetoPoo.Autenticacao;
import bd.BDConnection;
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
    private JTextField txtLogin;
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

        JLabel lblLogin = new JLabel("Login:");
        lblLogin.setBounds(50, 50, 60, 25);
        container.add(lblLogin);

        txtLogin = new JTextField();
        txtLogin.setBounds(120, 50, 200, 25);
        container.add(txtLogin);

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

                boolean registrou = Autenticacao.cadastro(email, senha);
                if(!registrou){
                    JOptionPane.showMessageDialog(rootPane, "Já existe uma conta com esse email!", "Email sendo utilizado!", HEIGHT);
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

    private void abrirTelaLogin() {
        TelaLogin telaLogin = new TelaLogin();
        telaLogin.setVisible(true);
        dispose();
    }

 
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TelaCadastro();
            }
        });
    }
}
