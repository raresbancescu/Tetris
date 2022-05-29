package database.dao;

import database.Database;
import java.awt.*;
import java.io.File;
import java.sql.*;

/**
 * Class responsible with communicating with the database
 */
public class PersonDAO
{
    private final static Connection connection = Database.getConnection();

    private PersonDAO(){}

    /**
     * Method used to add a new user to the database
     * @param name
     * @param password
     * @param confirmPassword
     * @return the return message of the add_register_user function from the database
     */
    public static String register(String name, String password, String confirmPassword)
    {
        String response = "";
        try {
            CallableStatement stmt;
            stmt = connection.prepareCall("{? = call user_validations.add_register_user(?,?,?)}");
            stmt.setString(2, name);
            stmt.setString(3, password);
            stmt.setString(4, confirmPassword);
            stmt.registerOutParameter(1, Types.VARCHAR);
            stmt.execute();
            response = stmt.getString(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * Method used to log in an existing user
     * @param username
     * @param password
     * @return
     */
    public static String login(String username, String password)
    {
        String response = "";
        try {
            CallableStatement stmt;
            stmt = connection.prepareCall("{? = call user_validations.check_login(?,?)}");
            stmt.setString(2, username);
            stmt.setString(3, password);
            stmt.registerOutParameter(1, Types.VARCHAR);
            stmt.execute();
            System.out.println("Player was logged out");
            response = stmt.getString(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * Method used to reset the password of a user
     * @param username
     * @param password
     * @param confirmPassword
     * @return the return message of the reset_password function from the database
     */
    public static String resetPassword(String username, String password, String confirmPassword)
    {
        String response = "";
        try {
            CallableStatement stmt;
            stmt = connection.prepareCall("{? = call user_validations.reset_password(?,?,?)}");
            stmt.setString(2, username);
            stmt.setString(3, password);
            stmt.setString(4, confirmPassword);
            stmt.registerOutParameter(1, Types.VARCHAR);
            stmt.execute();
            response = stmt.getString(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * Method used to delete a user from the database
     * @param username
     * @param password
     * @return the return message of the delete_user function from the database
     */
    public static String delete(String username, String password)
    {
        String response = "";
        try {
            CallableStatement stmt;
            String result = new String();
            stmt = connection.prepareCall("{? = call user_validations.delete_user(?,?)}");
            stmt.setString(2, username);
            stmt.setString(3, password);
            stmt.registerOutParameter(1, Types.VARCHAR);
            stmt.execute();
            response = stmt.getString(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * Method used to ass points to a user
     * @param username
     * @param points
     * @return
     */
    public static String addPoints(String username, String points)
    {
        String response = "";
        try {
            CallableStatement stmt;
            stmt = connection.prepareCall("{call tetris_points.add_points(?,?)}");
            stmt.setString(1, username);
            stmt.setString(2, points);
            stmt.execute();
            response = "Added points to " + username;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }


    public static String getPoints(String username)
    {
        String response = "";
        try {
            CallableStatement stmt;
            stmt = connection.prepareCall("{?=call getHighScore(?)}");
            stmt.setString(2, username);

            stmt.registerOutParameter(1, Types.VARCHAR);
            stmt.execute();
            response = stmt.getString(1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
    /**
     * Method used to write the top players in a file
     * @return
     */
    public static String getTop()
    {
        String response = "";
        try {
            response = "The top was written in the top file";
            File file = new File("D:\\facultate\\Anul 2\\Semestrul 2\\PA\\project\\Tetris\\src\\main\\java\\database\\dao\\top.txt");

            CallableStatement stmt;
            stmt = connection.prepareCall("{call tetris_points.top()}");
            stmt.execute();

            Desktop desktop = Desktop.getDesktop();
            desktop.open(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
