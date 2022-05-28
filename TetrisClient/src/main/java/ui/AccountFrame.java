package ui;

import Tetris.Tetris;
import ui.util.UIUtil;

import javax.swing.*;

public class AccountFrame extends JFrame
{
    private JButton play;
    private JButton resetPassword;
    private JButton delete;
    private JPanel panel;

    public AccountFrame(String name)
    {
        super(name);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setContentPane(this.panel);
        this.pack();

        play.addActionListener(e ->
        {
            // TODO switch to the tetris frame
            Tetris tetris=new Tetris();
            tetris.setVisible(true);
            this.setVisible(false);
        });

        resetPassword.addActionListener(e ->
                UIUtil.switchFrames(this, new ResetPasswordFrame("Tetris")));

        delete.addActionListener(e ->
                UIUtil.switchFrames(this, new DeleteAccountFrame("Tetris")));
    }

}
