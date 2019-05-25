
public class BTree {
	private int t;
	private BTreeNode root;
	
	public BTree(String t)
	{
		if(t==null)
			throw new RuntimeException("ileagal input");
		this.t=Integer.parseInt(t);
		root=new BTreeNode(this.t);
	}
	public void insert(String key)
	{
		if(key==null)
			throw new RuntimeException("ileagal input");
		if(root.getsize()==2*t-1)
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
		if(key==null||!search(key))
		{}
		else
		{
			key=UniFunctions.deCap(key);
			int i=0;
			while(root.getkey(i).compareTo(key)<0)
				i++;
			if(root.getkey(i).equals(key))
			{
				
				if(root.getleaf())
				{
					root.removeleaf();
				}
				else
				{
					if(root.getchild(i).getsize()>t-1)
					{
						BTreeNode node=	root.predessesor(i);
						root.getchild(i).remove(node.getkey(node.getsize()-1));
					}
					else
					{
						if(root.getchild(i+1).getsize()>t-1)
						{
							BTreeNode node=	root.sucssesor(i);
							root.getchild(i).remove(node.getkey(0));
						}
						else
						{
							root.mergeRight(i);
							root.getchild(i).remove(key);
						}
						
					}
				}
			}
			else
			{
				if(root.getchild(i).getsize()>t-1)
				{
					root.getchild(i).remove(key);
				}
				else
				{
					if(i>0)
					{
						if(root.getchild(i-1).getsize()>t-1)
							root.leftShift(i);
						else
						{
							if(i<root.getsize())
							{
								root.rightShift(i);
							}
							else
								root.mergeLeft(i);
								
						}
					}
					else
					{
						if(root.getchild(i+1).getsize()>t-1)
							root.rightShift(i);
						else
							root.mergeRight(i);
					}
									
				}
				root.getchild(i).remove(key);
			}
	
		}
	}
	public void deleteKeysFromTree(String string) {
		// ideleting values from text file by line
		
	}
	public String toString()
	{
		String s= root.toString();
		return s.substring(0,s.length()-1);
	}
	public String getSearchTime(String reqpasswords) {
		// TODO Auto-generated method stub
		long start = System.nanoTime();    
		String [] passwords = File_handler.readFile(reqpasswords, File_handler.file_lineNum(reqpasswords));
		for(int i=0;i<passwords.length;i++)
			search(passwords[i]);
		Double elapsedTime = (System.nanoTime() - start)/1000000.0;
		return elapsedTime.toString();
	}
	
	
}
