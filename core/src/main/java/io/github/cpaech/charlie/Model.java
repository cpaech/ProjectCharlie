package io.github.cpaech.charlie;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


public class Model {
    /**
    * Is set in the lwjgl3 launcher at line 30; "setWindowedMode( 800, 600);". Dynamic resizing is possible but unnecessary for this game.
    */
    public int screenWidth = 800;
    /**
    * Is set in the lwjgl3 launcher at line 30; "setWindowedMode( 800, 600);". Dynamic resizing is possible but unnecessary for this game.
    */
    public int screenHeight = 600;
    /**
    * Implements paddle A as a rectangle.
    */
    public Rectangle paddleA = new Rectangle();
    /**
    * Implements paddle B as a rectangle.
    */
    public Rectangle paddleB = new Rectangle();
    /**
    * Sets the paddle height of both paddles.
    */
    public int paddleHeight = 100; 
    /**
    * Sets the paddle width of both paddles.
    */
    public int paddleWidth = 20;
    /**
    * Implements the ball as a rectangle.
    */
    public Rectangle ball = new Rectangle();
    /**
    * Implements paddle B as a rectangle.
    */
    public Vector2 ballVelocity = new Vector2();
    /**
    * Implements a score for player A and sets it to 0.
    */
    public int scoreA = 0;
    /**
    * Implements a score for player B and sets it to 0.
    */
    public int scoreB = 0;
    /**
    * Speed of the paddles in pixel per second.
    */
    public int paddleSpeed = 300;
    /**
    * Sets the color of the background.
    */
    public float[] backgroundColor = {0.15f, 0.15f, 0.2f, 1f};
}
