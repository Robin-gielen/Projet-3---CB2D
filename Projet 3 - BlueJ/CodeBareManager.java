import java.util.Scanner;
import barcode2d.*;

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
        System.out.println("\t 3) Verifier un code-barre");

        try {
            Scanner sc = new Scanner(System.in); 
            Constantes.choixModeGeneral = sc.nextInt();

            // Lire un code 2D
            if (Constantes.choixModeGeneral == 1){

                String path; 
                try{

                    System.out.println ("Veuillez specifier le chemin d'acces de l'image : "); 
                    Scanner cheminAcces = new Scanner(System.in); 
                    Constantes.pathToImageFile = cheminAcces.nextLine();

                }
                catch(Exception e){
                    System.out.println (e.getMessage()); 
                }
            }
            
            // Generer un code 2D
            else if (Constantes.choixModeGeneral == 2){

                System.out.println("Veuillez entrer votre texte : ");
                try {
                    Scanner clavier = new Scanner(System.in);
                    Constantes.messageToPrint = clavier.nextLine(); 

                    //afficher(encode(Constantes.messageToPrint)); 

                }
                catch(Exception e){
                    System.out.println(" Erreur : " + e.getMessage()); 
                }
            }
            
            // Verifier un code 2D
            else if (Constantes.choixModeGeneral == 3){

            }
            else {
                System.out.println("Reponse non-valide"); 
            }
        }
        catch (Exception e) {
            System.out.println (e.getMessage()); 
        }
        
        
        if (Constantes.choixModeGeneral == 1)
        {
            BarCode2DData codeBarre = new BarCode2DData();
            loadBarCode2D(Constantes.pathToImageFile);
            codeBarre = getBarcodeData();
            
        }
    }

    }
    

