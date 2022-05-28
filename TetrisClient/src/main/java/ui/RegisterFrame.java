package ui;

import client.Client;
import ui.util.UIUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegisterFrame extends JFrame
{
    private JPanel panel;
    private JButton button;
    private JPasswordField confirmPasswordField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel errField, title;
    private JButton loginButton;

    public RegisterFrame(String title)
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
            String confirmPassword = String.valueOf(confirmPasswordField.getPassword());

            String request = "register " + username + " " + password + " " + confirmPassword;
            Client.send(request);
            String response = Client.receive();

            if(response.equals("Ok"))
            {
                UIUtil.switchFrames(this, new LogInFrame("Tetris"));
            }

            errField.setText(response);
        });

        usernameField.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                errField.setText("");
            }
        });

        passwordField.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                errField.setText("");
            }
        });

        confirmPasswordField.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                errField.setText("");
            }
        });

        loginButton.addActionListener(e -> UIUtil.switchFrames(this, new LogInFrame("Tetris")));
    }
}
