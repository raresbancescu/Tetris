import javax.swing.*;

public class Main
{
    public static void main(String[] args)
    {
        Client.start();
        JFrame frame = new RegisterFrame("Tetris");
        frame.setVisible(true);
    }
}
