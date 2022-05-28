package command;

import database.Database;
import database.dao.PersonDAO;

import java.sql.SQLException;
import java.util.List;

public class GetHighScoreCommand {

    public static String parseHigh(List<String> words)
    {
        String username = words.get(0);
        String response = PersonDAO.getPoints(username);

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
