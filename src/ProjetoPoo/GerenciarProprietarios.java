package ProjetoPoo;

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
                    String modelo = table.getValueAt(selectedRow, 2).toString();
                    String placa = table.getValueAt(selectedRow, 3).toString();
                    String ano = table.getValueAt(selectedRow, 4).toString();

                    // Exibir um JOptionPane com os dados selecionados para modificar
                    StringBuilder message = new StringBuilder();
                    message.append("ID: ").append(id).append("\n");
                    message.append("Nome: ").append(nome).append("\n");
                    message.append("Telefone: ").append(modelo).append("\n");
                    message.append("Nascimento: ").append(placa).append("\n");
                    message.append("Email: ").append(ano).append("\n");
                    message.append("cep: ").append(ano).append("\n");

                    JOptionPane.showMessageDialog(null, message.toString());
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

                 
                    int option = JOptionPane.showConfirmDialog(null, "Deseja deletar o registro com ID " + id + "?");
                    if (option == JOptionPane.YES_OPTION) {
                        
                        JOptionPane.showMessageDialog(null, "Registro deletado com sucesso!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Nenhum registro selecionado");
                }
            }
        });
    }

    private void updateTableData() {
        // Obter os dados do banco de dados
        Object[][] data = Autentic3.Listar();

        // Limpar os dados antigos da tabela
        tableModel.setRowCount(0);

        // Adicionar os novos dados à tabela
        for (Object[] row : data) {
            tableModel.addRow(row);
        }
    }
}
