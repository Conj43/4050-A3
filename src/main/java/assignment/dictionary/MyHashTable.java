package assignment.dictionary;

/*

 */

//

import java.util.Iterator;

/**

 */


public class MyHashTable<K,V>


{
    private static final int DEFAULT_CAPACITY = 10;
    private static final int MAX_CAPACITY = 10000;
    private AList<Entry<K, V>>[] buckets;
    private int size;


    public MyHashTable(){
        this(DEFAULT_CAPACITY);
    }

    public MyHashTable(int initial_capacity){
        buckets = new AList[initial_capacity];
        for(int i = 0; i < initial_capacity; i++){
            buckets[i] = new AList<>();
        }
        size = 0;
    }


    public V put(K key, V value){
        int index = hash(key);
        AList<Entry<K, V>> bucket = buckets[index];

        for(Entry<K, V> entry : bucket){
            if(entry.getKey().equals(key)){
                V old = entry.getValue();
                entry.setValue(value);
                return old;
            }
        }
        bucket.add(new Entry<K, V>(key, value));
        size++;
        return null;
    }


    public V get(K key){
        int index = hash(key);

        for(Entry<K, V> entry : buckets[index]){
            if(entry.getKey().equals(key)){
                return entry.getValue();
            }
        }
        return null;
    }

    private int hash(K key){
        int hashCode = key.hashCode();
        int index = hashCode % buckets.length;
        return index >= 0 ? index : index + buckets.length;
    }



    public V remove(K key) {
        int index = hash(key);
        AList<Entry<K, V>> bucket = buckets[index];

        for (int i = 1; i <= bucket.getLength(); i++) {
            Entry<K, V> entry = bucket.getEntry(i);
            if (entry.getKey().equals(key)) {
                V value = entry.getValue();
                bucket.remove(i);
                size--;
                return value;
            }
        }
        return null;
    }



    public V getValue(K key){
        int index = hash(key);
        AList<Entry<K,V>> bucket = buckets[index];

        for(Entry<K, V> entry : bucket){
            if(entry.getKey().equals(key)){
                return entry.getValue();
            }
        }
        return null;
    }

    public boolean containsKey(K key){

        int index = hash(key);
        AList<Entry<K,V>> bucket = buckets[index];

        for(Entry<K, V> entry : bucket){
            if(entry.getKey().equals(key)){
                return true;
            }
        }
        return false;
    }

    public void clear() {
        for (AList<Entry<K, V>> bucket : buckets) {
            bucket.clear();
        }
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Iterable<K> keySet() {

    }

    public Iterable<V> values() {

    }

    public int getSize(){
        return size;
    }


    // You need to implement this class without using the
   // Hashtable class from Java (“java.util.Hashtable<K,V>”).



}





