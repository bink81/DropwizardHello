package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public abstract class LineByLineFileReader {

	private final String filename;

	public LineByLineFileReader(String filename) {
		this.filename = filename;
	}

	public void loadFile() throws FileNotFoundException, IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			for (String line; (line = br.readLine()) != null;) {
				parseLine(line);
			}
		}
	}

	public abstract void parseLine(String line);
}
