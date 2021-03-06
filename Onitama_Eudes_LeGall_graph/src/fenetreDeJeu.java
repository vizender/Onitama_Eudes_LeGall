
import static com.sun.source.tree.Tree.Kind.MULTIPLY;
import java.util.Random;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author victorlegall
 */
public class fenetreDeJeu extends javax.swing.JFrame {

    Joueur[] ListeJoueurs = new Joueur[2];
    Joueur joueurCourant;
    Grille grilleJeu = new Grille();
    Pion pionCourant;
    Carte carteCourante;
    Carte[] CarteJeu = new Carte[5];

    /**
     * Creates new form fenetreDeJeu
     */
    public fenetreDeJeu() {
        initComponents();
        //panneau_info_joueurs.setVisible(false);
        panneau_info_partie.setVisible(false);
        jPvictoire.setVisible(false);
        for (int i = 4; i >= 0; i--) {
            for (int j = 0; j < 5; j++) {
                Cellule cellule = new Cellule(i, j);
                grilleJeu.ajouterCellule(cellule, i, j);

                cellule.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        System.out.println(joueurCourant.nom);
                        if (carteCourante != null) {
                            if (cellule.pionCourant != null && cellule.pionCourant.couleur == joueurCourant.couleur) {     //Si un pion du joueur courant
                                grilleJeu.selectCellule(cellule.ligne, cellule.colonne);
                                panneau_grille.repaint();
                            } else if (cellule.pionCourant == null && grilleJeu.celluleSelect[0] != 5) {
                                carteCourante.afficherGrille();
                                /* Pour la suite, on est obligé de faire des redondances entre rouge et bleu pour inverser les cartes
                                on ne peut pas simplifier avec des fonctions car on est dans un ActionListener, et on fait appel a ses variables locales, d'ou le (tres) long code.
                                */
                                if (joueurCourant.couleur == "rouge") {
                                    if (carteCourante.grilleCarte[cellule.ligne + 2 - grilleJeu.celluleSelect[0]][cellule.colonne + 2 - grilleJeu.celluleSelect[1]] == true) { //Si on clique sur une case jouable (sens rouge, inversé), et la case est vide
                                        System.out.println("C'EST CARREMENT LA WIN");
                                        grilleJeu.deplacerPion(cellule.ligne, cellule.colonne); // On deplace le pion
                                        if (cellule.pionCourant.roi == true && cellule.trone == true && cellule.couleur != joueurCourant.couleur) {
                                            Victoire();
                                        }
                                        changerCarte(carteCourante); // On echange des cartes
                                        System.out.println("Passe au joueur suivant");
                                        joueurSuivant(); // On passe au joueur suivant
                                    }
                                } else { // Meme chose, mais pour les bleus, la carte dans le sens bleu
                                    if (carteCourante.grilleCarte[4 - (cellule.ligne + 2 - grilleJeu.celluleSelect[0])][cellule.colonne + 2 - grilleJeu.celluleSelect[1]] == true) { //Si on clique sur une case vide
                                        grilleJeu.deplacerPion(cellule.ligne, cellule.colonne); // On deplace
                                        if (cellule.pionCourant.roi == true && cellule.trone == true && cellule.couleur != joueurCourant.couleur) {
                                            Victoire();
                                        }
                                        changerCarte(carteCourante); // On echange des cartes
                                        System.out.println("Passe au joueur suivant");
                                        joueurSuivant();
                                    }
                                }
                                
                            } // On refait une verif similaire, juste on verifie la presence d'un pion enemi sur la case
                            if (cellule.pionCourant != null && cellule.pionCourant.couleur != joueurCourant.couleur && grilleJeu.celluleSelect[0] != 5) { // Si on clique sur un pion adverse
                                if (joueurCourant.couleur == "rouge") {
                                    System.out.println("1er vagues");
                                    if (carteCourante.grilleCarte[cellule.ligne + 2 - grilleJeu.celluleSelect[0]][cellule.colonne + 2 - grilleJeu.celluleSelect[1]] == true) { //Si on clique sur une case vide
                                        System.out.println("2er vagues");
                                        if (cellule.pionCourant.roi == true) { // Si c'est un roi
                                            joueurSuivant();
                                            Victoire(); // On gagne
                                        }
                                        grilleJeu.deplacerPion(cellule.ligne, cellule.colonne); //On deplace le pion quoi qu'il arrive, roi ou pas
                                        joueurSuivant();
                                        panneau_grille.repaint();
                                    } 
                                }
                                else{ // Idem mais pour les bleus
                                    System.out.println("1er vagues");
                                    if (carteCourante.grilleCarte[4-(cellule.ligne + 2 - grilleJeu.celluleSelect[0])][cellule.colonne + 2 - grilleJeu.celluleSelect[1]] == true) { //Si on clique sur une case vide
                                        System.out.println("2er vagues");
                                        if (cellule.pionCourant.roi == true) { // Si c'est un roi
                                            joueurSuivant();
                                            Victoire(); // On gagne
                                        }

                                        grilleJeu.deplacerPion(cellule.ligne, cellule.colonne); //On deplace le pion
                                        if (grilleJeu.compterPions() != null) { // Si il n'y a plus de pions
                                            jLvictoire.setText("Victoire de " + joueurCourant.nom); //Victoire du joueur
                                            cellule.setEnabled(false);
                                            jPvictoire.setVisible(true);
                                            panneau_grille.repaint();
                                        }
                                        joueurSuivant();
                                        panneau_grille.repaint();
                                    } 
                                }
                            }
                        }

                    }
                }
                );
                panneau_grille.add(cellule);

