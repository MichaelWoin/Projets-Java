import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;




public class AccesServeur 
{
    Socket socket;
    Integer nbrUp;
    ObjectInputStream entree;
    ObjectOutputStream sortie;
    String messageEntrant;
    String messageSortant;
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
    /*
    String recevoirMessage () throws IOException, ClassNotFoundException
    {
       sortie = new ObjectOutputStream(socket.getOutputStream());
       entree = new ObjectInputStream(socket.getInputStream());
 
       
    }*/
    
    String envoyerRecevoirMessage(String message) throws IOException, ClassNotFoundException
    {
       sortie = new ObjectOutputStream(socket.getOutputStream());
       entree = new ObjectInputStream(socket.getInputStream());
       sortie.writeObject(message);
       return messageEntrant = (String)entree.readObject();
    }
}
