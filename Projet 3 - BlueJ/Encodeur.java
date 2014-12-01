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
     * @pre - 
     * @post -
     */
    public int[][] tableauBinaire (String chaineBinaire){

        int [][]tableau = new int [32][32]; 
        int indexe = 0;

        int place = 1;
        int indice = 0; 
        
        // Generation du tableau :

        for (int i = 1; i < tableau.length; i++){
            for (int j = 1; j < tableau.length; j++){

                int c = Character.getNumericValue(chaineBinaire.charAt(indexe));
                tableau[j][i] = c; 
                indexe ++; 

                if (indexe >= chaineBinaire.length()){
                    j = tableau.length; 
                    i = tableau.length;
                }
            }
        } 
    }
    
    /**
     * 
     * Cree les bit de correction
     */
    
    public static int[][] correcteur (int[][] data){
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
     * @post -
     */ 
    public Configuration getConfiguration(){
        
    }
}
