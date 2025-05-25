package io.github.cpaech.charlie;

public class Controller {
    public Model model;
   
    public Controller(Model model) {
        this.model = model;
    }
    public void render(float delta) {
        // Update the game state based on user input and other factors
        // For example, move paddles, update ball position, check for collisions, etc.
        // This is where the game logic would go
        // Verhalten im Unendlichen des Balles
        if (model.ball.x < 0) {
            model.scoreB++; // player B scores a point
            resetBall();    // Ball zurücksetzen
        }
        if (model.ball.x > 800) {
            model.scoreA++; // player A scores a point
            resetBall();    // Ball zurücksetzen
        }
        // keeps the ball updated based on the delta time and the current velocity
        // Delta is the time since the last frame, used for smooth movement
        model.ball.x = model.ball.x * delta;
        model.ball.y = model.ball.y * delta;

        // Collision with Paddle A
        if (model.ball.overlaps(model.paddleA)) {
            model.ballVelocity.x *= -1.0f; // x-Richtung umkehren
            model.ballVelocity.y *= -1.0f; // y-Richtung umkehren
        }

        // Collision with Paddle B
        if (model.ball.overlaps(model.paddleB)) {
            model.ballVelocity.x *= -1.0f; // x-Richtung umkehren
            model.ballVelocity.y *= -1.0f; // y-Richtung umkehren
        }

        // Collision with Decke/Boden (Spielfeldgrenzen)
        if (model.ball.y <= 0 || model.ball.y + model.ball.height >= 600) { // || steht für ODER, eins von beidem muss wahr sein
        // Wenn der Ball die obere oder untere Grenze des Spielfelds berührt, kehre die y-Richtung um
            model.ballVelocity.y *= -1;
        }
    }
    public void dispose() {
        // Dispose of any resources that need to be cleaned up
        // For example, textures, sounds, etc.
    }
    public modelwerteInitialisieren(){
        model.paddleA.setSize(20, 100);
        model.paddleB.setSize(20, 100);
        model.paddleA.setPosition(0.0f, 600.0f / 2.0f - (paddleA.height / 2.0f));
        model.paddleB.setPosition(800.0f - (paddleB.width), 600.0f / 2.0f - (paddleB.height / 2.0f));
        model.ball.setSize(20, 20);
        model.ball.setPosition(400.0f, 300.0f);
        model.ScoreA = 0;
        model.ScoreB = 0;
    }
    private void resetBall() {
        // Set the ball to the center of the screen and reset its velocity after a point is scored or the ball goes out of bounds
        model.ball.setPosition(400, 300);  // x,y Ball in die Mitte setzen
        model.ballVelocity.set(200, 200);  // Ballgeschwindigkeit zurücksetzen
    }
}
