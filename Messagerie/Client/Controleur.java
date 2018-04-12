import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilitairesMG.graphique.LF;

public class Controleur
{            
private static PriseServeur prise;
private static AccesServeur accesServeur;
private static FenetreChat maFenetre;

    public static void main(String argv[])
   {


        prise = new PriseServeur("192.168.0.10", 8189);
        prise.setFormatDate("dd/MM/yyyy");

        accesServeur = new AccesServeur(prise);
        

        javax.swing.SwingUtilities.invokeLater
        (
            new Runnable()
            {
                public void run()
                {
                    LF.setLF();
                    maFenetre = new FenetreChat("Fenetre messagerie");
                }
            }
        );
    }
    
    static void envoyerMessage()
    {
    try {
        accesServeur.getConnection();
        String message = accesServeur.envoyerRecevoirMessage(maFenetre.recupText());
        maFenetre.afficheTexte(message);
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


}
