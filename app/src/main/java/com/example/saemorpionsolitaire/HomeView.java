package com.example.saemorpionsolitaire;

import android.content.Context;
import android.widget.Toast;

/**
 * initialisation des toasts
 */
public class HomeView {
    private Context context;

    /**
     * Initialise une nouvelle instance de HomeView.
     *
     * @param context Le contexte de l'application.
     */
    public HomeView(Context context) {
        this.context = context;
    }

    /**
     * Affiche un toast pour le bouton "Play".
     */
    public void showPlayToast() {
        Toast.makeText(context, "Clic sur play ", Toast.LENGTH_SHORT).show();
    }

    /**
     * Affiche un toast pour le bouton "Paramètres".
     */
    public void showSettingsToast() {
        Toast.makeText(context, "Clic sur paramètres ", Toast.LENGTH_SHORT).show();
    }

    /**
     * Affiche un toast pour le lancement de l'application.
     */
    public void showDebugToast(){
        Toast.makeText(context, "Lancement de l'application ", Toast.LENGTH_SHORT).show();
    }
}
