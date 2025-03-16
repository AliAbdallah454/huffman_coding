import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class HuffmanCoding {

    private String textFile = null;
    private String huffmanTreeFile = null;
    private String text;
    private HuffmanNode huffmanNode;

    public HuffmanCoding(String textFile) {
        this.textFile = textFile;
    }

    public HuffmanCoding(String textFile, String huffmanTreeFile) {
        this.textFile = textFile;
        this.huffmanTreeFile = huffmanTreeFile;
    }

    public String compress() {
        if (textFile == null) {
            System.out.println("Text file must be provided");
        }

        String text = readFile();
        System.out.println(text);

        HashMap<Character, Integer> frequencies = new HashMap<>();
        PriorityQueue<HuffmanNode> minHeap = new PriorityQueue<>(new Comparator<HuffmanNode>() {
            @Override
            public int compare(HuffmanNode n1, HuffmanNode n2) {
                return Integer.compare(n1.frequency, n2.frequency);
            }
        });

        for(int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            frequencies.put(c, frequencies.getOrDefault(c, 0) + 1);
        }

        for (var e: frequencies.entrySet()) {
            HuffmanNode newNode = new HuffmanNode(e.getKey(), e.getValue());
            minHeap.add(newNode);
        }

        while (minHeap.size() >= 2) {
            HuffmanNode p1 = minHeap.poll();
            HuffmanNode p2 = minHeap.poll();

            HuffmanNode newNode = new HuffmanNode(HuffmanNode.NO_VAL, p1.frequency + p2.frequency, p1, p2);
            minHeap.add(newNode);
        }

        HuffmanNode root = minHeap.poll();

        HashMap<Character, String> map =  root.generateCodes();

        StringBuilder compressedText = new StringBuilder();
        for(int i = 0; i < text.length(); i++) {
            compressedText.append(map.get(text.charAt(i)));
        }

        System.out.println("Printing order");
        List<HuffmanNode> list = root.levelOrder();
        System.out.println("printing frequencies");

        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i).frequency + ", ");
        }
        System.out.println();

        String compressedTextString = compressedText.toString();
        writeToFile(compressedTextString);
        return compressedTextString;

    }

    public static void writeToFile(String text) {
        try (FileWriter writer = new FileWriter("./texts/compressed.txt")) {
            writer.write(text);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readFile() {
        StringBuilder text = new StringBuilder();
        try (Scanner scanner = new Scanner(new File("./texts/test1.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                text.append(line);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return text.toString();
    }

}