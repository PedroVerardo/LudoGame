package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.geom.Ellipse2D;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Model.Facade;
//import Model.Pawn;
//import Model.Player;

public class PNScoreBoard extends JPanel {
	private int diceroll;
	private Ellipse2D.Double players[] = new Ellipse2D.Double[4];
	JLabel statusLabel[] = new JLabel[4];
	JLabel pointLabel[] = new JLabel[4];
	Facade facade;
	Color colors[] = {Color.green,Color.yellow,Color.blue,Color.red};
	int colorCount = 0;
	List<Integer> b;
	List<Integer> pp;
	//List<Pawn> p;
	//Player r;
	int getDiceroll() {
		return diceroll;
	}
	public PNScoreBoard(Facade f) {
		facade = f;
	    setBackground(Color.LIGHT_GRAY);
	    setLayout(null);
	    Font labelFont = new Font("Monospace Bold", Font.BOLD, 26);
		for(int i = 0; i < 4; i++) {
			players[i] = new Ellipse2D.Double(70,30+60*(i),40,40);
			statusLabel[i] = new JLabel(""+(i+1)+"");
		    statusLabel[i].setBounds(70,30+60*(i),40,40);
		    statusLabel[i].setFont(labelFont);
		    statusLabel[i].setHorizontalAlignment(SwingConstants.CENTER);
		    pointLabel[i] = new JLabel("Pontos: "+(i+1)+"");
		    pointLabel[i].setBounds(140,30+60*(i),300,40);
		    pointLabel[i].setFont(labelFont);
		    //pointLabel[i].setHorizontalAlignment(SwingConstants.CENTER);
		    add(statusLabel[i]);
		    add(pointLabel[i]);
		}
	    
		JButton newGameButton = new JButton("Nova Partida");
		newGameButton.setBounds(30, 300, 150, 40);
	    newGameButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            JOptionPane.showMessageDialog(PNScoreBoard.this, "Iniciando nova partida...");
	        }
	    });
	    
	    JButton loadGameButton = new JButton("Carregar Jogo");
	    loadGameButton.setBounds(300, 300, 150, 40);
	    loadGameButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            JFileChooser fileChooser = new JFileChooser();
	            int returnValue = fileChooser.showOpenDialog(PNScoreBoard.this);
	            if (returnValue == JFileChooser.APPROVE_OPTION) {
	                File selectedFile = fileChooser.getSelectedFile();
	                JOptionPane.showMessageDialog(PNScoreBoard.this, "Carregando partida do arquivo: " + selectedFile.getAbsolutePath());
	                //função para abrir o arquivo e iniciar o jogo
	            }
	        }
	    });
	    
	    add(newGameButton);
	    add(loadGameButton);
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g.drawLine(70, 80, 450, 80);
        g.drawLine(70, 140, 450, 140);
        g.drawLine(70, 200, 450, 200);
        g.drawLine(70, 260, 450, 260);
    	g2d.setColor(Color.RED);
    	g2d.fill(players[0]);
    	g2d.setColor(Color.BLUE);
    	g2d.fill(players[1]);
        g2d.setColor(Color.YELLOW);
        g2d.fill(players[2]);
    	g2d.setColor(Color.GREEN);
    	g2d.fill(players[3]);
    }
    
}