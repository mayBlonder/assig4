import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class File_handler{
	
	//counts file's lines number.
	public static int file_lineNum(String file_p){
		Path path = Paths.get(file_p);
		long lineCount = 0;
		try {
			lineCount = Files.lines(path).count();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return (int)lineCount;
	}
	
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
