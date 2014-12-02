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

            BarCode2DData codeBarre = new BarCode2DData();
            loadBarCode2D(Constantes.pathToImageFile);
            codeBarre = getBarcodeData();

            // Feature #1 : Copier le message du code barre a deux dimensions dans le presse-papier

            System.out.println("Souhaitez-vous copier ce message dans le presse papier ?"); 

            try {
                Scanner copie = new Scanner (System.in); 
                boolean copier = copie.nextBoolean(); 
            } catch (Exception e) {
                System.out.println (" Erreur : " + e.getMessage()); 
            }

            if (choix == true) {
                StringSelection test = new StringSelection(/*message*/);
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(test, null);
                System.out.println("Le message a ete copie dans le presse papier");
            }

            // Feature #2 : Traduire le message encoder dans le code barre 2D

            System.out.println("Voulez-vous traduire ce message via Google translate ? (true/false)");
            boolean traduire = false; 

            try {
                Scanner traduction = new Scanner(System.in); 
                traduire = traduction.nextBoolean(); 
            }catch (Exception e) {
                System.out.println("Erreur : " + e.getMessage()); 
            }

            if (traduire == true) {
                traduction(/*message*/); 
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

    public static void traduction (String msg) throws IOException{

        System.out.println();
        System.out.println("Veuillez entrer la langue de convertion : ");
        System.out.println(" \t 1) Anglais - Francais"); 
        System.out.println(" \t 2) Neerlandais - Francais"); 
        System.out.println(" \t 3) Detection automatique de la langue"); 

        int langue = 0; 

        try {
            Scanner SelectionLangue = new Scanner(System.in); 
            langue = SelectionLangue.nextInt(); 
        }catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage()); 
        }

        System.out.println(); 
        String url = "";

        if (langue == 1) {
            url = "https://translate.google.fr/#en/fr/";
        }
        else if (langue == 2){
            url = "https://translate.google.fr/#nl/fr/";
        }
        else {
            url = "https://translate.google.fr/#auto/fr/";
        }

        StringBuffer adresse = new StringBuffer(url); 

        // La methode replaceAll("<char>/<String>","<char>/<String>") ne prend pas en charge les caracteres speciaux !
		// Une adresse URL ne peut contenir des caracteres speciaux
		
        for (int i = 0; i < msg.length();i++) {
            // Les references des caracteres speciaux chez Google :    
            
            if (msg.charAt(i) == ' ') {
                adresse.append("%20"); 
            }
            else if (msg.charAt(i) == '*') {
                adresse.append("%2A"); 
            }
            else if (msg.charAt(i) == '+') {
                adresse.append("%2B"); 
            }
            else if (msg.charAt(i) == ',') {
                adresse.append("%2C");
            }
            else if (msg.charAt(i) == ':') {
                adresse.append("%3A");
            }  
            else if (msg.charAt(i) == ';') {
                adresse.append("%3B");
            }   
            else if (msg.charAt(i) == '<') {
                adresse.append("%3C");
            }   
            else if (msg.charAt(i) == '=') {
                adresse.append("%3D");
            }   
            else if (msg.charAt(i) == '>') {
                adresse.append("%3E");
            }   
            else if (msg.charAt(i) == '?') {
                adresse.append("%3F");
            }
            else if (msg.charAt(i) == '#') {
                adresse.append("%3G");
            }
            else {
                adresse.append(msg.charAt(i)); 
            }
        }

        System.out.println("Votre message : " + msg);
        System.out.println(); 
        System.out.println("Adresse " + adresse.toString());
        System.out.println(); 

        URI lien = URI.create(adresse.toString()); 
        Desktop.getDesktop().browse(lien);

    }
}

