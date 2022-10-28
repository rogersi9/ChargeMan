package geometrie;

import java.awt.BasicStroke;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.io.Serializable;

import moteur.MoteurPhysique;
import obstacles.Dessinable;
import obstacles.Obstacles;

/**
 * Classe Personnage: représentation sommaire d'une balle à l'aide d'un simple cercle.
 * Le personnage mémorise sa masse, son diamètre, sa position, sa vitesse, son accélération, la somme des forces qui s'applique sur lui et sa charge.
 *@author Caroline Houle
 *@author Roger Sioufi
 *@author Nouhaila Aater
 */

public class Personnage implements Dessinable, Serializable{ 
	private double diametre;
	private double masseEnKg = 1*Math.pow(10, -27);
	private  double charge=0 ;
	private  double rayonAction ;
	private Ellipse2D.Double personnage;
	private Ellipse2D.Double cercleAction;

	private Vecteur position, vitesse, accel;


	//Nouhaila
	private Line2D.Double oeil1;
	private Line2D.Double oeil2;
	private Line2D.Double positif1;
	private Line2D.Double positif2;
	private Line2D.Double bouche;
	private Line2D.Double action;
	private VecteurAfficherVitesse  vecteurAfficherVitesse;
    private boolean afficherVecteurVitesse = false ;


	/**
	 * Constructeur ou la position, la vitesse et l'acceleration  initiales sont spécifiés
	 * @param position Vecteur incluant les positions en x et y du coin superieur-gauche 
	 * @param vitesse Vecteur incluant les vitesses en x et y
	 * @param accel Vecteur incluant les accelerations en x et y  
	 * @param diametre diametre (unites du monde reel)
	 */ 
    // Roger
	public Personnage(Vecteur position, Vecteur vitesse, Vecteur accel, double diametre, double rayonAction) {
		this.rayonAction=rayonAction;
		this.diametre = diametre;	
		setPosition( position ); //ces setters crent des copies des vecteurs
		setVitesse( vitesse );
		setAccel( accel );
	}
	/**
	 * Constructeur ou la position, la charge du personnage ,sa masse et sa charge  initiales sont spécifiés
	 * @param position
	 * @param charge
	 * @param masse
	 * @param rayonAction
	 */    // Roger

	public Personnage(Vecteur position, double charge, double masse, double rayonAction) {
		this.rayonAction=rayonAction;
		setPosition( position ); //ces setters crent des copies des vecteurs
		setCharge( charge );
		setMasseEnKg( masse );
	}


	/**
	 * Constructeur ou la position initiale est specifiee par un vecteur
	 * La vitesse et acceleration sont mises à zero. 
	 * @param position Vecteur incluant les positions en x et y du coin superieur-gauche
	 * @param diametre diametre (unites du monde reel)
	 */    // Roger

	public Personnage(Vecteur position, double diametre, double rayonAction) {
		this( position, new Vecteur(0,0),  new Vecteur(0, 0), diametre ,rayonAction);
	}

	/**
	 * Constructeur ou la position initiale est specifiee par x et y
	 * La vitesse et acceleration sont mises à zero. 
	 * @param x Coin superieur-gauche en x
	 * @param y Coin superieur-gauche en y
	 * @param diametre diametre (unites du monde reel)
	 */    // Roger
/**
 * un autre constructeur qui specieife la position x et  du personnage avec sa diametre et son rayon d'action
 * @param x
 * @param y
 * @param diametre
 * @param rayonAction
 */    // Roger

	public Personnage(double x, double y, double diametre, double rayonAction) {
		this( new Vecteur(x, y), new Vecteur(0,0),  new Vecteur(0, 0), diametre,rayonAction ); //appelle l'autre constructeur
	}

	/**
	 * Constructeur ou la position initiale est specifiee par un vecteur et ou la vitesse initiale est aussi specifiee
	 * @param position Vecteur incluant les positions en x et y du coin superieur-gauche
	 * @param vitesse Vecteur incluant les vitesses en x et y 
	 * @param diametre diametre (unites du monde reel)
	 */    // Roger

	public Personnage(Vecteur position, Vecteur vitesse, double diametre, double rayonAction) {
		this( position, vitesse,  new Vecteur(0, 0), diametre,rayonAction );
	}



