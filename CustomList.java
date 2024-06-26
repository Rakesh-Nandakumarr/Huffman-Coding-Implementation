public interface CustomList<E> {
    void add(E element);
    E get(int index);
    int size();
    boolean contains(E element);
    E remove(int index);
}
