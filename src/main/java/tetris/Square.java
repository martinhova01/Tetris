package tetris;

public class Square {

    private int offsetX;
    private int offsetY;
    private Piece piece;

    public Square(int offsetX, int offsetY, Piece p){
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.piece = p;
    }

    public int getOffsetX() {
        return offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }
    public Piece getPiece() {
        return piece;
    }
    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
    }
    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }

    public boolean canMove(int[] direction){

        int targetX = piece.getX() + this.offsetX + direction[0];
        int targetY = piece.getY() + this.offsetY + direction[1];

            //tries to move out of bounds
        if(targetX < 0 || targetX > piece.getBoard().getWidth() - 1 || targetY < 0 || targetY > piece.getBoard().getHeight() - 1){
            return false;
        }

        Square target = piece.getBoard().get(targetX, targetY);

            //tries to move into an empty square
        if (target == null){
            return true;
        }
            //this tries to move into a square of the same piece
        if (target.getPiece() == this.getPiece()){
            return true;
        }
        return false;
        
    }

    public boolean canRotate(boolean clockWise) {
        int targetX;
        int targetY;
        if(clockWise){
            targetX = piece.getX() + offsetY;
            targetY = piece.getY() + (offsetX * -1);
        }
        else{
            targetX = piece.getX() + (offsetY * -1);
            targetY = piece.getY() + offsetX;
        }

            //tries to move out of bounds
            if(targetX < 0 || targetX > piece.getBoard().getWidth() - 1 || targetY < 0 || targetY > piece.getBoard().getHeight() - 1){
                return false;
            }
    
            Square target = piece.getBoard().get(targetX, targetY);
    
                //tries to move into an empty square
            if (target == null){
                return true;
            }
                //this tries to move into a square of the same piece
            if (target.getPiece() == this.getPiece()){
                return true;
            }
            return false;

    }

    
}
