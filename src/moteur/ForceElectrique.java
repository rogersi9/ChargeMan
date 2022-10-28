package moteur;

import java.util.ArrayList;


import geometrie.Personnage;
import geometrie.Vecteur;
import obstacles.ParticuleObstacles;


/**
 * Classe Force Electrique permet de calculer les forces electrique que 
 * le personnage subit par les obstacles electrique qui font partie de son rayon d'action 
 * 
 * @author Nouhaila Aater
 *
 */
public class ForceElectrique {


	private Personnage personnage ;
	private ParticuleObstacles particuleObstacle; 
	private  boolean particuleEstPositif;
	private double charge;

	private final double K = 9 * Math.pow(10, 9) ;



	/**
	 * Constructeur qui prend en parametre le personnage et la particule obstacle qui rencontre, 
	 * avec le rayon d'action
	 * 
	 * @param personnage
	 * @param particuleObstacle
	 * @param rayonAction
	 */
	public ForceElectrique ( Personnage personnage , ParticuleObstacles particuleObstacle , double charge) {


		this.personnage = personnage ;
		this.particuleObstacle = particuleObstacle ;
		this.particuleEstPositif = particuleObstacle.getchargeParticuleEstPositif() ;
		if (particuleEstPositif)this.charge = charge;
		else this.charge = -1*charge;
		

	}



	/**
	 * Calculer la valeur de la force electrique entre le personnage et la particule obstacle
	 * 
	 * avec la formule:  F = (K*chargePerso*chargeParticule)/(distance entre perso et particule)^2

	 * 
	 * @return forceElec la force electrique entre le personnage et la particule obstacle 
	 */
	public double ForceElectriqueEnValeur() {

		double forceElec ;

		if (particulePriseEnCompte()) {

			double chargePerso = personnage.getCharge() ;
			double distance = distancePersoParticule();
			double r = Math.pow(distance, 2);
			
			forceElec = ( K * chargePerso * charge ) / r ;

		} else {

			forceElec = 0 ;

		}

		return forceElec ;
	}

	
	/**
	 * Calcule force electrique en vecteur avec la formule:
	 * F = (K*chargePerso*chargeParticule*vecteurUnitaire)/distance^2
	 * Donc ForceElecVecteur = ForceElecValeur * vecteurUnitaire
	 * @return
	 */
	public Vecteur forceElecVecteur() {
		
		
		Vecteur forceElecVecteur;
		double forceElec = ForceElectriqueEnValeur();
		Vecteur vecUnitaire = vecteurUnitaire();
		
		if(particulePriseEnCompte()) {
		
                forceElecVecteur = vecUnitaire.multiplie(forceElec);
		}  
		else {
			
			forceElecVecteur = new Vecteur(0,0) ;
		}
		 
		return forceElecVecteur;
		
		
	}

	


	/**
	 * Determine si une particule obstacle fait partie du rayon d'action, donc si elle est prise en considération
	 * dans le calcule de la force electrique que le personnage subit 
	 * 
	 * @return true/ false retourne vrai si elle fait partie du rayon d'action 
	 */
	public boolean particulePriseEnCompte () {



		double x = Math.abs(  personnage.getPosition().getX()+(personnage.getDiametre()/2)  - particuleObstacle.getPosition().getX()-particuleObstacle.getDiametre()/2 ) ;
		double y = Math.abs( personnage.getPosition().getY()+ (personnage.getDiametre()/2)  - particuleObstacle.getPosition().getY()-particuleObstacle.getDiametre()/2 ) ;

		double a = Math.pow(x, 2);
		double b = Math.pow(y, 2);

		double distance ;


		distance =  Math.sqrt( a + b ) ;

		if (distance <= personnage.getRayonAction() && distance>= personnage.getDiametre()/2) {
			return true;
			
		} else {
			return false;
		}
	}


	/**
	 *  Detection si le personnage et la particule obstacle ont le meme signe de charge ou non 
	 *  
	 * @return true/ false vrai si les deux particules ont le meme signe de charge
	 */

	public boolean particuleIdentique () {

	    double chargeParticule= particuleObstacle.getChargeParticule() ;

		if(particuleObstacle.getchargeParticuleEstPositif()==false) {
			
			chargeParticule = chargeParticule * -1 ;
		} 
		
		if(Math.signum( personnage.getCharge() ) == Math.signum( chargeParticule ) ) {
			return true;
		}
		else {
			return false;
		}
		
	
	}
	
	/**
	 * Calcule distance entre le personnage et une particule avec la formule
	 * a^2+b^2=c^2
	 * @return distance distance entre personnage et particule
	 */
	public double distancePersoParticule() {
		
		double x = Math.abs( personnage.getPosition().getX() - particuleObstacle.getPosition().getX()) ;
		double y = Math.abs( personnage.getPosition().getY() - particuleObstacle.getPosition().getY()) ;

		double a = Math.pow(x, 2);
		double b = Math.pow(y, 2);

		double distance ;


		distance =  Math.sqrt( a + b ) ;
		
		return distance;
	}
	
	
	/**
	 * Calcule le vecteur deplacement entre personnage et particule 
	 * vecDeplacement = (Xperso, Yperso) - (Xparticule, Yparticule)
	 * 
	 * @return vecDeplacement le vecteur deplacement entre personnage et particule
	 */
	public Vecteur VecteurDeplacement() {
		
		Vecteur vecDeplacement;
		
		Vecteur vecPersonnage = personnage.getPosition();
		Vecteur vecParticule = particuleObstacle.getVecteurPosition();
		
		vecDeplacement = vecPersonnage.soustrait(vecParticule);
		
		return vecDeplacement;
		
	}
	
	/**
	 * Calculer le vecteur unitaire a partie du vecteur deplacement et la distance entre personnage et particule
	 * vecteur unitaire = vecteur deplacement / distance
	 * @return vecUnitaire le vecteur unitaire 
	 */
	public Vecteur vecteurUnitaire() {
		
		Double distance = distancePersoParticule();
		Vecteur vecDeplacement = VecteurDeplacement();
		Vecteur vecUnitaire;
		
		vecUnitaire = vecDeplacement.multiplie(1/distance);
		
		return vecUnitaire;
		
	}
	
	public boolean getParticuleEstPositif() {
		return particuleObstacle.getchargeParticuleEstPositif();
	}

}



