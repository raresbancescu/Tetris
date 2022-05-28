package Tetris;

import ui.AccountFrame;
import ui.util.UIUtil;

import javax.swing.*;
import java.awt.*;

public class Tetris extends JFrame {
    private JLabel printScore=new JLabel("0");
    private JLabel highScore;
    public Tetris(String points)
    {
        printScore = new JLabel("Score:");
        highScore=new JLabel("HighScore: " + points);
        Board board=new Board(this);
        add(board);
        add(printScore, BorderLayout.NORTH);
        add(highScore,BorderLayout.SOUTH);
        board.start();
        setSize(200,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }



    public JLabel getScore() {
        return printScore;
    }

}
