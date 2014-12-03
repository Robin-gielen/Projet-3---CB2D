
/**
 * Cette classe a pour but de contenir toutes les constantes globales du programme
 * 
 * @author : Julien Banken, Robin Gielen, Jeremy Gossiaux  
 * @version : 2/12/2014
 */
public class Constantes
{
    
    public static int choixModeGeneral; // Va servir lors de l'utilisation du scanner afin de savoir dans quel mode l'utilisateur veut utiliser notre programme (lecteur/generateur)
    
    
    public static String messageToPrint; // Va stocker le texte que l'utilisateur
    public static String pathToImageFile; // Chemin vers le fichier image
    
    public static int actualSize; // Stocke la taille de la matrice de bit/code barre
    public static int actualType; // Stocke le parametre du type de donnee du code barre
    public static int actualCompression; // Stocke le parametre du type de compression du code barre
    
    
    public static String [] indexParam1 = {"000","001","010","011","100","101","110","111"}; // Tableau reprenant les correspondances entre les successions de bits et les parametres pour la taille et le mode de compression 
    public static String [] indexParam2 = {"0000","0001","0010","0011","0100","0101","0110","0111","1000","1001","1010","1011","1100","1101","1110","1111"}; // Tableau reprenant les correspondances entre les successions de bits et les parametres pour le type de données
}
