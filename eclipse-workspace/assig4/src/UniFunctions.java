public class UniFunctions {
	public static int p=15486907;
	
	
	public static int tonumber256(String s)
	{// converting string to a number in base 265 by hornor rule
		long sum=s.charAt(0);
		int x=256;
		for(int i=1;i<=s.length()-1;i++)
		{
			sum=s.charAt(i)+(sum*x)%p;
		}
		return (int) sum;
	}
	
	public static String deCap(String s)
	{//replacing all capital letters with regular ones a string
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