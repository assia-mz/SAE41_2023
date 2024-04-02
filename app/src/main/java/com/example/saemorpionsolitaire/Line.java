package com.example.saemorpionsolitaire;

import java.io.Serializable;

/**
 * Représente une ligne reliant deux points.
 * Cette classe est sérialisable.
 */
public class Line implements Serializable {
    private static final long serialVersionUID = 1L;
    private Point start; // Point de départ de la ligne
    private Point end; // Point d'arrivée de la ligne

    /**
     * Constructeur par défaut. Initialise la ligne avec des valeurs nulles.
     */
    public Line() {
        this.start = null;
        this.end = null;
    }

    /**
     * Constructeur prenant un point de départ et initialisant la fin de la ligne à null.
     *
     * @param start Point de départ de la ligne.
     */
    public Line(Point start) {
        this.start = start;
        this.end = null;
    }

    /**
     * Constructeur prenant un point de départ et un point d'arrivée.
     *
     * @param start Point de départ de la ligne.
     * @param end   Point d'arrivée de la ligne.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Définit le point d'arrivée de la ligne.
     *
     * @param end Point d'arrivée de la ligne.
     */
    public void setArrivee(Point end) {
        this.end = end;
    }

    /**
     * Récupère le point d'arrivée de la ligne.
     *
     * @return Le point d'arrivée de la ligne.
     */
    public Point getArrivee() {
        return this.end;
    }

    /**
     * Récupère le point de départ de la ligne.
     *
     * @return Le point de départ de la ligne.
     */
    public Point getDepart() {
        return this.start;
    }

    /**
     * Vérifie si la ligne a une longueur spécifiée.
     *
     * @param longueur Longueur spécifiée.
     * @return true si la ligne a la longueur spécifiée, sinon false.
     */
    public boolean haveLongueur(int longueur) {
        if (this.start == null || this.end == null) {
            return false;
        }
        if (Math.abs(this.start.getX() - this.end.getX()) == longueur) {
            if (Math.abs(this.start.getY() - this.end.getY()) == longueur || this.start.getY() - this.end.getY() == 0) {
                return true;
            }
        } else if (this.start.getX() - this.end.getX() == 0 && Math.abs(this.start.getY() - this.end.getY()) == longueur) {
            return true;
        }
        return false;
    }

    /**
     * Récupère les coordonnées de la ligne sous forme de tableau de flottants.
     *
     * @return Tableau de flottants contenant les coordonnées de la ligne [x1, y1, x2, y2].
     */
    public float[] getCoordonnee() {
        if (this.start == null || this.end == null) {
            return null;
        }
        return new float[]{
                this.start.getX(),
                this.start.getY(),
                this.end.getX(),
                this.end.getY()
        };
    }

    /**
     * Renvoie une représentation textuelle de la ligne.
     *
     * @return La représentation textuelle de la ligne sous forme de chaîne de caractères.
     */
    @Override
    public String toString() {
        StringBuilder texte = new StringBuilder();
        if (this.start != null) {
            texte.append(this.start.toString());
        } else {
            texte.append("null");
        }
        texte.append("; ");
        if (this.end != null) {
            texte.append(this.end.toString());
        } else {
            texte.append("null");
        }
        return texte.toString();
    }
}
