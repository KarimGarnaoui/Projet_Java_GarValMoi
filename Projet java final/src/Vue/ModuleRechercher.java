/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.* ;

/**
 *
 * @author karim
 */
public class ModuleRechercher extends JFrame {
   
    private final JLabel tableLabel, champsLabel, valeurLabel ; 
    private final JComboBox table, champs ; 
    private final JTextField valeur ; 
    private final JRadioButton conditionEgalite, conditionSuperieur, conditionInferieur, conditionDifferent ;
    private final JTable tableResultats ; 
    
    /**
     * Constructeur de la classe ModuleRechercher
     */
    public ModuleRechercher(){
        super();
        this.setTitle("Rechercher");
        this.setSize(800,600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        
        JPanel Content = new JPanel() ;
        Content.setBorder(BorderFactory.createTitledBorder("Critères de recherche"));
        Content.setLayout(null);
        Content.setPreferredSize(new Dimension(590,200));
        
        // Liste déroulante Table
        String tab[] = {"--Selectionner--" , "Chambre" , "Docteur" , "Employe" , "Hospitalisation" , "Infirmier" , "Malade" , "Service" , "Soigne" } ;
        table = new JComboBox(tab);
        table.setBounds(350,50,200,25);
        table.addActionListener(new ActionRechercher());
        tableLabel = new JLabel("Table : ") ;
        tableLabel.setBounds(280,50,50,25);
        Content.add(tableLabel) ; 
        Content.add(table) ;
        
        // Liste déroulante champ dépendant de listeTable
        String tab2[] = { "" } ;
        champs = new JComboBox(tab2);
        champs.setBounds(350,85,200,25);
        champsLabel = new JLabel("Champ : ") ; 
        champsLabel.setBounds(280,85,50,25);
        Content.add(champsLabel) ; 
        Content.add(champs) ;
        
        // Valeur recherchée
        valeur = new JTextField() ; 
        valeur.setBounds(350,120,200,25);
        valeurLabel = new JLabel("Valeur : ") ;
        valeurLabel.setBounds(280,120,50,25);
        Content.add(valeurLabel) ; 
        Content.add(valeur) ;
        
        // Nom de la BDD
        JLabel nomBDD = new JLabel("Base de donnée");        
        JLabel BDD = new JLabel("Hopital");
        nomBDD.setBounds(50,50,100,50);
        BDD.setBounds(75,70,100,50);
        Content.add(nomBDD) ; 
        Content.add(BDD) ; 
        
        // Condition sur valeur
        JPanel Condition = new JPanel() ;
        Condition.setPreferredSize(new Dimension(190,200));
        Condition.setLayout(new GridLayout(4,1));
        Condition.setBorder(BorderFactory.createTitledBorder("Conditions"));
        
        conditionEgalite = new JRadioButton("Egales") ;
        conditionSuperieur = new JRadioButton("Supérieures") ;
        conditionInferieur = new JRadioButton("Inférieures") ;
        conditionDifferent = new JRadioButton("Différentes") ;
        
        Condition.add(conditionEgalite) ;
        Condition.add(conditionSuperieur) ;
        Condition.add(conditionInferieur) ;
        Condition.add(conditionDifferent) ;
        
        // Tableau de résultats
        JPanel Result = new JPanel() ;
        Result.setLayout(null);
        Result.setBorder(BorderFactory.createTitledBorder("Résultats"));
        Result.setPreferredSize(new Dimension(780,390));
        
        String[][] donnees = {  
            {" ", " ", " "," ", " "}, 
            {" ", " ", " "," ", " "}, 
            {" ", " ", " "," ", " "},
            {" ", " ", " "," ", " "},
            {" ", " ", " "," ", " "},
            {" ", " ", " "," ", " "},
            {" ", " ", " "," ", " "},
            {" ", " ", " "," ", " "},
            {" ", " ", " "," ", " "},
            {" ", " ", " "," ", " "},
            {" ", " ", " "," ", " "},
            {" ", " ", " "," ", " "},
            {" ", " ", " "," ", " "},
            {" ", " ", " "," ", " "},
            {" ", " ", " "," ", " "},
            {" ", " ", " "," ", " "},
            {" ", " ", " "," ", " "},
            {" ", " ", " "," ", " "},
            {" ", " ", " "," ", " "},
            {" ", " ", " "," ", " "},
            {" ", " ", " "," ", " "},
            {" ", " ", " "," ", " "} 
        } ;
        
        String[] titreColonnes = {"Champ 1","Champ 2", "Champ 3", "Champ 4", "Champ 4"};
        
        tableResultats = new JTable(donnees,titreColonnes);
        JScrollPane tableResultatsDeroulant = new JScrollPane(tableResultats) ;
        tableResultatsDeroulant.setBounds(20,20,750,350);
        Result.add(tableResultatsDeroulant) ; 
        
        
        Result.setPreferredSize(new Dimension(800,400));
        
        this.getContentPane().add(Content,BorderLayout.WEST) ;
        this.getContentPane().add(Condition,BorderLayout.EAST) ;
        this.getContentPane().add(Result,BorderLayout.SOUTH) ;
        
        this.setVisible(true);
        
    }
    
    /**
     * Permet d'actualiser la JComboBox Champs en fonction de la table choisie par l'utilisateur
     * @param nomTable nom de la table sélectionnée
     */
    public void setJComboBox(String nomTable)
    {
        String[] tab = {""} ;
        if(nomTable.equals("Chambre")){
            tab = new String[]{"--Selectionner--" , "Code service","Numero chambre","Surveillant","Nombre de lits"} ;
        }
        if(nomTable.equals("Docteur")){
            tab = new String[]{"--Selectionner--" , "Numero","Specialite"} ;
        }
        if(nomTable.equals("Employe")){
            tab = new String[]{"--Selectionner--" , "Numero","Nom","Prenom","Adresse","Telephone"} ;
        }
        if(nomTable.equals("Hospitalisation")){
            tab = new String[]{"--Selectionner--" , "Numero malade","Code service","Numero chambre","Lit"} ;
        }
        if(nomTable.equals("Infirmier")){
            tab = new String[]{"--Selectionner--" , "Numero","Code service","Rotation","Salaire"} ;
        }
        if(nomTable.equals("Malade")){
            tab = new String[]{"--Selectionner--" , "Numero","Nom","Prenom","Adresse","Telephone","Mutuelle"};
        }
        if(nomTable.equals("Service")){
            tab = new String[]{"--Selectionner--" , "Code","Nom","Batiment","Directeur"} ;
        }
        if(nomTable.equals("Soigne")){
            tab = new String[]{"--Selectionner--" , "Numero docteur","Numero malade"} ;
        }
        DefaultComboBoxModel model = new DefaultComboBoxModel(tab);
        champs.setModel(model);
    }
     
    class ActionRechercher implements ActionListener {
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        setJComboBox(table.getSelectedItem().toString());
        
    }
}
    
}

