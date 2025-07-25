package io.github.cpaech.charlie;

import java.lang.reflect.Array;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 * The View is the part responsible for drawing all sorts of graphics to the screen.
 */
public class View {

    /**
     * TextureAtlas containing numbers to display the score.
     */
    private TextureAtlas numbersAtlas;

    /**
     * SpriteBatch is used to draw our Geometry on the screen (e.g. paddles, balls, etc.)
     */
    private final SpriteBatch batch;

    /**
     * Texture allocated for the paddles
     */
    private final Texture paddleTexture;

    /**
     * Texture for the background of the actual game.
     */
    private final Texture pongBackground2;
    
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
    Controller mvcController;

    /**
     * libgdx Array containing all the numbers from the numbersAtlas as sprites.
     */
    private com.badlogic.gdx.utils.Array<Sprite> numberSprites;

    MenuView menuView;

    /**
     * @param model Reference to the model by Main
     * @param mvcController Controller passed by {@link Main}
     */

    public View(Model model) {
        this.model = model;
        menuView = new MenuView(model);
        
        numbersAtlas = new TextureAtlas("numbers.txt");
        numberSprites = numbersAtlas.createSprites();

        pongBackground2 = new Texture("PongBackground2.png");
        batch = new SpriteBatch(); 
        paddleTexture = new Texture("libgdx.png");
        ballTexture = new Texture("ball.png");
        font = new BitmapFont();
    }

    public void setController(Controller mvcController) {
        this.mvcController = mvcController;
        menuView.setController(mvcController);
    }
    
    /**
     * This method gets called every frame to redraw the screen with all its elements.
     */
    public void render() {
        // clear the screen with one color
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        // begin drawing
        batch.begin();
        // wether to draw the home menu or game screen
        if (model.homeMenuVisible) {
            menuView.render(batch);
        } 
        else {
            batch.draw(pongBackground2, 0, 0);
            batch.draw(paddleTexture, model.paddleB.x, model.paddleB.y, model.paddleB.width,
                    model.paddleB.height);
            batch.draw(paddleTexture, model.paddleA.x, model.paddleA.y, model.paddleA.width,
                    model.paddleA.height);
            //font.draw(batch, model.scoreA + " : " + model.scoreB, model.screenWidth/2, model.screenHeight - 20);
            font.draw(batch,"" + model.player1Name, 10, model.screenHeight - 40);
            font.draw(batch, "Highscore: " + AppPreferences.getAppPreferences().getPlayerHighScore(model.player1Name), 10, 540);
            font.draw(batch,"" + model.player2Name, model.screenWidth - model.player2Name.length()*9 - 10, model.screenHeight - 20);
            font.draw(batch, "Highscore: " + AppPreferences.getAppPreferences().getPlayerHighScore(model.player2Name), model.screenWidth - 90, model.screenHeight - 40);
            batch.draw(ballTexture, model.ball.x, model.ball.y, model.ball.width, model.ball.height);
            batch.draw(numberSprites.get(Math.min((model.scoreA - (model.scoreA % 10)) / 10, 9)), 165, 530);
            batch.draw(numberSprites.get(model.scoreA % 10), 200, 530);
            
            batch.draw(numberSprites.get(Math.min((model.scoreB - (model.scoreB % 10)) / 10, 9)), 565, 530);
            batch.draw(numberSprites.get(model.scoreB % 10), 600, 530);

        }
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
        numbersAtlas.dispose();
    }
    
}
