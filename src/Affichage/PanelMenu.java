package Affichage;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import Niveau.GestionaireFichierNiveau;
import Niveau.Niveau;
import aaplication.FenetreAideConceptsScientifiques;
import aaplication.GuideUtilisateur;
/**
 *  La classe accueil qui permet à l'utilisateur de choisir le mode de jeu qu'il desire. 
 * @author Roger Sioufi
 * @author Junior Peumi
 */
public class PanelMenu {

	private JFrame frameMenu;
	

	// par Junior Peumi
	/*
	 * Méthode qui créé et qui met visible le frameMenu, étant la fenêtre dédiée au menu principal.
	 */
	public static void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PanelMenu window = new PanelMenu();
					window.frameMenu.setVisible(true);
				} catch (Exception e){
					e.printStackTrace();
				}
			}
		});
	}

	// par Junior Peumi
	/**
	 * Méthode qui instancie l'interface du panneau menu.
	 */
	public PanelMenu() {
		initialize();
	}

	// par Roger Sioufi
	/**
	 * Méthode qui initialise les éléments faisant partie de l'interface du panneau menu (JLabel, JButton et etc...).
	 */
	private void initialize() {
		frameMenu = new JFrame();
		frameMenu.getContentPane().setBackground(new Color(0, 0, 0));
		frameMenu.getContentPane().setForeground(new Color(0, 0, 0));
		frameMenu.setBackground(new Color(0, 51, 255));
		frameMenu.setBounds(100, 100, 728, 499);
		frameMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameMenu.getContentPane().setLayout(null);
		
		JButton btnNiveaux = new JButton("Mode Aventure");
		btnNiveaux.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		btnNiveaux.setBackground(new Color(255, 255, 255));
		btnNiveaux.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accederModeAventure();
			}
		});
		btnNiveaux.setBounds(252, 204, 180, 53);
		frameMenu.getContentPane().add(btnNiveaux);
		
		JButton btnEditeur = new JButton("Mode Cr\u00E9ateur");
		btnEditeur.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		btnEditeur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accederModeEditeur();
			}
		});
		btnEditeur.setBounds(252, 282, 180, 53);
		frameMenu.getContentPane().add(btnEditeur);
		
		JLabel lblChargeMan = new JLabel("Charge Man");
		lblChargeMan.setFont(new Font("Century Gothic", Font.PLAIN, 40));
		lblChargeMan.setForeground(new Color(255, 255, 255));
		lblChargeMan.setBackground(new Color(255, 255, 255));
		lblChargeMan.setBounds(228, 65, 273, 68);
		frameMenu.getContentPane().add(lblChargeMan);
		
		JButton buttonPerso = new JButton("Niveaux perso");
		buttonPerso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GestionaireFichierNiveau gestionFich = new GestionaireFichierNiveau();
				Niveau niveauDeBase = gestionFich.lireFichierBinBuildPath("NiveauDeBase");
				PanelNiveauPerso panelNiveauPerso = new PanelNiveauPerso(new ZoneAnimation(niveauDeBase));
				panelNiveauPerso.NewScreen();
				frameMenu.dispose();
			}
		});
		buttonPerso.setFont(new Font("Dialog", Font.PLAIN, 14));
		buttonPerso.setBounds(252, 362, 180, 27);
		frameMenu.getContentPane().add(buttonPerso);
		
		JMenuBar menuBar = new JMenuBar();
		frameMenu.setJMenuBar(menuBar);
		
		JMenu mnInformation = new JMenu("Information");
		menuBar.add(mnInformation);
		
		
		
		/**
		 * @author Nouhaila Aatter
		 */
		FenetreAideConceptsScientifiques conceptScientifique =  new FenetreAideConceptsScientifiques();
		
		
		
		/**
		 * @author Nouhaila Aater
		 */
		
		GuideUtilisateur guideUtilisateur = new GuideUtilisateur();
		
		JMenuItem mntmGuideUtili = new JMenuItem("Guide utilisateur");
		mntmGuideUtili.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				guideUtilisateur.setVisible(true);
				}
		});
		mnInformation.add(mntmGuideUtili);
	
	}
	
	// par Junior Peumi
	/**
	 * Méthode qui permet à l'utilisateur d'accéder au mode aventure du jeu. 
	 * La méthode créé un PanelNiveaux, étant le panneau réservé au mode aventure du jeu.
	 * À l'aide de la méthode dispose() du frameMenu, on peut fermer le frameMenu pour ensuite instancier le panneau niveaux avec la méthode NewScreen().
	 */
	private void accederModeAventure() {
		frameMenu.dispose();
		PanelNiveaux niveaux = new PanelNiveaux();
		niveaux.NewScreen();
	}
	
	// par Junior Peumi
	/**
	 * Méthode qui permet à l'utilisateur d'accéder au mode éditeur du jeu. 
	 * La méthode créé un PanelEditeur, étant le panneau réservé au mode éditeur du jeu.
	 * À l'aide de la méthode dispose() du frameMenu, on peut fermer le frameMenu pour ensuite instancier le panneau editeur avec la méthode NewScreen().
	 */
	private void accederModeEditeur() {
		frameMenu.dispose();
		PanelEditeur editeur = new PanelEditeur();
		editeur.NewScreen();
	}
}
