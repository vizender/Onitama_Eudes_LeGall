
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JButton;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author victorlegall
 */
public class Pion {
        
    // 2 Attribus des pions, couleur et type roi
    String couleur;
    boolean roi;
    
    // Constructeur; on demande la couleur et le type du pion
    public Pion(String color, boolean king){
        couleur = color;
        roi=king;
    }
    
    //Demander la couleur du pion
    String demanderCouleur(){
        if (couleur == "bleu"){
            return "bleu";
        }
        if (couleur == "rouge"){
            return "rouge";
        }
        return "erreur";
    }
    
    //Demander si estRoi
    boolean estRoi(){
        if (roi == true){
            return true;
        }
        return false;
    }    
}