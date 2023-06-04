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
	
	void writeIntegerList(ArrayList<List<Integer>> listAll, FileWriter file) throws IOException {
		for(List<Integer> playerPawns : listAll) {
			for(Integer pos : playerPawns) {
				file.write(pos);
			}
		}
	}
	
	
	void writePawnsState(String name, Facade f, ArrayList<List<Integer>> listAll) {
		try {
			FileWriter newF = createF(name);
			writeIntegerList(listAll, newF);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
