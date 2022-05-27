package command;

import database.Database;
import database.dao.PersonDAO;
import server.ClientThread;

import java.sql.SQLException;
import java.util.List;

public class LoginCommand
{
    public static String parseLogin(List<String> words)
    {
        String username = words.get(0);
        String password = words.get(1);

        String response = PersonDAO.login(username, password);

        try
        {
            Database.getConnection().commit();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return response;
    }
}
