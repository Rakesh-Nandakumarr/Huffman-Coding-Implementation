public class HuffmanNode {
    char symbol;
    int frequency;
    HuffmanNode left;
    HuffmanNode right;

    public HuffmanNode(char symbol, int frequency) {
        this.symbol = symbol;
        this.frequency = frequency;
        this.left = null;
        this.right = null;
    }

    public HuffmanNode(int frequency, HuffmanNode left, HuffmanNode right) {
        this.symbol = '$'; // '$' denotes an internal node
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }
}
