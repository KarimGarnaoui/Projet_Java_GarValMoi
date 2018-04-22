package Modele;

/** Librairie importé*/
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.* ;
import projet_bdd.Connexion;
import com.sun.corba.se.spi.orbutil.fsm.Guard;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.xml.transform.Result;
import sun.awt.image.PixelConverter;

/** Classe permettant de gerer le module de recherche*/
public class ModuleRechercher extends JFrame {
   
    private final JLabel tableLabel, champsLabel, valeurLabel,requeteManuelleLabel ; 
    private  JComboBox table, champs ; 
    private final JTextField valeur, requeteManuelle ; 
    private final JRadioButton conditionEgalite, conditionSuperieur, conditionInferieur, conditionDifferent ;
    private  JTable tableResultats ; 
    private final JButton valider, appliquer; 
    private final Connexion connexion ;
    private JPanel Result, Content, Condition ;
    private String conditionSelectionnee ; 
    private String[] titreColonnes ; 
    private String[][] donnees ; 
    private JScrollPane tableResultatsDeroulant ; 
    private String[] tab ; 
    
    /**
     * Constructeur de la classe ModuleRechercher
     * @param connexion1
     * @throws java.sql.SQLException
     */
    public ModuleRechercher(Connexion connexion1) throws SQLException{
        
        // Création de la fenêtre
        super();
        connexion = connexion1 ;
        conditionSelectionnee = "=" ; 
        this.setTitle("Rechercher");
        this.setSize(800,600);
        this.setLocation(50,432);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        
        // Création d'une partie de la fenêtre
        Content = new JPanel() ;
        Content.setBorder(BorderFactory.createTitledBorder("Critères de recherche"));
        Content.setLayout(null);
        Content.setPreferredSize(new Dimension(590,200));
        
        // Liste déroulante Table
        String tab1[] = {"--Selectionner--" , "chambre" , "docteur" , "employe" , "hospitalisation" , "infirmier" , "malade" , "service" , "soigne" } ;
        table = new JComboBox(tab1);
        table.setBounds(350,20,200,25);
        table.addActionListener(new ActionRechercher());
        tableLabel = new JLabel("Table : ") ;
        tableLabel.setBounds(280,20,50,25);
        Content.add(tableLabel) ; 
        Content.add(table) ;
        
        // Liste déroulante champ dépendant de listeTable
        String tab2[] = { "" } ;
        champs = new JComboBox(tab2);
        champs.setBounds(350,55,200,25);
        champsLabel = new JLabel("Champ : ") ; 
        champsLabel.setBounds(280,55,50,25);
        Content.add(champsLabel) ; 
        Content.add(champs) ;
        
        // Valeur recherchée
        valeur = new JTextField() ; 
        valeur.setBounds(350,90,200,25);
        valeurLabel = new JLabel("Valeur : ") ;
        valeurLabel.setBounds(280,90,50,25);
        Content.add(valeurLabel) ; 
        Content.add(valeur) ;
        
        // Création du bouton valider
        valider = new JButton("Valier");
        valider.setBounds(500,135,75,25);
        valider.addActionListener(new ActionValider());
        Content.add(valider) ; 
        
        // Nom de la BDD
        JLabel nomBDD = new JLabel("Base de donnée");        
        JLabel BDD = new JLabel("Hopital");
        nomBDD.setBounds(50,50,100,50);
        BDD.setBounds(75,70,100,50);
        Content.add(nomBDD) ; 
        Content.add(BDD) ; 
        
        // Condition sur valeur
        Condition = new JPanel() ;
        Condition.setPreferredSize(new Dimension(190,200));
        Condition.setLayout(new GridLayout(4,1));
        Condition.setBorder(BorderFactory.createTitledBorder("Conditions"));
        
        // Création des boutons de condition
        conditionEgalite = new JRadioButton("Egales") ;
        conditionInferieur = new JRadioButton("Inférieures") ;
        conditionSuperieur = new JRadioButton("Supérieures") ;
        conditionDifferent = new JRadioButton("Différentes") ;
        conditionEgalite.addActionListener(new ActionCondition()); 
        conditionInferieur.addActionListener(new ActionCondition()); 
        conditionSuperieur.addActionListener(new ActionCondition()); 
        conditionDifferent.addActionListener(new ActionCondition()); 
        
        ButtonGroup Bg = new ButtonGroup() ; 
        Bg.add(conditionEgalite) ;
        Bg.add(conditionInferieur) ;
        Bg.add(conditionSuperieur) ;
        Bg.add(conditionDifferent) ;
        Condition.add(conditionEgalite) ;
        Condition.add(conditionSuperieur) ;
        Condition.add(conditionInferieur) ;
        Condition.add(conditionDifferent) ;
        
        // Tableau de résultats
        Result = new JPanel() ;
        Result.setLayout(null);
        Result.setBorder(BorderFactory.createTitledBorder("Résultats"));
        Result.setPreferredSize(new Dimension(780,390));
        
        requeteManuelle = new JTextField() ; 
        requeteManuelle.setBounds(180,360,500,25);
        requeteManuelleLabel= new JLabel("Requête manuelle (avancé) : ");
        requeteManuelleLabel.setBounds(10,360,170,25);
        
        appliquer = new JButton("Appliquer");
        appliquer.setBounds(685,360,95,25);
        appliquer.addActionListener(new ActionAppliquer());
        
        String[][] donnees = {{""}} ;
        titreColonnes = new String[]{""};
        
        // Création du tableau de résultat
        tableResultats = new JTable(donnees,titreColonnes);
        tableResultatsDeroulant = new JScrollPane(tableResultats) ;
        tableResultatsDeroulant.setBounds(20,20,750,330);
        Result.add(tableResultatsDeroulant) ; 
        Result.add(requeteManuelleLabel) ; 
        Result.add(requeteManuelle) ; 
        Result.add(appliquer) ; 
        Result.setPreferredSize(new Dimension(800,400));
        
        // On rajoute tous les éléments sur la fenêtre
        this.getContentPane().add(Content,BorderLayout.WEST) ;
        this.getContentPane().add(Condition,BorderLayout.EAST) ;
        this.getContentPane().add(Result,BorderLayout.SOUTH) ;
        
        // On rend la fenêtre visible
        this.setVisible(true);
        
    }
    
