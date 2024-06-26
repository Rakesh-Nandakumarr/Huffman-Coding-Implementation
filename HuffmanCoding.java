public class HuffmanCoding {
    private HuffmanNode root;
    private CustomHashMap<Character, String> huffmanCodeMap;

    public HuffmanCoding(CustomHashMap<Character, Integer> frequencyMap) {
        buildHuffmanTree(frequencyMap);
        huffmanCodeMap = new CustomHashMap<>(frequencyMap.size());
        generateCodes(root, "");
    }

    private void buildHuffmanTree(CustomHashMap<Character, Integer> frequencyMap) {
        PriorityQueue queue = new PriorityQueue(frequencyMap.size());

        CustomLinkedList<Character> keys = frequencyMap.keySet();
        for (int i = 0; i < keys.size(); i++) {
            Character key = keys.get(i);
            queue.enqueue(new HuffmanNode(key, frequencyMap.get(key)));
        }

        while (queue.size() > 1) {
            HuffmanNode left = queue.dequeue();
            HuffmanNode right = queue.dequeue();
            int combinedFrequency = left.frequency + right.frequency;
            HuffmanNode internalNode = new HuffmanNode(combinedFrequency, left, right);
            queue.enqueue(internalNode);
        }

        root = queue.dequeue();
    }

    private void generateCodes(HuffmanNode node, String code) {
        if (node == null) {
            return;
        }

        if (node.symbol != '$') { // '$' denotes an internal node
            huffmanCodeMap.put(node.symbol, code);
        }

        generateCodes(node.left, code + '0');
        generateCodes(node.right, code + '1');
    }

    public String encode(String message) {
        StringBuilder encodedMessage = new StringBuilder();
        for (char c : message.toCharArray()) {
            encodedMessage.append(huffmanCodeMap.get(c));
        }
        return encodedMessage.toString();
    }

    public void printCodes() {
        CustomLinkedList<Character> keys = huffmanCodeMap.keySet();
        for (int i = 0; i < keys.size(); i++) {
            Character key = keys.get(i);
            System.out.println(key + ": " + huffmanCodeMap.get(key));
        }
    }

    public static void main(String[] args) {
        CustomHashMap<Character, Integer> frequencyMap = new CustomHashMap<>(256);
        String message = "hello world!";

        for (char c : message.toCharArray()) {
            if (frequencyMap.containsKey(c)) {
                frequencyMap.put(c, frequencyMap.get(c) + 1);
            } else {
                frequencyMap.put(c, 1);
            }
        }

        HuffmanCoding huffmanCoding = new HuffmanCoding(frequencyMap);
        huffmanCoding.printCodes();

        String encodedMessage = huffmanCoding.encode(message);
        System.out.println("Encoded Message: " + encodedMessage);
    }
}
