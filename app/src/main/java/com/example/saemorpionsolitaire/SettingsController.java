package com.example.saemorpionsolitaire;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

/**
 * Contrôleur pour gérer les actions liées aux paramètres.
 */
public class SettingsController {
    private final Context context;

    /**
     * Initialise une nouvelle instance de SettingsController.
     *
     * @param context Le contexte de l'application.
     */
    public SettingsController(Context context) {
        this.context = context;
    }

    /**
     * Gère les actions de l'élément de menu sélectionné.
     *
     * @param item L'élément de menu sélectionné.
     * @return True si l'action a été gérée avec succès, sinon False.
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        // Vérifie si l'élément de menu sélectionné est celui des paramètres
        if (id == R.id.settings) {
            // Lance l'activité des paramètres
            context.startActivity(new Intent(context, SettingsActivity.class));
            return true;
        }

        return false;
    }
}