    /**
     * Permet d'actualiser la JComboBox Champs en fonction de la table choisie par l'utilisateur
     * @param nomTable nom de la table sélectionnée
     */
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
    
    public void modifResultats(){
        
        DefaultTableModel tm = new DefaultTableModel(donnees, tab);
        tableResultats.setModel(tm) ;  
    }
    
    public void modifResultatsManuel(){
        tab = new String[donnees[0].length] ; 
        DefaultTableModel tm = new DefaultTableModel(donnees, tab);
        tableResultats.setModel(tm) ;  
    }
     
    class ActionRechercher implements ActionListener {
    
        @Override
        public void actionPerformed(ActionEvent e)
        {
            setJComboBox(table.getSelectedItem().toString());

        }
    }
    class ActionAppliquer implements ActionListener 
    {    
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String requete = requeteManuelle.getText();
            if(requete!="")
            {
                try { 
                    System.out.println("Requête SQL : "+requete);
                    donnees = connexion.remplirChampsRequete(requete) ;

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,ex);
                }
                modifResultatsManuel();
            }
        }
    }
    
    
    /** Permet de récuperer la condition selectionnée*/
    class ActionCondition implements ActionListener {
    
        @Override
        public void actionPerformed(ActionEvent e)
        {
            
            if(e.getSource() == conditionEgalite) conditionSelectionnee = "=" ; 
            if(e.getSource() == conditionInferieur) conditionSelectionnee = "<" ;
            if(e.getSource() == conditionSuperieur) conditionSelectionnee = ">" ; 
            if(e.getSource() == conditionDifferent) conditionSelectionnee = "!=" ; 
        }
    }
    
    /** Lorsqu'on auppuie sur le bouton valider on accede à la recherche demandée*/
    class ActionValider implements ActionListener 
    {    
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String table1 = table.getSelectedItem().toString();
            String champ1 = champs.getSelectedItem().toString();
            String valeur1 = valeur.getText();
            if(table1!="--Selectionner--")
            {
                try { 
                    System.out.println("Requête SQL : Select * FROM "+table1+" WHERE "+champ1 +conditionSelectionnee+"'"+valeur1+"'");
                    donnees = connexion.remplirChampsRequete("Select * FROM "+table1+" WHERE "+champ1 +conditionSelectionnee+"'"+valeur1+"'" ) ;

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,ex);
                }
                modifResultats();
            }
        }
    }   
}

