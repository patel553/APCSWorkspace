/**
 * 
 */

/**
 * @author jack
 *
 */
public class LinkedListWithTail {

	/**
	 * @param args
	 */
	
	private ListNode head;
	private ListNode tail;
	
	
	public LinkedListWithTail()
	{
		head=null;
		tail=null;
	}
	
	public boolean isEmpty()
	{
		return head==null;
	}
	
	public Object peek()
	{
		return head;
	}
	
	public Object remove()
	{
		Object result=head;
		head=head.getNext();
		return result;
	}
	
	public boolean add(Object obj)
	{
		tail.setNext(new ListNode(obj,null));
		return true;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
