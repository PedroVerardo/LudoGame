package View;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.List;
import java.awt.event.*;
import Model.*;


 
public class PNLudo extends JPanel implements MouseListener {
	double xIni=280.0,yIni=00.0,larg=40.0,alt=40.0,espLinha=2.0;
	int iClick,jClick;
	ViewHouse tab[]=new ViewHouse[72];
	ViewHouse start[][] = new ViewHouse[4][4];
	ViewHouse finalTrinagle[] = new ViewHouse[4];
	Rectangle2D.Double houses[] = new Rectangle2D.Double[72];
	Rectangle2D.Double base[] = new Rectangle2D.Double[4];
	Shape finalHouse[] = new Shape[4];
	Ellipse2D.Double baseHouses[][] = new Ellipse2D.Double[4][4];
	Ellipse2D.Double pawns[][] = new Ellipse2D.Double[4][4];
	Ellipse2D.Double pawnst[][] = new Ellipse2D.Double[4][4];
	SingletonBoard board;
	Player players[];
	List<Integer> pawnsPosition;
	Color colors[] = {Color.green,Color.yellow,Color.blue,Color.red};
	Integer bases[] = {2,15,28,41};
	
	
	private void createHouse(){
		double x=xIni,y=yIni,xFinal,yFinal;
		tab[0] = new ViewHouse(x,y);
		xFinal = x;
		yFinal = y;
		for(int i = 52; i < 58; i++) {
			yFinal=yFinal+40;
			if( i < 57 )
				tab[i] = new ViewHouse(xFinal,yFinal);
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
		for(int i = 57; i < 63; i++) {
			xFinal=xFinal-40;
			if( i < 62 )
				tab[i] = new ViewHouse(xFinal,yFinal);
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
		for(int i = 62; i < 68; i++) {
			yFinal=yFinal-40;
			if( i < 67 )
				tab[i] = new ViewHouse(xFinal,yFinal);
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
		for(int i = 67; i < 73; i++) {
			xFinal=xFinal+40;
			if( i < 72 )
				tab[i] = new ViewHouse(xFinal,yFinal);
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
		for(int i = 0; i<72;i++)
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
			if(board.isSafeHousePosition(i))
				g2d.fill(houses[i]);
			if(board.isInitialHousePositions(i)) {
				g2d.setPaint(colors[colorCount]);
				g2d.fill(houses[i]);
				g2d.setPaint(Color.black);
				colorCount++;
			}
			g2d.draw(houses[i]);	
		}
		colorCount = 0;
		for(int i = 52; i<72;i++) {
			g2d.setPaint(colors[colorCount]);
			g2d.fill(houses[i]);
			g2d.setPaint(Color.black);
			g2d.draw(houses[i]);
			if(i == 56 || i == 61 || i == 66)
				colorCount++;
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
	}
	
	private void drawCurrentState(Graphics2D g2d) {
		int[] p = new int[72];
		for(int i = 0; i<72;i++)
			p[i] = 0;
		for(int i = 0; i < 4; i ++) {
			pawnsPosition = players[i].getAllPawnsBoardposition();
			for(int j =0; j < 4; j++) {
				g2d.setPaint(Color.white);
				g2d.fill(baseHouses[i][j]);
				g2d.setPaint(Color.black);
				g2d.draw(baseHouses[i][j]);
				if(pawnsPosition.get(j) == bases[i]) {
					pawns[i][j] = new Ellipse2D.Double(start[i][j].x+10,start[i][j].y+10,30,30);
					g2d.draw(pawns[i][j]);
					g2d.setPaint(colors[i]);
					g2d.fill(pawns[i][j]);
					g2d.setPaint(Color.black);
				} else {
					if(p[pawnsPosition.get(j)] == 0) {
						pawns[i][j] = new Ellipse2D.Double(tab[pawnsPosition.get(j)].x+10,tab[pawnsPosition.get(j)].y+10,30,30);
						p[pawnsPosition.get(j)]++;
					} else {
						pawns[i][j] = new Ellipse2D.Double(tab[pawnsPosition.get(j)].x+15,tab[pawnsPosition.get(j)].y+15,20,20);
					}
					g2d.draw(pawns[i][j]);
					g2d.setPaint(colors[i]);
					g2d.fill(pawns[i][j]);
					g2d.setPaint(Color.black);
				}
				
			}
		}
	}
	
	public PNLudo(SingletonBoard c, Player[] p) {
		board = c;
		players = p;
		
		createHouse();
		
		addMouseListener(this);
		
		setUpHouses();
		
//		JButton pb=new JButton("Teste");
//		pb.setBounds(0,0,90,25);
//		add(pb);
//		setLayout(null);
//		setBounds(0,0,410,450);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d=(Graphics2D) g;
		//for(int i = 0; i<4;i++) 
		
		g2d.setStroke(new BasicStroke((float) espLinha,
                BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_MITER,
                10.0f));
		
		drawBoard(g2d);
		drawCurrentState(g2d);
	}
	

	public void mousePressed(MouseEvent e) {
		int x=e.getX()/40,y=e.getY()/40;
		int pos = -5;
		if (x == 6) {
			if(y >= 0 && y < 6)
				pos = 51 - y;
			if (y >= 6 && y<=8)
				pos = 72;
			if(y > 8 && y <= 14)
				pos = 32 - (y- 9); 
		}
		if (x == 7) {
			if(y == 0)
				pos = 0;
			if(y > 0 && y < 6)
				pos = y + 51;
			if (y >= 6 && y<=8)
				pos = 72;
			if(y > 8 && y < 14)
				pos = 66 - (y- 9); 
			if(y==14)
				pos = 26;
		}
		if (x == 8) {
			if(y >= 0 && y < 6)
				pos = y + 1;
			if (y >= 6 && y<=8)
				pos = 72;
			if(y > 8 && y <= 14)
				pos = 20 + (y- 9); 
		}
		if (x > 8 && x < 15) {
			if(y >= 0 && y < 6) {
				pos = -1;
			}	
			if (y == 6)
				pos = x - 2; 
			if (y == 7) {
				if (x < 14)
					pos = 61 - (x - 9);
				else
					pos = 13;
			}
			if (y == 8)
				pos = 19 - (x - 9);
			if (y > 8) {
				pos = -2;
			}
		}	
		if (x >= 0 && x < 6) {
			if(y >= 0 && y < 6)
				pos = -4;
			if (y == 6)
				pos = 40 + x; 
			if (y == 7) {
				if (x > 0)
					pos = 66 + x;
				else
					pos = 39;
			}
			if (y == 8)
				pos = 38 - x;
			if (y > 8) 
				pos = -3;
		}
		System.out.println("x =" + x + " y =" + y + " Position =" + pos);	
	}
	
	public void mouseEntered(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}