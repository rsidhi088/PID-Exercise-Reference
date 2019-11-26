package exercises;

class Entry {
    Object key;
    Object value;
    Entry next;
    Entry(Object key, Object value, Entry next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }
}

public class Hashtable {
    private static final int SIZE = 1024;
    private Entry[] table = new Entry[SIZE];

    public void insert(Object key, Object value) {
        if (key == null) {
            throw new IllegalArgumentException("null key not permitted");
        }
        int hc = key.hashCode();
        int index = hc % SIZE;
        // now insert a new Entry object into
        // the "table" array
        Entry e = new Entry(key, value, null);
        // best way: handle two cases separately
        if (table[index] == null) {
            // have not inserted anything yet
            // at that index!
            // all we need to do:
            // create a chain with a single Entry
            // object containing our key and value.
            table[index] = e;
        }
    }

    /**
     * Looks up the value associated with the given key,
     * or `null` if the key cannot be found.
     *
     * @param key The key to look up.
     * @return The value associated with the given key, or `null` if the key does not exist.
     */
    public Object lookup(Object key) {
        if (key == null) {
            throw new IllegalArgumentException("null key not permitted");
        }
        int hc = key.hashCode();
        int index = hc % SIZE;
        Entry e = table[index];
        if (e == null) {
            return null;
        } else {
            Entry current = e;
            while (current.key != key && current.next != null) {
                current = current.next;
            }
            if (current.key == key) {
                return current.value;
            } else {
                // not found but `current` is last entry
                return null;
            }
        }
    }
}
