package io.github.cpaech.charlie;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


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
    * Score counter for player A.
    */
    public int scoreA = 0;
    /**
    * Score counter for player B.
    */
    public int scoreB = 0;
    /**
    * Speed of the paddles in pixel per second.
    */
    public int paddleSpeed = 300;
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
}
