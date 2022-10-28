package ecouteurs;

import java.util.EventListener;

/**
 * Classe interface pour les écouteurs personnalisés.
 * @author Junior Peumi
 */
public interface EcouteursPerso extends EventListener{
	/**
	 * Méthode abstraite pour l'écouteur personnalisé bntModeNiveaux();
	 */
	public void btnModeNiveaux();
	
	/**
	 * Méthode abstraite pour l'écouteur personnalisé bntModeEditeur();
	 */
	public void btnModeEditeur();
	
	/**
	 * Méthode abstraite pour l'écouteur personnalisé bntModeMenu();
	 */
	public void btnModeMenu();
}