package View;

import javax.swing.*;

import Controller.Controller;

import java.awt.*;
import java.awt.geom.*;
import java.util.List;
import java.awt.event.*;
import Model.*;


 
public class PNLudo extends JPanel implements MouseListener {
	int pos;
	double xIni=280.0,yIni=00.0,larg=40.0,alt=40.0,espLinha=2.0;
	int iClick,jClick;
	Facade facade;
	Controller c = new Controller();
	Menu menu;
	ViewHouse tab[]=new ViewHouse[76];
	ViewHouse start[][] = new ViewHouse[4][4];
	//ViewHouse finalTrinagle[] = new ViewHouse[4];
	Rectangle2D.Double houses[] = new Rectangle2D.Double[76];
	Rectangle2D.Double base[] = new Rectangle2D.Double[4];
	Shape finalHouse[] = new Shape[4];
	Ellipse2D.Double baseHouses[][] = new Ellipse2D.Double[4][4];
	Ellipse2D.Double pawns[][] = new Ellipse2D.Double[4][4];
	Ellipse2D.Double ptest;
	Ellipse2D.Double pawnst[][] = new Ellipse2D.Double[4][4];
	List<Integer> pawnsPosition;
	Color colors[] = {Color.green,Color.yellow,Color.blue,Color.red};
	Integer bases[] = {2,15,28,41};
	
	public int getPositonClicked() {
		return pos;
	}		
	
