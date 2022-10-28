package obstacles;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

/**
 * 
 * Interface qui sert a dessiner des objets
 * 
 * @author Nouhaila Aater
 *
 */
public interface Dessinable {
	
	 public void dessiner( Graphics2D g2d , AffineTransform mat);
}
