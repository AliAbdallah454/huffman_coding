public class Main {

    public static void main(String[] args) {

        HuffmanCoding huffC = new HuffmanCoding("./texts/test1.txt");
        String x = huffC.compress();
        System.out.println(x);

//        String text = "Hello world";
//
//        HashMap<Character, Integer> frequencies = new HashMap<>();
//        PriorityQueue<HuffmanNode> minHeap = new PriorityQueue<>(new Comparator<HuffmanNode>() {
//            @Override
//            public int compare(HuffmanNode n1, HuffmanNode n2) {
//                return Integer.compare(n1.frequency, n2.frequency);
//            }
//        });
//
//        for(int i = 0; i < text.length(); i++) {
//            char c = text.charAt(i);
//            frequencies.put(c, frequencies.getOrDefault(c, 0) + 1);
//        }
//
//        for (var e: frequencies.entrySet()) {
//            HuffmanNode newNode = new HuffmanNode(e.getKey(), e.getValue());
//            minHeap.add(newNode);
//        }
//
//        while (minHeap.size() >= 2) {
//            HuffmanNode p1 = minHeap.poll();
//            HuffmanNode p2 = minHeap.poll();
//
//            HuffmanNode newNode = new HuffmanNode(HuffmanNode.NOVAL, p1.frequency + p2.frequency, p1, p2);
//            minHeap.add(newNode);
//        }
//
//        HuffmanNode root = minHeap.poll();
//
//        HashMap<Character, String> map =  root.generateCodes();
//        int compressedSize = 0;
//
//        for (var e: map.entrySet()) {
//            compressedSize += frequencies.get(e.getKey()) * e.getValue().length();
//        }
//
//        double compressionRatio = (double) compressedSize / (text.length() * 8);
//        System.out.println("Uncompressed size: " + text.length() * 8);
//        System.out.println("Compressed size: " + compressedSize);
//        System.out.println("Compression Ratio: " + compressionRatio);
//
//        StringBuilder compressedText = new StringBuilder();
//        for(int i = 0; i < text.length(); i++) {
//            compressedText.append(map.get(text.charAt(i)));
//        }
//
//        System.out.println("Compressed text: " + compressedText);
//        System.out.println("Compressed text size: " + compressedText.length());
//
//        System.out.println("Uncompressed text: " + uncompressed(compressedText.toString(), root));

    }

    public static String uncompressed(String compressedText, HuffmanNode node) {
        StringBuilder output = new StringBuilder();
        HuffmanNode savedNode = node;

        for(int i = 0; i < compressedText.length(); i++) {

            if (compressedText.charAt(i) == '1') {
                node = node.right;
            }
            else {
                node = node.left;
            }

            if (node.value != HuffmanNode.NO_VAL) {
                output.append(node.value);
                node = savedNode; // reset node to root
            }

        }
        return output.toString();
    }

}