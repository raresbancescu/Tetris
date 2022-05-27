import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterFrame extends JFrame
{
    private JPanel panel;
    private JButton button;
    private JPasswordField confirmPassword;
    private JTextField username;
    private JPasswordField password;

    public RegisterFrame(String title)
    {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(this.panel);
        this.pack();

        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {

            }
        });
    }
}
