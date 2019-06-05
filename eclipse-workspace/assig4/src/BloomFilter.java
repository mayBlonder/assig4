public class BloomFilter{
	String HASH_P;		//hash file path.
	byte[] bloomArr;
	Hash[] hashes;
	int P = 15486907;
	int m1;				//array length.
	public static String[] badPass;

	public BloomFilter(String m1, String HASH_P){
		try {
			this.m1 = Integer.parseInt(m1);
		}
		catch (NumberFormatException nfe){
			System.out.println("NumberFormatException: " + nfe.getMessage());
		}
		this.bloomArr = new byte[this.m1];
		this.HASH_P = HASH_P;
		this.hashes = BuildHash.build_hashFunc(HASH_P);
	}

	private int hashValue(Hash hash, int k){
		return (((hash.getA()*k + hash.getB()) % P) % m1);
	}

	public void updateTable(String BAD_P){
		int tmpNum256;
		int afterHash;
		int lines_n = File_handler.file_lineNum(BAD_P);
		badPass = File_handler.readFile(BAD_P, lines_n);
		
		for(int i=0;i<badPass.length;i++) {
			tmpNum256 = UniFunctions.tonumber256(badPass[i]);
			for(int j=0;j<hashes.length;j++) {	//performing all hashes.
				afterHash = hashValue(hashes[j], tmpNum256);
				bloomArr[afterHash] = (byte)0x01;
			}
		}
	}

	private boolean isInbloomArr(int pass) {
		boolean found = true;
		for(int i=0;i<hashes.length;i++) {	//checking all hashes values.
			if(bloomArr[hashValue(hashes[i], pass)] != (byte)0x01) {
				found = false;
			}
		}
		return found;
	}

	private int[] toIntArr(String rPass) {
		int lines_n = File_handler.file_lineNum(rPass);
		String[] reqPass = File_handler.readFile(rPass, lines_n);
		return File_handler.stringArrToInt(reqPass);
	}

	public String getFalsePositivePercentage(HashTable hashT, String rPass) {
		int[] reqPassInt = toIntArr(rPass);
		double notInTale = 0;
		double rej = 0;	//rejected password that are not in the table.
		for(int i=0;i<reqPassInt.length;i++) {
			if(hashT.find(reqPassInt[i]) == -1) {	//is not in table.
				notInTale++;
				if(isInbloomArr(reqPassInt[i])) {
					rej++;	
				}
			}
		}
		return Double.toString(rej/notInTale);	//percentage of false- positive.
	}

	public String getRejectedPasswordsAmount(String req_p) {
		int[] reqPassInt = toIntArr(req_p);
		int rej = 0;	//rejected by bloom filter.
		for(int i=0;i<reqPassInt.length;i++) {
			if(isInbloomArr(reqPassInt[i])) {
				rej++;	
			}
		}
		return Integer.toString((int)rej);
	}
}
