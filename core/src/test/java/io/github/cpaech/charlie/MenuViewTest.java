package io.github.cpaech.charlie;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;


public class MenuViewTest {
    private MenuView menuView;

    @Before
    public void setUp() {
        // Initialize LibGDX in headless mode
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        new HeadlessApplication(new ApplicationListener() {
            @Override
            public void create() {}
            @Override
            public void resize(int width, int height) {}
            @Override
            public void render() {}
            @Override
            public void pause() {}
            @Override
            public void resume() {}
            @Override
            public void dispose() {}
        }, config);

        // Create a mock model and initialize MenuView
        Model mockModel = new Model();
        menuView = new MenuView(mockModel);
    }

    @Test
    public void testErrorLabelInitialization() {
        Label errorLabel = menuView.errorLabel;
        assertNotNull("Error label should be initialized", errorLabel);
        assertEquals("Error label should be empty initially", "", errorLabel.getText().toString());
    }

    @Test
    public void testSetErrorText() {
        menuView.errorLabel.setText("Test error message");
        assertEquals("Error label should display the correct text", "Test error message", menuView.errorLabel.getText().toString());
    }

    @Test
    public void testButtonInitialization() {
        TextButton startGameButton = menuView.startGameButton;
        assertNotNull("Start game button should be initialized", startGameButton);
        assertEquals("Start game button should have correct text", "Start Game", startGameButton.getText().toString());
    }

    @Test
    public void testTextFieldInitialization() {
        TextField player1NameField = menuView.player1NameField;
        assertNotNull("Player 1 name field should be initialized", player1NameField);
        assertEquals("Player 1 name field should have correct placeholder text", "Enter Player 1 Name", player1NameField.getMessageText());
    }
}