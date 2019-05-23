import java.util.Arrays;

public class BloomFilter{
	//static String BAD_P = "C:\\Users\\maybl\\eclipse-workspace\\assig4\\src\\bad_passwords.txt";
	String HASH_P;
	byte[] bloomArr;
	Hash[] hashes;
	int P = 15486907;
	int m1;

	public BloomFilter(String m1, String HASH_P){
		this.m1 = Integer.parseInt(m1);
		this.bloomArr = new byte[this.m1];
		this.HASH_P = HASH_P;
		this.hashes = BuildHash.build_hashFunc(HASH_P);
	}

	public int hashValue(Hash hash, int k){
		return (((hash.a*k) + hash.b % P) % m1);
	}

	public void updateTable(String BAD_P){
		int tmpNum256;
		int afterHash;
		String[] badPass = File_handler.readFile(BAD_P, File_handler.file_lineNum(BAD_P));
		for(int i=0;i<badPass.length;i++) {
			tmpNum256 = UniFunctions.tonumber256(badPass[i]);
			afterHash = hashValue(hashes[i%hashes.length], tmpNum256);
			bloomArr[afterHash] = (byte)0x01;
		}
		System.out.println(Arrays.toString(bloomArr));
	}
	
	public static void main(String[] args) {
		//BloomFilter b = new BloomFilter(12);
	}
}
