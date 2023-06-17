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
import javax.swing.JFrame;
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
    
    public void quickSort(int a[], Color b[], int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(a,b, begin, end);

            quickSort(a,b, begin, partitionIndex-1);
            quickSort(a,b, partitionIndex+1, end);
        }
    }
    
    private int partition(int a[],Color b[], int begin, int end) {
        int pivot = a[end];
        int i = (begin-1);

        for (int j = begin; j < end; j++) {
            if (a[j] >= pivot) {
                i++;

                int swapTemp = a[i];
                Color swapTempb = b[i];
                a[i] = a[j];
                b[i] = b[j];
                a[j] = swapTemp;
                b[j] = swapTempb;
            }
        }

        int swapTemp = a[i+1];
        Color swapTempb = b[i+1];
        a[i+1] = a[end];
        b[i+1] = b[end];
        a[end] = swapTemp;
        b[end] = swapTempb;

        return i+1;
    }
    
    
	public PNScoreBoard(Facade f, JFrame j) {
		facade = f;
	    setBackground(Color.LIGHT_GRAY);
	    setLayout(null);
	    Font labelFont = new Font("Monospace Bold", Font.BOLD, 26);
	    Map<Short, Integer> score = facade.getScore();
	    for(int i = 0; i < 4; i++) {
	    	scores[i] = score.get(values[i]);
	    }
	    quickSort(scores,colors,0,3);
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
	            j.setVisible(false);
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