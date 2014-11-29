public class schmilblik{
    public static boolean isPaire(int [][] tab, int place, boolean isLine,Configuration config){
        int compteur = 0;
        boolean isPaire = false;
        if(isLine){
            for (int i = 1; i < config.getSize(); i++){
                if(tab[place][i]==1){
                    compteur ++;
                }
            }
        }
        else{
            for (int i = 1; i < config.getSize(); i++){
                if(tab[i][place]==1){
                    compteur ++;
                }
            }
        }  
        return ((compteur%2) == 0);
    }
}
