
public class DListNode {
	private DListNode prev;
	private DListNode next;
	private int type;
	private int feed;
	private int length;
	
	public DListNode(int x, int y, int z) {
		type = x;
		feed = y;
		length = z;
		this.prev= null;
		this.next = null;
	}
	public DListNode getPrev() {
		return prev;
	}
	public void setPrev(DListNode prev) {
		this.prev = prev;
	}
	public DListNode getNext() {
		return next;
	}
	public void setNext(DListNode next) {
		this.next = next;
	}
	public int getFeed() {
		return feed;
	}
	public void setFeed(int feed) {
		this.feed = feed;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	

}
