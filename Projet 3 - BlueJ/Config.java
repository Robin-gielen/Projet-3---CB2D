
/**
 * Write a description of class Config here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Config implements Configuration 
{
    int size;
    int dataType;
    int compressionMode;
    
    /**
     * Ce constructeur prend en argument une chaine de 7 caracteres (1 et 0) qui sont tires du code-barre en train d etre lu.
     * 
     */
    public Config (String septBitsConfig)
    {
        
    }
    
    public Config (int size, int dataType, int compressionMode)
    {
        this.size = size;
        this.dataType = dataType;
        this.compressionMode = compressionMode;
    }
    
    public int getSize()
    {
        
    }
    
    public int getDataType()
    {
        
    }
    
    public int getCompressionMode()
    {
        
    }
    
}
