package ProjetoPoo;

import bd.BDConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.sql.ResultSet;
import java.util.logging.Logger;

import java.util.List;
import java.util.ArrayList;

public class Autentic2 {
    public static Object[][] Listar() {
    String query = "SELECT id_veiculo, nome, modelo, placa, ano FROM veiculos";

    try (Connection connection = BDConnection.getConnection();
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

    } catch (ClassNotFoundException | SQLException ex) {
        ex.printStackTrace();
    }

    return new Object[0][];
}
}




/* public static Object[][] List2() {
    String query = "SELECT idinformacoesproprietario, nome, telefone, nascimento,email,cep SElECT FROM informacoes_proprietario";

    try (Connection connection = BDConnection.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(query);
         ResultSet resultSet = preparedStatement.executeQuery()) {

        List<Object[]> veiculosList = new ArrayList<>();
        while (resultSet.next()) {
            Object[] row = new Object[6];
            row[0] = resultSet.getObject("idinformacoesproprietario");
            row[1] = resultSet.getObject("nome");
            row[2] = resultSet.getObject("telefone");
            row[3] = resultSet.getObject("nascimento");
            row[4] = resultSet.getObject("email");
            row[5] = resultSet.getObject("cep");
            veiculosList.add(row);
        }

        return veiculosList.toArray(new Object[0][]);

    } catch (ClassNotFoundException | SQLException ex) {
        ex.printStackTrace();
    }

    return new Object[0][];
}
} 
\**/