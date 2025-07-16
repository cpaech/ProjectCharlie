package io.github.cpaech.charlie;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * This is the part of the view structure which takes care of rendering 
 * the menu with scoreboard and everything. From here the player will be able to start a game.
 */
public class MenuView {

    /**
     * This is the container for all ui elements. Required by libgdx.
     */
    private Stage stage;
    /**
     * Example of a button with text.
     */
    TextButton loginPlayer1Button;
    TextButton loginPlayer2Button;

    TextButton startGameButton;

    TextField player1NameField;
    TextField player2NameField;


    TextField errorField;
    /**
     * Style of the button (font, color, image, etc...).
     */
    TextButtonStyle textButtonStyle;

    TextField.TextFieldStyle textFieldStyle;
    /**
     * The Font used for all UI-elements.
     */
    BitmapFont font;
    /**
     * This is a button skin, containing all the images of each state for the button.
     */
    Skin skin;
    /**
     * Textureatlas containing all the imagery for the button graphics.
     */
    TextureAtlas buttonAtlas;
    Model model;
    Controller controller;
    /**
     * This method sets up all nesessary objects for the UI.
     * @param mvcModel Model passed by {@link Main}
     * @param mvcController Controller passed by {@link Main}, responsible for input processing.
     */
    public MenuView(Model model)
    {
        this.model = model;

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        font = new BitmapFont();
        skin = new Skin();
        buttonAtlas = new TextureAtlas(Gdx.files.internal("buttons/button.pack"));
        skin.addRegions(buttonAtlas);
        textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.up = skin.getDrawable("buttonnormal");
        textButtonStyle.down = skin.getDrawable("buttonpressed");
        textButtonStyle.checked = skin.getDrawable("buttonnormal");
        textButtonStyle.fontColor = Color.BLACK;

        textFieldStyle = new TextField.TextFieldStyle();
        textFieldStyle.font = font;
        textFieldStyle.fontColor = Color.RED;

        loginPlayer1Button = newTextButton(loginPlayer1Button, "LoginPlayer1Button", "Login Player 1", 200, 200, 120, 50);
        player1NameField = newTextField(player1NameField, "player1NameField", "Enter Player 1 Name", 200, 300, 120, 50);
        loginPlayer2Button = newTextButton(loginPlayer2Button, "LoginPlayer2Button", "Login Player 2", 400, 200, 135, 50);
        player2NameField = newTextField(player2NameField, "player2NameField", "Enter Player 2 Name", 400, 300, 135, 50);
        errorField = newTextField(errorField, null, null, 200, 0, 600, 50);
        startGameButton = newTextButton(startGameButton, "StartGameButton", "Start Game", 300, 100, 120, 50);
    }

    public TextField newTextField(TextField newField, String name, String messageText, float x, float y, float width, float height)
    {
        newField = new TextField("", textFieldStyle);
        newField.setMessageText(messageText);
        newField.setBounds(x, y, width, height);
        newField.setName(name);
        stage.addActor(newField);
        return newField;
    }

    public TextButton newTextButton(TextButton newButton, String name, String text, float x, float y, float width, float height)
    {
        newButton = new TextButton(text, textButtonStyle);
        newButton.setBounds(x, y, width, height);
        newButton.setName(name);
        stage.addActor(newButton);
        return newButton;
    }

    public void setController(Controller mvcController) {  //used in view to manage circular dependency between MenuView and Controller.
        this.controller = mvcController;
        startGameButton.addListener(mvcController);
        player1NameField.addListener(mvcController);
        loginPlayer1Button.addListener(mvcController);
        player2NameField.addListener(mvcController);
        loginPlayer2Button.addListener(mvcController);
    }

    /**
     * This method draws the entire UI
     * @param batch The SpriteBatch passed by {@link View}
     */
    public void render(SpriteBatch batch)
    {
        stage.draw();
    }
    /**
     * Clear all memory and cleanup
     */
    public void dispose()
    {
        stage.dispose();
        skin.dispose();
        buttonAtlas.dispose();
    }

}

