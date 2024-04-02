package com.example.saemorpionsolitaire;

import java.io.Serializable;
import java.util.Objects;

/**
 * Représente un point dans un espace bidimensionnel.
 */
public class Point implements Serializable {
    private static final long serialVersionUID = 1L;
    private float x; // Coordonnée x du point
    private float y; // Coordonnée y du point

    /**
     * Constructeur prenant les coordonnées x et y du point.
     *
     * @param x Coordonnée x du point.
     * @param y Coordonnée y du point.
     */
    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Définit les coordonnées du point.
     *
     * @param x Nouvelle coordonnée x du point.
     * @param y Nouvelle coordonnée y du point.
     */
    public void setCoordoonee(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Récupère la coordonnée x du point.
     *
     * @return La coordonnée x du point.
     */
    public float getX() {
        return this.x;
    }

    /**
     * Récupère la coordonnée y du point.
     *
     * @return La coordonnée y du point.
     */
    public float getY() {
        return this.y;
    }

    /**
     * Calcule la distance entre ce point et un autre point donné.
     *
     * @param point2 Le deuxième point.
     * @return La distance entre ce point et le point donné.
     */
    public float getDistance(Point point2) {
        return (float) Math.sqrt(Math.pow(this.x - point2.getX(), 2) + Math.pow(this.y - point2.getY(), 2));
    }

    /**
     * Ajoute un vecteur donné à ce point.
     *
     * @param vecteur Le vecteur à ajouter.
     */
    public void addVecteur(Point vecteur) {
        this.x += vecteur.getX();
        this.y += vecteur.getY();
    }

    /**
     * Effectue une copie profonde de ce point.
     *
     * @return Une copie de ce point.
     */
    public Point copy() {
        return new Point(this.getX(), this.getY());
    }

    /**
     * Calcule le vecteur moyen entre deux vecteurs donnés.
     *
     * @param vecteur1 Premier vecteur.
     * @param vecteur2 Deuxième vecteur.
     * @return Le vecteur moyen entre les deux vecteurs donnés.
     */
    public static Point averageV(Point vecteur1, Point vecteur2) {
        float moyenneX = (vecteur1.getX() + vecteur2.getX()) / 2;
        float moyenneY = (vecteur1.getY() + vecteur2.getY()) / 2;
        return new Point(moyenneX, moyenneY);
    }

    /**
     * Renvoie une représentation textuelle de ce point.
     *
     * @return La représentation textuelle de ce point sous forme de chaîne de caractères.
     */
    @Override
    public String toString() {
        return this.x + " " + this.y;
    }

    /**
     * Vérifie si ce point est égal à un autre objet donné.
     *
     * @param o L'objet à comparer avec ce point.
     * @return true si les objets sont égaux, sinon false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Point point = (Point) o;
        return Float.compare(point.getX(), this.x) == 0 && Float.compare(point.getY(), this.y) == 0;
    }

    /**
     * Calcule le code de hachage de ce point.
     *
     * @return Le code de hachage calculé pour ce point.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }
}
