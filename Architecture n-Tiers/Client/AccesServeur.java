import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import utilitairesMG.jdbc.JeuResultat;



public class AccesServeur 
{
    Socket socket;
    JeuResultat resultatQuery;
    Integer nbrUp;
    ObjectInputStream entree;
    ObjectOutputStream sortie;
    String requete;
    Object reponse;
    Integer typeRequeteNbr;

    private PriseServeur prise;
    
     AccesServeur(PriseServeur prise)
    {
        this.prise=prise;
    }
    

     
     
    public Socket getConnection() throws IOException
    {
        socket = new Socket(prise.nomMachine,prise.numeroPort);
        return socket;
    }
    
    public void closeConnection() throws IOException
    {
        socket.close();
    }
    
    JeuResultat executeQuery (String select) throws IOException, ClassNotFoundException
    {
       sortie = new ObjectOutputStream(socket.getOutputStream());
       entree = new ObjectInputStream(socket.getInputStream());
       sortie.writeObject(select);
       
       typeRequeteNbr = (Integer) entree.readObject();
       resultatQuery = (JeuResultat)entree.readObject();
       
       return resultatQuery ;
    }
    
    Integer executeUpdate(String requete) throws IOException, ClassNotFoundException
    {
       sortie = new ObjectOutputStream(socket.getOutputStream());
       entree = new ObjectInputStream(socket.getInputStream());
       sortie.writeObject(requete);
       
       nbrUp = (Integer) entree.readObject();
       
        return nbrUp;
    }
}
