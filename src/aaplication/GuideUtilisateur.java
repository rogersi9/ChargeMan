package aaplication;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Affichage.PanelMenu;

/**
 * Creation panneau pour Guide Utilisateur qui contient le texte
 * 
 * @author Nouhaila
 */
public class GuideUtilisateur extends JFrame {

	private JPanel contentPane;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuideUtilisateur frameGuideUtilisateur = new GuideUtilisateur();
					frameGuideUtilisateur.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public GuideUtilisateur() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 10, 1039, 1039);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.setBackground(Color.yellow);

		ImagesAvecDefilement panGuide = new ImagesAvecDefilement();
		
		contentPane.add(panGuide);

		//LES PAGES
		String tableauImages[] = {"page-0.jpg","page-1.jpg","page-2.jpg","page-3.jpg","page-4.jpg","page-5.jpg"};
		panGuide.setFichiersImages(tableauImages);
	    int largeur = panGuide.getLargeurCadre();
		panGuide.setBounds(35, 11,largeur, 900);

		JButton button = new JButton("Retour Menu");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		contentPane.add(button, BorderLayout.SOUTH);
		
		JButton button_2 = new JButton("<< PRECEDENT");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panGuide.precedente();
			}
		});
		
		contentPane.add(button_2, BorderLayout.WEST);
		
		JButton button_1 = new JButton("SUIVANT >>");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panGuide.suivante();
			}
		});
		contentPane.add(button_1, BorderLayout.EAST);
		
		 
	}
	
	

}
