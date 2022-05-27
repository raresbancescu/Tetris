package command;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Util class used to parse the commands from the client
 */
public class Command
{
    private final static String UNKNOWN_REQUEST = "Unknown request";
    private final static String REGISTER = "REGISTER";
    private final static String LOGIN = "LOGIN";
    private final static String RESET_PASSWORD = "RESETPASSWORD";
    private final static String DELETE = "DELETE";
    private final static String POINTS = "POINTS";

    public static String parse(String request)
    {
        List<String> words = Arrays.stream(request.trim().split(" ")).toList();
        System.out.println("Command: " + words);

        String commandName = words.get(0).toUpperCase(Locale.ROOT).trim();
        words = words.subList(1, words.size());

        return switch (commandName)
                {
                    case REGISTER -> RegisterCommand.parseRegister(words);
                    case LOGIN -> LoginCommand.parseLogin(words);
                    case RESET_PASSWORD -> ResetPasswordCommand.parseResetPassword(words);
                    case DELETE -> DeleteCommand.parseDelete(words);
                    case POINTS -> PointsCommand.parsePoints(words);
                    default -> UNKNOWN_REQUEST;
                };
    }
}
