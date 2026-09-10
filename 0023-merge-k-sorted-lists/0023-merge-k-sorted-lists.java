/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {

        PriorityQueue<ListNode> pq =
            new PriorityQueue<>((a, b) -> a.val - b.val);

        // Add first node of every list
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                pq.add(lists[i]);
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode temp = dummy;

        while (!pq.isEmpty()) {

            // Smallest node
            ListNode node = pq.poll();

            // Add it to answer
            temp.next = node;
            temp = temp.next;

            // Add next node from same list
            if (node.next != null) {
                pq.add(node.next);
            }
        }

        return dummy.next;
    }
}