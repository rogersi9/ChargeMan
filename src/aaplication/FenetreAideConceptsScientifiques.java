package aaplication;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Affichage.PanelMenu;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

/**
 * Exemple de fenetre qui cree un JPanel dans lequel sera affichees (et bien ajustees) une suite d'images representant du texte
 * continu. Les boutons places sur cette fenetre permettent de passer a l'image precedente/suicante.
 * 
 * @author Caroline Houle
 *
 */
public class FenetreAideConceptsScientifiques extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnPagePrecedente;
	private JButton btnPageSuivante;
	
	// c'est ici que l'on declare un tableau ou on enumere toutes les pages d'aide desirees
	private String tableauImages[] = {"science1.jpg","science2.jpg", "science3.jpg"};


	/**
	 * Application de test pour charger une image dans un panel, avec barre de d�filement
	 */
	public static void main(String[] args) {
		

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JOptionPane.showMessageDialog(null, 
							"*********************************************************************************************\n"
							+ "Lire le document de consignes en premier!\n"
							+ "*********************************************************************************************\n");
				
					FenetreAideConceptsScientifiques frame = new FenetreAideConceptsScientifiques();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Constructeur: cr�e une fen�tre qui inclut une instance d'image avec d�filement
	 */
	public FenetreAideConceptsScientifiques() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 886, 808);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// creation du composant qui contiendra les pages d'aide
		ImagesAvecDefilement panAide = new ImagesAvecDefilement();
		//Pour modifier la largeur et la couleur du cadre autour des pages 
		panAide.setLargeurCadre(10);
		panAide.setBackground(Color.white); 
		panAide.setFichiersImages(tableauImages); // on precise quelles images seront utilisees
		panAide.setBounds(49, 88, 772, 606);
		contentPane.add(panAide);


		
		btnPagePrecedente = new JButton("Page pr\u00E9c\u00E9dente");
		btnPagePrecedente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnPagePrecedente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnPagePrecedente.setEnabled( panAide.precedente() );
				btnPageSuivante.setEnabled(true);
			}
		});
		btnPagePrecedente.setBounds(49, 705, 165, 45);
		contentPane.add(btnPagePrecedente);
		
		btnPageSuivante = new JButton("Page suivante");
		btnPageSuivante.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnPageSuivante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnPageSuivante.setEnabled( panAide.suivante() );
				btnPagePrecedente.setEnabled(true);
			}
		
		});
		
		if (tableauImages.length==1 ) {
			btnPagePrecedente.setEnabled(false);
			btnPageSuivante.setEnabled(false);
		}
		btnPageSuivante.setBounds(656, 705, 165, 45);
		contentPane.add(btnPageSuivante);
		
		JLabel lblAideConcepts = new JLabel("Aide : Concepts scientifiques");
		lblAideConcepts.setHorizontalAlignment(SwingConstants.CENTER);
		lblAideConcepts.setForeground(Color.WHITE);
		lblAideConcepts.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAideConcepts.setBounds(264, 11, 342, 34);
		contentPane.add(lblAideConcepts);
		
	
		
		
		
		
		
	}//fin constructeur
}//fin classe
