package Affichage;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Niveau.Niveau;
import aaplication.GuideUtilisateur;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class ChoixPersonnage {
	private JFrame choixPersonnage;
	private Niveau niveauChoisi;

	/**
	 * permet de choisir son personnage
	 * @author junior
	 * @author Caroline
	 * 
	 */
	public void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChoixPersonnage window = new ChoixPersonnage(niveauChoisi);
					window.choixPersonnage.setVisible(true);
				} catch (Exception e){
					e.printStackTrace();
				}
			}
		});
	}

	// par Junior Peumi
	/**
	 * Méthode qui instancie l'interface du panneau niveaux.
	 * @wbp.parser.entryPoint
	 */
	public ChoixPersonnage(Niveau choixNiveau) {
		this.niveauChoisi = choixNiveau;
		initialize();
	}
	private void initialize() {
		choixPersonnage = new JFrame();
		choixPersonnage.setBounds(100, 100, 698, 403);
		choixPersonnage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		choixPersonnage.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.RED);
		panel.setBounds(0, 0, 682, 364);

		choixPersonnage.getContentPane().add(panel);
		panel.setLayout(null);

		JButton btnRetour = new JButton("Retour");
		btnRetour.setBounds(39, 411, 109, 23);
		panel.add(btnRetour);

		JButton btnProtonia = new JButton("Protonia");
		btnProtonia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PanelJeu panelJeu = new PanelJeu(new ZoneAnimation(niveauChoisi));
				// on instancie le panel jeu.
				panelJeu.NewScreen();
				choixPersonnage.dispose();
			}
		});
		btnProtonia.setBounds(90, 140, 173, 88);
		associerBoutonAvecImage(btnProtonia, "CapturePROTON.PNG");
		panel.add(btnProtonia);

		JButton btnElectronia = new JButton("Electronia");
		btnElectronia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelJeu panelJeu = new PanelJeu(new ZoneAnimation(niveauChoisi));
				// on set la charge.
				panelJeu.getZoneAnimation().getNiveau().getPersonnage().setCharge(panelJeu.getZoneAnimation().getNiveau().getPersonnage().getCharge()*-1);
				// on repaint;
				panelJeu.getZoneAnimation().repaint();
				// on instancie le panel jeu.
				panelJeu.NewScreen();
				choixPersonnage.dispose();
			}
		});
		btnElectronia.setBounds(454, 140, 173, 88);
		associerBoutonAvecImage(btnElectronia, "CaptureELECTRON.PNG");
		panel.add(btnElectronia);
		
		JLabel lblNewLabel = new JLabel("Choisis Ton Personnage");
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 25));
		lblNewLabel.setBounds(208, 36, 341, 47);
		panel.add(lblNewLabel);
		
		JMenuBar menuBar = new JMenuBar();
		choixPersonnage.setJMenuBar(menuBar);
		
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

		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				retourAuMenu();
			}
		});

	}

	/**
	 * @wbp.parser.entryPoint
	 */
	private void retourAuMenu() {
		choixPersonnage.dispose();
		PanelNiveaux niveaux = new PanelNiveaux();
		niveaux.NewScreen();
	}
	
	public void setNiveauChoisi(Niveau niveauChoisi) {
		this.niveauChoisi = niveauChoisi;
	}
	/**
	 * Mthode qui permet d'associer une image a un bouton 
	 * @param leBouton
	 * @param fichierImage
	 * @author Caroline 
	 */
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
}
