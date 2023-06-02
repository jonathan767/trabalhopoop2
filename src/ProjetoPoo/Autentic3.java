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

public class Autentic3 {
    public static Object[][] Listar() {
        // onde é feito comando banco
   String query = "SELECT idinformacoes_proprietario, nome, telefone, nascimento,email,cep FROM informacoes_proprietario";

    try (Connection connection = BDConnection.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(query);
         ResultSet resultSet = preparedStatement.executeQuery()) {

        List<Object[]> veiculosList = new ArrayList<>();
        while (resultSet.next()) {
            Object[] row = new Object[6];
            row[0] = resultSet.getObject("idinformacoes_proprietario");
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