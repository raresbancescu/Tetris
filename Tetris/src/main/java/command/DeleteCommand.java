package command;

import database.Database;
import database.dao.PersonDAO;

import java.sql.SQLException;
import java.util.List;

public class DeleteCommand
{
    public static String parseDelete(List<String> words)
    {
        String username = words.get(0);
        String password = words.get(1);

        String response = PersonDAO.delete(username, password);

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
