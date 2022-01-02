/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author victorlegall
 */
public class Grille {
    // La Grille de 5x5 sera constituée de cellules
    Cellule tabCellule [][] = new Cellule[5][5];
    int[] celluleSelect = new int[2];
    
    //On initialise une grille de ce
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
        tabCellule[ligne][colonne].pionCourant=tabCellule[celluleSelect[0]][celluleSelect[1]].pionCourant;
        tabCellule[celluleSelect[0]][celluleSelect[1]].pionCourant=null;
        celluleSelect[0]=5;
        celluleSelect[1]=5;
    }
    
    public String compterPions(){
        int comptR = 0;
        int comptB = 0;
        for (int i = 4; i >= 0; i--) {
            for (int j = 0; j < 5; j++) {
                if (tabCellule[i][j].pionCourant != null && tabCellule[i][j].pionCourant.couleur=="rouge") comptR ++ ;
                if (tabCellule[i][j].pionCourant != null && tabCellule[i][j].pionCourant.couleur=="bleu") comptB ++ ;
            }
        }
        if (comptR==0) return "rouge";
        else if (comptB==0) return "bleu" ;
        else return null ;
    }
    
    public void afficherGrilleSurConsole(){
        String res = "";
        String res1 = "";
        for (int i = 4 ; i >= 0 ; i--){ //lignes
            for (int j = 0 ; j <= 4 ; j++){ //colonnes
                if (tabCellule[i][j].pionCourant == null) res += " x ";
                else {if (tabCellule[i][j].pionCourant.couleur == "rouge") res += ("\u001B[31m R \u001B[30m");
                     if (tabCellule[i][j].pionCourant.couleur == "bleu") res += ("\u001B[33m J \u001B[30m");}
                //System.out.println(res);
            } 
            res1 += res + "\n";
            res = "";
        } 
        System.out.println(res1);
    }
}
