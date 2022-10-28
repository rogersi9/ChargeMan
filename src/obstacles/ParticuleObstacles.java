package obstacles;

import java.awt.BasicStroke;
import java.awt.Color;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import geometrie.Segments;
import geometrie.Vecteur;


/**
 * Representation d'une particule obstacle qui peut etre soit positive ou negative
 * 
 * @author Nouhaila Aater
 */
public class ParticuleObstacles extends Obstacles implements Dessinable  {


	private final double DIAMETRE = 5;
	private Vecteur vecteurPosition;
	private boolean chargeParticulePositive ;
	private  double chargeParticule;
	private double masseParticule ;
	private Ellipse2D.Double particuleObstacle;
	private Line2D.Double ligneHori;
	private Line2D.Double ligneVerti;
	private Shape forme;



	/**
	 * Constructeur qui prend les caracteristique de la particule obstacle sa position et le signe de sa chargeParticule
	 * @param vecteurPosition la position vecteur de la particule obstacle
	 * @param chargeParticule  la chargeParticule de la particule obstacle est-elle positif ou non
	 */
	public ParticuleObstacles(Vecteur vecteurPosition, double chargeParticule, boolean chargeParticuleEstPositif, double masseParticule) {

		super(vecteurPosition);
		this.vecteurPosition = vecteurPosition;
		this.chargeParticule = chargeParticule ;
		this.chargeParticulePositive = chargeParticuleEstPositif ;
		this.masseParticule = masseParticule ;

	}

	/**
	 * Dessiner la forme de la particule dessin selon sa chargeParticule soit positive/negative
	 * @param g2d
	 * @param mat 
	 */
	public void dessiner (Graphics2D g2d,  AffineTransform mat) {

		forme = new Ellipse2D.Double(vecteurPosition.getX(), vecteurPosition.getY(), DIAMETRE, DIAMETRE);

		particuleObstacle = new Ellipse2D.Double(vecteurPosition.getX(), vecteurPosition.getY(), DIAMETRE, DIAMETRE);
		forme = (Shape) particuleObstacle;

		ligneHori = new Line2D.Double(vecteurPosition.getX() + DIAMETRE/3 ,  vecteurPosition.getY()+DIAMETRE/2 , vecteurPosition.getX()+2*DIAMETRE/3 ,  vecteurPosition.getY()+DIAMETRE/2 );
		ligneVerti = new Line2D.Double(vecteurPosition.getX()+ DIAMETRE/2,  vecteurPosition.getY()+ DIAMETRE/3  , vecteurPosition.getX()+ DIAMETRE/2,  vecteurPosition.getY()+2*DIAMETRE/3);

		g2d.setStroke(new BasicStroke(2));

		if (chargeParticulePositive) {
			g2d.setColor(Color.red);
			g2d.fill(mat.createTransformedShape(particuleObstacle));

			g2d.setColor(Color.black);
			g2d.draw(mat.createTransformedShape(particuleObstacle));

			g2d.draw(mat.createTransformedShape(ligneHori));
			g2d.draw(mat.createTransformedShape(ligneVerti));


		} else {
			g2d.setColor(Color.blue);
			g2d.fill(mat.createTransformedShape(particuleObstacle));

			g2d.setColor(Color.black);
			g2d.draw(mat.createTransformedShape(particuleObstacle));

			g2d.draw(mat.createTransformedShape(ligneHori));
		}
	}

	//LES GETTERS ET LES SETTERS
	public  Vecteur getVecteurPosition() {
		return (vecteurPosition);
	}

	public void setVecteurPosition(Vecteur positionInput) {
		this.vecteurPosition = positionInput;
	}

	public boolean getchargeParticuleEstPositif() {
		return chargeParticulePositive ;
	}

	public double getChargeParticule() {
		return chargeParticule;
	}

	public void setChargeParticule(double chargeParticule) {
		this.chargeParticule = chargeParticule ;
	}


	public double getMasseParticule() {
		return masseParticule;
	}

	public void setMasseParticule(double masseParticule) {
		this.masseParticule = masseParticule ;
	}

	public double getDiametre() {
		return DIAMETRE ;
	}

	public Shape getForme() {
		return (this.forme);
	}

	@Override
	public ArrayList<Segments> getSegment() {

		return null;
	}

	@Override
	public boolean isTriangle() {
		return false;
	}


	@Override
	public boolean estCharge() {
		return true;
	}

	@Override
	public double getLargeur() {
		return 0;
	}

	@Override
	public double getLongueur() {
		return 0;
	}





}
