package io.github.cpaech.charlie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class AppPreferences {

    public static final String PREFS_NAME = "charlie";
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

    public void setPlayerHighScore(String playerName, int score) {
        getPrefs().putInteger(playerName + ".highscore", score);
        getPrefs().flush();
    }

    public int getPlayerHighScore(String playerName) {
        return getPrefs().getInteger(playerName + ".highscore", 0);
    }
}
