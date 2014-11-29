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
    */
    public String decode(int[][] data) throws DecodingException
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
                if ((data[i][j] != 0) && (data[i][j] != 1)) {
                    return false; 
                }  
            }
        }
        return true;
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
		// Lire une image externe : 
        Image code2D = ImageIO.read(new File (path)); 
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
