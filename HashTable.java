public class HashTable {
    private HashNode[] buckets;
    private int numberOfBuckets;
    private int size;
    public int c;

    HashTable() {
        this(10);
    }

    HashTable(int capacity) {
        this.numberOfBuckets = capacity;
        this.buckets = new HashNode[capacity];
        this.size = 0;
        this.c = capacity;
    }

    private class HashNode {
        private Integer key;
        private String value;
        private HashNode next;

        HashNode(Integer key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    int getBucketIndex(int key) {
        return key % numberOfBuckets;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void put(Integer key, String value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Key or value is null!!!");
        }
        int bucketIndex = getBucketIndex(key);
        HashNode head = buckets[bucketIndex];
        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }
        size++;
        head = buckets[bucketIndex];
        HashNode node = new HashNode(key, value);
        node.next = head;
        buckets[bucketIndex] = node;
    }

    public String get(Integer key) {
        if (key == null) {
            throw new IllegalArgumentException("Argument is null..");
        }
        int bucketIndex = getBucketIndex(key);
        HashNode head = buckets[bucketIndex];
        while (head != null) {
            if (head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
        }
        return "No key-value is found with the given key";
    }

    public String remove(Integer key) {
        if (key == null) {
            throw new IllegalArgumentException("Argument is null..");
        }
        int bucketIndex = getBucketIndex(key);
        HashNode head = buckets[bucketIndex];
        HashNode prev = null;
        while (head != null) {
            if (head.key.equals(key)) {
                break;
            }
            prev = head;
            head = head.next;
        }

        if (head == null) {
            return null;
        }
        --size;
        if (prev != null) {
            prev.next = head.next;
        } else {
            buckets[bucketIndex] = head.next;
        }
        return head.value;
    }

    public static void main(String[] args) {
        HashTable table = new HashTable(10);
        table.put(105, "Tom");
        table.put(21, "Jerry");
        table.put(221, "Sanal");
        table.put(321, "Akhin");
        table.put(421, "Vahid");
        System.out.println(table.size());
        System.out.println(table.get(105));
        System.out.println(table.remove(321));
        System.out.println(table.get(321));
        System.out.println(table.get(221));
        System.out.println(table.get(21));
        System.out.println(table.get(105));
    }
}