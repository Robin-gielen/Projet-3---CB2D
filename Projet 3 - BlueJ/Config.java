

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
    		String tempBit1 = dixBitsConfig.substring(0, 3);
    		for (int i = 0; i < 8; i++){
    			if (tab1[i].equals(tempBit1)){
    				size = i;
    				i = 8;
    			}
    		}
    	}
        return size;
    }
    
    public int getDataType(){
    	if (dixBitsConfig != null){
    		String tempBit2 = dixBitsConfig.substring(3, 7);
    		for (int i = 0; i < 16; i++){
    			if (tab2[i].equals(tempBit2)){
    				dataType = i;
    				i = 16;    				
    			}
    		}
    	}
    	return dataType;
    }
    
    public int getCompressionMode(){
    	if (dixBitsConfig != null){
    		String tempBit3 = dixBitsConfig.substring(7, 10);
    		for (int i = 0; i < 8; i++){
    			if (tab1[i].equals(tempBit3)){
    				compressionMode = i;
    				i = 8;
    			}
    		}
    	}
    	return compressionMode;
    }
    
}
