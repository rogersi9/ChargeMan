package Affichage;

import java.awt.BasicStroke;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import Niveau.Niveau;
import ecouteurs.EcouteurAnimation;
import ecouteurs.EcouteursFinNiveau;
import geometrie.Personnage;
import geometrie.Segments;
import geometrie.Vecteur;
import moteur.ForceElectrique;

import obstacles.ParticuleObstacles;
import obstacles.PointArrive;

/**
 * La classe ou l'animation vas se derouler
 * 
 * @author Clement
 * @author Roger
 */
public class ZoneAnimation extends JPanel implements Runnable {
	private Niveau niv;
	private boolean estEnAnimation;
	private final double LARGEUR_DU_MONDE = 100;
	private final double LONGUEUR_DU_MONDE = 80;
	private boolean gagne=true;
	private ModeleAffichage modele = new ModeleAffichage(getWidth(), getHeight(), LARGEUR_DU_MONDE);
	AffineTransform matMC = modele.getMatMC();
	private int deltaT =10;
	private double temps;
	private boolean forceADroite=false;
	private boolean forceAGauche=false;
	private boolean forceEnBas=false;
	private boolean forceEnHaut=false;
	private Vecteur forceAppliquee= new Vecteur(0,0);
	private  ArrayList <Segments> tot_segment = new ArrayList<Segments>();
	private Personnage personnage;
	private Vecteur forceElectriques= new Vecteur(0,0);
	private Vecteur forceResultante= new Vecteur(0,0);
	private Vecteur forceGraviter= new Vecteur(0,0);
	private Vecteur positionInitial;


	private String texte= "Gagne";
	private PanelJeu p;

	private  ArrayList<EcouteurAnimation> ecouteurAnimation= new ArrayList<EcouteurAnimation>();
	private ArrayList<EcouteursFinNiveau> ecouteurFinNiveau = new ArrayList<EcouteursFinNiveau>();


	//Nouhaila
	Musique musiqueDeFond = new Musique("MusiqueDeFond.wav");	
	Musique musiquePerdre = new Musique("MusiqueDeFin.wav");	
	Musique musiqueGagner= new Musique("0812.wav");
	ForceElectrique forceElectrique;
	boolean estPositif ;
	Timer timer ;
	int sec = 10;
	boolean posFinal = false;

	//par Clement
	/**
	 * Constructeur de la zoneAnimation qui prend en paramettre un niveau quelconque
	 * 
	 * @param niv
	 */

