
public class BTree {
	private int t;
	private BTreeNode root;
	
	public BTree(String t)
	{
		if(t==null)
			throw new RuntimeException("ileagal input");
		this.t=UniFunctions.toInt(t);
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
			root.insert(key);
		}
		else
			root.insert(key);
			
	}
	
	public boolean search(String s)
	{
		if(s==null)
			throw new RuntimeException("ileagal input");
		return root.search(s);
	}
	public void createFullTree(String string) {
		// inserting values from text file by line
		
	}
	public void remove(String key)
	{
		if(key==null||!search(key))
		{}
		else
		{
			
	
		}
	}
	public void deleteKeysFromTree(String string) {
		// ideleting values from text file by line
		
	}
	
	
}
