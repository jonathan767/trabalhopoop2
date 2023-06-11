import bd.BDAutenticacao;
import bd.BDProprietario;
import bd.BDVeiculo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TelaLogin telaLogin = new TelaLogin();
                telaLogin.setVisible(true);
            }
        });

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                String diretorioAtual = new File("").getAbsolutePath();
                File insere_veiculo = new File(diretorioAtual + "/insere_veiculo.txt");
                File insere_usuario = new File(diretorioAtual + "/insere_users.txt");
                File insere_informacoes_proprietario = new File(diretorioAtual + "/insere_informacoes_proprietario.txt");
                
                if (insere_veiculo.length() != 0) {
                    String linha;
                    try ( BufferedReader leitor = new BufferedReader(new FileReader(insere_veiculo))) {
                        while ((linha = leitor.readLine()) != null) {
                            String[] split = linha.split(",");
                            BDVeiculo.insereVeiculo(split[0], split[1], split[2], Integer.parseInt(split[3]), split[4], Integer.parseInt(split[5]));
                        }                        
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (SQLException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    FileWriter escritor;
                    try {
                        escritor = new FileWriter(insere_veiculo);
                        escritor.flush();
                    } catch (IOException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return;
                }
                
                
      
                 if (insere_informacoes_proprietario.length() != 0) {
                    String linha;
                    try ( BufferedReader leitor = new BufferedReader(new FileReader(insere_informacoes_proprietario))) {
                        while ((linha = leitor.readLine()) != null) {
                            String[] split = linha.split(",");
                            BDProprietario.inserirInformacoesProprietario(split[0], split[1], split[2], split[3],split[4]);
                        }                        
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (SQLException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    FileWriter escritor;
                    try {
                        escritor = new FileWriter(insere_informacoes_proprietario);
                        escritor.flush();
                    } catch (IOException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return;
                }
                
                
                
                
                if(insere_usuario.length() != 0){
                       String linha;
                    try ( BufferedReader leitor = new BufferedReader(new FileReader(insere_usuario))) {
                        while ((linha = leitor.readLine()) != null) {
                            String[] split = linha.split(",");
                            BDAutenticacao.cadastro(split[0], split[1]);
                        }                        
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    
                    FileWriter escritor;
                    try {
                        escritor = new FileWriter(insere_usuario);
                        escritor.flush();
                    } catch (IOException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return;
                }
                
            }
        };

        Timer timer = new Timer();

        timer.scheduleAtFixedRate(timerTask,
                0, 5 * 1000);
    }

}
