
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
}
