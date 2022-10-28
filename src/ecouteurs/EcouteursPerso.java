package ecouteurs;

import java.util.EventListener;

/**
 * Classe interface pour les �couteurs personnalis�s.
 * @author Junior Peumi
 */
public interface EcouteursPerso extends EventListener{
	/**
	 * M�thode abstraite pour l'�couteur personnalis� bntModeNiveaux();
	 */
	public void btnModeNiveaux();
	
	/**
	 * M�thode abstraite pour l'�couteur personnalis� bntModeEditeur();
	 */
	public void btnModeEditeur();
	
	/**
	 * M�thode abstraite pour l'�couteur personnalis� bntModeMenu();
	 */
	public void btnModeMenu();
}