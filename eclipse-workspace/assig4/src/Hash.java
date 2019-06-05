public class Hash{
	private int a;	//a value for hash
	private int b;	//b value for hash

	public Hash(int a, int b){
		this.a = a;
		this.b = b;
	}

	public int getA(){
		return a;
	}

	public int getB(){
		return b;
	}

	public String toString(){
		return "a: " + a + " b: " + b;
	}
}

