
/**
 * Write a description of interface Encoder here.
 * 
 * @author 
 * @version 
 */
    public interface Encoder
    {
        /**
         * @pre msg != null
         * @post La valeur renvoyee contient une matrice de bits correspondant
         * au message msg , encode en respectant la configuration de cet encodeur
         * @throw EncodingException au cas ou le message msg ne peut pas etre encode
         */
        public int[][] encode (String msg);
        
        /**
         * @pre -
         * @post La valeur renvoyee contient la configuration de cet encodeur
         */
        public Configuration getconfiguration();
    }

