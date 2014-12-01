import barcode2d.*;

/**
 * Cette classe a pour fonction de generer des codes barres a partir de texte
 * 
 * @author  Gielen Robin, Julien Banken, Jeremy Gossiaux
 * @version 29/11/2014
 */
public class Encodeur implements Encoder{
        /**
         * @pre   msg != null
         * @post  La valeur renvoyee contient une matrice de bits correspondant
         *        au message msg , encode en respectant la configuration de cet encodeur
         * @throw EncodingException au cas ou le message msg ne peut pas etre encode
         */
    public int[][] encode (String msg)throws EncodingException{
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
        return tableauBinaire(chaineBinaire.toString());
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
        else if(longeur <= 3969){
            size = 1;
        }
        else if(longeur <= 16129){
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
    public String configAddition(String Chaine,Configuration donnee){
        String completeBit = 111111;
        
        String configuration = constantes.indexParam1[donnee.getSize] + constantes.indexParam2[donnee.getType] + constantes.indexParam1[donnee.GetCompression];
        
        String finalChain = configuration.append(chaine);
        
        return finalChain;
    }
    
    /**
     * @pre - 
     * @post -
     */
    public int[][] fillTab (String chaineBinaire,Configuration config){
        int size =((config.getSize + 1) * 32);
        int [][] tab = new int [size][size];
        int index = 0;
        
        for (int i = 1; i < tab.length; i++){
            for (int j = 1; j < tab.length; j++){
                tableau[j][i] = chaineBinaire.charAt(index); 
                if (indexe >= chaineBinaire.length()){
                    j = tableau.length; 
                    i = tableau.length;
                }
                indexe ++; 
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
        if (UsableMethodes.isPaire(data,0,true) == UsableMethodes.isPaire(data,0,false)){
            if (UsableMethodes.isPaire(data,0,true)){
                data[0][0] = 1;
            }
        }
        else{
            System.out.println("Erreur : Les bits de parite ne correspondent pas");
        }
        return data;
    }
    
    
    /**
     * @pre - 
     * @post renvoie la configuration du code-barre
     */ 
    public Configuration getConfiguration(){
        Config config = new Config (0,0,0);
        return config;
    }
}
