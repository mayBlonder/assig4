import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.io.FileReader;

public class Runner1 {
	
	static String HASH_P = "C:\\Users\\maybl\\eclipse-workspace\\assig4\\src\\hash_functions.txt";
	
	public static void main(String[] args) {
		read_hashFunc();
	
	private static String read_hashFunc(){
		BufferedReader reader;
		int seperation_ind;
		String tmpA;
		String tmpB;
		
		try {
			reader = new BufferedReader(new FileReader(HASH_P));
			String line = reader.readLine();
			
			// create hash
			while (line != null) 
			{
				seperation_ind = line.indexOf("_");
				tmpA = line.substring(0, seperation_ind);
				tmpB = line.substring(seperation_ind+1);
				System.out.println(line);
				line = reader.readLine();
				// create hash
			}
			reader.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}			
		return ":)";
	}
}
