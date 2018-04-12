import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilitairesMG.jdbc.*;
import utilitairesMG.graphique.*;

public class Controleur
{

    private static Fenetre maFenetre;
    private static ServeurObjets serveur;


    public static void main(String args[])
    {


        javax.swing.SwingUtilities.invokeLater
                    (
                            new Runnable()
                            {
                                public void run()
                                {
                                    LF.setLF();
                                    maFenetre = new Fenetre("Serveur d'objets");
                                }
                            }
                    ); 
    }

// --------------------------------------------------------------------------
// Demarrage du serveur
// --------------------------------------------------------------------------
    public static void demarrerServeur()
    {
        serveur = new ServeurObjets();
        serveur.start();
    }
    
    public static void serveurDemarre()
    {
        maFenetre.allume();
    }

// --------------------------------------------------------------------------
// Arret du serveur
// --------------------------------------------------------------------------
    public static void arreterServeur()
    {
        serveur.interrupt();
    }

    public static void serveurArrete()
    {
        maFenetre.eteint();
    }

// --------------------------------------------------------------------------
// Envoi de messages a l'ecran
// --------------------------------------------------------------------------
    public static void traiterTexte(String s)
    {
        maFenetre.afficheTexte(s);
    }
    
    public static String recupText()
    {
    return maFenetre.recupText();
    }
            

// --------------------------------------------------------------------------
// Arret de l'application
// --------------------------------------------------------------------------
    public static void arreter()
    {
        System.exit(0);
    }
    /*
    static void envoyerMessage()
    {
    try 
    {
        accesServeur.getConnection();
        accesServeur.envoyerMessage(maFenetre.recupText());
        accesServeur.closeConnection();
    }
    catch (IOException ex) 
    {
        Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
    } 
    catch (ClassNotFoundException ex) 
    {
        Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
    static void afficheMessage()
    {
    try 
    {
        accesServeur.getConnection();
        maFenetre.setText(accesServeur.recevoirMessage());
        accesServeur.closeConnection();
    } 
    catch (IOException ex) 
    {
        Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
    } 
    catch (ClassNotFoundException ex) 
    {
        Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
    }
    }*/
}