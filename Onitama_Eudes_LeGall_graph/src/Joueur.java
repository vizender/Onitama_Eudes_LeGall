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
    Carte [] cartes = new Carte[2];
    
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
        if ("bleu".equals(couleur)){ // On verifie si la couleur est blanc
            return "bleu";
        }
        if ("rouge".equals(couleur)){ // On verifie si la couleur est noir
            return "rouge";
        }
        return "erreur";
    }
    
    
    
}
