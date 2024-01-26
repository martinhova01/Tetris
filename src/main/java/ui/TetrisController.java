package ui;


import java.util.ArrayList;
import java.util.Collection;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import model.Piece;
import model.Rect;
import model.Square;
import model.Tetris;

public class TetrisController {

    private Tetris tetris;
    private double sideLength;
    private Timeline gameLoop;
    private Scene scene;
    private Collection<Rect> rects = new ArrayList<>();
    private Collection<Rect> heldPiece = new ArrayList<>();
    private Collection<Rect> nextPiece = new ArrayList<>();
    
    @FXML
    private AnchorPane gamePane, sidePane, heldPane, nextPane;

    @FXML
    private Label scoreLabel, comboLabel;


    public void init(Scene scene) {
        this.scene = scene;

        tetris = new Tetris(this);
        sideLength = gamePane.getPrefWidth() / tetris.getWidth();

            //creates the rect objects in the gameBoard
        for(int i = 0; i < tetris.getWidth(); i++){
            for(int j = 0; j < tetris.getHeight(); j++){
                Square s = tetris.get(i, j);
                Rect rect = new Rect(i * sideLength, j * sideLength, sideLength, sideLength, i, j);
                if(s == null){
                    rect.setFill(Color.GREY);
                }
                else{
                    rect.setFill(s.getPiece().getColor());
                }
                rect.setStroke(Color.BLACK);
                gamePane.getChildren().add(rect);
                rects.add(rect);
            }
        }
            //creates the rect object for the heldpiece display and the nextpiece display
        double sideLengthHeld = heldPane.getPrefWidth() / 4;
        for(int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                Rect rectHeld = new Rect(i * sideLengthHeld, j * sideLengthHeld, sideLengthHeld, sideLengthHeld, i, j);
                heldPiece.add(rectHeld);
                rectHeld.setFill(Color.GREY);
                rectHeld.setStroke(Color.BLACK);
                heldPane.getChildren().addAll(rectHeld);

                Rect rectNext = new Rect(i * sideLengthHeld, j * sideLengthHeld, sideLengthHeld, sideLengthHeld, i, j);
                nextPiece.add(rectNext);
                rectNext.setFill(Color.GREY);
                rectNext.setStroke(Color.BLACK);
                nextPane.getChildren().addAll(rectNext);
            }
        }

        tetris.spawnPiece();

        update();

        gameLoop = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            update();
        }));
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.play();

        this.scene.setOnKeyPressed(e -> {
            keyPressed(e);
        });
    }

    private void keyPressed(KeyEvent e) {

        if(e.getCode() == KeyCode.LEFT){
            tetris.movePiece(Tetris.LEFT);
        }
        else if(e.getCode() == KeyCode.RIGHT){
            tetris.movePiece(Tetris.RIGHT);
        }
        else if(e.getCode() == KeyCode.UP){
            tetris.rotateCounterClockWise();
        }
        else if(e.getCode() == KeyCode.Z){
            tetris.rotateClockWise();
        }
        else if (e.getCode() == KeyCode.DOWN){
            update();
        }
        else if (e.getCode() == KeyCode.SPACE){
            while(tetris.movePiece(Tetris.DOWN)){}
            update();
        }
        else if(e.getCode() == KeyCode.SHIFT){
            tetris.holdPiece();
        }
        

    }

    public void updateAll(){
        
        for(Rect r : rects){
            Square s = tetris.get(r.getIndexX(), r.getIndexY());
            if(s == null){
                r.setFill(Color.GREY);
            }
            else{
                r.setFill(s.getPiece().getColor());
            }
        }
            
    }

    public void update(){
        //update model
        if(!tetris.movePiece(Tetris.DOWN)){
            tetris.spawnPiece();
        }
    }

    public void updateGUI(Square s, int x, int y) {
        Rect r = rects.stream().filter(rect -> rect.getIndexX() == x && rect.getIndexY() == y).findAny().get();
        
        if(s == null){
            r.setFill(Color.GREY);
        }
        else{
            r.setFill(s.getPiece().getColor());
        }
    }

    public void updateHeldPieceDisplay(){

        for(Rect r : heldPiece){
            r.setFill(Color.GREY);
        }
        Piece p = tetris.getHeldPiece();
        for(Square s : p.getSquares()){
            int x =  1 + s.getOffsetX();
            int y =  1 + s.getOffsetY();
            Rect r = heldPiece.stream().filter(rect -> rect.getIndexX() == x && rect.getIndexY() == y).findAny().get();
            r.setFill(s.getPiece().getColor());
        }
        
    }

    public void updateNextPieceDisplay() {
        for(Rect r : nextPiece){
            r.setFill(Color.GREY);
        }
        Piece p = tetris.getNextPiece();
        for(Square s : p.getSquares()){
            int x =  1 + s.getOffsetX();
            int y =  1 + s.getOffsetY();
            Rect r = nextPiece.stream().filter(rect -> rect.getIndexX() == x && rect.getIndexY() == y).findAny().get();
            r.setFill(s.getPiece().getColor());
        }
    }

    public void updateLabels() {
        scoreLabel.setText("" + tetris.getTotalScore());
        comboLabel.setText("" + tetris.getComboCounter());
    }
    

}
