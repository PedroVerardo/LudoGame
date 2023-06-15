package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
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
	Short values[] = {256,512,768,1024}; 
	int colorCount = 0;
	List<Integer> b;
	List<Integer> pp;
	int scores[] = new int[4];
	//List<Pawn> p;
	//Player r;
	int[] bogoSort(int[] a, Color[] b)
    {
        // if array is not sorted then shuffle the
        // array again
        while (isSorted(a) == false)
            shuffle(a,b);
        return a;
    }
 
    // To generate permutation of the array
    void shuffle(int[] a, Color[] b)
    {
        // Math.random() returns a double positive
        // value, greater than or equal to 0.0 and
        // less than 1.0.
        for (int i = 0; i < a.length; i++)
            swap(a,b, i, (int)(Math.random() * i));
    }
 
    // Swapping 2 elements
    void swap(int[] a, Color[] b, int i, int j)
    {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
        Color tempc = b[i];
        b[i] = b[j];
        b[j] = tempc;
    }
 
    // To check if array is sorted or not
    boolean isSorted(int[] a)
    {
        for (int i = 1; i < a.length; i++)
            if (a[i] > a[i - 1])
                return false;
        return true;
    }
	public PNScoreBoard(Facade f) {
		facade = f;
	    setBackground(Color.LIGHT_GRAY);
	    setLayout(null);
	    Font labelFont = new Font("Monospace Bold", Font.BOLD, 26);
	    Map<Short, Integer> score = facade.getScore();
	    for(int i = 0; i < 4; i++) {
	    	scores[i] = score.get(values[i]);
	    }
	    scores = bogoSort(scores,colors);
		for(int i = 0; i < 4; i++) {
			players[i] = new Ellipse2D.Double(70,30+60*(i),40,40);
			statusLabel[i] = new JLabel(""+(i+1)+"");
		    statusLabel[i].setBounds(70,30+60*(i),40,40);
		    statusLabel[i].setFont(labelFont);
		    statusLabel[i].setHorizontalAlignment(SwingConstants.CENTER);
		    pointLabel[i] = new JLabel("Pontos: "+scores[i]+"");
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
        for(int i = 0; i < 4; i++) {
        	g2d.setColor(Color.BLACK);
        	g.drawLine(70, 80+(60*i), 450, 80+(60*i));
        	g2d.setColor(colors[i]);
        	g2d.fill(players[i]);
        }
    }
    
}