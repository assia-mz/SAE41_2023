package com.example.saemorpionsolitaire;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.List;

/**
 * Activité principale pour jouer au jeu du morpion solitaire.
 */
public class GameActivity extends AppCompatActivity {

    private Game map; // Instance de la carte de jeu

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu); // Définit le contenu de l'activité

        // Récupère les valeurs booléennes supplémentaires de l'intent
        boolean rule1 = getIntent().getBooleanExtra("RULE_1", false); // Initialise à false si l'extra n'est pas présent
        boolean rule2 = getIntent().getBooleanExtra("RULE_2", false); // Initialise à false si l'extra n'est pas présent

        // Vérifie si l'intent contient les données supplémentaires
        if (getIntent().hasExtra("RULE_1") && rule1) {
            // Initialise le jeu avec une configuration spécifique (3 croix par ligne)
            setupGame(3);
        } else {
            // Initialise le jeu avec la configuration par défaut (4 croix par ligne)
            setupGame(4);
        }

        // Vérifie si l'intent contient l'extra pour la règle 2
        if (getIntent().hasExtra("RULE_2")) {
            // Affiche un message Toast indiquant que la règle 2 est activée
            Toast.makeText(this, "Règle 2 activée: " + rule2, Toast.LENGTH_SHORT).show();
        } else {
            // Affiche un message Toast indiquant qu'aucun paramètre n'est passé
            Toast.makeText(this, "Aucun paramètre passé", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Initialise le jeu avec le nombre de croix par ligne spécifié.
     *
     * @param crossesPerLine Nombre de croix par ligne.
     */
    private void setupGame(int crossesPerLine) {
        this.map = findViewById(R.id.game); // Récupère la vue de la carte de jeu
        this.map.setCrossesPerLine(crossesPerLine); // Configure le nombre de croix par ligne sur la carte

        // Met en place un écouteur de toucher pour la carte de jeu
        FingerDown fingerDownListener = new FingerDown(this.map);
        this.map.setOnTouchListener(fingerDownListener);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Sauvegarde l'état actuel de la carte de jeu dans le bundle
        Point mapPosition = this.map.getPositionMap();
        outState.putFloat("x", mapPosition.getX());
        outState.putFloat("y", mapPosition.getY());
        outState.putInt("score", this.map.getScore());
        HashMap<Point, List<Line>> dotLineMap = this.map.getHashMapDotLine();
        outState.putSerializable("dotLineMap", dotLineMap);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // Restaure l'état précédemment sauvegardé de la carte de jeu depuis le bundle
        Point mapPosition = new Point(savedInstanceState.getFloat("x", 0), savedInstanceState.getFloat("y", 0));
        HashMap<Point, List<Line>> dotLineMap = (HashMap<Point, List<Line>>) savedInstanceState.getSerializable("dotLineMap");
        int score = savedInstanceState.getInt("score", 0);
        this.map.setPositionMap(mapPosition);
        this.map.setHashMapDotLine(dotLineMap);
        this.map.setScore(score);
        this.map.setIsGameRN(true);
    }
}
