/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

/**
 *
 * @author pierr
 */

import Modele.Connexion;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;


public class ModuleReporting extends JFrame {
    
    private Connexion connexion ; 
    private JComboBox comboChoix ; 
    private JPanel panChoix , panResultat ; 
    private JLabel labelChoix ; 
    private JButton valider , retour ; 
    
    public ModuleReporting(Connexion connexion1){
        
        super();
        connexion = connexion1 ;
        this.setTitle("Reporting");
        this.setSize(800,600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        
        panChoix = new JPanel() ; 
        panChoix.setBorder(BorderFactory.createTitledBorder("Choix du diagramme"));
        panChoix.setLayout(null);
        panChoix.setPreferredSize(new Dimension(750,150));
        
        
        panResultat = new JPanel() ; 
        panResultat.setBorder(BorderFactory.createTitledBorder("Resultat"));
        panResultat.setLayout(null);
        panResultat.setPreferredSize(new Dimension(750,420));
        
        String[] tab = {"Nombre de malade par service","Nombre d'infirmiers par service","Nombre de malade par mutuelle"} ; 
        comboChoix = new JComboBox(tab);
        comboChoix.setBounds(100,40,300,25);
        labelChoix = new JLabel("Choix : ") ; 
        labelChoix.setBounds(20,40,50,25);
        
        valider = new JButton("Valider");
        valider.setBounds(680,110,100,25);
        retour = new JButton("Retour");
        retour.setBounds(570,110,100,25);
        retour.addActionListener(new ActionRetour());
        
        panChoix.add(comboChoix) ; 
        panChoix.add(labelChoix) ;
        panChoix.add(valider) ;
        panChoix.add(retour) ;
        
        
        this.getContentPane().add(panChoix,BorderLayout.NORTH);
        this.getContentPane().add(panResultat,BorderLayout.SOUTH);
        
        
        this.setVisible(true);
    }
    
    class ActionRetour implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e)
        {
            dispose();

        }
    }
    
}
    

