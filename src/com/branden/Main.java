package com.branden;
import java.util.*;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        // instantiate a new reader with the text from the report
        BufferedReader bReader = new BufferedReader(new FileReader("recyclingreport-mainstreet.txt"));

        // write new report
        BufferedWriter bWriter = new BufferedWriter(new FileWriter("Report.txt"));

        // read in a line from the report
        String line = bReader.readLine();

        // create a list to store the houses in
        ArrayList houseList = new ArrayList();

        // loop through the file and check each line for the number of crates
        // by splitting the line on the spaces
        // the crates should be in the 3rd index of the array created by line.split()
        while(line != null){
            houseList.add( Integer.parseInt( line.split(" ")[3] ) );

            // read the next line
            line = bReader.readLine();
        }

        // create a new array to hold the number of crates
        int houses[] = new int[ houseList.size() ];


        // try + catch any index errors
        try {

            for ( int i = 0; i < houseList.size();i++) {

                // cast crate strings to integers
                // store them in houses array.
                // the houses array is what the rest of the program works
                // houseList wont work in rest of program

                houses[i] = (Integer) houseList.get(i);
            }

        } catch ( IndexOutOfBoundsException err){
           System.out.println("You tried to reach a not existing element " + err);
        }

        // variable something to store the results
        int[] results;
        // stats() returns:

        // stats[0] = highest;
        // stats[1] = lowest;
        // stats[2] = houseNumber;
        // stats[3] = totalCrates;

        results = stats(houses);
        bWriter.write( "\nTotal crates recycled = " + results[3] + "\n\n");

        //Write the first line to the file
        bWriter.write("Houses that recycled the most\n");

        // get the the most crates
        // returns a hash map with the integer <houseNUmber><numberOfCrates>
        HashMap highestHouses = getMostCrates(houses);
        for ( Object ob : highestHouses.keySet() ){
            // loop through hash map and print to file
            bWriter.write("House "+ ob +" recycled "+   highestHouses.get(ob) +" crates" + "\n");
        }
        bWriter.close();
    }

    // Get the most crates by storing the house with the most crates,
    // then creating a hash map with all houses that match the highest number of crates
    public static HashMap getMostCrates( int[] houses ){
        int crateCount = 0;

        HashMap<Integer, Integer> houseNumbers = new HashMap();
        for (int i = 0; i < houses.length; i++) {
            if ( houses[i] >  crateCount ){
                crateCount = houses[i];
            }
        }
        for (int i = 0; i < houses.length; i++) {
            if ( houses[i] ==  crateCount ){
                houseNumbers.put(i, crateCount);
            }
        }
        return houseNumbers;

    }

    // store the highest number of crates, lowest number of crates,
    // the house number with the highest count and the total crates
    public static int[] stats( int[] houses ){
        int highest = houses[0];
        int lowest = houses[0]; // initilize with first house
        int houseNumber = 0;
        int[] stats = new int[4];
        int totalCrates = 0;

        // stats[0] = highest;
        // stats[1] = lowest;
        // stats[2] = houseNumber;
        // stats[3] = totalCrates;

        for (int i = 0; i < houses.length; i++) {
            totalCrates += houses[i];

            if (  houses[i] >= highest ){
                highest = houses[i];
                houseNumber = i;

            }
            if (  houses[i] <= lowest ){
                lowest = houses[i];
            }
        }
        stats[0] = highest;
        stats[1] = lowest;
        stats[2] = houseNumber;
        stats[3] = totalCrates;

        return stats;

    }
}
