package com.example.saemorpionsolitaire;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Cette classe gère les préférences de l'application.
 */
public class AppPreferences {
    private SharedPreferences prefs;
    private static final String DEFAULT_RULE_1_KEY = "regle_croix";
    private static final String DEFAULT_RULE_2_KEY = "regle_ligne";

    /**
     * Initialise une nouvelle instance de AppPreferences.
     *
     * @param context Le contexte de l'application.
     */
    public AppPreferences(Context context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * Récupère l'état de la première règle.
     *
     * @return True si la première règle est activée, sinon False.
     */
    public boolean getRule1() {
        return prefs.getBoolean(DEFAULT_RULE_1_KEY, false);
    }

    /**
     * Récupère l'état de la deuxième règle.
     *
     * @return True si la deuxième règle est activée, sinon False.
     */
    public boolean getRule2() {
        return prefs.getBoolean(DEFAULT_RULE_2_KEY, false);
    }

    /**
     * Définit l'état de la première règle.
     *
     * @param value True pour activer la première règle, False pour la désactiver.
     */
    public void setRule1(boolean value) {
        prefs.edit().putBoolean(DEFAULT_RULE_1_KEY, value).apply();
    }

    /**
     * Définit l'état de la deuxième règle.
     *
     * @param value True pour activer la deuxième règle, False pour la désactiver.
     */
    public void setRule2(boolean value) {
        prefs.edit().putBoolean(DEFAULT_RULE_2_KEY, value).apply();
    }
}
