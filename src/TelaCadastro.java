import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCadastro extends JFrame {
    private JTextField txtLogin;
    private JTextField txtEmail;
    private JPasswordField txtSenha;
    private JButton btnRegistrar;

    public TelaCadastro() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Tela de Cadastro");
        setSize(400, 200);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));

        JLabel lblLogin = new JLabel("Login:");
        JLabel lblEmail = new JLabel("E-mail:");
        JLabel lblSenha = new JLabel("Senha:");

        txtLogin = new JTextField(20);
        txtEmail = new JTextField(20);
        txtSenha = new JPasswordField(20);

        btnRegistrar = new JButton("Registrar");

        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String login = txtLogin.getText();
                String email = txtEmail.getText();
                String senha = new String(txtSenha.getPassword());

                // Aqui você pode adicionar a lógica de registro, como salvar os dados em um banco de dados

                JOptionPane.showMessageDialog(null, "Usuário registrado com sucesso!", "Cadastro realizado", JOptionPane.INFORMATION_MESSAGE);

                // Após o registro, você pode redirecionar para a tela de login ou qualquer outra ação desejada
                dispose(); // Fechar a tela de cadastro
            }
        });

        panel.add(lblLogin);
        panel.add(txtLogin);
        panel.add(lblEmail);
        panel.add(txtEmail);
        panel.add(lblSenha);
        panel.add(txtSenha);
        panel.add(new JLabel()); // Espaçamento vazio para alinhar corretamente o botão de registro
        panel.add(btnRegistrar);

        getContentPane().add(panel, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TelaCadastro();
            }
        });
    }
}
