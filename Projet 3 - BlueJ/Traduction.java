import java.util.Scanner;
import java.io.*;
import java.awt.*;

import java.net.*;

/**
 * Cette methode permet de convertir un message de type String en une adresse URL 
 * Ce lien redirige l'utilisateur vers une page internet du site GoogleTranslate
 *
 * @author (Julien Banken)
 * @version (V 1.0)
 */
public class Traduction
{
    public static void traduction (String msg) throws IOException{

        System.out.println("Voulez-vous traduire ce message via Google translate ? (true/false)");  

        boolean traduire = false; 

        try {
            Scanner traduction = new Scanner(System.in); 
            traduire = traduction.nextBoolean(); 
        }catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage()); 
        }

        if (traduire == true) {
            System.out.println();
            System.out.println("Veuillez entrer la langue de conversion : ");
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

            // La methode replaceAll ne prend pas en charge les caracteres speciaux comme : ? , 

            for (int i = 0; i < msg.length();i++) {
                // Les references des caracteres de Google :    
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
                else if (msg.charAt(i) == '-') {
                    adresse.append("%2D");
                }
                else if (msg.charAt(i) == '/') {
                    adresse.append("%2F");
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

            System.out.println("Adresse : " + adresse.toString());
            String web = adresse.toString().trim();
            
            
            System.out.println("Vous avez ete redirige sur une page internet");
   
            try {
                URI lien = URI.create(web); 
                Desktop.getDesktop().browse(lien);
            }
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage()); 
            }
            
            System.out.println(); 
        }
    }
}