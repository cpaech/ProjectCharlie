package io.github.cpaech.charlie;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


public class Model {
    public Rectangle paddleA = new Rectangle();
    public Rectangle paddleB = new Rectangle();
    public Rectangle ball = new Rectangle();
    public Vector2 ballVelocity = new Vector2();
    public int scoreA = 0;
    public int scoreB = 0;

    public Model() {
        ball.x = 20;
        ball.y = 30;
        paddleA.set(20, 200, 20, 100);
        paddleB.set(760, 200, 20, 100);
    }
}
