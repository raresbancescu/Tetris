package command;

import database.Database;
import database.dao.PersonDAO;

import java.sql.SQLException;
import java.util.List;

public class ResetPasswordCommand
{
    public static String parseResetPassword(List<String> words)
    {
        String username = words.get(0);
        String password = words.get(1);
        String confirmPassword = words.get(2);

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
