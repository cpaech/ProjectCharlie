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
    * This method initilizes the Model, Controller and View.
    * It sets them into relation with each other.
    * Overrides the method layed out by {@link com.badlogic.gdx.ApplicationListener#create()}.
    **/
    @Override
    public void create() {
        loadPreferences();
        // Initializes the model, view, and controller
        model = new Model();
        view = new View(model); // Temporarily pass null for the controller
        controller = new Controller(model, view.menuView);
        view.setController(controller);
    }

    private void loadPreferences() {    
        AppPreferences.getAppPreferences();
    }


    /**
    * This method is called every frame and is the main part of the update loop
    * It calls the Controller with the deltaTime and afterwards calls the view for a render
    * Overrides the method layed out by {@link com.badlogic.gdx.ApplicationListener#render()}.
    **/
    @Override
    public void render() {
        controller.render(Gdx.graphics.getDeltaTime());
        view.render();
    }

    /**
     * This method dispose of any previously allocated ressources.
     * Overrides the method layed out by {@link com.badlogic.gdx.ApplicationListener#dispose()}.
     */
    @Override
    public void dispose() {
        view.dispose();
    }
    
}
