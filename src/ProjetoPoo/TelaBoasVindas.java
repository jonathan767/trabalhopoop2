package ProjetoPoo;

import ProjetoPoo.GerenciarVeiculos;
import ProjetoPoo.TelaInicial;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TelaBoasVindas extends JFrame {
    private JButton btnCadastrarVeiculo;
    private JButton btnGerenciarProprietarios;
    private JButton btnGerenciaveicoloss;


    public TelaBoasVindas() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Boas-Vindas");
        setSize(400, 400);
        setResizable(false);
        setLocationRelativeTo(null);

        Container container = getContentPane();
        container.setLayout(null);

        JLabel lblBoasVindas = new JLabel("Seja bem-vindo!  Oque deseja ?");
        lblBoasVindas.setBounds(90, 30, 200, 25);
        lblBoasVindas.setHorizontalAlignment(SwingConstants.CENTER);
        container.add(lblBoasVindas);

        btnCadastrarVeiculo = new JButton("Cadastrar");
        btnCadastrarVeiculo.setBounds(120, 80, 160, 30);
        btnCadastrarVeiculo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    abrirTelaCadastroVeiculo();
                } catch (ParseException ex) {
                    Logger.getLogger(TelaBoasVindas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        container.add(btnCadastrarVeiculo);

        btnGerenciaveicoloss = new JButton("Gerenciar Veiculos");
btnGerenciaveicoloss.setBounds(80, 160, 250, 30);
btnGerenciaveicoloss.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        abrirTelaGerenciarCadastros();
    }
});
container.add(btnGerenciaveicoloss);

        
        
        
        btnGerenciarProprietarios = new JButton("Gerenciar Proprietarios ");
        btnGerenciarProprietarios.setBounds(80, 120, 250, 30);
        btnGerenciarProprietarios.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirTelaGerenciarInformacoes();
            }
        });
        container.add(btnGerenciarProprietarios);

        setVisible(true);
    }

    private void abrirTelaCadastroVeiculo() throws ParseException {
        TelaInicial TelaInicial = new TelaInicial();
        TelaInicial.setVisible(true);
        dispose();
    }

    private void abrirTelaGerenciarInformacoes() {
     
    
        GerenciarProprietarios telaGerenciarCadastros = new GerenciarProprietarios();
    telaGerenciarCadastros.setVisible(true);
    dispose();
}
    
private void abrirTelaGerenciarCadastros() {
    GerenciarVeiculos telaGerenciarCadastros = new GerenciarVeiculos();
        telaGerenciarCadastros.setVisible(true);
        dispose();
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TelaBoasVindas();
            }
        });
    }
}
