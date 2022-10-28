package obstacles;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;

import geometrie.Vecteur;

/**
 * Classe permettant de dessiner un point d'arrive d'un niveau
 * @author Junior Peumi
 */
public class PointArrive implements Dessinable, Serializable{
	private Ellipse2D.Double pointArrive;
	private Shape forme;
	private final double DIAMETRE = 10;
	private Vecteur Position;

	public PointArrive(Vecteur Position) {
		this.Position = Position;
	}
	
	/**
	 * Methode utilisee pour dessiner le point d'arrive
	 */
	@Override
	public void dessiner(Graphics2D g2d, AffineTransform mat) {
		forme = new Ellipse2D.Double(Position.getX(), Position.getY(), DIAMETRE, DIAMETRE);

		pointArrive = new Ellipse2D.Double(Position.getX(), Position.getY(), DIAMETRE, DIAMETRE);
		forme = (Shape) pointArrive;
		g2d.setColor(Color.blue);
		g2d.fill(mat.createTransformedShape(pointArrive));

		g2d.setColor(Color.red);
		g2d.setStroke(new BasicStroke(3));

		g2d.draw(mat.createTransformedShape(pointArrive));
		

	}
	public  Vecteur getVecteurPosition() {
		return (Position);
	}

	public void setPosition(Vecteur positionInput) {
		this.Position = positionInput;
	}
	public double getDiametre() {
		return DIAMETRE ;
	}

	public Shape getForme() {
		return (this.forme);
	}
	public double getCenterX() {
		
		return pointArrive.getCenterX();
	}
	public double getCenterY() {
		
		return pointArrive.getCenterY();
	}
	
}
