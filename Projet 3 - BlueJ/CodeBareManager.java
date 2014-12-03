import java.util.Scanner;
import barcode2d.*;
// Le copier-coller : 
import java.awt.datatransfer.*;
import java.awt.*;
import java.io.*;

// Le lien : 
import java.net.*;


/**
 * 
 * 
 * @author Gielen Robin 
 * @version 29/11
 */
public class CodeBareManager
{
    /**
     * Main executor of this class
     */
    public static void main (String[] args)
    {
        System.out.println("Bienvenu dans notre lecteur-generateur de code-barres.");
        System.out.println("Veuillez choisir votre mode : "); 

        System.out.println("\t 1) Lire un code-barre"); 
        System.out.println("\t 2) Generer un code-barre");

        // On try{...} catch{...} les "Scanner" qui sont susceptibles de lever une exception
        try {
            Scanner sc = new Scanner(System.in); 
            Constantes.choixModeGeneral = sc.nextInt();
        }
        catch (Exception e) {
            System.out.println (" Erreur : " + e.getMessage()); 
        }

        // Lire un code 2D :
        if (Constantes.choixModeGeneral == 1){

            // Initialisation du chemin d'acces : 
            String path; 
            System.out.println ("Veuillez specifier le chemin d'acces de l'image : "); 

            try{
                Scanner cheminAcces = new Scanner(System.in); 
                Constantes.pathToImageFile = cheminAcces.nextLine();
            } catch(Exception e){
                System.out.println (" Erreur : " + e.getMessage()); 
            }
            
            BarCode2DReader read = new BarCode2DReader();
            
            try{
                read.loadBarCode2D(Constantes.pathToImageFile,256,256);
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
            try {
                Scanner clavier = new Scanner(System.in);
                Constantes.messageToPrint = clavier.nextLine(); 
            }
            catch(Exception e){
                System.out.println(" Erreur : " + e.getMessage()); 
            }

            //afficher(encode(Constantes.messageToPrint)); 
        }
    }

    
}

