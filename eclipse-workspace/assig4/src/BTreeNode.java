
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
    /*private void movekeysright()
    {
    	for(int i=size-1;i>=0;i--)
    		keys[i+1]=keys[i];
    }*/
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
    private void movekeysleft(int k)
    {
    	for(int i=k;i<size;i++)
    		keys[i]=keys[i+1];
    	
    }
    private void movechildrenleft(int k)
    {
    	
    	for(int i=k;i<size;i++)
    		children[i]=children[i+1];
    }
    private void moveleft(int k)
    {
    	
    	for(int i=k;i<size-1;i++)
    		keys[i]=keys[i+1];
    	for(int i=k;i<size;i++)
    		children[i]=children[i+1];
    }
    
    public void insert(String key)
    {
       	if(leaf)
    	{
    		insertleaf(key);
    	}
    	else
    	{
    		int i=size-1;
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
    	int i=lesseq(s);
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
    	int i=lesseq(key);
    	if(getkey(i).equals(key))
    	{
    		if(leaf)
    		{
    			removeLeaf(i);
    		}
    		else
    		{
    			removeinnernode(key,i);
    		}
       	}
    	else
    	{
    		if(getchild(i).getsize()==t-1)
			{
				if(sizecorrection(i)==0)
					getchild(i).remove(key);
				else
					getchild(i-1).remove(key);
			}
			else
				getchild(i).remove(key);
			}
    	
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
    	children[i+1].moveleft(0);
    	children[i+1].decrisesize();
    	
    }
    public void mergeLeft(int i) //merging with left sibling
    {
    	children[i].moveright();
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
    	movekeysleft(i);
    	movechildrenleft(i+1);
    	decrisesize();
    }
    public String  predessesor(int i)
    {
    	BTreeNode node=children[i];
    	while(!node.leaf)
   			node=node.children[node.size];
   		return node.getkey(node.getsize()-1);
   	}
   	public String  sucssesor(int i)
   	{
   		BTreeNode node=children[i+1];
   		while(!node.leaf)
   			node=node.children[0];
   		return node.getkey(0);
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
   	public int legitsiblingof (int i)
   	{
   		if(i>0&&children[i-1].getsize()>t-1)
   			return 0;
   		else
   		{
   			if(i<size&&children[i+1].getsize()>t-1)
   				return 1;
   		}
   		return -1;
   	}
   	public int legitchild (int i)
   	{
   		if(i>0&&children[i].getsize()>t-1)
   			return 0;
   		else
   		{
   			if(i<size&&children[i+1].getsize()>t-1)
   				return 1;
   		}
   		return -1;
   	}
    private void insertleaf(String key)
    {
    	int i=size-1;
    	while(i>=0&&(keys[i]).compareTo(key)>0)
		{
			keys[i+1]=keys[i];
			i--;
		}
		keys[i+1]=key;
		incrisesize();
    }
    public int sizecorrection (int i)
   	{
   		int dir=legitsiblingof(i);
   		if(dir==0)
   		{
   			leftShift(i);
   			return 0;
   		}
   		else
   		{
   			if(dir==1)
   			{
   				rightShift(i);
   				return 0;
   			}
   			else
   			{
   				if(i==0)
   				{
   					mergeRight(i);
   					return 0;
   				}
   				else
   					mergeLeft(i);
   				return -1;
   			}
   		}
   	}
    public int lesseq (String key)
    {
    	int i=0;
		while(i<size&&getkey(i).compareTo(key)<0)
			i++;
		return i;
    }
    private void removeinnernode(String key,int i)
    {
    	int dir=legitchild(i);
    	if(dir==1)
    	{
    		keys[i]=sucssesor(i);
    		getchild(i+1).remove(sucssesor(i));
    	}
    	else
    	{
    		if(dir==0)
    		{
    			keys[i]=predessesor(i);
    			getchild(i).remove(predessesor(i));
    		}
    		else
    		{
    			mergeRight(i);
    			getchild(i).remove(key);
    			
    		}
    	}
    }
    private void removeLeaf(int i)
    {
    	moveleft(i);
		decrisesize();		
    }
}
   