	/**
	 * Effectue une iteration de l'algorithme d'Euler implicite. Calcule la nouvelle vitesse et la nouvelle
	 * position de la balle.
	 * @param deltaT intervalle de temps (pas)
	 *///Roger
	public void unPasEuler(double deltaT) {
		MoteurPhysique.unPasEuler(deltaT, position, vitesse, accel);
		System.out.println("Nouvelle vitesse: " + vitesse.toString() + "  Nouvelle position: " + position.toString());
	}


	/**
	 * Modifie la somme des forces excercées sur la balle
	 * Ceci a pour consequence de modifier l'acceleration
	 * @param forces Les force exercees sur la balle
	 *///Roger
	public void setSommeDesForces(Vecteur forces) {
		//on relegue cette tache au moteur physique car c'est le meme traitement peu importe de type d'objet dans la scene!
		MoteurPhysique.miseAJourAcceleration(forces, masseEnKg, accel);

	}
	
	
	
	

	/**
	 * Modifie la position de la balle
	 * Note: ici on decide de simplement refaire la forme sous-jacente!
	 * @param pos Vecteur incluant les positions en x et y 
	 */    // Roger

	public void setPosition(Vecteur pos) {
		//on fait une copie du vecteur  passe en paramètre (l'orignal peut donc être modifé au besoin)
		Vecteur nouveauVec = new Vecteur(pos.getX(), pos.getY());
		this.position = nouveauVec;
	}


	/**
	 * Retourne la position courante
	 * @return la position courante
*/
	public  Vecteur getPosition() {
		return (position);
	}

	/**
	 * Associe une vitesse, ou modifie la vitesse courante de la balle
	 * @param vitesse Vecteur incluant les vitesses en x et y 
	 */    
	public void setVitesse(Vecteur vitesse) {
	
		Vecteur nouveauVec = new Vecteur(vitesse.getX(), vitesse.getY());
		this.vitesse = nouveauVec;
	}

	/**
	 * Retourne la vitesse courante
	 * @return la vitesse courante
	 */   

	public Vecteur getVitesse() {
		return (vitesse);
	}

	/**
	 * Associe une acceleration, ou modifie l'acceleration courante de la balle
	 * @param accel Vecteur incluant les accelerations en x et y 
	 */

	public void setAccel(Vecteur accel) {
		
		Vecteur nouveauVec = new Vecteur(accel.getX(), accel.getY());
		this.accel = nouveauVec;
	}

	/**
	 * Retourne l'acceleration courante
	 * @return acceleration courante
	 */
	public Vecteur getAccel() {
		return (accel);
	}


	/**
	 * Retourne le diametre de la balle
	 * @return Le diamètre
	 */
	public double getDiametre() {
		return diametre;
	}

	/**
	 * MOdifie le diametre de la balle
	 * @param diametre Le nouveau diamètre
	 */
	public void setDiametre(double diametre) {
		this.diametre = diametre;
	}


	/**
	 * Retourne la masse en Kg
	 * @return La masse en kg
	 */
	public double getMasseEnKg() {
		return masseEnKg;
	}

	/**
	 * Modifie la masse 
	 * @param masseEnKg La msse exprimee en kg
	 */
	public void setMasseEnKg(double masseEnKg) {
		this.masseEnKg = masseEnKg;
	}



	/**
	 * Modifie la charge du personnage
	 * @param masseEnKg La masse exprimee en kg
	 */
	public void setCharge(double charge) {
		this.charge=charge;
	}
	/**
	 * Retourne la charge du personnage
	 * @return La charge
	 */
	public double getCharge() {
		return charge;

	}

