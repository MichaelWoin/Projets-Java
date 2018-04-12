import javax.swing.*;       // Pour JFrame, JPanel
import java.awt.*;          // Pour le BorderLayout
import java.awt.event.*;    // Pour les evenements (WindowEvent)

public class Fenetre extends JFrame implements ActionListener
{
    private JPanel panneauFond;

    private JPanel monPanneauHaut;
    private JLabel titre;

    private JPanel monPanneauCentre;
    private JTextArea text;
    private JTextArea monTextEnvoye;
    private JScrollPane defileur;

    private JPanel monPanneauBas;
    private JButton boutonOnOff;
    private JButton boutonEnvoyer;
    private ImageIcon allume = new ImageIcon("green-on.gif");
    private ImageIcon eteint = new ImageIcon("green-off.gif");

// ==========================================================================
// Constructeur
// ==========================================================================
    public Fenetre(String s)
    {
        super(s);
        addWindowListener(new EcouteWindowClosing());
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

// --------------------------------------------------------------------------
// panneauFond
// --------------------------------------------------------------------------
        panneauFond = new JPanel();
        panneauFond.setLayout(new BorderLayout());

// --------------------------------------------------------------------------
// monPanneauHaut : panneau du haut de l'ecran qui contiendra le titre
// --------------------------------------------------------------------------
        monPanneauHaut = new JPanel();
        monPanneauHaut.setBackground(Color.blue);

        titre = new JLabel("SERVEUR DE MESSAGERIE");
        Font fontParDefaut = titre.getFont();
        titre.setFont(new Font(fontParDefaut.getName(), Font.PLAIN, 32));
        titre.setForeground(Color.yellow);

        monPanneauHaut.add(titre);
        
        
        
        monPanneauCentre = new JPanel();
        monPanneauCentre.setLayout(new BorderLayout());

        text = new JTextArea();
        text.setEditable(false);
        //text.setHighlighter(null);
        text.setFont(new Font("Courier new", Font.PLAIN, 12));

        defileur = new JScrollPane(text);
        defileur.setPreferredSize(new Dimension(450, 150));
        monPanneauCentre.setLayout(new FlowLayout(FlowLayout.LEFT));
        monPanneauCentre.add(defileur);


        monPanneauBas = new JPanel();
        monPanneauBas.setLayout(new FlowLayout(FlowLayout.LEFT));
        boutonOnOff = new JButton("ON / OFF", eteint);
        boutonOnOff.addActionListener(this);
        
        monPanneauBas.add(boutonOnOff); 
        

        
        
        panneauFond.add(monPanneauHaut, BorderLayout.NORTH);
        panneauFond.add(monPanneauCentre);
        panneauFond.add(monPanneauBas, BorderLayout.SOUTH);

        getContentPane().add(panneauFond);

        pack();
        setVisible(true);
    }

// ==========================================================================
// Affichage dans la JTextArea
// ==========================================================================
    public void afficheTexte(String s)
    {
        text.append(s + "\n");
        text.setCaretPosition(text.getText().length());
    }
    
    public String recupText()
    {
        return text.getText();
    }
    

    public void allume()
    {
        boutonOnOff.setIcon(allume);
        afficheTexte("Le serveur a démarré.");
     }

    public void eteint()
    {
        boutonOnOff.setIcon(eteint);
        afficheTexte("Le serveur est arrêté.");
    }
    
// ==========================================================================
// Methode d'ecoute du JButton
// ==========================================================================
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == boutonOnOff)
        {
            if (boutonOnOff.getIcon() == allume)
            {
            Controleur.arreterServeur();
            }
            else
            {
            Controleur.demarrerServeur();
            }
        }
        else
        {
            //Controleur.traiterTexte();
        }

    }

// --------------------------------------------------------------------------
// Ecouteur de l'evenement fermeture de la fenetre
// --------------------------------------------------------------------------
    private class EcouteWindowClosing extends WindowAdapter
    {
        public void windowClosing(WindowEvent e)
        {
            if (boutonOnOff.getIcon() == allume)
            {
                afficheTexte(
                    "Veuillez arrêter le serveur avant de quitter...");
            }
            else
            {
                Controleur.arreter();
            }
        }
    }
}
