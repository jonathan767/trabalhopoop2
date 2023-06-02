package ProjetoPoo;
import bd.BDConnection;
import java.awt.BorderLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.text.MaskFormatter;


public class TelaInicial extends JFrame{
    
    
    public JLabel lblplaca, lblNome, lblmodelo, lblTipo, lblano ;
    public JTextField txtNome, txtplaca, txtmodelo ;
    public JFormattedTextField ftxtano;    
    public JComboBox cmbTipo;
    public JComboBox cmbProprietario;
    public JButton btnEnviar, btnAvancar, btnVoltar;
   
    
    
    private final String[] tiposVeiculos = {"Carro", "Moto"};
    private String modelo;
    
    public TelaInicial() throws ParseException{
        setLayout(null);
        
        //4°passo
        
        lblNome = new JLabel("Nome:");
        txtNome = new JTextField();
        lblplaca = new JLabel("placa:");
         txtplaca = new JTextField();
        lblano = new JLabel("ano do veiculo:");
        ftxtano = new JFormattedTextField(
                new MaskFormatter("####"));
        
       
        
        lblmodelo = new JLabel("modelo:");
        txtmodelo = new JTextField ();
        lblTipo = new JLabel("Tipo do veiculo:");
        cmbTipo = new JComboBox(tiposVeiculos);
        try {
            cmbProprietario = new JComboBox(BDConnection.getProprietarios());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TelaInicial.class.getName()).log(Level.SEVERE, null, ex);
        }
        btnEnviar = new JButton("Enviar");
        btnAvancar = new JButton("avançar");
        btnVoltar = new JButton ("voltar");
        
        
        btnAvancar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                try {
                    new Tel2a();
                } catch (ParseException ex) {
                    Logger.getLogger(TelaInicial.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        btnEnviar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    cliqueBtnEnviar();
                } catch (ParseException ex) {
                    Logger.getLogger(TelaInicial.class.getName()).log(Level.SEVERE, null, ex);
                     {
                   }
                }
            }
        });
        
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaBoasVindas telaBoasVindas = new TelaBoasVindas();
        telaBoasVindas.setVisible(true);
        dispose();
            }
        });
        
        
        lblNome.setBounds(10, 10, 200, 25);
        lblplaca.setBounds(10, 220, 200, 25);
        txtplaca.setBounds(80, 220, 200, 25);
        txtNome.setBounds(120, 10, 200, 25);
        lblmodelo.setBounds(10, 60, 200, 25);
        txtmodelo.setBounds(120, 60, 200, 25);
        ftxtano.setBounds(100, 50, 200, 25);
        lblTipo.setBounds(10, 110, 200, 25);
        cmbTipo.setBounds(120, 110, 200, 25);
        cmbProprietario.setBounds(170, 260, 200, 25);
        btnEnviar.setBounds(310, 320, 100, 70);
        btnAvancar.setBounds(160, 320, 100, 70);
       ftxtano.setBounds(129, 170, 200, 25);
       lblano.setBounds(10,170,200,25);
       btnVoltar.setBounds(235, 400, 100, 40);

        getContentPane().add(lblNome);
        getContentPane().add(lblplaca);
        getContentPane().add(txtplaca);
        getContentPane().add(ftxtano);
        getContentPane().add(txtNome);
        getContentPane().add(lblmodelo);
        getContentPane().add(txtmodelo);
        getContentPane().add(ftxtano);
        getContentPane().add(lblTipo);
        getContentPane().add(cmbTipo);
        getContentPane().add(cmbProprietario);
        getContentPane().add(btnEnviar);
        getContentPane().add(btnAvancar);
        getContentPane().add(lblano);
       getContentPane().add(btnVoltar);
      
        
        
        //Especificações da Tela
        setSize(600, 600);
        setTitle("veiculos");
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void cliqueBtnEnviar() throws ParseException{
        String nome = txtNome.getText(),
               ano = ftxtano.getText(),
              placa = txtplaca.getText(),
               tipo = cmbTipo.getSelectedItem().toString(),
            proprietario = cmbProprietario.getSelectedItem().toString();
              modelo = txtmodelo.getText();
              
        
        int id_proprietario = Integer.parseInt(proprietario.split("[.]")[0]);
        
        
        System.out.println("nome : " + nome);
        System.out.println("ano  : " + ano);
        System.out.println("tipo : " + tipo);
        System.out.println("placa : " + placa ); 
        System.out.println("modelo : " + modelo );
        
        
        try(PrintWriter pw = new PrintWriter(new File("usuarios"))){
            pw.println("nome : " + nome);
            pw.println("ano : " + ano);
            pw.println ("placa" + placa);
            pw.println("tipo : " + tipo);
           
            
        }catch(FileNotFoundException e){
            System.out.println("Arquivo não existe");
        }
        try {
             BDConnection.insereVeiculo(modelo, nome, placa, Integer.parseInt(ano), tipo, id_proprietario);
        } catch (Exception e) {
            System.out.println(e);
            
             
        }
        this.dispose();
        new Tel2a();
        
    }
}