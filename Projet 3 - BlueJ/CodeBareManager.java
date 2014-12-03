import java.util.Scanner;
import barcode2d.*; 
import java.awt.*;
import java.io.*;
import javax.imageio.*;

/* 
 * Cette class comporte la methode main, elle permet de gerer les interactions entre les utilisateurs et le programme
 * Elle fait appel aux methodes disponibles pour le projet
 * 
 * @author : Julien Banken, Robin Gielen, Jeremy Gossiaux  
 * @version : 2/12/2014
 */
public class CodeBareManager{
    /**
     * Class principal du logiciel
     */
    public static void main (String[] args){
        // On suprimer le texte present dans la console avant l'execution 
        System.out.println("\f"); 
        // Menu de navigation
        System.out.println("Bienvenu dans notre lecteur-generateur de code-barres.");
        System.out.println(); 
        System.out.println("Veuillez choisir votre mode : "); 
        System.out.println("\t 1) Lire un code-barre"); 
        System.out.println("\t 2) Generer un code-barre");
        System.out.println();

        boolean reponse = false;
        // Executer la boucle tant que reponse n'est pas valide
        while (!reponse){
            // Appel de la methode Constante qui contient plusieurs variables
            // On assigne la variable choixModeGeneral a la valeur entree par l'utilisateur (int)
            // La class gere les exception ... 
            Constantes.choixModeGeneral = UsableMethodes.value();
            // Si la valeur de cette constantes 
            if(Constantes.choixModeGeneral <=2 && Constantes.choixModeGeneral !=0){
                reponse = true;
            }
            else {
                System.out.println("Veuillez entrer une reponse valide");
            }
        }

        reponse = false;

        // Lire un code 2D :
        if (Constantes.choixModeGeneral == 1){
            String path; // Initialisation du chemin d'acces : 
            int width = 0;
            int height = 0;
            System.out.println ("Veuillez specifier le chemin d'acces de l'image : ");  
            System.out.println ("\t Exemple : C:\\Users\\<nom de l'utilisateur>\\Documents\\");  
            // On assigne la variable pathToImageFile a la valeur entree par l'utilisateur (String)
            Constantes.pathToImageFile = UsableMethodes.message();
            //Appel du consructeur 
            BarCode2DReader read = new BarCode2DReader();

            try{
                // Appel du constructeur Image : Creation d'un nouveau fichier (constante)
                Image img = ImageIO.read(new File(Constantes.pathToImageFile));
                // On assigne la hauteur et la largeur de l'image aux variables
                width = img.getWidth(null);
                height = img.getHeight(null);
            }
            catch(Exception e){
                System.out.println (" Erreur : " + e.getMessage()); 
            }

            try{
                // Lire le fichier (constantes, chemins d'accès, largeur, hauteur)
                read.loadBarCode2D(Constantes.pathToImageFile,width ,height);
            }
            catch(IOException e){
                System.out.println (" Erreur : " + e.getMessage()); 
            }
            // Appel du constructeur BarCode2DData : Lire les donnees du code barre
            BarCode2DData bar = read.getBarCodeData();
            // Appel du constructeur config : 
            Config config = new Config (UsableMethodes.configurationBits(bar));
            // On modifie les valeurs des constantes
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
            // Affiche le texte contenu dans le code barre
            System.out.println();
            System.out.println("Le message : " + texte);
            System.out.println(texte);

            try {
                Traduction.traduction(texte);
            }
            catch (IOException e) {
                System.out.println(e.getMessage()); 
            }

        }
        // Generer un code 2D :
        else if (Constantes.choixModeGeneral == 2){
            System.out.println("Voulez-vous entrer votre texte ou lire un fichier externe contenant du texte ?");
            System.out.println("\t 0) Ecriture le texte dans la console"); 
            System.out.println("\t 1) Lire un fichier dans la racine du code");
            String texte = "";
            int choix = 0;
            // Tant que la reponse est valide : 
            while (!reponse){
                choix = UsableMethodes.value();
                if(choix <= 1){
                    reponse = true;
                }
                else {
                    System.out.println("Veuillez entrer une reponse valide");
                }
            }

            reponse = false;

            if(choix == 0){
                System.out.println("Veuillez entrer votre texte : ");
                // On modifie la valeur de la constante messageToPrint
                Constantes.messageToPrint = UsableMethodes.message();
            }
            else{
                System.out.println("Veuillez entrer le nom de votre fichier");
                Constantes.messageToPrint = UsableMethodes.texteFichier(UsableMethodes.message());
            }

            try{
                // On appel la classe encoder 
                texte = Encodeur.encode(Constantes.messageToPrint);
            }
            catch(Exception e){
                System.out.println(" Erreur : " + e.getMessage()); 
            }
            Encodeur.tabSize(texte);

            System.out.println("Veuillez entrer le numero du type de format :");
            System.out.println("\t 0) ASCII 7bit"); 
            System.out.println("\t 1) ASCII long");
            System.out.println("\t 2) URL"); 
            System.out.println("\t 3) Kanji");

            int type = 0; 

            // Tant que la response n'est pas valide : Effectuer la boucle 
            while (!reponse){
                type = UsableMethodes.value();
                if(type <= 3){
                    reponse = true;
                }
                else {
                    System.out.println("Veuillez entrer une reponse valide");
                }
            }

            reponse = false;

            System.out.println("Souhaitez-vous compresser le fichier ?");
            System.out.println("\t 0) Pas de compression"); 
            System.out.println("\t 1) Compression");

            int compression = 0;

            while (!reponse){
                // 
                compression = UsableMethodes.value();
                if(compression <=3){
                    reponse = true;
                }
                else {
                    System.out.println("Veuillez entrer une reponse valide");
                }
            }

            // Appel du constructeur config
            Config config = new Config (((Constantes.actualSize / 32) - 1),type,compression);
            texte = Encodeur.configAddition(texte,config);

            try{
                // Creation de l'objet BarCode2D : 
                BarCode2D bare = new BarCode2D(Encodeur.fillTab(texte));
                // Creation de l'objet BarCode2DFrame : 
                BarCode2DFrame fenetre = new BarCode2DFrame(bare,Constantes.messageToPrint);
                BarCode2DWriter writer = new BarCode2DWriter(bare);

                System.out.println ("Veuillez specifier le chemin d'acces ou sera stocker l'image : ");
                String path = UsableMethodes.message();
                // Ecriture du code barre
                writer.drawBarCode2D(path,bare.getWidth(),bare.getHeight());
            }
            catch(Exception e){
                System.out.println(" Erreur : " + e.getMessage()); 
            } 
        }
    }   
}

