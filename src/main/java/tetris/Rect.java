package tetris;

import javafx.scene.shape.Rectangle;

public class Rect extends Rectangle {
    
    private int indexX;
    private int indexY;

    public Rect(double x, double y, double width, double height, int indexX, int indexY){
        super(x, y, width, height);
        this.indexX = indexX;
        this.indexY = indexY;
    }

    public int getIndexX() {
        return indexX;
    }

    public int getIndexY() {
        return indexY;
    }
}
