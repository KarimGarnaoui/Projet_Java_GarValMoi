package Vue;

/**Librairie importé*/
import Modele.Connexion;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.StringStack;
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

/** Classe permettant d'ajouter un élément*/
public class AjoutElem extends JFrame {
    
    private Connexion connexion ; 
    private JPanel ajoutPanel ; 
    private String[] tabChampsAjout, tabTablesAjout;
    private JComboBox tableAjout, fonctionEmploye,  champ7,  champ8 ; 
    private JLabel tableAjoutLabel ; 
    private JTextField champ1, champ2, champ3, champ4, champ5, champ6, champ9 ; 
    private JLabel champ1Label, champ2Label, champ3Label, champ4Label, champ5Label, champ6Label, champ7Label, champ8Label, champ9Label ; 
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
            
            // Déclare la fenêtre d'ajout
            this.setTitle("Ajouter");
            this.setSize(520,400);
            this.setLocation(954,30);
            this.setResizable(false);
            this.setLayout(new BorderLayout()); 
            
            ajoutPanel = new JPanel() ; 
            ajoutPanel.setBorder(BorderFactory.createTitledBorder("Ajouter"));
            ajoutPanel.setLayout(null);
            ajoutPanel.setPreferredSize(new Dimension(500,370));
            
            tabChampsAjout = new String[]{} ; 
            tabTablesAjout = new String[]{"--Selectionner--" , "employe" , "malade" } ;
            tableAjout = new JComboBox(tabTablesAjout) ;
            tableAjoutLabel = new JLabel() ;
            tableAjout.setBounds(120,30,120,25);
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
            
            champ7 = new JComboBox(new String[]{"REA","CHG","CAR"}) ;
            champ8 = new JComboBox(new String[]{"JOUR","NUIT"}) ;
            champ9 = new JTextField() ;
            
            champ1Label = new JLabel("Champ1") ;
            champ2Label = new JLabel("Champ2") ;
            champ3Label = new JLabel("Champ3") ;
            champ4Label = new JLabel("Champ4") ;
            champ5Label = new JLabel("Champ5") ;
            champ6Label = new JLabel("Champ6") ;
            
            champ7Label = new JLabel("code_service") ;
            champ8Label = new JLabel("rotation") ;
            champ9Label = new JLabel("salaire") ;
            
            fonctionEmploye = new JComboBox(new String[]{"Pneumologue","Cardiologue","Orthopediste","Traumatologue","Anesthesiste","Radiologue","Infirmier"}) ; 
            fonctionEmploye.setBounds(120,240,120,25);
            fonctionEmploye.addActionListener(new ModifListeFonction());
            
            champ1.setBounds(120,65,120,25);
            champ2.setBounds(120,100,120,25);
            champ3.setBounds(120,135,120,25);
            champ4.setBounds(120,170,120,25);
            champ5.setBounds(120,205,120,25);
            champ6.setBounds(120,240,120,25);
            champ7.setBounds(370,65,120,25);
            champ8.setBounds(370,100,120,25);
            champ9.setBounds(370,135,120,25);
            
            champ1Label.setBounds(30,65,100,25);
            champ2Label.setBounds(30,100,100,25);
            champ3Label.setBounds(30,135,100,25);
            champ4Label.setBounds(30,170,100,25);
            champ5Label.setBounds(30,205,100,25);
            champ6Label.setBounds(30,240,100,25);
            champ7Label.setBounds(260,65,120,25);
            champ8Label.setBounds(260,100,120,25);
            champ9Label.setBounds(260,135,120,25);
            
            champ7.setVisible(false);
            champ8.setVisible(false);
            champ9.setVisible(false);
            champ7Label.setVisible(false);
            champ8Label.setVisible(false);
            champ9Label.setVisible(false);
            
            
            ajoutPanel.add(champ1Label) ; 
            ajoutPanel.add(champ2Label) ; 
            ajoutPanel.add(champ3Label) ; 
            ajoutPanel.add(champ4Label) ; 
            ajoutPanel.add(champ5Label) ; 
            ajoutPanel.add(champ6Label) ; 
            ajoutPanel.add(champ7Label) ; 
            ajoutPanel.add(champ8Label) ; 
            ajoutPanel.add(champ9Label) ; 
            
            ajoutPanel.add(champ1) ;
            ajoutPanel.add(champ2) ;
            ajoutPanel.add(champ3) ;
            ajoutPanel.add(champ4) ;
            ajoutPanel.add(champ5) ;
            ajoutPanel.add(champ6) ;
            ajoutPanel.add(champ7) ;
            ajoutPanel.add(champ8) ;
            ajoutPanel.add(champ9) ;
            
            ajoutPanel.add(fonctionEmploye) ; 
            
