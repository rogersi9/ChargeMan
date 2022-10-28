package obstacles;

import java.awt.Color;



import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import geometrie.Segments;

import javax.swing.JPanel;

import Affichage.ZoneEditeur;
import geometrie.Vecteur;

import java.awt.Graphics;


/**
 *Respesentation sommaire d'un obstacle de forme barre horizontale qui est
 * Composée de 4 segments
 * 
 * @author Nouhaila Aater
 * @author Roger Sioufi
 */

public class BarreVerticale extends Obstacles implements Dessinable {

	private static final long serialVersionUID = 1L;
	private Vecteur vecteurPosition;
	private final  int  LARGEUR = 5 ; 
	private final int LONGUEUR = 25;
	private Rectangle2D.Double barreVerti;
	
	 ArrayList <Segments> tot_segment_verticale = new ArrayList<Segments>();
	 

	 
	/**
	 * Constructeur qui construit les points et les segments de la barre Vertical
	 * @param nbSegments le nombre de segments utiliser par la barre Verticale
	 * @param p1 le point de depart de la barre Verticale
	 */
	//Nouhaila Aater
	public BarreVerticale(Vecteur vecPosition) {
		super(vecPosition);
		this.vecteurPosition = vecPosition;
		
		
		
		// par Roger
	
		double x = vecPosition.getX()/7.92 ;
		double y = vecPosition.getY()/7.92;
		
		double LARGEUR_SCALED=LARGEUR/7.92;
		double LONGEUR_SCALED=LONGUEUR/7.92;
		
		Point2D.Double p1= new 	Point2D.Double(x,y);
		Point2D.Double p2= new 	Point2D.Double(x+LARGEUR_SCALED,y);
		Point2D.Double p3= new 	Point2D.Double(x+LARGEUR_SCALED,y+LONGEUR_SCALED);
		Point2D.Double p4= new 	Point2D.Double(x,y+LONGEUR_SCALED);
		
		Segments s1 =  new Segments(p1,p2);
		Segments s2 =  new Segments(p2,p3);
		Segments s3 =  new Segments(p3,p4);
		Segments s4=  new Segments(p4,p1);
		
		tot_segment_verticale.add(s1);
		tot_segment_verticale.add(s2);
		tot_segment_verticale.add(s3);
		tot_segment_verticale.add(s4);
	}

	
	/**
	 * Dessiner la forme d'une barre Verticale avec les segments
	 * @param g2d contexte graphique
	 * @param matMC matrice de transformation monde-vers-composant
	 */
	//Nouhaila Aater
	@Override
	public void dessiner (Graphics2D g2d,  AffineTransform mat) {
		g2d.setColor(Color.BLACK);
		barreVerti = new Rectangle2D.Double(vecteurPosition.getX(), vecteurPosition.getY(), LARGEUR, LONGUEUR);
		g2d.fill(mat.createTransformedShape(barreVerti));
	}

	
	
	//Nouhaila
	public Shape getForme() {
		return barreVerti;
	}
	
	//Nouhaila
	public Vecteur getVecteurPosition() {
		return (this.vecteurPosition);
	}
	
	//Nouhaila
	public void setPosition (Vecteur vecInput) {
		this.vecteurPosition = vecInput;
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
	 * @return liste des segments pour la barre verticale
	 */
	// par Roger
	public ArrayList<Segments> getSegment() {
		
		return this.tot_segment_verticale;
		
		
	}
	public boolean isTriangle() {
		return false;
		
		
	}

	@Override
	public boolean estCharge() {
		return false;
	}
	
	
	
}
