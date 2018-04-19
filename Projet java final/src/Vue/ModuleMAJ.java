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
    private final JLabel tableLabel, champsLabel, valeurLabel ; 
    private  JComboBox table, champs ; 
    private final JTextField valeur ;
    
    private  JTable tableResultats ; 
    
    private final JButton Supprimer,Ajouter,Modifier,retour ; 
    private final Connexion connexion ;
    private JPanel Result, Content;
    private String ChoixMAJ; 
    private String[] titreColonnes ; 
    private String[][] donnees ; 
    private JScrollPane tableResultatsDeroulant ; 
    private String[] tab ;
    
    public ModuleMAJ(Connexion connexion1) throws SQLException
    {
        super();
        connexion = connexion1;
        ChoixMAJ = "=";
        this.setTitle("Mise à jour des données");
        this.setSize(600,400);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        
        Content = new JPanel() ;
        Content.setLayout(null);
        Content.setPreferredSize(new Dimension(600,200));
        
        // Liste déroulante Table
        String tab1[] = {"--Selectionner--" , "chambre" , "docteur" , "employe" , "hospitalisation" , "infirmier" , "malade" , "service" , "soigne" } ;
        table = new JComboBox(tab1);
        table.setBounds(100,20,200,25);
        table.addActionListener(new ActionRechercher());
        tableLabel = new JLabel("Table : ") ;
        tableLabel.setBounds(10,20,50,25);
        Content.add(tableLabel) ; 
        Content.add(table) ;
        
        // Liste déroulante champ dépendant de listeTable
        String tab2[] = { "" } ;
        champs = new JComboBox(tab2);
        champs.setBounds(100,55,200,25);
        champsLabel = new JLabel("Champ : ") ; 
        champsLabel.setBounds(10,55,50,25);
        Content.add(champsLabel) ; 
        Content.add(champs) ;
        
        // Valeur recherchée
        valeur = new JTextField() ; 
        valeur.setBounds(100,90,200,25);
        valeurLabel = new JLabel("Valeur : ") ;
        valeurLabel.setBounds(10,90,50,25);
        Content.add(valeurLabel) ; 
        Content.add(valeur) ;
        
        Supprimer = new JButton("Supprimer");
        Supprimer.setBounds(350,100,75,25);
        Supprimer.addActionListener(new ActionSupprimer());
        Content.add(Supprimer);
        
        Ajouter = new JButton("Ajouter");
        Ajouter.setBounds(350,150,75,25);
        Content.add(Ajouter);
        
        Modifier = new JButton("Modifier");
        Modifier.setBounds(350,200,75,25);
        Content.add(Modifier);
        
        retour = new JButton("Retour");
        retour.setBounds(350,250,75,25);
        // retour.addActionListener(new ActionRetour());
        Content.add(retour) ; 
        
        // Nom de la BDD
        JLabel nomBDD = new JLabel("Base de donnée");        
        JLabel BDD = new JLabel("Hopital");
        
       // Tableau de résultats
        Result = new JPanel() ;
        String[][] donnees = {{""}} ;
        
        titreColonnes = new String[]{""};
        
        tableResultats = new JTable(donnees,titreColonnes);
        tableResultatsDeroulant = new JScrollPane(tableResultats) ;
        tableResultatsDeroulant.setBounds(20,20,750,350);
        Result.add(tableResultatsDeroulant) ; 
        
        
        Result.setPreferredSize(new Dimension(800,400));
        
        this.getContentPane().add(Content,BorderLayout.WEST) ;    
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
    
    
    public void modifResultats()
    {
        
        DefaultTableModel tm = new DefaultTableModel(donnees, tab);
        tableResultats.setModel(tm) ;  
    }
     
    class ActionRechercher implements ActionListener 
    {
    
        @Override
        public void actionPerformed(ActionEvent e)
        {
            setJComboBox(table.getSelectedItem().toString());

        }
    }
    
    class ActionRetour implements ActionListener 
    {
    
        @Override
        public void actionPerformed(ActionEvent e)
        {
            dispose();
        }
    }
     
    class ActionSupprimer implements ActionListener 
    {
             
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String table1 = table.getSelectedItem().toString();
            String champ1 = champs.getSelectedItem().toString();
            String valeur1 = valeur.getText();
          
                try 
                { 
                    System.out.println("Requête SQL : DELETE * FROM "+table1+" WHERE "+champ1+"'"+valeur1+"'");
                    connexion.executeUpdate("DELETE FROM "+table1+" WHERE "+champ1+"="+"'"+valeur1+"'");
                } 
                catch (SQLException ex) 
                {
                    Logger.getLogger(ModuleRechercher.class.getName()).log(Level.SEVERE, null, ex);
                }
                modifResultats();
            
        }
    }
}
