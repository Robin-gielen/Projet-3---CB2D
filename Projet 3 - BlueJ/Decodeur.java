import barcode2d.BarCode2DReader;

/**
 * Cette classe a pour but de lire un code-barre 2D et en extraire les informations 
 * (texte, taille, type de compression)
 * 
 * @author Gielen Robin 
 * @version 
 */
public class Decodeur implements Decoder
{
    /* 
    * @pre - data != null
    *        data est une matrice carree, de taille 32, 64, 128 ou 256
    *        data ne contient que des 0 et des 1
    * @post - La valeur renvoyee contient le decodage de la matrice de bits data
    *        decodee en respectant la configuration de ce decodeur
	* A ajouter : 
	*		Si la matrice contient une erreur, celle-ci est corrigee
    * @throw DecodingException au cas ou la matrice data ne peut pas etre decodee
    */
    public String decode(int[][] data)
    {
        StringBuffer msg = new StringBuffer(); 
        if (check (data)) {
            for (int i = 1; i < data.length; i++) {
                for (int j = 1; j < data[i].length; j++) {
                    msg.append(data[i][j]); 
                }
            }
        }
        return convertir(msg.toString());
    }
	
    /* 
    * @pre - data != null
    *        data est une matrice carree, de taille 32, 64, 128 ou 256
    *        data ne contient que des 0 et des 1
    * @post - Renvoi une chaine de caracteres qui correspond au code binaire 
    */
	public String convertir (String msg) 
	{
        StringBuffer texte = new StringBuffer(); 
        // On selectionne les bytes 8 par 8 : 
        for (int i = 0; i < msg.length()/8; i++) {
            int lettre = Integer.parseInt(msg.substring(8*i,(i+1)*8),2);
            texte.append((char)(lettre));
        }
        return texte.toString();
    }
	
    /* 
    * @pre - data != null
	*		 data est une matrice carree, de taille 32, 64, 128 ou 256
    *        data ne contient que des 0 et des 1
    * @post - La valeur renvoyee contient true si data ne contient pas
     *        d’erreurs (la parite de la matrice de bits est valide),
     *        et false sinon
    */
    public boolean check(int[][] data)
    {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                // On verifie si le tableau contient des 0 et des 1
                if ((data[i][j] != 0) && (data[i][j] != 1)) {
                    return false; 
                }  
                // On verifie si c'est une matrice carree et si ses dimensions sont egales à 32, 64, 128 ou 256  
                if ((data.length != data[i].length) || (data[i].length != 32) && (data[i].length != 64) && (data[i].length != 128) && (data[i].length != 256)){
                    return false; 
                }
            }
        }
        return true;
    }
	
    /* 
    * @pre - data != null
	*		 data est une matrice carree, de taille 32, 64, 128 ou 256
    *        data ne contient que des 0 et des 1
    * @post - Detecte les erreurs : Affiche les lignes et les colonnes des anomalies
    */
	public static void detection (int [][]data) {

        int compteurColonne = 0; 
        int compteurLigne = 0;
        int indicePariteColonne;
        int indicePariteLigne;
        
        // i = colonne
        // j = ligne

        // Les lignes de parite horizontale :
        for (int i = 1; i < data.length; i ++){
            // On calcul le nombre de 1 (colonne)
            for (int j = 1; j < data[i].length; j++){
                if(data[j][i] == 1){
                    compteurColonne ++;
                }
            }
            // Si le nombre de 1 est paire :
            if (compteurColonne%2 == 0) {
                indicePariteColonne = 0; 
            }
            // Si le nombre de 1 est impaire :
            else {
                indicePariteColonne = 1;
            }

            // On reset le compteur  
            compteurColonne = 0;

            // Si le bit de parite est different de ce que nous venons de calculer
            if (data[0][i] != indicePariteColonne ) {

                System.out.println("Erreur trouvee a la colonne : " + (i + 1));

                for (int k = 1 ; k < data.length; k++) {
                    // On calcul le nombre de 1 (ligne)
                    for (int l = 1; l < data[k].length; l++) {

                        if (data[k][l] == 1) {
                            compteurLigne++; 
                        }
                    }
                    // Si le nombre de 1 est paire
                    if (compteurLigne%2 == 0) {
                        indicePariteLigne = 0; 
                    }
                    // Si le nombre de 1 est impaire
                    else {
                        indicePariteLigne = 1;
                    }

                    // On reset le compteur 
                    compteurLigne = 0; 

                    if (data[k][0] != indicePariteLigne) {
                        System.out.println("Erreur trouvee a la ligne : " + (k + 1));
                    } 
                }  
            }
        }
    }
	
    /* 
    * @pre - 
    * @post - 
    */
    public Configuration getConfiguration()
    {
        
    }
 
    /* 
    * @pre - image != null
    * @post - 
    */ 
    public int[][] lecteur (String path)
    {

    }
	
	/* 
    * @pre - Type du tableau : int
	*		data != null
    * @post - Affiche l'integralite du tableau
    */
	public void afficher (int[][]data) 
	{
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                System.out.print(data[i][j]);
            }
            System.out.println();
        }
    }
}
