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
		long sum=0;
		for(int i=s.length()-1;i>=0;i--)
			sum=(sum+(256^i)*(int)(s.charAt(i)))%p;
		return (int) sum;
	}
	public static String deCap(String s)
	{
		String decap=new String();
		for(int i=0;i<s.length();i++)
		{
			if(s.charAt(i)>='A'&&s.charAt(i)<='Z')
				decap=decap+(s.charAt(i)+25);
			else
				decap=decap+s.charAt(i);
		}
		return decap;
	}
}