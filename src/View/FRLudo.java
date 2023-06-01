package View;

import Model.*;

import java.awt.*;
import javax.swing.*;

public class FRLudo extends JFrame {

	final int LARG_DEFAULT=810;
	final int ALT_DEFAULT=850;
	
	public FRLudo(Facade facade) {
		facade.setBoard();
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension screenSize=tk.getScreenSize();
		int sl=screenSize.width;
		int sa=screenSize.height;
		int x=sl/2-LARG_DEFAULT/2;
		int y=sa/2-ALT_DEFAULT/2;
		setBounds(x,y,800,650);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		 getContentPane().setLayout(new GridBagLayout());

	        GridBagConstraints constraints2 = new GridBagConstraints();
	        constraints2.gridx = 1;
	        constraints2.gridy = 0;
	        constraints2.weightx = 0.25; // Painel2 ocupa 1/3 da largura
	        constraints2.weighty = 1;
	        constraints2.fill = GridBagConstraints.BOTH;
	        Menu menu = new Menu(facade);
	        getContentPane().add(menu, constraints2);
	        
	        GridBagConstraints constraints1 = new GridBagConstraints();
	        constraints1.gridx = 0;
	        constraints1.gridy = 0;
	        constraints1.weightx = 0.85; // Painel1 ocupa 2/3 da largura
	        constraints1.weighty = 1;
	        constraints1.fill = GridBagConstraints.BOTH;
	        PNLudo pnLudo = new PNLudo(menu, facade);
	        getContentPane().add(pnLudo, constraints1);

		setTitle("Ludo Game");
//		setLayout(null);
	}
	
}
