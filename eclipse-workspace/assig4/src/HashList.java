public class HashList {
	private HashListElement head;
	private int numElements;

	public HashList(int data){
		this.head = new HashListElement(data);
		this.numElements++;
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

	public int find(int toFind){
		HashListElement element = head;
		int found = -1;
		int index = 0;
		while(element != null && found == -1)
		{
			if(element.getData() == toFind) {
				found = index;
			}
			else {
				element = element.getNext();
				index++;
			}
		}
		return found;
	}

	public int getSize(){
		return numElements;
	}

	public String toString() {
		String s = "";
		HashListElement e = getHead();
		while(e != null) {
			s += ", " + e.getData();
			e = e.getNext();
		}
		return s;
	}
}
