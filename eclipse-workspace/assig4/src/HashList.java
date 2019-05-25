public class HashList {
	private HashListElement head;
	private static int numElements;

	public HashList(int data){
		this.head = new HashListElement(data);
	}

	public HashListElement getHead(){
		return this.head;
	}

	public void add(int data){
		HashListElement prevHead = head;
		head = new HashListElement(data);
		prevHead.setPrev(head);
		head.setNext(prevHead);
		numElements++;
	}

	public void delete(HashListElement toDelete) {
		if (head != null && toDelete != null) {
			if (head == toDelete) { 
				head = toDelete.getNext();
			} 
			if (toDelete.getNext() != null) { 
				toDelete.getNext().setPrev(toDelete.getPrev()); 
			}
			if (toDelete.getPrev() != null) { 
				toDelete.getPrev().setNext(toDelete.getNext()); 
			} 
			numElements--;
		}
	}

	public int find(HashListElement toFind){
		HashListElement element = head;
		int found = -1;
		int index = 0;
		while(element != null && found == -1)
		{
			if(element.getData() == toFind.getData()) {
				found = index;
			}
			else {
				element = element.getNext();
				index++;
			}
		}
		return found;
	}

	public static int getSize(){
		return numElements;
	}
}
