import java.text.DecimalFormat;

public class BTree {
	private int t;
	private BTreeNode root;
	
	public BTree(String t)
	{//builder
		int s=Integer.parseInt(t);
		if(t==null||s<=1)//making sure t has legitimate value
			throw new RuntimeException("ileagal input");
		this.t=s;
		root=new BTreeNode(this.t);
	}
	public void insert(String key)
	{
		if(key==null)
			throw new RuntimeException("ileagal input");
		if(root.getsize()==2*t-1)//Splitting the leaf if needed
		{
			BTreeNode nroot=new BTreeNode(t);
			nroot.setleaf(false);
			nroot.setchild(0,root);
			root=nroot;
			root.splitChild(0);
			root.insert(UniFunctions.deCap(key));
		}
		else
			root.insert(UniFunctions.deCap(key));
			
	}
	
	public boolean search(String s)
	{
		if(s==null)
			throw new RuntimeException("ileagal input");
		return root.search(UniFunctions.deCap(s));
	}
	public void createFullTree(String bad_p) {
		// inserting values from text file by line
		String [] badP = File_handler.readFile(bad_p, File_handler.file_lineNum(bad_p));
		for(int i=0;i<badP.length;i++)
			insert(badP[i]);
	}
	public void remove(String key)
	{
		if(key==null||!search(key)) //making sure key is in the tree
		{}
		else
		{
			key=UniFunctions.deCap(key);//replacing all capital letters in the key
			int i=root.bigeq(key);//finding the fist key that is bigger or equal to wanted key to remove
			if(i<root.getsize()&&root.getkey(i).equals(key))
				{
					root.remove(key);
				}
			else
			{
				if(root.getchild(i).getsize()==t-1)//cheking if the next node in path has enough keys
				{
					if(root.sizecorrection(i)==0)//correcting the size of the next node
						root.getchild(i).remove(key);
					else
						root.getchild(i-1).remove(key);
				}
				else
					root.getchild(i).remove(key);
				}
			}
		updateRoot();
	}
	public void deleteKeysFromTree(String delet) {
		String [] delets = File_handler.readFile(delet, File_handler.file_lineNum(delet));
		for(int i=0;i<delets.length;i++)
			remove(delets[i]);
		
	}
	public String toString()
	{
		String s= root.toString();
		if(s.length()==0)
			return s;
		return s.substring(0,s.length()-1);
	}
	public String getSearchTime(String reqpasswords) {
		long start = System.nanoTime();    
		String [] passwords = File_handler.readFile(reqpasswords, File_handler.file_lineNum(reqpasswords));
		for(int i=0;i<passwords.length;i++)
			search(passwords[i]);
		Double elapsedTime = (System.nanoTime() - start)/1000000.0;
		DecimalFormat numberFormat = new DecimalFormat("0.0000");
		return numberFormat.format(elapsedTime);
	}
	private void updateRoot()
	{//updating root if height shrank
		if(root.getsize()==0)
		{
			if(root.getchild(0)!=null)
				root=root.getchild(0);
		}
	}
	
	
}
