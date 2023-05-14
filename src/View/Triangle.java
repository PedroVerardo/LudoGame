package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.Shape;

import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;

public class Triangle {
	public static Shape drawTriangleGreen(Graphics2D graphics, double x, double y) {
        final GeneralPath triangle = new GeneralPath();
        triangle.setWindingRule(Path2D.WIND_EVEN_ODD);
        triangle.moveTo(x - 40, y);
        triangle.lineTo(x + 20, y + 60);
        triangle.lineTo(x + 80, y);
        triangle.lineTo(x ,y);
        triangle.closePath();/* w  ww. jav  a 2s.  com*/

        graphics.setPaint(Color.green);
        graphics.fill(triangle);
        graphics.setPaint(Color.black);
        graphics.draw(triangle);

        return triangle;
    }
	public static Shape drawTriangleYellow(Graphics2D graphics, double x, double y) {
        final GeneralPath triangle = new GeneralPath();
        triangle.setWindingRule(Path2D.WIND_EVEN_ODD);
        triangle.moveTo(x + 40, y - 40);
        triangle.lineTo(x - 20 , y + 20);
        triangle.lineTo(x + 40, y + 80);
        triangle.lineTo(x + 40,y);
        triangle.closePath();/* w  ww. jav  a 2s.  com*/

        graphics.setPaint(Color.yellow);
        graphics.fill(triangle);
        graphics.setPaint(Color.black);
        graphics.draw(triangle);

        return triangle;
    }
	public static Shape drawTriangleBlue(Graphics2D graphics, double x, double y) {
		final GeneralPath triangle = new GeneralPath();
        triangle.setWindingRule(Path2D.WIND_EVEN_ODD);
        triangle.moveTo(x + 80, y + 40);
        triangle.lineTo(x + 20, y - 20);
        triangle.lineTo(x - 40, y + 40);
        triangle.lineTo(x + 40,y + 40);
        triangle.closePath();/* w  ww. jav  a 2s.  com*/


        graphics.setPaint(Color.blue);
        graphics.fill(triangle);
        graphics.setPaint(Color.black);
        graphics.draw(triangle);

        return triangle;
    }
	public static Shape drawTriangleRed(Graphics2D graphics, double x, double y) {
        final GeneralPath triangle = new GeneralPath();
        triangle.setWindingRule(Path2D.WIND_EVEN_ODD);
        triangle.moveTo(x, y + 80);
        triangle.lineTo(x + 60 , y + 20);
        triangle.lineTo(x, y - 40);
        triangle.lineTo(x,y);
        triangle.closePath();/* w  ww. jav  a 2s.  com*/

        graphics.setPaint(Color.red);
        graphics.fill(triangle);
        graphics.setPaint(Color.black);
        graphics.draw(triangle);

        return triangle;
    }
}
