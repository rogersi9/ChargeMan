package Niveau;

import java.io.Serializable;




import java.util.ArrayList;

import geometrie.Personnage;
import geometrie.Vecteur;
import geometrie.Segments;

import obstacles.Obstacles;
import obstacles.ParticuleObstacles;
import obstacles.PointArrive;

/**
 * Classe permettant de créer et manipuler un niveau ainsi que ses propriétés. 
 * 
 * @author Clement Harvey
 * @author nouhaila
 */
public class Niveau implements Serializable {

	private PointArrive arrivee;
	private int tempsMax;
	private ArrayList<Obstacles> obstacles;
	private double chargeObst;
	private double rayon;
	private double forceParClick;
	private Vecteur forceGravite;
	private String nom;
	private Personnage personnage;
	
	
	//Nouhaila
	boolean arreterTemps ;
	boolean afficherVecteur ;
	private final int tempsMaximum;

	//par Clement
	/**
	 * Constructeur créant un niveau.
	 * 
	 * @param personnage contient le personnage placer a sa position initial
	 * @param arrivee contient le la zone a se rendre pour gagner le niveau.
	 * @param tempsMax Le temps maximal pour compléter le niveau.
	 * @param obstacles Une "ArrayList" contenant les obstacles du niveau.
	 * @param chargeObst Valeur absolue de la charge des obstacles.
	 * @param rayon Rayon d'action des particules chargées.
	 * @param forceParClick Valeur de la force appliquée par l'utilisateur lors d'un évènement sur les flèches du clavier.
	 * @param forceGravite Valeur de la force gravitationnelle.
	 * @param nom Nom du niveau.
	 */
	public Niveau(Personnage personnage, PointArrive arrivee,int tempsMax,
			ArrayList<Obstacles> obstacles,double chargeObst,double rayon,
			double forceParClick,Vecteur forceGravite,  String nom , boolean arreterTemps, boolean afficherVecteur) {
		
		
		this.tempsMax=tempsMax;
		this.arrivee=arrivee;
		this.obstacles=obstacles;
		this.chargeObst=chargeObst;
		this.rayon=rayon;
		this.personnage=personnage;
		this.forceParClick=forceParClick;
		this.forceGravite=forceGravite;
		this.nom=nom;
		this.arreterTemps = arreterTemps ;
		this.afficherVecteur = afficherVecteur ;
		this.tempsMaximum = tempsMax;
		
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}


	public int getTempsMax() {
		return tempsMax;
	}

	public void setTempsMax(int tempsMax) {
		this.tempsMax = tempsMax;
	}

	public ArrayList<Obstacles> getObstacles() {
		return obstacles;
	}

	public void setObstacles(ArrayList<Obstacles> obstacles) {
		this.obstacles = obstacles;
	}

	public double getChargeObst() {
		return chargeObst;
	}

	public void setChargeObst(double chargeObst) {
		this.chargeObst = chargeObst;
	}

	public double getRayon() {
		return rayon;
	}

	public void setRayon(double rayon) {
		this.rayon = rayon;
	}

	public Personnage getPersonnage() {
		return personnage;
	}

	public void setPersonnage(Personnage perso) {
		this.personnage=perso;
	}


	public double getForceParClick() {
		return forceParClick;
	}

	public void setForceParClick(double forceParClick) {
		this.forceParClick = forceParClick;
	}

	public Vecteur getForceGravite() {
		return forceGravite;
	}

	public void setForceGravite(Vecteur forceGravite) {
		this.forceGravite = forceGravite;
	}
	public PointArrive getPointArrive() {
		return arrivee;
	}
	public void resetNiveau() {
		this.tempsMax = 0;
		this.personnage.setVitesse(new Vecteur(0,0));
		this.personnage.setAccel(new Vecteur(0,0));
		this.personnage.setPosition(new Vecteur(40, 40));
	}
	


	//Nouhaila
	public boolean getTimerArreter() {
		return arreterTemps;
	}

	//Nouhaila
	public void setTimerArreter( boolean arreterTemps) {
		this.arreterTemps = arreterTemps ;
	}



	//Nouhaila
	public boolean getAfficherVecteur() {
		return afficherVecteur;
	}

	//Nouhaila
	public void setAfficherVecteur( boolean afficherVecteur) {
		this.afficherVecteur = afficherVecteur ;
	}

	//Nouhaila
	public int getTempsMaximum() {
		return tempsMaximum;
	}


	

}
