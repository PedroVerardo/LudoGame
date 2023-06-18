package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Controller.Controller;
import Controller.IObserver;
import Model.Facade;
//import Model.Pawn;
//import Model.Player;

public class Menu extends JPanel implements IObserver{
	private int diceroll;
	private Rectangle2D.Double playing;
	Facade facade;
	//Controller controller = new Controller();
	Color colors[] = {Color.green,Color.yellow,Color.blue,Color.red};
	int colorCount = 0;
	List<Integer> b;
	List<Integer> pp;
	JButton rollDiceButton;
	JLabel imageLabel = new JLabel();
	//List<Pawn> p;
	//Player r;
	int getDiceroll() {
		return diceroll;
	}
	
	public void setDiceButton(boolean status) {
		rollDiceButton.setEnabled(status);
	}
	
	public void setDiceToSix() {
		diceroll = 6;
		String image = "src/Images/Dado" + diceroll + ".png";	
        ImageIcon imageIcon = new ImageIcon(image);
        imageLabel.setIcon(imageIcon);
        imageLabel.setBounds(35, 240, imageIcon.getIconWidth(), imageIcon.getIconHeight());
	}
	
	public Menu(Facade f) {
		facade = f;
	    setBackground(Color.LIGHT_GRAY);
	    setLayout(null);
	    facade.subscribeInRound(this);

	    JButton newGameButton = new JButton("Nova Partida");
	    newGameButton.setBounds(10, 30, 150, 40);
	    newGameButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            System.out.println("oi");
	            facade.resetGame();
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
	                //função para abrir o arquivo e iniciar o jogo
	                facade.loadGame(selectedFile.getAbsolutePath());
	                facade.subscribeInRound(Menu.this);
	                repaint();
	            }
	        }
	    });

	    JButton saveGameButton = new JButton("Salvar");
	    saveGameButton.setBounds(10, 140, 150, 40);
	    saveGameButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            // Create a new dialog box
	        	//new FRScoreBoard(facade).setVisible(true);
	        	facade.saveGame();
	        }
	    });
	    
	    JLabel statusLabel = new JLabel("À jogar: ");
	    statusLabel.setBounds(10, 190, 150, 40);
	    Font labelFont = new Font("Monospace Bold", Font.BOLD, 26);
	    statusLabel.setFont(labelFont);
	    statusLabel.setHorizontalAlignment(SwingConstants.CENTER);

	    playing = new Rectangle2D.Double(30, 235, 110, 110);
	    rollDiceButton = new JButton("Lançar Dado");
	    rollDiceButton.setBounds(10, 360, 150, 40);
	    rollDiceButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            diceroll = facade.GetDiceRoll();
	            String image = "src/Images/Dado" + diceroll + ".png";	
	            ImageIcon imageIcon = new ImageIcon(image);
	            imageLabel.setIcon(imageIcon);
	            imageLabel.setBounds(35, 240, imageIcon.getIconWidth(), imageIcon.getIconHeight());
	            
	            int count = 0;
	            b = facade.getPawnsMoveTypesOfPlayer(diceroll);
	            setDiceButton(false);
	            for (int i = 0; i < 4; i++) {
	            	if (b.get(i) == 0) {
	            		count++; }
	            }
	            
	            if (count == 4) { 
	            	facade.getPlayerOfRound();
	            	repaint();
	            	setDiceButton(true); }
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
        //System.out.println(facade.getPlayerColor());
        if(facade.getPlayerColor()==1024)
        	g2d.setColor(Color.RED);
        else {
        	if (facade.getPlayerColor()==768)
        		g2d.setColor(Color.BLUE);
        	else {
        		if (facade.getPlayerColor()==512)
            		g2d.setColor(Color.YELLOW);
        		else
        			g2d.setColor(Color.GREEN);
        	}
        }
        g2d.fill(playing);
    }

	@Override
	public void updateBoard() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateDice() {
		System.out.println("kkkkk");
		repaint();
		
	}
    
}