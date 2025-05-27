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
}
