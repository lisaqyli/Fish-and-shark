
public class DList {

	private int sTime;
	private int width;
	private int height;
	private DListNode head;
	private DListNode tail;
	
	
	public DList(int x, int y , int z) {
		setWidth(x);
		setHeight(y);
		setsTime(z);
		this.tail=null;
		this.head = null;
	}
public DListNode getHead() {
	return head;
}
public DListNode getTail() {
	return tail;
}
	public DListNode getFirst() {
		return head;
	}
	
	public int getsTime() {
		return sTime;
	}


	public void setsTime(int sTime) {
		this.sTime = sTime;
	}


	public int getWidth() {
		return width;
	}


	public void setWidth(int width) {
		this.width = width;
	}


	public int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		this.height = height;
	}
	
	public void insertBefore(DListNode node, DListNode nNode) {
		nNode.setNext(node);
		nNode.setPrev(node.getPrev());
		if (node.getPrev() == null) {
			this.head  =nNode;
		} else {
			node.getPrev().setNext(nNode);  //!!!!! potential prob
		}
		node.setPrev(nNode);
	}
	public void insertAfter(DListNode node, DListNode nNode) {
		nNode.setPrev(node);
		nNode.setNext(node.getNext());
		if (node.getNext() == null) {
			this.tail = nNode;
		} else {
			node.getNext().setPrev(nNode);
		}
		node.setNext(nNode);
	}
	
	public void insertFront(DListNode i) {
		if (head == null) {
			head = i;
			tail =i;
			i.setPrev(null);
			i.setNext(null);
		} else {
			insertBefore(head, i);
		}
	}
	public void insertEnd(DListNode i) {
		if (head == null) {
			insertFront(i);
		} else {
			insertAfter(tail, i);
		}
	}
}
