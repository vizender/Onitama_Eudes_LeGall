
import java.awt.Graphics;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Anais
 */

public class Cellule extends JButton {
    
    // On importes les images de toutes les pions
    ImageIcon img_vide = new javax.swing.ImageIcon(getClass().getResource("/images/Case_Vide.png"));
    ImageIcon img_pion_rouge = new javax.swing.ImageIcon(getClass().getResource("/images/Pion_Rouge.png"));
    ImageIcon img_pion_bleu = new javax.swing.ImageIcon(getClass().getResource("/images/Pion_Bleu.png"));
    ImageIcon img_roi_rouge = new javax.swing.ImageIcon(getClass().getResource("/images/Roi_Rouge.png"));
    ImageIcon img_roi_bleu = new javax.swing.ImageIcon(getClass().getResource("/images/Roi_Bleu.png"));
    ImageIcon img_castle_rouge = new javax.swing.ImageIcon(getClass().getResource("/images/Case_Castle_Down.png"));
    ImageIcon img_castle_bleu = new javax.swing.ImageIcon(getClass().getResource("/images/Case_Castle_Up.png"));
    
    //Chaque cellule possede un pion associé, des coordonnées, et peut etre le trone
    Pion pionCourant ;
    boolean trone ;
    int ligne ;
    int colonne ;
    
    //quand on construit la cellule, il faut au minimum les coordonnées associées
    public Cellule (int i, int j) {
        ligne=i;
        colonne=j;
    }
    
    //Affichage des pions
    @Override
    public void paintComponent ( Graphics G) {
        //System.out.println("testtt");
        //if (roi==true)System.out.println("true");
        super.paintComponent(G);
        //if (roi==true)System.out.println("true");
        if (pionCourant==null)setIcon(img_vide);
        else if(pionCourant.couleur=="bleu" && pionCourant.roi==false)setIcon(img_pion_bleu);
        else if(pionCourant.couleur=="rouge" && pionCourant.roi==false)setIcon(img_pion_rouge);
        else if(pionCourant.couleur=="bleu" && pionCourant.roi==true)setIcon(img_roi_bleu);
        else if(pionCourant.couleur=="rouge" && pionCourant.roi==true)setIcon(img_roi_rouge);
        else setIcon(img_vide);
    }
}
