/**
 * Cette classe a pour but de cree un objet Config qui renvoie la taille, le type d'ecriture et la compression du code barre.
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
     */
    public Config (String dixBitsConfig){
        this.dixBitsConfig = dixBitsConfig;
    }
    
    /**
     * Ce constructeur prend en argument la taille, le type de donnee et le mode de compression qui sont tire du code-barre en train d etre cree.
     */
    public Config (int size, int dataType, int compressionMode){
        this.size = size;
        this.dataType = dataType;
        this.compressionMode = compressionMode;
    }
    
    /**
     * Cette methode renvoie la taille de la matrice de bits.
     */
    public int getSize(){
        if (dixBitsConfig != null){
            size = index(Constantes.indexParam1,0,3);
        }
        return size;
    }
    
    /**
     * Cette methode renvoie le type d'ecriture du code barre. 
     */
    public int getDataType(){
        if (dixBitsConfig != null){
            dataType = index(Constantes.indexParam2,3,7);
        }
        return dataType;
    }
    
    /**
     * Cette methode renvoie le type de compression du code barre.
     */
    public int getCompressionMode(){
        if (dixBitsConfig != null){
            compressionMode = index(Constantes.indexParam1,7,10);
        }
        return compressionMode;
    }
    
    /**
     * Cette methode renvoie les parametres du code barre (taille, type, compression).
     * 
     * @pre : tab != null, begin <= 9, end <=10
     * @post : renvoie le parametre de l'information demandee
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
