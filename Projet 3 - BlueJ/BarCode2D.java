import barcode2d.*;
/**
 * Write a description of class BaraCode2D here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BarCode2D implements BarCode2DData{
    int[][] data;
    public BarCode2D(int [][] data){
        this.data = data;
    }
    
    public int getHeight(){
        return Constantes.actualSize;
    }
    
    public boolean getValue(int line, int column){
        return (data[line][column] == 1);
    }
    
    public int getWidth(){
        return Constantes.actualSize;
    }
}
