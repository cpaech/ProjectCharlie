package io.github.cpaech.charlie;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


public class Model {
    public int screenWidth = 800;
    public int screenHeight = 600; // are set in the lwjgl3 launcher at line 30; "setWindowedMode( 800, 600);". Dynamic resizing is possible but unnecary for this game.
    public Rectangle paddleA = new Rectangle(); // implements paddle A as a rectangle
    public Rectangle paddleB = new Rectangle(); // implements paddle B as a rectangle
    public int paddleHeight = 100; // sets the paddle height of both paddles
    public int paddleWidth = 20; // sets the paddle width of both paddles
    public Rectangle ball = new Rectangle(); // implements the ball as a rectangle
    public Vector2 ballVelocity = new Vector2();
    public int scoreA = 0; // implements a score for player A and sets it to 0
    public int scoreB = 0; // implements a score for player A and sets it to 0
    public int paddleSpeed = 300; // speed of the paddles in pixel per second
    public float[] backgroundColor = {0.15f, 0.15f, 0.2f, 1f}; // sets the color of the background
}
