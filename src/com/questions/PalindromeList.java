package com.gt.bishi.nowcoder;

/**
 * ����Ļ��Ľṹ
 * ע��㣺1��������
 * 		2�����õ�ʱ��Ҫnewһ�£�
 * @author checkermu
 *
 * ˼��ܼ򵥣��ҵ���һ��������ȵģ�Ȼ�������ضϳ����룬���η�ת���õ���������ã���
 * 
 * Ȼ�����������ͷ�ȵ�β��������ֶ���ȣ���������ģ�
 * 
 * ע�ⷴת�����ʱ���һ�� preҪnewһ��******���Ǽ���Ҫ������
 * ��Ȼ����ɺ��س��ȶ��࣡ ��������ʵ��µģ���
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
		//����ͨ����ת������������ж��Ƿ��ǻ�������
		//ͷ�巨��ת���� �� tmp��ʼ��ת
		ListNode pre =null, p2 = null, ne=null;
		//����Ϊɶһ��Ҫnewһ���أ���
		pre = new ListNode(tmp.val);
		p2 = tmp.next;
		while(p2!=null){
			//���ڿ�ʼ��ת���� Ҫ��p2��next��ΪC����ôҪ�ȱ���p2��next
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
