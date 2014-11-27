public class schmilblik{
    public static boolean isPaire(int [][] tab, int place, boolean isLine){
        int compteur = 0;
        boolean isPaire = false;
        if(isLine){
            for (int i = 1; i < 10; i++){
                if(tab[place][i]==1){
                    compteur ++;
                }
            }
        }
        else{
            for (int i = 1; i < 10; i++){
                if(tab[i][place]==1){
                    compteur ++;
                }
            }
        }  
        if(compteur%2 ==0){
            isPaire = true;
        } 
        return (isPaire);
    }
}
