import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Vector;

public class Panneau extends JPanel implements MouseListener,MouseWheelListener,MouseMotionListener
{
    private Vector <CarreCouleur> carreV = new Vector <CarreCouleur> ();
    private int tailleCourante = 16;
    private Color couleurCourante = Color.black;


    
// --------------------------------------------------------------------------
// Constructeur
// --------------------------------------------------------------------------
    public Panneau()
    {
        setPreferredSize(new Dimension(600,600));
        addMouseListener(this);
        addMouseWheelListener(this);
        addMouseMotionListener(this);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        for(int i =0 ; i< carreV.size() ; i++)
        {
            carreV.get(i).affiche(g);
        }
     
    }
//
// EVENT  
//
    
    /*public int trouveCarre(Point p)
    {
        
    }*/
    public void mouseClicked(MouseEvent e)
    {
        
     }


    public void mousePressed(MouseEvent e)
    {
        if (e.getButton() == MouseEvent.BUTTON1) 
        {
            
        
        CarreCouleur carre = new CarreCouleur(e.getX(), 
                                              e.getY(), 
                                              tailleCourante, 
                                              tailleCourante, 
                                              couleurCourante);
        carreV.addElement(carre);
        }
       
        if (e.getButton() == MouseEvent.BUTTON3) 
        {
            
            for(int i =0 ; i< carreV.size() ; i++)
            {
                if(carreV.get(i).contains(e.getX(),e.getY()))
                {
                    carreV.removeElementAt(i);
                }
            }
        }
        repaint();
    }
    
    public void videPanneau ()
    {
        carreV.clear();
        repaint();
    }
    
    
    public void setCouleurCourante(Color couleurCourante) 
    {
        this.couleurCourante = couleurCourante;
    }


    public void mouseReleased(MouseEvent e)
    {
    }

    public void mouseEntered(MouseEvent e)
    {
        System.out.println("enter");
    }
    

    public void mouseExited(MouseEvent e)
    {
    }
    

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) 
    {

       if( e.getWheelRotation()!=0)
        {
           tailleCourante -= e.getWheelRotation() ;

       }
       
    }

    @Override
    public void mouseDragged(MouseEvent e) 
    {
        System.out.println("draged");
    }

    @Override
    public void mouseMoved(MouseEvent e) 
    {
        /*for(int i =0 ; i< carreV.size() ; i++)
            {
                if(carreV.get(i).contains(e.getX(),e.getY()))
                {
                    ;
                }
        */
    }

}
