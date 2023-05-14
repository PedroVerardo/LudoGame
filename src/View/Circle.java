package View;

import java.awt.*;

import java.awt.geom.*;

public class Circle {
	 public static void drawCircle(AffineTransform affine, double radius, Color color, Point2D.Double centerPt, Graphics2D g2d) {
	        if (affine == null) {
	            affine = new AffineTransform();
	            affine.setToIdentity();
	        }
	        boolean drawPath = true;
	        Color origColor = null;
	        Shape theCircle = new Ellipse2D.Double(centerPt.x - radius,
	                centerPt.y - radius, 2.0 * radius, 2.0 * radius);
	        if (drawPath) {
	            g2d.setColor(color);
	            g2d.draw(theCircle);
	            g2d.setColor(origColor);
	        }
	    }
}
