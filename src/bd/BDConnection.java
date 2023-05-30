package bd;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BDConnection {

    private static String hostname = "localhost";
    private static String database = "jonas";
    private static String username = "root";
    private static String password = "";

    public static void insereVeiculo(String modelo, String nome, String placa, int ano, String tipo, int id_proprietario) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String urlJDBC = "jdbc:mysql://" + hostname + ":3306/" + database;
        Connection conexao = (Connection) DriverManager.getConnection(urlJDBC, username, password);

        String query = "INSERT INTO veiculos (modelo, nome, placa, ano, tipo, id_proprietario) VALUES(?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = conexao.prepareStatement(query)) {
            preparedStatement.setString(1, modelo);
            preparedStatement.setString(2, nome);
            preparedStatement.setString(3, placa);
            preparedStatement.setInt(4, ano);
            preparedStatement.setString(5, tipo);
            preparedStatement.setInt(6, id_proprietario);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insereProprietario(String login, String email, String senha, String email1, String cep) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String urlJDBC = "jdbc:mysql://" + hostname + ":3306/" + database;
        Connection conexao = (Connection) DriverManager.getConnection(urlJDBC, username, password);

        String query = "INSERT INTO proprietarios (login, email, senha) VALUES(?, ?, ?)";
        try (PreparedStatement preparedStatement = conexao.prepareStatement(query)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, senha);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean verificaProprietario(String login, String senha) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String urlJDBC = "jdbc:mysql://" + hostname + ":3306/" + database;
        Connection conexao = (Connection) DriverManager.getConnection(urlJDBC, username, password);

        String query = "SELECT * FROM proprietarios WHERE login = ? AND senha = ?";
        try (PreparedStatement preparedStatement = conexao.prepareStatement(query)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, senha);

            try (ResultSet result = preparedStatement.executeQuery()) {
                return result.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static String[] getProprietarios() throws ClassNotFoundException {
        List<String> proprietarios = new ArrayList<>();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String urlJDBC = "jdbc:mysql://" + hostname + ":3306/" + database;
            Connection conexao = (Connection) DriverManager.getConnection(urlJDBC, username, password);

            String query = "SELECT * FROM informacoes_proprietario;";
            try (PreparedStatement preparedStatement = conexao.prepareStatement(query);
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
        } catch (SQLException ex) {
            Logger.getLogger(BDConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return proprietarios.toArray(new String[0]);
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String urlJDBC = "jdbc:mysql://" + hostname + ":3306/" + database;
        return (Connection) DriverManager.getConnection(urlJDBC, username, password);
    }
}
