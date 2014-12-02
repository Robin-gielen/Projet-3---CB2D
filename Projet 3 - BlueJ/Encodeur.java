
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
    public String encode (String msg)throws EncodingException{
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
    public int tabSize(String finalChain){
        int longueur = finalChain.length();
        int size = 0;
        if (longueur <= 961){ // 32*32-32-31 = 961 bit dispo
            size = 0;
        }
        else if(longueur <= 3969){
            size = 1;
        }
        else if(longueur <= 16129){
            size = 2;
        }
        else{
            size = 3;
        }
        
        return size;
    }
    
    /**
     *  Ajoute les bit de configuration
     * 
     */
    public String configAddition(String chaine,Config donnee){
        String completeBit = "111111";

        return (Constantes.indexParam1[donnee.getSize()] + Constantes.indexParam2[donnee.getDataType()] + Constantes.indexParam1[donnee.getCompressionMode()]) + (completeBit + (chaine));
    }
    
    /**
     * @pre - 
     * @post -
     */
    public int[][] fillTab (String chaineBinaire,Config config){
        int size =((config.getSize() + 1) * 32);
        int [][] tab = new int [size][size];
        int index = 0;
        
        for (int i = 1; i < tab.length; i++){
            for (int j = 1; j < tab.length; j++){
                tab[j][i] = chaineBinaire.charAt(index); 
                if (index >= chaineBinaire.length()){
                    j = tab.length; 
                    i = tab.length;
                }
                index ++; 
            }
        } 
        return tab;
    }
    
    /**
     * 
     * Cree les bit de correction
     */
    
    public int[][] correcteur (int[][] data){
        // paire = 1
        //ligne
        for (int i = 1; i < data.length; i++){
            if (UsableMethodes.isPaire(data,i,true)){
                data[0][i] = 1;
            }
        }
        //colone
        for (int i = 1; i < data.length; i++){
            if (UsableMethodes.isPaire(data,i,false)){
                data[i][0] = 1;
            }
        }

        if (UsableMethodes.isPaire(data,0,true)){
            data[0][0] = 1;
        }
        return data;
    }
    
    
    /**
     * @pre - 
     * @post renvoie la configuration du code-barre
     */ 
    public Config getConfiguration(int size){
        Config config = new Config (size,0,0);
        return config;
    }
}
