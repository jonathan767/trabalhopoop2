import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaLogin extends JFrame {
    private JTextField txtLogin;
    private JPasswordField txtSenha;
    private JButton btnLogin, btnRegistrar;

    public TelaLogin() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Tela de Login");
        setSize(300, 200);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));

        JLabel lblLogin = new JLabel("Login:");
        JLabel lblSenha = new JLabel("Senha:");

        txtLogin = new JTextField(20);
        txtSenha = new JPasswordField(20);

        btnLogin = new JButton("Entrar");
        btnRegistrar = new JButton("Registrar");

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registrar();
            }
        });

        panel.add(lblLogin);
        panel.add(txtLogin);
        panel.add(lblSenha);
        panel.add(txtSenha);
        panel.add(btnLogin);
        panel.add(btnRegistrar);

        getContentPane().add(panel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void login() {
        String login = txtLogin.getText();
        String senha = new String(txtSenha.getPassword());

        // Realizar ações de login aqui

        JOptionPane.showMessageDialog(this, "Login realizado com sucesso!");
    }

    private void registrar() {
        new Telaregistro();
       dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TelaLogin();
            }
        });
    }

    private static class Telaregistro {

        public Telaregistro() {
        }
    }
}


