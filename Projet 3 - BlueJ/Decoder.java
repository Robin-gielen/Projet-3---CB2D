public interface Decoder
{
    /**
     * @pre data != null
     *      data est une matrice carree, de taille 32, 64, 128 ou 256
     *      data ne contient que des 0 et des 1
     * @post La valeur renvoyee contient le decodage de la matrice de bits data
     *      decodee en respectant la configuration de ce decodeur
     *      Si la matrice contient une erreur, celle-ci est corrigee
     * @throw DecodingException au cas ou la matrice data ne peut pas etre decodee
     */
    public String decode(int [][] data);
    
    /**
     * @pre data != null
     *      data est une matrice carree, de taille 32, 64, 128 ou 256
     *      data ne contient que des 0 et des 1
     * @post La valeur renvoyee contient true si data ne contient pas
     *      d’ erreurs (la parite de la matrice de bits est valide),
     *      et false sinon
    */
    public boolean check(int [][] data);
    
    /**
     * @pre -
     * @post La valeur renvoyee contient la configuration de ce decodeur
    */
    public Configuration getConfiguration();
}
