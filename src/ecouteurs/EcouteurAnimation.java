package ecouteurs;

import java.util.EventListener;

import geometrie.Personnage;
import geometrie.Vecteur;

/**
 * Classe contenant les ecouteur perso de la zoneAnimation
 * @author Clement
 *
 */
public interface EcouteurAnimation extends EventListener{
	/**
	 * M�thode abstraite pour l'�couteur personnalis� de la zoneAnimation
	 * @param perso
	 * @param forceElectrique
	 * @param ForceResultante
	 */
	public void mouvementPerso(Personnage perso, Vecteur forceElectrique, Vecteur ForceResultante);
	

}