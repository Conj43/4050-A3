package assignment.dictionary;

/*

 */

//

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**

 */


public class MyHashTable<K,V> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int MAX_CAPACITY = 10000;
    private AList<Entry<K, V>>[] buckets;
    private int size;


    public MyHashTable() {
        this(DEFAULT_CAPACITY);
    }



    @Override
    public String toString() {
        return "MyHashTable{" +
                "buckets=" + Arrays.toString(buckets) +
                ", size=" + size +
                '}';
    }


    public MyHashTable(int initial_capacity) {
        buckets = new AList[initial_capacity];
        for (int i = 0; i < initial_capacity; i++) {
            buckets[i] = new AList<>();
        }
        size = 0;
    }


    public V put(K key, V value) {
        int index = hash(key);
        AList<Entry<K, V>> bucket = buckets[index];

        for (Entry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                V old = entry.getValue();
                entry.setValue(value);
                return old;
            }
        }
        bucket.add(new Entry<K, V>(key, value));
        size++;
        return null;
    }


    public V get(K key) {
        int index = hash(key);

        for (Entry<K, V> entry : buckets[index]) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    private int hash(K key) {
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


    public V getValue(K key) {
        int index = hash(key);
        AList<Entry<K, V>> bucket = buckets[index];

        for (Entry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public boolean containsKey(K key) {

        int index = hash(key);
        AList<Entry<K, V>> bucket = buckets[index];

        for (Entry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
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
        return new Iterable<K>() {
            @Override
            public Iterator<K> iterator() {
                return new KeyIterator(); //returns an iterable object that provides access to the keys in the hash table
            }
        };
    }

    public Iterable<V> values() {
        return new Iterable<V>() {
            @Override
            public Iterator<V> iterator() {
                return new ValueIterator(); // returns an iterable object that provides access to the values
            }
        };
    }

    private class KeyIterator implements Iterator<K> {
        private int currentBucket; //the index of the current bucket
        private Iterator<Entry<K, V>> entryIterator; //iterator over the current entries in the bucket

        //constructor that inits the iterator
        public KeyIterator() {
            currentBucket = 0;
            entryIterator = buckets[currentBucket].iterator();
        }

        @Override // uses the iterator to check if there is a next element in the hash table
        public boolean hasNext() {
            if(currentBucket >= buckets.length) //if its at the last bucket it returns false
                return false;
            if (entryIterator.hasNext()) //if it has a next element return true
                return true;
            //checks if there are any non-empty buckets after the current one
            for (int i = currentBucket + 1; i < buckets.length; i++) {
                if (!buckets[i].isEmpty()) {
                    return true;
                }
            }
            return false;
        }

        //returns the next key in the iteration
        public K next() {
            //if the current bucket has more entries it returns the next key
            if (entryIterator.hasNext())
                return entryIterator.next().getKey();
            //moves to the next non-empty bucket
            while (currentBucket < buckets.length && buckets[currentBucket].isEmpty()) {
                currentBucket++;
            }
            //if that was the last bucket throw exception showing there is no such element
            if(currentBucket >= buckets.length)
            {
                throw new NoSuchElementException();
            }
            //update the entryIterator to iterate over the next non-empty bucket
            entryIterator = buckets[currentBucket].iterator();
            //returns the key of the first entry in the next non-empty bucket
            return entryIterator.next().getKey();
        }

    }

    private class ValueIterator implements Iterator<V>{
        private Iterator<Entry<K,V>> entryIterator = new EntryIterator();

        //checks if there are more values
        public boolean hasNext(){
            return entryIterator.hasNext();
        }
        //returns the next value
        public V next(){
            return entryIterator.next().getValue();
        }
    }

    public class EntryIterator implements Iterator<Entry<K,V>>{
        private int currentBucket;
        private Iterator<Entry<K,V>> entryIterator;

        //contructor to init the EntryIterator
        public EntryIterator(){
            currentBucket = 0;
            entryIterator = buckets[currentBucket].iterator();
        }

        //uses the iterator to check if there is a next element in the hash table
        public boolean hasNext(){
            //returns false if it is the last bucket
            if(currentBucket >= buckets.length)
                return false;
            //if there is a next bucket it returns true
            if(entryIterator.hasNext()){
                return true;
            }
            //checks if there are any non-empty buckets after
            for(int i = currentBucket + 1; i < buckets.length; i++){
                if(!buckets[i].isEmpty()){
                    return true;
                }
            }
            return false;
        }


        // returns the next Entry
        public Entry<K,V> next(){
            //if there is a next element return that one
            if(entryIterator.hasNext()){
                return entryIterator.next();
            }
            //moves to the next non empty bucket
            while(currentBucket < buckets.length && buckets[currentBucket].isEmpty())
            {
                currentBucket++;
            }
            //if there are no more entries throw an exception
            if(currentBucket >= buckets.length){
                throw new NoSuchElementException();
            }
            //set it up to interate over the next non empty entry
            entryIterator = buckets[currentBucket].iterator();
            //returns the next entry
            return entryIterator.next();
        }
    }
    public int getSize() {
        return size;
    }


    // You need to implement this class without using the
    // Hashtable class from Java (“java.util.Hashtable<K,V>”).


}





