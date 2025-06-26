package io.github.cpaech.charlie;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


public class Model {
    public Rectangle paddleA = new Rectangle(); // Linkes Paddle wird als Rechteck implementiert
    public Rectangle paddleB = new Rectangle(); // Rechtes Paddle wird als Rechteck implementiert
    public Rectangle ball = new Rectangle(); // Ball wird als Rechteck implementiert
    public Vector2 ballVelocity = new Vector2(); //
    public int scoreA = 0; // Attribut mit dem die Anzahl der Punkte von Spieler A gezählt werden
    public int scoreB = 0; // Attribut mit dem die Anzahl der Punkte von Spieler B gezählt werden
    public int paddleSpeed = 300; // Geschwindigkeit der Paddles in Pixel pro Sekunde
}
