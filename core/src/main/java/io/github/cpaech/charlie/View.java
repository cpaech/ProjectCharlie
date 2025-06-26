package io.github.cpaech.charlie;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class View {
    /**
     * SpriteBatch is used to draw our Geometry on the screen (e.g. paddles, balls, etc.).
     * 
     * Usually a sprite batch is used to draw many textures of the same type (e.g. many paddles)
     * at once by collecting the geometry and then submitting it to the GPU in one go.
     * However, in this case we will use it to draw just two paddles and a single ball.
     */
    private final SpriteBatch batch;
    private final Texture paddleTexture;   //texture object for the paddle.
    private final Texture ballTexture;    //texture object for the ball.
    BitmapFont font;    //font for the Scoredisplay.

    private Model model; //the View only needs a reference to the Model to get the current state of the game.
    //later a refernce to the controller might be needed to handle user input from buttons (difficult because circular refernce).

    public View(Model model) {
        batch = new SpriteBatch(); //SpriteBatch. Necessary to draw things to the screen
        this.model = model;
        paddleTexture = new Texture("libgdx.png");
        ballTexture = new Texture("ball.png");
        font = new BitmapFont(); //a default font of 15pt
    }
  
    public void render() {
        //clear the screen with one color
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
      
        //begin drawing for the current frame
        batch.begin();
        batch.draw(paddleTexture, model.paddleB.x , model.paddleB.y, model.paddleB.width, model.paddleB.height);
        batch.draw(paddleTexture, model.paddleA.x, model.paddleA.y, model.paddleA.width, model.paddleA.height);
        font.draw(batch, model.scoreA + " : " + model.scoreB, model.screenWidth/2, model.screenHeight-40);
        batch.draw(ballTexture, model.ball.x, model.ball.y, model.ball.width, model.ball.height);
      
        // end drawing
        batch.end();
    }

   /**
     * This methods disposes of all graphical elements from memory
     */
    public void dispose() {
        paddleTexture.dispose();
        ballTexture.dispose();
        batch.dispose();
    }
}
