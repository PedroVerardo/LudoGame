package Model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Save {
	FileWriter createF(String name) throws IOException {
		FileWriter writeNewFile = new FileWriter(name + ".txt");
		
		return writeNewFile;
	}
	
	void writeIntegerList(ArrayList<List<Integer>> listAll) {
		
	}
	
	
	void writePawnsState(String name, Facade f) {
		try {
			FileWriter newF = createF(name);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		ArrayList<List<Integer>> listAll = new ArrayList<List<Integer>>();
	}
}
