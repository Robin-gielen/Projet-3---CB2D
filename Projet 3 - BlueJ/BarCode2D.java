import barcode2d.*;
/**
 * Write a description of class BaraCode2D here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BarCode2D implements BarCode2DData{
    int[][] data;
    int size;
    int heigth;
    public BarCode2D(int [][] data, int size){
        this.data = data;
        this.size = size;
    }
    
    public int getHeight(){
        return data[0].length;
    }
    
    public boolean getValue(int line, int column){
        return (data[line][column] == 1);
    }
    
    public int getWidth(){
        return size;
    }
}
