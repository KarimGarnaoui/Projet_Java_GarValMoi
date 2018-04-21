package Vue;

import Modele.Connexion;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.StringStack;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultTreeCellEditor;


public class ModuleMAJ extends JFrame
{
    private final JLabel tableLabel, champsLabel, valeurLabel, table2Label, clefSuppLabel, clefModifLabel ; 
    private  JComboBox table, champs, table2 ; 
    private final JTextField valeur, clef, clefModif ;
    
    private  JTable tableResultats ; 
    private final JButton Supprimer,Ajouter,Modifier ; 
    private final Connexion connexion ;
    private JPanel modifPan, suppPan, ajoutPan ; 
    private String ChoixMAJ; 
    private String[] titreColonnes ; 
    private String[][] donnees ; 
    private JScrollPane tableResultatsDeroulant ; 
    private String[] tabChamps, tabTables ;
    
    public ModuleMAJ(Connexion connexion1) throws SQLException
    {
        super();
        connexion = connexion1;
        ChoixMAJ = "=";
        this.setTitle("Mise à jour des données");
        this.setSize(500,400);
        this.setLocation(452, 30);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        
        /////////////Modification /////////////////
        modifPan = new JPanel() ;
        modifPan.setBorder(BorderFactory.createTitledBorder("Modifier"));
        modifPan.setLayout(null);
        modifPan.setPreferredSize(new Dimension(490,190));
        
        
        tabTables = new String[]{"--Selectionner--" , "employe" , "docteur" , "infirmier" , "malade" } ;
        table = new JComboBox(tabTables);
        table.setBounds(120,30,150,25);
        table.addActionListener(new ModifListe());
        tableLabel = new JLabel("Table : ") ;
        tableLabel.setBounds(20,30,100,25);
        modifPan.add(tableLabel) ; 
        modifPan.add(table) ; 
        
        
        tabChamps = new String[]{ "" } ;
        champs = new JComboBox(tabChamps);
        champs.setBounds(120,65,150,25);
        champsLabel = new JLabel("Champs : ") ; 
        champsLabel.setBounds(20,65,100,25);
        modifPan.add(champsLabel) ; 
        modifPan.add(champs) ;
        
        clefModif = new JTextField() ; 
        clefModif.setBounds(120,100,150,25);
        clefModifLabel = new JLabel("Numero : ") ;
        clefModifLabel.setBounds(20,100,100,25);
        modifPan.add(clefModifLabel) ; 
        modifPan.add(clefModif) ;
        
        valeur = new JTextField() ; 
        valeur.setBounds(120,135,150,25);
        valeurLabel = new JLabel("Nouvelle valeur : ") ;
        valeurLabel.setBounds(20,135,100,25);
        modifPan.add(valeurLabel) ; 
        modifPan.add(valeur) ;
        
        Modifier = new JButton("Modifier");
        Modifier.setBounds(375,135,100,25);
        Modifier.addActionListener(new ActionModifier());
        modifPan.add(Modifier);
        
        //////////// Suppression //////////////
        
        suppPan = new JPanel() ;
        suppPan.setBorder(BorderFactory.createTitledBorder("Supprimer"));
        suppPan.setLayout(null);
        suppPan.setPreferredSize(new Dimension(340,190));
        
        table2 = new JComboBox(new String[]{"--Selectionner--","docteur","employe","infirmier","malade"});
        table2.setBounds(100,30,150,25);
        table2Label = new JLabel("Table : ") ;
        table2Label.setBounds(20,30,100,25);
        suppPan.add(table2Label) ; 
        suppPan.add(table2) ; 
        
        clef = new JTextField();
        clef.setBounds(100,65,150,25);
        clefSuppLabel = new JLabel("Numero : ");
        clefSuppLabel.setBounds(20,65,100,25);
        suppPan.add(clefSuppLabel);
        suppPan.add(clef);
        
        Supprimer = new JButton("Supprimer");
        Supprimer.setBounds(220,140,100,25);
        Supprimer.addActionListener(new ActionSupprimer());
        suppPan.add(Supprimer);
        
        
        //////////// Ajout ////////////////
        ajoutPan = new JPanel() ; 
        ajoutPan.setBorder(BorderFactory.createTitledBorder("Ajouter"));
        ajoutPan.setLayout(null);
        ajoutPan.setPreferredSize(new Dimension(140,190));
        
        Ajouter = new JButton("Ajouter");
        Ajouter.setBounds(20,140,100,25);
        Ajouter.addActionListener(new ActionAjouter());
        ajoutPan.add(Ajouter);
        
        
        this.getContentPane().add(modifPan,BorderLayout.NORTH) ;    
        this.getContentPane().add(suppPan,BorderLayout.WEST) ;    
        this.getContentPane().add(ajoutPan,BorderLayout.EAST) ;    
        this.setVisible(true);     
    }

    
    public void setJComboBox(String nomTable)
    {
        
        if(nomTable.equals("docteur")){
            tabChamps = new String[]{"specialite"} ;
        }
        if(nomTable.equals("employe")){
            tabChamps = new String[]{"nom","prenom","adresse","tel"} ;
        }
        if(nomTable.equals("infirmier")){
            tabChamps = new String[]{"code_service","rotation","salaire"} ;
        }
        if(nomTable.equals("malade")){
            tabChamps = new String[]{"nom","prenom","adresse","tel","mutuelle"};
        }
        DefaultComboBoxModel model = new DefaultComboBoxModel(tabChamps);
       
        champs.setModel(model);
    }
    
    
    public void modifResultats()
    {
        
        DefaultTableModel tm = new DefaultTableModel(donnees, tabChamps);
        tableResultats.setModel(tm) ;  
    }
     
