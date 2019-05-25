
public class BTreeNode {
	private String [] keys;  
	private int t;       
	private BTreeNode[] children;  
	private int size;     
	private boolean leaf;
  

    public BTreeNode(int t)
    {
    	if(t<=1)
    		throw new RuntimeException ("invalid input");
    	this.t=t;
    	size=0;
    	leaf=true;
    	keys =new String[2*t-1];
    	children=new BTreeNode[2*t];
    }
    public int getsize()
    {
    	return size;
    }
    public void setsize(int size)
    {
    	this.size=size;
    }
    public void decrisesize()
    {
    	this.size--;
    }
    public void incrisesize()
    {
    	this.size++;
    }
    public String getkey(int i)
    {
    	if(i<0||i>size)
    		return null;
    	return keys[i];
    }
    public void setkey(int i,String s)
    {
    	if(i<0||i>size)
    		return;
    	keys[i]=s;
    }
    public boolean getleaf()
    {
    	return leaf;
    }
    public void setleaf(boolean leaf)
    {
    	this.leaf=leaf;
    }
    public BTreeNode getchild(int i)
    {
    	if(i<0||i>size)
    		return null;
    	return children[i];
    }
    public void setchild(int i,BTreeNode node)
    {
    	if(i<0||i>size)
    		return;
    	children[i]=node;
    }
    private void movekeysright()
    {
    	for(int i=size-1;i>=0;i--)
    		keys[i+1]=keys[i];
    }
    private void moveright()
    {
    	for(int i=size-1;i>=0;i--)
    		keys[i+1]=keys[i];
    	for(int i=size;i>=0;i--)
    		children[i+1]=children[i];
    }
        private void moveright(int k)
    {
    	for(int i=size-1;i>=k;i--)
    		keys[i+1]=keys[i];
    	for(int i=size;i>=k;i--)
    		children[i+1]=children[i];
    }
    private void moveleft()
    {
    	for(int i=0;i<size;i++)
    		keys[i]=keys[i+1];
    	for(int i=0;i<=size;i++)
    		children[i+1]=children[i];
    }
    private void moveleft(int k)
    {
    	for(int i=k;i<size;i++)
    		keys[i]=keys[i+1];
    	for(int i=k;i<=size;i++)
    		children[i]=children[i+1];
    }
    
    public void insert(String key)
    {
    	int i=size-1;
    	if(leaf)
    	{
    		while(i>=0&&(keys[i]).compareTo(key)>0)
    		{
    			keys[i+1]=keys[i];
    			i--;
    		}
    		keys[i+1]=key;
    		incrisesize();
    	}
    	else
    	{
    		while(i>=0&&(keys[i]).compareTo(key)>0)
    		{
    			i--;
    		}
    		i=i+1;
    		if(children[i].size==2*t-1)
    		{
    			splitChild(i);
    			if(keys[i].compareTo(key)<0)
    				i++;
    		}
    		children[i].insert(key);
    	}
    }
    
    public boolean search (String s)
    {
    	int i=0;
    	while(i<size&&(keys[i]).compareTo(s)<0)
    	{
    		i++;
    	}
    	if(i<size&&keys[i].equals(s))
    		return true;
    	if(leaf)
    		return false;
    	return children[i].search(s);
    }
    
    public void  splitChild(int i)
    {
    	BTreeNode split=this.children[i];
    	BTreeNode splitted=new BTreeNode(t);
    	splitted.leaf=split.leaf;
    	for(int j=0;j<t-1;j++)
    	{
    		splitted.keys[j]=split.getkey(j+t);
    		splitted.incrisesize();
    		
    	}
    	if(!split.leaf)
    	{
    		for(int j=0;j<t;j++)
        	{
        		splitted.children[j]=split.getchild(j+t);
        	}
    	}
    	moveright(i+1);
    	keys[i]=split.getkey(t-1);
    	children[i+1]=splitted;
    	incrisesize();
    	split.setsize(t-1);
    }
    public void remove(String key)
    {
    	
    }
    public void leftShift(int i)// left shifting
    {
    	children[i].moveright();
    	children[i].keys[0]=keys[i-1];
    	children[i].children[0]=children[i-1].children[children[i-1].size];
    	children[i].incrisesize();
    	keys[i-1]=children[i-1].keys[children[i-1].size-1];
    	children[i-1].decrisesize();
    }
    public void rightShift(int i)//right shifting
    {
    	children[i].keys[children[i].size]=keys[i];
    	children[i].children[children[i].size+1]=children[i+1].children[0];
    	children[i].size++;
    	keys[i]=children[i+1].keys[0];
    	children[i+1].size--;
    	for(int j=0;j<children[i+1].size;j++)
    	{
    		children[i+1].keys[j]=children[i+1].keys[j+1];
    	}
    }
    public void mergeLeft(int i) //merging with left sibling
    {
    	children[i].movekeysright();
    	children[i].keys[0]=keys[i-1];
    	children[i].incrisesize();
    	for(int j=children[i].size-1;j>=0;j--)
    	{
    		children[i].keys[children[i-1].size+j]=children[i].keys[j];
    	}
    	for(int j=children[i].size;j>=0;j--)
    	{
    		children[i].children[children[i-1].size+j]=children[i].children[j];
    	}
    	for(int j=0;j<children[i-1].size;j++)
    	{
    		children[i].keys[j]=children[i-1].keys[j];
    		children[i].incrisesize();
    	}
    	for(int j=0;j<=children[i-1].size;j++)
    	{
    		children[i].children[j]=children[i-1].children[j];
    	}
    	moveleft(i-1);
    	decrisesize();
    }
    public void mergeRight(int i)  //merging with right sibling
    {
    	children[i].keys[size]=keys[i];
    	children[i].incrisesize();
    	for(int j=0;j<children[i+1].getsize();j++)
    	{
    		children[i].keys[children[i].size+j]=children[i+1].keys[j];
    	}
    	for(int j=0;j<=children[i+1].getsize();j++)
    	{
    		children[i].children[children[i].size+j]=children[i+1].children[j];
    	}
    	children[i].size=children[i].size+children[i+1].size;
    	for(int j=0;j<children[i-1].size;j++)
    	{
    		children[i].keys[j]=children[i-1].keys[j];
    		children[i].incrisesize();
    	}
    	for(int j=0;j<=children[i-1].size;j++)
    	{
    		children[i].children[j]=children[i-1].children[j];
    	}
    	decrisesize();
    }
    public BTreeNode  predessesor(int i)
    {
    	BTreeNode node=children[i];
    	while(!node.leaf)
   			node=node.children[node.size];
   		return node;
   	}
   	public BTreeNode  sucssesor(int i)
   	{
   		BTreeNode node=children[i+1];
   		while(!node.leaf)
   			node=node.children[0];
   		return node;
   	}
   	public String toString()
   	{
   		return toString(0);
   	}
   	private String toString(int i)
   	{
   		String s=new String();
   		if(leaf)
   		{
   			for(int j=0;j<size;j++)
   				s=s+keys[j]+"_"+i+",";
   			return s;
   		}
   		else
   		{
   			for(int j=0;j<size;j++)
   			{
   				s=s+children[j].toString(i+1);
   				s=s+keys[j]+"_"+i+",";
   			}
   			s=s+children[size].toString(i+1);
   		}
   		return s;
   	}
    	
    	
    
}
