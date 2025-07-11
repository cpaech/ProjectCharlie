package io.github.cpaech.charlie;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 * The View is the part responsible for drawing all sorts of graphics to the screen.
 */
public class View {

    /**
     * SpriteBatch is used to draw our Geometry on the screen (e.g. paddles, balls, etc.)
     */
    private final SpriteBatch batch;

    /**
     * Texture allocated for the paddles
     */
    private final Texture paddleTexture;
    
    /**
     * Texture allocated for the ball
     */
    private final Texture ballTexture;

    /**
     * A simple/standard bitmap font used to draw the the score
     */
    private BitmapFont font;

    /**
     * Reference to the model. This reference is used to fetch values needed in order to draw
     * graphics to the screen.
     */
    private Model model; 

    /**
     * This loads and allocates all required graphical ressources.
     * @param model Reference to the model by Main
     */
    public View(Model model) {
        this.model = model;

        batch = new SpriteBatch(); 
        paddleTexture = new Texture("libgdx.png");
        ballTexture = new Texture("ball.png");
        font = new BitmapFont();
    }
    
    /**
     * This method gets called every frame to redraw the screen with all its elements.
     */
    public void render() {
        ScreenUtils.clear(model.backgroundColor[0], model.backgroundColor[1], model.backgroundColor[2], model.backgroundColor[3]);
      
        batch.begin();
        
        batch.draw(paddleTexture, model.paddleB.x , model.paddleB.y, model.paddleB.width, model.paddleB.height);
        batch.draw(paddleTexture, model.paddleA.x, model.paddleA.y, model.paddleA.width, model.paddleA.height);
        batch.draw(ballTexture, model.ball.x, model.ball.y, model.ball.width, model.ball.height);

        font.draw(batch, model.scoreA + " : " + model.scoreB, model.screenWidth/2, model.screenHeight-40);
      
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