	/**
	 * Dessiner le personnage en fonction de sa charge, le rayon d'action et vetceur vitesse
	 * @param g2d contexte graphique
	 * @param matMC matrice de transformation monde-vers-composant
	 * 
	 * @author Nouhaila Aater
	 */
	//Nouhaila	 
	public void dessiner(Graphics2D g2d, AffineTransform mat ) {

		Double rayon = diametre /2   ;
		personnage = new Ellipse2D.Double(position.getX(), position.getY(), diametre, diametre); //creation du personnage 
		g2d.setColor(Color.YELLOW);
        g2d.fill( mat.createTransformedShape(personnage) );	
		oeil1 = new Line2D.Double(position.getX() + diametre/6 , position.getY() + diametre/3, position.getX() + diametre/3,position.getY() + diametre/3); // creation dessin oeil trait horizontale a droite 
		oeil2 = new Line2D.Double(position.getX() + 4*rayon/3, position.getY()+diametre/3, position.getX()+5* diametre/6, position.getY()+diametre/3); // creation dessin oeil trait horizontale a gauche
		bouche = new Line2D.Double(position.getX() + rayon/2 , position.getY() + 3*rayon/2 , position.getX() + 3*rayon/2, position.getY() + 3*rayon/2); // creation dessin bouche

		g2d.setColor(Color.BLACK);
		g2d.setStroke(new BasicStroke(2));
		g2d.draw( mat.createTransformedShape(personnage) );	
		g2d.draw( mat.createTransformedShape(oeil1));	
		g2d.draw( mat.createTransformedShape(oeil2));
	    g2d.draw( mat.createTransformedShape( bouche ));

		if( charge > 0 ) {
			
			positif1 = new Line2D.Double(position.getX()+ diametre/4 , position.getY() + rayon/2, position.getX() + diametre/4 , position.getY() + 4*rayon/5); // creation dessin oeil positif
			positif2 = new Line2D.Double(position.getX()+ 3*rayon/2 , position.getY() + rayon/2, position.getX() + 3*rayon/2 , position.getY()+ 4*rayon/5); // creation dessin oeil positif

			g2d.draw( mat.createTransformedShape(positif1));	
			g2d.draw( mat.createTransformedShape(positif2));
		}
		 	
		
		cercleAction = new Ellipse2D.Double(position.getX()+rayon-rayonAction, position.getY()+rayon-rayonAction, rayonAction*2, rayonAction*2); //creation du personnage 
		action = new Line2D.Double(position.getX()+rayon-rayonAction+rayonAction ,  position.getY()+rayon, position.getX()+rayon-rayonAction+(rayonAction*2) ,position.getY()+rayon);
		
		
		g2d.setColor(Color.black);
		float dash1[] = {10.0f};
	    BasicStroke dashed = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f);
		g2d.setStroke(dashed);
		
		g2d.draw( mat.createTransformedShape(cercleAction) );	
		g2d.draw( mat.createTransformedShape(action));	

	  if(afficherVecteurVitesse) {
		
		vecteurAfficherVitesse = new VecteurAfficherVitesse(vitesse, position, diametre);
			
		vecteurAfficherVitesse.dessiner(g2d, mat);
		//1
		
	}

	}
	
	
	/**
	 * detecte s'il faut dessiner le vecteur vitesse ou pas
	 * @param afficherVec true si on veux afficher Vecteur vitesse
	 */
	//Nouhaila
	public void afficherVecteurVitesse(boolean afficherVecteur) {
		afficherVecteurVitesse = afficherVecteur;
	}

	/**
	 * Retourne le diametre de la balle
	 * @return Le diamètre
	 */
	public double getRayonAction() {
		return rayonAction;
	}

	/**
	 * MOdifie le diametre de la balle
	 * @param diametre Le nouveau diamètre
	 */
	public void setRayonAction(double rayonAction) {
		this.rayonAction = rayonAction;
		
	}
	
	public double getX() {
		return personnage.getCenterX();
 		
 		
 	}
	public void setX(double d ) {
		this.position.setX(d);
 		
 		
 	}
	public void setY(double d ) {
		this.position.setY(d);
 		
 		
 	}
	public double getY() {
		return personnage.getCenterY();
 		
 		
		
 	}
	
	public Vecteur getDirection() {
		
		
		return accel;
		
	}
	
	public Point2D.Double getCentre() {
		
		double x = this.personnage.getCenterX();
		double y = this.personnage.getCenterY();
		Point2D.Double centre = new Point2D.Double(x,y);
		return centre;
		
	}
	public Vecteur getCentreVecteur() {
		double x = this.personnage.getCenterX();
		double y = this.personnage.getCenterY();
		return (new Vecteur(x,y));
	}
	

}//fin classe





