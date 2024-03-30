package other.pojo;

/**
 * 键值对
 */
@SuppressWarnings("all")
public class Pair<K, V> {

    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pair(key = ").append(key).append(", ");
        sb.append("value = ").append(value).append(")");
        return sb.toString();
    }
}
