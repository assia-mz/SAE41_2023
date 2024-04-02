package com.example.saemorpionsolitaire;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Cette classe fournit une méthode statique pour déterminer si le jeu est terminé.
 * Le jeu est considéré comme terminé si chaque point de la grille est lié à au moins deux lignes
 * ou s'il n'y a plus de mouvements possibles.
 */
public class EndGame {

    /**
     * Liste des directions possibles pour vérifier les lignes.
     */
    private static Point[] directionList = new Point[]{
            new Point(-1f, -1f),
            new Point(-1f, 0f),
            new Point(-1f, 1f),
            new Point(0f, -1f),
            new Point(0f, 1f),
            new Point(1f, -1f),
            new Point(1f, 0f),
            new Point(1f, 1f)
    };

    /**
     * Vérifie si le jeu est terminé en parcourant chaque point de la grille
     * et en vérifiant s'il est lié à au moins deux lignes.
     *
     * @param dicoPoint  Dictionnaire de points avec leurs lignes associées.
     * @param tailleLine Taille maximale d'une ligne.
     * @return true si le jeu est terminé, sinon false.
     */
    public static boolean finJeu(HashMap<Point, List<Line>> dicoPoint, int tailleLine) {
        for (Point dot : dicoPoint.keySet()) {
            for (Point direction : EndGame.directionList) {
                boolean flag = false;
                int i;
                int free = 0;
                Point dot1 = dot.copy();
                List<Line> lineCroisee = new ArrayList<>();
                for (i = 0; i <= tailleLine && !flag && free < 2; i++) {
                    List<Line> listeLine = dicoPoint.get(dot1);
                    if (listeLine == null) {
                        free++;
                    } else {
                        if (!fillList(lineCroisee, listeLine)) {
                            flag = true;
                        }
                    }
                    dot1.addVecteur(direction);
                }
                if (flag == false && free == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Remplit une liste avec les éléments d'une autre liste en évitant les doublons.
     *
     * @param listeARemplir   Liste à remplir.
     * @param elementAAjouter Liste d'éléments à ajouter.
     * @return true si l'ajout s'est bien déroulé, sinon false.
     */
    private static boolean fillList(List<Line> listeARemplir, List<Line> elementAAjouter) {
        for (Line line : elementAAjouter) {
            if (listeARemplir.contains(line)) {
                return false;
            }
            listeARemplir.add(line);
        }
        return true;
    }
}
