package Affichage;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import Niveau.Niveau;
import aaplication.GuideUtilisateur;
import ecouteurs.EcouteurAnimation;
import ecouteurs.EcouteursFinNiveau;
import geometrie.Personnage;
import geometrie.Vecteur;
import javax.swing.JRadioButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 * Cette classe reprsente le premier niveau du mode aventure du jeu.
 * @author Junior Peumi
 * @author Nouhaila Aater
 * @author Clement
 *
 */
public class PanelNiveauPerso {
	private JFrame frameNiveauPerso;
	private ZoneAnimation zoneAnimation;
	private Timer timer;
	private Niveau niveau;
	private int sec;
	private Musique musiqueDeFond = new Musique("MusiqueDeFond.wav");
	final JFileChooser fc = new JFileChooser();
	private JLabel labelTemps;
	private JLabel lblValeurPosition,lblNiveau, lblForceResultante,lblForceElectrique,label_12,label_10,label_8,label_6,label_4,label_2,lblNewLabel,label;
	private JButton btnNext,btnPpause;


	// par Junior Peumi
	/*
	 * Mthode qui cr et qui met visible le frameJeu1, tant la fentre ddie au 1er niveau du jeu.
	 */	
	public void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PanelNiveauPerso window = new PanelNiveauPerso(zoneAnimation);
					window.frameNiveauPerso.setVisible(true);
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
	public PanelNiveauPerso(ZoneAnimation zoneAnimation) {
		this.zoneAnimation = zoneAnimation;
		initialize();
	}

