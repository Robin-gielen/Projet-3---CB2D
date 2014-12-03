import java.lang.IndexOutOfBoundsException;
/**
 * Cette classe a pour fonction de generer des codes barres a partir de texte
 * 
 * @author  Gielen Robin, Julien Banken, Jeremy Gossiaux
 * @version 2/12/2014
 */
public class Encodeur{
        /**
         * @pre   msg != null
         * @post  
         * @throw EncodingException au cas ou le message msg ne peut pas etre encode
         */
    public static String encode (String msg)throws EncodingException{
        StringBuffer chaineBinaire = new StringBuffer(); 

        for (int i = 0; i < msg.length(); i++){
            String temp = Integer.toBinaryString(msg.charAt(i));
            if (temp.length()>7){
                throw new EncodingException();
            }
            else{
                chaineBinaire.append("0").append(temp);  
            }
        }
        return chaineBinaire.toString();
    }

    /**
     * calcule la taille du tableau necessaire pour stocker les info (message plus configuration)
     * 
     */
    public static void tabSize(String finalChain)throws IndexOutOfBoundsException{
        int longueur = (finalChain.length()) + 16;
        int size = 0;
        if (longueur <= 960){ // 32*32-32-31 = 961 bit dispo
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
     *  Ajoute les bit de configuration
     * 
     */
    public static String configAddition(String chaine,Config donnee){
        String completeBit = "111111";

        return (Constantes.indexParam1[donnee.getSize()] + Constantes.indexParam2[donnee.getDataType()] + Constantes.indexParam1[donnee.getCompressionMode()]) + (completeBit + (chaine));
    }
    
    /**
     * @pre - 
     * @post -
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
        correcteur(tab);
        return tab;
    }
    
    /**
     * 
     * Cree les bit de correction
     */
    
    public static void correcteur (int[][] data){
        // paire = 1
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
     * @pre - 
     * @post renvoie la configuration du code-barre
     */ 
    public Config getConfiguration(int size,int type, int compression){
        Config config = new Config (size,type,compression);
        return config;
    }
}
