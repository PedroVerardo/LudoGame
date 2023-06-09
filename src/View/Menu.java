package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
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
	private Rectangle2D.Double playing;
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
	                //função para abrir o arquivo e iniciar o jogo
	            }
	        }
	    });

	    JButton saveGameButton = new JButton("Salvar");
	    saveGameButton.setBounds(10, 140, 150, 40);
	    saveGameButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            //função para salvar
	            }
	        });
	    
	    JLabel statusLabel = new JLabel("À jogar: ");
	    statusLabel.setBounds(10, 190, 150, 40);
	    Font labelFont = new Font("Monospace Bold", Font.BOLD, 26);
	    statusLabel.setFont(labelFont);
	    statusLabel.setHorizontalAlignment(SwingConstants.CENTER);

	    playing = new Rectangle2D.Double(30, 235, 110, 110);
	    JLabel imageLabel = new JLabel();
	    JButton rollDiceButton = new JButton("Lançar Dado");
	    rollDiceButton.setBounds(10, 360, 150, 40);
	    rollDiceButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            diceroll = facade.GetDiceRoll();
	            String image = "src/Images/Dado" + diceroll + ".png";	
	            ImageIcon imageIcon = new ImageIcon(image);
	            imageLabel.setIcon(imageIcon);
	            imageLabel.setBounds(35, 240, imageIcon.getIconWidth(), imageIcon.getIconHeight());
	        }
	    });
	    
	    JButton dice1 = new JButton("1");
	    dice1.setBounds(10, 410, 50, 50);
	    dice1.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            diceroll = 1;
	            String image = "src/Images/Dado" + diceroll + ".png";	
	            ImageIcon imageIcon = new ImageIcon(image);
	            imageLabel.setIcon(imageIcon);
	            imageLabel.setBounds(35, 240, imageIcon.getIconWidth(), imageIcon.getIconHeight());
	        }
	    });
	    JButton dice2 = new JButton("2");
	    dice2.setBounds(110, 410, 50, 50);
	    dice2.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            diceroll = 2;
	            String image = "src/Images/Dado" + diceroll + ".png";	
	            ImageIcon imageIcon = new ImageIcon(image);
	            imageLabel.setIcon(imageIcon);
	            imageLabel.setBounds(35, 240, imageIcon.getIconWidth(), imageIcon.getIconHeight());
	        }
	    });
	    JButton dice3 = new JButton("3");
	    dice3.setBounds(10, 470, 50, 50);
	    dice3.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            diceroll = 3;
	            String image = "src/Images/Dado" + diceroll + ".png";	
	            ImageIcon imageIcon = new ImageIcon(image);
	            imageLabel.setIcon(imageIcon);
	            imageLabel.setBounds(35, 240, imageIcon.getIconWidth(), imageIcon.getIconHeight());
	        }
	    });
	    JButton dice4 = new JButton("4");
	    dice4.setBounds(110, 470, 50, 50);
	    dice4.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            diceroll = 4;
	            String image = "src/Images/Dado" + diceroll + ".png";	
	            ImageIcon imageIcon = new ImageIcon(image);
	            imageLabel.setIcon(imageIcon);
	            imageLabel.setBounds(35, 240, imageIcon.getIconWidth(), imageIcon.getIconHeight());
	        }
	    });
	    JButton dice5 = new JButton("5");
	    dice5.setBounds(10, 530, 50, 50);
	    dice5.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            diceroll = 5;
	            String image = "src/Images/Dado" + diceroll + ".png";	
	            ImageIcon imageIcon = new ImageIcon(image);
	            imageLabel.setIcon(imageIcon);
	            imageLabel.setBounds(35, 240, imageIcon.getIconWidth(), imageIcon.getIconHeight());
	        }
	    });
	    JButton dice6 = new JButton("6");
	    dice6.setBounds(110, 530, 50, 50);
	    dice6.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            diceroll = 6;
	            String image = "src/Images/Dado" + diceroll + ".png";	
	            ImageIcon imageIcon = new ImageIcon(image);
	            imageLabel.setIcon(imageIcon);
	            imageLabel.setBounds(35, 240, imageIcon.getIconWidth(), imageIcon.getIconHeight());
	        }
	    });

	    add(newGameButton);
	    add(loadGameButton);
	    add(saveGameButton);
	    add(statusLabel);
	    add(imageLabel);
	    add(rollDiceButton);
	    add(dice1);
	    add(dice2);
	    add(dice3);
	    add(dice4);
	    add(dice5);
	    add(dice6);
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(colors[colorCount]);
        g2d.fill(playing);
    }
    
}