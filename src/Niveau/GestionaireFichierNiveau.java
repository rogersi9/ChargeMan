package Niveau;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import javax.swing.JOptionPane;




/**
 * Classe qui permet de sauvegarder un niveau en fichier binaire et de le lire.
 * @author Clement Harvey
 * @author Caroline Houle
 */

public class GestionaireFichierNiveau {

	private String sousDossierSurBureau = "Niveau ChargeMan";

	/**
	 * Methode qui permet de crer un rpertoire sur le bureau contenant un niveau en fichier binaire.
	 * @param niv Le niveau en question.
	 */
	public void ecrireFichierBinBureau(Niveau niv) {
		
		// chemin d'acces au fichier de travail, qui sera sur le Bureau
		File dossier = new File(System.getProperty("user.home"), "Desktop" + "\\" + sousDossierSurBureau);
		
		//Version pour nouhaila
		//File dossier = new File(System.getProperty("user.home"), "OneDrive"+"\\" +"Bureau" + "\\" + sousDossierSurBureau);
				
		if (dossier.mkdir()) {
			JOptionPane.showMessageDialog(null,	"Le dossier " + dossier.toString() + " a t cr car il n'existait pas...");
		}
		File fichierDeTravail = new File(dossier + "\\" + niv.getNom());
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(fichierDeTravail));
			
			oos.writeObject(niv);
			
			JOptionPane.showMessageDialog(null, "\nLe niveau a t sauvegarder avec succs. \nLe fichier " + fichierDeTravail.toString() + " est pret pour la lecture!");
		}
		
		catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erreur  l'criture:");
			e.printStackTrace();
		}
		
		finally {
			// on excutera toujours ceci, erreur ou pas
			try {
				oos.close();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Erreur rencontre lors de la fermeture!");
			}
		}	
	}
	/**
	 * Methode qui retourne un niveau aprs la lecture d'un fichier binaire sur le bureau.
	 * @param nomNiv Le nom du niveau  lire.
	 * @return Le niveau lu.
	 */
		public Niveau lireFichierBinBureau(String nomNiv) {
		ObjectInputStream ois = null;
		Niveau niv1 = null;

		// chemin d'acces au fichier de travail, qui sera sur le Bureau
		File fichierDeTravail = new File(System.getProperty("user.home"),"Desktop" + "\\" + sousDossierSurBureau + "\\" + nomNiv);
		
		
		//Version pour nouhaila
		//File fichierDeTravail = new File(System.getProperty("user.home"), "OneDrive"+"\\" +"Bureau" + "\\" + sousDossierSurBureau + "\\" + nomNiv);


		// on teste si le fichier  lire existe
		if (!fichierDeTravail.exists()) {
			JOptionPane.showMessageDialog(null,	"Problme! Le fichier " + fichierDeTravail.toString() + " n'existe pas...");
		}

		try {
			ois = new ObjectInputStream(new FileInputStream(fichierDeTravail));
			 niv1 = (Niveau) ois.readObject();
			
			JOptionPane.showMessageDialog(null, "Le fichier a t lu avec succs");
			
		} 
		
		catch (ClassNotFoundException e) {
			System.out.println("L'objet lu est d'une classe inconnue");
			e.printStackTrace();
		}
		
		catch (InvalidClassException e) {
			System.out.println("Les classes utilises pour l'criture et la lecture diffrent!");
			e.printStackTrace();
		}
		
		catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Fichier  " + fichierDeTravail.toString() + "  introuvable!");
			System.exit(0);
		}

		catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erreur rencontree lors de la lecture");
			e.printStackTrace();
			System.exit(0);
		}

		finally {
			// on excutera toujours ceci, erreur ou pas
			try {
				ois.close();
				
			} catch (IOException e) {
				System.out.println("Erreur rencontre lors de la fermeture!");
			}
			
		} // fin finally
		return niv1;
		
}
		/**
		 * Methode qui retourne un niveau apres la lecture d'un fichier binaire dans le fichier ressource.
		 * @param nomNiv Le nom du niveau  lire.
		 * @return Le niveau lu.
		 */
		public Niveau lireFichierBinBuildPath(String nomNiv) {
			
			Niveau nivLu=null;
			ObjectInputStream ois=null;
			InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(nomNiv);
			
			if (is == null) {
				JOptionPane.showMessageDialog(null, "Incapable de trouver ce fichier dans le BuildPath (ou dans le jar exécutable) : " + nomNiv );
				System.exit(0);
			}


			 //ce fichier a été conçu d'avance et placé dans un dossier qui fait partie du Build Path
			try {
				 ois = new ObjectInputStream(is);
				 //on lit d'un coup un objet stocké dans le fichier
				 nivLu = (Niveau) ois.readObject(); 
				// JOptionPane.showMessageDialog(null, "Lecture du fichier " + nomNiv + " avec succès." );
				
			} 	
			catch (ClassNotFoundException e) {
				JOptionPane.showMessageDialog(null,"L'objet lu est d'une classe inconnue");
				e.printStackTrace();
			}
			catch (InvalidClassException e) {
				JOptionPane.showMessageDialog(null,"Les classes utilisées pour l'écriture et la lecture diffèrent!");
				e.printStackTrace();
			}
			catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null, "Fichier " + nomNiv + "  introuvable!");
				System.exit(0);
			}
			
			catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Erreur rencontree lors de la lecture " + nomNiv);
				e.printStackTrace();
				System.exit(0);
			}
			
			finally {
				//on exécutera toujours ceci, erreur ou pas
			  	try { 
			  		ois.close();
			  	}
			    catch (IOException e) { 
			    	System.out.println("Erreur rencontrée lors de la fermeture!"); 
			    }
			}//fin finally
			return nivLu;
		}
	
	
}
