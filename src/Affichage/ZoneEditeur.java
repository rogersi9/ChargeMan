package Affichage;

import java.awt.BasicStroke;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import geometrie.Personnage;
import geometrie.Vecteur;
import obstacles.BarreHorizontale;
import obstacles.BarreVerticale;

import obstacles.PointArrive;
import obstacles.Obstacles;
import obstacles.ParticuleObstacles;
import obstacles.Triangle;



/**
 * La classe du zone editeur qui permet a l'utilisateur d'effectuer 
 * 
 *@author Nouhaila Aater
 *@author Roger s
 *@author junior
 */
public class ZoneEditeur extends JPanel {

	private static final long serialVersionUID = 1L;
	private final double LARGEUR_DU_MONDE = 100; //en metres
	private final double LONGUEUR_DU_MONDE = 80;

	private Vecteur posInitiale = new Vecteur (40,40);
	private double chargeParticule = 1*Math.pow(10, -17);
	private double masseParticule = 2 ;
	private ParticuleObstacles particuleObstaclePos = new ParticuleObstacles(posInitiale, chargeParticule ,true, masseParticule);
	private ParticuleObstacles particuleObstacleNeg = new ParticuleObstacles(posInitiale,chargeParticule, false, masseParticule);
	private BarreHorizontale barreHori = new BarreHorizontale (posInitiale);
	private BarreVerticale barreVerti = new BarreVerticale (posInitiale);
	private Triangle triangle = new Triangle(posInitiale );
	private int rayonAction=7;


	private Vecteur posMechant= new Vecteur(30,30);
	 
	// matrice
	private ModeleAffichage modele = new ModeleAffichage(getWidth(), getHeight(), LARGEUR_DU_MONDE);
	AffineTransform matMC = modele.getMatMC();
	
	// Roger
	private int nb_triangle=0;
	private int nb_barre_horizontal=0;
	private int nb_barre_verticale=0;
	private int nb_proton=0;
	private int nb_electron=0;
	private Personnage perso = new Personnage(10, 10, 7,rayonAction);
	
	//Nouhaila 
	private Vecteur vecPointArrive = new Vecteur (70,5);
	int temps = 25;
	private PointArrive PointArrive = new PointArrive (vecPointArrive );
	private boolean afficherVecteur = false;
	private boolean arreterTemps =false ;
	private boolean ennemie =false ;
	//junior peumi
	private ArrayList <Obstacles> listeObstacles = new ArrayList<Obstacles>();
	
	
	/**
	 * Cree la scene ou l'utilisateur va etre capable de placer les objets
	 */
	
	public ZoneEditeur() {
		
		Color n = new Color(217, 217, 217);
		setBackground(n);
	
	}
	/**
	 * Permet de dessiner la scene 
	 * @param g Contexte graphique
	 */
	@Override
	public void paintComponent(Graphics g) {   
		
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;    
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		
		modele = new ModeleAffichage(getWidth(), getHeight(), LARGEUR_DU_MONDE);
		matMC = modele.getMatMC();

		g2d.setColor(Color.blue);

		perso.dessiner(g2d, matMC);
		dessinerObstacles(g2d, matMC);
		
		
		PointArrive.dessiner(g2d, matMC);
		if(ennemie) {
		
		}
		//Par Roger
		
		float[] dash1 = { 15f, 0f, 15f };

	    BasicStroke bs1 = new BasicStroke(1, 
	        BasicStroke.CAP_BUTT, 
	        BasicStroke.JOIN_ROUND, 
	        1.0f, 
	        dash1,
	        2f);
	    
	    g2d.setColor(Color.white);
	    g2d.setStroke(bs1);
	    g2d.drawLine(0,0, getWidth(), (0));
	    
	    g2d.drawLine(0,0, (0), getHeight());
	    g2d.setColor(Color.white);
	    
	    g2d.setFont(new Font("Cataneo", Font.PLAIN, 20)); 

	    g2d.drawString(""+ LARGEUR_DU_MONDE+ " m", getWidth()-100, 30);
	    
	    
	    g2d.drawString(""+ LONGUEUR_DU_MONDE + " m",10 , getHeight()-20);
		
	}//fin paintComponent

	//par Junior Peumi
	public void ajoutBarreHorizontale(Vecteur vecInput) {
		listeObstacles.add(new BarreHorizontale(vecInput));
		repaint();
	}
	//par Junior Peumi
	public void ajoutBarreVerticale(Vecteur vecInput) {
		listeObstacles.add(new BarreVerticale(vecInput));
		repaint();
		
	}
	//par Junior Peumi
	public void ajoutTriangle(Vecteur vecInput) {

		listeObstacles.add(new Triangle(vecInput));
		repaint();
		
	}
	
	
	
