package io.github.cpaech.charlie;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


public class Model {
    public int screenWidth = 800;
    public int screenHeight = 600; // are set in the lwjgl3 launcher at line 30; "setWindowedMode( 800, 600);". Dynamic resizing is possible but unnecary for this game.
    public Rectangle paddleA = new Rectangle();
    public Rectangle paddleB = new Rectangle();
    public int paddleHeight = 100;
    public int paddleWidth = 20;
    public Rectangle ball = new Rectangle();
    public Vector2 ballVelocity = new Vector2();
    public int scoreA = 0;
    public int scoreB = 0;
    public int paddleSpeed = 300; // Geschwindigkeit der Paddles in Pixel pro Sekunde
}
