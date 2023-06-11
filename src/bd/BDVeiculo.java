package bd;

import bd.BDConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BDVeiculo {
   
    public static Object[][] Listar() throws SQLException {
        String query = "SELECT id_veiculo, nome, modelo, placa, ano FROM veiculos";

        try (Connection connection = BDConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            
            List<Object[]> veiculosList = new ArrayList<>();
            while (resultSet.next()) {
                Object[] row = new Object[5];
                row[0] = resultSet.getObject("id_veiculo");
                row[1] = resultSet.getObject("nome");
                row[2] = resultSet.getObject("modelo");
                row[3] = resultSet.getObject("placa");
                row[4] = resultSet.getObject("ano");
                veiculosList.add(row);
            }

            return veiculosList.toArray(new Object[0][]);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return new Object[0][];
    }

    public static void insereVeiculo(String modelo, String nome, String placa, int ano, String tipo, int id_proprietario) throws SQLException {
        String query = "INSERT INTO veiculos (modelo, nome, placa, ano, tipo, id_proprietario) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = BDConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
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
    
    
    public static boolean Modificar(String id, String Nome, String Modelo, String Placa, String Ano) {
    String query = "UPDATE veiculos SET  Nome = ?, Modelo = ?, Placa= ?, Ano= ? WHERE id_veiculo = ?";

    try (Connection connection = BDConnection.getInstance().getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        preparedStatement.setString(1, Nome);
        preparedStatement.setString(2, Modelo);
        preparedStatement.setString(3, Placa);
        preparedStatement.setString(4, Ano);
        preparedStatement.setString(5, id);

        int rowsUpdated = preparedStatement.executeUpdate();
        return rowsUpdated > 0;

    } catch (SQLException ex) {
        ex.printStackTrace();
    }

    return false;
}


    public static boolean Deletar(String id) {
        String query = "DELETE FROM veiculos WHERE id_veiculo = ?";

        try (Connection connection = BDConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, id);

            int rowsDeleted = preparedStatement.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    }

    