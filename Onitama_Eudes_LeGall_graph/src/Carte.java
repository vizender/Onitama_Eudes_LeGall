/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.awt.Graphics;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
/**
 *
 * @author victorlegall
 */
public class Carte extends JButton {
    
    //On ajoute toutes les images
    ImageIcon carte_boar = new javax.swing.ImageIcon(getClass().getResource("/cartes/boar.jpg"));
    ImageIcon carte_cobra = new javax.swing.ImageIcon(getClass().getResource("/cartes/cobra.jpg"));
    ImageIcon carte_crab = new javax.swing.ImageIcon(getClass().getResource("/cartes/crab.jpg"));
    ImageIcon carte_crane = new javax.swing.ImageIcon(getClass().getResource("/cartes/crane.jpg"));
    ImageIcon carte_dragon = new javax.swing.ImageIcon(getClass().getResource("/cartes/dragon.jpg"));
    ImageIcon carte_eel = new javax.swing.ImageIcon(getClass().getResource("/cartes/eel.jpg"));
    ImageIcon carte_elephant = new javax.swing.ImageIcon(getClass().getResource("/cartes/elephant.jpg"));
    ImageIcon carte_frog = new javax.swing.ImageIcon(getClass().getResource("/cartes/frog.jpg"));
    ImageIcon carte_goose = new javax.swing.ImageIcon(getClass().getResource("/cartes/goose.jpg"));
    ImageIcon carte_horse = new javax.swing.ImageIcon(getClass().getResource("/cartes/horse.jpg"));
    ImageIcon carte_mantis = new javax.swing.ImageIcon(getClass().getResource("/cartes/mantis.jpg"));
    ImageIcon carte_monkey = new javax.swing.ImageIcon(getClass().getResource("/cartes/monkey.jpg"));
    ImageIcon carte_ox = new javax.swing.ImageIcon(getClass().getResource("/cartes/ox.jpg"));
    ImageIcon carte_rabbit = new javax.swing.ImageIcon(getClass().getResource("/cartes/rabbit.jpg"));
    ImageIcon carte_rooster = new javax.swing.ImageIcon(getClass().getResource("/cartes/rooster.jpg"));
    ImageIcon carte_tiger = new javax.swing.ImageIcon(getClass().getResource("/cartes/tiger.jpg"));
    
    String nom;
    
    
    // On creer une grille de booleens, false si on ne peut pas joueur la sur la position de la grille, true si on peut joueur cette position
    boolean [][] grilleCarte = new boolean[5][5];
    
    static HashMap h = new HashMap(); // On creer un hashage dans lequel les images 
    
    //On initialise la carte a false partout
    public Carte(String nom, int [][] tabCoords){
        for (int i=0; i<tabCoords.length; i++){
            int x=tabCoords[i][0];
            int y=tabCoords[i][1];
            grilleCarte[x][y]=true;
        }
        h.put("boar", carte_boar);
        h.put("cobra", carte_crab);
        h.put("crab", carte_crab);
        h.put("crane", carte_crane);
        h.put("dragon", carte_dragon);
        h.put("eel", carte_eel);
        h.put("elephant", carte_elephant);
        h.put("frog", carte_frog);
        h.put("goose", carte_goose);
        h.put("horse", carte_horse);
        h.put("mantis", carte_mantis);
        h.put("monkey", carte_monkey);
        h.put("ox", carte_ox);
        h.put("rabbit", carte_rabbit);
        h.put("rooster", carte_rooster);
        h.put("tiger", carte_tiger);
    }
    
    
    //Affichage des cartes via le hashage, on apelle le nom de la carte, qui est la clef correspondante a l'img de la carte
    @Override
    public void  paintComponent ( Graphics G) {
        ImageIcon result = (ImageIcon) h.get(nom);
        super.paintComponent(G);
        setIcon(result); 
    }
    
}


