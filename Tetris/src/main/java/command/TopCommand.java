package command;

import database.Database;
import database.dao.PersonDAO;

import java.sql.SQLException;
import java.util.List;

public class TopCommand
{
    public static String getTop()
    {
        String response = PersonDAO.getTop();

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
