package model;

public class I extends Piece {
    
    public I(Tetris board) {
        super(board);
    }

    @Override
    protected void addSquares() {
        squares.add(new Square(-1, -1, this));
        squares.add(new Square(0, -1, this));
        squares.add(new Square(1, -1, this));
        squares.add(new Square(2, -1, this));
    }

    @Override
    protected void setColor() {
        color = "lightblue";
    }
}
