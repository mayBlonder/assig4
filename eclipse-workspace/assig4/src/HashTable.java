public class HashTable {
	private String[] badP;
	private HashList[] table;
	private int total_elements;
	int P = 15486907;
	private int n; //initial size of the table
	
	/*public static void main(String[] args) {
		HashTable hashTable = new HashTable("4");
		String t = hashTable.getSearchTime(System.getProperty("user.dir")+"/requested_passwords.txt");
		System.out.println(t);
	}*/
	public HashTable(String n) {
		try {
			this.n = Integer.parseInt(n);
		}
		catch (NumberFormatException nfe){
			System.out.println("NumberFormatException: " + nfe.getMessage());
		}
		this.table = new HashList[this.n];
		this.total_elements = 0;
	}

	public HashList[] getTable() {
		return this.table;
	}
	
	public int find(HashListElement toFind) {
		int found = -1;
		int i = 0;
		while(found == -1 && i<table.length) {
			if(table[i] != null) {
				found = table[i].find(toFind);
			}
			i++;
		}
		return found;
	}

	public void updateTable(String bad_p){
		int tmpNum256;
		badP = File_handler.readFile(bad_p, File_handler.file_lineNum(bad_p));
		for(int i=0;i<badP.length;i++) {
			tmpNum256 = UniFunctions.tonumber256(badP[i]);
			if(table[hashFunction(tmpNum256)] == null){
				table[hashFunction(tmpNum256)] = new HashList(tmpNum256);
			}
			else{
				table[hashFunction(tmpNum256)].add(tmpNum256);
			}
		}
	}

	public void addKey(int key) {
		if(this.total_elements < this.n) {
			table[hashFunction(key)].add(key);
		}
		else {  
			n = 2*n;	//multiply by 2 the size of the table.
			HashList[] tmpTable = new HashList[n];
			for(int i=0;i<table.length;i++) {
				HashListElement elem = table[i].getHead();
				while(elem.getNext() != null)
					tmpTable[hashFunction(elem.getData())].add(elem.getData());
				elem = elem.getNext();
			}
			table = tmpTable;
			table[hashFunction(key)].add(key);	//add the wanted key.
		}
	}

	private int hashFunction(int num) {
		return (num % P) % n;
	}
	
	public String getSearchTime(String req_p) {
		long startTime = System.nanoTime();
		int lines_n = File_handler.file_lineNum(req_p);
		int[] reqPass = File_handler.stringArrToInt(File_handler.readFile(req_p, lines_n));
		for(int i=0;i<reqPass.length;i++) {
			HashListElement e = new HashListElement(reqPass[i]);
			this.find(e);
		}
		long endTime = System.nanoTime();
		return Double.toString((double)(endTime - startTime)/1000000);
	}

	public String toString() {
		String s = "";
		for(int i=0;i<table.length;i++) {
			if(table[i] != null) {
				HashListElement elem = table[i].getHead();
				s += "[";
				while(elem != null)
				{
					s += elem.getData() + ",";
					elem = elem.getNext();
				}
				s += "]\n";
			}
		}	
		return s;
	}
}
