package command;

import database.Database;
import database.dao.PersonDAO;

import java.sql.SQLException;
import java.util.List;

public class PointsCommand
{
    public static String parsePoints(List<String> words)
    {
        String username = words.get(0);
        String points = words.get(1);

        String response = PersonDAO.addPoints(username, points);

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
