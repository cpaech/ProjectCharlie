package io.github.cpaech.charlie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;

public class Controller {
    public Model model;
   
    public Controller(Model model) {
        this.model = model;
        modelwerteInitialisieren();
    }
    public void render(float delta) {
        // Update the game state based on user input and other factors
        // For example, move paddles, update ball position, check for collisions, etc.
        // This is where the game logic would go
        // Verhalten im Unendlichen des Balles

        if (model.ball.x + model.ball.width< 0) { // +width, damit der Ball komplett aus dem Spielfeld ist
            model.scoreB++; // player B scores a point
            resetBall();    // Ball zurücksetzen
        }
        if (model.ball.x > model.screenWidth) {
            model.scoreA++; // player A scores a point
            resetBall();    // Ball zurücksetzen
        }

        Vector2 tempBallPosition = new Vector2(model.ball.x, model.ball.y); // Position des Balls diesen Render speichern, um ihn bei Kollisionen zurückzusetzen. In model auslagern?
        // keeps the ball updated based on the delta time and the current velocity
        // Delta is the time since the last frame, used for smooth movement
        model.ball.x += model.ballVelocity.x * delta; // Ball in x-Richtung bewegen
        model.ball.y += model.ballVelocity.y * delta; // Ball in y-Richtung bewegen

        // Collision with Paddle A
        if (model.ball.overlaps(model.paddleA)) {
            model.ballVelocity.x *= -1.0f;                                      // x-Richtung umkehren
            model.ball.setPosition(tempBallPosition.x, tempBallPosition.y);     // ball zurücksetzen, da es sein kann, das durch unterschiedliche delta-Werte der Ball nach der nächsten Ballbewegung immernoch im Paddle wäre
            if(model.ball.overlaps(model.paddleA)){                             // der Ball kann nun immernoch im Paddle sein, wenn man mit dem Paddle in den Ball hinein fährt und Ballgeschwindigkeit.y<Paddlegeschwindigkeit
                if(model.ball.y < model.paddleA.y + model.paddleA.height / 2){  // Fallunterscheidung; ist man von oben/unten mit dem Paddle auf den Ball gefahren
                    model.ball.y = model.paddleA.y - model.ball.height;         // da dies nur eintreten kann, wenn der Ball zu kleine Geschwindigkeit.y hat um selbst aus dem Paddle auszutreten/in das Paddle hinein fährt, schieben wir den Ball vor dem Paddle her
                }else{
                    model.ball.y = model.paddleA.y + model.paddleA.height;
                }
            model.ballVelocity.x *= -1.0f;
            model.ball.x += model.ballVelocity.x * delta;                       // falls wir den Ball schieben müssen, bewegt er sich ungehindert in die x-Richtung weiter
            }
        }

        // Collision with Paddle B
        if (model.ball.overlaps(model.paddleB)) {
            model.ballVelocity.x *= -1.0f; // x-Richtung umkehren
            model.ball.setPosition(tempBallPosition.x, tempBallPosition.y);
            if(model.ball.overlaps(model.paddleB)){ // siehe Paddle A
                if(model.ball.y < model.paddleB.y + model.paddleB.height / 2){
                    model.ball.y = model.paddleB.y - model.ball.height;
                }else{
                    model.ball.y = model.paddleB.y + model.paddleB.height;
                }
            model.ballVelocity.x *= -1.0f;
            model.ball.x += model.ballVelocity.x * delta;
            }
        }

        inputHandling(); // Eingaben verarbeiten

        // Collision with Decke/Boden (Spielfeldgrenzen)
        if (model.ball.y <= 0 || model.ball.y + model.ball.height >= model.screenHeight) { // || steht für ODER, eins von beidem muss wahr sein
        // Wenn der Ball die obere oder untere Grenze des Spielfelds berührt, kehre die y-Richtung um
            model.ballVelocity.y *= -1.0f; // y-Richtung umkehren
            model.ball.setY(tempBallPosition.y); // Ball zurücksetzen, damit er bei unterschiedliche delta-Werten nicht in der Wand bleibt
        }
    }
    public void dispose() {
        // Dispose of any resources that need to be cleaned up
        // For example, textures, sounds, etc.
    }
    public void modelwerteInitialisieren(){
        model.paddleA.setSize(model.paddleWidth, model.paddleHeight);
        model.paddleB.setSize(model.paddleWidth, model.paddleHeight);
        model.paddleA.setPosition(0.0f, model.screenHeight / 2.0f - (model.paddleA.height / 2.0f));
        model.paddleB.setPosition(model.screenWidth - (model.paddleB.width), model.screenHeight / 2.0f - (model.paddleB.height / 2.0f));
        model.ball.setSize(20, 20);
        model.scoreA = 0;
        model.scoreB = 0;
        resetBall(); // Ball zurücksetzen
    }
    private void resetBall() {
        // Set the ball to the center of the screen and reset its velocity after a point is scored or the ball goes out of bounds
        model.ball.setPosition(model.screenWidth/2, model.screenHeight/2);  // x,y Ball in die Mitte setzen
        model.ballVelocity.set((float)(((int)(Math.random() * 2)) * 2 - 1) * 200.0f, (float)(((int)(Math.random() * 2)) * 2 - 1) * 200.0f + (float)Math.random() * 100.0f - 50.0f);  // Ballgeschwindigkeit zurücksetzen
    }

    public void inputHandling()
    {
        boolean isPressedW = Gdx.input.isKeyPressed(Keys.W);
        boolean isPressedS = Gdx.input.isKeyPressed(Keys.S);
        boolean isPressedUp = Gdx.input.isKeyPressed(Keys.UP);
        boolean isPressedDown = Gdx.input.isKeyPressed(Keys.DOWN);
        
        model.paddleA.y += (isPressedW ? 1 : 0) * model.paddleSpeed * Gdx.graphics.getDeltaTime(); // Paddle A nach oben bewegen
        model.paddleA.y -= (isPressedS ? 1 : 0) * model.paddleSpeed * Gdx.graphics.getDeltaTime(); // Paddle A nach unten bewegen
        model.paddleB.y += (isPressedUp ? 1 : 0) * model.paddleSpeed * Gdx.graphics.getDeltaTime(); // Paddle B nach oben bewegen
        model.paddleB.y -= (isPressedDown ? 1 : 0) * model.paddleSpeed * Gdx.graphics.getDeltaTime(); // Paddle B nach unten bewegen
        // Ensure paddles stay within the bounds of the screen
        if (model.paddleA.y < 0) {
            model.paddleA.y = 0; // Paddle A nicht unter den Bildschirm bewegen
        }
        if (model.paddleA.y + model.paddleA.height > model.screenHeight) {
            model.paddleA.y = model.screenHeight - model.paddleA.height; // Paddle A nicht über den Bildschirm bewegen
        }
        if (model.paddleB.y < 0) {
            model.paddleB.y = 0; // Paddle B nicht unter den Bildschirm bewegen
        }
        if (model.paddleB.y + model.paddleB.height > model.screenHeight) {
            model.paddleB.y = model.screenHeight - model.paddleB.height; // Paddle B nicht über den Bildschirm bewegen
        }
    }
}
