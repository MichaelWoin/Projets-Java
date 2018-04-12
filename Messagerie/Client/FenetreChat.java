import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class FenetreChat extends JFrame implements ActionListener
{
    private JPanel panneauFond;

    private JPanel monPanneauHaut;
    private JLabel titre;

    private JPanel monPanneauCentre;
    private JTextArea monTexteRecu;
    private JTextArea monTextEnvoye;
    private JScrollPane defileur;
    private JButton bouton;

    private JPanel monPanneauBas;


// ==========================================================================
// Constructeur
// ==========================================================================
    public FenetreChat(String s)
    {
        super(s);
        
        addWindowListener(new EcouteWindowClosing());
        
        panneauFond = new JPanel();
        panneauFond.setLayout(new BorderLayout());
        
        
        monPanneauHaut = new JPanel();
        monPanneauHaut.setBackground(Color.blue);

        titre = new JLabel("Fenetre de messagerie");
        Font fontParDefaut = titre.getFont();
        titre.setFont(new Font(fontParDefaut.getName(), Font.PLAIN, 32));
        titre.setForeground(Color.yellow);

        monPanneauHaut.add(titre);

// --------------------------------------------------------------------------
// monPanneauCentre : panneau qui contiendra la zone de texte
// --------------------------------------------------------------------------
        monPanneauCentre = new JPanel();
        monPanneauCentre.setLayout(new BorderLayout());

        monTexteRecu = new JTextArea();
        monTexteRecu.setEditable(false);
        monTexteRecu.setHighlighter(null);
        monTexteRecu.setFont(new Font("Courier new", Font.PLAIN, 12));

        defileur = new JScrollPane(monTexteRecu);
        defileur.setPreferredSize(new Dimension(450, 150));
        monPanneauCentre.setLayout(new FlowLayout(FlowLayout.LEFT));
        

        monPanneauCentre.add(defileur);

// --------------------------------------------------------------------------
// monPanneauBas : panneau qui contiendra le bouton marche/arret
// --------------------------------------------------------------------------
        monPanneauBas = new JPanel();

        
        monTextEnvoye = new JTextArea();
        monTextEnvoye.setEditable(true);
        
        monPanneauBas.add(monTextEnvoye);
        monPanneauBas.setLayout(new FlowLayout(FlowLayout.LEFT));
        monTextEnvoye.setPreferredSize(new Dimension(350, 50));
        
        bouton = new JButton("Envoyer");
        bouton.addActionListener(this);
        
        monPanneauBas.add(bouton);
        
        
        panneauFond.add(monPanneauHaut, BorderLayout.NORTH);
        panneauFond.add(monPanneauCentre);
        panneauFond.add(monPanneauBas, BorderLayout.SOUTH);

        getContentPane().add(panneauFond);

        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
       Controleur.envoyerMessage();
    }
    
    public String recupText()
    {
        return monTextEnvoye.getText();
    }
    
    public void afficheTexte(String s)
    {
        monTexteRecu.setText(s);
        monTexteRecu.setCaretPosition(monTexteRecu.getText().length());
    }
    
       private class EcouteWindowClosing extends WindowAdapter
    {
        public void windowClosing(WindowEvent e)
        {
            System.exit(0);
        }
    }
}