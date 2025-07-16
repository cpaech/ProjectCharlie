package io.github.cpaech.charlie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 * This is the part of the view structure which takes care of rendering 
 * the menu with scoreboard and everything. From here the player will be able to start a game and login.
 */
public class MenuView {
    /**
     * This is the container for all ui elements. Required by libgdx.
     */
    private Stage stage;

    //I won't do a Javadoc for each and everyone of them ;(
    public TextButton loginPlayer1Button;
    public TextButton loginPlayer2Button;

    public TextButton bot1loginButton;
    public TextButton bot2loginButton;

    public TextField player1NameField;
    public TextField player2NameField;

    public TextButton startGameButton;

    public Label errorLabel;

    /**
     * as a background for the textfield
     */
    Pixmap pixmap;
    
    /**
     * Style of the button and textfield(font, color, image, etc...).
     */
    TextButtonStyle textButtonStyle;

    /**
     * Style of the button and textfield(font, color, image, etc...).
     */
    TextField.TextFieldStyle textFieldStyle;

    /**
     * The Default font used for all UI-elements.
     */
    BitmapFont font;

    /**
     * This is a button skin, containing all the images of each state for the button.
     */
    Skin skin;

    /**
     * This is the texture for the Pong Logo
     */
    Texture pongTitle;

    /**
     * This is the texture for the Pong Menu Background
     */
    Texture pongBackground;

    /**
     * Textureatlas containing all the imagery for the button graphics.
     */
    TextureAtlas buttonAtlas;

    /**
     * This method sets up all nesessary objects for the UI.
     * @param mvcModel Model passed by {@link Main}
     * @param mvcController Controller passed by {@link Main}, responsible for input processing.
     */
    public MenuView(Model model) {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        pongTitle = new Texture(Gdx.files.internal("PongTitle.png"));
        pongBackground = new Texture(Gdx.files.internal("PongBackground.png"));

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
        Pixmap pixmap = new Pixmap(200, 50, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.GRAY);
        pixmap.fill();
        textFieldStyle.background = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
    
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font;
        labelStyle.fontColor = Color.RED;

        loginPlayer1Button = newTextButton(loginPlayer1Button, "LoginPlayer1Button", "Login Player 1", 200, 200, 135, 50);
        player1NameField = newTextField(player1NameField, "player1NameField", "Enter Player 1 Name", 200, 300, 135, 50);
        bot1loginButton = newTextButton(bot1loginButton, "Bot1LoginButton", "Computer Login", 200, 250, 135, 50);
        loginPlayer2Button = newTextButton(loginPlayer2Button, "LoginPlayer2Button", "Login Player 2", 400, 200, 135, 50);
        player2NameField = newTextField(player2NameField, "player2NameField", "Enter Player 2 Name", 400, 300, 135, 50);
        bot2loginButton = newTextButton(bot1loginButton, "Bot2LoginButton", "Computer Login", 400, 250, 135, 50);
        startGameButton = newTextButton(startGameButton, "StartGameButton", "Start Game", 300, 100, 120, 50);
        errorLabel = new Label("", labelStyle);
        errorLabel.setBounds(200, 0, 600, 50);
        stage.addActor(errorLabel);
    }

    /**
     * Create a new textfield, assign it a text, dimensions and adds it to the stage.
     * @param newField Handle to the TextField variable, which will hold the new TextField
     * @param name Name of the actor in the stage
     * @param messageText Text, to be displayed
     * @param x x-coordinate of the lower bottom corner of the label
     * @param y y-coordinate of the lower bottom corner of the label
     * @param width Width of the textfield
     * @param height Height of the textfield
     */
    public TextField newTextField(TextField newField, String name, String messageText, float x, float y, float width, float height)
    {
        newField = new TextField("", textFieldStyle);
        newField.setMessageText(messageText);
        newField.setBounds(x, y, width, height);
        newField.setName(name);
        stage.addActor(newField);
        return newField;
    }

    /**
     * Create a new TextButton, assign it a text, dimensions and adds it to the stage.
     * @param newButton Handle to the TextButton variable, which will hold the new TextButton
     * @param name Name of the actor in the stage
     * @param messageText Text, to be displayed
     * @param x x-coordinate of the lower bottom corner of the label
     * @param y y-coordinate of the lower bottom corner of the label
     * @param width Width of the TextButton
     * @param height Height of the TextButton
     */
    public TextButton newTextButton(TextButton newButton, String name, String text, float x, float y, float width, float height)
    {
        newButton = new TextButton(text, textButtonStyle);
        newButton.setBounds(x, y, width, height);
        newButton.setName(name);
        stage.addActor(newButton);
        return newButton;
    }

    /**
     * Assigns the MenuView the current Controller and sets up all Listeners.
     */
    public void setController(Controller mvcController) {  //used in view to manage circular dependency between MenuView and Controller.
        startGameButton.addListener(mvcController);
        player1NameField.addListener(mvcController);
        loginPlayer1Button.addListener(mvcController);
        player2NameField.addListener(mvcController);
        loginPlayer2Button.addListener(mvcController);
        bot1loginButton.addListener(mvcController);
        bot2loginButton.addListener(mvcController);
    }

    /**
     * This method draws the entire UI
     * @param batch The SpriteBatch passed by {@link View}
     */
    public void render(SpriteBatch batch)
    {
        stage.draw();
        batch.draw(pongTitle, 400 - 143, 440);
        batch.draw(pongBackground, 0, 0);
    }

    /**
     * Clear all memory and cleanup
     */
    public void dispose()
    {
        stage.dispose();
        skin.dispose();
        buttonAtlas.dispose();
        pixmap.dispose();
        pongTitle.dispose();
    }

}

