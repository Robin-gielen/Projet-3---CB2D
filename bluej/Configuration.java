
/**
 * Write a description of interface Configuration here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public interface Configuration
{
    /**
     * @pre  -
     * @post La valeur renvoyee contient la taille du code-barres
     */
    public int getSize();
    
    /**
     * @pre  -
     * @post La valeur renvoyee contient le type de données du code-barres
     */
    public int getDataType();
    
    /**
     * @pre  -
     * @post La valeur renvoyee contient le type de compression du code-barres
     */
    public int getCompressionMode();
    
}
