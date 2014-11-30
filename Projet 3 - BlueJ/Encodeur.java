import barcode2d.*;

/**
 * Cette classe a pour fonction de generer des codes barres a partir de texte
 * 
 * @author  Gielen Robin
 * @version 29/11/2014
 */
public class Encodeur implements Encoder
{
    /**
     * @pre - 
	 * @post -
     */
    public int[][] encode (String msg)
    {
        StringBuffer chaineBinaire = new StringBuffer(); 

        for (int i = 0; i < msg.length(); i++) {
            if (msg.contains(" ")){
                if (msg.charAt(i) == ' '){
                    chaineBinaire.append("00100000"); 
                }
                else {
                    chaineBinaire.append("0").append(Integer.toBinaryString(msg.charAt(i)));
                }   
            }
            else {
                chaineBinaire.append("0").append(Integer.toBinaryString(msg.charAt(i)));
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

		/*
		System.out.println();
        System.out.println("Dimension : " + tableau.length);
        System.out.println("Chaine binaire :" + chaineBinaire);
        System.out.println ("Taille du String : " + chaineBinaire.length()); 
        System.out.println(); 
		*/
		
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

        // Generation des bits de parite :

        // Paire = 0
        // Impaire = 1; 

        while (place < tableau.length) {
            // Ligne : 
            if (UsableMethodes.isPaire(tableau, place, true)) { 
                tableau [place][0] = 0; 
            }
            else{
                tableau [place][0] = 1; 
            }
            // Colonne : 
            if (UsableMethodes.isPaire(tableau, place, false)) { 
                tableau [0][place] = 0; 
            }
            else{
                tableau [0][place] = 1; 
            }

            place++; 
        }

        // Bit de parite (0;0) :

        if (UsableMethodes.isPaire (tableau,indice, true) == UsableMethodes.isPaire (tableau, indice, false)) { 

            if (UsableMethodes.isPaire (tableau,indice, true)) {
                // Paire
                tableau [0][0] = 1;
            }
            else {
                // Impaire
                tableau [0][0] = 1;
            }
        }
        else {
            System.out.println("Erreur : Les bits de parite ne correspondent pas"); 
        }
        return tableau; 
    }

    /**
     * @pre - 
	 * @post -
     */	
    public static void afficher (int[][]tableau) {

        for (int i = 0; i < tableau.length; i++) {
            for (int j = 0; j < tableau.length; j++) {
                System.out.print(tableau[j][i]);
            }
            System.out.println();
        }

    }
	
    /**
     * @pre - 
	 * @post -
     */	
    public Configuration getConfiguration()
    {
        
    }
}
