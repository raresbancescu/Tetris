package command;

import database.Database;
import database.dao.PersonDAO;

import java.sql.SQLException;
import java.util.List;

public class RegisterCommand
{
    public static String parseRegister(List<String> words)
    {
        String name = words.get(0);
        String password = words.get(1);
        String confirmPassword = words.get(2);

        String response = PersonDAO.register(name, password, confirmPassword);
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
