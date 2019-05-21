import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//import java.util.Arrays;

public class BuildHash{
	static String HASH_P = "C:\\Users\\maybl\\eclipse-workspace\\assig4\\src\\hash_functions.txt";
	
	public static void main(String[] args) {
		//read_hashFunc();
	}
	
	public static Hash[] read_hashFunc(){
		BufferedReader reader;
		Hash[] hashes = new Hash[4];
		int seperation_ind;
		int tmpA;
		int tmpB;
		int i = 0;
		
		try {
			reader = new BufferedReader(new FileReader(HASH_P));
			String line = reader.readLine();
			while (line != null)
			{
				seperation_ind = line.indexOf("_");
				tmpA = Integer.parseInt(line.substring(0, seperation_ind));
				tmpB =Integer.parseInt(line.substring(seperation_ind+1));
				hashes[i] = new Hash(tmpA, tmpB);
				//System.out.println(hashes[i]);
				line = reader.readLine();
				i++;
			}
			reader.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return hashes;
	}
}

