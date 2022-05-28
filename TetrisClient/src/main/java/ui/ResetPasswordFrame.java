package ui;

import client.Client;
import ui.util.UIUtil;

import javax.swing.*;
import java.util.Arrays;

public class ResetPasswordFrame extends JFrame
{
    private JPanel panel;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JButton confirmButton;

    public ResetPasswordFrame(String name)
    {
        super(name);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setContentPane(this.panel);
        this.pack();

        confirmButton.addActionListener(e ->
        {
            String password = Arrays.toString(passwordField.getPassword());
            String confirmPassword = Arrays.toString(confirmPasswordField.getPassword());
            String request = "resetpassword " + Client.getUsername() + " " + password + " " + confirmPassword;
            Client.send(request);
            String response = Client.receive();

            if(response.equals("Ok"))
            {
                UIUtil.switchFrames(this, new LogInFrame("Tetris"));
            }
        });
    }

}