    class ModifListe implements ActionListener 
    {
    
        @Override
        public void actionPerformed(ActionEvent e)
        {
            setJComboBox(table.getSelectedItem().toString());

        }
    }
    
    class ActionSupprimer implements ActionListener 
    {
             
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String table1 = table2.getSelectedItem().toString();
            String clef1 = clef.getText() ; 
          
                try 
                { 
                    if(!clef1.equals("") && !table1.equals("--Selectionner--"))
                    {
                        if(table1.equals("docteur")){
                        System.out.println("DELETE FROM docteur WHERE numero ="+"'"+clef1+"'");
                        connexion.executeUpdate("DELETE FROM docteur WHERE numero ="+"'"+clef1+"'");
                        System.out.println("DELETE FROM employe WHERE numero ="+"'"+clef1+"'");
                        connexion.executeUpdate("DELETE FROM employe WHERE numero ="+"'"+clef1+"'");
                        System.out.println("DELETE FROM soigne WHERE no_docteur ="+"'"+clef1+"'");
                        connexion.executeUpdate("DELETE FROM soigne WHERE no_docteur ="+"'"+clef1+"'");
                        }
                        if(table1.equals("infirmier")){
                            System.out.println("DELETE FROM infirmier WHERE numero ="+"'"+clef1+"'");
                            connexion.executeUpdate("DELETE FROM infirmier WHERE numero ="+"'"+clef1+"'");
                            System.out.println("DELETE FROM employe WHERE numero ="+"'"+clef1+"'");
                            connexion.executeUpdate("DELETE FROM employe WHERE numero ="+"'"+clef1+"'");
                            System.out.println("DELETE FROM chambre WHERE numero ="+"'"+clef1+"'");
                            connexion.executeUpdate("DELETE FROM chambre WHERE numero ="+"'"+clef1+"'");
                        }
                        if(table1.equals("malade")){
                            System.out.println("DELETE FROM malade WHERE numero ="+"'"+clef1+"'");
                            connexion.executeUpdate("DELETE FROM malade WHERE numero ="+"'"+clef1+"'");
                            System.out.println("DELETE FROM soigne WHERE no_malade ="+"'"+clef1+"'");
                            connexion.executeUpdate("DELETE FROM soigne WHERE no_malade ="+"'"+clef1+"'");
                        }
                    }
                     
                } 
                catch (SQLException ex) 
                {
                    Logger.getLogger(ModuleRechercher.class.getName()).log(Level.SEVERE, null, ex);
                }
            
        }
    }
    
    class ActionModifier implements ActionListener 
    {
             
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String table1 = table.getSelectedItem().toString();
            String champ1 = champs.getSelectedItem().toString();
            String clefSelect = clefModif.getText();
            String clef1 = "numero" ; 
            String new_val = valeur.getText();
          
                try 
                { 
                    if(!table1.equals("--Selectionner--") && !clef1.equals("") && !new_val.equals("")){
                        System.out.println("Requête SQL : UPDATE  "+table1+" SET "+champ1+" = '"+new_val+"' WHERE "+clef1+" = '"+clefSelect+"'");
                        connexion.executeUpdate("UPDATE  "+table1+" SET "+champ1+" = '"+new_val+"' WHERE "+clef1+" = '"+clefSelect+"'");
                    }
                    
                } 
                catch (SQLException ex) 
                {
                    Logger.getLogger(ModuleRechercher.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            
        }
    }
    
    class ActionAjouter implements ActionListener 
    {
             
        @Override
        public void actionPerformed(ActionEvent e)
        {
            AjoutElem ajoutelem = new AjoutElem(connexion) ; 
        }
    }
}