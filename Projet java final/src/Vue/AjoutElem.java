/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Modele.Connexion;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author karim
 */
public class AjoutElem extends JFrame {
    
    private Connexion connexion ; 
    private JPanel ajoutPanel ; 
    private String[] tabChampsAjout, tabTablesAjout;
    private JComboBox tableAjout ; 
    private JLabel tableAjoutLabel ; 
    private JTextField champ1, champ2, champ3, champ4, champ5, champ6 ; 
    private JLabel champ1Label, champ2Label, champ3Label, champ4Label, champ5Label, champ6Label ; 
    private JButton ajouterButton ; 
    
    
//    String table1 = table.getSelectedItem().toString();
//            String champ1 = champs.getSelectedItem().toString();
//            String clefSelect = clefModif.getText();
//            String clef1 = clefModifLabel.getText();
//            String new_val = valeur.getText();
            
//            connexion.executeUpdate("INSERT INTO docteur VALUES ('6547','Pneumologue')");
            
          
//                try 
//                { 
//                    System.out.println("Requête SQL : UPDATE  "+table1+" SET "+champ1+" = '"+new_val+"' WHERE "+clef1+" = '"+clefSelect+"'");
//                    connexion.executeUpdate("UPDATE  "+table1+" SET "+champ1+" = '"+new_val+"' WHERE "+clef1+" = '"+clefSelect+"'");
//                } 
//                catch (SQLException ex) 
//                {
//                    Logger.getLogger(ModuleRechercher.class.getName()).log(Level.SEVERE, null, ex);
//                }
            
    public AjoutElem(Connexion connexion1){
        
            connexion = connexion1 ; 
            
            this.setTitle("Ajouter");
            this.setSize(420,370);
            this.setLocationRelativeTo(null);
            this.setResizable(false);
            this.setLayout(new BorderLayout()); 
            
            ajoutPanel = new JPanel() ; 
            ajoutPanel.setBorder(BorderFactory.createTitledBorder("Ajouter"));
            ajoutPanel.setLayout(null);
            ajoutPanel.setPreferredSize(new Dimension(400,350));
            
            tabChampsAjout = new String[]{} ; 
            tabTablesAjout = new String[]{"--Selectionner--" , "employe" , "malade" } ;
            tableAjout = new JComboBox(tabTablesAjout) ;
            tableAjoutLabel = new JLabel() ;
            tableAjout.setBounds(120,30,200,25);
            tableAjout.addActionListener(new ModifListeAjout());
            tableAjoutLabel = new JLabel("Table : ") ;
            tableAjoutLabel.setBounds(30,30,100,25);
            ajoutPanel.add(tableAjoutLabel) ; 
            ajoutPanel.add(tableAjout) ;
            
            champ1 = new JTextField() ;
            champ2 = new JTextField() ;
            champ3 = new JTextField() ;
            champ4 = new JTextField() ;
            champ5 = new JTextField() ;
            champ6 = new JTextField() ;
            
            champ1Label = new JLabel("Champ1") ;
            champ2Label = new JLabel("Champ2") ;
            champ3Label = new JLabel("Champ3") ;
            champ4Label = new JLabel("Champ4") ;
            champ5Label = new JLabel("Champ5") ;
            champ6Label = new JLabel("Champ6") ;
            
            champ1.setBounds(120,65,200,25);
            champ2.setBounds(120,100,200,25);
            champ3.setBounds(120,135,200,25);
            champ4.setBounds(120,170,200,25);
            champ5.setBounds(120,205,200,25);
            champ6.setBounds(120,240,200,25);
            
            champ1Label.setBounds(30,65,100,25);
            champ2Label.setBounds(30,100,100,25);
            champ3Label.setBounds(30,135,100,25);
            champ4Label.setBounds(30,170,100,25);
            champ5Label.setBounds(30,205,100,25);
            champ6Label.setBounds(30,240,100,25);
            
            
            ajoutPanel.add(champ1Label) ; 
            ajoutPanel.add(champ2Label) ; 
            ajoutPanel.add(champ3Label) ; 
            ajoutPanel.add(champ4Label) ; 
            ajoutPanel.add(champ5Label) ; 
            ajoutPanel.add(champ6Label) ; 
            
            ajoutPanel.add(champ1) ;
            ajoutPanel.add(champ2) ;
            ajoutPanel.add(champ3) ;
            ajoutPanel.add(champ4) ;
            ajoutPanel.add(champ5) ;
            ajoutPanel.add(champ6) ;
            
            ajouterButton = new JButton("Ajouter") ; 
            ajouterButton.setBounds(280,290,100,25);
            ajouterButton.addActionListener(new BoutonAjouter());
            ajoutPanel.add(ajouterButton) ; 
            
            this.add(ajoutPanel, BorderLayout.NORTH) ; 
            this.setVisible(true);  
    }
            
            class ModifListeAjout implements ActionListener 
            {

                @Override
                public void actionPerformed(ActionEvent e)
                {
                    if(tableAjout.getSelectedItem().toString().equals("employe")){
                        champ1Label.setText("numero");
                        champ2Label.setText("nom");
                        champ3Label.setText("prenom");
                        champ4Label.setText("adresse");
                        champ5Label.setText("tel");
                        champ6Label.setVisible(false);
                        champ6.setVisible(false);
                    }
                    if(tableAjout.getSelectedItem().toString().equals("malade")){
                        champ1Label.setText("numero");
                        champ2Label.setText("nom");
                        champ3Label.setText("prenom");
                        champ4Label.setText("adresse");
                        champ5Label.setText("tel");
                        champ6Label.setVisible(true);
                        champ6.setVisible(true);
                        champ6Label.setText("mutuelle");
                    }
                    
                }
            }
            
            class BoutonAjouter implements ActionListener 
            {
                
                @Override
                public void actionPerformed(ActionEvent e)
                {
                   String table1 = tableAjout.getSelectedItem().toString() ; 
                   String champ1_ = champ1.getText() ; 
                   String champ2_ = champ2.getText() ; 
                   String champ3_ = champ3.getText() ; 
                   String champ4_ = champ4.getText() ; 
                   String champ5_ = champ5.getText() ; 
                   String champ6_ = champ6.getText() ; 
                   try {
                   if(table1.equals("employe")){
                           System.out.println("INSERT INTO "+table1+" VALUES "+"('"+champ1_+"','"+champ2_+"','"+champ3_+"','"+champ4_+"','"+champ5_+"')");
                           connexion.executeUpdate("INSERT INTO "+table1+" VALUES "+"('"+champ1_+"','"+champ2_+"','"+champ3_+"','"+champ4_+"','"+champ5_+"')");
                       }
                   if(table1.equals("malade")){
                           System.out.println("INSERT INTO "+table1+" VALUES "+"("+champ1_+","+champ2_+","+champ3_+","+champ4_+","+champ5_+","+champ6_+")");
                           connexion.executeUpdate("INSERT INTO "+table1+" VALUES "+"('"+champ1_+"','"+champ2_+"','"+champ3_+"','"+champ4_+"','"+champ5_+"','"+champ6_+"')");
                       }
                   
                   } catch (SQLException ex) {
                           Logger.getLogger(AjoutElem.class.getName()).log(Level.SEVERE, null, ex);  
                   }
                }
            }
           
    
}