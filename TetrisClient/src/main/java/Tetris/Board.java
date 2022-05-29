package Tetris;

import client.Client;
import ui.AccountFrame;
import ui.RegisterFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class Board extends JPanel implements ActionListener {

    // for board
    private Integer b_width = 10;
    private Integer b_height = 22;
    private Timer time;
    private Boolean fallingPiece = false;
    private Boolean started = false;
    public static Integer score = 0;
    private Integer currentX = 0;
    private Integer currentY = 0;
    private JLabel printScore=new JLabel("0");
    private Integer gameOver=0;
    // shapes
    private Shape currentPiece;
    private Shape.Piece[] boardPieces;


    public Board(Tetris tetris) {
        setFocusable(true);
        currentPiece = new Shape();
        boardPieces = new Shape.Piece[100000];
       printScore=tetris.getScore();
        time = new Timer(300, this);
        addKeyListener();
        clearBoard();

    }

    private void addKeyListener() {
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                Integer key=e.getKeyCode();
                System.out.println(key);
                if (key.equals(KeyEvent.VK_LEFT))
                {
                    canMove(currentPiece,currentX-1,currentY);

                }
                if (key.equals(KeyEvent.VK_RIGHT))
                {
                    canMove(currentPiece,currentX+1,currentY);
                }
                if (key.equals(KeyEvent.VK_DOWN))
                {
                    canMove(currentPiece.left(),currentX,currentY);
                }
                if (key.equals(KeyEvent.VK_UP))
                {
                    canMove(currentPiece.right(),currentX,currentY);
                }
                if (key.equals(KeyEvent.VK_SPACE))
                {
                    dropFull();
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }


    public Integer sqWidth() {
        return (int) getSize().getWidth() / b_width;
    }

    public Integer sqHeight() {
        return (int) getSize().getHeight() / b_height;
    }

    public Shape.Piece shapeAtCoords(Integer x, Integer y) {
        return boardPieces[y * b_width + x];
    }

    public void clearBoard() {
        for (int i = 0; i < b_height * b_width; i++) {
            boardPieces[i] = Shape.Piece.defaultPiece;
        }
    }

    public void pieceDropped() {
        for (int i = 0; i < 4; i++) {
            Integer x = currentX + currentPiece.getX(i);
            Integer y = currentY - currentPiece.getY(i);
            boardPieces[y * b_width + x] = currentPiece.getShape();
        }
        removeLine();
        if (fallingPiece == false) {
            newPiece();
        }
    }


    public void actionPerformed(ActionEvent ev) {



        if (fallingPiece ) {

            fallingPiece = false;
            newPiece();

        } else {
            if (gameOver!=1)
            dropSlow();
        }
    }

    public void newPiece() {
        currentPiece.setRandomShape();
        currentY = b_height - 1 + currentPiece.minY();
        currentX = b_width / 2 + 1;
        //game over
        if (!canMove(currentPiece, currentX, currentY - 1)) {
            currentPiece.setShape(Shape.Piece.defaultPiece);
            started = false;
            System.out.println("game over");
            //send the points to the server
            String request = "points " + Client.getUsername() + " " + score;
            Client.send(request);
            Client.receive();
            score=0;
            JFrame frame = new AccountFrame("Tetris");
            frame.setVisible(true);
            gameOver=1;
        }
    }

    public void drawSquare(Graphics g, Integer x, Integer y, Shape.Piece piece) {

        g.setColor(piece.color);

        g.fillRect(x + 1, y + 1, sqWidth()-2, sqHeight()-2);
        g.setColor(Color.BLACK);
        g.drawLine(x, y + sqHeight() - 1, x, y);
        g.drawLine(x, y, x + sqWidth() - 1, y);
        g.setColor(Color.BLACK);
        g.drawLine(x + 1, y + sqHeight() - 1, x + sqWidth() - 1, y + sqHeight() - 1);
        g.drawLine(x + sqWidth() - 1, y + sqHeight() - 1, x + sqWidth() - 1, y + 1);

    }

    public void paint(Graphics g) {
        super.paint(g);
        setBackground(new Color(255,209,204));
        Integer top = (int) getSize().getWidth() - b_width * sqHeight();

        for (int i = 0; i < b_height; i++) {
            for (int j = 0; j < b_width; j++) {
                Shape.Piece shape = shapeAtCoords(j, b_height - i - 1);

                if (shape != Shape.Piece.defaultPiece) {
                    drawSquare(g, j * sqWidth(), top + i * sqHeight(), shape);
                }
            }
        }

        if (currentPiece.getShape() != Shape.Piece.defaultPiece) {
            for (int i = 0; i < 4; i++) {
                Integer x = currentX + currentPiece.getX(i);
                Integer y = currentY - currentPiece.getY(i);
                drawSquare(g, x * sqWidth(), top + (b_height - y - 1) * sqHeight(), currentPiece.getShape());
            }
        }

    }

    public void start() {
        started = true;
        fallingPiece = false;
        score = 0;
        clearBoard();
        newPiece();
        time.start();

    }

    private Boolean canMove(Shape newPiece, Integer nX, Integer nY) {
        for (int i = 0; i < 4; i++) {
            Integer x = nX + newPiece.getX(i);
            Integer y = nY - newPiece.getY(i);

            if (x < 0 || x >= b_width || y < 0 || y >= b_height)
                return false;
            if (shapeAtCoords(x, y) != Shape.Piece.defaultPiece)
                return false;
        }
        currentPiece = newPiece;
        currentX = nX;
        currentY = nY;

        repaint();
        return true;
    }

    public void removeLine() {
        int numberOfLines = 0;

        for (int i = b_height - 1; i >= 0; i--) {
            int ok = 1;

            for (int j = 0; j < b_width; j++) {
                if (shapeAtCoords(j, i) == Shape.Piece.defaultPiece) {
                    ok = 0;
                    break;
                }
            }
            if (ok == 1) {
                numberOfLines++;
                //get the shape next to it
                for (int k = i; k < b_height - 1; k++) {
                    for (int f = 0; f < b_width; f++) {
                        boardPieces[k * b_width + f] = shapeAtCoords(f, k + 1);
                    }
                }
            }

            if (numberOfLines > 0) {
                score += numberOfLines*100;
                printScore.setText("Score : " + score);
                fallingPiece = true;
                currentPiece.setShape(Shape.Piece.defaultPiece);
                repaint();
            }
        }
    }


    public void dropFull ()
    {
        int nY = currentY;

        while (nY > 0) {
            if (!canMove(currentPiece, currentX, nY - 1))
                break;
            nY--;
        }
        pieceDropped();
    }

    public void dropSlow ()
    {
        if (!canMove(currentPiece, currentX, currentY - 1))
            pieceDropped();
    }


}

