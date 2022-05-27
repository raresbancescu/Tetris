import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Client
{
    private static final String LOCALHOST = "127.0.0.1";
    private static final int PORT = 7002;
    private static Socket socket = null;

    static PrintWriter out;
    static BufferedReader in;
    static BufferedReader reader;

    private Client()
    {

    }

    public static void start()
    {
        try
        {
            socket = new Socket(LOCALHOST, PORT);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            reader = new BufferedReader(new InputStreamReader(System.in));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void send(String request)
    {
        out.println(request);
    }

    public static String receive()
    {
        String response = "";
        try
        {
            response = in.readLine();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return response;
    }
}

