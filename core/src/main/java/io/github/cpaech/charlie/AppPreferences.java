package io.github.cpaech.charlie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;


public class AppPreferences {
    public static final String PREFS_NAME = "Pong4Net";
    private static AppPreferences appPreferences = null;

    public static AppPreferences getAppPreferences() {
        if (appPreferences == null) {
            AppPreferences.appPreferences = new AppPreferences();
        }
        // Ensure that the singleton instance is returned
        return AppPreferences.appPreferences;
    }

    protected Preferences getPrefs() {
	    return Gdx.app.getPreferences(PREFS_NAME);
    }

    public void setPlayerHighScore(String playerName, int score) { // Set the high score for a player
        getPrefs().putInteger(playerName + ".highscore", score);
        getPrefs().flush(); // needed to safe the preferences
    }

    public int getPlayerHighScore(String playerName) {
        return getPrefs().getInteger(playerName + ".highscore", 0); // Get the high score for a player, default is 0
    }

    public void resetPreferences() {
        getPrefs().clear();
        getPrefs().flush();
    }
}
