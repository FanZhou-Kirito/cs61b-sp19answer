/**
 * ArrayDeque
 * implemented in circular way
 * @author zangsy
 */

public class ArrayDeque<Item> {

    private Item[] items;
    private int nextFirst;
    private int nextLast;
    private int size;

    /**
     * Create an empty ArrayDeque.
     */
    public ArrayDeque() {
        // Java does not allow to create new generic array directly. So need cast.
        items = (Item[]) new Object[8];
        nextFirst = 0;
        nextLast = 1;
        size = 0;
    }

    /**
     * Return true if deque is full, false otherwise.
     */
    private boolean isFull() {
        return size == items.length;
    }

    /**
     * Whether to downsize the deque.
     */
    private boolean isSparse() {
        return items.length >= 16 && size < (items.length / 4);
    }

    /**
     * Add one circularly.
     */
    private int plusOne(int i) {
        return (i + 1) % items.length;
    }

    /**
     * Minus one circularly.
     */
    private int minusOne(int i) {
        return (i - 1) % items.length;
    }

    /**
     * Resize the deque.
     */
    private void resize(int capacity) {
        Item[] newDeque = (Item[]) new Object[capacity];
        int oldIndex = plusOne(nextFirst); // the index of the first item in original deque
        int newIndex = 0;
        for (int i = 0; i < size; i++) {
            newDeque[newIndex] = items[oldIndex];
            newIndex += 1;
            oldIndex = plusOne(oldIndex);
        }
        items = newDeque;
        nextFirst = capacity - 1; // since the new deque is starting from true 0 index.
        nextLast = size;

    }

    /**
     * Upsize the deque.
     */
    private void upSize() {
        resize(size * 2);
    }

    /**
     * Downsize the deque
     */
    private void downSize() {
        resize(items.length / 2);
    }

    /**
     * Return true if deque is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Return the number of items in the deque.
     */
    public int size() {
        return size;
    }

    /**
     * Print the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line.
     */
    public void printDeque() {
        for (int i = plusOne(nextFirst); i != nextLast; i = plusOne(i)) {
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }

    /**
     * Add an item of type Item to the front of the deque.
     */
    public void addFirst(Item x) {
        if (isFull()) {
            upSize();
        }
        items[nextFirst] = x;
        nextFirst = minusOne(nextFirst);
        size += 1;
    }

    /**
     * Add an item of type Item to the back of deque.
     */
    public void addLast(Item x) {
        if (isFull()) {
            upSize();
        }
        items[nextLast] = x;
        nextLast = plusOne(nextLast);
        size += 1;
    }

    /**
     * Remove and return the item at the front of the deque.
     * If no such item exist, return null.
     */
    public Item removeFirst() {
        if (isSparse()) {
            downSize();
        }
        nextFirst = plusOne(nextFirst);
        Item toRemove = items[nextFirst];
        items[nextFirst] = null;
        size -= 1;
        return toRemove;
    }

    /**
     * Remove and return the item at the back oc the deque.
     * If no such item exist, return null.
     */
    public Item removeLast() {
        if (isSparse()) {
            downSize();
        }
        nextLast = minusOne(nextLast);
        Item toRemove = items[nextLast];
        items[nextLast] = null;
        size -= 1;
        return toRemove;
    }

    /**
     * Get the item at the given index, where 0 is the front,
     * 1 is the next item, and so forth. If no such item exists,
     * returns null. Must not alter the deque.
     */
    public Item get(int index) {
        int start = plusOne(nextFirst);
        return items[(start + index) % items.length];
    }

    /**
     * Create a deep copy of other.
     */
    public ArrayDeque(ArrayDeque other) {
        items = (Item[]) new Object[other.size];
        nextFirst = other.nextFirst;
        nextLast = other.nextLast;
        size = other.size;

        System.arraycopy(other.items, plusOne(nextFirst), items, plusOne(nextFirst), other.size);
    }






























}
















