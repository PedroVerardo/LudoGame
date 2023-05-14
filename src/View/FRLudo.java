package View;

import Model.*;

import java.awt.*;
import javax.swing.*;

public class FRLudo extends JFrame {
	final int LARG_DEFAULT=810;
	final int ALT_DEFAULT=850;
	
	public FRLudo(SingletonBoard c, Player[] p) {
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension screenSize=tk.getScreenSize();
		int sl=screenSize.width;
		int sa=screenSize.height;
		int x=sl/2-LARG_DEFAULT/2;
		int y=sa/2-ALT_DEFAULT/2;
		setBounds(x,y,LARG_DEFAULT,ALT_DEFAULT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().add(new PNLudo(c,p));
		setTitle("Ludo Game");
//		setLayout(null);
	}
	
	public static void main(String args[]) {
	}
}
