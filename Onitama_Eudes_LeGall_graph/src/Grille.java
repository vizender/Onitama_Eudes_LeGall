/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author victorlegall
 */
public class Grille {
    // La Grille de 5x5 sera constituée de pions
    Cellule tabCellule [][] = new Cellule[5][5];
    int[] celluleSelect = new int[2];
    
    //On initialise une grille vide
    public Grille(){
        for (int i=0; i<5; i++){
            for (int j=0; j<5; j++){
                tabCellule[i][j]=null;
            }
        }
        celluleSelect[0]=5;
        celluleSelect[1]=5;
    }
    
    public void ajouterCellule(Cellule c, int i, int j) {
        tabCellule[i][j]=c ;
    }

    public void selectCellule(int i,int j) {
        celluleSelect[0]=i ;    //Sauvegarde ligne
        celluleSelect[1]=j ;    //Sauvegarde colonne
    }

    public void deplacerPion(int ligne, int colonne) {
        tabCellule[ligne][colonne]=tabCellule[celluleSelect[0]][celluleSelect[1]];
    }
    
}
