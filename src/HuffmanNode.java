import java.util.*;

public class HuffmanNode {

    public char value;
    public int frequency;
    public HuffmanNode left;
    public HuffmanNode right;
    public static final char NO_VAL = '+';

    HuffmanNode(char value, int frequency) {
        this.value = value;
        this.frequency = frequency;
    }

    HuffmanNode(char value, int frequency, HuffmanNode left, HuffmanNode right) {
        this.value = value;
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }

    public static void generateCodes(HuffmanNode head, HashMap<Character, String> map, String val) {
        if (head == null) {
            return;
        }
        if (head.value != HuffmanNode.NO_VAL) {
            map.put(head.value, val);
        }
        generateCodes(head.left, map, val + '0');
        generateCodes(head.right, map, val + '1');
    }

    public HashMap<Character, String> generateCodes() {
        HashMap<Character, String> map = new HashMap<>();
        generateCodesHelper(this, map, "");
        return map;
    }
    public void generateCodesHelper(HuffmanNode head, HashMap<Character, String> map, String val) {
        if (head == null) {
            return;
        }
        if (head.value != HuffmanNode.NO_VAL) {
            map.put(head.value, val);
        }
        generateCodes(head.left, map, val + '0');
        generateCodes(head.right, map, val + '1');
    }

    public void inorderTraversal() {
        inorderTraversalHelper(this);
    }
    private void inorderTraversalHelper(HuffmanNode head) {
        if (head == null) {
            return;
        }
        inorderTraversalHelper(head.left);
        System.out.println(head.value);
        inorderTraversalHelper(head.right);
    }

    public void preorderTraversal() {
        HuffmanNode temp = this;
        preorderTraversalHelper(temp);
    }
    private void preorderTraversalHelper(HuffmanNode node) {
        if (node == null) {
            return;
        }
        System.out.println(node.frequency);
        preorderTraversalHelper(node.left);
        preorderTraversalHelper(node.right);
    }

    //! needs revision
    public List<HuffmanNode> levelOrder() {
        return levelOrderHelper(this);
    }
    public List<HuffmanNode> levelOrderHelper(HuffmanNode root) {
        List<HuffmanNode> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<HuffmanNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            HuffmanNode current = queue.poll();
            result.add(current);
            System.out.print(current.value + " ");

            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
        }
        return result;
    }

    @Override
    public String toString() {
        return this.value + "," + this.frequency;
    }

}