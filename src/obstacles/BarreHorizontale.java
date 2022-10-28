 package obstacles;

import java.awt.Color;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import geometrie.Segments;

import javax.swing.JPanel;
import javax.swing.text.Segment;

import Affichage.ZoneEditeur;
import geometrie.Vecteur;
/**
 * Représentation sommaire d'un obstacle de forme barre horizontale qui est
 * composé de 4 segements 
 * 
 * @author Nouhaila Aater
 * @author Roger Sioufi
 */

public class BarreHorizontale  extends Obstacles implements Dessinable   {
	
	private static final long serialVersionUID = 1L;
	private Vecteur vecteurPosition;
	private final  int  LARGEUR = 25 ; 
    private final int LONGUEUR = 5 ;
	private Rectangle2D.Double barreHori;

	 ArrayList<Segments> tot_segment_horizontale = new ArrayList<Segments>();
	 
	 
	 
	 
	 
   /**
    * Constructeur qui construit les points et les segments de la barre Horizontale
    * @param vecPosition le vecteur position de la barre horizontale
    * @param couleurBarre la couleur de la barre horizontale
	*/
    //Nouhaila Aater
	public BarreHorizontale(Vecteur vecPosition) {
		
		super(vecPosition);
		this.vecteurPosition = vecPosition;
		
		// Roger
	    double x = vecPosition.getX();
		double y = vecPosition.getY();
		
		double LARGEUR_SCALED=LARGEUR;
		double LONGEUR_SCALED=LONGUEUR;
		
		Point2D.Double p1= new 	Point2D.Double(x,y);
		Point2D.Double p2= new 	Point2D.Double(x+LARGEUR_SCALED,y);
		Point2D.Double p3= new 	Point2D.Double(x+LARGEUR_SCALED,y+LONGEUR_SCALED);
		Point2D.Double p4= new 	Point2D.Double(x,y+LONGEUR_SCALED);
		
		Segments s1 =  new Segments(p1,p2);
		Segments s2 =  new Segments(p2,p3);
		Segments s3 =  new Segments(p3,p4);
		Segments s4=  new Segments(p4,p1);
		
		tot_segment_horizontale.add(s1);
		tot_segment_horizontale.add(s2);
		tot_segment_horizontale.add(s3);
		tot_segment_horizontale.add(s4);
	}

  /**
  * Dessiner la forme d'une barre horizontale a l'aide des segements
  * @param g2d contexte graphique
  * @param mat matrice de transformation monde-vers-composant
  */
    //Nouhaila Aater
	public void dessiner (Graphics2D g2d , AffineTransform mat) {
		g2d.setColor(Color.BLACK);
		barreHori = new Rectangle2D.Double(vecteurPosition.getX(), vecteurPosition.getY(), LARGEUR, LONGUEUR);
        g2d.fill(mat.createTransformedShape(barreHori));
     }
	
	
	
	//GETTERS ET SETTERS 
	
	//Nouhaila
	public Shape getForme() {
		return barreHori;
	}
	
	//Nouhaila
	public Vecteur getVecteurPosition() {
		return (this.vecteurPosition);
	}
	
	//Nouhaila
	public void setPosition (Vecteur vecteurPosition) {
		this.vecteurPosition = vecteurPosition;
	}
	
	//Nouhaila
	public double getLargeur() {
		return (this.LARGEUR);
	}
	
	//Nouhaila
	public double getLongueur() {
		return (this.LONGUEUR);
	}
	
	
	/** methode qui retourne la liste des segments du rectangle
	 * 
	 * @return liste des segments pour la barre horizontale
	 */
	// par Roger
	public ArrayList<Segments> getSegment() {
		return this.tot_segment_horizontale;
		
		
	}
	
	public boolean isTriangle() {
		return false;
		
		
	}

	@Override
	public boolean estCharge() {
		return false;
	}
	
	
}
