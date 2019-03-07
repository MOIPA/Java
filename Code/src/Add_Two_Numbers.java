import java.util.Stack;

//Example:
//
//        Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
//        Output: 7 -> 0 -> 8
//        Explanation: 342 + 465 = 807.
public class Add_Two_Numbers {
    public static void main(String[] args) {

        ListNode l1 = new ListNode(5);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        ListNode l2 = new ListNode(5);
//        l2.next = new ListNode(6);
//        l2.next.next = new ListNode(4);

        Solution solution = new Solution();

        ListNode listNode = solution.addTwoNumbers(l1, l2);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
}


 class ListNode {
     int val;
     ListNode next;
      ListNode(int x) { val = x; }
 }

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        //思路：算出各自的节点数量，统一，不足的置0
        int l1Number = 0;
        int l2Number = 0;
        ListNode temp = l1;
        ListNode sumNode = null;
        //先算l1
        while (temp != null) {
            l1Number++;
            temp = temp.next;
        }
        temp = l2;
        //算l2
        while (temp != null) {
            l2Number++;
            temp = temp.next;
        }
        //找出小的那个，算出初始化位数  在尾部插入0节点
        if(l1Number>l2Number){
            //初始化l2
            temp = l2;
            while (temp != null && temp.next!=null) {
                temp = temp.next;
            }
            //temp为l2尾部节点
            for (int i = 0; i < l1Number - l2Number; i++) {
                temp.next = new ListNode(0);
                temp = temp.next;
            }
        } else if (l1Number < l2Number) {
            //初始化l1
            temp = l1;
            while (temp != null && temp.next!=null) {
                temp = temp.next;
            }
            //temp为l1尾部节点
            for (int i = 0; i < l2Number - l1Number; i++) {
                temp.next = new ListNode(0);
                temp = temp.next;
            }
        }
        //现在位数相同 可以相加
        temp = l1;  //为了防止丢掉l1的头节点
        while (l1 != null) {  //把所有数值加载l1上  最后给temp
            l1.val+=l2.val;
            l1 = l1.next;
            l2 = l2.next;
        }
        //处理进位问题
        l1 = temp;
        while (l1 != null) {
            if (l1.val >= 10) {
                l1.val -=10;
                if(l1.next==null)l1.next = new ListNode(1);
                else l1.next.val+=1;
            }
            l1 = l1.next;
        }
        return temp;
    }

    /**
     * bug 入栈的这些节点本身就有关系，强行出栈并指定next导致了循环链表
     * @param node
     * @return
     */
    private ListNode getReverseList(ListNode node) {
        Stack<ListNode> stack = new Stack<ListNode>();
        ListNode outNode = null;
        ListNode temp = null;
        while (node != null) {
            ListNode tempNode = node;
            stack.push(node);
            node = node.next;
            tempNode.next=null;
        }
        if (!stack.isEmpty()) {
            outNode = stack.pop();
            temp = outNode;
        } else if (stack.isEmpty()) {
            return null;
        }
        while (!stack.isEmpty()) {
            temp.next = stack.pop();
            temp = temp.next;
        }
        return outNode;
    }

    public void printNode(ListNode node) {
        while (node != null) {
            System.out.print(node.val);
            node = node.next;
        }
    }
}
