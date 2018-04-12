import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import utilitairesMG.graphique.*;

public class Fenetre extends JFrame implements ActionListener
{
        private Panneau panneauFond;
        private JPanel panneauFond1;
        private JFrame help;
        private JMenuBar menu;
        private JMenu menu1,menu2;
        private JMenuItem menuEffacer,menuAide;
        private JCheckBox menuRouge,menuVert,menuBleu;
        private Vector <Point> co = new Vector <Point> ();
        private JButton bouton;
 
    public Fenetre(String s)
    {
        super(s);
        addWindowListener(new EcouteFenetre());

        panneauFond = new Panneau();
        panneauFond.setBackground(new Color(150, 150, 150));
        //panneauFond.setForeground(Color.black);
        //panneauFond.setPreferredSize(new Dimension(400, 200));
        menu = new JMenuBar();
        
        
        menu1 = new JMenu("Jeu");
        menuEffacer = new JMenuItem ("Effacer");
        menuEffacer.addActionListener(this);
        menuAide = new JMenuItem("Aide");
        menuAide.addActionListener(this);
        menu1.add(menuEffacer);
        menu1.add(menuAide);
        
        
        menu2 = new JMenu("Couleur");
        menuRouge = new JCheckBox("Rouge");
        menuVert = new JCheckBox("Vert");
        menuBleu = new JCheckBox("Bleu");
        
        menuRouge.addActionListener(this);
        menuVert.addActionListener(this);
        menuBleu.addActionListener(this);
        
        menu2.add(menuRouge);
        menu2.add(menuVert);
        menu2.add(menuBleu);
        
        menu.add(menu1);
        menu.add(menu2);
        
               
        add(panneauFond);
        setJMenuBar(menu);
        
        
        
        pack();
        setVisible(true);
    }

    
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        int r, v, b;
        
        if(e.getSource() == menuAide)
        {
            JOptionPane.showMessageDialog(this, "Clic gauche : ajout et déplacement\nClic droit : supression\nRoulette : dimension carré", "Fonctionement du jeu", JOptionPane.ERROR_MESSAGE, new ImageIcon("C:\\Users\\afpa1492\\Downloads\\terre.gif"));
   
        }
        else
        {
            if (e.getSource() == menuEffacer)
            {
                panneauFond.videPanneau();
            }
            else
            {
                r = 0;
                if (menuRouge.isSelected()) r = 255;
                v = 0;
                if (menuVert.isSelected()) v = 255;
                b = 0;
                if (menuBleu.isSelected()) b = 255;
                
                panneauFond.setCouleurCourante(new Color(r, v, b));
            }
        }
        
         
            
    }
}
