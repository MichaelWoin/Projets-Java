import java.util.*;
import utilitairesMG.divers.*;
import utilitairesMG.jdbc.*;

public class ContactDAO
{

    BaseDeDonnees base;
    AccesBase accesBase;
    JeuResultat resultats;
    Vector<Contact> listeContacts = new Vector<Contact>();
    Vector<Colonne> listeColonnes = new Vector<Colonne>();

 

    public Vector<Contact> creeListeContacts(Vector<Vector<Object>> listeLignes)
    {

                    for (int i = 0; i < listeLignes.size(); i++)
                    {
                        Vector<Object> ligne = listeLignes.elementAt(i);
                        Contact c = new Contact();

                        c.setNumero((Integer) ligne.elementAt(0));
                        c.setNom((String) ligne.elementAt(1));
                        c.setAdresse((String) ligne.elementAt(2));
                        c.setCodePostal((String) ligne.elementAt(3));
                        c.setVille((String) ligne.elementAt(4));
                        c.setCodeSecteur((Integer) ligne.elementAt(5));

                        listeContacts.addElement(c);
                    }
                    //System.out.println(listeLignes);

        return listeContacts;
    }

    /*public Vector<Colonne> creeListeColonnes()
    {

        return resultats.getColonnes();
    }*/
}
