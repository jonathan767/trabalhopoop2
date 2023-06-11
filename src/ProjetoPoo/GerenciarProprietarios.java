package ProjetoPoo;

import bd.BDProprietario;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

public class GerenciarProprietarios extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private Timer timer;
    private JButton btnVoltar;
    private JButton btnModificar;
    private JButton btnDeletar;

    public GerenciarProprietarios() {
        initComponents();
        configureLayout();
        configureListeners();
    }

    private void initComponents() {
        String[] columnNames = {"id", "nome", "telefone", "nascimento", "email", "cep"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        btnVoltar = new JButton("Voltar");
        btnModificar = new JButton("Modificar");
        btnDeletar = new JButton("Deletar");
    }

    private void configureLayout() {
        setLayout(new BorderLayout());
        setSize(500, 400);
        setTitle("Gerenciar Cadastros");
        setResizable(false);
        setLocationRelativeTo(null);

        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(btnModificar);
        buttonPanel.add(btnDeletar);
        buttonPanel.add(btnVoltar);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void configureListeners() {
    timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            updateTableData();
            timer.setDelay(9000); // Define o delay para 9000 milissegundos após a primeira execução
            timer.setInitialDelay(9000); // Define o initial delay para 9000 milissegundos após a primeira execução
            timer.restart(); // Reinicia o Timer com o novo delay
        }
    });
timer.start();

        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaBoasVindas telaBoasVindas = new TelaBoasVindas();
                telaBoasVindas.setVisible(true);
                dispose();
            }
        });

       btnModificar.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            // Obter os dados da linha selecionada
            String id = table.getValueAt(selectedRow, 0).toString();
            String nome = table.getValueAt(selectedRow, 1).toString();
            String telefone = table.getValueAt(selectedRow, 2).toString();
            String nascimento = table.getValueAt(selectedRow, 3).toString();
            String email = table.getValueAt(selectedRow, 4).toString();

            // Criar um formulário de edição para atualizar os dados
            JTextField txtNome = new JTextField(nome);
            JTextField txtTelefone = new JTextField(telefone);
            JTextField txtNascimento = new JTextField(nascimento);
            JTextField txtEmail = new JTextField(email);

            Object[] fields = {
                "Nome:", txtNome,
                "Telefone:", txtTelefone,
                "Nascimento:", txtNascimento,
                "Email:", txtEmail
            };

            int option = JOptionPane.showConfirmDialog(null, fields, "Modificar Proprietário", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                // Obter os novos valores do formulário
                String novoNome = txtNome.getText();
                String novoTelefone = txtTelefone.getText();
                String novoNascimento = txtNascimento.getText();
                String novoEmail = txtEmail.getText();

                // Atualizar os dados no banco de dados
                BDProprietario bdProprietario = new BDProprietario();
                boolean updated = bdProprietario.Modificar(id, novoNome, novoTelefone, novoNascimento, novoEmail);
                if (updated) {
                    JOptionPane.showMessageDialog(null, "Registro atualizado com sucesso!");
                    updateTableData(); // Atualizar a tabela após a modificação
                } else {
                    JOptionPane.showMessageDialog(null, "Falha ao atualizar o registro.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum registro selecionado");
        }
    }
});


      
        
       btnDeletar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    // Obter o ID do registro selecionado
                    String id = table.getValueAt(selectedRow, 0).toString();

                    // Exibir um JOptionPane de confirmação para deletar o registro
                    int option = JOptionPane.showConfirmDialog(null, "Deseja deletar o registro com ID " + id + "?");
                    if (option == JOptionPane.YES_OPTION) {
                        // Deletar o registro
                        boolean deleted = BDProprietario.Deletar(id);
                        if (deleted) {
                            JOptionPane.showMessageDialog(null, "Registro deletado com sucesso!");
                            updateTableData(); // Atualizar a tabela após a exclusão
                        } else {
                            JOptionPane.showMessageDialog(null, "Falha ao deletar o registro.");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Nenhum registro selecionado");
                }
            }
        });
    }

    private void updateTableData() {
        // Obter os dados do banco de dados
        Object[][] data = BDProprietario.Listar();

        // Limpar os dados antigos da tabela
        tableModel.setRowCount(0);

        // Adicionar os novos dados à tabela
        for (Object[] row : data) {
            tableModel.addRow(row);
        }
    }
}
