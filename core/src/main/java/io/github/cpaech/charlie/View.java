package io.github.cpaech.charlie;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class View {
    /**
     * SpriteBatch is used to draw our Geometry on the screen (e.g. paddles, balls, etc.).
     * Usually a sprite batch is used to draw many textures of the same type (e.g. many paddles)
     * at once by collecting the geometry and then submitting it to the GPU in one go.
     * However, in this case we will use it to draw just two paddles and a single ball.
     */
    private final SpriteBatch batch;
    private final Texture paddleTextureB;
    private final Texture paddleTextureA;
    private final Texture ballTexture;
    BitmapFont font;
    Model model;

    public View(Model model) {
        batch = new SpriteBatch(); //SpriteBatch. Necessary to draw things to the screen
        this.model = model;
        font = new BitmapFont();
        paddleTextureA = new Texture("paddle.png");
        paddleTextureB = new Texture("paddle.png");
        ballTexture = new Texture("ball.png");
        font = new BitmapFont();
    }

    public void render() {
        //clear the screen with one color
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        //begin drawing for the current frame
        batch.begin();
        batch.draw(paddleTextureA, model.paddleA.x , model.paddleA.y, model.paddleA.width, model.paddleA.height);
        batch.draw(paddleTextureB, model.paddleB.x, model.paddleB.y, model.paddleB.width, model.paddleB.height);
        font.draw(batch, model.scoreA + " : " + model.scoreB, 400, 560);
        batch.draw(ballTexture, model.ball.x, model.ball.y);

        // end drawing
        batch.end();
    }

   /**
     * This methods disposes of all graphical elements from memory
     */
    public void dispose() {
        paddleTextureA.dispose();
        paddleTextureB.dispose();
        ballTexture.dispose();
        batch.dispose();
    }
}
