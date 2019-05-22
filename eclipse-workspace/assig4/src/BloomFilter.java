import java.util.Arrays;

public class BloomFilter{
	static String BAD_P = "C:\\Users\\maybl\\eclipse-workspace\\assig4\\src\\bad_passwords.txt";
	byte[] bloomArr;
	Hash[] hashes;
	int P = 15486907;
	int m1;

	public BloomFilter(int m1){
		this.bloomArr = new byte[m1];
		for(int i=0;i<bloomArr.length;i++) {	//initializing the array.
			bloomArr[i] = (byte)0x00;
		}
		this.m1 = m1;
		this.hashes = BuildHash.build_hashFunc();
		buildFilterBloom();
	}

	public int hashValue(Hash hash, int k){
		return (((hash.a*k) + hash.b % P) % m1);
	}

	public void buildFilterBloom(){
		int tmpNum256;
		int afterHash;
		String[] badPass = files.readFile(BAD_P, 11);
		for(int i=0;i<badPass.length;i++) {
			tmpNum256 = UniFunctions.tonumber256(badPass[i]);
			afterHash = hashValue(hashes[i%hashes.length], tmpNum256);
			bloomArr[afterHash] = (byte)0x01;
		}
		System.out.println(Arrays.toString(bloomArr));
	}
	
	public static void main(String[] args) {
		BloomFilter b = new BloomFilter(12);
	}
}
