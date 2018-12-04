package com.company;

import java.util.Random;

public class Row {

    // Initialize the length of the second attribute (the 1000 char string)
    public final static int SecondAttributeLength = 1000;

    // Initialize your three attributes
    private int firstAttribute;
    private char[] secondAttribute;
    private long ID;

    // Create the Row to be used in other classes
    public Row(int firstAttribute, char[] secondAttribute, long ID){
        this.firstAttribute = firstAttribute;
        this.ID = ID;

        this.secondAttribute = new char[SecondAttributeLength];
        for(int i = 0; i < secondAttribute.length; i++){
            this.secondAttribute[i] = secondAttribute[i];
        }
    }

    // Create a getter for the first attribute (an int)
    public int getFirstAttribute() {
        return firstAttribute;
    }

    // Create a getter for the second attribute (a 1000 char string)
    public char[] getSecondAttribute() {
        return secondAttribute;
    }

    // Create a getter for the third attribute (a unique 64 bit long) which we have called ID
    public long getID() {
        return ID;
    }
}
