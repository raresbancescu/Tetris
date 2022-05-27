package ServerOperations;

import java.awt.*;
import java.io.File;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

public class DatabaseHandler {
    private static DbConnection connection = DbConnection.getInstance();

    public String handleMessage(String message) {
        String returnedMessage = "";
        String[] words = message.split(" ");
        if (words[0].equals("login")) {
            try (Connection conn = connection.createNewConnection()) {


                CallableStatement stmt;
                String result = new String();
                stmt = conn.prepareCall("{?=call user_validations.check_login(?,?)}");
                stmt.setString(2, words[1]);
                stmt.setString(3, words[2]);
                stmt.registerOutParameter(1, Types.VARCHAR);
                stmt.execute();
                System.out.println("s a executat");
                returnedMessage = stmt.getString(1);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (words[0].equals("register")) {
            try (Connection conn = connection.createNewConnection()) {
                CallableStatement stmt;
                String result = new String();
                stmt = conn.prepareCall("{?=call user_validations.add_register_user(?,?,?)}");
                stmt.setString(2, words[1]);
                stmt.setString(3, words[2]);
                stmt.setString(4, words[3]);
                stmt.registerOutParameter(1, Types.VARCHAR);
                stmt.execute();
                returnedMessage = stmt.getString(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (words[0].equals("resetpassword"))
        {
            try (Connection conn = connection.createNewConnection()) {
                CallableStatement stmt;
                String result = new String();
                stmt = conn.prepareCall("{?=call user_validations.reset_password(?,?,?)}");
                stmt.setString(2, words[1]);
                stmt.setString(3, words[2]);
                stmt.setString(4, words[3]);
                stmt.registerOutParameter(1, Types.VARCHAR);
                stmt.execute();
                returnedMessage = stmt.getString(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (words[0].equals("delete"))
        {
            try (Connection conn = connection.createNewConnection()) {
                CallableStatement stmt;
                String result = new String();
                stmt = conn.prepareCall("{?=call user_validations.delete_user(?,?)}");
                stmt.setString(2, words[1]);
                stmt.setString(3, words[2]);
                stmt.registerOutParameter(1, Types.VARCHAR);
                stmt.execute();
                returnedMessage = stmt.getString(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (words[0].equals("points"))
        {
            try (Connection conn = connection.createNewConnection()) {
                CallableStatement stmt;
                String result = new String();
                stmt = conn.prepareCall("{call tetris_points.add_points(?,?)}");
                stmt.setString(1, words[1]);
                stmt.setString(2, words[2]);
                stmt.execute();
                returnedMessage ="Done";
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (words[0].equals("top"))
        {
            try (Connection conn = connection.createNewConnection()) {
                CallableStatement stmt;
                String result = new String();
                stmt = conn.prepareCall("{call tetris_points.top()}");
                stmt.execute();
                returnedMessage ="Done";
                File file = new File("D:\\facultate\\Anul 2\\Semestrul 2\\PA\\top.txt");
                Desktop desktop = Desktop.getDesktop();
                desktop.open(file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else
        {
            returnedMessage="nu s a executat nimic";
        }
        System.out.println("De la baza de date" + returnedMessage);
        return returnedMessage;
    }
}
