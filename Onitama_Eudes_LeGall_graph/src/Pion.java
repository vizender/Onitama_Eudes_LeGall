/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author victorlegall
 */
public class Pion {
    String couleur;
    String type;
    
    public Pion(String color){
        couleur = color;
    }
    
    String demanderCouleur(){
        if (couleur == "blanc"){
            return "blanc";
        }
        if (couleur == "noir"){
            return "noir";
        }
        return "erreur";
    }
}
