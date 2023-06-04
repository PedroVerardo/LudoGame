package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Load {
	
	void buildStateGame(String path) throws FileNotFoundException {
		File file = new File(path);
		Scanner scan = null;
		int cont = 0;
		int i = 0;
		
		scan = new Scanner(file);
		
		ArrayList<List<Integer>> results = new ArrayList<List<Integer>>();
		
		while(scan.hasNextInt()) {
			if(cont%4 == 0) {
				i++;
			}
			
		}
		
	}
	
}
