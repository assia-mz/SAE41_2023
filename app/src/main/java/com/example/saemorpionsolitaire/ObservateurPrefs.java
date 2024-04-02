package com.example.saemorpionsolitaire;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

/**
 * Classe qui écoute les changements dans les préférences partagées.
 */
public class ObservateurPrefs implements SharedPreferences.OnSharedPreferenceChangeListener {

    private Context context; // Contexte de l'application

    /**
     * Constructeur pour initialiser le contexte et enregistrer le listener.
     *
     * @param context Le contexte de l'application.
     */
    public ObservateurPrefs(Context context) {
        this.context = context;
        // Récupère les préférences partagées par défaut
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        // Enregistre ce listener pour écouter les changements
        prefs.registerOnSharedPreferenceChangeListener(this);
    }

    /**
     * Méthode appelée lorsqu'une préférence partagée est modifiée.
     *
     * @param sharedPreferences L'objet SharedPreferences contenant les préférences partagées modifiées.
     * @param key               La clé de la préférence modifiée.
     */
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        // Vérifie si la préférence modifiée appartient à la SettingsActivity
        if (key.equals("regle_croix")) {
            // Gère le changement de préférence ici
            // Affiche un Toast pour indiquer que la modification a été prise en compte
            Toast.makeText(context, "Modification de la règle croix prise en compte", Toast.LENGTH_SHORT).show();
        } else if (key.equals("regle_ligne")){
            // Gère le changement de préférence ici
            // Affiche un Toast pour indiquer que la modification a été prise en compte
            Toast.makeText(context, "Modification de la règle ligne prise en compte", Toast.LENGTH_SHORT).show();
        }
    }
}
