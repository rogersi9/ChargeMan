package moteur;
import geometrie.Vecteur;

/**
 * Cette classe regroupera les calculs physiques nécessaires au mouvement du personnage
 * @author Caroline Houle
 * @author Roger Sioufi
 *
 */
public class MoteurPhysique {
private double forceGravi = 9.8 ;
private static Vecteur acceletation = new Vecteur(0,0)  ;
	/**
	 * Calcule l'acceleration en fonction de la masse et des forces appliquees
	 * @param sommeDesForces La somme des forces appliquees
	 * @param masse La masse 
	 * @param accel En sortie, l'acceleraion calculee, avec a=m/F
	 */
	public static void miseAJourAcceleration(Vecteur sommeDesForces, double masse, Vecteur accel) {
		//sachant que f=ma, on calcule a = m / f
		accel.setX( sommeDesForces.getX() / masse );
		accel.setY( sommeDesForces.getY() / masse );
		
	}
	/**
	 * Calcule d'un pas d'euler en fonction du vecteur position, vitesse et accel et du delta T
	 * @param deltaT	deltaT intervalle de temps (pas)
	 * @param position vecteur position	
	 * @param vitesse	vecteur vitesse
	 * @param accel		vecteur acceleration
	 */
	public static void unPasEuler(double deltaT, Vecteur position, Vecteur vitesse, Vecteur accel) {

		Vecteur deltaVitesse = Vecteur.multiplie(accel, deltaT);
		Vecteur resultV = vitesse.additionne( deltaVitesse ); 
		vitesse.setX(resultV.getX());	//on change le vecteur vitesse
		vitesse.setY(resultV.getY());
	
		Vecteur deltaPosition = Vecteur.multiplie(vitesse, deltaT);
		Vecteur resultP = position.additionne(deltaPosition); //on calcule la position en considerant la nouvelle vitesse
		position.setX(resultP.getX());  //on change le vecteur position
		position.setY(resultP.getY());

	}
	
}
