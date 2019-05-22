import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class files{
	public static String[] readFile(String path, int fileLen) {
		int i = 0;
		String[] lines = new String[fileLen];
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			String line = reader.readLine();
			while (line != null)
			{
				lines[i] = line;
				line = reader.readLine();
				i++;
			}
			reader.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}
}
