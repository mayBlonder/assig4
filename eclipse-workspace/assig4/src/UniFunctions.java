
public class UniFunctions {
	public static int p=15486907;
	public static int toInt(String s)
	{
		int sum=0;
		for(int i=s.length()-1;i>=0;i--)
			sum=sum+(10^i)*toInt(s.charAt(i));
		return sum;
	}
	private static int toInt(char c)
	{
		String s="0123456789";
		return s.indexOf(c);
				
	}
	public static int tonumber256(String s)
	{
		int sum=0;
		for(int i=s.length()-1;i>=0;i--)
			sum=(sum+(256^i)*(int)(s.charAt(i)))%p;
		return sum;
	}
}
