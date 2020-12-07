public class LRUCache {
    private static int SIZE;

    private static final int DEFAULT_SIZE = 2;

    private Entry head;

    private Entry tail;

    private int length;

    public LRUCache(int size) {
        if (size < 0) {
            size = DEFAULT_SIZE;
        }
        SIZE = size;
        head = tail = null;
        length = 0;
    }

    public LRUCache() {
        SIZE = DEFAULT_SIZE;
        length = 0;
        head = tail = null;
    }

    public void put(int key, int value) {
        Entry entry = new Entry(key, value);
        if (length < SIZE) {
            if (head == null) {
                head = entry;
                tail = entry;
                entry.next = null;
                entry.per = null;
            } else {
                Entry temp = head;
                head = entry;
                entry.next = temp;
                entry.per = null;
                temp.per = head;
            }
            length++;
        } else {
            Entry temp = head;
            head = entry;
            entry.next = temp;
            entry.per = null;
            temp.per = head;
            tail.per.next = null;
            tail.per = null;
        }
    }

    public Integer get(Integer key) {
        Entry entry = head;
        if (head == null) {
            return null;
        }
        while (entry != null) {
            if (entry.key.equals(key)) {
                Entry entry1 = entry.next;
                Entry entry2 = entry.per;
                if (entry2 == null) {
                    return entry.value;
                }
                // zuihouyige
                if (entry1 == null) {
                    tail = entry2;
                    Entry temp = head;
                    head = entry;
                    entry.next = temp;
                    temp.per = entry;
                    entry2.next = null;
                } else {
                    Entry temp = head;
                    head = entry;
                    entry.next = temp;
                    temp.per = entry;
                    entry2.next = entry1;
                    entry1.per = entry2;
                }
                return entry.value;
            }
            entry = entry.next;
        }
        return null;
    }



    class Entry {
        private Integer key;
        private Integer value;
        private Entry next;
        private Entry per;

        public Entry(Integer key, Integer value) {
            this.key = key;
            this.value = value;
        }
    }
}

class Test {
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);

        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));
        cache.put(3, 3);
        System.out.println(cache.get(2));
        cache.put(4, 4);
        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));

    }
}
