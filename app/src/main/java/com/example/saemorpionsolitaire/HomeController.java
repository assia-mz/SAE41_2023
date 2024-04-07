package com.example.saemorpionsolitaire;

import android.content.Context;
import android.content.Intent;
import android.view.View;

/**
 * Le contrôleur pour la vue d'accueil de l'application.
 */
public class HomeController implements View.OnClickListener {
    private Context context;
    private HomeView view;

    private AppPreferences appPref;

    /**
     * Initialise une nouvelle instance de HomeController.
     *
     * @param context Le contexte de l'application.
     * @param view    La vue d'accueil associée au contrôleur.
     */
    public HomeController(Context context, HomeView view) {
        this.context = context;
        this.view = view;
        this.appPref = new AppPreferences(this.context);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.play) {
            //view.showPlayToast();
            Intent intent = new Intent(context, GameActivity.class);

            // Passe les valeurs des paramètres comme des extra avec leurs clé respectives
            intent.putExtra("RULE_1", appPref.getRule1());
            intent.putExtra("RULE_2", appPref.getRule2());

            // Commence l'activité de jeu
            context.startActivity(intent);

        } else if (id == R.id.parametres) {
            //view.showSettingsToast();
            Intent intent = new Intent(context, SettingsActivity.class);
            view.showDebugToast();
            context.startActivity(intent);

        } else if (id == R.id.quit) {
            ((MainActivity) context).finishAffinity();
        }
    }
}
