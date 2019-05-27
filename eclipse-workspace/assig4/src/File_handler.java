import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class File_handler{

	public static int file_lineNum(String file_p){
		//counts file's lines number.
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

	public static int[] stringArrToInt(String[] arr){
		//converts a String array into int array.
		int[] newArr = new int[arr.length];
		int tmpNum256;
		System.out.println(Arrays.toString(arr));
		for(int i=0;i<arr.length;i++) {
			tmpNum256 = UniFunctions.tonumber256(arr[i]);
			newArr[i] = tmpNum256;
			System.out.println(newArr[i] + " " + arr[i]);
		}
		//System.out.println(Arrays.toString(newArr));
		return newArr;
	}
}
