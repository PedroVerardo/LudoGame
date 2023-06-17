package View;

import Model.*;

import java.awt.*;
import javax.swing.*;

public class FRScoreBoard extends JFrame {

	final int LARG_DEFAULT=500;
	final int ALT_DEFAULT=500;
	
	public FRScoreBoard(Facade facade) {
		facade.setBoard();
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension screenSize=tk.getScreenSize();
		int sl=screenSize.width;
		int sa=screenSize.height;
		int x=sl/2-LARG_DEFAULT/2;
		int y=sa/2-ALT_DEFAULT/2;
		setBounds(x,y,500,400);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		getContentPane().add(new PNScoreBoard(facade,this));
		
		
		setTitle("Score board");
//		setLayout(null);
	}
	
}
