package Tetris;

import java.awt.*;
import java.util.Random;

public class Shape {

    enum Piece {
        defaultPiece(new Integer[][]{{0, 0}, {0, 0}, {0, 0}, {0, 0}},new Color(0,0,0)),
        zPiece(new Integer[][]{{0, -1}, {0, 0}, {-1, 0}, {-1, 1}},new Color(255,0,127)),
        linePiece(new Integer[][]{{0, -1}, {0, 0}, {0, 1}, {0, 2}},new Color(153,0,0)),
        tPiece(new Integer[][]{{-1, 0}, {0, 0}, {1, 0}, {0, 1}},new Color(178,102,255)),
        squarePiece(new Integer[][]{{0, 0}, {1, 0}, {0, 1}, {1, 1}},new Color(255,0,255)),
        lPiece(new Integer[][]{{-1, -1}, {0, -1}, {0, 0}, {0, 1}},new Color(51,51,255));



        public Integer[][]coordonates; //choose the coordonates from enum
        public Color color;

        private Piece(Integer [][]coordonates, Color color)
        {
            this.coordonates=coordonates;
            this.color=color;
        }
    }

    private Piece pieceShape;
    private Integer[][] coordonates;

    public Shape()
    {
        coordonates=new Integer[5][3];
        setShape(Piece.defaultPiece);
    }
    //we get the coordonates from the piece and we add to the shape

    //default coordonates initial (after that we have others coordonates)
    public void setShape(Piece piece)
    {
        for (int i=0;i<4;i++)
        {
            for (int j=0;j<2;j++)
            {
                coordonates[i][j]=piece.coordonates[i][j];
            }
        }
        pieceShape=piece;
    }

    public void setX(Integer index, Integer x)
    {
        coordonates[index][0]=x;
    }

    public void setY(Integer index, Integer y)
    {
        coordonates[index][1]=y;
    }

    public Piece getShape()
    {
        return pieceShape;
    }

    public Integer getX(Integer index)
    {
        return coordonates[index][0];
    }

    public Integer getY(Integer index)
    {
        return coordonates[index][1];
    }

    public  void setRandomShape()
    {
        Random random=new Random();

        Integer result = Math.abs(random.nextInt()) % 5 + 1;
        Piece[]pieces=Piece.values();
        setShape(pieces[result]);
    }

    //get the lowest point of a piece
    public Integer minX()
    {
        Integer minimum=100000;
        Integer result=0;
        for (int i=0;i<4;i++)
        {
            if (coordonates[i][0]<minimum)
            {
                result=coordonates[i][0];
                minimum=coordonates[i][0];
            }
        }
        return result;
    }

    public Integer minY()
    {
        Integer minimum=100000;
        Integer result=0;
        for (int i=0;i<4;i++)
        {
            if (coordonates[i][1]<minimum)
            {
                result=coordonates[i][1];
                minimum=coordonates[i][1];
            }
        }
        return result;
    }

    public Shape left() {
        if (pieceShape != Piece.squarePiece) {
            Shape rotate = new Shape();
            rotate.pieceShape = pieceShape;

            for (int i = 0; i < 4; i++) {
                rotate.setX(i, getY(i));
                rotate.setY(i, -getX(i));


            }
            return rotate;
        }
        else return this;
    }

    public Shape right()
    {
        if (pieceShape != Piece.squarePiece) {
            Shape rotate = new Shape();
            rotate.pieceShape = pieceShape;

            for (int i = 0; i < 4; i++) {
                rotate.setX(i, -getY(i));
                rotate.setY(i, -getX(i));


            }
            return rotate;
        }
        else return this;
    }


}

