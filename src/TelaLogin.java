import ProjetoPoo.TelaInicial;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TelaLogin extends JFrame {
    
    private JTextField txtLogin;
    private JPasswordField txtSenha;
    private JButton btnLogin;
    private JButton btnRegistrar;

    public TelaLogin() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Tela de Login");
        setSize(400, 200);
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
                String login = txtLogin.getText();
                String senha = new String(txtSenha.getPassword());

                // Verificar a autenticação do usuário
                if (autenticarUsuario(login, senha)) {
                    TelaInicial telaInicial = null;
                    try {
                        telaInicial = new TelaInicial();
                    } catch (ParseException ex) {
                        Logger.getLogger(TelaLogin.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    telaInicial.setVisible(true);
                    dispose(); // Fechar a tela de login
                } else {
                    JOptionPane.showMessageDialog(null, "Credenciais inválidas. Por favor, tente novamente.", "Erro de autenticação", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirTelaCadastro();
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

    private boolean autenticarUsuario(String login, String senha) {
    String loginCorreto = "admin";
    String senhaCorreta = "admin";

    return login.equals(loginCorreto) && senha.equals(senhaCorreta);
}

    private void abrirTelaCadastro() {
        TelaCadastro telaCadastro = new TelaCadastro();
        telaCadastro.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TelaLogin();
            }
        });
    }
}
