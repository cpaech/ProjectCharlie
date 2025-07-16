package io.github.cpaech.charlie;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private Model model;
    private View view;
    private Controller controller;
    @Override
    public void create() {
        loadPreferences();
        // Initializes the model, view, and controller
        model = new Model();
        view = new View(model);
        controller = new Controller(model);
    }

    private void loadPreferences() {    
        AppPreferences.getAppPreferences();
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
