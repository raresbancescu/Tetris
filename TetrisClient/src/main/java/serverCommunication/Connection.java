package serverCommunication;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Connection {

    private Socket socket = null;
    private DataInputStream read = null;
    private DataOutputStream send = null;
    private DataInputStream readAgain = null;

    public Connection(String address, int port) {
        setConnection(address, port);
        messageControl();
    }

    public void setConnection(String address, int port) {


        try {
            socket = new Socket(address, port);
            read = new DataInputStream(System.in);
            send = new DataOutputStream(socket.getOutputStream());
            readAgain = new DataInputStream(socket.getInputStream());

        } catch (Exception e) {

            try {
                read = new DataInputStream(System.in);
                send = new DataOutputStream(socket.getOutputStream());
            } catch (Exception b) {
                b.printStackTrace();
            }
        }


    }

    public void messageControl() {
        String whatWeSend = "";
        String whatWeReceive = "";
        while (!whatWeSend.equals("stop")) {
            try {

                whatWeSend = read.readLine(); //from user

                    send.writeUTF(whatWeSend); //send to socket
                    whatWeReceive = readAgain.readUTF();
                    System.out.println(whatWeReceive);

            } catch (Exception e) {

                System.out.println("Server closed");
                System.exit(130);
            }
        }


        try {
            read.close();
            send.close();
            System.exit(130);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}