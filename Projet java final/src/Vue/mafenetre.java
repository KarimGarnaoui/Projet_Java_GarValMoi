
package vue;

/** Librairie importer*/
import projet_bdd.Connexion;
import java.awt.event.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.TitledBorder;

/**Interface du module de Connexion */
public class mafenetre extends JFrame 
{   
    /**Declaration de mes boutons et texte*/
    JLabel login,mdp;
    JTextField login1;
    JPasswordField mdp1;
    JButton Local, distance,quitter;
    
    /** Constructeur de ma fenetre*/
    public mafenetre()
    {
       /** Appel du constructeur de JFrame et mise en page de la fenetre principale*/
       super();
       this.setTitle("Connexion : Gestion informatique d'un centre hospitalier");
       this.setSize(450,200);
       this.setLocationRelativeTo(null);
       this.setResizable(false);
       this.setDefaultCloseOperation(EXIT_ON_CLOSE);
       
       /** Instancier tous les boutons et les textes*/
       login = new JLabel("Login");
       login1 = new JTextField();
       mdp = new JLabel("Mot de Passe");
       mdp1 = new JPasswordField();
       Local = new JButton("Local ");
       distance = new JButton("Distance");
       quitter = new JButton(" Quitter");
       
       /**Ajout des boutons sur la fenetre et declaration de leur dimension*/
       Container cont = this.getContentPane();
       cont.setLayout(null);
       cont.add(login);
       login.setBounds(20, 20, 100, 20);
       cont.add(login1);
       login1.setBounds(150, 20, 150, 20);
       cont.add(mdp);
       mdp.setBounds(22, 55, 100, 20);
       cont.add(mdp1);
       mdp1.setBounds(150, 55, 150, 20);
       cont.add(Local);
       Local.setBounds(50,120 ,85 ,20 );
       cont.add(distance);
       distance.setBounds(155,120,85,20);
       cont.add(quitter);
       quitter.setBounds(260, 120, 85, 20);
       
       Local.setMnemonic(KeyEvent.VK_ENTER); 
       getRootPane().setDefaultButton(Local);
       
       
       /** Si on appuie sur le bouton quitter la fenetre se ferme*/ 
       quitter.addActionListener(new ActionListener() 
       {
         @Override
         public void actionPerformed(ActionEvent e) 
         {
           System.exit(0);
         }
       });
       
        /** Si on appuie sur le bouton local on test si on a le bon login et mot de passe*/
       Local.addActionListener(new ActionListener() 
       {
         @Override
         public void actionPerformed(ActionEvent e) 
         {
             try {
                 
                 mafenetre(login1 ,mdp1);
             } catch (SQLException ex) {
                 Logger.getLogger(mafenetre.class.getName()).log(Level.SEVERE, null, ex);
             } catch (ClassNotFoundException ex) {
                 Logger.getLogger(mafenetre.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
         
         /**Fonction qui permet de verifier si le login et le mot de passe sont correct puis se connecte a la BDD*/
         private void mafenetre(JTextField login1, JPasswordField mdp1) throws SQLException, ClassNotFoundException 
         {
          /** On recupere le login et le mot de passe saisie*/
          String login =login1.getText();
          String password = mdp1.getText();
         
              /** On teste si le login et le mot de passe rentrer pour se connecter en local est correcte*/
              if((login.equals("root")) && (password.equals("")))
              {
                  /** On se connecte à la BDD*/
                  
                  Connexion ma_co = new Connexion("hopital",login,password);
                  
                  
                  dispose(); // Ferme l'ancienne Jframe pour laisser place à la nouvelle
                  
                  /** On ouvre la fenêtre du menu*/
                  Vue.FenetreModules fenetremodules = new Vue.FenetreModules(ma_co);
              }  
              else 
              {
                JOptionPane.showMessageDialog(null,"Login ou mot de passe incorrect! ","Error",1);
              }   
         }
        });
        
      /** On rend visible le tout*/
     this.setVisible(true);    
    }
}
 
        