	public ZoneAnimation(Niveau niv ) {



		this.niv=niv;
		forceGraviter=niv.getForceGravite().multiplie(niv.getPersonnage().getMasseEnKg());
		this.estEnAnimation=false;
		this.personnage=niv.getPersonnage();
		trouverSegement();
		Color n = new Color(217, 217, 217);
		setBackground(n);
		positionInitial=new Vecteur (personnage.getPosition().getX(),personnage.getPosition().getY());
		System.out.println(positionInitial);
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(estEnAnimation) {
					int code = e.getKeyCode();
					switch (code) {
					case KeyEvent.VK_LEFT:

						forceAGauche=true;
						break;

					case KeyEvent.VK_RIGHT:

						forceADroite=true;
						break;

					case KeyEvent.VK_UP:

						forceEnHaut=true;
						break;

					case KeyEvent.VK_DOWN:

						forceEnBas=true;
						break;		
					}// fin switch
				}
			}
		});

		repaint();

	}//fin constructeur
	//par Clement

	/**
	 * Methode utilisee pour faire l'animation du jeu
	 */
	@Override
	public void run() {
		while(estEnAnimation) {
			
			System.out.println(niv.getPersonnage().getCharge());
			positionFinale(niv.getPointArrive(), niv.getPersonnage()  );

			forceAppliquee = new Vecteur(0,0);



			orienterPersonnage();
			TempsEcoule();




			if (forceAGauche) {
				forceAppliquee=forceAppliquee.additionne(new Vecteur (-1*niv.getForceParClick(),0));
				forceAGauche=false;
			}
			if (forceADroite) {
				forceAppliquee=forceAppliquee.additionne(new Vecteur (1*niv.getForceParClick(),0));
				forceADroite=false;
			}
			if (forceEnHaut) {
				forceAppliquee=forceAppliquee.additionne(new Vecteur (0,-1*niv.getForceParClick()));
				forceEnHaut=false;
			}
			if (forceEnBas) {
				forceAppliquee=forceAppliquee.additionne(new Vecteur (0,1*niv.getForceParClick()));
				forceEnBas=false;
			}
			temps=temps+deltaT/1000.0;
			System.out.println(temps);

			//detecter si colision et calculer le nouveau vector et trouver le vecteur de la nouvelle vitesse
			//niv.getPersonnage().setVitesse(vitesseSuite¿LaCollision);
			//detecter si il est attirer ou repouser par une particule obstacle‡




			forceResultante= niv.getForceGravite().multiplie(personnage.getMasseEnKg()).additionne(forceAppliquee).additionne(forceElectriqueResultante());//.additionne(forceClick)
			niv.getPersonnage().setAccel(forceResultante.multiplie(1/personnage.getMasseEnKg()));
			niv.getPersonnage().unPasEuler(deltaT/1000.0);
			leverMouvementPerso();
			repaint();

			try {
				Thread.sleep(deltaT);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		} 	

	} 	

	//par Clement
	/**
	 * Methode qui demarre l'animation
	 */
	public void demarrer() {
		estEnAnimation=true;	
		Thread processusAnim = new Thread(this);
		processusAnim.start();
	}//fin methode



	//par Clement
	/**
	 * Methode qui arrete l'animation
	 */
	public void arreter() {
		estEnAnimation=false;

	}//fin methode

	private void dessinerObstacles(Graphics2D g2d, AffineTransform matMC) {
		for(int i=0; i<niv.getObstacles().size(); i++) {
			niv.getObstacles().get(i).dessiner(g2d, matMC);
		}

	}
	//par Clement
	/**
	 * methode permetant de faire une itÈration d'euler
	 */
	public void unPas () {
		System.out.println(niv.getPersonnage().getCharge());

		forceAppliquee = new Vecteur(0,0);



		orienterPersonnage();





		if (forceAGauche) {
			forceAppliquee=forceAppliquee.additionne(new Vecteur (-1*niv.getForceParClick(),0));
			forceAGauche=false;
		}
		if (forceADroite) {
			forceAppliquee=forceAppliquee.additionne(new Vecteur (1*niv.getForceParClick(),0));
			forceADroite=false;
		}
		if (forceEnHaut) {
			forceAppliquee=forceAppliquee.additionne(new Vecteur (0,-1*niv.getForceParClick()));
			forceEnHaut=false;
		}
		if (forceEnBas) {
			forceAppliquee=forceAppliquee.additionne(new Vecteur (0,1*niv.getForceParClick()));
			forceEnBas=false;
		}
		temps=temps+deltaT/1000.0;
		System.out.println(temps);

		//detecter si colision et calculer le nouveau vector et trouver le vecteur de la nouvelle vitesse
		//niv.getPersonnage().setVitesse(vitesseSuite¿LaCollision);
		//detecter si il est attirer ou repouser par une particule obstacle‡




		forceResultante= niv.getForceGravite().multiplie(personnage.getMasseEnKg()).additionne(forceAppliquee).additionne(forceElectriqueResultante());//.additionne(forceClick)
		niv.getPersonnage().setAccel(forceResultante.multiplie(1/personnage.getMasseEnKg()));
		niv.getPersonnage().unPasEuler(deltaT/1000.0);
		leverMouvementPerso();
		repaint();
	}


	//par Clement
	/**
	 * Methode prenant en parametre un niveau et qui change le niveau de la zone animation et par se fait meme arrete l'animation
	 * @param niv
	 */
	public void setNiveau(Niveau niv) {

		forceElectriques= new Vecteur(0,0);
		forceResultante= new Vecteur(0,0);
		estEnAnimation=false;
		temps=0;
		personnage=niv.getPersonnage();
		this.niv=niv;
		positionInitial=new Vecteur (personnage.getPosition().getX(),personnage.getPosition().getY());
		forceGraviter=niv.getForceGravite().multiplie(niv.getPersonnage().getMasseEnKg());
		trouverSegement();
		repaint();
	}

	public Niveau getNiveau() {
		return (niv);
	}


	public double getMatMCY() {
		return this.matMC.getScaleX();

	}

	/**
	 * Methode pour mettre tout les segment des obstacle dans une liste 
	 * 
	 */
	// par Roger
	public void trouverSegement() {

		tot_segment.clear();
		Point2D.Double coin_1 = new 	Point2D.Double(0,0);
		Point2D.Double coin_2 = new 	Point2D.Double(LARGEUR_DU_MONDE,0);
		Point2D.Double coin_3 = new 	Point2D.Double(LARGEUR_DU_MONDE,LONGUEUR_DU_MONDE);
		Point2D.Double coin_4 = new 	Point2D.Double(0,LONGUEUR_DU_MONDE);


		Segments c1 =  new Segments(coin_1,coin_2);
		Segments c2 =  new Segments(coin_2,coin_3);
		Segments c3 =  new Segments( coin_3,coin_4);
		Segments c4=  new Segments(coin_4,coin_1);

		tot_segment.add(c1);
		tot_segment.add(c2);
		tot_segment.add(c3);
		tot_segment.add(c4);



		for(int i=0;i< niv.getObstacles().size();i++) {
			// tot_segment.addAll( niv.getObstacles().get(i).getSegment());



			if(  !niv.getObstacles().get(i).estCharge() && !niv.getObstacles().get(i).isTriangle()) {

				double x = niv.getObstacles().get(i).getPosition().getX()-0.1;
				double y = 	 niv.getObstacles().get(i).getPosition().getY()-0.1;

				double largeur = niv.getObstacles().get(i).getLargeur()-0.1;
				double hauteur = niv.getObstacles().get(i).getLongueur()-0.1;

				Point2D.Double p1= new 	Point2D.Double(x,y);
				Point2D.Double p2= new 	Point2D.Double(x+largeur,y);
				Point2D.Double p3= new 	Point2D.Double(x+largeur,y+hauteur);
				Point2D.Double p4= new 	Point2D.Double(x,y+hauteur);

				Segments s1 =  new Segments(p1,p2);
				Segments s2 =  new Segments(p2,p3);
				Segments s3 =  new Segments(p3,p4);
				Segments s4=  new Segments(p4,p1);
				tot_segment.add(s1);
				tot_segment.add(s2);
				tot_segment.add(s3);
				tot_segment.add(s4);
			}else if(!niv.getObstacles().get(i).estCharge() && niv.getObstacles().get(i).isTriangle()) {

				double x = niv.getObstacles().get(i).getPosition().getX();
				double y = niv.getObstacles().get(i).getPosition().getY();

				Point2D.Double p1= new 	Point2D.Double(x,y ) ;
				Point2D.Double p2= new 	Point2D.Double((x+6),  (y-8));
				Point2D.Double p3= new 	Point2D.Double((x+12) ,y);

				Segments s1 =  new Segments(p1,p2);
				Segments s2 =  new Segments(p2,p3);
				Segments s3 =  new Segments(p3,p1);

				tot_segment.add(s1);
				tot_segment.add(s2);
				tot_segment.add(s3);

			}






		}



	}

	/**
	 * Methode qui permet de trouver le vecteur vitesse resultant apres une collision
	 * @param orientation_rayon_incident vitesse initiale du personnage
	 * @param orientation_normal  vecteur normal au segment 
	 * @return vitesse finale 
	 * par Roger
	 */
	public  static Vecteur orientationCollision(Vecteur orientation_rayon_incident , Vecteur orientation_normal){



		Vecteur e = orientation_rayon_incident.multiplie(-1);   //orientation inverse du rayon incident

		return (orientation_normal.multiplie(e.prodScalaire(orientation_normal)).multiplie(2)).additionne(orientation_rayon_incident);




	}

	/**
	 * Methode qui permet de reorienter le personnage avec la nouvelle vitesse suite ala collision
	 */
	// par Roger
	public void orienterPersonnage() {

		for(int i=0;i< tot_segment.size();i++) {

			if(tot_segment.get(i).enCollision(niv.getPersonnage())  ) {

				niv.getPersonnage().setVitesse(  orientationCollision(niv.getPersonnage().getVitesse()  ,tot_segment.get(i).getNormale()  ));


				System.out.println("collision");
			}

		}

	}

	public boolean getEstEnAnimation() {
		return estEnAnimation;
	}
	/**
	 * La methode qui detecte quand l'utilisateur gagne le jeu 
	 * @param a le cercle du point d'arrive
	 * 
	 * @param p le personnage
	 */
	// Roger
	public void positionFinale(PointArrive a, Personnage p  ) {
		double xa = a.getCenterX();
		double ya = a.getCenterY();

		double xp= p.getX();
		double yp = p.getY();

		double rayon_a = a.getDiametre()/2 +3;
		double rayon_p = p.getDiametre()/2;



		double distance = Math.sqrt( ((xa-xp)*(xa-xp)) +((ya-yp)*(ya-yp)) );

		if((rayon_a >= ( distance + rayon_p))  ) {

			//	fin.setLabel("BRAVOO Vous avez reussi ce niveau dans " +String.valueOf(tempsPris()) +" secondes");
			// debut junior
			FinDuJeu fin = new FinDuJeu("Bravoo !! Vous avez reussi ce niveau en " +  String.valueOf(tempsPris()) +" secondes") ;
			arreter();
			leverRecommencerNiveau();
			musiqueGagner.jouerMusique();     
			fin.NewScreen();
			leverretourMenu();
		}else {			

			if(niv.getTempsMax()==0) {
				FinDuJeu fin = new FinDuJeu("Le temps est termin\u00E9. Vous avez perdu ce niveau !! ") ;

				arreter();
				leverRecommencerNiveau();
				musiquePerdre.jouerMusique();  
				fin.NewScreen();
				leverretourMenu();
				// fin junior
			}
		}
	}


	/**
	 * Methode qui calcule la valeur eletrique resultante
	 * @return vecResultant represente le vecteur force electrique resultante
	 */
	//Nouhaila
	public Vecteur forceElectriqueResultante() {

		Vecteur vecForceElec = new Vecteur(0,0);
		Vecteur vecResultant = new Vecteur(0,0);

		for(int i = 0 ; i < niv.getObstacles().size() ; i++ ) {

			if( niv.getObstacles().get(i).estCharge()) {

				ParticuleObstacles particuleObstacle = (ParticuleObstacles) niv.getObstacles().get(i);

				estPositif = particuleObstacle.getchargeParticuleEstPositif();
				forceElectrique = new ForceElectrique(niv.getPersonnage(), particuleObstacle /**, estPositif**/,niv.getChargeObst() );

				vecForceElec = forceElectrique.forceElecVecteur() ;

				vecResultant = vecForceElec.additionne(vecResultant);
			}

		}
		forceElectriques=vecResultant;

		System.out.print("VECTEUR RESULTANT ELECTRIQUE : "  + vecResultant);

		return vecResultant;

	}

	

	/**
	 * Methode utilisee pour dessiner le niveau lors de l'animation
	 */
	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;    
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

		modele = new ModeleAffichage(getWidth(), getHeight(), LARGEUR_DU_MONDE);
		matMC = modele.getMatMC();

		g2d.setColor(Color.blue);

		niv.getPersonnage().dessiner(g2d, matMC);
		dessinerObstacles(g2d, matMC);
		niv.getPointArrive().dessiner(g2d, matMC);
		
		requestFocusInWindow();


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

	}



	/**Methode pour arreter l'animation 
	 * 
	 * Roger
	 */
	public void pause() {
		estEnAnimation=false;
	}

	//Nouhaila
	public void setTemps(int sec) {
		niv.setTempsMax(sec);
		repaint();

	}

	//Nouhaila
	public int getTemps() {
		return niv.getTempsMax();
	}

	//Nouhaila
	public boolean getTimerArreter() {
		return niv.getTimerArreter();
	}


	//Nouhaila
	public void setTimerArretr( boolean arreterTemps) {
		niv.setTimerArreter(arreterTemps);
	}

	//Nouhaila
	public void setAfficherVecteur( boolean afficherVecteur) {
		niv.setTimerArreter(afficherVecteur);
	}

	//Nouhaila
	public int tempsPris() {
		int tmps = niv.getTempsMaximum();
		int tempsPris = tmps - getTemps();
		return tempsPris;
	}


	//Nouhaila
	public boolean getAfficherVecteur() {
		return niv.getAfficherVecteur();
	}

	//Nouhaila
	public Vecteur getForceElectriques() {
		return forceElectriques;
	}





	public double getLongeur() {
		return this.LONGUEUR_DU_MONDE;

	}
	public double getLargeur() {
		return this.LARGEUR_DU_MONDE;

	}
	/**Methode qui arrete le jeu quand le timer est termine 
	 * 
	 */
	//Par Roger
	public void TempsEcoule() {
		if(getTemps()==0) {
			arreter();
			//fin.setLabel("  Le temps est ecoule!!  Essayez encore une fois . ");



		}

	}
	//Clement
	/**
	 * Methode qui ajoute l'ecouteur personnaliser
	 * @param ecouteur
	 */
	public void addEcouteurAnimation(EcouteurAnimation ecouteur) {
		ecouteurAnimation.add(ecouteur);
	}
	//Clement
	/**
	 * Methode qui envoie de l'information lorsque levent est lever
	 * @param ecouteur
	 */
	private void leverMouvementPerso() {
		for(EcouteurAnimation ecout : ecouteurAnimation) {
			ecout.mouvementPerso(personnage, forceElectriques,forceResultante );
		}
	}

	// Fin Clement
	// junior
	public void addEcouteurFinNiveau(EcouteursFinNiveau ecouteur) {
		ecouteurFinNiveau.add(ecouteur);
	}

	private void leverRecommencerNiveau() {
		for(EcouteursFinNiveau ecout : ecouteurFinNiveau) {
			ecout.recommencerNiveau();
		}
		}
		private void leverretourMenu() {
			for(EcouteursFinNiveau ecouts : ecouteurFinNiveau) {
				ecouts.retourMenu();
			}
	}
	

	//Clement
	/**
	 * Methode recommence le niveau du dÈbut
	 * @param ecouteur
	 */
	public void reset() {
		//niv.getTempsMaximum()
		//recommencer le timer (a faire)
		niv.setTempsMax(niv.getTempsMax());
		estEnAnimation = false;
		forceElectriques= new Vecteur(0,0);
		personnage.setVitesse(new Vecteur(0,0));
		Vecteur forceResultante= new Vecteur(0,0);
		System.out.println(positionInitial);
		personnage.setPosition(positionInitial);
		forceADroite=false;
		forceAGauche=false;
		forceEnBas=false;
		forceEnHaut=false;
		leverMouvementPerso();



		repaint();
	}



	//Nouhaila
	public void setVecteurVitesse(boolean afficherVec) {
		personnage.afficherVecteurVitesse(afficherVec);
		repaint();
	}
	
	//Nouhaila
	public int getTempsInitial(){
		return niv.getTempsMaximum();
	}
	
	public Vecteur getForceGraviter(){
		return forceGraviter;
	}

	public Vecteur getForceResultante() {

		return forceResultante;
	}
	/**
	 * La methode qui arrete le jeu quand le mechant arrive au personnage
	 * @param p personnage du jeu 
	 * @param m mechant du jeu 
	 */
	//Roger
	
	/*  public void tuerPersonnage(Personnage p , Mechants m ) {
	
		double xm = m.getCenterX();
		double ym = m.getCenterY();



		double xp= p.getX();
		double yp = p.getY();



		double rayon_m = m.getDiametre()/2 ;
		double rayon_p = p.getDiametre()/2+2;




		double distance = Math.sqrt( ((xm-xp)*(xm-xp)) +((ym-yp)*(ym-yp)) );



		if((rayon_p >= ( distance + rayon_m))  ) {
			arreter();
			JOptionPane.showMessageDialog(null, "Vous avez perdu!!! Clickez sur Ok pour plus d'options");
			// junior
			leverretourMenu();
			FinDuJeu fin = new FinDuJeu("Le temps est termin\u00E9. Vous avez perdu ce niveau !! ") ;

			arreter();
			leverRecommencerNiveau();
			musiquePerdre.jouerMusique();  
			fin.NewScreen();
			leverretourMenu();

		}

	} */


}//fin classe