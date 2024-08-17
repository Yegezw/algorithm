package stage3.week13.hash;

import java.util.TreeMap;

/**
 * <p>哈希表, key 需要实现 hashCode()
 * <p>分块分组思想, 哈希值相等的 key 放在一组中
 * <p>容忍度 Tolerance degree
 */
@SuppressWarnings("all")
public class HashTable<K, V>
{

    /**
     * 最大容忍度 Upper Tolerance degree
     */
    private static final int upperTol = 10;
    /**
     * 最小容忍度 Lower Tolerance degree
     */
    private static final int lowerTol = 2;

    private final int[] capacity      = {
            53, 97, 193, 389, 769, 1543, 3079, 6151, 12289, 24593, 49157,
            98317, 196613, 393241, 786433, 1572869, 3145739, 6291469, 12582917,
            25165843, 50331653, 100663319, 201326611, 402653189, 805306457, 1610612741
    };
    private       int   capacityIndex = 0;

    private TreeMap<K, V>[] hashTable;
    private int             M; // M 是 hashTable 的长度, 它是一个素数
    private int             size;

    public HashTable()
    {
        this.M    = capacity[capacityIndex];
        size      = 0;
        hashTable = new TreeMap[M];
        for (int i = 0; i < M; i++) hashTable[i] = new TreeMap<>();
    }

    private int hash(K key)
    {
        return (key.hashCode() & 0x7FFFFFFF) % M;
    }

    public int getSize()
    {
        return size;
    }

    /**
     * 添加
     */
    public void add(K key, V value)
    {
        TreeMap<K, V> map = hashTable[hash(key)];

        if (map.containsKey(key)) map.put(key, value);
        else
        {
            map.put(key, value);
            size++;

            if (size >= upperTol * M && capacityIndex + 1 < capacity.length) resize(capacity[++capacityIndex]);
        }
    }

    /**
     * 删除
     */
    public V remove(K key)
    {
        TreeMap<K, V> map = hashTable[hash(key)];

        V ret = null;
        if (map.containsKey(key))
        {
            ret = map.remove(key);
            size--;

            if (size < lowerTol * M && capacityIndex - 1 >= 0) resize(capacity[--capacityIndex]);
        }

        return ret;
    }

    /**
     * 修改
     */
    public void set(K key, V newValue)
    {
        TreeMap<K, V> map = hashTable[hash(key)];

        if (!map.containsKey(key)) throw new IllegalArgumentException(key + "doesn't exist!");
        map.put(key, newValue);
    }

    /**
     * 查看
     */
    public boolean contains(K key)
    {
        TreeMap<K, V> map = hashTable[hash(key)];
        return map.containsKey(key);
    }

    /**
     * 查看
     */
    public V get(K key)
    {
        return hashTable[hash(key)].get(key);
    }

    /**
     * 动态的扩容或缩容
     */
    private void resize(int newM)
    {
        TreeMap<K, V>[] newHashTable = new TreeMap[newM];
        for (int i = 0; i < newM; i++) newHashTable[i] = new TreeMap<>();

        int oldM = M;
        this.M = newM;

        for (int i = 0; i < oldM; i++)
        {
            TreeMap<K, V> map = hashTable[i];
            map.forEach(
                    (key, value) ->
                    {
                        newHashTable[hash(key)].put(key, value);
                    }
            );
        }

        hashTable = newHashTable;
    }
}
