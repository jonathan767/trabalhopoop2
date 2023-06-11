package bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BDAutenticacao {

    public static boolean login(String email, String password) throws SQLException {
        String query = "SELECT email, password FROM users WHERE email=? AND password=?";

        try (Connection connection = BDConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return true;
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public static boolean cadastro(String email, String senha) {
        String query = "INSERT INTO users (email, password) VALUES(?,?)";

        try (Connection connection = BDConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, senha);

            int inseriu = preparedStatement.executeUpdate();
            if (inseriu > 0) {
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }
}
