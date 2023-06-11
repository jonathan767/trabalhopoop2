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

    private static BDConnection instance;

    private String url = "jdbc:mysql://localhost:3306/jonas";
    private String username = "root";
    private String password = "";
    private Connection connection;

    private BDConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            this.connection = DriverManager.getConnection(url, username, password);
            
        } catch (ClassNotFoundException ex) {
               ex.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static synchronized BDConnection getInstance() {
        try {
            if (instance == null || instance.getConnection() == null || instance.getConnection().isClosed()) {
                instance = new BDConnection();
            }
        } catch (SQLException e) {
          e.printStackTrace();
        }

        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
