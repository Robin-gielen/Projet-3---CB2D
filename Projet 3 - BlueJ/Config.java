/**
 * 
 * @author : Julien Banken, Robin Gielen, Jeremy Gossiaux  
 * @version : 30/11/2014
 */
public class Config{
    int size;
    int dataType;
    int compressionMode;
    String dixBitsConfig;
    
    /**
     * Ce constructeur prend en argument une chaine de 10 caracteres (1 et 0) qui sont tires du code-barre en train d etre lu.
     * 
     */
    public Config (String dixBitsConfig){
        this.dixBitsConfig = dixBitsConfig;
    }
    
    /**
     * 
     * 
     */
    public Config (int size, int dataType, int compressionMode){
        
        this.size = size;
        this.dataType = dataType;
        this.compressionMode = compressionMode;
    }
    
    /**
     * 
     * 
     */
    public int getSize(){
        if (dixBitsConfig != null){
            size = index(Constantes.indexParam1,0,3);
        }
        return size;
    }
    
    /**
     * 
     * 
     */
    public int getDataType(){
        if (dixBitsConfig != null){
            dataType = index(Constantes.indexParam2,3,7);
        }
        return dataType;
    }
    
    /**
     * 
     * 
     */
    public int getCompressionMode(){
        if (dixBitsConfig != null){
            compressionMode = index(Constantes.indexParam1,7,10);
        }
        return compressionMode;
    }
    
    /**
     * 
     * 
     */
    public int index (String [] tab, int begin, int end){
        int index = 100;     
        String temp = dixBitsConfig.substring(begin, end);
        int endOfTab = tab.length;
        for (int i = 0; i < endOfTab; i++){
            if (tab[i].equals(temp)){
                index = i;
                i = endOfTab;
            }
        }
        return index;
    }
}
