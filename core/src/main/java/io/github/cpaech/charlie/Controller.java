package io.github.cpaech.charlie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

/**
 * This is the Controller. It updates the gameStates and values 
 * and is responsible for the game logic, as well as menu view logic.
 */
public class Controller extends ChangeListener{

    /**
     * Reference to the menu part of the view
     */
    private MenuView menuView;
    
    /**
     * Reference to the global model
     */
    private Model model;
   
    /**
     * Initilizes the Controller and calls @see io.github.cpaech.charlie.Controller#modelwerteInitialisieren
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
    * This is where the game logic would go. It skips running, when the home menu is displayed.
    * @param delta This is the time in seconds since the last call to this method
    */
    public void render(float delta) {
        if (!model.homeMenuVisible) {
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
            
            if (model.scoreA > AppPreferences.getAppPreferences().getPlayerHighScore(model.player1Name)) {
                    AppPreferences.getAppPreferences().setPlayerHighScore(model.player1Name, model.scoreA);
            }
            if (model.scoreB > AppPreferences.getAppPreferences().getPlayerHighScore(model.player2Name)) {
                    AppPreferences.getAppPreferences().setPlayerHighScore(model.player2Name, model.scoreB);
            }

            inputHandling(); //PaddleMovement and Collisioncheck

            // Collision with top and bottom
            if (model.ball.y <= 0 || model.ball.y + model.ball.height >= model.screenHeight) {
                model.ballVelocity.y *= -1.0f; // y-Richtung umkehren
                model.ball.setY(model.tempBallPosition.y); 
            }
        }
    }

     /**
     * This method gets called whenever something happens (eg. buttonclick) in the view (and through the view the menuView).
     */
    @Override
    public void changed(ChangeEvent event, Actor actor) {
        System.out.println("Button Pressed: " + actor.getName());

        if (!model.homeMenuVisible) {return;}

        if(actor.getName().equals("StartGameButton")) {
            if(model.player1Name == null || model.player2Name == null) {
                menuView.errorLabel.setText("Please enter player Names first!");
            }
            else{
                model.homeMenuVisible = false;
                resetBall();
                model.scoreA = 0;
                model.scoreB = 0;
            }
        }

        if(actor.getName().equals("Bot1LoginButton")) {
            model.player1Name = "Computer 1";
            model.playerABot = true;
            menuView.errorLabel.setText("Player 1 is controlled by the computer");
        }

        if(actor.getName().equals("Bot2LoginButton")) {
            model.player2Name = "Computer 2";
            model.playerBBot = true;
            menuView.errorLabel.setText("Player 2 is controlled by the computer");
        }

        if(actor.getName().equals("LoginPlayer1Button")) {
            
            model.playerABot = false;
            model.player1Name = menuView.player1NameField.getText();
            
            if(AppPreferences.getAppPreferences().getPlayerHighScore(model.player1Name) == 0){
                model.playerInfo = model.player1Name + ". This is a new Player with no highscore yet.";
            } 
            else {
                model.playerInfo = model.player1Name + ". Your current highscore is: " + AppPreferences.getAppPreferences().getPlayerHighScore(model.player1Name);
            }

            menuView.errorLabel.setText("Player 1 logged in as: " + model.playerInfo);
        }

        if(actor.getName().equals("LoginPlayer2Button")) {
            
            model.playerBBot = false;
            model.player2Name = menuView.player2NameField.getText();
            
            if(AppPreferences.getAppPreferences().getPlayerHighScore(model.player2Name) == 0) {
                model.playerInfo = model.player2Name + ". This is a new Player with no highscore yet.";
            } 
            else {
                model.playerInfo = model.player2Name + ". Your current highscore is: " + AppPreferences.getAppPreferences().getPlayerHighScore(model.player2Name);
            }

            menuView.errorLabel.setText("Player 2 logged in as: " + model.playerInfo);
        }
    }

    /**
     * This method disposes of any allocated ressources like textures of fonts. But there aren't any ;)
     */
    public void dispose() {
    }

    /**
     * This initializes values of the model
     * For example paddle size or position. It also calls resetBall().
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
     * and assigns it a random velocity. The last collided paddle is set to 0/none.
     */
    private void resetBall() {
        model.ballVelocity.set(0, 0);
        model.ball.setPosition(model.screenWidth / 2.0f - model.ball.width / 2.0f, model.screenHeight / 2.0f - model.ball.height / 2.0f);
        model.lastCollidedPaddle = 0;

        com.badlogic.gdx.utils.Timer.schedule(                     // Schedule the ball reset after 1 second
            new com.badlogic.gdx.utils.Timer.Task() {
                @Override
                public void run() {                                // Reset the ball's position and velocity
                        setRandomBallSpeed();
                }
            }, 0.8f);                                  // Delay of 0.8 seconds
    }

    public void setRandomBallSpeed() {                              //this generates a random angle (between 45 degreees up and down on both sides) for the ball
        int arc = (int) (Math.random() * 90 - 45);
        System.out.println("Initial arc: " + arc);

        float xSpeed = 0;
        if (Math.abs(arc) % 2 == 0) {
            xSpeed = -(float) Math.cos(arc * Math.PI / 180);
        } else {
            xSpeed = (float) Math.cos(arc * Math.PI / 180);
        }

        model.ballVelocity.set(xSpeed * model.BallSpeed, (float) (Math.sin(arc * Math.PI / 180) * model.BallSpeed));
        System.out.println("Initial ball speed: " + model.ballVelocity);
    }

    /**
     * This moves the paddles up or down, based on userinput
     */
    public void inputHandling()
    {   
        if(Gdx.input.isKeyJustPressed(Keys.ESCAPE)){
            model.homeMenuVisible = true;
            menuView.player1NameField.setText("");
            menuView.player2NameField.setText("");
            menuView.errorLabel.setText("");
        }
        
        boolean isPressedW = Gdx.input.isKeyPressed(Keys.W);
        boolean isPressedS = Gdx.input.isKeyPressed(Keys.S);
        boolean isPressedUp = Gdx.input.isKeyPressed(Keys.UP);
        boolean isPressedDown = Gdx.input.isKeyPressed(Keys.DOWN);
        
        if(model.playerABot)
        {
            if(model.paddleA.getCenter(new Vector2()).y > model.ball.getCenter(new Vector2()).y) {
                isPressedW = false;
                isPressedS = true;
            }
            else {
                isPressedW = true;
                isPressedS = false;
            }
        }
        if(model.playerBBot)
        {
            if(model.paddleB.getCenter(new Vector2()).y > model.ball.getCenter(new Vector2()).y) {
                isPressedUp = false;
                isPressedDown = true;
            }
            else {
                isPressedUp = true;
                isPressedDown = false;
            }
        }
        
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

