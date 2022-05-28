package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
    private static final int PORT = 2909;

    private Server()
    {

    }

    public static void start()
    {
        try (ServerSocket serverSocket = new ServerSocket(PORT))
        {
            while (true)
            {
                System.out.println("Waiting for a client...");
                Socket socket = serverSocket.accept();

                new ClientThread(socket).start();
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}