	//par Junior Peumi
	public void ajoutParticuleObstaclePositive(Vecteur vecInput) {
		listeObstacles.add(new ParticuleObstacles(vecInput,chargeParticule, true, masseParticule));
		repaint();
	}
	//par Junior Peumi
	public void ajoutParticuleObstacleNegative(Vecteur vecInput) {
		listeObstacles.add(new ParticuleObstacles(vecInput,chargeParticule, false, masseParticule));
		repaint();
	}
	//par Junior Peumi
	public ArrayList<Obstacles> getListeObstacles() {
		return listeObstacles;
	}
	

	/**
	 * Methode qui permet de preciser le nombre de triangle desire dans le niveau
	 * @param t 
	 * @author Roger
	 */
	public void setNombreTriangle(int t) {
		this.nb_triangle=t;
		repaint();
	}
	
	/**
	 * Methode qui permet de preciser le nombre de Barre Horizontale desire dans le niveau
	 * @param h
	 * @author Roger
	 */
	public void setNombreBarreHori(int h) {
		this.nb_barre_horizontal=h;
		repaint();
	}
	
	/**
	 * Methode qui permet de preciser le nombre de barre verticale desire dans le niveau
	 * @param v
	 * @author Roger
	 */
	public void setNombreBarreVerti(int v) {
		this.nb_barre_verticale=v;
		repaint();
	}
	
	/**
	 * Methode qui permet de preciser le nombre de proton desire dans le niveau
	 * @param v
	 * @author Roger
	 */
	public void setNombreProton(int p) {
		this.nb_proton=p;
		repaint();
	}
	
	/**
	 * Methode qui permet de preciser le nombre d'electron desire dans le niveau
	 * @param v
	 * @author Roger
	 */
	public void setNombreElectron(int e) {
		this.nb_electron=e;
		repaint();
	}
	
	/**
	 * Methode qui permet d'effacer tout les obstacles
	 * @author Roger
	 */
public void reinitialiser() {
		
		listeObstacles.clear();
		
		this.nb_barre_horizontal=0;
		this.nb_barre_verticale=0;
		this.nb_triangle=0;
		this.nb_proton=0;
		this.nb_electron=0;
		repaint();
	}
	
	/*
	public boolean estSelectionee(int posX, int posY) {
		for(int x=0; 
	}
	*/
	
	// par Junior Peumi
	/**
	 * Méthode qui permet de supprimer un obstacle base sur son indice.
	 * @param nb Indice de l'obstacle a supprimer
	 */
	public void effacerObstacle (int nb) {
		listeObstacles.remove(nb);
		repaint();
	}
	private void dessinerObstacles(Graphics2D g2d, AffineTransform matMC) {
		for(int i=0; i<listeObstacles.size(); i++) {
			listeObstacles.get(i).dessiner(g2d, matMC);
		}
	}
	
	/*private void dessinerTriangle(Graphics2D g2d, AffineTransform matMC) {
		for(int i=0; i<listeTriangles.size(); i++) {
			listeTriangles.get(i).dessiner(g2d, matMC);
		}
	}*/
	
	public AffineTransform getMatMC() {
		return this.matMC;
		
	}
	public double getMatMCY() {
		return this.matMC.getScaleY();
		
	}
	
	
	public void redessiner() {
		repaint();
	}
	public Personnage getPersonnage() {
		return perso;
	}
	public void setChargePerso(double charge) {
		perso.setCharge(charge);
	}

	public PointArrive getPointArrive () {
		return this.PointArrive ;
	}
	
	public void setRayonAction(int rayon) {
		this.rayonAction=rayon;
		perso.setRayonAction(rayon);
		repaint();
	}
	
	
	//Nouhaila
   public int getTempsMax() {
	   return temps;
   }
	//Nouhaila
   public boolean getAfficherVecteur() {
	   return afficherVecteur;
   }
	//Nouhaila
   public boolean getArreterTemps() {
	   return arreterTemps;
   }
   
   
	/**
	 * Methode qui retourne la longueur du panel
	 * @return longueur
	 */
   //Roger
	 public double getLongeur() {
		return this.LONGUEUR_DU_MONDE;
		 
	 }
	 /**
	  * Methode qui retourne la largeur du panel
	 * @return largeur
	  */
	 //Roger
	 public double getLargeur() {
			return this.LARGEUR_DU_MONDE;
			 
		 }
	 public void setEnnemie(boolean b) {
		 
		 this.ennemie= b;
	 }
}

