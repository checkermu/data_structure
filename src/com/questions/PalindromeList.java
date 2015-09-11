package com.gt.bishi.nowcoder;

/**
 * 链表的回文结构
 * 注意点：1，分两半
 * 		2，逆置的时候要new一下！
 * @author checkermu
 *
 * 思想很简单：找到第一次两个相等的，然后把链表截断成两半，后半段反转（用到链表的逆置）；
 * 
 * 然后两个链表从头比到尾，如果数字都相等，则表明回文！
 * 
 * 注意反转链表的时候第一个 pre要new一下******五星级重要！！，
 * 不然会造成后半截长度多余！ 链表的性质导致的！！
 *
 */
public class PalindromeList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PalindromeList pl = new PalindromeList();
		ListNode p1 =new ListNode(100);ListNode p2 =new ListNode(54);
		ListNode p3 =new ListNode(98);ListNode p4 =new ListNode(98);
		ListNode p5 =new ListNode(54);ListNode p6 =new ListNode(100);
		p1.next=p2; p2.next=p3; p3.next=p4;p4.next = p5; p5.next=p6;
		
		System.out.println(pl.chkPalindrome(p1));
	}
	
	public boolean chkPalindrome(ListNode A) {
        // write code here
		ListNode p = A;
		ListNode tmp = null;
		while(p.next!=null && p.next.val != p.val){
			p = p.next;
		}
		tmp = p.next;
		p.next=null;
		if(tmp==null)
			return false;
		p=A;
		//下面通过反转后面的链表来判断是否是回文链表
		//头插法反转链表 从 tmp开始反转
		ListNode pre =null, p2 = null, ne=null;
		//这里为啥一定要new一下呢？？
		pre = new ListNode(tmp.val);
		p2 = tmp.next;
		while(p2!=null){
			//现在开始反转咯， 要把p2的next变为C，那么要先保存p2的next
			ne = p2.next;
			p2.next=pre;
			pre = p2;
			p2 = ne;
		}
		boolean flag = true;
		while(p!=null && pre!=null){
			if(p.val!=pre.val){
				flag = false;
				break;
			}
			p=p.next;
			pre = pre.next;
		}
		
		if(p!=null||p2!=null)
			flag = false;
		
		return flag;
    }
	
	
	

}
class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}
