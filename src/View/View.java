package View;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class View {
	
	private Scanner scan;
	
	public View(String path) throws FileNotFoundException {
		this.scan = ReadFile.readFile(path);
	}
	
	public void printLines() {
		while(scan.hasNextLine()) {
			System.out.println(scan.nextLine());
		}
	}
}
