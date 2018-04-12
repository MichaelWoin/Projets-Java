import java.io.*;
import utilitairesMG.jdbc.*;
import utilitairesMG.graphique.LF;

public class Controleur
{
static ServeurObjets serveur;
static BaseDeDonnees base;
static String message;
static FenetreServeur fenetre;

public static void main(String[] argv) throws IOException
{
try
{
Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
base = new BaseDeDonnees("jdbc:sqlserver://mars;user=UTIL_BIP;"
+ "password=x;databasename=gnmi");
base.setFormatDate("dd/MM/yyyy");


 }
 catch (ClassNotFoundException ex)
 {
 System.out.println(ex.getMessage());
 }

 javax.swing.SwingUtilities.invokeLater
 (
 new Runnable()
 {
 public void run()
 {
 LF.setLF();
 fenetre = new FenetreServeur("Serveur d'objets");
 }
 }
 );
 }

 public static Integer demarreServ()
 {
 serveur = new ServeurObjets(base);
 fenetre.editText("Le serveur a ete démarré...\n");
 serveur.start();
 return 0;
 }

 public static Integer fermServ()
 {
 serveur.interrupt();
 fenetre.editText("Le serveur a ete arrete...\n");
 return 1;
 }
 public static void afficheMess(String message)
 {
 fenetre.editText(""+message+"\n");
 }
}