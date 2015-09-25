
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/*
 KET1 TASK2 Specs: OverView Application
 * 
 *  1.  Prompt the user for the name of the input file (listings.txt).

 2.  Open the listings.txt file and read in property listing information using a buffered FileReader.

 3.  Count the total number of property listings for sale.

 •  Use buffered FileWriter to write the count of the number of property listings to your overview.txt file.

 4.  Calculate the total value of property for sale.

 •  Use a buffered FileWriter to write the total value of properties currently for sale.

 5.  Store each property id into an ArrayList.

 a.  Sort the ArrayList of property ids using natural ordering.

 b.  Use a for-each loop to iterate through the sorted ArrayList and write property ids to overview.txt file using buffered FileWriter.

 6.  Use buffered FileWriter to write the total value of the properties listed and the total number of properties currently for sale.
 */
/**
 *
 * @author Ryan Crossan; Student ID: 000225067
 */
public class agentOverviewClass {

    public static void main(String[] args) {
        try {
            //This will prompt end user for file name to import and read
            System.out.print("Enter the file name : ");
            Scanner input = new Scanner(System.in);

            //This opens a file and reads in listings.txt data
            File file = new File(input.next());

            //Create variables for storing data
            int propertyID;
            String type;
            double price;
            int agentID;

            double totalValueOfProperties = 0; 
            int numberOfProperties = 0; 

            //Create array list to hold data
            ArrayList<Integer> propertyIDs = new ArrayList<>();

            Scanner scanner = new Scanner(file);
            try (BufferedReader in = new BufferedReader(new FileReader(file))) {
                String line = null;
                while ((line = in.readLine()) != null) {

                    //This parses and extracts data read in previously by BufferedReader
                    scanner = new Scanner(line);
                    propertyID = scanner.nextInt();
                    type = scanner.next();
                    price = scanner.nextDouble();
                    agentID = scanner.nextInt();

                    //The following will perform calculations by counting property
                    //listings and then determining the total value of property for sale
                    numberOfProperties++;
                    totalValueOfProperties = totalValueOfProperties + price;

                    //Property ID is added to array list
                    propertyIDs.add(propertyID);
                }
            }

            //Resort array list
            Collections.sort(propertyIDs);
            try (PrintWriter overviewWriter = new PrintWriter(new BufferedWriter(new FileWriter("overview.txt")))) {
                overviewWriter.println("Total properties listed: " + numberOfProperties);
                overviewWriter.println("Total value of properties listed: " + totalValueOfProperties);
                overviewWriter.println();
                //Print the above in the Output screen
                System.out.println("Total properties listed: " + numberOfProperties);
                System.out.println("Total value of properties listed: " + totalValueOfProperties);
                System.out.println();

                //The Property IDs are written to output file

                for (int i : propertyIDs) {
                    overviewWriter.println(i);
                    System.out.println(i);
                }
            }
        } catch (FileNotFoundException fnfex) {
            System.out.println("Incorrect file name, please try again.");
        } catch (IOException ioExep) {
            System.out.println("Incorrect file name, please try again.");
        }
    }
}
