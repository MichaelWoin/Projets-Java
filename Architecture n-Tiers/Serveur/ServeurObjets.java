 import java.net.*;
 import java.io.*;
 import utilitairesMG.jdbc.BaseDeDonnees;

 public class ServeurObjets extends Thread
 {
 BaseDeDonnees base;

 public ServeurObjets(BaseDeDonnees base)
 {
 this.base=base;
 }

 public void run()
 {
 ServerSocket serveur;
 boolean actif;
 Socket socketServeur;
 Thread threadClient;

 try
 {
 serveur = new ServerSocket(8189);
 try
 {
 serveur.setSoTimeout(10);
 actif = true;


 while (actif)
 {
 try
 {
 socketServeur = serveur.accept();

 threadClient = new ThreadClient(socketServeur,base);
 threadClient.start();
 }
 catch (SocketTimeoutException e)
 {
 if (interrupted())
 {
 actif = false;
 }
 }
 }
 }
 finally
 {
 serveur.close();
 }
 }
 catch(IOException e)
 {
 System.out.println(e.getMessage());
 }
 }
 }