/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author victorlegall
 */
public class Carte {
    // On creer une grille de booleens, false si on ne peut pas joueur la sur la position de la grille, true si on peut joueur cette position
    boolean [][] grilleCarte = new boolean[5][5];
    
    //On initialise la carte a false partou
    public Carte(){
        for (int i=0; i<5; i++){
            for (int j=0; i<0; j++){
                grilleCarte[i][j]=false;
            }
        }
    }
    
}
