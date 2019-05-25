public class HashListElement {
	private HashListElement next;
	private HashListElement prev;
	private int data;
	
	public HashListElement(int data){
		this.data = data;
	}
	
	public int getData(){
		return data;
	}
	
	public HashListElement getNext() {
		return next;
	}
	
	public void setData(int data){
		this.data = data;
	}
	
	public void setNext(HashListElement next) {
		this.next = next;
	}
	
	public HashListElement getPrev() {
		return prev;
	}
	
	public void setPrev(HashListElement prev){
		this.prev = prev;
	}
}
