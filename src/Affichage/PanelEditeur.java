package Affichage;

import java.awt.EventQueue;	
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import aaplication.Aaplication;
import aaplication.GuideUtilisateur;
import geometrie.Personnage;
import geometrie.Vecteur;
import Niveau.GestionaireFichierNiveau;
import Niveau.Niveau;
import obstacles.BarreHorizontale;
import obstacles.BarreVerticale;
import obstacles.Obstacles;
import obstacles.ParticuleObstacles;
import obstacles.Triangle;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.text.AbstractDocument.Content;
import javax.swing.text.html.HTMLDocument.Iterator;
import javax.swing.event.ChangeEvent;
import java.awt.Font;
import java.awt.Window.Type;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.TransferHandler;
import javax.swing.UIManager;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import javax.swing.JCheckBox;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
/**
 *  La classe qui cre l'interface du mode crateur de jeu qui permet a l'utilisatuer de placer des objets et de crer ses niveau personnel
 *@author Roger Sioufi
 *@author Clement Harvey
 *@author Nouhaila Aater
 *@author Junior Peumi
 */
public class PanelEditeur extends JPanel  {
	
	
	private JFrame frameEditeur;
	private JLabel lblGraviteValeur;
	private JSlider sliderGravite;
	private JSlider sliderChargeParticule;
	private JSlider sliderForceApp;
	
	private boolean proton = true;
	private Niveau niv;
	private JLabel label;
	private JLabel label_5;
	private JSlider sliderChargePersonnage;
	private JLabel label_7;
	private JSlider sliderRayonAction;
	private int click_droit_proton =0;
	private int click_gauche_proton =0;
	private int click_droite_obstacle =0;
	private int click_gauche_obstacle =0;
	private int indexObstacleABouger, indexTriangleABouger;
	private boolean move , moveperso, moveTriangle, moveArrivee , movemechant; 
	private ZoneEditeur editeur;
	private String obstacleSelection;
	private String nomNiv;
	private Vecteur vecteur , vecteurtri ;
	private JSlider sliderTemps;
	
	
	//nouhaila
	
