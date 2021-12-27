
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
    
    
    //Affichage des pions
    @Override
    public void paintComponent ( Graphics G) {
        super.paintComponent(G);
        if(couleur=="bleu" && roi==false)setIcon(img_pion_bleu);
        if(couleur=="rouge" && roi==false)setIcon(img_roi_rouge);
        if(couleur=="bleu" && roi==true)setIcon(img_roi_bleu);
        if(couleur=="rouge" && roi==true)setIcon(img_roi_rouge);
        setIcon(img_vide);
        
    }
}
