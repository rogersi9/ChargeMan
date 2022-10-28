package Affichage;

import java.applet.Applet;
import java.applet.AudioClip;

/**
 * Classe qui s'occupe de la musique
 * @author Nouhaila Aater
 * @author Junior Peumi
 */
public class Musique {
	
	
	private AudioClip musique;
    private String nomSon;
    
    /**
     * Methode qui sert a demarer la musique
     */
    //Nouhaila
	public void jouerMusique () {
		musique.play();
	}
	
	
	
	/**
     * Methode qui sert a arreter la musique
     */
    //Nouhaila
	public void arreterMusique() {
		musique.stop();
	}
	
	
	/**
     * Methode qui sert a jouer la musique en boucle
     */
    //Nouhaila
	public void jouerMusiqueEnBoucle() {
		musique.loop();
	}
	
	//Junior
	public Musique (String nom) {
		this.nomSon = nom;
		java.net.URL fichier = getClass().getClassLoader().getResource(nom);
		this.musique = Applet.newAudioClip(fichier);
	}
}
