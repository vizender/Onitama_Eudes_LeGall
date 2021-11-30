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
    Pion grille [][] = new Pion[5][5];
    
    //On initialise une grille vide
    public Grille(){
        for (int i=0; i<5; i++){
            for (int j=0; j<5; j++){
                grille[i][j]=null;
            }
        }
    }
}