            ajouterButton = new JButton("Ajouter") ; 
            ajouterButton.setBounds(280,290,100,25);
            ajouterButton.addActionListener(new BoutonAjouter());
            ajoutPanel.add(ajouterButton) ; 
            
            this.add(ajoutPanel, BorderLayout.NORTH) ; 
            this.setVisible(true);  
    }
            
           /** Permet de modifier les champs à saisir en focntion de la table selectionné*/ 
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
                        champ6.setVisible(false);
                        fonctionEmploye.setVisible(true);
                        champ6Label.setText("fonction");
                    }
                    if(tableAjout.getSelectedItem().toString().equals("malade")){
                        champ1Label.setText("numero");
                        champ2Label.setText("nom");
                        champ3Label.setText("prenom");
                        champ4Label.setText("adresse");
                        champ5Label.setText("tel");
                        champ6.setVisible(true);
                        fonctionEmploye.setVisible(false);
                        champ6Label.setText("mutuelle");
                    }
                    
                }
            }
            
           
            class ModifListeFonction implements ActionListener 
            {

                @Override
                public void actionPerformed(ActionEvent e)
                {
                    if(fonctionEmploye.getSelectedItem().toString().equals("Infirmier")){
                       champ7.setVisible(true);
                       champ8.setVisible(true);
                       champ9.setVisible(true);
                       champ7Label.setVisible(true);
                       champ8Label.setVisible(true);
                       champ9Label.setVisible(true);
                    }
                    else{
                        champ7.setVisible(false);
                       champ8.setVisible(false);
                       champ9.setVisible(false);
                       champ7Label.setVisible(false);
                       champ8Label.setVisible(false);
                       champ9Label.setVisible(false);
                    }
                }
            }
            
            /** Si on appuie sur le bouton ajouter on ajoute un élément à la BDD*/
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
                   if(!champ1_.equals("") && !champ2_.equals("") && !champ3_.equals("") && !champ4_.equals("") && !champ5_.equals("") && !table1.equals("--Selectionner--"))
                   {
                   if(table1.equals("employe")){
                       if(fonctionEmploye.getSelectedItem().toString().equals("Infirmier") && !champ9.getText().equals(""))
                       {
                           System.out.println("INSERT INTO employe VALUES "+"('"+champ1_+"','"+champ2_+"','"+champ3_+"','"+champ4_+"','"+champ5_+"')");
                           connexion.executeUpdate("INSERT INTO employe VALUES "+"('"+champ1_+"','"+champ2_+"','"+champ3_+"','"+champ4_+"','"+champ5_+"')");
                           System.out.println("INSERT INTO infirmier VALUES "+"('"+champ1_+"','"+champ7.getSelectedItem().toString()+"','"+champ8.getSelectedItem().toString()+"','"+champ9.getText()+"')");
                           connexion.executeUpdate("INSERT INTO infirmier VALUES "+"('"+champ1_+"','"+champ7.getSelectedItem().toString()+"','"+champ8.getSelectedItem().toString()+"','"+champ9.getText()+"')");
                       }
                       if(fonctionEmploye.getSelectedItem().toString().equals("Pneumologue") ||
                          fonctionEmploye.getSelectedItem().toString().equals("Cardiologue") ||
                          fonctionEmploye.getSelectedItem().toString().equals("Orthopediste") ||
                          fonctionEmploye.getSelectedItem().toString().equals("Traumatologue") ||
                          fonctionEmploye.getSelectedItem().toString().equals("Anesthesiste") ||
                          fonctionEmploye.getSelectedItem().toString().equals("Radiologue")) 
                       {
                           System.out.println("INSERT INTO employe VALUES "+"('"+champ1_+"','"+champ2_+"','"+champ3_+"','"+champ4_+"','"+champ5_+"')");
                           connexion.executeUpdate("INSERT INTO employe VALUES "+"('"+champ1_+"','"+champ2_+"','"+champ3_+"','"+champ4_+"','"+champ5_+"')");
                           System.out.println("INSERT INTO docteur VALUES "+"('"+champ1_+"','"+fonctionEmploye.getSelectedItem().toString()+"')");
                           connexion.executeUpdate("INSERT INTO docteur VALUES "+"('"+champ1_+"','"+fonctionEmploye.getSelectedItem().toString()+"')");
                       }
                   }
                   if(table1.equals("malade")){
                           System.out.println("INSERT INTO "+table1+" VALUES "+"("+champ1_+","+champ2_+","+champ3_+","+champ4_+","+champ5_+","+champ6_+")");
                           connexion.executeUpdate("INSERT INTO "+table1+" VALUES "+"('"+champ1_+"','"+champ2_+"','"+champ3_+"','"+champ4_+"','"+champ5_+"','"+champ6_+"')");
                       }
                   }
                      } catch (SQLException ex) {
                           Logger.getLogger(AjoutElem.class.getName()).log(Level.SEVERE, null, ex);  
                   }
                }
            }   
}