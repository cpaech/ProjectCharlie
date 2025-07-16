package io.github.cpaech.charlie;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * This is the model part of the MVC - structure.
 * It stores all sorts of variables important to the game, ranging from
 * hardcoded ones to continously changing ones, like paddles positions or speed.
 */
public class Model {

    /**
    * Width of the game window in pixels.
    */
    public int screenWidth = 800;

    /**
    * Height of the game window in pixels.
    */
    public int screenHeight = 600;

    /**
    * Rectangle represents paddle of player A.
    */
    public Rectangle paddleA = new Rectangle();

    /**
    * Rectangle represents paddle of player B
    */
    public Rectangle paddleB = new Rectangle();

    /**
    * Height of both paddles in pixels.
    */
    public int paddleHeight = 100; 

    /**
    * Width of both paddles in pixels.
    */
    public int paddleWidth = 20;

    /**
    * Rectangle represents the ball.
    */
    public Rectangle ball = new Rectangle();

    /**
    * Velocity of the ball as a 2D vector.
    */
    public Vector2 ballVelocity = new Vector2();

    /**
    * The paddle the ball last collided with. This is used to avoid any further collisions with the same paddle, after the ball has already changed direction.
    * 0 = none
    * 1 = PaddleA
    * 2 = PaddleB
    */
    public int lastCollidedPaddle = 0; //0 = none; 1 = paddle A, 2 = paddle

    /**
    * Score counter for player A.
    */
    public int scoreA = 0;

    /**
    * Score counter for player B.
    */
    public int scoreB = 0;

    /**
    * Temporary position of the ball, prior to a collision check. Used to revert the position if necessary to avoid buggy behaviour caused by changing update rates.
    */
    Vector2 tempBallPosition = new Vector2(0, 0);

    /**
    * Background color of the game screen as a float array.
    */
    public float[] backgroundColor = {0.15f, 0.15f, 0.2f, 1f};

    /**
    * Basespeed of the paddle
    */
    public int paddleSpeed = 300;

    /**
    * Initial base speed of the ball
    */
    public float initialBallSpeed = 300.0f;

    /**
    * Maximum random speed added to the base speed of the ball
    */
    public float randomBallSpeed = 100.0f;

    /**
    * Squaresize of the Ball in px
    */
    public int ballSize = 20;
    
    /**
     * This tells the Model, View and MenuView if the menu or game should be visible.
     */
    public boolean homeMenuVisible = true;

    public String player1Info; // temporary storage used in the menu to display player info
}
