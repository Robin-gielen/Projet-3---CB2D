/**
 * Cette classe a pour but de lire un code-barre 2D et en extraire les informations 
 * (texte, taille, type de compression)
 * 
 * @author : Julien Banken, Robin Gielen, Jeremy Gossiaux  
 * @version : 2/12/2014
 */
public class Decodeur{
    /**
     * @pre  : data != null
     *        data est une matrice carree, de taille 32, 64, 128 ou 256
     *        data ne contient que des 0 et des 1
     * @post : La valeur renvoyee contient le decodage de la matrice de bits data
     *        decodee en respectant la configuration de ce decodeur
     * @throw : DecodingException au cas ou la matrice data ne peut pas etre decodee
     */
    public static String decode(int[][] data) throws DecodingException{
        StringBuffer msg = new StringBuffer(); 
        
        if (check (data, Constantes.actualSize)){
            for (int i = 2; i < Constantes.actualSize; i++) {
                for (int j = 1; j < Constantes.actualSize; j++) {
                    msg.append(data[i][j]); 
                }
            }
        }
        else{
            throw new DecodingException();
        }
        return convertir(configurationEnd(data) + ((msg.toString().substring(0,msg.length()-1))));
    }
    
    /** 
     * @pre  : data != null
     *        data est une matrice carree, de taille 32, 64, 128 ou 256
     *        data ne contient que des 0 et des 1
     * @post : Renvoi une chaine de caracteres qui correspond au code binaire 
     */
    public static String convertir (String msg){
        StringBuffer texte = new StringBuffer();
        
        // On selectionne les bytes 8 par 8 : 
        for (int i = 0; i < msg.length()/8; i++) {
            int lettre = Integer.parseInt(msg.substring(8*i,(i+1)*8),2);
            texte.append((char)(lettre));
        }
        return texte.toString();
    }
    
    /** 
     * @pre - data != null
     *        data est une matrice carree, de taille 32, 64, 128 ou 256
     *        data ne contient que des 0 et des 1
     * @post - Detecte les erreurs : Affiche les lignes et les colonnes des anomalies
     */
    public static boolean check (int [][]data,int size){
        int compteurErreurLine = 0;
        int compteurErreurColumn = 0;
        int erreurLine = 0;//Index de la ligne ou se trouve l'erreur
        int erreurColumn = 0;//Index de la colonne ou se trouve l'erreur
        boolean isValide = true;
        int bit = 0;
        
        //Verrifie les lignes
        for (int i =0; i < size; i++){
            if (UsableMethodes.isPaire(data,i,true)){
                bit = 1;
            }
            if  (bit != data[i][0]){
                compteurErreurLine ++;
                if (compteurErreurLine < 0){
                    erreurLine = i;
                }
                else{
                    i = size;
                }
            }
            bit = 0;
        }
        bit = 0;
        
        //Verifie les colonnes
        for (int i =0; i < size && compteurErreurLine < 2; i++){
            if(UsableMethodes.isPaire(data,i,false)){
                bit = 1;
            }
            if  (bit != data[0][i]){
                compteurErreurColumn ++;
                if (compteurErreurColumn < 0){
                    erreurColumn = i;
                }
                else{
                    i = size;
                }
            }
            bit = 0;
        }
        
        //Verifie si la matrice peut etre corrigee, si oui, la corrige en appelant la methode de correction
        if (compteurErreurLine > 1){
            isValide = false;
        }
        else if (compteurErreurLine == 1){
            correction(erreurLine, erreurColumn, data);
        }
        return isValide;
    }
    
    /**
     * Methode qui corrige la matrice de bits si il n'y a qu'une faute
     * 
     * @pre : line != null, colonne != null, data != null
     * @post : Corrige la matrice de bits
     */
    public static void correction (int line, int colonne, int[][]data){
        if (data[colonne][line] == 0){
            data[colonne][line] = 1;
        }
        else{
            data[colonne][line] = 0;
        }
    }
    
    /**
     * Methode qui renvoie les bits sur la derniere ligne entamee par les bits de configuraton
     * 
     * @pre : data != null
     * @post : Retourne les bits de texte se trouvant sur la ligne ou se termine les bits de configuration
     */
    public static String configurationEnd(int[][] data){
        StringBuffer bitBegin = new StringBuffer();       
        
        for (int i = 17; i < Constantes.actualSize; i++){
            bitBegin = bitBegin.append(data[1][i]);
        }
        return (bitBegin.toString());
    }
    
    /**
     * @pre : / 
     * @post : Renvoie un objet Config contenant les parametres du code barre
     */
    public static Config getConfiguration(String configuration){
        Config config = new Config (configuration);
        return config;
    }
}
