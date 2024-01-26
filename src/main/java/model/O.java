package model;


public class O extends Piece {
    
    public O(Tetris board) {
        super(board);
    }

    @Override
    protected void addSquares() {
        squares.add(new Square(-1, -1, this));
        squares.add(new Square(-1, 0, this));
        squares.add(new Square(0, -1, this));
        squares.add(new Square(0, 0, this));
    }

    @Override
    protected void setColor() {
        color = "yellow";
    }
}
