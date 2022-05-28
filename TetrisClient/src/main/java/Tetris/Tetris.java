package Tetris;

import javax.swing.*;

public class Tetris extends JFrame {
    public Tetris()
    {  Board board=new Board(this);
        add(board);
        board.start();
        setSize(200,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


}