                panneau_grille.repaint();
            }
        }
        creationJoueurs();
        Carte CarteJeu[] = creationCartes();

        
        

        j1_carte1.add(CarteJeu[0]);
        j1_carte2.add(CarteJeu[1]);
        j2_carte1.add(CarteJeu[2]);
        j2_carte2.add(CarteJeu[3]);
        att_carte.add(CarteJeu[4]);

        j1_carte1.repaint();
        j1_carte2.repaint();
        j2_carte1.repaint();
        j2_carte2.repaint();
        att_carte.repaint();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPvictoire = new javax.swing.JPanel();
        jLvictoire = new javax.swing.JLabel();
        panneau_grille = new javax.swing.JPanel();
        panneau_info_joueurs = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        nom_joueur2 = new javax.swing.JTextField();
        nom_joueur1 = new javax.swing.JTextField();
        jbt_start = new javax.swing.JButton();
        panneau_info_partie = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabelC1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabelJ1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabelC2 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        CCtext = new javax.swing.JLabel();
        jLabelJ2 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        CCenter = new javax.swing.JLabel();
        JCenter = new javax.swing.JLabel();
        panneau_carte6 = new javax.swing.JPanel();
        j1_carte2 = new javax.swing.JPanel();
        j2_carte2 = new javax.swing.JPanel();
        j2_carte1 = new javax.swing.JPanel();
        j1_carte1 = new javax.swing.JPanel();
        att_carte = new javax.swing.JPanel();
        btn_j1_c1 = new javax.swing.JButton();
        btn_j2_c1 = new javax.swing.JButton();
        btn_j2_c2 = new javax.swing.JButton();
        btn_j1_c2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPvictoire.setBackground(new java.awt.Color(240, 240, 230));
        jPvictoire.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "FELICITATION", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Imprint MT Shadow", 0, 24))); // NOI18N
        jPvictoire.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLvictoire.setFont(new java.awt.Font("Imprint MT Shadow", 0, 48)); // NOI18N
        jLvictoire.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLvictoire.setText("VICTOIRE !");
        jLvictoire.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPvictoire.add(jLvictoire, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 430, 90));

        getContentPane().add(jPvictoire, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, 430, 210));

        panneau_grille.setBackground(new java.awt.Color(240, 230, 230));
        panneau_grille.setLayout(new java.awt.GridLayout(5, 5));
        getContentPane().add(panneau_grille, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 200, 400, 400));

        panneau_info_joueurs.setBackground(new java.awt.Color(240, 200, 180));
        panneau_info_joueurs.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel1.setText("Joueur 2 :");
        panneau_info_joueurs.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel2.setText("Joueur 1 :");
        panneau_info_joueurs.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        nom_joueur2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        nom_joueur2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nom_joueur2ActionPerformed(evt);
            }
        });
        panneau_info_joueurs.add(nom_joueur2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 170, -1));

        nom_joueur1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        nom_joueur1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nom_joueur1ActionPerformed(evt);
            }
        });
        panneau_info_joueurs.add(nom_joueur1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, 170, -1));

        jbt_start.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jbt_start.setText("Start");
        jbt_start.setSelected(true);
        jbt_start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbt_startActionPerformed(evt);
            }
        });
        panneau_info_joueurs.add(jbt_start, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 120, -1, -1));

        getContentPane().add(panneau_info_joueurs, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 350, 180));

        panneau_info_partie.setBackground(new java.awt.Color(240, 200, 180));
        panneau_info_partie.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 26)); // NOI18N
        jLabel4.setText("Infos joueurs");
        panneau_info_partie.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabelC1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        panneau_info_partie.add(jLabelC1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 110, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        panneau_info_partie.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, -1, -1));

        jSeparator1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        panneau_info_partie.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 290, 10));

        jLabelJ1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        panneau_info_partie.add(jLabelJ1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 80, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        panneau_info_partie.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel10.setText("Joueur 1");
        panneau_info_partie.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        jLabelC2.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabelC2.setText(" ");
        panneau_info_partie.add(jLabelC2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 190, -1, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel11.setText("Couleur");
        panneau_info_partie.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel12.setText("Joueur 2");
        panneau_info_partie.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        CCtext.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        CCtext.setText("Carte Courante");
        panneau_info_partie.add(CCtext, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, -1, -1));

        jLabelJ2.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        panneau_info_partie.add(jLabelJ2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 160, -1, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel14.setText("Couleur");
        panneau_info_partie.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, -1, -1));

        jSeparator2.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        panneau_info_partie.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 290, 10));

        CCenter.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        CCenter.setText(" ");
        panneau_info_partie.add(CCenter, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 280, 130, -1));

        JCenter.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        JCenter.setText(" ");
        panneau_info_partie.add(JCenter, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 250, 130, -1));

        getContentPane().add(panneau_info_partie, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 450, 350, 330));

        panneau_carte6.setBackground(new java.awt.Color(240, 230, 200));
        panneau_carte6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(panneau_carte6, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 610, -1, -1));

        j1_carte2.setBackground(new java.awt.Color(240, 230, 200));
        j1_carte2.setLayout(new java.awt.GridLayout(1, 1));
        getContentPane().add(j1_carte2, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 610, 300, 174));

        j2_carte2.setBackground(new java.awt.Color(240, 230, 200));
        j2_carte2.setLayout(new java.awt.GridLayout(1, 1));
        getContentPane().add(j2_carte2, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 10, 300, 174));

        j2_carte1.setBackground(new java.awt.Color(240, 230, 200));
        j2_carte1.setLayout(new java.awt.GridLayout(1, 1));
        getContentPane().add(j2_carte1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 10, 300, 174));

        j1_carte1.setBackground(new java.awt.Color(240, 230, 200));
        j1_carte1.setLayout(new java.awt.GridLayout(1, 1));
        getContentPane().add(j1_carte1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 610, 300, 174));

        att_carte.setBackground(new java.awt.Color(240, 230, 200));
        att_carte.setLayout(new java.awt.GridLayout(1, 1));
        getContentPane().add(att_carte, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 260, 300, 174));

        btn_j1_c1.setText("selectionner carte");
        btn_j1_c1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_j1_c1ActionPerformed(evt);
            }
        });
        getContentPane().add(btn_j1_c1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 580, -1, -1));

        btn_j2_c1.setText("selectionner carte");
        btn_j2_c1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_j2_c1ActionPerformed(evt);
            }
        });
        getContentPane().add(btn_j2_c1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 190, -1, -1));

        btn_j2_c2.setText("selectionner carte");
        btn_j2_c2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_j2_c2ActionPerformed(evt);
            }
        });
        getContentPane().add(btn_j2_c2, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 200, -1, -1));

        btn_j1_c2.setText("selectionner carte");
        btn_j1_c2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_j1_c2ActionPerformed(evt);
            }
        });
        getContentPane().add(btn_j1_c2, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 580, -1, -1));

        setBounds(0, 0, 1075, 831);
    }// </editor-fold>//GEN-END:initComponents

    private void nom_joueur1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nom_joueur1ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_nom_joueur1ActionPerformed

    private void nom_joueur2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nom_joueur2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nom_joueur2ActionPerformed
    //Bouton de start
    private void jbt_startActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbt_startActionPerformed
        // TODO add your handling code here:
        panneau_info_joueurs.setVisible(true);
        panneau_info_partie.setVisible(true);
        initialiserPartie();
        //attribuerCouleurAuxJoueurs();
        panneau_grille.repaint();
    }//GEN-LAST:event_jbt_startActionPerformed

    
    //Les boutons suivants sont ceux pour choisir la carte
    //Il neut peuvent etre selectionner que pour les joueurs courants quansd se sont leurs propres cartes
    private void btn_j1_c1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_j1_c1ActionPerformed
        if (joueurCourant.couleur == "bleu") {  
            carteCourante = CarteJeu[0]; //Bouton bas droite
            System.out.println(carteCourante.nom);
            CCenter.setText(carteCourante.nom);
            panneau_grille.repaint();
        }
    }//GEN-LAST:event_btn_j1_c1ActionPerformed

    private void btn_j1_c2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_j1_c2ActionPerformed
        if (joueurCourant.couleur == "bleu") {
            carteCourante = CarteJeu[1]; //bouton bas gauche
            System.out.println(carteCourante.nom);
            CCenter.setText(carteCourante.nom);
            panneau_grille.repaint();
        }
    }//GEN-LAST:event_btn_j1_c2ActionPerformed

    private void btn_j2_c1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_j2_c1ActionPerformed
        if (joueurCourant.couleur == "rouge") {
            carteCourante = CarteJeu[2]; //bouton haut gauche
            System.out.println(carteCourante.nom);
            CCenter.setText(carteCourante.nom);
            panneau_grille.repaint();
        }
    }//GEN-LAST:event_btn_j2_c1ActionPerformed

    private void btn_j2_c2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_j2_c2ActionPerformed
        if (joueurCourant.couleur == "rouge") {
            carteCourante = CarteJeu[3]; //bouton haut droit
            System.out.println(carteCourante.nom);
            CCenter.setText(carteCourante.nom);
            panneau_grille.repaint();

        }
    }//GEN-LAST:event_btn_j2_c2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(fenetreDeJeu.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(fenetreDeJeu.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(fenetreDeJeu.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(fenetreDeJeu.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new fenetreDeJeu().setVisible(true);
            }
        });
    }

    //On change de joueur
    public void joueurSuivant() {
        if (joueurCourant == ListeJoueurs[0]) {
            joueurCourant = ListeJoueurs[1];
        } else {
            joueurCourant = ListeJoueurs[0];
        }
        carteCourante = null; // On remets la carte courante a null par precaution
        panneau_grille.repaint(); // On repaint des qu'on joue, ie quand on change de joueur
    }

    //attribution des couleurs non aléatoires, rouge en 0 par defaut
    public void attribuerCouleurAuxJoueurs() {
        ListeJoueurs[0].affecterCouleur("rouge");
        ListeJoueurs[1].affecterCouleur("bleu");
        joueurCourant = ListeJoueurs[0];
    }

    //Void pour creer et distribuer 5 cartes de jeu au debut de la partie
    public Carte[] creationCartes() {

        //On definit toutes les cartes, avec les coordonées jouables relatives au pion, et le nom.
        int[][] coordboar = {{2, 1}, {1, 2}, {2, 3}};
        Carte cboar = new Carte("boar", coordboar);

        int[][] coordcobra = {{2, 1}, {1, 3}, {3, 3}};
        Carte ccobra = new Carte("cobra", coordcobra);

        int[][] coordcrab = {{2, 0}, {1, 2}, {2, 4}};
        Carte ccrab = new Carte("crab", coordcrab);

        int[][] coordcrane = {{1, 2}, {3, 1}, {3, 3}};
        Carte ccrane = new Carte("crane", coordcrane);

        int[][] coorddragon = {{1, 0}, {1, 4}, {3, 1}, {3, 3}};
        Carte cdragon = new Carte("dragon", coorddragon);

        int[][] coordeel = {{1, 1}, {3, 1}, {2, 3}};
        Carte ceel = new Carte("eel", coordeel);

        int[][] coordelephant = {{1, 1}, {2, 1}, {1, 3}, {2, 3}};
        Carte celephant = new Carte("elephant", coordelephant);

        int[][] coordfrog = {{2, 0}, {1, 1}, {3, 3}};
        Carte cfrog = new Carte("frog", coordfrog);

        int[][] coordgoose = {{1, 1}, {2, 1}, {2, 3}, {3, 3}};
        Carte cgoose = new Carte("goose", coordgoose);

        int[][] coordhorse = {{1, 2}, {2, 1}, {3, 2}};
        Carte chorse = new Carte("horse", coordhorse);

        int[][] coordmantis = {{1, 1}, {1, 3}, {3, 2}};
        Carte cmantis = new Carte("mantis", coordmantis);

        int[][] coordmonkey = {{1, 1}, {1, 3}, {3, 1}, {3, 3}};
        Carte cmonkey = new Carte("monkey", coordmonkey);

        int[][] coordox = {{1, 2}, {3, 2}, {2, 3}};
        Carte cox = new Carte("ox", coordox);

        int[][] coordrabbit = {{1, 3}, {3, 1}, {2, 4}};
        Carte crabbit = new Carte("rabbit", coordrabbit);

        int[][] coordrooster = {{2, 1}, {3, 1}, {1, 3}, {2, 3}};
        Carte crooster = new Carte("rooster", coordrooster);

        int[][] coordtiger = {{0, 2}, {3, 2}};
        Carte ctiger = new Carte("tiger", coordtiger);

        //On mets ttes les cartes dans un tableau qu'on va ensuite piocher
        Carte[] TabCartes = {cboar, ccobra, ccrab, ccrane, cdragon, ceel, celephant, cfrog,
            cgoose, chorse, cmantis, cmonkey, cox, crabbit, crooster, ctiger};

        //Tableau des 5 cartes utilisées dans une partie
        Random r = new Random();
        boolean test;

        //Shenanigans pour selectionner 5 cartes au hasard sans doublons
        //PS : on ne fait pas d'operations sur le tableau des 16 cartes originels par securite, donc la fonction ci-dessous est pas belle du tout
        for (int i = 0; i < 5; i++) { // On repete l'operation pour les 5 cartes
            do { // On repete une selection de carte tant que y'a doublons
                int R = r.nextInt(16); //On prends aleatoirement une des 16 cartes du tableau ci dessus
                test = false;
                int tot = 0; // tot et test vont servir de verification aux doublons (pas tres joli mais fonctionnel)
                CarteJeu[i] = TabCartes[R]; // On assinge la carte aleatoirement choisie au tableau carte de jeu
                for (int j = 0; j < i; j++) { // On verifie que y'a pas de les doublons un a un avec les precedentes cartes
                    if (CarteJeu[i] != CarteJeu[j]) {
                        tot++; // Comme on verifie un a un, on utilise tot pour confirmer que tout est bon
                    }
                }
                if (tot == i) {
                    test = true; // Si toutes les cartes precedentes sont bien differentes, alors on est bon
                }
            } while (test == false); // On confirme pas de doublon, on passe a l'attribution de la carte suivante
        }
        //On rajoute les cartes dans le jpanel via le hashage de la classe carte
        j1_carte1.add(CarteJeu[0]);
        j1_carte2.add(CarteJeu[1]);
        j2_carte1.add(CarteJeu[2]);
        j2_carte2.add(CarteJeu[3]);
        att_carte.add(CarteJeu[4]);

        j1_carte1.repaint();
        j1_carte2.repaint();
        j2_carte1.repaint();
        j2_carte2.repaint();
        att_carte.repaint();

        if (ListeJoueurs[0].couleur == "rouge") {
            ListeJoueurs[1].cartes[0] = CarteJeu[0];
            ListeJoueurs[1].cartes[1] = CarteJeu[1];
            ListeJoueurs[0].cartes[0] = CarteJeu[2];
            ListeJoueurs[0].cartes[1] = CarteJeu[3];
        } else {
            ListeJoueurs[0].cartes[0] = CarteJeu[0];
            ListeJoueurs[0].cartes[1] = CarteJeu[1];
            ListeJoueurs[1].cartes[0] = CarteJeu[2];
            ListeJoueurs[1].cartes[1] = CarteJeu[3];
        }

        return CarteJeu;
    }

    public void changerCarte(Carte carteAChanger) {
        int k = 0;
        for (int i = 0; i <= 4; i++) {
            if (carteAChanger == CarteJeu[i]) {
                k = i;
            }
        }
        Carte att;
        att = CarteJeu[k];
        CarteJeu[k] = CarteJeu[4];
        CarteJeu[4] = att;

        j1_carte1.add(CarteJeu[0]);
        j1_carte2.add(CarteJeu[1]);
        j2_carte1.add(CarteJeu[2]);
        j2_carte2.add(CarteJeu[3]);
        att_carte.add(CarteJeu[4]);

        j1_carte1.repaint();
        j1_carte2.repaint();
        j2_carte1.repaint();
        j2_carte2.repaint();
        att_carte.repaint();

    }

    public void creationPion() {
        for (int i = 0; i < 5; i++) {
            grilleJeu.tabCellule[0][i].pionCourant = new Pion("bleu", false);
            grilleJeu.tabCellule[4][i].pionCourant = new Pion("rouge", false);
        }
        grilleJeu.tabCellule[0][2].pionCourant.roi = true;
        grilleJeu.tabCellule[4][2].pionCourant.roi = true;
    }

    public void creationJoueurs() {
        String nomJoueur1 = nom_joueur1.getText();
        Joueur j1 = new Joueur(nomJoueur1);
        String nomJoueur2 = nom_joueur2.getText();
        Joueur j2 = new Joueur(nomJoueur2);

        Random r = new Random();
        int R = r.nextInt(2); // on créé ici un entier aléatoire entre 0 et 1
        if (R == 0) {
            ListeJoueurs[0] = j1;
            ListeJoueurs[1] = j2;
        } else {
            ListeJoueurs[1] = j1;
            ListeJoueurs[0] = j2;
        }

        this.attribuerCouleurAuxJoueurs();

        jLabelJ1.setText(nomJoueur1);
        jLabelJ2.setText(nomJoueur2);
        jLabelC1.setText(j1.couleur);
        jLabelC2.setText(j2.couleur);
        JCenter.setText(ListeJoueurs[0].nom);
    }

    public void initialiserPartie() {

        grilleJeu.tabCellule[0][2].trone = true;    //initialisation des trones
        grilleJeu.tabCellule[4][2].trone = true;
        grilleJeu.tabCellule[0][2].couleur = "bleu";
        grilleJeu.tabCellule[4][2].couleur = "rouge";
        creationPion();
    }

    public void Victoire() {
        for (int i = 4; i >= 0; i--) {
            for (int j = 0; j < 5; j++) {
                grilleJeu.tabCellule[i][j].setEnabled(false);
            }
        }

        for (int i = 4; i < 5; i++) {
            att_carte.setVisible(false);
        }
        jLvictoire.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPvictoire.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CCenter;
    private javax.swing.JLabel CCtext;
    private javax.swing.JLabel JCenter;
    private javax.swing.JPanel att_carte;
    private javax.swing.JButton btn_j1_c1;
    private javax.swing.JButton btn_j1_c2;
    private javax.swing.JButton btn_j2_c1;
    private javax.swing.JButton btn_j2_c2;
    private javax.swing.JPanel j1_carte1;
    private javax.swing.JPanel j1_carte2;
    private javax.swing.JPanel j2_carte1;
    private javax.swing.JPanel j2_carte2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelC1;
    private javax.swing.JLabel jLabelC2;
    private javax.swing.JLabel jLabelJ1;
    private javax.swing.JLabel jLabelJ2;
    private javax.swing.JLabel jLvictoire;
    private javax.swing.JPanel jPvictoire;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JButton jbt_start;
    private javax.swing.JTextField nom_joueur1;
    private javax.swing.JTextField nom_joueur2;
    private javax.swing.JPanel panneau_carte6;
    private javax.swing.JPanel panneau_grille;
    private javax.swing.JPanel panneau_info_joueurs;
    private javax.swing.JPanel panneau_info_partie;
    // End of variables declaration//GEN-END:variables
}
