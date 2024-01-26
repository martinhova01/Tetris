package model;

import java.util.ArrayList;
import java.util.Collection;

public abstract class Piece {

    protected Collection<Square> squares = new ArrayList<>();

        // the (x, y)-position that the squares offsets are based on
    protected int x;
    protected int y;
    protected String color;
    protected Tetris board;

    public Piece(Tetris board){
        this.x = board.getWidth() / 2;
        this.y = 1;
        this.board = board;
        setColor();
        addSquares();
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public Tetris getBoard() {
        return board;
    }
    public Collection<Square> getSquares() {
        return squares;
    }
    public String getColor() {
        return color;
    }

        //creates the square objects and define their offsets besed on what piece it is
    protected abstract void addSquares();

    protected abstract void setColor();

    public boolean canMove(int[] direction) {
        for(Square s : squares){
            if(!s.canMove(direction)){
                return false;
            }
        }
        return true;
    }

    public void move(int[] direction) {
        x += direction[0];
        y += direction[1];
    }

    public void rotateCounterClockWise(){
        for(Square s : squares){
            int temp = s.getOffsetX();
            s.setOffsetX(s.getOffsetY() * -1);
            s.setOffsetY(temp);
        }
    }

    public void rotateClockWise(){
        for(Square s : squares){
            int temp = s.getOffsetX();
            s.setOffsetX(s.getOffsetY());
            s.setOffsetY(temp * -1);
        }
    }

    public boolean canRotate(boolean clockWise) {
        for(Square s : squares){
            if(!s.canRotate(clockWise)){
                return false;
            }
        }
        return true;
    }

    public void setY(int y) {
        this.y = y;
    }
    
}
