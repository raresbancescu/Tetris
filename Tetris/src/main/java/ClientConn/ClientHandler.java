package ClientConn;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import ServerOperations.*;

public class ClientHandler implements Runnable {
    private Socket socket;
    DatabaseHandler databaseFunctions=new DatabaseHandler();

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run()
    {

        DataInputStream dataReceived = null;
        DataOutputStream dataSend = null;

        try {
            dataReceived = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            dataSend = new DataOutputStream(socket.getOutputStream());
            String message = "";
            while (!message.equals("stop")) {
                message = dataReceived.readUTF();
                String returnMessage = messageHandler(message);
                dataSend.writeUTF(returnMessage);
            }
            socket.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }








    public String messageHandler(String message) {
        String returnMessage = "";
        //login

        if (message.contains("login")) {

            String[] words = message.split(" ");
            if (words.length != 3) {
                returnMessage = "Syntax error";
            } else {
               returnMessage= databaseFunctions.handleMessage("login " + words[1] + " " + words[2]);
            }
        }

        //register

        else if (message.contains("register")) {
            String[] words = message.split(" ");
            if (words.length != 4) {
                returnMessage = "Syntax error";
            } else {
                returnMessage= databaseFunctions.handleMessage("register " + words[1] + " " + words[2]+ " " + words[3]);
            }
        }

        else if (message.contains("resetpassword"))
        {
            String[] words = message.split(" ");
            if (words.length!=4)
            {
                returnMessage="Syntax error";
            }
            else
            {
                returnMessage=databaseFunctions.handleMessage("resetpassword " + words[1] + " " + words[2] + " " + words[3]);
            }
        }

        else if(message.contains("delete"))
        {
            String[] words = message.split(" ");
            if (words.length!=3)
            {
                returnMessage="Syntax error";
            }
            else
            {
                returnMessage=databaseFunctions.handleMessage("delete " + words[1] + " " + words[2]);
            }
        }
        else if (message.contains("points"))
        {
            String[] words=message.split(" ");
            if (words.length!=3)
            {
                returnMessage="Syntax error";
            }
            else
            {
                returnMessage=databaseFunctions.handleMessage("points " + words[1] + " " + words[2]);
            }
        }
        else if (message.contains("top"))
        {
            String[] words=message.split(" ");
            if (words.length!=1)
            {
                returnMessage="Syntax error";
            }
            else
            {
                returnMessage=databaseFunctions.handleMessage("top");
            }
        }
        else returnMessage="Syntax error";
        System.out.println(returnMessage);
       return returnMessage;

    }
}