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
		if(c<'A'||(c>'Z'&&c<'a')||c>'z')
			throw new RuntimeException("value isnt leagal");
		String s="0123456789";
		return s.indexOf(c);
				
	}
	
	public static int tonumber256(String s)
	{
		long sum=s.charAt(0);
		int x=256;
		for(int i=1;i<=s.length()-1;i++)
		{
			sum=s.charAt(i)+(sum*x)%p;
		}
		return (int) sum;
	}
	
	public static String deCap(String s)
	{
		String decap=new String();
		for(int i=0;i<s.length();i++)
		{
			if(s.charAt(i)>='A'&&s.charAt(i)<='Z')
				decap=decap+(char)(s.charAt(i)+32);
			else
				decap=decap+s.charAt(i);
		}
		return decap;
	}
}