/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import vue.mafenetre;

/**
 *
 * @author Nicolas
 */

/** Module de connexion permettant de se connecter a la BDD*/
public class Connexion 
{
  public Connection connexion;

     public void ConnexionBD()
     {
          try
          {
              Class.forName("com.mysql.jdbc.Driver");
          }
          
          catch(Exception e)
          { 
             System.out.print("Erreur Base de donnée non chargée");
             System.exit(0);
          }
          
          try
          {
             String url = "jdbc:mysql://localhost/hopital?useSSL=false";
             String user = "root";
             String passwd = "";
             connexion = DriverManager.getConnection(url, user, passwd);
             
          }

          catch(Exception ex)
          {
             System.out.print("Erreur de connexion à la base de donnée");
          }
     }
     
     /** Permet d'acceder a l'objet connexion dans les autres classe*/
     public Connection getConnect()
     {
         return connexion;          
     }
     
     /** Permet de se deconnecter de la BDD*/
     public void DeconnexionBD()
     {
          try 
          {
            connexion.close();
          }
      
          catch(Exception ex) 
          {
            System.out.print("Déconnexion impossible");
          }
     }
 }
    

