package ui;

import client.Client;
import ui.util.UIUtil;

import javax.swing.*;
import java.awt.*;

public class LogInFrame extends JFrame
{
    private JPanel panel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton button;
    private JLabel title;
    private JButton registerButton;
    private JLabel errField;

    public LogInFrame(String title)
    {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setContentPane(this.panel);
        this.pack();

        errField.setForeground(Color.RED);

        button.addActionListener(e ->
        {
            String username = usernameField.getText();
            String password = String.valueOf(passwordField.getPassword());

            String request = "login " + username + " " + password;
            Client.send(request);
            String response = Client.receive();

            if(response.equals("Ok"))
            {
                UIUtil.switchFrames(this, new AccountFrame("Tetris"));
                Client.setUsername(username);
            }

            errField.setText(response);
        });

        registerButton.addActionListener(e -> UIUtil.switchFrames(this, new AccountFrame("Tetris")));
    }
}
