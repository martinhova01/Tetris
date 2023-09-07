package tetris;

import javafx.scene.paint.Color;

public class L extends Piece {

    

    public L(Tetris board) {
        super(board);
    }

    @Override
    protected void addSquares() {
        squares.add(new Square(-1, 0, this));
        squares.add(new Square(0, 0, this));
        squares.add(new Square(1, 0, this));
        squares.add(new Square(1, -1, this));
    }

    @Override
    protected void setColor() {
        color = Color.ORANGE;
    }
    
}
