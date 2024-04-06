package com.example.saemorpionsolitaire;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Classe utilitaire pour gérer les lignes dans le jeu.
 */
public class LineGestion {

    private final static int dotNbr = 4; // Nombre de points par ligne
    private final static boolean LIGNEVALIDE = false; // Indicateur de validité de la ligne

    /**
     * Vérifie si une ligne est valide et l'ajoute au dictionnaire des points et lignes si elle est valide.
     *
     * @param line           Ligne à vérifier.
     * @param hashMapDotLine Dictionnaire des points et des lignes associées.
     * @param rule2 Boleen qui permet de savoir si la regle n°2 est active ou pas
     * @return Le nouveau point ajouté au dictionnaire si la ligne est valide, sinon null.
     */
    public static Point isValide(Line line, HashMap<Point, List<Line>> hashMapDotLine, boolean rule2) {
        if (line != null) {
            if (line.haveLongueur(LineGestion.dotNbr)) {
                // Calcul de la direction de la ligne
                int directionX = (int)(line.getArrivee().getX() - line.getDepart().getX());
                int directionY = (int)(line.getArrivee().getY() - line.getDepart().getY());
                if (directionX > 0) {
                    directionX = 1;
                } else if (directionX < 0) {
                    directionX = -1;
                }
                if (directionY > 0) {
                    directionY = 1;
                } else if (directionY < 0) {
                    directionY = -1;
                }

                // Vérifie s'il y a un prolongement de ligne et les croisements
                boolean prolongementLine = false;
                List<Line> listeLineCroise = new ArrayList<>();
                List<List<Line>> listDot = new ArrayList<>();
                Point start = line.getDepart().copy();
                Point newDot = null;
                int cC = 0;
                for (int i = 0; i <= LineGestion.dotNbr; i++) {
                    List<Line> point = hashMapDotLine.get(start);
                    if (point != null) {
                        listDot.add(point);
                        cC++;
                        for (Line lineCroise : point) {
                            if (rule2 && lineCroise != line) {
                                prolongementLine = true; // Si la règle 2 est activée, vérifie s'il y a un prolongement de ligne
                            }
                            if (!LIGNEVALIDE) {
                                if (listeLineCroise.contains(lineCroise)) {
                                    prolongementLine = true;
                                } else {
                                    listeLineCroise.add(lineCroise);
                                }
                            }
                        }
                    } else {
                        newDot = start.copy();
                    }
                    start.addVecteur(new Point(directionX, directionY));
                }

                // Si la ligne est valide et il n'y a pas de prolongement de ligne, l'ajoute au dictionnaire
                if (cC == LineGestion.dotNbr && !prolongementLine) {
                    for (List<Line> croisement : listDot) {
                        croisement.add(line);
                    }
                    List<Line> encoreUneListePourLesCroisements = new ArrayList<>();
                    encoreUneListePourLesCroisements.add(line);
                    hashMapDotLine.put(newDot, encoreUneListePourLesCroisements);
                    return newDot;
                }
            }
        }
        return null;
    }
}
