

public class Config implements Configuration 
{
    int size;
    int dataType;
    int compressionMode;
    String dixBitsConfig;
    String [] tab1 = {"000","001","010","011","100","101","110","111"};
    String [] tab2 = {"0000","0001","0010","0011","0100","0101","0110","0111","1000","1001","1010","1011","1100","1101","1110","1111"};
    
    /**
     * Ce constructeur prend en argument une chaine de 10 caracteres (1 et 0) qui sont tires du code-barre en train d etre lu.
     * 
     */
    public Config (String dixBitsConfig){
        this.dixBitsConfig = dixBitsConfig;
    }
    
    public Config (int size, int dataType, int compressionMode){
        
        this.size = size;
        this.dataType = dataType;
        this.compressionMode = compressionMode;
    }
    
    public int getSize(){
        if (dixBitsConfig != null){
            size = index(tab1,0,3);
        }
        return size;
    }
    
    public int getDataType(){
        if (dixBitsConfig != null){
            dataType = index(tab2,3,7);
        }
        return dataType;
    }
    
    public int getCompressionMode(){
        if (dixBitsConfig != null){
            compressionMode = index(tab1,7,10);
    	}
    	return compressionMode;
    }
    
    public int index (String [] tab, int begin, int end){
        int index = 100;     
        String temp = dixBitsConfig.substring(begin, end);
        int fin = tab.length;
        for (int i = 0; i < fin; i++){
            if (tab[i].equals(temp)){
    			index = i;
    			i = fin;
    		}
    	}
    	return index;
    }
}
