package obstacles;

import java.awt.Color;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.ArrayList;

import geometrie.Segments;
import geometrie.Vecteur;


/**
 * Creation de la classe abstraite Obstacles qui regroupe les methodes que les obstacles formes doivent utiliser.
 * 
 * @author Nouhaila Aater
 */
public abstract class Obstacles implements Dessinable,Serializable{

	private Vecteur vecteurPosition;
	private Shape formeObstacle;
	private Color couleurObstacle;
	
	public abstract ArrayList<Segments> getSegment();
	public abstract  boolean isTriangle() ;
		public abstract double getLargeur();
		public abstract double getLongueur();
		public abstract boolean estCharge();
		
	
	
	/**
	 * Constructeur qui permet de savoir le nombre de segment utiliser par la forme et la position de son point de depart
	 * @param nbSegment le nombre de segment utiliser par la forme
	 * @param p1 la position de point de depart de la forme
	 */
	public Obstacles(Vecteur vecteurPosition) {
		this.vecteurPosition = vecteurPosition;
	}

	
	/**
	 * Permet de dessiner les formes
	 */
	@Override
	public void dessiner(Graphics2D g2d,  AffineTransform mat) {
		// set color
		g2d.setColor(this.couleurObstacle);
		g2d.fill(mat.createTransformedShape(formeObstacle));
	}

	
	/**
	 * Permet le detection des Collision
	 * @param rayon le rayon du personnage 
	 * @param centre le point centre de la forme obstacle
	 */
	public void detecterCollisionH(Double  rayon, Point centre) {

	}

	public Vecteur getPosition() {
		return this.vecteurPosition;
	}

	public void setPosition(Vecteur vecteurInput) {
		this.vecteurPosition = vecteurInput;
	}

	public Shape getForme() {
		return this.formeObstacle;
	}

	
	
	public void setForme(Shape formeInput) {
		this.formeObstacle = formeInput;
	}
	
	
	public void setPosition(int posX, int posY) {
		getForme().getBounds().setBounds(posX, posY, (int) getForme().getBounds().getWidth(), (int) getForme().getBounds().getHeight());
	}

}
