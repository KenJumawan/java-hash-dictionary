package edu.commonwealthu.mrodriguez.cmsc230.hashlab;

import java.util.LinkedList;
import java.util.HashSet;


public class Dictionary {

    // bucket array for the hash table
    private LinkedList<Word>[] buckets;

    // number of buckets total
    private int tableSize;



    public Dictionary() {
        // Create a new Dictionary
        this(50000); // default
    }


    public Dictionary(int tableSize) {
        // Create a new Dictionary with the provided custom size.
        if (tableSize <= 0) {
            tableSize = 1;
        }

        this.tableSize = tableSize;

        // buckets start as null
        this.buckets = new LinkedList[tableSize];
    }

    public int getBucketFor(Word w) {
        // Gets the appropriate bucket index for a given String.
        if (w == null) {
            return 0;
        }
        return getBucketFor(w.getWord());
    }

    public int getBucketFor(String word) {
        if (word == null) {
            return 0;
        }

        int rawHash = word.hashCode();

        // Treat rawHash as unsigned 32-bit
        long unsigned = ((long) rawHash) & 0xFFFFFFFFL;

        // Fit into table range
        int index = (int) (unsigned % tableSize);

        return index;
    }


    public boolean add(String word, String definition) {
        // Add a given word and its definition to the dictionary.
        Word entry = new Word(word, definition);

        int bucketIndex = getBucketFor(entry);

        // if this bucket hasn't been used yet, make a list now
        if (buckets[bucketIndex] == null) {
            buckets[bucketIndex] = new LinkedList<>();
        }

        // check if this exact word + definition pair already exists
        for (Word w : buckets[bucketIndex]) {
            if (w.getWord() != null && w.getWord().equals(word)) {
                // same word text
                String def = w.getDefinition();
                if (def == null && definition == null) {
                    // same word and both definitions null, duplicate
                    return false;
                }
                if (def != null && def.equals(definition)) {
                    // same word AND same definition, duplicate
                    return false;
                }
            }
        }

        // not a duplicate pair
        // append at the end to preserve insertion order
        buckets[bucketIndex].add(entry);

        return true;
    }


    public String lookFor(String word) {
        int bucketIndex = getBucketFor(word);

        LinkedList<Word> bucket = buckets[bucketIndex];
        if (bucket == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        int counter = 1;

        // track which definitions we've already output for THIS word only
        // goal: if the same definition string is added twice for the same word,
        // only list it once in the results.
        HashSet<String> seenDefs = new HashSet<>();

        for (Word w : bucket) {
            // only care about entries where the stored word text matches exactly
            if (w.getWord() != null && w.getWord().equals(word)) {
                String def = w.getDefinition();

                // only add if it's not a duplicate definition we already printed
                if (!seenDefs.contains(def)) {
                    sb.append(counter)
                            .append(". ")
                            .append(def)
                            .append(System.lineSeparator());
                    counter++;

                    // remember that we printed this exact definition text already
                    seenDefs.add(def);
                }
            }
        }

        // if none matched, sb will be empty, then return
        return sb.toString();
    }
}
