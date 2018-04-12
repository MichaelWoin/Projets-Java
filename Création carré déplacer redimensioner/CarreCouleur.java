
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class CarreCouleur extends Rectangle
{
    private Color couleur ;
    
    public CarreCouleur (int x, int y, int width, int height, Color couleur)
    {
        super(x, y, width, height);
        this.couleur = couleur;
    } 
    
    public void affiche(Graphics g)
    {
        g.setColor(couleur);
        g.fillRect(x, y, width, height);
    }
 
}
