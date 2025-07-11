package io.github.cpaech.charlie;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    /** Reference to the Model containing all important Values and States**/
    private Model model;
    /** Reference to the View, handling all the rendering work**/
    private View view;
    /** Reference to the Controller handling all gamelogic**/
    private Controller controller;

    /**
    This method initilizes the Model, Controller and View.
    It sets them into relation with each other.
    **/
    @Override
    public void create() {
        // Initializes the model, view, and controller
        model = new Model();
        view = new View(model);
        controller = new Controller(model);
    }

    /**
    This method is called every frame and is the main part of the update loop
    It calls the Controller with the deltaTime and afterwards calls the view for a render
    **/
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