	private void createHouse(){
		double x=xIni,y=yIni,xFinal,yFinal;
		tab[0] = new ViewHouse(x,y);
		xFinal = x;
		yFinal = y;
		for(int i = 52; i < 58; i++) {
			yFinal=yFinal+40;
			//if( i < 57 )
			tab[i] = new ViewHouse(xFinal,yFinal);
			//else
				//finalTrinagle[0] = new ViewHouse(xFinal,yFinal);
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
		for(int i = 58; i < 64; i++) {
			xFinal=xFinal-40;
			//if( i < 62 )
			tab[i] = new ViewHouse(xFinal,yFinal);
			//else
				//finalTrinagle[1] = new ViewHouse(xFinal,yFinal);
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
		for(int i = 64; i < 70; i++) {
			yFinal=yFinal-40;
			//if( i < 67 )
			tab[i] = new ViewHouse(xFinal,yFinal);
			//else
				//finalTrinagle[2] = new ViewHouse(xFinal,yFinal);
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
		for(int i = 70; i < 76; i++) {
			xFinal=xFinal+40;
			//if( i < 72 )
			tab[i] = new ViewHouse(xFinal,yFinal);
			//else
			//finalTrinagle[3] = new ViewHouse(xFinal,yFinal);
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
		
		start[0][0] = new ViewHouse(401.00,41.00);
		start[0][1] = new ViewHouse(401.00,161.00);
		start[0][2] = new ViewHouse(521.00,161.00);
		start[0][3] = new ViewHouse(521.00,41.00);
		
		start[1][0] = new ViewHouse(401.00,401.00);
		start[1][1] = new ViewHouse(401.00,521.00);
		start[1][2] = new ViewHouse(521.00,521.00);
		start[1][3] = new ViewHouse(521.00,401.00);
		
		start[2][0] = new ViewHouse(41.00,401.00);
		start[2][1] = new ViewHouse(41.00,521.00);
		start[2][2] = new ViewHouse(161.00,521.00);
		start[2][3] = new ViewHouse(161.00,401.00);
		
		start[3][0] = new ViewHouse(41.00,41.00);
		start[3][1] = new ViewHouse(41.00,161.00);
		start[3][2] = new ViewHouse(161.00,161.00);
		start[3][3] = new ViewHouse(161.00,41.00);
	}
	
	private void setUpHouses() {
		for(int i = 0; i<76;i++)
			houses[i] = new Rectangle2D.Double(tab[i].x+6,tab[i].y+6,40.00,40.00);
		
		
		base[0] = new Rectangle2D.Double(6.00,6.00,239.00,239.00);
		base[1] = new Rectangle2D.Double(367.00,6.00,239.00,239.00);
		base[2] = new Rectangle2D.Double(367.00,367.00,239.00,239.00);
		base[3] = new Rectangle2D.Double(6.00,367.00,239.00,239.00);
		
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				baseHouses[i][j] = new Ellipse2D.Double(start[i][j].x,start[i][j].y,50,50);
			}
		}
	}
	
	private void drawBoard(Graphics2D g2d) {
		int colorCount = 0;
		g2d.setPaint(Color.black);
		for(int i = 0; i<52;i++) {
			if(i == 11 || i == 24 || i == 37 || i == 50)
				g2d.fill(houses[i]);
			if(i == 2 || i == 15 || i == 28 || i == 41) {
				g2d.setPaint(colors[colorCount]);
				g2d.fill(houses[i]);
				g2d.setPaint(Color.black);
				colorCount++;
			}
			g2d.draw(houses[i]);	
		}
		colorCount = 0;
		for(int i = 52; i<76;i++) {
			g2d.setPaint(colors[colorCount]);
			g2d.fill(houses[i]);
			g2d.setPaint(Color.black);
			g2d.draw(houses[i]);
			if(i == 57 || i == 63 || i == 69)
				colorCount++;
		}
		finalHouse[0] = Triangle.drawTriangleGreen(g2d, tab[57].x+6.00, tab[57].y+6.00);
		finalHouse[1] = Triangle.drawTriangleYellow(g2d, tab[63].x+6.00, tab[63].y+6.00);
		finalHouse[2] = Triangle.drawTriangleBlue(g2d, tab[69].x+6.00, tab[69].y+6.00);
		finalHouse[3] = Triangle.drawTriangleRed(g2d, tab[75].x+6.00, tab[75].y+6.00);
		
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
	}
	
	private void drawCurrentState(Graphics2D g2d) {
		int[] p = new int[72];
		int u = 0;
		for(int i = 0; i<72;i++)
			p[i] = 0;
		for(int i = 0; i < 4; i ++) {
			pawnsPosition = facade.getAllPawnsPositions().get(i);
			for(int j =0; j < 4; j++) {
				g2d.setPaint(Color.white);
				g2d.fill(baseHouses[i][j]);
				g2d.setPaint(Color.black);
				g2d.draw(baseHouses[i][j]);
				if(pawnsPosition.get(j) == bases[i]) {
					if(facade.getAllPawnsInBase().get(i).get(j)) {
						pawns[i][j] = new Ellipse2D.Double(start[i][j].x+10,start[i][j].y+10,30,30);
					}
					else {
						pawns[i][j] = new Ellipse2D.Double(tab[pawnsPosition.get(j)].x+10,tab[pawnsPosition.get(j)].y+10,30,30);
					}
							
				} else {
					if(p[pawnsPosition.get(j)] == 0) {
						pawns[i][j] = new Ellipse2D.Double(tab[pawnsPosition.get(j)].x+10,tab[pawnsPosition.get(j)].y+10,30,30);
						p[pawnsPosition.get(j)]++;
					} else {
						pawns[i][j] = new Ellipse2D.Double(tab[pawnsPosition.get(j)].x+15,tab[pawnsPosition.get(j)].y+15,20,20);
					}
				}
				g2d.draw(pawns[i][j]);
				g2d.setPaint(colors[i]);
				g2d.fill(pawns[i][j]);
				g2d.setPaint(Color.black);
			}
		}
	}
	
	public PNLudo(Menu m, Facade f) {
		facade = f;
		menu = m;
		createHouse();
		
		addMouseListener(this);
		
		setUpHouses();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d=(Graphics2D) g;
		
		g2d.setStroke(new BasicStroke((float) espLinha,
                BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_MITER,
                10.0f));
		
		drawBoard(g2d);
		drawCurrentState(g2d);
	}
	

	public void mousePressed(MouseEvent e) {
		pos = c.getPositionByClick(e.getX()/40, e.getY()/40);
		Color colors[] = {Color.green,Color.yellow,Color.blue,Color.red};
		int colorCount = 0;
		int diceroll = menu.getDiceroll();
		int chosenPawnMoveType = 0;
		List<Integer> b;
		List<Integer> pp;
		facade.getPlayerOfRound();
        b = facade.getPawnsMoveTypesOfPlayer(diceroll);
        pp = facade.getPawnsPositionOfPlayer();

        /*
        for (int i = 0; i < b.size(); i++) {
        	if (b.get(i) != 0) {
        		chosenPawnMoveType = b.get(i);
        		facade.makeMove(i, pos, diceroll);
        		break;
        	}
        }
        */
        
        int repeatPlayer = c.extraMovement(diceroll, chosenPawnMoveType);
        
        System.out.println("\n\n\n" + repeatPlayer + "\n\n\n");
        if (repeatPlayer == 1)	{
        	
        	
        	
        }
        
        if(colorCount < 3)
        	colorCount++;
        else
        	colorCount = 0;	
        menu.nextRound();
		//System.out.println("x = " + x + " y = " + y + " Position = " + pos);	
		repaint();
		menu.repaint();
	}
	
	public void mouseEntered(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}