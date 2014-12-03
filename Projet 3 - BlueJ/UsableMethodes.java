import java.util.Scanner;
import barcode2d.*;
/**
 * 
 * 
 * @author : Julien Banken, Robin Gielen, Jeremy Gossiaux  
 * @version : 1/12/2014 
 */
public class UsableMethodes
{
    
    /**
     * Methode qui verifie si la ligne ou la colonne a un nombre de 1 pair ou impair
     * 
     * @pre - 
     * @post -
     */
    public static boolean isPaire(int [][] tableau, int place, boolean isLine){
        int compteur = 0;
        boolean isPaire = false;

        if(isLine){
            for (int i = 1; i < tableau.length; i++){
                if(tableau[place][i] == 1){
                    compteur ++;
                }
            }
        }
        else{
            for (int i = 1; i < tableau.length; i++){
                if(tableau[i][place] == 1){
                    compteur ++;
                }
            }
        }  

        return ((compteur % 2) == 0);
    }
    
    /**
     * methode qui renvoie les bits de configuration
     * 
     * 
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
}
