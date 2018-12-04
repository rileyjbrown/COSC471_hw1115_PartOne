package com.company;

import java.util.Random;

public class JoinOperation {

    // Create a variable for tablesize
    private int tableSize;

    // Initialize tables R1, S1, and S2
    private Row[] tableR1;
    private Row[] tableS1;
    private HashTableItem[] tableS2;

    // Perform the join operation by inserting data into tables
    public JoinOperation(int tableSize){
        this.tableSize = tableSize;

        tableR1 = new Row[tableSize];
        tableS1 = new Row[tableSize];
        tableS2 = new HashTableItem[tableSize*2];

        for(long i = 0; i < tableSize; i++){
            insertIntoTableR1(i);
            insertIntoTableS1(i);
            insertIntoTableS2(i);

        }
    }

    // Create a method to insert data into table R1
    public void insertIntoTableR1(long insertLocation){

        long ID = insertLocation + 1;

        // Insert our attribute data into table R1
        tableR1[(int)insertLocation] = new Row(Utilities.createRandomInt(),
                Utilities.createRandomCharArray(Row.SecondAttributeLength), ID);
    }

    // Create a method to insert data into table S1
    public void insertIntoTableS1(long insertLocation){

        long ID = insertLocation + 1;

        // Insert attribute data into table S2
        tableS1[(int)insertLocation] = new Row(Utilities.createRandomInt(),
                Utilities.createRandomCharArray(Row.SecondAttributeLength), ID);

    }

    public void insertIntoTableS2(long insertLocation){

        long ID = insertLocation + 1;

        // Initialize the row to contain our attributes
        Row row = new Row(Utilities.createRandomInt(), Utilities.createRandomCharArray(Row.SecondAttributeLength), ID);

        // Set the index
        int index = new Integer((int)ID).hashCode()%tableSize;

        // If there is already data in table S2, insert data in the overflow table
        if(tableS2[index] != null){
            HashTableItem item = tableS2[index];
            item.insertIntoOverflowTable(row);
        }
        // If there is not already data in table S2, insert the data into table S2 at the index
        else{
            HashTableItem item = new HashTableItem(row);
            tableS2[index] = item;
        }
    }

    public JoinResult joinR1OnS1(){
        // Intialize hitCount and missCount to 0 to start
        int hitCount = 0;
        int missCount = 0;


        for(int i = 0; i < tableR1.length; i++){
            for(int j = 0; j < tableS1.length; j++){
                // If there is data at the index for tables S1 and S2, there is a hit
                if(tableR1[i].getID() == tableS1[j].getID()){
                    hitCount++;
                    break;
                }

                // If there is not data at the index of the tables, there is a miss
                else{
                    missCount++;
                }

            }
        }
        // Return the hitCount and missCount resulting from the join
        return new JoinResult(hitCount, missCount);
    }

    public JoinResult joinR1OnS2(){

        // Initialize the hitCount and missCount
        int hitCount = 0;
        int missCount = 0;

        for(int i = 0; i < tableR1.length; i++){

            /// Set the index for our join
            int index = new Integer((int)tableR1[i].getID()).hashCode()%tableSize;


            if(tableS2[index] != null){
                // Create an index for the hash table
                HashTableItem item = tableS2[index];

                // If there is information in both tables at the index, there is a hit
                if(item.getRow().getID() == tableR1[i].getID()){
                    hitCount++;
                }

                // If there is not data at the index of both tables, there is a miss
                else{
                    missCount++;
                    for(int j = 0; j < item.getOverflowTable().length; j++){
                        Row row = item.getOverflowTable()[j];
                        if(row.getID() == tableR1[i].getID()){
                            hitCount++;
                            break;
                        }
                        else{
                            missCount++;
                        }
                    }
                }
            }
            else{
                missCount++;
            }
        }

        // Return the hitCount and missCount of the join
        return new JoinResult(hitCount, missCount);
    }


}
