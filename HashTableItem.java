package com.company;

public class HashTableItem {

    // Initialize row
    private Row row;

    // Initialize the overflowTable
    private Row[] overflowTable;

    public HashTableItem(Row row){

        this.row = row;

        overflowTable = new Row[0];
    }

    public void insertIntoOverflowTable(Row row){
        Row[] temp = new Row[overflowTable.length+1];

        // Insert into the overflow table
        for(int i = 0; i < overflowTable.length; i++){
            temp[i] = overflowTable[i];
        }

        temp[temp.length - 1] = row;

        temp = overflowTable;
    }

    // Create a getter for row to be used later
    public Row getRow() {
        return row;
    }

    // Create a getter for overflowTable to be used later
    public Row[] getOverflowTable(){
        return overflowTable;
    }
}