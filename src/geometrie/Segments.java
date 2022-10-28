package geometrie;

import java.awt.Graphics2D;


import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.io.Serializable;

import obstacles.Dessinable;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * La classe segment qui defini un segment qui represente les cotes des obstacles 
 * @author Roger
 * 
 */
public class Segments  implements Dessinable, Serializable {

	private Point2D.Double p1;
	private Point2D.Double p2;
	private Line2D.Double formeSegment;
	private Line2D.Double vecteurNormale;
	private Line2D.Double flecheVecteur;
	private double angleFleche = 0.3;   
	private double longueur;
	
	private Vecteur normale;
	private Vecteur segment;
	private Vecteur segmentNormalise;
	
	
	
	/**
	 * le constructeur ou les deux point du segment sont precise
	 * @param p1 premier point
	 * @param p2 deuxieme point 
	 */
	public Segments(Point2D.Double p1, Point2D.Double p2 ) {
		this.p1=p1;
		this.p2=p2;
		this.formeSegment=new Line2D.Double(p1,p2); 
		this.longueur= Math.sqrt(Math.pow(p2.getX()-p2.getX()	, 2)+Math.pow(p2.getY()-p2.getY()	, 2));
		
		this.segment= new Vecteur((p2.x-p1.x),(p2.y-p1.y));

	
	}
	
	/**
	 * Methode pour dessiner le segmenet selon la matrice de translation 
	 * 
	 */
	public void dessiner (Graphics2D g2d,  AffineTransform mat) {
		
		formeSegment = new Line2D.Double(p1,p2); 
		
		
		g2d.draw(formeSegment);  
		g2d.draw(vecteurNormale);  
		
     }
	
    
    public double getLongueur() {
    	
   	   return this.longueur;
   	  
      }
    
    
    public void setLongeur (int longueur) {
    	
    	this.longueur = longueur ;
    }
   
   /**Methode qui permet de trouver s'il y a collision ou pas avec le perosnnage
    * 
    * @param p le personnage 
    * @return en collision out pas 
    */
    public boolean enCollision(Personnage p) {
    	
		double distance =  formeSegment.ptSegDist(p.getCentre());
		if(distance<=(p.getDiametre()/2)) {
			  System.out.println("Test de collision entre le segment aux extremites suivantes ["+ p1.getX() + "," + p1.getY() + "] et [" + p2.getX() + "," + p2.getY() + "] et le cercle de centre " + p.getCentre().getX() + "");
			   
			return true;
			   
		}
		else {
			return false;
		}
    }
   
    public Vecteur getNormale()  {
	   
	  try {
		this.normale = new Vecteur(-segment.normalise().getY(),segment.normalise().getX());
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	   
	   
	return this.normale;
	   
	   
   }
    
    
}
