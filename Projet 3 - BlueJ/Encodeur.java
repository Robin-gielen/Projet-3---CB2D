import java.lang.IndexOutOfBoundsException;
/**
 * Cette classe reunit toute les methode pour necessaire pour encoder un code barre
 * 
 * @author : Julien Banken, Robin Gielen, Jeremy Gossiaux  
 * @version : 1/12/2014
 */
public class Encodeur{
    /**
     * @pre : msg != null
     * @post : Renvoie la traduction de msg en binaire 
     * @throw : EncodingException au cas ou le message msg ne peut pas etre encode
     */
    public static String encode (String msg)throws EncodingException{
        StringBuffer chaineBinaire = new StringBuffer(); 

        for (int i = 0; i < msg.length(); i++){
            char caractere = msg.charAt(i);
            String bitChaine = Integer.toBinaryString(caractere);
            
            if (bitChaine.length()>7){// Verifie que le caractere est en ASCII 7bit
                throw new EncodingException();
            }
            if (caractere == ' '){// Si c'est un espace, rajoute sa valeur en binaire
                chaineBinaire.append("00100000");
            }
            else{// Traduit le caractere en binaire
                chaineBinaire.append("0").append(bitChaine);  
            }
        }
        return chaineBinaire.toString();
    }

    /**
     * calcule la taille du tableau necessaire pour stocker les info (message plus configuration)
     * 
     * @pre : finalChain != null
     * @post : Renvoie la taille necessaire de la matrice de bit pour pouvoir y stocker les informations
     */
    public static void tabSize(String finalChain)throws IndexOutOfBoundsException{
        int longueur = (finalChain.length()) + 16;
        int size = 0;
        if (longueur <= 960){ // 32*32-32-31 = 961 bit disponible, moins un car il faut que ce soit un multiple de 8
            size = 0;
        }
        else if(longueur <= 3968){
            size = 1;
        }
        else if(longueur <= 16128){
            size = 2;
        }
        else if(longueur <= 65024){
            size = 3;
        }
        else{
            throw new IndexOutOfBoundsException();
        }
        Constantes.actualSize = ((size + 1) *32);
    }
    
    /**
     *  Ajoute les bits de configuration
     * 
     * @pre : chaine != null, donnee != null
     * @post : Renvoie la chaine de bits de configuration
     */
    public static String configAddition(String chaine,Config donnee){
        String completeBit = "111111"; //bits servant a completer la chaine pour atteindre 16 bits

        return (Constantes.indexParam1[donnee.getSize()] + Constantes.indexParam2[donnee.getDataType()] + Constantes.indexParam1[donnee.getCompressionMode()]) + (completeBit + (chaine));
    }
    
    /**
     * Methode qui remplit la matrice de bit avec les informations qui devront se trouver dans le code barre
     * 
     * @pre : chaineBinaire != null
     * @post : Renvoie la matrice de bits completee
     */
    public static int[][] fillTab (String chaineBinaire){
        int size = Constantes.actualSize;
        int [][] tab = new int [size][size];
        int index = 0;
        
        for (int i = 1; i < tab.length; i++){
            for (int j = 1; j < tab.length; j++){
                tab[i][j] = Character.getNumericValue(chaineBinaire.charAt(index)); 
                if (index >= chaineBinaire.length() - 1){
                    j = tab.length; 
                    i = tab.length;
                }
                index ++; 
            }
        } 
        correcteur(tab); // Appel a la methode de placement de bits de parite
        return tab;
    }
    
    /**
     * Cree les bits de correction
     * 
     * @pre : data != null
     * @post : Complete la matrice de bits avec les bits de parite
     */
    public static void correcteur (int[][] data){
        //paire = 1
        //ligne
        for (int i = 1; i < data.length; i++){
            if (UsableMethodes.isPaire(data,i,true)){
                data[i][0] = 1;
            }
        }
        //colone
        for (int i = 1; i < data.length; i++){
            if (UsableMethodes.isPaire(data,i,false)){
                data[0][i] = 1;
            }
        }

        if (UsableMethodes.isPaire(data,0,true)){
            data[0][0] = 1;
        }
    }
    
    /**
     * @pre : size ! = null, type <= 16, compression <= 8
     * @post renvoie la configuration du code-barre
     */ 
    public Config getConfiguration(int size,int type, int compression){
        Config config = new Config (size,type,compression);
        return config;
    }
}
