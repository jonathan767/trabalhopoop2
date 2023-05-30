package ProjetoPoo;

import bd.BDConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.sql.ResultSet;
import java.util.logging.Logger;

public class Autenticacao {

    public static boolean login(String email, String password) {
        String query = "SELECT email, password FROM users WHERE email=? AND password=?";

        try (Connection connection = BDConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return true;
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Autenticacao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Autenticacao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
    
   public static boolean cadastro(String email, String senha){
       String query = "INSERT INTO users (email, password) VALUES(?,?)";
       
        try (Connection connection = BDConnection.getConnection(); 
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, senha);

            int inseriu = preparedStatement.executeUpdate();
            if(inseriu > 0) return true;  
                      
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Autenticacao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Autenticacao.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       return false;
   }

}
