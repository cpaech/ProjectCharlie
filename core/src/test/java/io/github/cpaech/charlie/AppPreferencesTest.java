package io.github.cpaech.charlie;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AppPreferencesTest {
    private AppPreferences appPreferences;

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

        // Initialize AppPreferences
        appPreferences = AppPreferences.getAppPreferences();
    }

    @Test
    public void testSetAndGetPlayerHighScore() {
        String playerName = "Player1";
        int score = 100;

        appPreferences.setPlayerHighScore(playerName, score);
        int retrievedScore = appPreferences.getPlayerHighScore(playerName);

        assertEquals("High score should be saved and retrieved correctly", score, retrievedScore);
    }

    @Test
    public void testDefaultHighScore() {
        String playerName = "NonExistentPlayer";
        int defaultScore = appPreferences.getPlayerHighScore(playerName);

        assertEquals("Default high score should be 0 for non-existent players", 0, defaultScore);
    }
}
