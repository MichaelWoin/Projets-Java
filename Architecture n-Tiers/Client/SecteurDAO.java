
import java.io.IOException;
import java.sql.SQLException;
import java.util.Vector;
import metierMapping.Contact;
import metierMapping.Secteur;
import utilitairesMG.divers.Colonne;
import utilitairesMG.divers.Conversion;
import utilitairesMG.jdbc.JeuResultat;

public class SecteurDAO
{

// ==========================================================================
// PROPRIETES
// ==========================================================================
// --------------------------------------------------------------------------
// Base de donnees liee a la table SECTEUR
// --------------------------------------------------------------------------
    private AccesServeur accesServeur;

// --------------------------------------------------------------------------
// Jeu de resultats lu par l'un des "executeQuery"
// Il contient toutes les donnees des lignes lues dans la table SECTEUR et
// les donnees relatives aux colonnes.
// --------------------------------------------------------------------------
    private JeuResultat jeuResultat;

// ==========================================================================
// METHODES
// ==========================================================================
// --------------------------------------------------------------------------
// Constructeur
// --------------------------------------------------------------------------
    public SecteurDAO(AccesServeur accesServeur)
    {
        this.accesServeur = accesServeur;
    }

// --------------------------------------------------------------------------
// Lecture d'un objet Secteur (dont la clef est renseignee)
// --------------------------------------------------------------------------
    public void lire(Secteur secteur) throws SQLException, IOException, ClassNotFoundException
    {
        int rowCount;

        String select;
        Vector<Object> ligne;

        select = "SELECT * FROM SECTEUR WHERE CODE = " + secteur.getCode();

        accesServeur.getConnection();
        jeuResultat = accesServeur.executeQuery(select);
        accesServeur.closeConnection();

        rowCount = (jeuResultat.getLignes()).size();

// --------------------------------------------------------------------------
// Si le executeQuery retourne 0 ligne, il n'y a pas SQLException. C'est la
// raison de la creation d'une SQLException particuliere.
// --------------------------------------------------------------------------
        if (rowCount == 1)
        {
            ligne = (jeuResultat.getLignes()).elementAt(0);

            secteur.setLibelle((String) ligne.elementAt(1));
        }
        else
        {
            if (rowCount == 0)
            {
                throw new SQLException(
                    "Secteur " + secteur.getCode() + " inconnu");
            }
            else
            {
                throw new SQLException(
                    "Clef " + secteur.getCode() + " en double !");
            }
        }
    }

// --------------------------------------------------------------------------
// Creation (insert) d'un objet Secteur
// --------------------------------------------------------------------------
    public int creer(Secteur secteur) throws SQLException, IOException, ClassNotFoundException
    {
        int rowCount;
        String insert;

        Integer code = secteur.getCode();
        String libelle = secteur.getLibelle();

        insert = "INSERT INTO SECTEUR VALUES("
            + code + ", "
            + Conversion.chaineSQL(libelle) + ")";

        accesServeur.getConnection();
        rowCount = accesServeur.executeUpdate(insert);
        accesServeur.closeConnection();

        return rowCount;
    }

// --------------------------------------------------------------------------
// Modification (update) d'un objet Secteur
// --------------------------------------------------------------------------
    public int modifier(Secteur secteur) throws SQLException, IOException, ClassNotFoundException
    {
        int rowCount;
        String update;

        Integer code = secteur.getCode();
        String libelle = secteur.getLibelle();

        update = "UPDATE SECTEUR SET "
            + "LIBELLE = " + Conversion.chaineSQL(libelle) + " "
            + "WHERE CODE = " + code;

        accesServeur.getConnection();
        rowCount = accesServeur.executeUpdate(update);
        accesServeur.closeConnection();

        return rowCount;
    }

// --------------------------------------------------------------------------
// Destruction (delete) d'un objet Secteur
// --------------------------------------------------------------------------
    public int detruire(Secteur secteur) throws SQLException, IOException, ClassNotFoundException
    {
        int rowCount;
        String delete;

        Integer code = secteur.getCode();

        delete = "DELETE FROM SECTEUR WHERE CODE = " + code;

        accesServeur.getConnection();
        rowCount = accesServeur.executeUpdate(delete);
        accesServeur.closeConnection();

        return rowCount;
    }

// --------------------------------------------------------------------------
// Lecture d'un Secteur, pour un Contact donne
// --------------------------------------------------------------------------
    public Secteur lireSecteur(Contact contact) throws SQLException, IOException, ClassNotFoundException
    {
        Secteur secteur = null;

        if (contact.getCodeSecteur() != null)
        {
            secteur = new Secteur();
            secteur.setCode(contact.getCodeSecteur());
            lire(secteur);
        }
        return secteur;
    }

// --------------------------------------------------------------------------
// Liste des secteurs
// --------------------------------------------------------------------------
    public Vector<Secteur> lireListe() throws SQLException, IOException, ClassNotFoundException
    {
        Vector<Secteur> listeSecteurs;
        Secteur secteur;

        String select = "SELECT * FROM SECTEUR";

        int nombreSecteurs;
        Vector ligne;
        int i;

        accesServeur.getConnection();
        jeuResultat = accesServeur.executeQuery(select);
        accesServeur.closeConnection();

        listeSecteurs = new Vector<Secteur>();
        nombreSecteurs = (jeuResultat.getLignes()).size();

        for (i = 0; i < nombreSecteurs; i++)
        {
            ligne = (jeuResultat.getLignes()).elementAt(i);

            secteur = new Secteur();
            secteur.setCode((Integer) ligne.elementAt(0));
            secteur.setLibelle((String) ligne.elementAt(1));

            listeSecteurs.addElement(secteur);
        }

        return listeSecteurs;
    }

// --------------------------------------------------------------------------
// Liste des colonnes de la table SECTEUR
// --------------------------------------------------------------------------
    public Vector<Colonne> getListeColonnes()
    {
        return jeuResultat.getColonnes();
    }
}
