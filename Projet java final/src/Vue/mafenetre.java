/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

/** Importer tout ce qui est necessaire pour le projet*/
import projet_bdd.Connexion;
import projet_bdd.Projet_BDD;
import java.awt.event.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
import javax.swing.border.TitledBorder;

/** Cette classe est l'interface d'affichage de la Connexion */
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
       /** Appel du constructeur de JFame et mise en page de la fenetre principale*/
       super();
       this.setTitle(" Connexion : Entrée dans la base de donnée de l'hopital ");
       this.setSize(400,200);
       this.setLocationRelativeTo(null);
       this.setResizable(false);
       
       /** Instancier tous les boutons et les textes*/
       login = new JLabel("Login");
       login1 = new JTextField();
       mdp = new JLabel("Mot de Passe");
       mdp1 = new JPasswordField();
       Local = new JButton("Local ");
       distance = new JButton("Distance");
       quitter = new JButton(" Quitter");
       
       /**Ajout des boutons sur la fenetre et declaration de leur dimension*/
       /** IL faudra Peut etre redisigner*/
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
            mafenetre(login1 ,mdp1);
         }
         
         /**Fonction qui permet de verifier si le login et le mot de passe sont correct puis se connecte a la BDD*/
         private void mafenetre(JTextField login1, JPasswordField mdp1) 
         {
          
          /** On instancie une nouvelle connexion a la BDD en appel la classe connexion*/
          Connexion ma_co = new Connexion();
          Connection connexion;
          
          /** Variable a créer si l'on veut effectuer des requetes SQL*/
          Statement statement = null;
          ResultSet resultat;
          
          /** On recupere le login et le mot de passe saisie*/
          String login =login1.getText();
          String password = mdp1.getText();
          
          /** On appel de la fonction de connexion pour se connecter a la BDD*/
          ma_co.ConnexionBD();
          connexion = ma_co.getConnect();
          
          /** On teste si le login et le mot de passe rentrer pour se connecter en local est correcte*/
          try
          {
              if((login.equals("root")) && (password.equals("")))
              {
                JOptionPane.showMessageDialog(null,"Connexion réussie ! ","Success",JOptionPane.PLAIN_MESSAGE);
                // Il faudra refaire une class fenetre2 avec notre interface proposant les 3 modules possibles*/
              }  
              else 
              {
                JOptionPane.showMessageDialog(null,"Login ou mot de passe incorrect! ","Error",1);
              }
         
            connexion.close();
          }
          catch (SQLException e4) 
          {    
             System.out.println(e4.getMessage());
          }
         }
        });
        
      /** On rend visible le tout*/
     this.setVisible(true);
    }
}
 
        
