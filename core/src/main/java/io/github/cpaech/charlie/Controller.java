package io.github.cpaech.charlie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Rectangle;

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

        model.tempBallPosition.set(model.ball.x, model.ball.y); // Speichern der Ballposition vor der Bewegung, um sie bei Kollisionen zurückzusetzen
        // keeps the ball updated based on the delta time and the current velocity
        // Delta is the time since the last frame, used for smooth movement
        model.ball.x += model.ballVelocity.x * delta; // Ball in x-Richtung bewegen
        model.ball.y += model.ballVelocity.y * delta; // Ball in y-Richtung bewegen

        if (model.ball.overlaps(model.paddleA) && (model.lastCollidedPaddle == 2 || model.lastCollidedPaddle == 0)) { // Überprüfen, ob der Ball das Paddle berührt und ob der Ball sich in der Höhe des Paddles befindet. / 2 damit dieser Kollisionsfall nicht in extremfällen eintritt die bei einer niedrigen Renderrate Visuell sehr unwahrscheinlich erscheinen
            model.lastCollidedPaddle = 1; // Paddle A ist das letzte Paddle, mit dem der Ball kollidiert ist
            model.ballVelocity.x *= -1.0f; // x-Richtung umkehren
        }
        if (model.ball.overlaps(model.paddleB) && (model.lastCollidedPaddle == 1 || model.lastCollidedPaddle == 0)) { // Überprüfen, ob der Ball das Paddle berührt und ob der Ball sich in der Höhe des Paddles befindet. / 2 damit dieser Kollisionsfall nicht in extremfällen eintritt die bei einer niedrigen Renderrate Visuell sehr unwahrscheinlich erscheinen
            model.lastCollidedPaddle = 2; // Paddle B ist das letzte Paddle, mit dem der Ball kollidiert ist
            model.ballVelocity.x *= -1.0f; // x-Richtung umkehren
        }
        inputHandling(); // Eingaben verarbeiten
        if (model.ball.overlaps(model.paddleA) && (model.lastCollidedPaddle == 2 || model.lastCollidedPaddle == 0)) { // Überprüfen, ob der Ball das Paddle A berührt
            model.lastCollidedPaddle = 1; // Paddle A ist das letzte Paddle, mit dem der Ball kollidiert ist
            model.ballVelocity.x *= -1.0f; // x-Richtung umkehren
            model.ball.x = model.paddleA.x + model.paddleA.width; // Ball auf die rechte Seite des Paddles setzen, damit er nicht in der Wand bleibt
        }
        else if (model.ball.overlaps(model.paddleB) && (model.lastCollidedPaddle == 1 || model.lastCollidedPaddle == 0)) { // Überprüfen, ob der Ball das Paddle A berührt
            model.lastCollidedPaddle = 2; // Paddle B ist das letzte Paddle, mit dem der Ball kollidiert ist
            model.ballVelocity.x *= -1.0f; // x-Richtung umkehren
            model.ball.x = model.paddleB.x - model.ball.width; // Ball auf die rechte Seite des Paddles setzen, damit er nicht in der Wand bleibt
        }
        // Collision with Decke/Boden (Spielfeldgrenzen)
        if (model.ball.y <= 0 || model.ball.y + model.ball.height >= model.screenHeight) { // || steht für ODER, eins von beidem muss wahr sein
        // Wenn der Ball die obere oder untere Grenze des Spielfelds berührt, kehre die y-Richtung um
            model.ballVelocity.y *= -1.0f; // y-Richtung umkehren
            model.ball.setY(model.tempBallPosition.y); // Ball zurücksetzen, damit er bei unterschiedliche delta-Werten nicht in der Wand bleibt
        }
    }
    public void dispose() {
        // Dispose of any resources that need to be cleaned up
        // For example, textures, sounds, etc.
    }

    public void isPaddleNowOverBall(Rectangle paddle, float delta) {
        if(model.ball.overlaps(paddle)){                             // der Ball kann nun immernoch im Paddle sein, wenn man mit dem Paddle in den Ball hinein fährt und Ballgeschwindigkeit.y<Paddlegeschwindigkeit
            if(model.ball.y < paddle.y + paddle.height / 2){         // Fallunterscheidung; ist man von oben/unten mit dem Paddle auf den Ball gefahren
                model.ball.y = paddle.y - model.ball.height;         // da dies nur eintreten kann, wenn der Ball zu kleine Geschwindigkeit.y hat um selbst aus dem Paddle auszutreten/in das Paddle hinein fährt, schieben wir den Ball vor dem Paddle her
                if(model.ballVelocity.y < 0.0f){                     // wenn der Ball auf die Untere Hälfte des Paddles trifft und sich nach unten bewegt, muss die Umdrehung der y-Richtung aus ballCollisionWithPaddle() rückgängig gemacht werden. Genauso für den Fall das der Ball auf die obere Hälfte des Paddles trifft und sich nach oben bewegt
                    model.ballVelocity.y = -model.ballVelocity.y;
                }
            }else{
                model.ball.y = paddle.y + paddle.height;
                if(model.ballVelocity.y < 0.0f){
                    model.ballVelocity.y = -model.ballVelocity.y;
                }
            }
        }
    }

    public void modelwerteInitialisieren(){
        model.paddleA.setSize(20, 100);
        model.paddleB.setSize(20, 100);
        model.paddleA.setPosition(0.0f, model.screenHeight / 2.0f - (model.paddleA.height / 2.0f));
        model.paddleB.setPosition(model.screenWidth - (model.paddleB.width), model.screenHeight / 2.0f - (model.paddleB.height / 2.0f));
        model.ball.setSize(20, 20);
        model.scoreA = 0;
        model.scoreB = 0;
        resetBall(); // Ball zurücksetzen
    }
    private void resetBall() {
        model.lastCollidedPaddle = 0; // Letztes Paddle zurücksetzen, damit der Ball nicht sofort wieder zurückprallt
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
        if (model.paddleA.y + model.paddleA.height > 600) {
            model.paddleA.y = 600 - model.paddleA.height; // Paddle A nicht über den Bildschirm bewegen
        }
        if (model.paddleB.y < 0) {
            model.paddleB.y = 0; // Paddle B nicht unter den Bildschirm bewegen
        }
        if (model.paddleB.y + model.paddleB.height > 600) {
            model.paddleB.y = 600 - model.paddleB.height; // Paddle B nicht über den Bildschirm bewegen
        }
    }
}

