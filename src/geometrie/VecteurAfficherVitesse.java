package geometrie;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.io.Serializable;

import obstacles.Dessinable;



/**
 * Classe qui permet de dessiner le vecteur vitesse du personnage 
 * @author nouhaila
 *
 */
public class VecteurAfficherVitesse implements Dessinable, Serializable {
	
	
	private Vecteur vitesse;
	private Vecteur position;
	private double diametrePerso;
	private double angle;
	private double rotationX ;
	private double rotationY;
	private Path2D vecteur = new Path2D.Double();
	private Path2D fleche = new Path2D.Double();
	
	
	
	public VecteurAfficherVitesse(Vecteur vitesse, Vecteur position , double diametrePerso) {
		
		this.vitesse = vitesse;
		this.position = position ;
		this.diametrePerso = diametrePerso ;
		
	}
	
	
	
	@Override
	public void dessiner(Graphics2D g2d, AffineTransform matC) {
		
		g2d.setStroke(new BasicStroke(6));
		g2d.setColor(Color.BLACK);

		
		double rayon = diametrePerso/2 ;
		
		AffineTransform mat = new AffineTransform(matC);

		
	  if( vitesse != null) {
			
           if (Math.signum(vitesse.getX()) == 1 ) {
			        
				
		       vecteur.moveTo(position.getX() + rayon, position.getY() + rayon);
		       vecteur.lineTo(position.getX() + rayon + vitesse.getX()/10,position.getY() + vitesse.getY() / 10 + rayon);



		       fleche.moveTo(position.getX() + rayon + vitesse.getX()/10  ,position.getY() + vitesse.getY() / 10 + 2 *rayon / 3);  

		       fleche.lineTo(position.getX() + rayon + vitesse.getX() / 10,position.getY() + vitesse.getY() / 10 + 4*rayon / 3 );
		       fleche.lineTo(position.getX() + rayon + vitesse.getX() / 10 + rayon/3   ,position.getY() + vitesse.getY() / 10 + rayon);
		       fleche.lineTo(position.getX() + rayon + vitesse.getX() / 10,position.getY() + vitesse.getY() / 10 + 2 *rayon / 3);


		       rotationX = position.getX() + rayon + vitesse.getX() / 10;
		       rotationY = position.getY() + vitesse.getY() / 10 + rayon;


		               } else {


					       vecteur.moveTo(position.getX() + rayon, position.getY() + rayon);
					       vecteur.lineTo(position.getX() + rayon + vitesse.getX() / 10,position.getY() + vitesse.getY() / 10 +rayon);


					       fleche.moveTo(position.getX() + rayon + vitesse.getX() / 10, position.getY() + vitesse.getY() / 10 + 2 *rayon / 3 );

					       fleche.lineTo(position.getX()+ rayon + vitesse.getX()/10  ,   position.getY() + vitesse.getY() / 10 + 4*rayon / 3 );
					       fleche.lineTo(position.getX() + rayon + vitesse.getX()/10 - rayon/3  , position.getY() + vitesse.getY() / 10 + rayon);
					       fleche.lineTo(position.getX() + rayon + vitesse.getX() / 10,position.getY() + vitesse.getY() / 10 + 2 *rayon / 3);


					       rotationX = position.getX()  + rayon + vitesse.getX() / 10;
					       rotationY = position.getY() + vitesse.getY() / 10 + rayon;



			}


	       g2d.draw(mat.createTransformedShape(vecteur));
	       
	       angle = Math.atan(vitesse.getY() / vitesse.getX());
	       
	       mat.rotate(angle, rotationX, rotationY);
	       
	       g2d.fill(mat.createTransformedShape(fleche));

		}
	}




}

