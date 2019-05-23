public class HashList {
	private static HashListElement head;
	private static int numElements;

	public HashList(Object data){
		head = new HashListElement(data);
	}

	public void addFirst(Object data){
		HashListElement tmp = head;
		head = new HashListElement(data);
		head.setNext(tmp);
		numElements++;
	}

	public void addLast(Object data){
		HashListElement tmp = head;
		while(tmp.getNext() != null)
		{
			tmp = tmp.getNext();
		}

		tmp.setNext(new HashListElement(data));
		numElements++;
	}

	public void addataIndex(int index, Object data){
		HashListElement tmp = head;
		HashListElement holder;
		for(int i=0; i < index-1 && tmp.getNext() != null; i++)
		{
			tmp = tmp.getNext();
		}
		holder = tmp.getNext();
		tmp.setNext(new HashListElement(data));
		tmp.getNext().setNext(holder);
		numElements++;
	}

	public void deleteAtIndex(int index){
		HashListElement tmp = head;
		for(int i=0; i< index - 1 && tmp.getNext() != null; i++)
		{
			tmp = tmp.getNext();
		}
		tmp.setNext(tmp.getNext().getNext());
		numElements--;
	}

	public static int find(HashListElement n){
		HashListElement t = head;
		int index = 0;
		while(t != n)
		{
			index++;
			t = t.getNext();
		}
		return index;
	}

	public static HashListElement find(int index){
		HashListElement tmp=head;
		for(int i=0; i<index; i++)
		{
			tmp = tmp.getNext();
		}
		return tmp;
	}

	public static void printList(){
		HashListElement tmp = head;
		while(tmp != null)
		{
			System.out.println(tmp.getData());
			tmp = tmp.getNext();
		}
	}

	public static int getSize(){
		return numElements;
	}
}
