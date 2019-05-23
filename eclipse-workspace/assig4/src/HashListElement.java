public class HashListElement {
	private HashListElement next;
	private Object data;
	
	public HashListElement(Object data){
		this.data = data;
	}
	
	public Object getData(){
		return data;
	}
	
	public HashListElement getNext() {
		return next;
	}
	
	public void setData(Object data){
		this.data = data;
	}
	
	public void setNext(HashListElement next) {
		this.next = next;
	}
}
