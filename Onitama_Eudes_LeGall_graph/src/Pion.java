
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
public class Pion extends JButton {
    
    // On importes les images de toutes les pions
    ImageIcon img_vide = new javax.swing.ImageIcon(getClass().getResource("/images/Case_Vide.png"));
    ImageIcon img_pion_rouge = new javax.swing.ImageIcon(getClass().getResource("/images/Pion_Rouge.png"));
    ImageIcon img_pion_bleu = new javax.swing.ImageIcon(getClass().getResource("/images/Pion_Bleu.png"));
    ImageIcon img_roi_rouge = new javax.swing.ImageIcon(getClass().getResource("/images/Roi_Rouge.png"));
    ImageIcon img_roi_bleu = new javax.swing.ImageIcon(getClass().getResource("/images/Roi_Bleu.png"));
    ImageIcon img_castle_rouge = new javax.swing.ImageIcon(getClass().getResource("/images/Case_Castle_Down.png"));
    ImageIcon img_castle_bleu = new javax.swing.ImageIcon(getClass().getResource("/images/Case_Castle_Up.png"));
    
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
    
    @Override
    public void paintComponent ( Graphics G) {
        super.paintComponent(G);
        setIcon(img_vide);
        
    }
}
