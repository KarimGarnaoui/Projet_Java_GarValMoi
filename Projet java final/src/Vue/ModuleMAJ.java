package Vue;

import Modele.Connexion;
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


public class ModuleMAJ extends JFrame
{
    private final JLabel tableLabel,champsLabel,valeurLabel ; 
    private  JComboBox table,champs; 
    private final JTextField valeur;
    private final JButton Supprimer; 
    private final Connexion connexion ;
    private JPanel Ajouterlab,Content,Condition,Modifierlab;
    private String conditionSelectionnee ;
    private final JRadioButton conditionEgalite, conditionSuperieur, conditionInferieur, conditionDifferent ;
    
    private String[] titreColonnes ; 
    //private String[][] donnees ; 
    //private JScrollPane tableResultatsDeroulant ; 
    private String[] tab ;
    
    public ModuleMAJ(Connexion connexion1) throws SQLException
    {
        // Permet de créer la fenêtre principale
        super();
        connexion = connexion1;
        conditionSelectionnee = "=" ;
        this.setTitle("Mise à jour des données");
        this.setSize(800,600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        
        // Créer la partie de suppression
        Content = new JPanel() ;
        Content.setBorder(BorderFactory.createTitledBorder("SUPPRIMER"));
        Content.setLayout(null);
        Content.setPreferredSize(new Dimension(200,200));
        
        // Créer la partie d'ajout
        Ajouterlab = new JPanel() ;
        Ajouterlab.setLayout(null);
        Ajouterlab.setPreferredSize(new Dimension(790,180));
        Ajouterlab.setBorder(BorderFactory.createTitledBorder("AJOUTER"));
        
        // Créer la partie de modification
        Modifierlab = new JPanel() ;
        Modifierlab.setLayout(null);
        Modifierlab.setPreferredSize(new Dimension(590,180));
        Modifierlab.setBorder(BorderFactory.createTitledBorder("MODIFIER"));
        
        
        // Liste déroulante Table pour supprimer
        String tab1[] = {"--Selectionner--" , "chambre" , "docteur" , "employe" , "hospitalisation" , "infirmier" , "malade" , "service" , "soigne" } ;
        table = new JComboBox(tab1);
        table.setBounds(100,20,200,25);
        table.addActionListener(new ActionRechercher());
        tableLabel = new JLabel("Table : ") ;
        tableLabel.setBounds(10,20,50,25);
        Content.add(tableLabel) ; 
        Content.add(table) ;
       
        // Liste déroulante champ dépendant de listeTable pour supprimer
        // Creer la selection de la table
        String tab2[] = { "" } ;
        champs = new JComboBox(tab2);
        champs.setBounds(100,55,200,25);
        champsLabel = new JLabel("Champ : ") ; 
        champsLabel.setBounds(10,55,50,25);
        Content.add(champsLabel) ; 
        Content.add(champs) ;
        
        // Valeur recherchée et créer la selection des champs
        valeur = new JTextField();
        valeur.setBounds(100,90,200,25);
        valeurLabel = new JLabel("Valeur : ") ;
        valeurLabel.setBounds(10,90,50,25);
        Content.add(valeurLabel) ; 
        Content.add(valeur);
        
        // Permet de creer le bouton supprimer
        Supprimer = new JButton("Supprimer");
        Supprimer.setBounds(150,130,100,30);
        Supprimer.addActionListener(new ActionSupprimer());
        Content.add(Supprimer);
        
       
        //Permet d'afficher et de gerer les conditions pour la suppression
        Condition = new JPanel() ;
        Condition.setPreferredSize(new Dimension(200,200));
        Condition.setLayout(new GridLayout(4,1));
        Condition.setBorder(BorderFactory.createTitledBorder("Conditions"));
        conditionEgalite = new JRadioButton("Egales") ;
        conditionInferieur = new JRadioButton("Inférieures") ;
        conditionSuperieur = new JRadioButton("Supérieures") ;
        conditionDifferent = new JRadioButton("Différentes") ;
        ButtonGroup Bg = new ButtonGroup() ; 
        Bg.add(conditionEgalite) ;
        Bg.add(conditionInferieur) ;
        Bg.add(conditionSuperieur) ;
        Bg.add(conditionDifferent) ;
        Condition.add(conditionEgalite) ;
        Condition.add(conditionSuperieur) ;
        Condition.add(conditionInferieur) ;
        Condition.add(conditionDifferent) ;     
        conditionEgalite.addActionListener(new ModuleMAJ.ActionCondition()); 
        conditionInferieur.addActionListener(new ModuleMAJ.ActionCondition()); 
        conditionSuperieur.addActionListener(new ModuleMAJ.ActionCondition()); 
        conditionDifferent.addActionListener(new ModuleMAJ.ActionCondition()); 
        
        // Permet d'afficher toute la fenetre de MAJ       
        this.getContentPane().add(Content,BorderLayout.CENTER) ;
        this.getContentPane().add(Condition,BorderLayout.EAST);
        this.getContentPane().add(Ajouterlab,BorderLayout.NORTH) ;
        this.getContentPane().add(Modifierlab,BorderLayout.SOUTH);
        this.setVisible(true);     
    }

    
    public void setJComboBox(String nomTable)
    {
        
        if(nomTable.equals("chambre")){
            tab = new String[]{"code_service","no_chambre","surveillant","nb_lits"} ;
        }
        if(nomTable.equals("docteur")){
            tab = new String[]{"numero","specialite"} ;
        }
        if(nomTable.equals("employe")){
            tab = new String[]{"numero","nom","prenom","adresse","tel"} ;
        }
        if(nomTable.equals("hospitalisation")){
            tab = new String[]{"no_malade","code_service","no_chambre","lit"} ;
        }
        if(nomTable.equals("infirmier")){
            tab = new String[]{"numero","code_service","rotation","salaire"} ;
        }
        if(nomTable.equals("malade")){
            tab = new String[]{"numero","nom","prenom","adresse","tel","mutuelle"};
        }
        if(nomTable.equals("service")){
            tab = new String[]{"code","nom","batiment","directeur"} ;
        }
        if(nomTable.equals("soigne")){
            tab = new String[]{"no_docteur","no_malade"} ;
        }
        DefaultComboBoxModel model = new DefaultComboBoxModel(tab);
        champs.setModel(model);
    }
    
         
    class ActionRechercher implements ActionListener 
    {
    
        @Override
        public void actionPerformed(ActionEvent e)
        {
            setJComboBox(table.getSelectedItem().toString());
        }
    }
    
    
    // Permet de gerer les conditions 
    class ActionCondition implements ActionListener 
     {
    
        @Override
        public void actionPerformed(ActionEvent e)
        {
            
            if(e.getSource() == conditionEgalite) conditionSelectionnee = "=" ; 
            if(e.getSource() == conditionInferieur) conditionSelectionnee = "<" ;
            if(e.getSource() == conditionSuperieur) conditionSelectionnee = ">" ; 
            if(e.getSource() == conditionDifferent) conditionSelectionnee = "!=" ; 
        }
    }
     
    // Permet de gerer la suppresion
    class ActionSupprimer implements ActionListener 
    {
             
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String table1 = table.getSelectedItem().toString();
            String champ1 = champs.getSelectedItem().toString();
            String valeur1 = valeur.getText();
            if(table1!="--Selectionner--"){
                try 
                { 
                    System.out.println("Requête SQL : DELETE * FROM "+table1+" WHERE "+champ1+"'"+valeur1+"'");
                    connexion.executeUpdate("DELETE FROM "+table1+" WHERE "+champ1+conditionSelectionnee+"'"+valeur1+"'");
                } 
                catch (SQLException ex) 
                {
                    Logger.getLogger(ModuleRechercher.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    // Permet d'ajouter dans la BDD
    class ActionAjouter implements ActionListener 
    {
             
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String table1 = table.getSelectedItem().toString();
            String champ1 = champs.getSelectedItem().toString();
            String valeur1 = valeur.getText();
            //String valeur2 = valeur0.getText()
            if(table1!="--Selectionner--"){
                try 
                { 
                    System.out.println("Requête SQL : DELETE * FROM "+table1+" WHERE "+champ1+"'"+valeur1+"'");
                    connexion.executeUpdate("INSERT INTO"+table1+"VALUES("+"'"+valeur+"'"+"'"+valeur+"'");
                } 
                catch (SQLException ex) 
                {
                    Logger.getLogger(ModuleRechercher.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }    
}