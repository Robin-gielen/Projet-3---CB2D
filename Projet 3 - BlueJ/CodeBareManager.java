import java.util.Scanner;
import barcode2d.*; 
import java.awt.*;
import java.io.*;
import java.net.*;

/**
 * 
 * 
 * @author Gielen Robin 
 * @version 29/11
 */
public class CodeBareManager{
    /**
     * Main executor of this class
     */
    public static void main (String[] args){
        System.out.println("Bienvenu dans notre lecteur-generateur de code-barres.");
        System.out.println("Veuillez choisir votre mode : "); 
        System.out.println("\t 1) Lire un code-barre"); 
        System.out.println("\t 2) Generer un code-barre");

        Constantes.choixModeGeneral = UsableMethodes.value();

        // Lire un code 2D :
        if (Constantes.choixModeGeneral == 1){

            // Initialisation du chemin d'acces : 
            String path; 
            System.out.println ("Veuillez specifier le chemin d'acces de l'image : "); 

            Constantes.pathToImageFile = UsableMethodes.message();
            
            BarCode2DReader read = new BarCode2DReader();
            
            try{
                read.loadBarCode2D(Constantes.pathToImageFile,32,32);
            }
            catch(IOException e){
                System.out.println (" Erreur : " + e.getMessage()); 
            }
            
            BarCode2DData bar = read.getBarCodeData();
            Config config = new Config (UsableMethodes.configurationBits(bar));
            Constantes.actualSize = ((config.getSize() + 1) * 32);
            Constantes.actualType = config.getDataType();
            Constantes.actualCompression = config.getCompressionMode();
            String texte = "";
            try{
                texte = Decodeur.decode(UsableMethodes.usableTab(bar,Constantes.actualSize));
            }
            catch(DecodingException e){
                System.out.println (" Erreur : " + e.getMessage()); 
            }
            
            if(Constantes.actualType == 2){
                URI uri = URI.create(texte);
                try{
                    Desktop.getDesktop().browse(uri);
                }
                catch(Exception e){
                    System.out.println (" Erreur : " + e.getMessage()); 
                }
            }
            else{
                System.out.println(texte);
            }
            
        }
        // Generer un code 2D :
        else if (Constantes.choixModeGeneral == 2){

            System.out.println("Veuillez entrer votre texte : ");
            Constantes.messageToPrint = UsableMethodes.message();
            
            String texte = "";
            try{
                texte = Encodeur.encode(Constantes.messageToPrint);
            }
            catch(Exception e){
                System.out.println(" Erreur : " + e.getMessage()); 
            }
            Encodeur.tabSize(texte);
            
            System.out.println("Veuillez entrer le numero du type de format que vous voulez utiliser");
            System.out.println("\t 0) ASCII 7bit"); 
            System.out.println("\t 1) ASCII long");
            System.out.println("\t 2) URL"); 
            System.out.println("\t 3) Kanji");
            int type = UsableMethodes.value();
            
            System.out.println("Veuillez choisir si les information doivent être compressee");
            System.out.println("\t 0) Pas de compression"); 
            System.out.println("\t 1) Compression");
            int compression = UsableMethodes.value();
            
            Config config = new Config (((Constantes.actualSize / 32) - 1),type,compression);
            texte = Encodeur.configAddition(texte,config);
            try{
                BarCode2D bare = new BarCode2D(Encodeur.fillTab(texte));
                BarCode2DFrame fenetre = new BarCode2DFrame(bare,Constantes.messageToPrint);
                BarCode2DWriter writer = new BarCode2DWriter(bare);
            
                System.out.println ("Veuillez specifier le chemin d'acces du repertoire ou sera stocker l'image : ");
                String path = UsableMethodes.message();
            
                writer.drawBarCode2D(path,bare.getWidth(),bare.getHeight());
            }
            catch(Exception e){
                System.out.println(" Erreur : " + e.getMessage()); 
            }            
        }
    }   
}

