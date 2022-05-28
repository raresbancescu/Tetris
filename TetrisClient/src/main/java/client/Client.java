package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Client
{
    private static final String LOCALHOST = "127.0.0.1";
    private static final int PORT = 2909;
    private static Socket socket = null;
    private static String username;

    private static PrintWriter out;
    private static BufferedReader in;
    private static BufferedReader reader;

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

    public static String getUsername()
    {
        return username;
    }

    public static void setUsername(String username)
    {
        Client.username = username;
    }
}

