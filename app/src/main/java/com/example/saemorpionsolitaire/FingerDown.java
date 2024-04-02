package com.example.saemorpionsolitaire;

import android.view.MotionEvent;
import android.view.View;

/**
 * Gère les événements de toucher de l'écran pour le début du mouvement du doigt.
 */
public class FingerDown implements View.OnTouchListener {

    private Game map; // Référence vers l'objet Game
    private boolean isMovInProgress; // Indique si un mouvement est en cours
    private int finger; // Nombre de doigts utilisés
    private Point pos1; // Position du premier doigt
    private Point pos2; // Position du deuxième doigt

    public FingerDown(Game map) {
        this.map = map; // Initialise la référence vers l'objet Game
        this.isMovInProgress = false; // Initialise le mouvement comme non en cours
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getActionMasked(); // Récupère le type d'action de l'événement

        switch (action) {
            case MotionEvent.ACTION_DOWN: // Lorsque le premier doigt touche l'écran
                this.pos1 = new Point(event.getX(0), event.getY(0)); // Enregistre la position du premier doigt
                this.map.traceStartLine(this.pos1); // Commence le tracé de la ligne
                this.isMovInProgress = true; // Indique qu'un mouvement est en cours
                this.finger = 1; // Indique qu'un seul doigt est utilisé
                break;

            case MotionEvent.ACTION_POINTER_DOWN: // Lorsqu'un deuxième doigt touche l'écran
                this.map.cancelLine(); // Annule le tracé de la ligne
                if (this.finger > 1) {
                    this.isMovInProgress = false; // Indique qu'aucun mouvement n'est en cours si plus d'un doigt est utilisé
                } else {
                    this.finger++; // Incrémente le nombre de doigts utilisés
                    this.pos2 = new Point(event.getX(1), event.getY(1)); // Enregistre la position du deuxième doigt
                }
                break;

            case MotionEvent.ACTION_MOVE: // Lorsqu'un doigt bouge sur l'écran
                if (this.isMovInProgress) { // Si un mouvement est en cours
                    this.pos1 = new Point(event.getX(0), event.getY(0)); // Met à jour la position du premier doigt
                    if (this.finger == 1) { // Si un seul doigt est utilisé
                        this.pos2 = new Point(event.getX(0), event.getY(0)); // Met à jour la position du deuxième doigt avec la même que le premier
                        this.map.traceEndLine(this.pos2); // Trace la fin de la ligne
                    } else if (this.finger == 2) { // Si deux doigts sont utilisés
                        // Calcule les vecteurs des deux doigts
                        Point vecteurDoigt1 = new Point(event.getX(0) - pos1.getX(), event.getY(0) - pos1.getY());
                        Point vecteurDoigt2 = new Point(event.getX(1) - pos2.getX(), event.getY(1) - pos2.getY());
                        this.pos1 = new Point(event.getX(0), event.getY(0)); // Met à jour la position du premier doigt
                        this.pos2 = new Point(event.getX(1), event.getY(1)); // Met à jour la position du deuxième doigt
                        this.map.moveMap(Point.averageV(vecteurDoigt1, vecteurDoigt2)); // Déplace la carte
                    }
                }
                break;

            case MotionEvent.ACTION_POINTER_UP: // Lorsqu'un doigt est retiré de l'écran
                this.finger--; // Décrémente le nombre de doigts utilisés
                break;

            case MotionEvent.ACTION_UP: // Lorsque le doigt quitte l'écran
                if (this.isMovInProgress) {
                    this.map.validateLine(); // Valide la ligne
                }
                this.isMovInProgress = false; // Indique qu'aucun mouvement n'est en cours
                break;

            case MotionEvent.ACTION_CANCEL: // Lorsqu'un événement est annulé
                this.isMovInProgress = false; // Indique qu'aucun mouvement n'est en cours
                break;
        }
        return true;
    }
}
