package ui;

import client.Client;
import ui.util.UIUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class DeleteAccountFrame extends JFrame
{
    private JPanel panel;
    private JPasswordField passwordField;
    private JButton confirmButton;

    public DeleteAccountFrame(String name)
    {
        super(name);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setContentPane(this.panel);
        this.pack();

        confirmButton.addActionListener(e ->
        {
            String request = "delete " + Client.getUsername() + " " + Arrays.toString(passwordField.getPassword());
            Client.send(request);
            String response = Client.receive();

            if(response.equals("Ok"))
            {
                UIUtil.switchFrames(this, new RegisterFrame("Tetris"));
            }
        });
    }
}
