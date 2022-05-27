package ServerOperations;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import ClientConn.*;
import ServerOperations.DbConnection;

public class Connections {


    private ServerSocket server = null;


    public void createConnection(int port) {
        try {
            server = new ServerSocket(port);
            server.setReuseAddress(true);


            while (true) {


                Socket client = server.accept();
                System.out.println("test client" + client.getInetAddress().getHostName());
                ClientHandler clientInstance = new ClientHandler(client);
                new Thread(clientInstance).start();


            }
        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            if (server != null) {
                try {
                    server.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }
}