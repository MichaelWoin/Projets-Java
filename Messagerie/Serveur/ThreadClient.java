import java.net.*;
import java.io.*;
import java.sql.SQLException;
import utilitairesMG.jdbc.*;



public class ThreadClient extends Thread
{

    private Socket socketServeur;
    private String message;

    
    public ThreadClient(Socket socketServeur)
    {
        this.socketServeur = socketServeur;

    }


    public void run()
    {

 
 

        ObjectInputStream entree;
        ObjectOutputStream sortie;

        try
        {



            try
            {
                sortie = new ObjectOutputStream(socketServeur.getOutputStream());
                entree = new ObjectInputStream(socketServeur.getInputStream());
                        
                message = (String) entree.readObject();

                Controleur.traiterTexte("Client "
                    + socketServeur.getInetAddress()
                    + "  : "
                    + message);
                    sortie.writeObject(""
                    + Controleur.recupText());
                                              
                        
            }
            catch (IOException e)
            {
                System.out.println(e.getMessage());
            }
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Echec du chargement du pilote " + e.getMessage());
        }
    }
    

    
}