
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
    
    private void moveright()
    {//moving both keys and children one spot to the right in order to make room for new key and child
    	for(int i=size-1;i>=0;i--)
    		keys[i+1]=keys[i];
    	for(int i=size;i>=0;i--)
    		children[i+1]=children[i];
    }
        private void moveright(int k)
    {//moving both keys and children one spot to the right from index k in order to make room for new key and child
    	for(int i=size-1;i>=k;i--)
    		keys[i+1]=keys[i];
    	for(int i=size;i>=k;i--)
    		children[i+1]=children[i];
    }
    private void movekeysleft(int k)
    {//moving  keys  one spot to the left from index k
    	for(int i=k;i<size;i++)
    		keys[i]=keys[i+1];
    	
    }
    private void movechildrenleft(int k)
    {//moving  children  one spot to the left from index k
    	for(int i=k;i<size;i++)
    		children[i]=children[i+1];
    }
    private void moveleft(int k)
    {//moving both keys and children one spot to the left from index k in order to make room for new key and child
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
    		int i=bigeq(key);//finding first key that is bigger  then wanted key therefore the key needs to be inserted to his left child
    		if(children[i].size==2*t-1)
    		{	splitChild(i);//Splitting the next node if needed
    			if(keys[i].compareTo(key)<0)//correcting the next node in path after split
    				i++;
    		}
    		children[i].insert(key);
    	}
    }
    
    public boolean search (String s)
    {
    	int i=bigeq(s);//finding first key that is bigger or equal then wanted key
    	if(i<size&&keys[i].equals(s))
    		return true;
    	if(leaf)
    		return false;
    	return children[i].search(s);
    }
    
    public void  splitChild(int i)
    {//spliting child with 2t-1 keys
    	BTreeNode split=this.children[i];
    	BTreeNode splitted=new BTreeNode(t);
    	splitted.leaf=split.leaf;
    	for(int j=0;j<t-1;j++) //copying the larger t-1 keys to the new node
    	{
    		splitted.keys[j]=split.getkey(j+t);
    		splitted.incrisesize();
    		
    	}
    	if(!split.leaf)//copying children as well if they exist
    	{
    		for(int j=0;j<t;j++)
        	{
        		splitted.children[j]=split.getchild(j+t);
        	}
    	}
    	moveright(i+1);//making room for the new child
    	keys[i]=split.getkey(t-1);
    	children[i+1]=splitted;
    	incrisesize();
    	split.setsize(t-1);
    }
    public void remove(String key)
    {
    	int i=bigeq(key);//finding first key that is bigger or equal then the wanted key
    	if(getkey(i).equals(key))//if i found the key proceed to delete him from this node
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
    	else//otherwise proceeding down the tree
    	{
    		if(getchild(i).getsize()==t-1)
			{
				if(sizecorrection(i)==0)//correcting the size of the next node
					getchild(i).remove(key);
				else
					getchild(i-1).remove(key);//if it was merged with the left sibling going down to the left sibling
			}
			else
				getchild(i).remove(key);
			}
    	
    	}
    
    public void leftShift(int i)//Borrowing a key from left sibling
    {
    	children[i].moveright();
    	children[i].keys[0]=keys[i-1];
    	children[i].children[0]=children[i-1].children[children[i-1].size];
    	children[i].incrisesize();
    	keys[i-1]=children[i-1].keys[children[i-1].size-1];
    	children[i-1].decrisesize();
    }
    public void rightShift(int i)//Borrowing a key from right sibling
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
    public void mergeRight(int i)  //merging a child with his right sibling
    {
    	children[i].keys[size]=keys[i];// inserting the buffer key to the left child
    	children[i].incrisesize();
    	for(int j=0;j<children[i+1].getsize();j++)
    	{// copying keys from right sibling to current child
    		children[i].keys[children[i].size+j]=children[i+1].keys[j];
    	}
    	for(int j=0;j<=children[i+1].getsize();j++)
    	{// copying children from right sibling to current child
    		children[i].children[children[i].size+j]=children[i+1].children[j];
    	}
    	children[i].size=children[i].size+children[i+1].size;// incrising the size to the correct one after merging
    	movekeysleft(i);//correcting current node keys
    	movechildrenleft(i+1);//correcting current node children
    	decrisesize();
    }
    public String  predecessor(int i)
    {// finding the predecessor of a key
    	BTreeNode node=children[i];
    	while(!node.leaf)
   			node=node.children[node.size];
   		return node.getkey(node.getsize()-1);
   	}
   	public String  successor(int i)
   	{// finding the successor of a key
   		BTreeNode node=children[i+1];
   		while(!node.leaf)
   			node=node.children[0];
   		return node.getkey(0);
   	}
   	public String toString()
   	{
   		return toString(0);//calling a privetE function that creates string with depth
   	}
   	private String toString(int depth)
   	{// going over the tree  dfs and creating a string with depth indicator
   		String s=new String();
   		if(leaf)
   		{
   			for(int j=0;j<size;j++)
   				s=s+keys[j]+"_"+depth+",";
   			return s;
   		}
   		else
   		{
   			for(int j=0;j<size;j++)
   			{
   				s=s+children[j].toString(depth+1);
   				s=s+keys[j]+"_"+depth+",";
   			}
   			s=s+children[size].toString(depth+1);
   		}
   		return s;
   	}
   	public int legitsiblingof (int i)
   	{// finding if there is a sibling with more then t-1 keys
   		if(i>0&&children[i-1].getsize()>t-1)
   			return 0; //indicating that the left sibling has more then  t-1 keys
   		else//indicating that the right sibling has more then  t-1 keys
   		{
   			if(i<size&&children[i+1].getsize()>t-1)
   				return 1;
   		}
   		return -1;//indicating that none of the siblings has more then  t-1 keys
   	}
   	public int legitchild (int i)
   	{// finding if there is a child with more then t-1 keys
   		if(i>0&&children[i].getsize()>t-1)//indicating that the left child has more then  t-1 keys
   			return 0;
   		else
   		{
   			if(i<size&&children[i+1].getsize()>t-1)
   				return 1;//indicating that the right child has more then  t-1 keys
   		}
   		return -1;//indicating that none of the children has more then  t-1 keys
   	}
    private void insertleaf(String key)
    {// inserting a key to a leaf
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
   	{// correcting the sizes of the child next in deletion path
   		int dir=legitsiblingof(i);
   		if(dir==0) //Indicating that the child has a left brother with more then t-1 keys
   		{
   			leftShift(i);
   			return 0;
   		}
   		else
   		{
   			if(dir==1)//Indicating that the child has a right brother with more then t-1 keys
   			{
   				rightShift(i);
   				return 0;
   			}
   			else//Indicating that bith right and left siblings has t-1 keys therefore merging with one of them.preferebly left
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
    public int bigeq (String key)
    {// Finding the first key in node that is bigger or equal to wanted key
    	int i=0;
		while(i<size&&getkey(i).compareTo(key)<0)
			i++;
		return i;
    }
    private void removeinnernode(String key,int i)
    {
    	int dir=legitchild(i);//saving the course of action 
    	if(dir==1)//indicating that the right child is larger then t-1
    	{
    		keys[i]=successor(i);//replacing the the deleted value with its successor
    		getchild(i+1).remove(successor(i));//deleting the successor from its subtree
    	}
    	else
    	{
    		if(dir==0)//indicating that the left child is larger then t-1
    		{
    			keys[i]=predecessor(i);//replacing the the deleted value with its predecessor
    			getchild(i).remove(predecessor(i));////deleting the predecessor from its subtree
    		}
    		else
    		{//  both right and left child sizes is t-1 therefore merging them and deleting the wanted key from the merged node
    			mergeRight(i);
    			getchild(i).remove(key);
    			
    		}
    	}
    }
    private void removeLeaf(int i)
    {// removing key from leaf
    	moveleft(i);
		decrisesize();		
    }
}
   
