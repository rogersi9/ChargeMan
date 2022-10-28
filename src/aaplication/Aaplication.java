package aaplication;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Affichage.Musique;
import Affichage.PanelEditeur;
import Affichage.PanelMenu;
import Affichage.PanelNiveaux;
import geometrie.Personnage;
import geometrie.Vecteur;
import moteur.ForceElectrique;
import obstacles.ParticuleObstacles;

/**
 * Classe de démarrage du jeu.
 * @author Junior Peumi
 */
public class Aaplication extends JFrame {
	private JPanel contentPane;
	private static PanelMenu menu;
	private static Aaplication frame;
	private JFrame f = new JFrame("Login");
	
	public static void main(String[] args) {
	
	

		
		//test reussi force electrique
		Vecteur vecPerso = new Vecteur (1,2);
		Vecteur vecParti = new Vecteur (3,1);
		double chargePerso = 3 ;
		
		/**
		 *
		Personnage perso = new Personnage(vecPerso, 7*Math.pow(10, -6), 3);
		ParticuleObstacles particule = new ParticuleObstacles(vecParti, true);
		ForceElectrique force = new ForceElectrique (perso , particule );
	    System.out.println("distance entre perso et particule "+force.distancePersoParticule());
	    System.out.println( "force vecteur Deplacement  "+force.VecteurDeplacement());
	    System.out.println( "force vecteur Unitaire "+force.vecteurUnitaire());
	    System.out.println( "force en valeur "+force.ForceElectriqueEnValeur());
	    System.out.println( "force vecteur "+force.forceElecVecteur()); 
		 */
		PanelMenu.NewScreen();
	}

	/**
	 * Constructeur pour instancier l'interface de démarrage.
	 */
	public Aaplication() {
		setResizable(false);
		setAlwaysOnTop(true);
		/*
		 * on doit supprimer cette partie, puisque j'ai (junior) instancié le panelMenu dans le Main.
		 * 
		 * Si vous voulez changer quelque chose dans le menu, faîtes le dans la classe PanelMenu
		 */
		f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(new Rectangle(0, 0, 1000, 500));
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Info");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmAide = new JMenuItem("Aide");
		mntmAide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			}
		});
		mnNewMenu.add(mntmAide);
		
		JMenuItem mntmAPropos = new JMenuItem("A Propos");
		mnNewMenu.add(mntmAPropos);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().setLayout(null);
		setContentPane(contentPane);

		/*
		 * labelMenu
		 */
		contentPane.setLayout(null);

		/*
		 * btnNiveau : bouton qui créer la nouvelle fenêtre avec le panel Niveau
		 */
		JButton btnNiveau = new JButton("Mode Aventure");
		btnNiveau.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		btnNiveau.setBounds(new Rectangle(250, 250, 0, 0));
		btnNiveau.setBounds(335, 230, 268, 54);
		getContentPane().add(btnNiveau);
		btnNiveau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				PanelNiveaux zoneNiveaux = new PanelNiveaux();
				PanelNiveaux.NewScreen();
			}
		});

		/*
		 * btnEditeur : bouton qui créer la nouvelle fenêtre avec le panel Editeur
		 */
		JButton btnEditeur = new JButton("Mode Cr\u00E9ateur");
		btnEditeur.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		btnEditeur.setBounds(new Rectangle(250, 250, 0, 0));
		btnEditeur.setBounds(335, 343, 268, 54);
		getContentPane().add(btnEditeur);
		
		JLabel lblChargeMan = new JLabel("Charge Man");
		lblChargeMan.setFont(new Font("Century", Font.PLAIN, 50));
		lblChargeMan.setBounds(322, 85, 323, 69);
		contentPane.add(lblChargeMan);
		btnEditeur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				PanelEditeur zoneEditeur = new PanelEditeur();
				PanelEditeur.NewScreen();
			}
		});
	}
}
