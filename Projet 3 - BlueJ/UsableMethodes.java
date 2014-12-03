import java.util.Scanner;
import barcode2d.*;
import java.io.*;
import java.lang.StringBuffer.*; 
/**
 * Cette classe contient les methodes qui seront utilisee dans plusieur classes
 * 
 * @author : Julien Banken, Robin Gielen, Jeremy Gossiaux  
 * @version : 1/12/2014 
 */
public class UsableMethodes
{
    
    /**
     * Methode qui verifie si la ligne ou la colonne a un nombre de 1 pair ou impair
     * 
     * @pre : tableau != null, place != null
     * @post : Renvoie la parite de la ligne ou la colonne renseignee en parametre
     */
    public static boolean isPaire(int [][] tableau, int place, boolean isLine){
        int compteur = 0;
        boolean isPaire = false;

        if(isLine){// Verifie la parite pour une ligne
            for (int i = 1; i < tableau.length; i++){
                if(tableau[place][i] == 1){
                    compteur ++;
                }
            }
        }
        else{// Verifie la parite pour une colonne
            for (int i = 1; i < tableau.length; i++){
                if(tableau[i][place] == 1){
                    compteur ++;
                }
            }
        }  
        return ((compteur % 2) == 0);
    }
    
    /**
     * Methode qui renvoie les bits de configuration
     * 
     * @pre : barCode != null
     * @post : Renvoie la chaine de bits de gonfiguration du code barre
     */
    public static String configurationBits(BarCode2DData barCode){
        StringBuffer configuration = new StringBuffer(); 
        
        for (int i = 1; i < 11; i++){
            if (barCode.getValue(1,i)){
                configuration.append("1");
            }
            else{
                configuration.append("0");
            }
        }
        return (configuration.toString());
    }
    
    /**
     * Methode qui genere la matrice de bits 
     * 
     * @pre : barCode != null
     * @post : Genere et retourne la matrice de bits correspondante au code barre
     */
    public static int[][] usableTab (BarCode2DData barCode, int size){
        int [][] tab = new int [size][size];
        
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(barCode.getValue(i,j)){
                    tab[i][j] = 1;
                }
            }
        }
        return tab;
    }
    
    /**
     * Methode qui demande a l'utilisateur de renvoyer un int
     * 
     * @pre : /
     * @post : Renvoie le chiffre rentre par l'utilisateur
     */
    public static int value (){
        int nombre = 0;
        
        try {
            Scanner sc = new Scanner(System.in); 
            nombre = sc.nextInt();
        }
        catch (Exception e) {
            System.out.println (" Erreur : " + e.getMessage()); 
        }
        return nombre;
    }
    
    /**
     * Methode qui demande a l'utilisateur de renvoyer un String
     * 
     * @pre : /
     * @post : Renvoie la chaine de carracteres rentree par l'utilisateur
     */
    public static String message(){
        String message = "";
        
        try {
            Scanner sc = new Scanner(System.in); 
            message= sc.nextLine();
        }
        catch (Exception e) {
            System.out.println (" Erreur : " + e.getMessage()); 
        }
        return message;
    }
    
    /**
     * Methode qui renvoie le texte d'un fichier texte
     * 
     * @pre : text est un nom de fichier valide, text != null
     * @post : Renvoie le texte contenu dans le fichier text
     */
    public static String texteFichier(String text){
        String ligneCourante; 
        StringBuffer phrase = null; 
        BufferedReader test = null; 

        try {
            test = new BufferedReader(new FileReader (text));
            phrase = new StringBuffer(); 
            ligneCourante = test.readLine(); 
            while (ligneCourante != null) {// lit le fichier
                phrase.append(ligneCourante); 
                ligneCourante = test.readLine();
            }
        } 
        catch (IOException e){
            System.out.println(e.getMessage()); 
        }
        return(phrase.toString()); 
    }
}
