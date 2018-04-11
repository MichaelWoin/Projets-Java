// ==========================================================================
// Classe Controleur                               Projet GestionContactLocal
// ==========================================================================

import java.sql.SQLException;
import java.util.*;
import utilitairesMG.divers.*;
import utilitairesMG.graphique.*;
import utilitairesMG.jdbc.AccesBase;
import utilitairesMG.jdbc.BaseDeDonnees;
import utilitairesMG.jdbc.JeuResultat;

public class Controleur
{

    private static Fenetre maFenetre;

    private static ContactDAO contactDAO;

    public static void main(String args[])
    {

        contactDAO = new ContactDAO();

        javax.swing.SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                LF.setLF();
                maFenetre = new Fenetre("GestionContactLocal");
            }
        }
        );
    }


    public static void demandeContacts()
    {
    Vector<Vector<Object>> listeLignes;
    Vector<Contact> listeLignesContact;
    Vector<Colonne> listeColonnes;
        
    BaseDeDonnees base;
    AccesBase accesBase;
    JeuResultat resultats;
    
    
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            base = new BaseDeDonnees(
                    "jdbc:sqlserver://mars;databasename=gnmi;" +
                    "user=util_bip;password=x");
            base.setFormatDate("dd/MM/yyyy");
            accesBase = new AccesBase(base);

            try
            {
                accesBase.getConnection();

                try
                {
                    resultats = accesBase.executeQuery(
                        "SELECT * FROM CONTACT ");

                    //System.out.println("Liste des colonnes :\n");
                    //System.out.println(resultats.getColonnes());
                    listeLignes = resultats.getLignes();
                    listeColonnes = resultats.getColonnes();
                    //System.out.println(listeColonnes);
                    contactDAO = new ContactDAO ();
                    listeLignesContact = contactDAO.creeListeContacts(listeLignes);
                    maFenetre.afficheContacts(listeLignesContact, listeColonnes);

                }
                catch (SQLException e)
                {
                    System.out.println(e.getMessage());
                }
                finally
                {
                    accesBase.closeConnection();
                }
            }
            catch (SQLException e)
            {
                System.out.println("Connexion impossible " + e.getMessage());
                Controleur.erreurBdd();
            }
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Echec du chargement du pilote " + e.getMessage());
        }



       
    }


    public static void demandeVersements()
    {
        maFenetre.afficheMessage("Gestion des versements non réalisée");
    }


    public static void demandeSecteurs()
    {
        maFenetre.afficheMessage("Gestion des secteurs non réalisée");
    }
    
        public static void erreurBdd()
    {
        maFenetre.afficheMessage("Impossible d'ouvrir la base de donnée");
    }

// --------------------------------------------------------------------------
// Mise a jour de la table CONTACT. 
// --------------------------------------------------------------------------
// Cette methode est appelee lors de la fermeture de la fenetre interne
// Contact. 
// --------------------------------------------------------------------------
 /*   public static void majContacts(Vector<Contact> contactsModifies,
                                   Vector<Contact> contactsInseres,
                                   Vector<Contact> contactsSupprimes)
    {

// --------------------------------------------------------------------------
// Mise a jour de l'affichage de la fenetre principale. Ici, cela consiste
// a revalider le menu d'affichage de la table CONTACT.
// --------------------------------------------------------------------------
        maFenetre.valideItemContact();

// --------------------------------------------------------------------------
// Affichage des vecteurs de contacts recus par la methode.
// --------------------------------------------------------------------------
        System.out.println("Liste des contacts modifies :\n");

        if (contactsModifies.size() == 0)
        {
            System.out.println("Il n'y a pas de contacts modifies.");
        }
        else
        {
            for (int i = 0; i < contactsModifies.size(); i++)
            {
                System.out.println(contactsModifies.elementAt(i));
            }
        }

        System.out.println("\nListe des contacts inseres :\n");

        if (contactsInseres.size() == 0)
        {
            System.out.println("Il n'y a pas de contacts inseres.");
        }
        else
        {
            for (int i = 0; i < contactsInseres.size(); i++)
            {
                System.out.println(contactsInseres.elementAt(i));
            }
        }

        System.out.println("\nListe des contacts supprimes :\n");

        if (contactsSupprimes.size() == 0)
        {
            System.out.println("Il n'y a pas de contacts supprimes.");
        }
        else
        {
            for (int i = 0; i < contactsSupprimes.size(); i++)
            {
                System.out.println(contactsSupprimes.elementAt(i));
            }
        }
    }*/

// --------------------------------------------------------------------------
// Arret de l'application. 
// --------------------------------------------------------------------------
    public static void arreter()
    {
        System.exit(0);
    }
    

}
