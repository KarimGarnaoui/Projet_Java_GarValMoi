package Modele;


/**Librairie importe*/
import projet_bdd.Connexion;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import org.jfree.chart.*;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;


/** Classe Gérant le module reporting*/
public class ModuleReporting extends JFrame {
    
    private Connexion connexion ; 
    private JComboBox comboChoix ; 
    private JPanel panChoix , panResultat ; 
    private JLabel labelChoix ; 
    private JButton valider ; 
    private ChartPanel camPan ; 
    
    /** Constructeur de la classe du module reporting*/
    public ModuleReporting(Connexion connexion1){
        
        // Création de la fenêtre
        super();
        connexion = connexion1 ;
        this.setTitle("Reporting");
        this.setSize(624,600);
        this.setLocation(850,432);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        
        //Designe de la partie haute de la fenêtre
        panChoix = new JPanel() ; 
        panChoix.setBorder(BorderFactory.createTitledBorder("Choix du diagramme"));
        panChoix.setLayout(null);
        panChoix.setPreferredSize(new Dimension(750,150));
        
        // Design de la partie resultat de la fenêtre
        panResultat = new JPanel() ; 
        panResultat.setBorder(BorderFactory.createTitledBorder("Resultat"));
        panResultat.setLayout(null);
        panResultat.setPreferredSize(new Dimension(750,420));
        
        // Création de la barre de choix
        String[] tab = {"--Selectionner--","Nombre de malade par service","Nombre d'infirmiers par service","Nombre de malade par mutuelle"} ; 
        comboChoix = new JComboBox(tab);
        comboChoix.setBounds(100,40,300,25);
        comboChoix.addActionListener(new ActionComboBox());
        labelChoix = new JLabel("Choix : ") ; 
        labelChoix.setBounds(20,40,50,25);
        
        // Création du bouton valider
        valider = new JButton("Valider");
        valider.setBounds(480,110,100,25);
        
        panChoix.add(comboChoix) ; 
        panChoix.add(labelChoix) ;
        /////// Pas necessaire ///////
        //panChoix.add(valider) ;
        
        DefaultPieDataset cam = new DefaultPieDataset();
         
        // Création du camembert
        JFreeChart camDiag = ChartFactory.createPieChart("", cam);
         
        // Design du camembert
        camPan = new ChartPanel(camDiag);
        camPan.setBounds(40,40,500,350);
        panResultat.add(camPan);
        
        //on affiche tous les éléments swing créer
        this.getContentPane().add(panChoix,BorderLayout.NORTH);
        this.getContentPane().add(panResultat,BorderLayout.SOUTH);
        
        // On rend visible la fenêtre
        this.setVisible(true);
    }
    
    public void Camembert(String titre, String[] tab, String[] tabQ)
    {
        
        DefaultPieDataset cam1 = new DefaultPieDataset();
        for(int i = 0 ; i< tab.length ; i++)
        {
            cam1.setValue(tabQ[i], Integer.parseInt(tab[i].trim()));
        }
        
        JFreeChart cam1Diag = ChartFactory.createPieChart(titre, cam1);
        camPan.setChart(cam1Diag);  
        
    }
     
    /** Classe permettant la création du camembert*/
    class ActionComboBox implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String[] tab = new String[]{} ; 
            String[] tabMalSer = new String[]{"REA","CHG","CAR"} ; 
            String[] tabInfSer = new String[]{"REA","CHG","CAR"} ; 
            String[] tabMalMut = new String[]{"AG2R","CCVRP","CNAMTS","LMDE","MAAF","MAS","MGEN","MGSP","MMA","MNAM","MNFTC","MNH"} ; 
            try {
                if(comboChoix.getSelectedItem().toString().equals("Nombre de malade par service")){
                    tab = new String[tabMalSer.length] ; 
                    for(int i = 0 ; i<tab.length;i++){
                       tab[i] = connexion.remplirChampsRequeteArray("SELECT COUNT(no_malade) FROM hospitalisation WHERE code_service = ('"+tabMalSer[i]+"')").get(0) ;
                    }
                    
                    Camembert("Nombre de malade par service",tab,tabMalSer);
                }
                if(comboChoix.getSelectedItem().toString().equals("Nombre d'infirmiers par service")){
                    tab = new String[tabInfSer.length] ; 
                    for(int i = 0 ; i<tab.length;i++){
                       tab[i] = connexion.remplirChampsRequeteArray("SELECT COUNT(numero) FROM infirmier WHERE code_service = ('"+tabInfSer[i]+"')").get(0) ;
                    }
                    Camembert("Nombre d'infirmiers par service",tab,tabInfSer);
                }
                if(comboChoix.getSelectedItem().toString().equals("Nombre de malade par mutuelle")){
                    tab = new String[tabMalMut.length] ; 
                    for(int i = 0 ; i<tab.length;i++){
                       tab[i] = connexion.remplirChampsRequeteArray("SELECT COUNT(numero) FROM malade WHERE mutuelle = ('"+tabMalMut[i]+"')").get(0) ;
                        System.out.println(tab[i]);
                    }
                    Camembert("Nombre de malade par mutuelle",tab,tabMalMut);
                }
            }catch (SQLException ex) {
                    Logger.getLogger(ModuleReporting.class.getName()).log(Level.SEVERE, null, ex);
                }    
        }
    }
    
}