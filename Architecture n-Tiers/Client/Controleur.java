// ==========================================================================
// Classe Controleur                                Projet GestionContactJdbc
// ==========================================================================

import java.io.IOException;
import java.net.Socket;
import java.util.*;
import utilitairesMG.divers.*;
import utilitairesMG.graphique.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import metierMapping.*;

public class Controleur
{

// --------------------------------------------------------------------------
// PROPRIETES
// --------------------------------------------------------------------------
    private static Fenetre maFenetre;
    private static ContactDAO contactDAO;
    private static SecteurDAO secteurDAO;
    private static PriseServeur prise;
    private static AccesServeur accesServeur;

// --------------------------------------------------------------------------
// Methode main : lancement de l'application
// --------------------------------------------------------------------------
    public static void main(String args[])
    {


        prise = new PriseServeur("localhost", 8189);
        prise.setFormatDate("dd/MM/yyyy");

        accesServeur = new AccesServeur(prise);
        

        contactDAO = new ContactDAO(accesServeur);
        secteurDAO = new SecteurDAO(accesServeur);


        javax.swing.SwingUtilities.invokeLater
        (
            new Runnable()
            {
                public void run()
                {
                    LF.setLF();
                    maFenetre = new Fenetre("GestionContactJdbc");
                }
            }
        );
    }


    public static void demandeContacts()
    {
        Vector<Contact> listeContacts;
        Vector<Colonne> listeColonnes;
        Vector<Secteur> listeSecteurs;
        try
        {
            listeContacts = contactDAO.lireListe();
            listeColonnes = contactDAO.getListeColonnes();
            listeSecteurs = secteurDAO.lireListe();
            
            maFenetre.afficheContacts(listeContacts,
                    listeColonnes,
                    listeSecteurs);
        }
        catch (SQLException e)
        {
            maFenetre.afficheMessage(e.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                accesServeur.closeConnection();
            } catch (IOException ex) {
                Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

// --------------------------------------------------------------------------
// Creation du vecteur des versements et du vecteur des colonnes a afficher.
// --------------------------------------------------------------------------
    public static void demandeVersements()
    {
        maFenetre.afficheMessage("Gestion des versements non réalisée");
    }

// --------------------------------------------------------------------------
// Creation du vecteur des secteurs et du vecteur des colonnes a afficher.
// --------------------------------------------------------------------------
    public static void demandeSecteurs()
    {
        maFenetre.afficheMessage("Gestion des secteurs non réalisée");
    }

// --------------------------------------------------------------------------
// Mise a jour de la table CONTACT.
// --------------------------------------------------------------------------
// Cette methode est appelee lors de la fermeture de la fenetre interne
// Contact.
// --------------------------------------------------------------------------
    public static void majContacts(Vector<Contact> contactsInseres,
                                   Vector<Contact> contactsModifies,
                                   Vector<Contact> contactsSupprimes) 
    {
        try
        {
            Contact contact;
            int i;

            

maFenetre.valideItemContact();


    for (i = 0; i < contactsSupprimes.size(); i++)
    {
        contact = contactsSupprimes.elementAt(i);
        try
        {
            contactDAO.detruire(contact);
        }
        catch (SQLException e)
        {
            maFenetre.afficheMessage(e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
// --------------------------------------------------------------------------
// 2. Sauvegarde du vecteur des Contacts inseres dans la base de donnees.
// --------------------------------------------------------------------------
for (i = 0; i < contactsInseres.size(); i++)
{
    contact = contactsInseres.elementAt(i);
    
    try
    {
        contactDAO.creer(contact);
    }
    catch (SQLException e)
    {
        maFenetre.afficheMessage(e.getMessage());
    }   catch (ClassNotFoundException ex) {
        System.out.println(ex.getMessage());

        }
}

// --------------------------------------------------------------------------
// 3. Sauvegarde du vecteur des Contacts modifies dans la base de donnees.
// --------------------------------------------------------------------------
for (i = 0; i < contactsModifies.size(); i++)
{
    contact = contactsModifies.elementAt(i);
    
    try
    {
        contactDAO.modifier(contact);
    }
    catch (SQLException e)
    {
        maFenetre.afficheMessage(e.getMessage());
    }   
    catch (ClassNotFoundException ex) 
    {
            System.out.println(ex.getMessage());
    }
}



        }
        catch (IOException ex) 
        {
        System.out.println(ex.getMessage());

        }
    }

// --------------------------------------------------------------------------
// Arret de l'application.
// --------------------------------------------------------------------------
    public static void arreter()
    {
        System.exit(0);
    }
}
