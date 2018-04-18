/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_bdd;
import Vue.ModuleRechercher;
import vue.mafenetre;

/**
 *
 * @author Nicolas
 */
public class Projet_BDD 
{
   /** Constructeur par default*/
   public Projet_BDD() 
   {
   
   }

   /** Main qui lance le programm
     * @param args*/
   public static void main(String[] args) 
   {
       int j = 0;
       String chaine = "44,jean,valjean" ; 
       String[] tab = new String[10] ;
       for(int i = 0;i<tab.length;i++){
            tab[i] = ""; 
       }
       for(int i = 0;i<chaine.length();i++){
            //System.out.println("Caractère n°"+i+" : "+chaine.charAt(i));
            if(chaine.charAt(i) == ','){
                j++ ;
                i++;
            } 
            tab[j] += chaine.charAt(i) ;
       }
       for(int i = 0;i<tab.length;i++){
            System.out.println(tab[i]); 
       }
      
       mafenetre mf = new mafenetre();
   }
 }
    

