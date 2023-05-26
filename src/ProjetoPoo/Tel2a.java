package ProjetoPoo ;
import ProjetoPoo.TelaInicial;
import bd.BDConnection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class Tel2a extends JFrame{
   
    public JLabel lblNome, lblidade, lblTipo, lbltelefone, lblemail, lblcep;
    public JTextField txtNome, txtemail;
    public JFormattedTextField ftxtidade, ftxtelefone, ftxcep;    
    public JButton btnEnviar,btnVoltar, btnTrocar;
    
    
    
    public Tel2a() throws ParseException{
        
      
        setLayout(null);
        
        
        lblNome = new JLabel("nome proprietario:");
        txtNome = new JTextField();
        
        lbltelefone = new JLabel("telefone");
        ftxtelefone = new JFormattedTextField(
                new MaskFormatter("##-#####-####"));
        
        lblcep = new JLabel("cep");
        ftxcep = new JFormattedTextField(
                new MaskFormatter("#####-###"));
        
        lblemail = new JLabel("email:");
        txtemail = new JTextField();
        
        lblidade = new JLabel("data de nascimento :");
        ftxtidade = new JFormattedTextField(
                new MaskFormatter("##/##/####"));
        lblTipo = new JLabel("Tipos de pessoas:");
        btnEnviar = new JButton("Enviar");
        btnVoltar = new JButton("sair");
        
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                
                }
            
        });
                btnEnviar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	try{
                    cliqueBtnEnviar();
                } catch (ParseException ex) {
                    Logger.getLogger(TelaInicial.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        
        
       
        
        lblNome.setBounds(10, 10, 200, 25);
        txtNome.setBounds(120, 10, 200, 25);
        lbltelefone.setBounds(10, 60, 200, 25);
        ftxtelefone.setBounds(110, 60, 200, 25);
        lblemail.setBounds(10, 110, 200, 25);
        txtemail.setBounds(110, 110, 200, 25);
        lblidade.setBounds(10, 170, 200, 25);
        ftxtidade.setBounds(129,170,200,25);
        lblcep.setBounds(10,210,200,25);
        ftxcep.setBounds(129,210,200,25);
        btnEnviar.setBounds(100, 300, 100, 70);
        btnVoltar.setBounds(250, 300, 100, 70);

        getContentPane().add(lblNome);
        getContentPane().add(txtNome);
        getContentPane().add(lbltelefone);
        getContentPane().add(ftxtelefone);
        getContentPane().add(ftxtidade);
        getContentPane().add(lblemail);
        getContentPane().add(txtemail);
        getContentPane().add(lblcep);
        getContentPane().add(ftxcep);
        getContentPane().add(lblidade);
        getContentPane().add(lblTipo);
        getContentPane().add(btnEnviar);
        getContentPane().add(btnVoltar);
        
        //Especificações da Tela
        setSize(500, 500);
        setTitle("informacoes proprietario");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void cliqueBtnEnviar() throws ParseException{
        String nome = txtNome.getText(),
               nascimento = ftxtidade.getText(),
             email  = txtemail.getText(),
                cep = ftxcep.getText(),
             telefone = ftxtelefone.getText();
             
               
        
        System.out.println("nome : " + nome);
        //System.out.println("data de nascimento: " + idade);
        System.out.println("email: " + email);
        System.out.println("cep: " + cep );
        System.out.println("telefone: " + telefone  );
                
        try {
            BDConnection.insereProprietario(nome, telefone, nascimento, email, cep);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Tel2a.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Tel2a.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.dispose();
        new TelaInicial();
     
    }
}

