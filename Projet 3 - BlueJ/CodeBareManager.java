import java.util.Scanner;
import barcode2d.*;

// Le copier-coller : 
import java.awt.datatransfer.*;
import java.awt.*;
import java.io.*;

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

        // Lire un code 2D
        if (Constantes.choixModeGeneral == 1){

            String path; 
            System.out.println ("Veuillez specifier le chemin d'acces de l'image : "); 

            try{
                Scanner cheminAcces = new Scanner(System.in); 
                Constantes.pathToImageFile = cheminAcces.nextLine();
            } catch(Exception e){
                System.out.println (" Erreur : " + e.getMessage()); 
            }

            BarCode2DData codeBarre = new BarCode2DData();
            loadBarCode2D(Constantes.pathToImageFile);
            codeBarre = getBarcodeData();

            // Feature : Copier le message du code barre a deux dimensions dans le presse papier

            StringSelection test = new StringSelection();
            System.out.println("Souhaitez-vous copier ce message dans le presse papier ?"); 
            try {
                Scanner copie = new Scanner (System.in); 
                boolean copier = copie.nextBoolean(); 
            } catch (Exception e) {
                System.out.println (" Erreur : " + e.getMessage()); 
            }

            if (choix == true) {
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(test, null);
                System.out.println("Le message a ete copie dans le presse papier ...");
            }

        }
        // Generer un code 2D
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

