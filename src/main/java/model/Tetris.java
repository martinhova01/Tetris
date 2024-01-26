package model;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tetris {

    private List<List<Square>> grid;
    private int width;
    private int height;
    private Piece activePiece;
    private Piece heldPiece;
    private Piece nextPiece;
    private int comboCounter;
    private int totalScore;

    public static final int[] DOWN = {0, 1};
    public static final int[] LEFT = {-1, 0};
    public static final int[] RIGHT = {1, 0};

    public Tetris(){
        this.grid = new ArrayList<>();
        this.height = 20;
        this.width = 10;
        this.comboCounter = 0;

        for(int i = 0; i < height; i++){
            List<Square> newRow = new ArrayList<>();
            for (int j = 0; j < width; j++){
                newRow.add(null);
            }
            grid.add(newRow);
        }

    }

    public Square get(int x, int y){
        return grid.get(y).get(x);
    }

    public void setSquare(int x, int y, Square s){
        grid.get(y).set(x, s);
    }

    public Piece getHeldPiece() {
        return heldPiece;
    }
    public Piece getNextPiece() {
        return nextPiece;
    }
    public int getTotalScore() {
        return totalScore;
    }
    public int getComboCounter() {
        return comboCounter;
    }

    public void spawnPiece(){
        boolean combo = false;
        int linesCleared = 0;
        for(int line = 0; line < height; line++){
            if(isFullLine(line)){
                clearLine(line);
                combo = true;
                linesCleared++;
            }
        }
        if(combo){
            comboCounter++;
            totalScore += (comboCounter * 100 * linesCleared);
        }
        else{
            comboCounter = 0;
        }
        try{
            //first time a piece is spawned
            if(nextPiece == null){
                nextPiece = getRandomPiece(this);
                activePiece = getRandomPiece(this);
            }
            else{
                activePiece = nextPiece;
                nextPiece = getRandomPiece(this);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        update(false);
    }

    private void clearLine(int line) {
        for(int y = line - 1; y >= 0; y--){
            for(int x = 0; x < width; x++){
                Square s = get(x, y);
                setSquare(x, y + 1, s);
            }
        }
    }

    //from chatGPT
    public static Piece getRandomPiece(Tetris tetris) throws Exception {
        List<Class<? extends Piece>> subclasses = new ArrayList<>();
        subclasses.add(I.class);
        subclasses.add(L.class);
        subclasses.add(J.class);
        subclasses.add(O.class);
        subclasses.add(S.class);
        subclasses.add(T.class);
        subclasses.add(Z.class);
        // Add as many subclasses as needed
        
        Random random = new Random();
        Class<? extends Piece> randomClass = subclasses.get(random.nextInt(subclasses.size()));
    
        Constructor<? extends Piece> constructor = randomClass.getDeclaredConstructor(tetris.getClass());
        return constructor.newInstance(tetris);
    }

    public boolean movePiece(int[] direction){
        if(activePiece.canMove(direction)){
            update(true);
            activePiece.move(direction);
            update(false);
            return true;
        }
        return false;
    }
    
    public void rotateCounterClockWise(){
        if(!activePiece.canRotate(false)){
            return;
        }   
        update(true);
        activePiece.rotateCounterClockWise();
        update(false);
    }

    public void rotateClockWise(){
        if(!activePiece.canRotate(true)){
            return;
        }   
        update(true);
        activePiece.rotateClockWise();
        update(false);
    }
    
    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }

    public void holdPiece(){
        update(true);
        if(heldPiece == null){
            heldPiece = activePiece;
            spawnPiece();
        }
        else{
            Piece temp = heldPiece;
            heldPiece = activePiece;
            activePiece = temp;
            activePiece.setY(1);
            update(false);
        }
    }

    private void update(boolean remove){
        for(Square s : activePiece.getSquares()){
            int x = activePiece.getX() + s.getOffsetX();
            int y = activePiece.getY() + s.getOffsetY();
            setSquare(x, y, remove ? null : s);
        }
    }

    private boolean isFullLine(int line){
        
        for(int col = 0; col < width; col++){
                if(get(col, line) == null){
                    break;
                }
                if(col == 9){
                    return true;
                }
            }
        return false;
    }

    public void print(){
        String result = "";
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                if(get(j, i) == null){
                    result += ".";
                }
                else if(get(j, i).getPiece() == activePiece){
                    result += "@";
                }
                else{
                    result += "#";
                }
            }
            result += "\n";
        }
        System.out.println(result);
    }

  
}
