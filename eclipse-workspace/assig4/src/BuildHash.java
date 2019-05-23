public class BuildHash{
	//static String HASH_P = "C:\\Users\\maybl\\eclipse-workspace\\assig4\\src\\hash_functions.txt";
	static Hash[] hashes = new Hash[4];
	
	public static void main(String[] args) {
		//read_hashFunc();
	}
	
	private static void updateHash_array(String line, int i) {
		int seperation_index = line.indexOf("_");
		int tmpA = Integer.parseInt(line.substring(0, seperation_index));
		int tmpB =Integer.parseInt(line.substring(seperation_index + 1));
		hashes[i] = new Hash(tmpA, tmpB);
	}
	
	public static Hash[] build_hashFunc(String HASH_P){
		String[] lines = File_handler.readFile(HASH_P, File_handler.file_lineNum(HASH_P));
		for(int i=0;i<lines.length;i++) {
			updateHash_array(lines[i], i);
		}
		return hashes;
	}
}
