package Affichage;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import Niveau.GestionaireFichierNiveau;
import Niveau.Niveau;
import aaplication.GuideUtilisateur;
import ecouteurs.EcouteurAnimation;
import ecouteurs.EcouteursFinNiveau;
import geometrie.Personnage;
import geometrie.Vecteur;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 * Cette classe reprsente le premier niveau du mode aventure du jeu.
 * @author Junior Peumi
 * @author Nouhaila Aater
 * @author Clement
 * @author Roger
 *
 */
public class PanelJeu {
	private JFrame frameJeu;
	ZoneAnimation zoneAnimation;
	ZoneEditeur editeur ;
	private JLabel lblValeurPosition,lblNiveau, lblForceResultante,lblForceElectrique,label_12,label_10,label_8,label_6,label_4,label_2,lblNewLabel,label;
	private JButton btnPpause,btnNext,buttonStart ;
	
	
	//Nouhaila
	Timer timer ;
	int sec;
	Musique musiqueDeFond = new Musique("MusiqueDeFond.wav");
	Musique musiquePerdre = new Musique("j_perdu2.wav");	



	// par Junior Peumi
	/*
	 * Mthode qui cr et qui met visible le frameJeu1, tant la fentre ddie au 1er niveau du jeu.
	 */	
	public void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PanelJeu window = new PanelJeu(zoneAnimation);
					window.frameJeu.setVisible(true);
				} catch (Exception e){
					e.printStackTrace();
				}
			}
		});
	}


	// par Junior Peumi
	/**
	 * Mthode qui instancie l'interface du panneau du premier niveau du jeu.
	 */
	public PanelJeu(ZoneAnimation zoneAnimation) {
		this.zoneAnimation = zoneAnimation;
		initialize();
	}

	// par Junior Peumi 
	/**
	 * Methode qui initialise les lments faisant partie de l'interface du panneau du premier niveau du jeu (JLabel, JButton et etc...).
	 */
	private void initialize() {
		frameJeu = new JFrame();
		frameJeu.setResizable(false);
		frameJeu.getContentPane().setBackground(new Color(243,128,132));
		frameJeu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameJeu.setBounds(100, 100, 1500, 900);

		frameJeu.setBackground(new Color(204, 204, 255));
		frameJeu.getContentPane().setLayout(null);

		JPanel panelAffichage = new JPanel();
		panelAffichage.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, null, null, null));
		panelAffichage.setBackground(new Color (241, 250, 238));
		panelAffichage.setBounds(876, 79, 408, 670);
		frameJeu.getContentPane().add(panelAffichage);
		panelAffichage.setLayout(null);

		JLabel lblVitesse = new JLabel("La Vitesse");
		lblVitesse.setFont(new Font("Century Gothic", Font.BOLD, 12));
		lblVitesse.setBounds(18, 140, 102, 38);
		panelAffichage.add(lblVitesse);

		JLabel lblAcceleration = new JLabel("Acceleration");
		lblAcceleration.setFont(new Font("Century Gothic", Font.BOLD, 12));
		lblAcceleration.setBounds(18, 185, 102, 38);
		panelAffichage.add(lblAcceleration);

		JLabel lblChargePerso = new JLabel("Charge du presonnage");
		lblChargePerso.setFont(new Font("Century Gothic", Font.BOLD, 12));
		lblChargePerso.setBounds(18, 230, 160, 46);
		panelAffichage.add(lblChargePerso);

		JLabel lblMassePerso = new JLabel("Masse du personnage");
		lblMassePerso.setFont(new Font("Century Gothic", Font.BOLD, 12));
		lblMassePerso.setBounds(18, 275, 157, 46);
		panelAffichage.add(lblMassePerso);

		JLabel lblForceApplique = new JLabel("Force appliqu\u00E9 par click");
		lblForceApplique.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblForceApplique.setBounds(18, 320, 157, 38);
		panelAffichage.add(lblForceApplique);

		JLabel lblForcegravite = new JLabel("Force gravitationnelle");
		lblForcegravite.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblForcegravite.setBounds(18, 365, 137, 38);
		panelAffichage.add(lblForcegravite);


		JLabel lblRayonAction = new JLabel("Rayon d'action");
		lblRayonAction.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblRayonAction.setBounds(18, 410, 123, 46);
		panelAffichage.add(lblRayonAction);

		JLabel lblChargeParticule = new JLabel("Charge de la particule");
		lblChargeParticule.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblChargeParticule.setBounds(18, 455, 157, 46);
		panelAffichage.add(lblChargeParticule);

		JLabel lblComposanteForce = new JLabel("Force resultante");
		lblComposanteForce.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblComposanteForce.setBounds(18, 545, 157, 52);
		panelAffichage.add(lblComposanteForce);

		label = new JLabel(zoneAnimation.getNiveau().getPersonnage().getVitesse().toString());
		label.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		label.setBounds(160, 153, 220, 14);
		panelAffichage.add(label);

		JLabel lblMs = new JLabel("m/s");
		lblMs.setFont(new Font("Century", Font.PLAIN, 14));
		lblMs.setBounds(365, 152, 27, 14);
		panelAffichage.add(lblMs);

		lblNewLabel = new JLabel(zoneAnimation.getNiveau().getPersonnage().getAccel().toString());
		lblNewLabel.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		lblNewLabel.setBounds(160, 197, 220, 14);
		panelAffichage.add(lblNewLabel);

		JLabel lblMs_1 = new JLabel("m/s");
		lblMs_1.setFont(new Font("Century", Font.PLAIN, 14));
		lblMs_1.setBounds(365, 197, 46, 14);
		panelAffichage.add(lblMs_1);


		label_2 = new JLabel(Double.toString(zoneAnimation.getNiveau().getPersonnage().getCharge()));
		label_2.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		label_2.setBounds(220, 246, 148, 14);
		panelAffichage.add(label_2);

		JLabel lblC = new JLabel("C");
		lblC.setFont(new Font("Century", Font.PLAIN, 14));
		lblC.setBounds(365, 246, 18, 14);
		panelAffichage.add(lblC);
		
		label_4 = new JLabel(Double.toString(zoneAnimation.getNiveau().getPersonnage().getMasseEnKg()));
		label_4.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		label_4.setBounds(220, 294, 160, 14);
		panelAffichage.add(label_4);

		JLabel lblKg = new JLabel("kg");
		lblKg.setFont(new Font("Century", Font.PLAIN, 14));
		lblKg.setBounds(365, 291, 46, 14);
		panelAffichage.add(lblKg);

		label_6 = new JLabel(Double.toString(zoneAnimation.getNiveau().getForceParClick()));
		label_6.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		label_6.setBounds(180, 333, 160, 14);
		panelAffichage.add(label_6);

		JLabel lblN = new JLabel("N/0.01s");
		lblN.setFont(new Font("Century", Font.PLAIN, 14));
		lblN.setBounds(345, 333, 53, 14);
		panelAffichage.add(lblN);
		
				JButton btnRetourAuMenu = new JButton("Retour au menu");
				btnRetourAuMenu.setBounds(0, 0, 163, 43);
				panelAffichage.add(btnRetourAuMenu);
				btnRetourAuMenu.setForeground(Color.RED);
				btnRetourAuMenu.setBackground(new Color(241, 250, 238));
				btnRetourAuMenu.setFont(new Font("Tahoma", Font.BOLD, 14));
				btnRetourAuMenu.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						zoneAnimation.arreter();
						retourAuMenu();
					}
				});

		label_8 = new JLabel(zoneAnimation.getNiveau().getForceGravite().multiplie(zoneAnimation.getNiveau().getPersonnage().getMasseEnKg()).toStringPetitNombres());
		label_8.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		label_8.setBounds(220, 378, 160, 14);
		panelAffichage.add(label_8);

		JLabel lblN_1 = new JLabel("N*10");
		lblN_1.setFont(new Font("Century", Font.PLAIN, 14));
		lblN_1.setBounds(358, 378, 72, 14);
		panelAffichage.add(lblN_1);

		label_10 = new JLabel(Double.toString(zoneAnimation.getNiveau().getPersonnage().getRayonAction()));
		label_10.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		label_10.setBounds(220, 429, 148, 14);
		panelAffichage.add(label_10);

		JLabel lblM = new JLabel("m");
		lblM.setFont(new Font("Century", Font.PLAIN, 14));
		lblM.setBounds(365, 425, 46, 14);
		panelAffichage.add(lblM);

		label_12 = new JLabel(Double.toString(zoneAnimation.getNiveau().getChargeObst()));
		label_12.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		label_12.setBounds(220, 472, 138, 14);
		panelAffichage.add(label_12);

		JLabel lblC_1 = new JLabel("C");
		lblC_1.setFont(new Font("Century", Font.PLAIN, 14));
		lblC_1.setBounds(365, 468, 46, 14);
		panelAffichage.add(lblC_1);

		JLabel lblN_2 = new JLabel("N*10");
		lblN_2.setFont(new Font("Century", Font.PLAIN, 14));
		lblN_2.setBounds(358, 517, 72, 14);
		panelAffichage.add(lblN_2);

		JLabel labelTempsEcouler = new JLabel("TEMPS :");
		labelTempsEcouler.setBounds(18, 40, 108, 52);
		panelAffichage.add(labelTempsEcouler);
		labelTempsEcouler.setFont(new Font("Century", Font.PLAIN, 20));
		labelTempsEcouler.setBackground(Color.BLACK);
		labelTempsEcouler.setForeground(Color.DARK_GRAY);



		JLabel labelTemps = new JLabel(Integer.toString(zoneAnimation.getTemps()));
		labelTemps.setBounds(115, 55, 40, 23);
		panelAffichage.add(labelTemps);
		labelTemps.setFont(new Font("Century", Font.PLAIN, 20));

		JLabel lblS = new JLabel("S");
		lblS.setBounds(220, 45, 18, 43);
		panelAffichage.add(lblS);
		lblS.setFont(new Font("Century", Font.PLAIN, 20));

		//Nouhaila Aater
		JCheckBox chckbxNoTimer = new JCheckBox("no timer");
		chckbxNoTimer.setBackground((panelAffichage.getBackground()));
		chckbxNoTimer.setBounds(6, 640, 97, 23);
		panelAffichage.add(chckbxNoTimer);
		chckbxNoTimer.setFont(new Font("Tahoma", Font.BOLD, 14));


		//Nouhaila
		JCheckBox chckbxAfficherVecteurResultant = new JCheckBox("Afficher le vecteur resultant");
		chckbxAfficherVecteurResultant.addActionListener(new ActionListener() {
			/**
			 * Permet d'afficher de vecteur vitesse si la case est cochée
			 */
			//Nouhaila
			public void actionPerformed(ActionEvent arg0) {

				if (chckbxAfficherVecteurResultant.isSelected()) {
					zoneAnimation.setVecteurVitesse(true);
				} else {
					zoneAnimation.setVecteurVitesse(false);
				}
			}
		}); // fin nouhaila
		chckbxAfficherVecteurResultant.setBackground((panelAffichage.getBackground()));
		chckbxAfficherVecteurResultant.setBounds(6, 616, 236, 23);
		panelAffichage.add(chckbxAfficherVecteurResultant);
		chckbxAfficherVecteurResultant.setFont(new Font("Tahoma", Font.BOLD, 12));

		JLabel lblPositionDuPersonnage = new JLabel("Position du personnage");
		lblPositionDuPersonnage.setFont(new Font("Century Gothic", Font.BOLD, 12));
		lblPositionDuPersonnage.setBounds(18, 95, 160, 38);
		panelAffichage.add(lblPositionDuPersonnage);

		JLabel lblM_1 = new JLabel("m");
		lblM_1.setFont(new Font("Century", Font.PLAIN, 14));
		lblM_1.setBounds(365, 108, 27, 14);
		panelAffichage.add(lblM_1);

		JLabel lblValeurPosition = new JLabel(zoneAnimation.getNiveau().getPersonnage().getCentreVecteur().toString());
		lblValeurPosition.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		lblValeurPosition.setBounds(178, 110, 222, 14);
		panelAffichage.add(lblValeurPosition);

		JLabel lblForcelectrique = new JLabel("Force \u00E9lectrique");
		lblForcelectrique.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblForcelectrique.setBounds(18, 500, 157, 46);
		panelAffichage.add(lblForcelectrique);

		lblForceElectrique = new JLabel(zoneAnimation.getForceElectriques().toStringPetitNombres());
		lblForceElectrique.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		lblForceElectrique.setBounds(130, 517, 215, 14);
		panelAffichage.add(lblForceElectrique);

		JLabel lblForceResultante = new JLabel(zoneAnimation.getForceResultante().toStringPetitNombres());
		lblForceResultante.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		lblForceResultante.setBounds(130, 563, 228, 14);
		panelAffichage.add(lblForceResultante);

		JLabel lblN_3 = new JLabel("N*10");
		lblN_3.setFont(new Font("Century", Font.PLAIN, 14));
		lblN_3.setBounds(358, 565, 72, 14);
		panelAffichage.add(lblN_3);
		
		JLabel lblNewLabel_1 = new JLabel("2");
		lblNewLabel_1.setFont(new Font("Century", Font.PLAIN, 9));
		lblNewLabel_1.setBounds(388, 187, 46, 14);
		panelAffichage.add(lblNewLabel_1);
		
		JLabel label_1 = new JLabel("-26");
		label_1.setFont(new Font("Century", Font.PLAIN, 9));
		label_1.setBounds(375, 504, 46, 14);
		panelAffichage.add(label_1);
		
		JLabel label_3 = new JLabel("-26");
		label_3.setFont(new Font("Century", Font.PLAIN, 9));
		label_3.setBounds(375, 550, 46, 14);
		panelAffichage.add(label_3);






		String nbNiveau = zoneAnimation.getNiveau().getNom().substring(zoneAnimation.getNiveau().getNom().length() - 1);
		JLabel lblNiveau = new JLabel("NIVEAU " + nbNiveau);
		lblNiveau.setForeground(Color.BLACK);
		lblNiveau.setFont(new Font("Century", Font.BOLD, 35));
		lblNiveau.setBounds(335, 11, 382, 57);
		frameJeu.getContentPane().add(lblNiveau);




		buttonStart = new JButton("Start");
		buttonStart.setForeground(Color.BLACK);
		buttonStart.setBackground(new Color(241, 250, 238));
		buttonStart.setFont(new Font("Tahoma", Font.BOLD, 14));


		buttonStart.addActionListener(new ActionListener() {


		/**
		 * Controle le temps tels qu'un compte à rebours
		 * @author nouha
		 */
			//Nouhaila
			public void actionPerformed(ActionEvent arg0) {
				buttonStart.setEnabled(false);
				btnPpause.setEnabled(true);
				btnNext.setEnabled(false);
				sec = zoneAnimation.getTemps();
				timer = new Timer (1000, new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						if(!chckbxNoTimer.isSelected()) {

							if(sec==0 || zoneAnimation.getTimerArreter()) {

								timer.stop();

								zoneAnimation.arreter() ;
							} else {
								zoneAnimation.setTemps( sec-1);
								sec = zoneAnimation.getTemps();
							}
							labelTemps.setText(""+sec);}
					}
				});
				start();
			}
		}); // fin nouhaila
		buttonStart.setFocusable(false);
		buttonStart.setBounds(113,750, 116, 90);
		frameJeu.getContentPane().add(buttonStart);
		//Clement
		zoneAnimation.setBounds(20, 79, 846, 670);
		frameJeu.getContentPane().add(zoneAnimation);
		zoneAnimation.setFocusable(true);


		btnPpause = new JButton("Pause");
		btnPpause.setForeground(Color.BLACK);
		btnPpause.setBackground(new Color(241, 250, 238));
		btnPpause.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnPpause.addActionListener(new ActionListener() {
			//par Clement
			/**
			 * Methode mettant fin a l'animation
			 */
			public void actionPerformed(ActionEvent arg0) {
				btnPpause.setEnabled(false);
				buttonStart.setEnabled(true);
				btnNext.setEnabled(true);
				
				//Nouhaila
				if(!chckbxNoTimer.isSelected()) {
					timer.stop();
				}
				//fin Nouhaila
				pause();

			}
		});
		btnPpause.setBounds(30, 760, 73, 75);
		frameJeu.getContentPane().add(btnPpause);
		btnPpause.setEnabled(false);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalStrut.setBackground(new Color(0, 0, 0));
		horizontalStrut.setForeground(Color.BLACK);
		horizontalStrut.setBounds(23, 748, 860, 1);
		frameJeu.getContentPane().add(horizontalStrut);
		JCheckBox checkBoxHide = new JCheckBox("Cacher les sorties");

		//fin Clement


	//Nouhaila
		JCheckBox chckbxMusique = new JCheckBox("Musique");
		chckbxMusique.addActionListener(new ActionListener() {
			/**
			 * demarre la musique si le case est cochée
			 */
			//Nouhaila
			public void actionPerformed(ActionEvent e) {

				if(chckbxMusique.isSelected()) {
					musiqueDeFond.jouerMusique();
				}
				else {
					musiqueDeFond.arreterMusique();
				}



			}
		});
		chckbxMusique.setForeground(Color.BLACK);
		chckbxMusique.setBackground(new Color(241, 250, 238));
		chckbxMusique.setFont(new Font("Tahoma", Font.BOLD, 12));
		chckbxMusique.setBounds(876, 761, 97, 23);
		frameJeu.getContentPane().add(chckbxMusique);

		JCheckBox chckbxModeNuit = new JCheckBox("Mode nuit");
		chckbxModeNuit.addActionListener(new ActionListener() {


			/**
			 * Mode nuit
			 * @author 
			 */
			//Nouhaila
			public void actionPerformed(ActionEvent arg0) {

				if(chckbxModeNuit.isSelected()) {
					
					frameJeu.getContentPane().setBackground(new Color(29,53,87));
					panelAffichage.setBackground(new Color(168,218,220));
					btnPpause.setBackground(new Color(168,218,220));
					buttonStart.setBackground(new Color(168,218,220));
					chckbxModeNuit.setBackground(new Color(168,218,220));
					btnRetourAuMenu.setBackground(new Color(168,218,220));
					chckbxMusique.setBackground(new Color(168,218,220));
					checkBoxHide.setBackground(new Color(241, 250, 238));
					chckbxNoTimer.setBackground((panelAffichage.getBackground()));
					chckbxAfficherVecteurResultant.setBackground((panelAffichage.getBackground()));
					lblNiveau.setForeground(Color.white);
					

				}
				else {
				
					frameJeu.getContentPane().setBackground(new Color(243,128,132));
					panelAffichage.setBackground(new Color(241, 250, 238));
					btnPpause.setBackground(new Color(241, 250, 238));
					buttonStart.setBackground(new Color(241, 250, 238));
					btnRetourAuMenu.setBackground(new Color(241, 250, 238));
					chckbxModeNuit.setBackground(new Color(241, 250, 238));
					chckbxMusique.setBackground(new Color(241, 250, 238));
					checkBoxHide.setBackground(new Color(241, 250, 238));
					chckbxNoTimer.setBackground((panelAffichage.getBackground()));					
					chckbxAfficherVecteurResultant.setBackground((panelAffichage.getBackground()));
					lblNiveau.setForeground(Color.BLACK);
				}
			}
		});
		chckbxModeNuit.setBackground(new Color(241, 250, 238));
		chckbxModeNuit.setForeground(Color.BLACK);
		chckbxModeNuit.setFont(new Font("Tahoma", Font.BOLD, 12));
		chckbxModeNuit.setBounds(877, 787, 97, 23);
		frameJeu.getContentPane().add(chckbxModeNuit);

		btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				zoneAnimation.unPas();
			}
		});
		btnNext.setForeground(Color.BLACK);
		btnNext.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNext.setBackground(new Color(224, 255, 255));
		btnNext.setBounds(234, 774, 73, 50);
		frameJeu.getContentPane().add(btnNext);
		btnNext.setEnabled(false);

		associerBoutonAvecImage(buttonStart,"play.png ");
		associerBoutonAvecImage(btnPpause,"pause.png ");
		associerBoutonAvecImage(btnNext,"next.png ");
		
		JButton btnReset = new JButton("");
		btnReset.addActionListener(new ActionListener() {
			//par Clement
			/**
			 * Methode qui recommence le niveau
			 */
			public void actionPerformed(ActionEvent e) {
				
				//Nouhaila
				zoneAnimation.setTemps(zoneAnimation.getTempsInitial());
				int seconde =  zoneAnimation.getTemps();
				labelTemps.setText(""+seconde);
				timer.stop();
				//Fin Nouhaila
				zoneAnimation.reset();
				btnPpause.setEnabled(false);
				btnNext.setEnabled(false);
				buttonStart.setEnabled(true);
				
				
			}
		});
		btnReset.setBounds(341, 774, 62, 61);
		associerBoutonAvecImage(btnReset, "restart.png" );
		frameJeu.getContentPane().add(btnReset);

		/**
		 * Methode pour cacher les sorties dans le panel 
		 *
		 */
		// Roger
		checkBoxHide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkBoxHide.isSelected()) {

					panelAffichage.setVisible(false);
					zoneAnimation.setBounds(	zoneAnimation.getX()+180, zoneAnimation.getY(), zoneAnimation.getWidth(), zoneAnimation.getHeight());
					lblNiveau.setBounds(lblNiveau.getX() + 180, lblNiveau.getY(), lblNiveau.getWidth(), lblNiveau.getHeight());

					btnNext.setBounds(btnNext.getX() +180,btnNext.getY(),btnNext.getWidth(),btnNext.getHeight());
					btnPpause.setBounds(btnPpause.getX()+180 , btnPpause.getY(), btnPpause.getWidth(), btnPpause.getHeight());
					buttonStart.setBounds(buttonStart.getX() +180, buttonStart.getY(), buttonStart.getWidth(), buttonStart.getHeight());
					
					btnReset.setBounds(btnReset.getX() +180, btnReset.getY(), btnReset.getWidth(), btnReset.getHeight());

				}
				else {
					panelAffichage.setVisible(true);
					zoneAnimation.setBounds(zoneAnimation.getX()-180, zoneAnimation.getY(), zoneAnimation.getWidth(), zoneAnimation.getHeight());
					lblNiveau.setBounds(lblNiveau.getX() - 180, lblNiveau.getY(), lblNiveau.getWidth(), lblNiveau.getHeight());
					btnNext.setBounds(btnNext.getX() -180,btnNext.getY(),btnNext.getWidth(),btnNext.getHeight());
					btnPpause.setBounds(btnPpause.getX()-180 , btnPpause.getY(), btnPpause.getWidth(), btnPpause.getHeight());
					buttonStart.setBounds(buttonStart.getX() -180, buttonStart.getY(), buttonStart.getWidth(), buttonStart.getHeight());
					btnReset.setBounds(btnReset.getX() -180, btnReset.getY(), btnReset.getWidth(), btnReset.getHeight());
				}
			}
		});
		checkBoxHide.setForeground(Color.BLACK);
		checkBoxHide.setFont(new Font("Tahoma", Font.BOLD, 12));
		checkBoxHide.setBackground(new Color(241, 250, 238));
		checkBoxHide.setBounds(876, 811, 163, 23);
		frameJeu.getContentPane().add(checkBoxHide);
		
		JMenuBar menuBar = new JMenuBar();
		frameJeu.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Information");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Guide Utilisateur");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuideUtilisateur guideUtilisateur = new GuideUtilisateur();
				guideUtilisateur.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);

		//Clement
		/**
		 * Even personnalisé qui permet de changer les sortie afin de les voirs en temps réelle
		 */
		zoneAnimation.addEcouteurAnimation(new EcouteurAnimation() {
			public void mouvementPerso (Personnage perso, Vecteur forceElectrique, Vecteur forceResultante) {
				label.setText(perso.getVitesse().toString());
				lblNewLabel.setText(perso.getAccel().toString());
				lblForceElectrique.setText(forceElectrique.toStringPetitNombres());
				lblForceResultante.setText(forceResultante.toStringPetitNombres());
				lblValeurPosition.setText(perso.getCentreVecteur().toString());
			}

		});


		zoneAnimation.addEcouteurFinNiveau(new EcouteursFinNiveau() {
			@Override
			public void recommencerNiveau() {
				/*zoneAnimation.reset();
				labelTemps.setText(Integer.toString(zoneAnimation.getTemps()));*/
				frameJeu.dispose();
				zoneAnimation.arreter();
			}

			@Override
			public void retourMenu() {
				
				
			}

			
		});

	}

	//par Clement
	/**
	 * Methode qui met en pause le jeu
	 */
	private void pause() {

		if (zoneAnimation.getEstEnAnimation()) {
			zoneAnimation.arreter();

		}

	}
	/**
	 * Methode qui demarre ou reprend le jeu
	 */
	private void start() {
		if(!zoneAnimation.getEstEnAnimation()) {
			zoneAnimation.demarrer();
			zoneAnimation.requestFocusInWindow();
			timer.start();
		}
	}

	// par Junior Peumi
	/**
	 * Mthode qui permet  l'utilisateur de revenir au menu. 
	 * La mthode cr un PanelMenu, tant le panneau rserv au menu prinicpal.
	 *  l'aide de la mthode dispose() du frameJeu1, on peut fermer le frameJeu1 pour ensuite instancier le panneau menu avec la mthode NewScreen().
	 */
	private void retourAuMenu() {
		frameJeu.dispose();
		PanelMenu menu = new PanelMenu();
		menu.NewScreen();
		zoneAnimation.arreter();
	}

	public ZoneAnimation getZoneAnimation() {
		return (zoneAnimation);
	}
	/**
	 * Mthode qui permet d'associer une image a un bouton 
	 * @param leBouton
	 * @param fichierImage
	 * @author Caroline 
	 *///Roger
	public void associerBoutonAvecImage( JButton leBouton, String fichierImage ) {		
		Image imgLue=null;
		java.net.URL urlImage = getClass().getClassLoader().getResource(fichierImage);  
		if (urlImage == null) {
			JOptionPane.showMessageDialog(null , "Fichier " + fichierImage + " introuvable");
		} 
		try {   
			imgLue = ImageIO.read(urlImage);  
		} catch (IOException e) {  
			JOptionPane.showMessageDialog(null , "Erreur pendant la lecture du fichier d'image"); 
		}

		//redimensionner l'image de la mme grandeur que le bouton
		Image imgRedim = imgLue.getScaledInstance( leBouton.getWidth(),  leBouton.getHeight(), Image.SCALE_SMOOTH);

		//au cas o le fond de limage serait transparent
		leBouton.setOpaque(false);
		leBouton.setContentAreaFilled(false);
		leBouton.setBorderPainted(false);

		//associer l'image au bouton
		leBouton.setText("");
		leBouton.setIcon( new ImageIcon(imgRedim) );

		//on se dbarrasse des images intermdiaires
		imgLue.flush();
		imgRedim.flush();
	}
// par junior
	private Niveau accederNiveau(int nbNiveau) {
		GestionaireFichierNiveau gestionFich = new GestionaireFichierNiveau();
		Niveau niveau = gestionFich.lireFichierBinBuildPath("niveau" + nbNiveau);
		return niveau;
	}

	public void setZoneAnimation(ZoneAnimation zoneAnimationInput) {
		this.zoneAnimation = zoneAnimationInput;
	}
	public void setValue(){
		
	}
}

