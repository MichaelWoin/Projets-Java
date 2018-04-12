 import java.awt.*;
 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;
 import javax.swing.*;

 import utilitairesMG.graphique.EcouteFenetre;


 public class FenetreServeur extends JFrame implements ActionListener
 {


 FenetreServeur fenetre;
 JPanel panneauFond,panneauHaut,panneauMiddle,panneauBas;
 JButton bouton;
 JLabel libelle;
 JTextArea text;
 Integer controle=1;


 FenetreServeur(String titre)
 {
 super (titre);
 addWindowListener(new EcouteFenetre());

 panneauFond = new JPanel();
 panneauFond.setLayout(new BorderLayout());


 panneauHaut = new JPanel();
 panneauHaut.setBackground(Color.GRAY);
 libelle = new JLabel("SERVEUR D'OBJETS");
 libelle.setPreferredSize(new Dimension(300,30));
 panneauHaut.add(libelle);
 panneauFond.add(panneauHaut,BorderLayout.NORTH);


 text = new JTextArea();
 text.setPreferredSize(new Dimension(300,200));
 panneauFond.add(text);

 panneauBas = new JPanel();
 panneauBas.setPreferredSize(new Dimension(300,30));
 panneauBas.setBackground(Color.GRAY);
 bouton = new JButton("ON/OFF");
 bouton.addActionListener(this);
 panneauBas.add(bouton);
 panneauFond.add(panneauBas,BorderLayout.SOUTH);


 add(panneauFond);

 pack();
 setVisible(true);
 }

 @Override
 public void actionPerformed(ActionEvent e)
 {


 if(controle==1)
 {
 controle = Controleur.demarreServ();
 }
 else
 {
 controle = Controleur.fermServ();
 }
 }

 public void editText(String tt)
 {
 text.append(tt);
 }

 }
