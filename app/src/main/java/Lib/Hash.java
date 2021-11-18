package Lib;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Models.Contact;
/**
 * Hash Class, used for building the hash table that so the navigation can be achieved
 */
public class Hash {
    private ArrayList<Contact> hashTable[];

    /** Constructor, constructs a arraylist with 27 slots */
    public Hash() {

        hashTable = new ArrayList[27];
        for(int i = 0; i < hashTable.length; i++){
            hashTable[i] = new ArrayList<Contact>();
        }
    }

    /**
     * returns the arrayList based on bool value passed in to determine its reversed or not
     * @param: boolean reverse
     * @returns: ArrayList<Contact> c
     * */
    public ArrayList<Contact> toList(boolean reverse) {
        ArrayList<Contact> c = new ArrayList ();
        for(int i = 0; i < hashTable.length; i++){
            for(int j = 0; j < hashTable[i].size(); j++){
                c.add(hashTable[i].get(j));
            }
        }
        if(reverse == true) {
            Collections.reverse(c);
        }
        return c;
    }

    /** calculates the amount of scrolling needed for the key
     * @param k
     * @return int offset
     */
    public int calcOffsetByKey(int k) {
        int offset = 0;
        if (k < 0 || k >= hashTable.length) {
            offset = 0;
        } else {
            for(int i = 0; i < k; i++){
                offset = offset + hashTable[i].size();
            }
        }
        return offset;
    }

    /**
     * builds a hashtable from the list passed in
     * @param: List<Contact> list
     * @returns: void
     * */
    public void buildHashTable(List<Contact> list) {
        if(list == null) {
            return;
        }
        for(int i = 0; i < list.size(); i++){
            Contact c = list.get(i);
            int hashTableIndex = hash(c.getFirstName());
            hashTable[hashTableIndex].add(c);
        }

        for(int i = 0; i < hashTable.length; i++){
            Collections.sort(hashTable[i]);
        }
        return;
    }

    /**
     * returns the ascii value of the first letter of a string
     * @param: String s
     * @returns: int asciiValue
     * */
    private int hash(String s) {
        char c = s.toUpperCase().charAt(0);
        int asciiValue = (int)c;
        if(asciiValue >= 65 && asciiValue <= 90) {
            asciiValue = asciiValue - 64;
        } else {
            asciiValue = 0;
        }
        return asciiValue;
    }
}