	// par Junior Peumi
	/**
	 * Mthode qui initialise les lments faisant partie de l'interface du panneau du premier niveau du jeu (JLabel, JButton et etc...).
	 */
	private void initialize() {
		frameNiveauPerso = new JFrame();
		frameNiveauPerso.getContentPane().setBackground(new Color(243,128,132));
		frameNiveauPerso.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameNiveauPerso.setBounds(100, 100, 1500, 900);
		frameNiveauPerso.setBackground(new Color(204, 204, 255));
		frameNiveauPerso.getContentPane().setLayout(null);

		JPanel panelAffichage = new JPanel();
		panelAffichage.setBackground(new Color(241, 250, 238));
		panelAffichage.setBounds(871, 123, 522, 598);
		frameNiveauPerso.getContentPane().add(panelAffichage);
		panelAffichage.setLayout(null);

		JLabel lblVitesse = new JLabel("Vitesse");
		lblVitesse.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblVitesse.setBounds(14, 89, 102, 38);
		panelAffichage.add(lblVitesse);

		JLabel lblAcceleration = new JLabel("Acceleration");
		lblAcceleration.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAcceleration.setBounds(14, 125, 102, 38);
		panelAffichage.add(lblAcceleration);

		JLabel lblChargePerso = new JLabel("Charge du presonnage");
		lblChargePerso.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblChargePerso.setBounds(14, 163, 160, 46);
		panelAffichage.add(lblChargePerso);

		JLabel lblMassePerso = new JLabel("Masse du personnage");
		lblMassePerso.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMassePerso.setBounds(14, 206, 157, 46);
		panelAffichage.add(lblMassePerso);

		JLabel lblForceApplique = new JLabel("Force appliqu\u00E9 par click");
		lblForceApplique.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblForceApplique.setBounds(17, 249, 157, 38);
		panelAffichage.add(lblForceApplique);

		JLabel lblForcegravite = new JLabel("Force gravitationnelle");
		lblForcegravite.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblForcegravite.setBounds(17, 298, 137, 38);
		panelAffichage.add(lblForcegravite);

		JLabel lblRayonAction = new JLabel("Rayon d'action");
		lblRayonAction.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblRayonAction.setBounds(17, 347, 123, 46);
		panelAffichage.add(lblRayonAction);

		JLabel lblChargeParticule = new JLabel("Charge de la particule");
		lblChargeParticule.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblChargeParticule.setBounds(17, 388, 157, 46);
		panelAffichage.add(lblChargeParticule);

		JLabel lblComposanteForce = new JLabel("Force resultante");
		lblComposanteForce.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblComposanteForce.setBounds(14, 459, 157, 52);
		panelAffichage.add(lblComposanteForce);

		label = new JLabel(zoneAnimation.getNiveau().getPersonnage().getVitesse().toString());
		label.setBounds(114, 102, 226, 14);
		panelAffichage.add(label);

		JLabel lblMs = new JLabel("m/s");
		lblMs.setBounds(400, 102, 27, 14);
		panelAffichage.add(lblMs);

		 lblNewLabel = new JLabel(zoneAnimation.getNiveau().getPersonnage().getAccel().toString());
		lblNewLabel.setBounds(114, 138, 249, 14);
		panelAffichage.add(lblNewLabel);

		JLabel lblMs_1 = new JLabel("m/s^2");
		lblMs_1.setBounds(400, 138, 46, 14);
		panelAffichage.add(lblMs_1);

		 label_2 = new JLabel(Double.toString(zoneAnimation.getNiveau().getPersonnage().getCharge()));
		label_2.setBounds(174, 180, 191, 14);
		panelAffichage.add(label_2);

		JLabel lblC = new JLabel("C");
		lblC.setBounds(400, 180, 18, 14);
		panelAffichage.add(lblC);

		 label_4 = new JLabel(Double.toString(zoneAnimation.getNiveau().getPersonnage().getMasseEnKg()));
		label_4.setBounds(176, 223, 186, 14);
		panelAffichage.add(label_4);

		JLabel lblKg = new JLabel("kg");
		lblKg.setBounds(400, 223, 46, 14);
		panelAffichage.add(lblKg);

		label_6 = new JLabel(Double.toString(zoneAnimation.getNiveau().getForceParClick()));
		label_6.setBounds(188, 262, 177, 14);
		panelAffichage.add(label_6);

		JLabel lblN = new JLabel("N/0.01s");
		lblN.setBounds(400, 262, 46, 14);
		panelAffichage.add(lblN);

		label_8 = new JLabel(zoneAnimation.getNiveau().getForceGravite().multiplie(zoneAnimation.getNiveau().getPersonnage().getMasseEnKg()).toStringPetitNombres());
		label_8.setBounds(188, 311, 174, 14);
		panelAffichage.add(label_8);
		
		JLabel lblN_1 = new JLabel("N*10^-26");
		lblN_1.setBounds(400, 311, 61, 14);
		panelAffichage.add(lblN_1);

		label_10 = new JLabel(Double.toString(zoneAnimation.getNiveau().getPersonnage().getRayonAction()));
		label_10.setBounds(188, 364, 169, 14);
		panelAffichage.add(label_10);

		JLabel lblM = new JLabel("m");
		lblM.setBounds(400, 364, 46, 14);
		panelAffichage.add(lblM);

		label_12 = new JLabel(Double.toString(zoneAnimation.getNiveau().getChargeObst()));
		label_12.setBounds(184, 405, 177, 14);
		panelAffichage.add(label_12);

		JLabel lblC_1 = new JLabel("C");
		lblC_1.setBounds(400, 405, 46, 14);
		panelAffichage.add(lblC_1);

		JLabel lblN_2 = new JLabel("N*10^-26");
		lblN_2.setBounds(400, 438, 77, 14);
		panelAffichage.add(lblN_2);

		JLabel labelTempsEcouler = new JLabel("TEMPS :");
		labelTempsEcouler.setBounds(14, 11, 77, 52);
		panelAffichage.add(labelTempsEcouler);
		labelTempsEcouler.setFont(new Font("Tahoma", Font.BOLD, 17));
		labelTempsEcouler.setBackground(Color.BLACK);
		labelTempsEcouler.setForeground(Color.DARK_GRAY);

		
		
		labelTemps = new JLabel(Integer.toString(zoneAnimation.getTemps()));
		labelTemps.setBounds(101, 31, 27, 14);
		panelAffichage.add(labelTemps);
		labelTemps.setFont(new Font("Tahoma", Font.BOLD, 16));

		JLabel lblS = new JLabel("s");
		lblS.setBounds(138, 17, 18, 43);
		panelAffichage.add(lblS);
		lblS.setFont(new Font("Tahoma", Font.BOLD, 16));

		//Nouhaila Aater
		JCheckBox chckbxNewCheckBox = new JCheckBox("no timer");
		chckbxNewCheckBox.setBackground((panelAffichage.getBackground()));
		chckbxNewCheckBox.setBounds(6, 544, 97, 23);
		panelAffichage.add(chckbxNewCheckBox);
		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.BOLD, 14));

		JCheckBox chckbxAfficherVecteurResultant = new JCheckBox("Afficher le vecteur resultant");
		chckbxAfficherVecteurResultant.addActionListener(new ActionListener() {
			
			
			/**
			 * Permet d'afficher de vecteur vitesse si la case est cochée
			 */
			//Nouhaila			
			public void actionPerformed(ActionEvent e) {
				
				if (chckbxAfficherVecteurResultant.isSelected()) {
					zoneAnimation.setVecteurVitesse(true);
				} else {
					zoneAnimation.setVecteurVitesse(false);
				}
				
			}
		});//fin Nouhaila
		chckbxAfficherVecteurResultant.setBackground((panelAffichage.getBackground()));
		chckbxAfficherVecteurResultant.setBounds(6, 518, 236, 23);
		panelAffichage.add(chckbxAfficherVecteurResultant);
		chckbxAfficherVecteurResultant.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblForcelectrique = new JLabel("Force \u00E9lectrique");
		lblForcelectrique.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblForcelectrique.setBounds(14, 421, 157, 46);
		panelAffichage.add(lblForcelectrique);
		
		lblForceElectrique = new JLabel(zoneAnimation.getForceElectriques().toStringPetitNombres());
		lblForceElectrique.setBounds(126, 438, 227, 14);
		panelAffichage.add(lblForceElectrique);
		
		lblForceResultante = new JLabel(zoneAnimation.getForceResultante().toStringPetitNombres());
		lblForceResultante.setBounds(126, 479, 236, 14);
		panelAffichage.add(lblForceResultante);
		
		JLabel lblN_3 = new JLabel("N*10^-26");
		lblN_3.setBounds(400, 479, 61, 14);
		panelAffichage.add(lblN_3);
		
		JLabel lblPositionPerso = new JLabel("Position du personnage");
		lblPositionPerso.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPositionPerso.setBounds(14, 54, 160, 38);
		panelAffichage.add(lblPositionPerso);
		
		lblValeurPosition = new JLabel(zoneAnimation.getNiveau().getPersonnage().getCentreVecteur().toString());
		lblValeurPosition.setBounds(172, 67, 206, 14);
		panelAffichage.add(lblValeurPosition);
		
		JLabel lblM_1 = new JLabel("m");
		lblM_1.setBounds(400, 67, 27, 14);
		panelAffichage.add(lblM_1);
		
		JRadioButton rdbtnProtonia = new JRadioButton("Protonia");
		rdbtnProtonia.setSelected(true);
		rdbtnProtonia.addActionListener(new ActionListener() {
			//par Clement
			/**
			 * Methode permetant d'utiliser le personnage Protonia
			 */
			public void actionPerformed(ActionEvent arg0) {
				if (zoneAnimation.getNiveau().getPersonnage().getCharge()<0) {
					zoneAnimation.getNiveau().getPersonnage().setCharge(-1*zoneAnimation.getNiveau().getPersonnage().getCharge());
					zoneAnimation.repaint();
				}
			}
		});
		rdbtnProtonia.setBounds(14, 570, 109, 23);
		panelAffichage.add(rdbtnProtonia);
		
		
		JRadioButton rdbtnElectronia = new JRadioButton("Electronia");
		rdbtnElectronia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//par Clement
				/**
				 * Methode permetant d'utiliser le personnage Electronia
				 */
				if (zoneAnimation.getNiveau().getPersonnage().getCharge()>0) {
					zoneAnimation.getNiveau().getPersonnage().setCharge(-1*zoneAnimation.getNiveau().getPersonnage().getCharge());
					zoneAnimation.repaint();
				}
			}
		});
		rdbtnElectronia.setBounds(167, 570, 109, 23);
		panelAffichage.add(rdbtnElectronia);
	
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnProtonia);
		bg.add(rdbtnElectronia);

		
		

		
		lblNiveau = new JLabel("Choisissez votre niveau!");
		lblNiveau.setForeground(Color.BLACK);
		lblNiveau.setFont(new Font("Century", Font.BOLD, 35));
		lblNiveau.setBounds(202, 10, 550, 57);
		frameNiveauPerso.getContentPane().add(lblNiveau);




		JButton buttonStart = new JButton("Start");
		buttonStart.setForeground(Color.BLACK);
		buttonStart.setBackground(new Color(241, 250, 238));
		buttonStart.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		
		buttonStart.addActionListener(new ActionListener() {


		
             /**
              * Compte a rebours
              */
			//Nouhaila
			public void actionPerformed(ActionEvent arg0) {
					buttonStart.setEnabled(false);
					btnNext.setEnabled(false);
					btnPpause.setEnabled(true);
					sec = zoneAnimation.getTemps();

					timer = new Timer (1000, new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							
							if(sec==0 ) {
								
								timer.stop();
								zoneAnimation.arreter() ;
					
							} else {
								 zoneAnimation.setTemps( sec-1);
								 sec = zoneAnimation.getTemps();
							}
							labelTemps.setText(""+sec);}
					});

				
				
				start();
			}
		});
		buttonStart.setFocusable(false);
		buttonStart.setBounds(101,753, 116, 90);
		frameNiveauPerso.getContentPane().add(buttonStart);

		JButton btnRetourAuMenu = new JButton("Retour au menu");
		btnRetourAuMenu.setBackground(new Color(241, 250, 238));
		btnRetourAuMenu.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnRetourAuMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				zoneAnimation.arreter();
				retourAuMenu();
			}
		});
		btnRetourAuMenu.setBounds(871, 760, 303, 75);
		frameNiveauPerso.getContentPane().add(btnRetourAuMenu);
		//Clement
		zoneAnimation.setBounds(20, 79, 846, 670);
		frameNiveauPerso.getContentPane().add(zoneAnimation);
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
				if(!chckbxNewCheckBox.isSelected()) {
					timer.stop();
				}
				pause();
				
			}
		});
		btnPpause.setBounds(30, 760, 73, 75);
		frameNiveauPerso.getContentPane().add(btnPpause);
		btnPpause.setEnabled(false);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalStrut.setBackground(new Color(0, 0, 0));
		horizontalStrut.setForeground(Color.BLACK);
		horizontalStrut.setBounds(23, 748, 860, 1);
		frameNiveauPerso.getContentPane().add(horizontalStrut);
		//fin Clement

 
	    //Nouhaila
		JCheckBox chckbxMusique = new JCheckBox("Musique");
		chckbxMusique.addActionListener(new ActionListener() {
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
		chckbxMusique.setBounds(1008, 723, 97, 23);
		frameNiveauPerso.getContentPane().add(chckbxMusique);

		JCheckBox chckbxModeNuit = new JCheckBox("Mode nuit");
		chckbxModeNuit.addActionListener(new ActionListener() {


			/**
			 *Mode nuit
			 */
			//Nouhaila
			public void actionPerformed(ActionEvent arg0) {

				if(chckbxModeNuit.isSelected()) {
					//nuit
					frameNiveauPerso.getContentPane().setBackground(new Color(29,53,87));
					panelAffichage.setBackground(new Color(168,218,220));
					btnPpause.setBackground(new Color(168,218,220));
					buttonStart.setBackground(new Color(168,218,220));
					chckbxModeNuit.setBackground(new Color(168,218,220));
					btnRetourAuMenu.setBackground(new Color(168,218,220));
					chckbxMusique.setBackground(new Color(168,218,220));
					chckbxAfficherVecteurResultant.setBackground((panelAffichage.getBackground()));
					lblNiveau.setForeground(Color.white);
					

				}
				else {
					//jour
					frameNiveauPerso.getContentPane().setBackground(new Color(243,128,132));
					panelAffichage.setBackground(new Color(241, 250, 238));
					btnPpause.setBackground(new Color(241, 250, 238));
					buttonStart.setBackground(new Color(241, 250, 238));
					btnRetourAuMenu.setBackground(new Color(241, 250, 238));
					chckbxModeNuit.setBackground(new Color(241, 250, 238));
					chckbxMusique.setBackground(new Color(241, 250, 238));				
					chckbxAfficherVecteurResultant.setBackground((panelAffichage.getBackground()));
					lblNiveau.setForeground(Color.BLACK);
				}
			}
		});
		chckbxModeNuit.setBackground(new Color(241, 250, 238));
		chckbxModeNuit.setForeground(Color.BLACK);
		chckbxModeNuit.setFont(new Font("Tahoma", Font.BOLD, 12));
		chckbxModeNuit.setBounds(870, 723, 97, 23);
		frameNiveauPerso.getContentPane().add(chckbxModeNuit);
		
		btnNext = new JButton("Pause");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				zoneAnimation.unPas();
			}
		});
		btnNext.setForeground(Color.BLACK);
		btnNext.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNext.setBackground(new Color(224, 255, 255));
		btnNext.setBounds(234, 774, 73, 50);
		frameNiveauPerso.getContentPane().add(btnNext);
		btnNext.setEnabled(false);


		associerBoutonAvecImage(buttonStart,"play.png ");
		associerBoutonAvecImage(btnPpause,"pause.png ");
		associerBoutonAvecImage(btnNext,"next.png ");
		
		JButton btnChargerUnNiveau = new JButton("Charger un niveau...");
		btnChargerUnNiveau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				accederNiveau();
				btnPpause.setEnabled(false);
				btnNext.setEnabled(false);
				buttonStart.setEnabled(true);
				
			}
		});
		btnChargerUnNiveau.setBounds(491, 762, 303, 75);
		frameNiveauPerso.getContentPane().add(btnChargerUnNiveau);
		
		JButton btnReset = new JButton("");
		btnReset.addActionListener(new ActionListener() {
			//par Clement
			/**
			 * Methode recommencer le niveau du début
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
		frameNiveauPerso.getContentPane().add(btnReset);
		
		JMenuBar menuBar = new JMenuBar();
		frameNiveauPerso.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Information");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Guide Utilisateur");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuideUtilisateur guideUtilisateur = new GuideUtilisateur();
				guideUtilisateur.setVisible(true);
				// Roger
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		zoneAnimation.addEcouteurAnimation(new EcouteurAnimation() {
			public void mouvementPerso (Personnage perso, Vecteur forceElectrique, Vecteur forceResultante) {
				label.setText(perso.getVitesse().toString());
				lblNewLabel.setText(perso.getAccel().toString());
				lblForceElectrique.setText(forceElectrique.toStringPetitNombres());
				lblForceResultante.setText(forceResultante.toStringPetitNombres());
				lblValeurPosition.setText(perso.getCentreVecteur().toString());
				}
			
		});
		

		//junior
		zoneAnimation.addEcouteurFinNiveau(new EcouteursFinNiveau() {
			@Override
			public void recommencerNiveau() {
				/*zoneAnimation.reset();
				labelTemps.setText(Integer.toString(zoneAnimation.getTemps()));*/
				
				zoneAnimation.arreter();
			}

			@Override
			public void retourMenu() {
				frameNiveauPerso.dispose();
				zoneAnimation.arreter();
				
			}

			
		});


		


	} // fin junior

	//par Clement
	/**
	 * Methode qui met en pause le jeu
	 */
	private void pause() {

		if (zoneAnimation.getEstEnAnimation()) {
			zoneAnimation.arreter();

		}

	}
	//par Clement
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
		frameNiveauPerso.dispose();
		PanelMenu menu = new PanelMenu();
		menu.NewScreen();
	

	}

	public ZoneAnimation getZoneAnimation() {
		return (zoneAnimation);
	}//fin junior
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
	
	//Clement
	/**
	 * Methode ouvrant une fenetre a l'utilisateur ou il peut selectionner un niveau dans le fichier Niveau ChargeMen sur le bureau et le jouer
	 */
	private void accederNiveau() {
		ObjectInputStream ois = null;
		fc.setDialogTitle("Chargez un de ces niveaux.");
		fc.setCurrentDirectory(new File(System.getProperty("user.home"), "Desktop" + "\\Niveau ChargeMan"));
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int niveauChoisi = fc.showOpenDialog(null);
		
		try {
			InputStream is = new FileInputStream(fc.getSelectedFile().getAbsoluteFile());
			ois = new ObjectInputStream(is);
			Niveau niv = (Niveau) ois.readObject();
			zoneAnimation.setNiveau(niv);
			labelTemps.setText(Integer.toString(zoneAnimation.getTemps()));
			label.setText(zoneAnimation.getNiveau().getPersonnage().getVitesse().toString()); 
			lblNewLabel.setText(zoneAnimation.getNiveau().getPersonnage().getAccel().toString());  
			label_2.setText(Double.toString(zoneAnimation.getNiveau().getPersonnage().getCharge()));   
			label_4.setText(Double.toString(zoneAnimation.getNiveau().getPersonnage().getMasseEnKg()));
			label_6.setText(Double.toString(zoneAnimation.getNiveau().getForceParClick()));
			label_8.setText(zoneAnimation.getForceGraviter().toStringPetitNombres());
			label_10.setText(Double.toString(zoneAnimation.getNiveau().getPersonnage().getRayonAction()));
			label_12.setText(Double.toString(zoneAnimation.getNiveau().getChargeObst()));
			lblForceElectrique.setText(zoneAnimation.getForceElectriques().toStringPetitNombres());
			lblForceResultante.setText(zoneAnimation.getForceResultante().toStringPetitNombres());
			lblValeurPosition.setText(zoneAnimation.getNiveau().getPersonnage().getCentreVecteur().toString());
		    lblNiveau.setText(zoneAnimation.getNiveau().getNom());
			
			
			
			
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
}

