import client.Client;
import ui.RegisterFrame;

import javax.swing.*;

public class Main
{
    public static void main(String[] args)
    {
        Client.start();
        //System.out.println(Client.receive());
        JFrame frame = new RegisterFrame("Tetris");
        frame.setVisible(true);
    }
}
