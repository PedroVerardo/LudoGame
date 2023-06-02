package Model;

import java.io.FileWriter;

class Save {
	void WriteF(String name) {
		FileWriter writeNewFile = new FileWriter(name + ".txt");
	}
}
