package Affichage;

/**
*cette classe reprente la fin du jeu
 * @author junior
 * 
 */

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

import Niveau.GestionaireFichierNiveau;
import Niveau.Niveau;
	import javax.swing.JLabel;
	import javax.swing.JOptionPane;

	import java.awt.Font;
	import java.awt.Image;

	public class FinDuJeu {
		private JFrame finDuJeux;
	
		
		
		public String texte;
		
		public int temps;
		public JLabel lblNewLabel ;
		public int nb_niveau;
		
		/**
		 * @author junior
		 */
		public void NewScreen() {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						FinDuJeu window = new FinDuJeu(texte);
						window.finDuJeux.setVisible(true);
					} catch (Exception e){
						e.printStackTrace();
					}
				}
			});
		}

		// par Junior Peumi
		/**
		 * Méthode qui instancie l'interface du panneau niveaux.
		 * @param string 
		 */
		public FinDuJeu(String s) {
			this.texte=s;
			lblNewLabel= new JLabel(texte);
			lblNewLabel.setBackground(new Color(173, 216, 230));
			initialize();
		}
		private void initialize() {
			
			finDuJeux = new JFrame();
			finDuJeux.setBounds(100, 100, 619, 288);
			finDuJeux.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			finDuJeux.getContentPane().setLayout(null);

			JPanel niveauSuivant = new JPanel();
			niveauSuivant.setBackground(new Color(175, 238, 238));
			niveauSuivant.setBounds(0, 0, 603, 311);

			finDuJeux.getContentPane().add(niveauSuivant);
			niveauSuivant.setLayout(null);

			JButton btnRetour = new JButton("Retour");
			btnRetour.setBounds(39, 411, 109, 23);
			niveauSuivant.add(btnRetour);

			JButton btnretouNiv = new JButton("Retour au menu");
			btnretouNiv.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					PanelMenu menu = new PanelMenu();
					
					
					menu.NewScreen();
					finDuJeux.dispose();
					
					
				}
			});
			btnretouNiv.setBounds(39, 164, 152, 47);
			
			niveauSuivant.add(btnretouNiv);
			
			
			lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 20));
			lblNewLabel.setBounds(22, 36, 571, 91);
			niveauSuivant.add(lblNewLabel);
			
			JButton btnChoixDeNiveau = new JButton("Choix de niveau\r\nx");
			btnChoixDeNiveau.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					retourniveeau() ; 
				}
			});
			btnChoixDeNiveau.setBounds(407, 164, 152, 47);
			niveauSuivant.add(btnChoixDeNiveau);
			
			

		

		}

		/**
		 * @wbp.parser.entryPoint
		 */
		private void retourniveeau() {
			finDuJeux.dispose();
			PanelNiveaux niveaux = new PanelNiveaux();
			niveaux.NewScreen();
		}
		public void setLabel(String label) {
			this.lblNewLabel.setText(label);
			
		}
		
		public void setTemps(int t) {
			this.temps= t;
		}
		
	}

