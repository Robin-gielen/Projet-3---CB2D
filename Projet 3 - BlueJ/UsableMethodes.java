import barcode2d.*;
/**
 * 
 * 
 * @author  
 * @version 
 */
public class UsableMethodes
{
    
    /**
     * Methode qui check si la ligne a un nombre de 1 pair ou impair
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
     * renvoie les bit de configuration
     * 
     */
    public static String configurationBits(BarCode2DData bar){
        StringBuffer configuration = new StringBuffer(); 
        for (int i = 1; i < 11; i++){
            if (bar.getValue(2,i)){
                configuration.append("1");
            }
            else{
                configuration.append("0");
            }
        }
        return (configuration.toString());
    }
    
    /**
     * crée le tableau a utilisé
     * 
     */
    public static int[][] usableTab (BarCode2DData bar, int size){
        int [][] tab = new int [size][size];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(bar.getValue(i,j)){
                    tab[i][j] = 1;
                }
            }
        }
        return tab;
    }
}
