import java.util.HashMap;

public class HuffmanNode {

    public char value;
    public int frequency;
    public HuffmanNode left;
    public HuffmanNode right;
    public static char NOVAL = '+';

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
        if (head.value != HuffmanNode.NOVAL) {
            map.put(head.value, val);
        }
        generateCodes(head.left, map, val + '0');
        generateCodes(head.right, map, val + '1');
    }

    public void generateCodesHelper(HuffmanNode head, HashMap<Character, String> map, String val) {
        if (head == null) {
            return;
        }
        if (head.value != HuffmanNode.NOVAL) {
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

    private void inorderTraversalHelper(HuffmanNode head) {
        if (head == null) {
            return;
        }
        inorderTraversalHelper(head.left);
        System.out.println(head.value);
        inorderTraversalHelper(head.right);
    }
    public void inorderTraversal() {
        inorderTraversalHelper(this);
    }

}
