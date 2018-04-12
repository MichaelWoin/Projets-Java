 import java.net.*;
 import java.io.*;
 import java.sql.SQLException;
 import utilitairesMG.jdbc.*;



 public class ThreadClient extends Thread
{
 private BaseDeDonnees base;
 private Socket socketServeur;
 private String requete;


 public ThreadClient(Socket socketServeur,BaseDeDonnees base)
 {
 this.socketServeur = socketServeur;
 this.base=base;
 }


 public void run()
 {


 AccesBase accesBase;
 JeuResultat resultats;
 ObjectInputStream entree;
 ObjectOutputStream sortie;

 Object number;
 Object message;
 Object ligneMod;

 try
 {
 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

 accesBase = new AccesBase(base);


 try
 {
 accesBase.getConnection();


 try
 {
 sortie = new ObjectOutputStream(socketServeur.getOutputStream());
 entree = new ObjectInputStream(socketServeur.getInputStream());
 requete = (String) entree.readObject();


 if(requete.regionMatches(0, "SELECT", 0, 6))
 {
 resultats = accesBase.executeQuery(requete);
 number=1;
 message = resultats;
 System.out.println("Le protocole est :"+number+" et le resultats est :"+message);
 sortie.writeObject(number);
 sortie.writeObject(resultats);
 }
 else
 {
 if(requete.regionMatches(0, "UPDATE", 0, 6)||requete.regionMatches(0, "INSERT", 0, 6)||requete.regionMatches(0, "DELETE", 0, 6))
 {
 ligneMod = accesBase.executeUpdate(requete);
 number=2;
 System.out.println("Le protocole est :"+number+" et le nom de lignes modifiés est :"+ligneMod);
 sortie.writeObject(number);
 sortie.writeObject(ligneMod);
 }
 else
 {
 number=0;
 message="Erreur la requete a fail!";
 System.out.println("Le protocole est :"+number+" et le resultats est :"+message);
 sortie.writeObject(number);

 sortie.writeObject(message);
 }
}

 Controleur.afficheMess(socketServeur.getLocalAddress()+" "+requete);


 }
 catch (SQLException e)
 {
 System.out.println(e.getMessage());
 }
 catch (IOException ex)
 {
 System.out.println(ex.getMessage());
 }
 finally
 {
 accesBase.closeConnection();
 }
 }
 catch (SQLException e)
 {
 System.out.println("Connexion impossible " + e.getMessage());
 }
 }
 catch (ClassNotFoundException e)
 {
 System.out.println("Echec du chargement du pilote " + e.getMessage());
 }
 }



 }