package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Model.Facade;
//import Model.Pawn;
//import Model.Player;

public class Menu extends JPanel {
	private int diceroll;
	private Ellipse2D playing;
	Color colors[] = {Color.green,Color.yellow,Color.blue,Color.red};
	int colorCount = 0;
	List<Integer> b;
	List<Integer> pp;
	//List<Pawn> p;
	//Player r;
	int getDiceroll() {
		return diceroll;
	}
	void nextRound() {
        if(colorCount < 3)
        	colorCount++;
        else
        	colorCount = 0;	
	}
	public Menu(Facade facade) {
	    setBackground(Color.LIGHT_GRAY);
	    setLayout(null);

	    JButton newGameButton = new JButton("Nova Partida");
	    newGameButton.setBounds(10, 30, 150, 40);
	    newGameButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            JOptionPane.showMessageDialog(Menu.this, "Iniciando nova partida...");
	        }
	    });

	    JButton loadGameButton = new JButton("Carregar Jogo");
	    loadGameButton.setBounds(10, 90, 150, 40);
	    loadGameButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            JFileChooser fileChooser = new JFileChooser();
	            int returnValue = fileChooser.showOpenDialog(Menu.this);
	            if (returnValue == JFileChooser.APPROVE_OPTION) {
	                File selectedFile = fileChooser.getSelectedFile();
	                JOptionPane.showMessageDialog(Menu.this, "Carregando partida do arquivo: " + selectedFile.getAbsolutePath());
	            }
	        }
	    });

	    JButton saveGameButton = new JButton("Salvar");
	    saveGameButton.setBounds(10, 140, 150, 40);

	    JLabel statusLabel = new JLabel("À jogar: ");
	    statusLabel.setBounds(10, 190, 150, 40);
	    Font labelFont = new Font("Monospace Bold", Font.BOLD, 26);
	    statusLabel.setFont(labelFont);
	    statusLabel.setHorizontalAlignment(SwingConstants.CENTER);

	    playing = new Ellipse2D.Double(60, 250, 50, 50);
	    JLabel imageLabel = new JLabel();
	    JButton rollDiceButton = new JButton("Lançar Dado");
	    rollDiceButton.setBounds(10, 340, 150, 40);
	    rollDiceButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            diceroll = facade.GetDiceRoll();
	            String image = "src/Images/Dado" + diceroll + ".png";	
	            ImageIcon imageIcon = new ImageIcon(image);
	            imageLabel.setIcon(imageIcon);
	            imageLabel.setBounds(35, 390, imageIcon.getIconWidth(), imageIcon.getIconHeight());
	        }
	    });
	    

	    add(newGameButton);
	    add(loadGameButton);
	    add(saveGameButton);
	    add(statusLabel);
	    add(imageLabel);
	    add(rollDiceButton);

	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(colors[colorCount]);
        g2d.fill(playing);
    }
    
}