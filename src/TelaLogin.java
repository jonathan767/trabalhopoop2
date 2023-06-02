import ProjetoPoo.TelaBoasVindas;
import ProjetoPoo.Autenticacao;
import ProjetoPoo.TelaInicial;
import ProjetoPoo.GerenciarVeiculos;

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
        setSize(500, 250);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        JLabel lblLogin = new JLabel("Login:");
        JLabel lblSenha = new JLabel("Senha:");

        txtLogin = new JTextField(15);
        txtSenha = new JPasswordField(15);
        txtSenha.setEchoChar('*');

        btnLogin = new JButton("Entrar");
        btnRegistrar = new JButton("Registrar");
        

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String login = txtLogin.getText();
                String senha = new String(txtSenha.getPassword());

                // Verificar a autenticação do usuário
                if (autenticarUsuario(login, senha)) {
                    TelaBoasVindas telaBoasVindas = null;
                    telaBoasVindas = new TelaBoasVindas();
                    telaBoasVindas.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Credenciais inválidas. Por favor, tente novamente.", "Erro de autenticação", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirTelaCadastro();
                dispose();
            }
        });

        
        // Deixar bonito
        lblLogin.setFont(new Font("Arial", Font.BOLD, 16));
        lblSenha.setFont(new Font("Arial", Font.BOLD, 16));

        btnLogin.setPreferredSize(new Dimension(120, 30));
        btnLogin.setFont(new Font("Arial", Font.BOLD, 14));
        btnLogin.setBackground(Color.LIGHT_GRAY);

        btnRegistrar.setPreferredSize(new Dimension(120, 30));
        btnRegistrar.setFont(new Font("Arial", Font.BOLD, 14));
        btnRegistrar.setBackground(Color.LIGHT_GRAY);
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(lblLogin, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(txtLogin, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(lblSenha, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(txtSenha, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(btnLogin, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        panel.add(btnRegistrar, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        

        getContentPane().add(panel, BorderLayout.CENTER);

        setVisible(true);
    }

    private boolean autenticarUsuario(String login, String senha) {
        return Autenticacao.login(login, senha);
    }

    private void abrirTelaCadastro() {
        TelaCadastro telaCadastro = new TelaCadastro();
        telaCadastro.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TelaBoasVindas();
            }
        });
    }
}
