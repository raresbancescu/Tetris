package server;


import command.Command;
import command.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

public class ClientThread extends Thread
{
    private Socket socket;

    public ClientThread(Socket socket)
    {
        this.socket = socket;
    }

    public void run()
    {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream());)
        {
            while(true)
            {
                try
                {
                    String request = in.readLine();
                    String response = Command.parse(request);
                    out.println(response);
                    out.flush();
                } catch (SocketException | SocketTimeoutException e)
                {
                    System.out.println("Client disconnected");
                    break;
                }
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