	private JSpinner spinnerTemps;
	// fin nouhaila
	/**
	 * Launch the application.
	 */
//  junior
	public static void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PanelEditeur window = new PanelEditeur();
					window.frameEditeur.setVisible(true);
				} catch (Exception e){
					e.printStackTrace();
				}
			}
		});
	}

	
	/**
	 * Create the application.
	 */
	public PanelEditeur() {

		initialize();
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	//Roger
	private void initialize() {


		frameEditeur = new JFrame();
		frameEditeur.getContentPane().setBackground(new Color(255, 255, 255));
		frameEditeur.setResizable(false);
		frameEditeur.setTitle("Mode Cr\u00E9ateur");

		//par Junior Peumi
		editeur= new ZoneEditeur();
		editeur.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				selectionnerObstacle(arg0);
				selectionnerperso(arg0);
				selectionnerTriangle(arg0);
				selectionnerpointArrivee( arg0);
				selectionnerMechant(arg0);
			}

			public void mouseReleased(MouseEvent arg0) {
				dropObstacle(arg0);
			}
		});
		// par Junior Peumi	

		editeur.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				
				
				if(move) {
					System.out.println("drag");
					editeur.redessiner();
					editeur.getListeObstacles().get(indexObstacleABouger).getPosition().setXY((int) (e.getX() / editeur.getMatMC().getScaleX()), (int) (e.getY() / editeur.getMatMC().getScaleY()));
					//System.out.println(editeur.getListeObstacles().get(indexObstacleABouger).getPosition());
					editeur.redessiner();
					move = true;

				}else if (moveperso) {
					
					
					if(e.getX()>=editeur.getPersonnage().getDiametre()/2 && e.getY() >=editeur.getPersonnage().getDiametre()/2 && e.getX() <= 810- editeur.getPersonnage().getDiametre()/2  
							&& e.getY()<= 625-editeur.getPersonnage().getDiametre()/2)  {
						
						
					editeur.redessiner();
					editeur.getPersonnage().setPosition( vecteur = new Vecteur((int) (e.getX() / editeur.getMatMC().getScaleX()), (int) (e.getY() / editeur.getMatMC().getScaleY())));
					editeur.redessiner();
					moveperso = true;
					
					
					}
			
				}else if (moveArrivee) {
					
					
					if(e.getX()>=editeur.getPointArrive().getDiametre()/2 && e.getY() >=editeur.getPointArrive().getDiametre()/2 && e.getX() <= 785- editeur.getPointArrive().getDiametre()/2  
							&& e.getY()<= 600-editeur.getPointArrive().getDiametre()/2) 
						
					{
					editeur.redessiner();
					editeur.getPointArrive().getVecteurPosition().setXY((int) (e.getX() / editeur.getMatMC().getScaleX()), (int) (e.getY() / editeur.getMatMC().getScaleY()));
					editeur.redessiner();
					moveTriangle= true;
					}
				}else if (moveTriangle) {
					System.out.println("drag tri");
					editeur.getListeObstacles().get(indexTriangleABouger).getPosition().setXY((int) (e.getX() / editeur.getMatMC().getScaleX()), (int) (e.getY() / editeur.getMatMC().getScaleY()));		
	
					editeur.redessiner();
					moveTriangle= true;
				}

			}

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stu

			}

		});

		editeur.setBounds(20, 79, 860, 675);
		frameEditeur.getContentPane().add(editeur);
		editeur.requestFocusInWindow();
		editeur.setFocusable(true);
		//fin junior Peumi

		frameEditeur.getContentPane();
		frameEditeur.setBounds(100, 100,1234, 900);
		frameEditeur.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameEditeur.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 73, 883, 696);
		frameEditeur.getContentPane().add(panel);
		panel.setLayout(null);

		JButton btnRetour = new JButton("retour au menu");
		btnRetour.setFont(new Font("Century", Font.PLAIN, 14));
		btnRetour.setBounds(24, 811, 145, 23);
		frameEditeur.getContentPane().add(btnRetour);

		JPanel panel_entree = new JPanel();
		panel_entree.setBackground(new Color(255, 255, 255));
		panel_entree.setForeground(new Color(128, 0, 0));
		panel_entree.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_entree.setBounds(903, 79, 300, 656);
		frameEditeur.getContentPane().add(panel_entree);
		panel_entree.setLayout(null);

		JLabel lblForceDeGravit = new JLabel("Acc\u00E9l\u00E9ration gravitationnelle :");
		lblForceDeGravit.setFont(new Font("Century", Font.PLAIN, 14));
		lblForceDeGravit.setBounds(15, 132, 208, 36);
		panel_entree.add(lblForceDeGravit);

		lblGraviteValeur = new JLabel("0");
		lblGraviteValeur.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblGraviteValeur.setBounds(169, 179, 33, 14);
		panel_entree.add(lblGraviteValeur);

		sliderGravite = new JSlider();
		sliderGravite.setBackground(new Color(255, 255, 255));
		sliderGravite.setBorder(null);
		sliderGravite.setForeground(new Color(255, 255, 255));
		sliderGravite.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				modifierGravite();


			}
		});
		sliderGravite.setValue(0);
		sliderGravite.setMaximum(10);
		sliderGravite.setMinimum(-10);
		sliderGravite.setBounds(15, 179, 128, 14);
		panel_entree.add(sliderGravite);

		JLabel lblForceAppliqu = new JLabel("Force appliqu\u00E9 :");
		lblForceAppliqu.setFont(new Font("Century", Font.PLAIN, 14));
		lblForceAppliqu.setBounds(15, 204, 156, 36);
		panel_entree.add(lblForceAppliqu);

		JLabel labelForceSortie = new JLabel("10");
		labelForceSortie.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		labelForceSortie.setBounds(169, 247, 33, 14);
		panel_entree.add(labelForceSortie);

		sliderForceApp = new JSlider();
		sliderForceApp.setBackground(new Color(255, 255, 255));

		sliderForceApp.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				labelForceSortie.setText(""+sliderForceApp.getValue());


			}
		});

		sliderForceApp.setMinimum(5);
		sliderForceApp.setMaximum(15);
		sliderForceApp.setBounds(15, 251, 128, 14);
		panel_entree.add(sliderForceApp);
		sliderForceApp.setValue(10);

		JLabel lblMs = new JLabel("m/s");
		lblMs.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblMs.setBounds(223, 179, 48, 14);
		panel_entree.add(lblMs);

		JLabel lblN = new JLabel("N durant 0.01s");
		lblN.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblN.setBounds(180, 267, 102, 14);
		panel_entree.add(lblN);

		JLabel lblChargeDesParticules = new JLabel("Charge des particules :");
		lblChargeDesParticules.setFont(new Font("Century", Font.PLAIN, 14));
		lblChargeDesParticules.setBounds(15, 276, 150, 27);
		panel_entree.add(lblChargeDesParticules);

		label = new JLabel("1");
		label.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		label.setBounds(184, 310, 18, 14);
		panel_entree.add(label);

		JLabel lblC = new JLabel("C");
		lblC.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblC.setBounds(264, 310, 36, 14);
		panel_entree.add(lblC);

		sliderChargeParticule = new JSlider();
		sliderChargeParticule.setBackground(new Color(255, 255, 255));
		sliderChargeParticule.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				modifierChargeParticule();

			}
		});
		sliderChargeParticule.setValue(1);
		sliderChargeParticule.setMinimum(1);
		sliderChargeParticule.setMaximum(10);
		sliderChargeParticule.setBounds(15, 314, 136, 14);
		panel_entree.add(sliderChargeParticule);

		JLabel label_1 = new JLabel("\u00B1");
		label_1.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		label_1.setBounds(169, 303, 8, 27);
		panel_entree.add(label_1);

		JLabel lblX_1 = new JLabel("x 10");
		lblX_1.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblX_1.setBounds(203, 310, 68, 14);
		panel_entree.add(lblX_1);

		JLabel lblChargeDuPersonnage = new JLabel("Charge du personnage :");
		lblChargeDuPersonnage.setFont(new Font("Century", Font.PLAIN, 14));
		lblChargeDuPersonnage.setBounds(15, 348, 165, 27);
		panel_entree.add(lblChargeDuPersonnage);

		JLabel label_3 = new JLabel("\u00B1");
		label_3.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		label_3.setBounds(169, 372, 8, 27);
		panel_entree.add(label_3);

		label_5 = new JLabel("1");
		label_5.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		label_5.setBounds(183, 380, 18, 14);
		panel_entree.add(label_5);

		sliderChargePersonnage = new JSlider();
		sliderChargePersonnage.setBackground(new Color(255, 255, 255));
		sliderChargePersonnage.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				modifierChargePersonnage();


			}
		});
		sliderChargePersonnage.setValue(1);
		sliderChargePersonnage.setMinimum(1);
		sliderChargePersonnage.setMaximum(10);
		sliderChargePersonnage.setBounds(15, 386, 136, 14);
		panel_entree.add(sliderChargePersonnage);

		JLabel label_6 = new JLabel("C");
		label_6.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		label_6.setBounds(264, 380, 36, 14);
		panel_entree.add(label_6);

		JLabel lblX_2 = new JLabel("x 10");
		lblX_2.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblX_2.setBounds(203, 380, 68, 14);
		panel_entree.add(lblX_2);

		JLabel lblRay = new JLabel("Rayon d'action :");
		lblRay.setFont(new Font("Century", Font.PLAIN, 14));
		lblRay.setBounds(15, 420, 165, 27);
		panel_entree.add(lblRay);

		label_7 = new JLabel("7");
		label_7.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		label_7.setBounds(183, 457, 33, 14);
		panel_entree.add(label_7);

		JLabel lblCm = new JLabel("m");
		lblCm.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblCm.setBounds(203, 457, 36, 14);
		panel_entree.add(lblCm);

		sliderRayonAction = new JSlider();
		sliderRayonAction.setToolTipText("");
		sliderRayonAction.setForeground(new Color(0, 0, 255));
		sliderRayonAction.setBackground(new Color(255, 255, 255));
		sliderRayonAction.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				modifierRayonAction();
				
				

			}
		});
		sliderRayonAction.setValue(7);
		sliderRayonAction.setMinimum(7);
		sliderRayonAction.setMaximum(20);
		sliderRayonAction.setBounds(15, 463, 128, 14);
		panel_entree.add(sliderRayonAction);

		JLabel lblMasseDuPersonnage = new JLabel("Masse du personnage :");
		lblMasseDuPersonnage.setFont(new Font("Century", Font.PLAIN, 14));
		lblMasseDuPersonnage.setBounds(15, 495, 165, 27);
		panel_entree.add(lblMasseDuPersonnage);

		JLabel lblxkg = new JLabel("x 10       kg");
		lblxkg.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblxkg.setBounds(215, 538, 76, 19);
		panel_entree.add(lblxkg);

		JLabel lblTemps = new JLabel("Temps max du jeu :");
		lblTemps.setFont(new Font("Century", Font.PLAIN, 14));
		lblTemps.setBounds(15, 562, 165, 27);
		panel_entree.add(lblTemps);

		JLabel labelTempsUnite = new JLabel("s");
		labelTempsUnite.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		labelTempsUnite.setBounds(220, 594, 16, 19);
		panel_entree.add(labelTempsUnite);


		//INITILISER LES BOUTON / Roger

		JButton buttonProton = new JButton("New button");
		buttonProton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editeur.ajoutParticuleObstaclePositive(new Vecteur(40,40));

			}
		});
		JButton buttonElectron = new JButton("New button");
		buttonElectron.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editeur.ajoutParticuleObstacleNegative(new Vecteur(40,40));
			}
		});
		JButton btnObstaclehHorizontal = new JButton("New button");
		btnObstaclehHorizontal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editeur.ajoutBarreHorizontale(new Vecteur(40, 40));
			}
		});
		JButton btnObstaclehVertical = new JButton("New button");
		btnObstaclehVertical.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editeur.ajoutBarreVerticale(new Vecteur(40,40));
			}
		});
		JButton btnObstaclehTriangle = new JButton("New button");
		btnObstaclehTriangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editeur.ajoutTriangle(new Vecteur(40,40));
			}
		});

		// Proton / Roger
		buttonProton.setBounds(83, 85, 36, 36);
		panel_entree.add(buttonProton);
		associerBoutonAvecImage(buttonProton,"proton.jpg");
		buttonProton.setVisible(true);

		//Electron/ Roger
		buttonElectron.setBounds(83, 85, 36, 36);
		panel_entree.add(buttonElectron);
		associerBoutonAvecImage(buttonElectron,"electron.png");
		buttonElectron.setVisible(false);

		//Obstacle Horizontal/ Roger
		btnObstaclehHorizontal.setBounds(54, 28, 89, 23);
		panel_entree.add(btnObstaclehHorizontal);
		associerBoutonAvecImage(btnObstaclehHorizontal,"horizontal-line.png");
		btnObstaclehHorizontal.setVisible(true);

		//Obstacle Vertical/ Roger
		btnObstaclehVertical.setBounds(58, 11, 85, 63);
		panel_entree.add(btnObstaclehVertical);
		associerBoutonAvecImage(btnObstaclehVertical,"vertical.png");
		btnObstaclehVertical.setVisible(false);

		//Obstacle Triangle / Roger
		btnObstaclehTriangle.setBounds(75, 11, 61, 40);
		panel_entree.add(btnObstaclehTriangle);
		associerBoutonAvecImage(btnObstaclehTriangle,"triangle.png");
		btnObstaclehTriangle.setVisible(false);

		// boutton droite Obstacles  / Roger
		JButton btnDroiteObstacle = new JButton("New button");
		btnDroiteObstacle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click_droite_obstacle+=1;
				if(click_droite_obstacle==1) {
					btnObstaclehHorizontal.setVisible(true);
					btnObstaclehVertical.setVisible(false);
					btnObstaclehTriangle.setVisible(false);

				}
				if(click_droite_obstacle==2) {
					btnObstaclehHorizontal.setVisible(false);
					btnObstaclehVertical.setVisible(true);
					btnObstaclehTriangle.setVisible(false);
					
				}
				if(click_droite_obstacle==3) {
					btnObstaclehHorizontal.setVisible(false);
					btnObstaclehVertical.setVisible(false);
					btnObstaclehTriangle.setVisible(true);
					click_droite_obstacle=0;
				}
			}
		});
		btnDroiteObstacle.setBounds(153, 21, 33, 36);
		panel_entree.add(btnDroiteObstacle);
		// boutton gauche Obstacles / Roger
		JButton btnGaucheObstacle = new JButton("New button");
		btnGaucheObstacle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click_gauche_obstacle+=1;

				if(click_gauche_obstacle==1) {
					btnObstaclehHorizontal.setVisible(true);
					btnObstaclehVertical.setVisible(false);
					btnObstaclehTriangle.setVisible(false);
				}
				if(click_gauche_obstacle==3) {
					btnObstaclehHorizontal.setVisible(false);
					btnObstaclehVertical.setVisible(false);
				
					btnObstaclehTriangle.setVisible(true);
					click_gauche_obstacle=0;
				}
				if(click_gauche_obstacle==2) {
					btnObstaclehHorizontal.setVisible(false);
					btnObstaclehVertical.setVisible(true);
					btnObstaclehTriangle.setVisible(false);
					
				}
			}
		});
		btnGaucheObstacle.setBounds(15, 21, 33, 36);
		panel_entree.add(btnGaucheObstacle);

		associerBoutonAvecImage(btnGaucheObstacle,"left.png");
		associerBoutonAvecImage(btnDroiteObstacle,"right.png");



		// bouton gauche ecouteur Proton/Electron / Roger
		JButton btn_gauche_particule = new JButton("New button");
		btn_gauche_particule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click_gauche_proton+=1;
				if (click_gauche_proton==1) {
					buttonProton.setVisible(true);
					buttonElectron.setVisible(false);
				}
				if (click_gauche_proton==2) {

					buttonProton.setVisible(false);
					buttonElectron.setVisible(true);
					click_gauche_proton=0;
				}

			}
		});
		btn_gauche_particule.setBounds(15, 85, 36, 36);
		panel_entree.add(btn_gauche_particule);

		// bouton droite ecouteur  Proton/Electron / Roger
		JButton btn_droite_particule = new JButton("New button");
		btn_droite_particule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click_droit_proton+=1;
				if (click_droit_proton==1) {
					buttonProton.setVisible(false);
					buttonElectron.setVisible(true);
				}
				if (click_droit_proton==2) {

					buttonProton.setVisible(true);
					buttonElectron.setVisible(false);
					click_droit_proton=0;
				}
			}
		});

		btn_droite_particule.setBounds(153, 85, 36, 36);
		panel_entree.add(btn_droite_particule);

		associerBoutonAvecImage(btn_gauche_particule,"left.png");
		associerBoutonAvecImage(btn_droite_particule,"right.png");

		JButton btnPoubelle = new JButton("New button");
		btnPoubelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editeur.effacerObstacle(editeur.getListeObstacles().size()-1);
			}
		});
		btnPoubelle.setBounds(215, 50, 56, 36);
		panel_entree.add(btnPoubelle);

		associerBoutonAvecImage(btnPoubelle,"undo.png");
		
		JLabel lblNewLabel = new JLabel("(max 120 s)");
		lblNewLabel.setBounds(75, 620, 89, 14);
		panel_entree.add(lblNewLabel);
		
		JSlider sliderMassePerso = new JSlider();
		
		
		
		sliderMassePerso.setValue(1);
		sliderMassePerso.setToolTipText("");
		sliderMassePerso.setMinimum(1);
		sliderMassePerso.setMaximum(10);
		sliderMassePerso.setForeground(Color.BLUE);
		sliderMassePerso.setBackground(Color.WHITE);
		sliderMassePerso.setBounds(15, 533, 128, 14);
		panel_entree.add(sliderMassePerso);
		
		sliderTemps = new JSlider();
		
		sliderTemps.setValue(30);
		sliderTemps.setToolTipText("");
		sliderTemps.setMinimum(30);
		sliderTemps.setMaximum(120);
		sliderTemps.setForeground(Color.BLUE);
		sliderTemps.setBackground(Color.WHITE);
		sliderTemps.setBounds(15, 601, 128, 14);
		panel_entree.add(sliderTemps);
		
		JLabel label_masse_perso = new JLabel("1");
		label_masse_perso.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		label_masse_perso.setBounds(180, 540, 33, 14);
		panel_entree.add(label_masse_perso);
		
		sliderMassePerso.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				
				editeur.getPersonnage().setMasseEnKg(sliderMassePerso.getValue()*Math.pow(10, -27));
				
				label_masse_perso.setText(""+sliderMassePerso.getValue());
			}
		});
		
		JLabel label_temps = new JLabel("30");
		label_temps.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		label_temps.setBounds(180, 596, 33, 14);
		panel_entree.add(label_temps);
		
		JLabel lblX = new JLabel("x 10");
		lblX.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblX.setBounds(201, 247, 68, 14);
		panel_entree.add(lblX);
		
		JLabel lblNewLabel_1 = new JLabel("-25");
		lblNewLabel_1.setFont(new Font("Century Gothic", Font.PLAIN, 9));
		lblNewLabel_1.setBounds(225, 237, 16, 14);
		panel_entree.add(lblNewLabel_1);
		
		JLabel label_9 = new JLabel("-17");
		label_9.setFont(new Font("Century Gothic", Font.PLAIN, 9));
		label_9.setBounds(225, 298, 16, 14);
		panel_entree.add(label_9);
		
		JLabel label_10 = new JLabel("-16");
		label_10.setFont(new Font("Century Gothic", Font.PLAIN, 9));
		label_10.setBounds(225, 370, 16, 14);
		panel_entree.add(label_10);
		
		JLabel label_11 = new JLabel("-27");
		label_11.setFont(new Font("Century Gothic", Font.PLAIN, 9));
		label_11.setBounds(235, 527, 16, 14);
		panel_entree.add(label_11);
		
		JLabel label_2 = new JLabel("2");
		label_2.setFont(new Font("Century Gothic", Font.PLAIN, 9));
		label_2.setBounds(246, 170, 16, 14);
		panel_entree.add(label_2);
		
		sliderTemps.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				label_temps.setText(""+sliderTemps.getValue());
			}
		});
		
		JLabel lblModeCrateur = new JLabel("Cr\u00E9er votre niveau !");
		lblModeCrateur.setForeground(new Color(0, 0, 128));
		lblModeCrateur.setBackground(new Color(25, 25, 112));
		lblModeCrateur.setFont(new Font("Lucida Console", Font.PLAIN, 35));
		lblModeCrateur.setBounds(315, 19, 424, 43);
		frameEditeur.getContentPane().add(lblModeCrateur);

		JButton btnReinitialiser = new JButton("Reinitialiser");
		btnReinitialiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editeur.reinitialiser();
			}
		});
		btnReinitialiser.setFont(new Font("Century", Font.PLAIN, 20));
		btnReinitialiser.setBounds(915, 807, 173, 43);
		frameEditeur.getContentPane().add(btnReinitialiser);
		
				JButton btnSauvegarder = new JButton("Sauvegarder");
				btnSauvegarder.setBounds(915, 746, 173, 43);
				frameEditeur.getContentPane().add(btnSauvegarder);
				btnSauvegarder.setFont(new Font("Century", Font.PLAIN, 20));
				
				JMenuBar menuBar = new JMenuBar();
				frameEditeur.setJMenuBar(menuBar);
				
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
				btnSauvegarder.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						enregistrer();
					}
				});

		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameEditeur.dispose();
				PanelMenu menu = new PanelMenu();
				menu.NewScreen();
			}
		});
	} // fin junior


	//par Clement
	/**
	 * Methode qui met a jour le lblGraviteValeur
	 */
	private void modifierGravite( ) {
		lblGraviteValeur.setText(""+sliderGravite.getValue());

	}



	//par Clement
	/**
	 * Methode qui met a jour le label ou la charge de la particule est indiquee
	 */
	private void modifierChargeParticule() {
		label.setText(""+sliderChargeParticule.getValue());
	}
	//par Clement
	/**
	 * methode qui modifie la charge du Personnage et qui met a jour le label ou la charge du personnage est indiquee
	 */
	private void modifierChargePersonnage() {
		label_5.setText(""+sliderChargePersonnage.getValue() );
		editeur.setChargePerso(sliderChargePersonnage.getValue()*Math.pow(10, -16));
	}
	
	/**
	 * Methode qui met a jour le label ou le rayon d'action est indiquee
	 */
	//par Clement
	private void modifierRayonAction() {
		editeur.setRayonAction(sliderRayonAction.getValue());
		
		label_7.setText(""+sliderRayonAction.getValue());
		repaint();
	}
	//par Clement
	/**
	 * Methode permettant d'enregistrer un niveau du mode créateur sur le bureau
	 */
	private void enregistrer() {
		nomNiv=JOptionPane.showInputDialog("Écrivez le nom de votre niveau afin de pouvoir le reconnaitre");
		Vecteur accGraviter= new Vecteur (0,-1*sliderGravite.getValue());
		
		niv= new Niveau (editeur.getPersonnage(),editeur.getPointArrive(),(int)sliderTemps.getValue() , editeur.getListeObstacles(),sliderChargeParticule.getValue()*Math.pow(10, -17),sliderRayonAction.getValue(),sliderForceApp.getValue()*Math.pow(10, -25),accGraviter,nomNiv , editeur.getArreterTemps(),editeur.getAfficherVecteur() );

		
		GestionaireFichierNiveau gestionFich = new GestionaireFichierNiveau();
		gestionFich.ecrireFichierBinBureau(niv);
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

	// par Junior Peumi
	/**
	 * Methode permettant de selectionner un obstacle dans le panneau editeur et de le manipuler.
	 * @param arg0 objet ecouteur
	 */
	private void selectionnerObstacle(MouseEvent arg0) {
		//  mettre dans une mthode private
		int posX = (int) (arg0.getX() / editeur.getMatMC().getScaleX());
		int posY = (int) (arg0.getY() / editeur.getMatMC().getScaleY());
		move = false;
		// get zone du click
		Rectangle2D rectClick = new Rectangle2D.Double(posX, posY, 1, 1);

		// initialisation des donnes pour listener
		double largeurObstacle = 0;
		double hauteurObstacle = 0;
		int posTriX=0;
		int posTriY=0;
		// checker pour le dernier lment ajout
		for(int index=editeur.getListeObstacles().size()-1; index>=0; index--) {

			// get coordonnes et dimensions de l'obstacle selon son type.
			double posXObstacle = editeur.getListeObstacles().get(index).getPosition().getX();
			double posYObstacle = editeur.getListeObstacles().get(index).getPosition().getY();
			if(editeur.getListeObstacles().get(index) instanceof ParticuleObstacles) {
				largeurObstacle = editeur.getListeObstacles().get(index).getForme().getBounds().getHeight();
				hauteurObstacle = largeurObstacle;
			} else if(!(editeur.getListeObstacles().get(index) instanceof Triangle)) {
				largeurObstacle = ((Shape) (editeur.getListeObstacles().get(index).getForme())).getBounds().getWidth();
				hauteurObstacle = ((Shape) (editeur.getListeObstacles().get(index).getForme())).getBounds().getHeight();
			} 
			// crer une zone non visible de l'osbtacle.
			Rectangle2D rectObstacle = new Rectangle2D.Double(posXObstacle, posYObstacle, largeurObstacle, hauteurObstacle);

			// condition 1 : le click est dans la zone de l'obstacle
			// condition 2 : si il est le dernier d'entre deux
			if(rectClick.intersects(rectObstacle)) {
				move = true;
				indexObstacleABouger = index;
				//estSelectione.setText("Slction : " + editeur.getListeObstacles().get(indexObstacleABouger).getForme().getBounds().toString()); 
				System.out.println("selected");
			} else {
				System.out.println("Vous n'avez pas selectionné d'éléments.");
			}
		}	


	}

	// par Junior Peumi
	/**
	 * Methode permettant de selectionner un obstacle de type triangle.
	 * @param arg0 objet ecouteur
	 */
	private void selectionnerTriangle (MouseEvent arg0) { 
		Point p = new Point((int)(arg0.getX() / editeur.getMatMC().getScaleX()), (int)(arg0.getY() / editeur.getMatMC().getScaleX()));
		moveTriangle = false ;
		for(int index=editeur.getListeObstacles().size()-1; index>=0; index--) {
			if((editeur.getListeObstacles().get(index) instanceof Triangle)) {
		Polygon triangle = (Polygon) editeur.getListeObstacles().get(index).getForme();
		if(triangle.contains(p)) {
		System.out.println("select triangle");
		moveTriangle = true;
		indexTriangleABouger =index;
		}
			}
	}
	}

	// par Junior Peumi
	/**
	 * Methode permettant de selectionner le personnage pour modifier sa position initiale.
	 * @param arg0 objet ecouteur
	 */
	private void selectionnerperso(MouseEvent arg0) {
		
		
		
		Rectangle2D rectPerso = new Rectangle2D.Double(editeur.getPersonnage().getPosition().getX(), editeur.getPersonnage().getPosition().getY() ,editeur.getPersonnage().getDiametre(),editeur.getPersonnage().getDiametre());
		
		  
		Rectangle2D rectClick = new Rectangle2D.Double((int) (arg0.getX() / editeur.getMatMC().getScaleX())
				, (int) (arg0.getY() / editeur.getMatMC().getScaleY()), 1, 1);
		
		if (rectClick.intersects(rectPerso)){
			moveperso = true ; 

		}
		
	}
	
	// par Junior Peumi
	/**
	 * Methode permettant de selectionner le point d'arrive
	 * @param arg0 objet ecouteur
	 */
	private void selectionnerpointArrivee(MouseEvent arg0) {
		moveArrivee = false ; 
		Rectangle2D rectPerso = new Rectangle2D.Double(editeur.getPointArrive().getVecteurPosition().getX(), editeur.getPointArrive().getVecteurPosition().getY() ,editeur.getPointArrive().getDiametre(),editeur.getPointArrive().getDiametre());
		Rectangle2D rectClick = new Rectangle2D.Double((int) (arg0.getX() / editeur.getMatMC().getScaleX()), (int) (arg0.getY() / editeur.getMatMC().getScaleY()), 1, 1);
		if (rectClick.intersects(rectPerso)){
			moveArrivee = true ; 
			System.out.println("select arriver");

		}
	}
	private void selectionnerMechant(MouseEvent arg0) {
		movemechant= false;
		Rectangle2D rectclic= new Rectangle2D.Double((int) (arg0.getX() / editeur.getMatMC().getScaleX()), (int) (arg0.getY() / editeur.getMatMC().getScaleY()), 1, 1);
		//Rectangle2D rectMechant = new Rectangle2D.Double(editeur.getMechant().getPosition().getX(), editeur.getMechant().getPosition().getY() ,editeur.getMechant().getDiametre(),editeur.getMechant().getDiametre());

		/*if (rectclic.intersects(rectMechant)){
			movemechant= true;
			System.out.println("mechabt");
		}*/
		
		
	}
	// par Junior Peumi
	/**
	 * Mthode permettant de bouger un obstacle aprs l'avoir slctionner avec la mthode selectionnerObstacle().
	 * @param arg0 objet ecouteur
	 */
	public void mouseDragged(MouseEvent arg0) {
		if(move) {
			System.out.println("drag");
			editeur.getListeObstacles().get(indexObstacleABouger).getPosition().setXY((int) (arg0.getX() / editeur.getMatMC().getScaleX()), (int) (arg0.getY() / editeur.getMatMC().getScaleY()));
			//System.out.println(editeur.getListeObstacles().get(indexObstacleABouger).getPosition());
			editeur.redessiner();
			move = true;
		}
	}

	// par Junior Peumi
	/**
	 * Methode permettant de lacher l'obstacle apres l'avoir deplacer
	 * @param arg0 objet ecouteur
	 */
	private void dropObstacle(MouseEvent arg0) {
		if(move) {
			System.out.println("released");
			editeur.getListeObstacles().get(indexObstacleABouger).getPosition().setXY((int) (arg0.getX() / editeur.getMatMC().getScaleX()), (int) (arg0.getY() / editeur.getMatMC().getScaleY()));
			//System.out.println(editeur.getListeObstacles().get(indexObstacleABouger).getPosition());
			editeur.redessiner();
			//estSelectione.setText("Slction : rien");
			move = false;
		} else if (moveperso) {
			
			if(arg0.getX()>=editeur.getPersonnage().getDiametre()/2 && arg0.getY() >=editeur.getPersonnage().getDiametre()/2 && arg0.getX() <= 810- editeur.getPersonnage().getDiametre()/2  
					&& arg0.getY()<= 625-editeur.getPersonnage().getDiametre()/2) 
			{
				
			editeur.getPersonnage().setPosition( vecteur = new Vecteur((int) (arg0.getX() / editeur.getMatMC().getScaleX()), (int) (arg0.getY() / editeur.getMatMC().getScaleY())));
			editeur.redessiner();
			moveperso = false;
			
			
			}
			
			
		}else if (moveArrivee) {
			
			
			if(arg0.getX()>=editeur.getPointArrive().getDiametre()/2 && arg0.getY() >=editeur.getPointArrive().getDiametre()/2 && arg0.getX() <= 790- editeur.getPointArrive().getDiametre()/2  
					&& arg0.getY()<= 600-editeur.getPointArrive().getDiametre()/2) 
				
			{
			editeur.getPointArrive().getVecteurPosition().setXY((int) (arg0.getX() / editeur.getMatMC().getScaleX()), (int) (arg0.getY() / editeur.getMatMC().getScaleY()));
			editeur.redessiner();
			moveTriangle= false;
			}
		}else if (moveTriangle) {
			editeur.getListeObstacles().get(indexTriangleABouger).getPosition().setXY((int) (arg0.getX() / editeur.getMatMC().getScaleX()), (int) (arg0.getY() / editeur.getMatMC().getScaleY()));		
			editeur.redessiner();
			moveTriangle= false;
		}/*	editeur.getMechant().getPosition().setXY((int) (arg0.getX() / editeur.getMatMC().getScaleX()), (int) (arg0.getY() / editeur.getMatMC().getScaleY()));
			editeur.redessiner();
			movemechant = false ; 
		}*/
	}
}




