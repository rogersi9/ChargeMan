
package Affichage;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Niveau.GestionaireFichierNiveau;
import Niveau.Niveau;
import aaplication.GuideUtilisateur;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 * Cette classe créé l'interface du sélecteur de niveaux.
 * @author Junior Peumi
 * @author Roger Sioufi
 */
public class PanelNiveaux {

	private JFrame frameNiveaux;	
	private boolean estProtonia = false;
	private boolean estElectronia = false;
	 

	// par Junior Peumi
	/*
	 * Méthode qui créé et qui met visible le frameNiveaux, étant la fenêtre dédiée au sélecteur de niveaux.
	 */
	public static void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PanelNiveaux window = new PanelNiveaux();
					window.frameNiveaux.setVisible(true);
				} catch (Exception e){
					e.printStackTrace();
				}
			}
		});
	}

	// par Junior Peumi
	/**
	 * Méthode qui instancie l'interface du panneau niveaux.
	 */
	public PanelNiveaux() {
		initialize();
		//nouhaila
	}

	// par Roger Sioufi
	/**
	 * Méthode qui initialise les éléments faisant partie de l'interface du panneau niveaux (JLabel, JButton et etc...).
	 */
	private void initialize() {
		frameNiveaux = new JFrame();
		frameNiveaux.setBounds(100, 100, 985, 498);
		frameNiveaux.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameNiveaux.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(51, 204, 255));
		panel.setBounds(0, 0, 969, 459);
		frameNiveaux.getContentPane().add(panel);
		panel.setLayout(null);

		JButton btnRetour = new JButton("Retour");
		btnRetour.setBounds(39, 411, 109, 23);
		panel.add(btnRetour);

		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				retourAuMenu();
			}
		});

		JLabel lblZoneNiveau = new JLabel("Choisissez le niveau d\u00E9sir\u00E9 : \r\n");
		lblZoneNiveau.setFont(new Font("Century Gothic", Font.PLAIN, 50));
		lblZoneNiveau.setBounds(39, 42, 722, 116);
		lblZoneNiveau.setForeground(new Color(0, 0, 0));
		panel.add(lblZoneNiveau);

		JButton btnNewButton = new JButton("1");
		btnNewButton.setFont(new Font("Centaur", Font.PLAIN, 50));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				accederNiveau(Integer.parseInt(btnNewButton.getText()));
			}
		});
		btnNewButton.setBounds(83, 193, 117, 98);
		panel.add(btnNewButton);

		JButton button = new JButton("2");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				accederNiveau(Integer.parseInt(button.getText()));
			}
		});
		button.setFont(new Font("Centaur", Font.PLAIN, 50));
		button.setBounds(431, 193, 117, 98);
		panel.add(button);

		JButton button_1 = new JButton("3");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accederNiveau(Integer.parseInt(button_1.getText()));
			}
		});
		button_1.setFont(new Font("Centaur", Font.PLAIN, 50));
		button_1.setBounds(769, 193, 117, 98);
		panel.add(button_1);

		JButton button_2 = new JButton("4");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accederNiveau(Integer.parseInt(button_2.getText()));
			}
		});
		button_2.setFont(new Font("Centaur", Font.PLAIN, 50));
		button_2.setBounds(266, 302, 117, 98);
		panel.add(button_2);

		JButton button_3 = new JButton("5");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accederNiveau(Integer.parseInt(button_3.getText()));
			}
		});
		button_3.setFont(new Font("Centaur", Font.PLAIN, 50));
		button_3.setBounds(609, 302, 117, 98);
		panel.add(button_3);
		
		JMenuBar menuBar = new JMenuBar();
		frameNiveaux.setJMenuBar(menuBar);
		
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
	
		ButtonGroup groupeBouton = new ButtonGroup();
	}

	// par Junior Peumi
	/**
	 * Méthode qui permet à l'utilisateur de revenir au menu. 
	 * La méthode créé un PanelMenu, étant le panneau réservé au menu prinicpal.
	 * À l'aide de la méthode dispose() du frameNiveaux, on peut fermer le frameNiveaux pour ensuite instancier le panneau menu avec la méthode NewScreen().
	 */
	private void retourAuMenu() {
		frameNiveaux.dispose();
		PanelMenu menu = new PanelMenu();
		menu.NewScreen();
	}

	// par Junior Peumi
	/**
	 * Méthode qui permet à l'utilisateur d'accéder au premier niveau du jeu. 
	 * La méthode créé un PanelJeu, étant le panneau réservé au premier niveau du jeu.
	 * À l'aide de la méthode dispose() du frameNiveaux, on peut fermer le frameNiveaux pour ensuite instancier le panneau niveau1 avec la méthode NewScreen().
	 */
	private void accederNiveau(int nbNiveau) {
		GestionaireFichierNiveau gestionFich = new GestionaireFichierNiveau();
		Niveau niveau = gestionFich.lireFichierBinBuildPath("niveau" + nbNiveau);
		ChoixPersonnage ecranChoixPersonnage = new ChoixPersonnage(niveau);
		ecranChoixPersonnage.NewScreen();
		frameNiveaux.dispose();
	}
}
