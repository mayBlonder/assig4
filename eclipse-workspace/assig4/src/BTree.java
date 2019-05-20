
public class BTree {
	private int t;
	private BTreeNode root;
	
	public BTree(String t)
	{
		this.t=UniFunctions.toInt(t);
		root=new BTreeNode(this.t);
	}
	public void insert(String key)
	{
		if(root==null)
		{
			String[] s={key};
			root=new BTreeNode(s,t);
		}
		else
			root.insert(key);
	}
	
	public boolean search(String s)
	{
		return root.search(s);
	}
	
}
