package bd;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BDProprietario {


    public static void inserirInformacoesProprietario(String nome, String telefone, String nascimento, String email, String cep) throws SQLException {
String query = "INSERT INTO informacoes_proprietario (nome, telefone, nascimento, email, cep) VALUES (?, ?, ?, ?, ?)";


      try (PreparedStatement preparedStatement = BDConnection.getInstance().getConnection().prepareStatement(query)){

      
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, telefone);
            preparedStatement.setString(3, nascimento);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, cep);
      
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        
            }
        
    }

    public static Object[][] Listar() {
        String query = "SELECT idinformacoes_proprietario, nome, telefone, nascimento, email, cep FROM informacoes_proprietario";

        try (PreparedStatement preparedStatement = BDConnection.getInstance().getConnection().prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            List<Object[]> proprietarioList = new ArrayList<>();
            while (resultSet.next()) {
                Object[] row = new Object[6];
                row[0] = resultSet.getObject("idinformacoes_proprietario");
                row[1] = resultSet.getObject("nome");
                row[2] = resultSet.getObject("telefone");
                row[3] = resultSet.getObject("nascimento");
                row[4] = resultSet.getObject("email");
                row[5] = resultSet.getObject("cep");
                proprietarioList.add(row);
            }

            return proprietarioList.toArray(new Object[0][]);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }
  public static boolean Deletar(String id) {
        String query = "DELETE FROM informacoes_proprietario WHERE idinformacoes_proprietario = ?";

        try (PreparedStatement preparedStatement = BDConnection.getInstance().getConnection().prepareStatement(query)) {

            preparedStatement.setString(1, id);

            int rowsDeleted = preparedStatement.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }


   
    public String[] getProprietarios() {
        String query = "SELECT * FROM informacoes_proprietario";
        List<String> proprietarios = new ArrayList<>();

        try (PreparedStatement preparedStatement = BDConnection.getInstance().getConnection().prepareStatement(query);
             ResultSet result = preparedStatement.executeQuery()) {

            while (result.next()) {
                int id = result.getInt("idinformacoes_proprietario");
                String nome = result.getString("nome");

                proprietarios.add(id + ". " + nome);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return proprietarios.toArray(new String[0]);
    }

    
       

    public boolean Modificar(String id, String novoNome, String novoTelefone, String novoNascimento, String novoEmail) {
    String query = "UPDATE informacoes_proprietario SET nome = ?, telefone = ?, nascimento = ? WHERE idinformacoes_proprietario = ?";

    try (Connection connection = BDConnection.getInstance().getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(query)) {

        preparedStatement.setString(1, novoNome);
        preparedStatement.setString(2, novoTelefone);
        preparedStatement.setString(3, novoNascimento);
        preparedStatement.setString(4, id);

        int rowsUpdated = preparedStatement.executeUpdate();
        return rowsUpdated > 0;

    } catch (SQLException ex) {
        ex.printStackTrace();
    }

    return false;
}}



 







