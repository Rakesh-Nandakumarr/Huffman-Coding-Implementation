public class PriorityQueue {
    private HuffmanNode[] heap;
    private int size;

    public PriorityQueue(int capacity) {
        heap = new HuffmanNode[capacity];
        size = 0;
    }

    public void enqueue(HuffmanNode node) {
        heap[size] = node;
        size++;
        int current = size - 1;

        while (current > 0 && heap[current].frequency < heap[(current - 1) / 2].frequency) {
            swap(current, (current - 1) / 2);
            current = (current - 1) / 2;
        }
    }

    public HuffmanNode dequeue() {
        if (size == 0) {
            return null;
        }

        HuffmanNode root = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapify(0);

        return root;
    }

    public int size() {
        return size;
    }

    private void heapify(int pos) {
        int smallest = pos;
        int leftChild = 2 * pos + 1;
        int rightChild = 2 * pos + 2;

        if (leftChild < size && heap[leftChild].frequency < heap[smallest].frequency) {
            smallest = leftChild;
        }

        if (rightChild < size && heap[rightChild].frequency < heap[smallest].frequency) {
            smallest = rightChild;
        }

        if (smallest != pos) {
            swap(pos, smallest);
            heapify(smallest);
        }
    }

    private void swap(int index1, int index2) {
        HuffmanNode temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }
}
