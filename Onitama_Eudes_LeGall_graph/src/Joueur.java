/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author victorlegall
 */
public class Joueur {
    //Un joueur a un nom, et un couleur
    String nom;
    String couleur;
    
    //Quand on creer un joueur, il lui faut un nom
    public Joueur (String name){
        nom = name;
    }
    
    //Affecter la couleur au joueur
    void affecterCouleur(String color){
        couleur=color;
        }
    
    //On demande la couleur du joueur
    String demanderCouleur(){
        if ("blanc".equals(couleur)){ // On verifie si la couleur est blanc
            return "blanc";
        }
        if ("noir".equals(couleur)){ // On verifie si la couleur est noir
            return "noir";
        }
        return "erreur";
    }
    
}
