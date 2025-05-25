package io.github.cpaech.charlie;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private Model model;
    private View view;
    private Controller controller;
    @Override
    public void create() {
        // Initializes the model, view, and controller
        model = new Model();
        view = new View(model);
        controller = new Controller(model);
    }

    @Override
    public void render() {
        controller.render(Gdx.graphics.getDeltaTime());
        view.render();
    }

    @Override
    public void dispose() {
        view.dispose();
    }
}
