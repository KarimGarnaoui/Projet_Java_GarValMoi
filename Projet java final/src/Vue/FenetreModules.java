/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import Modele.Connexion;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author karim
 */
public class FenetreModules extends JFrame {
     
    private JButton BoutonMAJ, BoutonRecherche, BoutonReporting ; 
    private Connexion connexion ; 
    
    public FenetreModules(Connexion connexion1){
        
        super();
        connexion = connexion1 ; 
        this.setTitle("Menu");
        this.setSize(240,220);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        
        BoutonMAJ = new JButton("Mise à jour des données") ;
        BoutonRecherche = new JButton("Rechercher") ;
        BoutonReporting = new JButton("Reporting");
        
        this.add(BoutonMAJ) ; 
        this.add(BoutonRecherche) ; 
        this.add(BoutonReporting) ;
        
        BoutonMAJ.setBounds(20,20,180,40);
        BoutonRecherche.setBounds(20,70,180,40);
        BoutonReporting.setBounds(20,120,180,40);
        
        BoutonRecherche.addActionListener(new BoutonRechercher());
        
        
        
        this.setVisible(true);
               
    }
    
    class BoutonRechercher implements ActionListener{
           
            @Override
            public void actionPerformed(ActionEvent e){
                
                try {
                    ModuleRechercher recherche = new ModuleRechercher(connexion) ;
                } catch (SQLException ex) {
                    Logger.getLogger(FenetreModules.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
      
    }
    
}
