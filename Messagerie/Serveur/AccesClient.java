import java.net.*;
import java.io.*;

public class AccesClient extends Thread
{
    Socket socket;
    Integer nbrUp;
    ObjectInputStream entree;
    ObjectOutputStream sortie;
    String messageEntrant;
    String messageSortant;
    Object reponse;
    Integer typeRequeteNbr;

    private PriseClient prise;
    
     AccesClient(PriseClient prise)
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
    
    String recevoirMessage () throws IOException, ClassNotFoundException
    {
       sortie = new ObjectOutputStream(socket.getOutputStream());
       entree = new ObjectInputStream(socket.getInputStream());
       
       messageSortant = (String)entree.readObject();
       
       return messageSortant ;
    }
    
    void envoyerMessage(String message) throws IOException, ClassNotFoundException
    {
       sortie = new ObjectOutputStream(socket.getOutputStream());
       entree = new ObjectInputStream(socket.getInputStream());
       sortie.writeObject(message);
       
    }
}