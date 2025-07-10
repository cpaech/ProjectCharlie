package io.github.cpaech.charlie;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


public class Model {
    public int screenWidth = 800;
    public int screenHeight = 600; // are set in the lwjgl3 launcher at line 30; "setWindowedMode( 800, 600);". Dynamic resizing is possible but unnecary for this game.
    public Rectangle paddleA = new Rectangle();
    public Rectangle paddleB = new Rectangle();
    public Rectangle ball = new Rectangle();
    public Vector2 ballVelocity = new Vector2();
    public int lastCollidedPaddle = 0; //0 = none; 1 = paddle A, 2 = paddle B; this is used to determine which paddle was last hit by the ball, so that the ball can be moved back to its position before the collision if it hits the paddle again.
    public int scoreA = 0;
    public int scoreB = 0;
    Vector2 tempBallPosition = new Vector2(0, 0); // Hier wird die Position des Balls bevor der Bewegung peichern, um diese bei einer Kollisionen zurückzusetzen.
    public int paddleSpeed = 300; // Geschwindigkeit der Paddles in Pixel pro Sekunde
    public float[] backgroundColor = {0.15f, 0.15f, 0.2f, 1f};
}
