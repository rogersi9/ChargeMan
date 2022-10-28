package obstacles;

import java.awt.BasicStroke;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import Affichage.ZoneEditeur;
import geometrie.Segments;
import geometrie.Vecteur;

import java.awt.Graphics;


/**
 *Respesentation sommaire d'un obstacle de forme Triangle qui est
 * Composée de 3 segments
 * @author Junior  Peumi
 * @author Roger Sioufi
 * 
 */

public class Triangle extends Obstacles  implements Dessinable {

	private static final int nbSegments = 3;
	private Segments  tab_segments[] = new Segments[3];
	private Vecteur vecteurPosition;
    private Polygon triangle ;
    ArrayList <Segments> tot_segment_triangle = new ArrayList<Segments>();
    
    
    //Junior Peumi
	/**
	 * Constructeur qui construit les points et les segments d'un triangle
	 * @param vecPosition le vecteur position du triangle
	 */
	public Triangle(Vecteur vecPosition ) {

		super(vecPosition);
		this.vecteurPosition = vecPosition;
		
		// par Roger
		double x = vecteurPosition.getX()/8.6;
		double y = vecteurPosition.getY()/8.6;
	
		//double x = vecPosition.getX() / editeur.getMatMC().getScaleX();
		//double y = vecPosition.getY()/editeur.getMatMC().getScaleY();
		
		
		Point2D.Double p1= new 	Point2D.Double(x/8.6,y /8.6) ;
		Point2D.Double p2= new 	Point2D.Double((x+6)/ 8.6 ,  (y-8)/8.6);
		Point2D.Double p3= new 	Point2D.Double((x+12) /8.6 ,y/ 1);
		//Point2D.Double p4= new 	Point2D.Double((x+12)/ 8.6 ,y /8.6);
		
		Segments s1 =  new Segments(p1,p2);
		Segments s2 =  new Segments(p2,p3);
		Segments s3 =  new Segments(p3,p1);
		//Segments s4 =  new Segments(p3,p1);
		
		tot_segment_triangle.add(s1);
		tot_segment_triangle.add(s2);
		tot_segment_triangle.add(s3);
		//tot_segment_triangle.add(s4);
		
	}
	
	//Junior Peumi
	/**
	 * Dessiner l'obstacle triangle avec ses segments
	 * @param g2d contexte graphique
	 * @param matMC matrice de transformation monde-vers-composant
	 */
	public void dessiner (Graphics2D g2d,  AffineTransform mat) {
		g2d.setColor(Color.black);
		triangle = new Polygon(new int[] {(int) vecteurPosition.getX(),(int) vecteurPosition.getX()+6,(int) vecteurPosition.getX()+12}, new int[] {(int)vecteurPosition.getY(),(int)vecteurPosition.getY()-8,(int) vecteurPosition.getY()} ,3)  ;
		g2d.fill(mat.createTransformedShape(triangle));		
	}
	/**
	 *  La méthode qui verifie si le personnage est en collision avec les segements de l'objet 
	 *  @param rayon : le rayon du personnage 
	 *  
	 *  @param centre : le point du centre du personnage.
	 * 
	 */ 


	//GETTERS ET SETTERS
	//Junior Peumi
	public Shape getForme() {
		return triangle;
	}
	
	public Vecteur getVecteurPosition() {
		return (this.vecteurPosition);
	}
	
	public void setPosition (Vecteur vecInput) {
		this.vecteurPosition = vecInput;
	}
	
	/** methode qui retourne la liste des segments du triangle
	 * 
	 * @return liste des segments pour le triangle
	 */
	// par Roger
	public ArrayList<Segments> getSegment() {
		return null;
		//return this.tot_segment_triangle;
	}
	
	public boolean isTriangle() {
		return true;
		
		
	}
	
	public boolean estCharge() {
		
		return false;

	}
	

	@Override
	public double getLargeur() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getLongueur() {
		// TODO Auto-generated method stub
		return 0;
	}
	public void movetri(int deltaX, int deltaY) {
		triangle.translate(deltaX, deltaY);
	}
	
}

