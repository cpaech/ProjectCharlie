package io.github.cpaech.charlie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

/**
 * This is the Controller. It updates the gameStates and values 
 * and is responsible for the game logic
 */
public class Controller extends ChangeListener{

    private MenuView menuView;
    /**
     * Reference to the global model
     */
    private Model model;
   
    /**
     * Initilizes the Controller and calls @see io.github.cpaech.charlie.Controller
     * @param model Reference to the global model
    */
    public Controller(Model model, MenuView menuView){
        this.menuView = menuView;
        this.model = model;
        modelwerteInitialisieren();
    }

    /**
    * Update the game state based on user input and other factors
    * For example, move paddles, update ball position, check for collisions, etc.
    * This is where the game logic would go
    * @param delta This is the time in seconds since the last call to this method
    */
    public void render(float delta) {
        if (model.ball.x + model.ball.width < 0) { //check right side of ball against left wall of playingfield
            model.scoreB++;
            resetBall();
        }
        if (model.ball.x > model.screenWidth) { //check left side of ball against right wall of playingfield
            model.scoreA++;
            resetBall();
        }

        model.tempBallPosition.set(model.ball.x, model.ball.y); 
        
        model.ball.x += model.ballVelocity.x * delta;
        model.ball.y += model.ballVelocity.y * delta;

        if (model.ball.overlaps(model.paddleA) && (model.lastCollidedPaddle == 2 || model.lastCollidedPaddle == 0)) { 
            model.lastCollidedPaddle = 1; // Paddle A ist now lastCollidedPaddle
            model.ballVelocity.x *= -1.0f; 
        }
        if (model.ball.overlaps(model.paddleB) && (model.lastCollidedPaddle == 1 || model.lastCollidedPaddle == 0)) { 
            model.lastCollidedPaddle = 2; // Paddle B is now the lastCollidedPaddle
            model.ballVelocity.x *= -1.0f; 
        }

        inputHandling(); //PaddleMovement and Collisioncheck

        // Collision with top and bottom
        if (model.ball.y <= 0 || model.ball.y + model.ball.height >= model.screenHeight) {
            model.ballVelocity.y *= -1.0f; // y-Richtung umkehren
            model.ball.setY(model.tempBallPosition.y); 
        }
    }

     /**
     * This method gets called whenever something happens (eg. buttonclick) in the view.
     */

    @Override
    public void changed (ChangeEvent event, Actor actor) {
        System.out.println("Button Pressed: " + actor.getName());

        if(actor.getName().equals("StartGameButton")) {
            if(model.player1Name == null){menuView.errorField.setText("Please enter a name first!");}
            else{
                model.homeMenuVisible = false;
            }
        }
        if(actor.getName().equals("SoloPlayButton")) {
            if(model.player1Name == null){menuView.errorField.setText("Please enter a name first!");}
            else{
                model.homeMenuVisible = false;
            }
        }
        if(actor.getName().equals("LoginButton")) {
            model.player1Name = menuView.usernameField.getText();
            menuView.errorField.setText("Logged in as: " + model.player1Name);
            System.out.println("Player name set to: " + model.player1Name);
            if(AppPreferences.getAppPreferences().getPlayerHighScore(model.player1Name) == 0){
                model.player1Info = model.player1Name + ". This is a new Player with no highscore yet.";
            } else {
                model.player1Info = model.player1Name + ". Your current highscore is: " + AppPreferences.getAppPreferences().getPlayerHighScore(model.player1Name);
            }
            menuView.errorField.setText("Logged in as: " + model.player1Info);
        }
    }

    /**
     * This method disposes of any allocated ressources like textures of fonts
     */
    public void dispose() {
    }

    /**
     * This initializes values of the model
     * For example paddle size or position
     */
    public void modelwerteInitialisieren() {
        model.paddleA.setSize(model.paddleWidth, model.paddleHeight);
        model.paddleB.setSize(model.paddleWidth, model.paddleHeight);
        model.paddleA.setPosition(0.0f, model.screenHeight / 2.0f - (model.paddleA.height / 2.0f));
        model.paddleB.setPosition(model.screenWidth - (model.paddleB.width), model.screenHeight / 2.0f - (model.paddleB.height / 2.0f));
        
        model.ball.setSize(model.ballSize, model.ballSize);
        
        model.scoreA = 0;
        model.scoreB = 0;
        
        resetBall();
    }

    /**
     * This resets the Ball to the middle of the screen
     * and assigns it a random velocity
     */
    private void resetBall() {
        model.lastCollidedPaddle = 0;
        model.ball.setPosition(model.screenWidth/2, model.screenHeight/2);
        model.ballVelocity.set(model.initialBallSpeed + (float)Math.random() * model.randomBallSpeed, model.initialBallSpeed + (float)Math.random() * model.randomBallSpeed);
    }

    /**
     * This moves the paddles up or down, based on userinput
     */
    public void inputHandling()
    {
        boolean isPressedW = Gdx.input.isKeyPressed(Keys.W);
        boolean isPressedS = Gdx.input.isKeyPressed(Keys.S);
        boolean isPressedUp = Gdx.input.isKeyPressed(Keys.UP);
        boolean isPressedDown = Gdx.input.isKeyPressed(Keys.DOWN);
        
        model.paddleA.y += (isPressedW ? 1 : 0) * model.paddleSpeed * Gdx.graphics.getDeltaTime();
        model.paddleA.y -= (isPressedS ? 1 : 0) * model.paddleSpeed * Gdx.graphics.getDeltaTime(); 
        model.paddleB.y += (isPressedUp ? 1 : 0) * model.paddleSpeed * Gdx.graphics.getDeltaTime(); 
        model.paddleB.y -= (isPressedDown ? 1 : 0) * model.paddleSpeed * Gdx.graphics.getDeltaTime();
        
        if (model.paddleA.y < 0) {
            model.paddleA.y = 0;
        }
        if (model.paddleA.y + model.paddleA.height > model.screenHeight) {
            model.paddleA.y = model.screenHeight - model.paddleA.height;
        }
        if (model.paddleB.y < 0) {
            model.paddleB.y = 0;
        }
        if (model.paddleB.y + model.paddleB.height > model.screenHeight) {
            model.paddleB.y = model.screenHeight - model.paddleB.height;
        }

        //Collisionchecks if the Paddle moved into the Ball
        if (model.ball.overlaps(model.paddleA) && (model.lastCollidedPaddle == 2 || model.lastCollidedPaddle == 0)) { 
            model.lastCollidedPaddle = 1; // Paddle A is now lastCollidedPaddle
            model.ballVelocity.x *= -1.0f; 
            model.ball.x = model.paddleA.x + model.paddleA.width; //Move in front of Paddle
        }
        else if (model.ball.overlaps(model.paddleB) && (model.lastCollidedPaddle == 1 || model.lastCollidedPaddle == 0)) {
            model.lastCollidedPaddle = 2; // Paddle B is now the lastCollidedPaddle
            model.ballVelocity.x *= -1.0f; 
            model.ball.x = model.paddleB.x - model.ball.width; // Move in front of Paddle
        }
    }
    
}

