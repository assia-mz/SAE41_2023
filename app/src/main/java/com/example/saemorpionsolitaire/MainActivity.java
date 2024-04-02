package com.example.saemorpionsolitaire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * L'activité principale de l'application.
 */
public class MainActivity extends AppCompatActivity {
    private HomeView homeView;
    private HomeController homeController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Crée une nouvelle instance de HomeView associée à cette activité
        homeView = new HomeView(this);

        // Crée une nouvelle instance de HomeController associée à cette activité et à la HomeView
        homeController = new HomeController(this, homeView);

        // Récupère les références des boutons de l'interface utilisateur
        Button playBtn = findViewById(R.id.play);
        FloatingActionButton settingsBtn = findViewById(R.id.parametres);
        Button quitBtn = findViewById(R.id.quit);

        // Associe les écouteurs d'événements aux boutons
        playBtn.setOnClickListener(homeController);
        settingsBtn.setOnClickListener(homeController);
        quitBtn.setOnClickListener(homeController);
    }
}