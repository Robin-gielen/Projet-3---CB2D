import barcode2d.*;
/**
 * Cette classe cree un objet BarCode2DData
 * 
 * @author : Julien Banken, Robin Gielen, Jeremy Gossiaux   
 * @version : 29/11/2014
 */
public class BarCode2D implements BarCode2DData{
    int[][] data;
    
    /**
     * Ce constructeur prend en argument la matrice de bits correspondant au au code barre qui doit être genere
     */
    public BarCode2D(int [][] data){
        this.data = data;
    }
    
    /**
     * Cette methode renvoie la hauteur du code barre a cree
     */
    public int getHeight(){
        return Constantes.actualSize;
    }
    
    /**
     * Cette methode renvoie la valeur en position (i;j) de la matrice de bits
     */
    public boolean getValue(int line, int column){
        return (data[line][column] == 1);
    }
    
    /**
     * Cette methode renvoie la largeur du code barre a cree
     */
    public int getWidth(){
        return Constantes.actualSize;
    }
}
