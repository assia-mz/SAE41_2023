package com.example.saemorpionsolitaire;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.view.Menu;

// SettingsActivity.java
/**
 * Activité pour gérer les paramètres de l'application.
 */
public class SettingsActivity extends PreferenceActivity implements OnSharedPreferenceChangeListener {
    private SharedPreferences prefs;
    private boolean defaultValueRule1 = false, defaultValueRule2 = false;
    private boolean parametre_regle1, parametre_regle2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Charge les préférences à partir du fichier XML
        addPreferencesFromResource(R.xml.preference_screen_settings);

        // Récupère les préférences partagées par défaut
        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        // Enregistre ce listener pour écouter les changements dans les préférences
        prefs.registerOnSharedPreferenceChangeListener(this);

        // Définit les valeurs par défaut pour les préférences
        PreferenceManager.setDefaultValues(this, R.xml.preference_screen_settings, false);

        // Initialise parametre_regle1 et parametre_regle2 dans onCreate
        parametre_regle1 = prefs.getBoolean("regle_croix", defaultValueRule1);
        parametre_regle2 = prefs.getBoolean("regle_ligne", defaultValueRule2);

        // Initialise les préférences de l'application
        AppPreferences appPreferences = new AppPreferences(this);

        // Initialise l'observateur de préférences
        ObservateurPrefs observer = new ObservateurPrefs(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        // Gère les changements de préférences ici
        if (key.equals("regle_croix")) {
            parametre_regle1 = sharedPreferences.getBoolean(key, defaultValueRule1);
        } else if (key.equals("regle_ligne")) {
            parametre_regle2 = sharedPreferences.getBoolean(key, defaultValueRule2);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_menu, menu);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Désenregistre le listener lors de la fermeture de l'activité
        prefs.unregisterOnSharedPreferenceChangeListener(this);
    }
}
