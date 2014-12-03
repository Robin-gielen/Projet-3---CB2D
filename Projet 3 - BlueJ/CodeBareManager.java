import java.util.Scanner;
import barcode2d.*; 
import java.awt.*;
import java.io.*;
import javax.imageio.*;

/**
 * Cett
 * 
 * @author : Julien Banken, Robin Gielen, Jeremy Gossiaux  
 * @version : 2/12/2014
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
        boolean reponse = false;
        while (!reponse){
            Constantes.choixModeGeneral = UsableMethodes.value();
            if(Constantes.choixModeGeneral <=2 && Constantes.choixModeGeneral !=0){
                reponse = true;
            }
            else {
                System.out.println("Veuillez entrer une réponse valide");
            }
        }
        reponse = false;
        
        // Lire un code 2D :
        if (Constantes.choixModeGeneral == 1){
            String path; // Initialisation du chemin d'acces : 
            int width = 0;
            int height = 0;
            System.out.println ("Veuillez specifier le chemin d'acces de l'image : "); 

            Constantes.pathToImageFile = UsableMethodes.message();
            BarCode2DReader read = new BarCode2DReader();
            
            try{
                Image img = ImageIO.read(new File(Constantes.pathToImageFile));
                width = img.getWidth(null);
                height = img.getHeight(null);
            }
            catch(Exception e){
                System.out.println (" Erreur : " + e.getMessage()); 
            }
            
            try{
                read.loadBarCode2D(Constantes.pathToImageFile,width ,height);
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
            // Affiche le texte contenu dans le code barre.
            System.out.println(texte);
        }
        // Generer un code 2D :
        else if (Constantes.choixModeGeneral == 2){
            System.out.println("Voulez-vous ecrire votre texte ou qu'il soit lu dans un fichier?");
            System.out.println("\t 0) Ecriture du texte"); 
            System.out.println("\t 1) Lecture du fichier a la racine du code");
            String texte = "";
            int choix = 0;
            
            while (!reponse){
                choix = UsableMethodes.value();
                if(choix <= 1){
                    reponse = true;
                }
                else {
                    System.out.println("Veuillez entrer une réponse valide");
                }
            }
            reponse = false;

            
            if(choix == 0){
                System.out.println("Veuillez entrer votre texte : ");
                Constantes.messageToPrint = UsableMethodes.message();
                try{
                    texte = Encodeur.encode(Constantes.messageToPrint);
                }
                catch(Exception e){
                    System.out.println(" Erreur : " + e.getMessage()); 
                }
            }
            else{
                System.out.println("Veuillez entrer le nom de votre fichier");
                texte = UsableMethodes.texteFichier(UsableMethodes.message());
            }
            
            Encodeur.tabSize(texte);
            
            System.out.println("Veuillez entrer le numero du type de format que vous voulez utiliser");
            System.out.println("\t 0) ASCII 7bit"); 
            System.out.println("\t 1) ASCII long");
            System.out.println("\t 2) URL"); 
            System.out.println("\t 3) Kanji");
            
            int type = 0; 
            while (!reponse){
                type = UsableMethodes.value();
                if(type <= 3){
                    reponse = true;
                }
                else {
                    System.out.println("Veuillez entrer une réponse valide");
                }
            }
            reponse = false;
            
            System.out.println("Veuillez choisir si les information doivent être compressee");
            System.out.println("\t 0) Pas de compression"); 
            System.out.println("\t 1) Compression");

            int compression = 0;
            while (!reponse){
                compression = UsableMethodes.value();
                if(compression <=3){
                    reponse = true;
                }
                else {
                    System.out.println("Veuillez entrer une réponse valide");
                }
            }
            
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

