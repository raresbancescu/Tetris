package ServerOperations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private static String dbhost = "jdbc:oracle:thin:@localhost:1521:xe";
    private static String username = "RARES";
    private static String password = "RARES";
    private static Connection conn;
    private static DbConnection singleInstance = null;

    private DbConnection() {

    }

    public static DbConnection getInstance() {
        if (singleInstance == null)
            singleInstance = new DbConnection();
        return singleInstance;
    }

    public static Connection createNewConnection() {
        try {
            conn = DriverManager.getConnection(dbhost, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //System.out.println("s a reusit conexiunea");
            return conn;
        }
    }


}