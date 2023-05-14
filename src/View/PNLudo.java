package View;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import Model.*;


 
public class PNLudo extends JPanel implements MouseListener {
	double xIni=280.0,yIni=00.0,larg=40.0,alt=40.0,espLinha=2.0;
	int iClick,jClick;
	ViewHouse tab[]=new ViewHouse[52];
	ViewHouse finalLines[][] =new ViewHouse[4][5];
	ViewHouse finalTrinagle[] = new ViewHouse[4];
	Rectangle2D.Double finalHouses[][] = new Rectangle2D.Double[4][5];
	Rectangle2D.Double houses[] = new Rectangle2D.Double[52];
	Rectangle2D.Double base[] = new Rectangle2D.Double[4];
	Shape finalHouse[] = new Shape[4];
	Ellipse2D.Double baseHouses[][] = new Ellipse2D.Double[4][4];
	SingletonBoard board;
	Color colors[] = {Color.green,Color.yellow,Color.blue,Color.red};
	
	public PNLudo(SingletonBoard c) {
		double x=xIni,y=yIni,xFinal,yFinal;
		board = c;

		tab[0] = new ViewHouse(x,y);
		xFinal = x;
		yFinal = y;
		for(int i = 0; i < 6; i++) {
			yFinal=yFinal+40;
			if( i < 5 )
				finalLines[0][i] = new ViewHouse(xFinal,yFinal);
			else
				finalTrinagle[0] = new ViewHouse(xFinal,yFinal);
		}
		x=x+40;
		for(int i = 0; i < 6; i++) {
			tab[i+1] = new ViewHouse(x,y);
			y=y+40;
		}
		for(int i = 0; i < 6; i++) {
			x=x+40;
			tab[i+7] = new ViewHouse(x,y);
		}
		y=y+40;
		tab[13] = new ViewHouse(x,y);
		xFinal = x;
		yFinal = y;
		for(int i = 0; i < 6; i++) {
			xFinal=xFinal-40;
			if( i < 5 )
				finalLines[1][i] = new ViewHouse(xFinal,yFinal);
			else
				finalTrinagle[1] = new ViewHouse(xFinal,yFinal);
		}
		y=y+40;
		for(int i = 0; i < 6; i++) {
			tab[i+14] = new ViewHouse(x,y);
			x=x-40;
		}
		for(int i = 0; i < 6; i++) {
			y=y+40;
			tab[i+20] = new ViewHouse(x,y);
		}
		x=x-40;
		tab[26] = new ViewHouse(x,y);
		xFinal = x;
		yFinal = y;
		for(int i = 0; i < 6; i++) {
			yFinal=yFinal-40;
			if( i < 5 )
				finalLines[2][i] = new ViewHouse(xFinal,yFinal);
			else
				finalTrinagle[2] = new ViewHouse(xFinal,yFinal);
		}
		x=x-40;
		for(int i = 0; i < 6; i++) {
			tab[i+27] = new ViewHouse(x,y);
			y=y-40;
		}
		for(int i = 0; i < 6; i++) {
			x=x-40;
			tab[i+33] = new ViewHouse(x,y);
		}
		y=y-40;
		tab[39] = new ViewHouse(x,y);
		xFinal = x;
		yFinal = y;
		for(int i = 0; i < 6; i++) {
			xFinal=xFinal+40;
			if( i < 5 )
				finalLines[3][i] = new ViewHouse(xFinal,yFinal);
			else
				finalTrinagle[3] = new ViewHouse(xFinal,yFinal);
		}
		y=y-40;
		for(int i = 0; i < 6; i++) {
			tab[i+40] = new ViewHouse(x,y);
			x=x+40;
		}
		for(int i = 0; i < 6; i++) {
			y=y-40;
			tab[i+46] = new ViewHouse(x,y);
		}
	
		addMouseListener(this);
		
		for(int i = 0; i<52;i++)
			houses[i] = new Rectangle2D.Double(tab[i].x+6,tab[i].y+6,40.00,40.00);
		for(int i = 0; i<4;i++) {
			for(int j = 0; j < 5; j++)
				finalHouses[i][j] = new Rectangle2D.Double(finalLines[i][j].x+6,finalLines[i][j].y+6,40.00,40.00);
		}
		base[0] = new Rectangle2D.Double(6.00,6.00,239.00,239.00);
		base[1] = new Rectangle2D.Double(366.00,6.00,239.00,239.00);
		base[2] = new Rectangle2D.Double(366.00,367.00,239.00,239.00);
		base[3] = new Rectangle2D.Double(6.00,367.00,239.00,239.00);
		
		baseHouses[0][0] = new Ellipse2D.Double(41.00,41.00,50,50);
		baseHouses[0][1] = new Ellipse2D.Double(41.00,161.00,50,50);
		baseHouses[0][2] = new Ellipse2D.Double(161.00,161.00,50,50);
		baseHouses[0][3] = new Ellipse2D.Double(161.00,41.00,50,50);
		
		baseHouses[1][0] = new Ellipse2D.Double(401.00,41.00,50,50);
		baseHouses[1][1] = new Ellipse2D.Double(401.00,161.00,50,50);
		baseHouses[1][2] = new Ellipse2D.Double(521.00,161.00,50,50);
		baseHouses[1][3] = new Ellipse2D.Double(521.00,41.00,50,50);
		
		baseHouses[2][0] = new Ellipse2D.Double(41.00,401.00,50,50);
		baseHouses[2][1] = new Ellipse2D.Double(41.00,521.00,50,50);
		baseHouses[2][2] = new Ellipse2D.Double(161.00,521.00,50,50);
		baseHouses[2][3] = new Ellipse2D.Double(161.00,401.00,50,50);
		
		baseHouses[3][0] = new Ellipse2D.Double(401.00,401.00,50,50);
		baseHouses[3][1] = new Ellipse2D.Double(401.00,521.00,50,50);
		baseHouses[3][2] = new Ellipse2D.Double(521.00,521.00,50,50);
		baseHouses[3][3] = new Ellipse2D.Double(521.00,401.00,50,50);
		
//		JButton pb=new JButton("Teste");
//		pb.setBounds(0,0,90,25);
//		add(pb);
//		setLayout(null);
//		setBounds(0,0,410,450);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d=(Graphics2D) g;
		int colorCount = 0;
		//for(int i = 0; i<4;i++) 
		
		g2d.setStroke(new BasicStroke((float) espLinha,
                BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_MITER,
                10.0f));
		
		g2d.setPaint(Color.black);
		for(int i = 0; i<52;i++) {
			if(board.isSafeHousePosition(i))
				g2d.fill(houses[i]);
			if(board.isInitialHousePositions(i)) {
				g2d.setPaint(colors[colorCount]);
				for(int j = 0; j < 5; j++) {
					g2d.fill(finalHouses[colorCount][j]);
					//g2d.fill(finalHouse[colorCount]);
					g2d.setPaint(Color.black);
					g2d.draw(finalHouses[colorCount][j]);
					//g2d.draw(finalHouse[colorCount]);
					g2d.setPaint(colors[colorCount]);
				}
				g2d.fill(houses[i]);
				g2d.setPaint(Color.black);
				colorCount++;
			}
			g2d.draw(houses[i]);	
		}
		finalHouse[0] = Triangle.drawTriangleGreen(g2d, finalTrinagle[0].x+6.00, finalTrinagle[0].y+6.00);
		finalHouse[1] = Triangle.drawTriangleYellow(g2d, finalTrinagle[1].x+6.00, finalTrinagle[1].y+6.00);
		finalHouse[2] = Triangle.drawTriangleBlue(g2d, finalTrinagle[2].x+6.00, finalTrinagle[2].y+6.00);
		finalHouse[3] = Triangle.drawTriangleRed(g2d, finalTrinagle[3].x+6.00, finalTrinagle[3].y+6.00);
		
		g2d.setPaint(Color.red);
		g2d.fill(base[0]);
		g2d.setPaint(Color.green);
		g2d.fill(base[1]);
		g2d.setPaint(Color.yellow);
		g2d.fill(base[2]);
		g2d.setPaint(Color.blue);
		g2d.fill(base[3]);
		g2d.setPaint(Color.black);
		g2d.draw(base[0]);
		g2d.draw(base[1]);
		g2d.draw(base[2]);
		g2d.draw(base[3]);
		for(int i = 0; i < 4; i ++) {
			for(int j =0; j < 4; j++) {
				g2d.setPaint(Color.white);
				g2d.fill(baseHouses[i][j]);
				g2d.setPaint(Color.black);
				g2d.draw(baseHouses[i][j]);
			}
		}
	}
	

	public void mousePressed(MouseEvent e) {

	}
	
	public void mouseEntered(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}