package Vue;

/** Librairie importé*/
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import Modele.Connexion;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/** Classe permettant d'afficher le menu principale*/
public class FenetreModules extends JFrame {
     
    private JButton BoutonMAJ, BoutonRecherche, BoutonReporting, Quitter ; 
    private Connexion connexion ; 
    
    public FenetreModules(Connexion connexion1){
        
        // Déclaration de la fenêtre
        super();
        connexion = connexion1 ; 
        this.setTitle("Menu principal");
        this.setSize(400,400);
        this.setLocation(50,30);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        
        // déclaration des boutons
        BoutonMAJ = new JButton("Mise à jour des données") ;
        BoutonRecherche = new JButton("Rechercher") ;
        BoutonReporting = new JButton("Reporting");
        Quitter = new JButton("Quitter");
        
        BoutonMAJ.setBounds(100,40,180,50);
        BoutonRecherche.setBounds(100,110,180,50);
        BoutonReporting.setBounds(100,180,180,50);
        Quitter.setBounds(300,320,75,25);
        
        BoutonRecherche.addActionListener(new BoutonRechercher());
        BoutonMAJ.addActionListener(new BoutonMAJ());
        Quitter.addActionListener(new BoutonQuitter());
        BoutonReporting.addActionListener(new BoutonReporting());
        
        // On ajoute les éléments sur la fenêtre
        this.add(BoutonMAJ) ; 
        this.add(BoutonRecherche) ; 
        this.add(BoutonReporting) ;
        this.add(Quitter) ;
       
        // On rend la fenêtre visible
        this.setVisible(true);
               
    }
    
    /** Si on appuie sur le bouton rechercher on lance le module rechercher*/
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
       /** Si on appuie sur le bouton mise à jour on lance le module mise à jour*/
       class BoutonMAJ implements ActionListener{
           
            @Override
            public void actionPerformed(ActionEvent e){
                
                try {
                    ModuleMAJ maj = new ModuleMAJ(connexion) ;
                } catch (SQLException ex) {
                    Logger.getLogger(FenetreModules.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
      
    }
    
    /** Si on appuie sur le bouton quitter on quitte le programme*/
    class BoutonQuitter implements ActionListener{
           
            @Override
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
      
    }
    
    /** Si on appuie sur le bouton reporting on lance le module reporting*/
    class BoutonReporting implements ActionListener{
           
            @Override
            public void actionPerformed(ActionEvent e){
                
                ModuleReporting reporting = new ModuleReporting(connexion) ;
                
            }
      
    }
    
}
