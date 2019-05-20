
public class BTreeNode {
	protected String [] keys;  
	protected int t;       
	protected BTreeNode[] childs;  
	protected int size;     
	protected boolean leaf;
  

    public BTreeNode(int t)
    {
    	this.t=t;
    	size=0;
    	leaf=true;
    	keys =new String[2*t-1];
    	childs=new BTreeNode[2*t];
    }
    public BTreeNode(String []keys,int t)
    {
    	this.t=t;
    	this.keys =new String[2*t-1];
    	size=keys.length;
    	leaf=true;
    }
    public void insert(String key)
    {
    	int i=size;
    	if(leaf)
    	{
    		while(i>=0&&(UniFunctions.deCap(keys[i])).compareTo(UniFunctions.deCap(key))>0)
    		{
    			keys[i+1]=keys[i];
    			i--;
    		}
    		keys[i+1]=key;
    		size++;
    	}
    	else
    	{
    		while(i>=0&&(UniFunctions.deCap(keys[i])).compareTo(UniFunctions.deCap(key))>0)
    		{
    			i--;
    		}
    		i=i+1;
    		if(childs[i].size==2*t-1)
    		{
    			splitChild(i);
    			if(UniFunctions.deCap(keys[i]).compareTo(UniFunctions.deCap(key))<0)
    				i++;
    			childs[i].insert(key);
    		}
    	}
    }
    
    public boolean search (String s)
    {
    	int i=0;
    	String decap=UniFunctions.deCap(s);
    	while(i<size&&(UniFunctions.deCap(keys[i])).compareTo(decap)<0)
    	{
    		i++;
    	}
    	if(keys[i].equals(s))
    		return true;
    	if(leaf)
    		return false;
    	return childs[i-1].search(s);
    }
    
    public void  splitChild(int i)
    {
    	BTreeNode split=this.childs[i];
    	BTreeNode splitted=new BTreeNode(t);
    	splitted.leaf=split.leaf;
    	for(int j=0;i<t-1;i++)
    	{
    		splitted.keys[j]=split.keys[j+t];
    		
    	}
    	if(!split.leaf)
    	{
    		for(int j=0;i<t;i++)
        	{
        		splitted.childs[j]=split.childs[j+t];
        		
        	}
    	}
    	for(int j=size-1;j>i;j--)
    	{
    		childs[j+1]=childs[j];
    		keys[j+1]=keys[j];
    	}
    	childs[i+1]=splitted;
    	keys[i+1]=split.keys[t-1];
    	size++;
    	split.size=t-1;
    	
    }
    
}
