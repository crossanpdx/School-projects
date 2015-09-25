import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
/*
 * KET1 Task2 Specs:
 *  1.  Prompt the user for the name of the input file (listings.txt).

2.  Open listings.txt file and read in property listings.

3.  Store each property type into a Set.

a.  Convert property type to upper case before adding to your Set using method(s) from String class.

b.  Sort your Set of property types alphabetically.

4.  Use a Map to calculate total property listed in dollars and cents for each agent id.
 

Note: Agent id would be the key, and accumulated total of property listed would be the value.
 

•  Sort your Map by agent id.

•  Create an agentreport.txt file.

5.  Use an Iterator to iterate through your Set and write your sorted set of property types sold by the agents to the agentreport.txt file.

6.  Iterate through your Map to write your sorted pair of agent id and total property listed to the agentreport.txt file. 
 */

/**
 *
 * @author Ryan Crossan, Student ID: 000225067
 */
public class agentReporter {
    
    public static void main(String[] args){      
      try{
            //TreeSet util is created to store and sort property types
            Set propertyTypes = new TreeSet();
            //TreeMap util is created to store and sort the price list
            TreeMap propertyPriceList = new TreeMap();

            //This will prompt end user for file name to import and read
            System.out.print("Enter the file name: ");
            Scanner input = new Scanner(System.in);

            //This opens a file and reads in listings.txt data
            File file = new File(input.next());

            //Create variables for storing data
            int propertyID;
            String type;
            double price;
            int agentID;
            
            //Variable stores the value of any property sharing matching agent ids
            double totalValue;

            //scanner reads file
            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                propertyID = scanner.nextInt();
                type = scanner.next();
                price = scanner.nextDouble();
                agentID = scanner.nextInt();

                //Property types are put into a set and converted to upper case
                propertyTypes.add(type.toUpperCase());
                totalValue = 0;
                Object objVal;

                //Total properties are calculated and validates if matching agent id is present
                if( (objVal = propertyPriceList.get( new Integer(agentID) ) ) !=null) {
                    totalValue = ((Double)objVal ).doubleValue() + price;
                }
                else {
                    totalValue = price;
                }
                propertyPriceList.put( new Integer(agentID), new Double(totalValue));
            }
            try (PrintWriter agentReportWriter = new PrintWriter("agentreport.txt")) {
                Iterator propertyListIterator = propertyTypes.iterator();
                 while (propertyListIterator.hasNext()) {
                     String propertyType = (String)propertyListIterator.next();
                     agentReportWriter.println(propertyType); 
                     System.out.println(propertyType); 
                }
                agentReportWriter.println();   
                System.out.println();   
                
                //The Iterate util is used to map and write data to the output file.
                Set keys = propertyPriceList.keySet();  
                Iterator propertyPriceListIterator = keys.iterator();
                //Write agent id and total property value into the file
                 while (propertyPriceListIterator.hasNext()) {
                     Integer singleEntry = (Integer)propertyPriceListIterator.next();
                     //Grabs the property value to the agent id and writes to output file
                     agentReportWriter.println(singleEntry+" "+ propertyPriceList.get(singleEntry));
                     System.out.println(singleEntry+" "+ propertyPriceList.get(singleEntry)); 
                }
            }
      }
      catch (FileNotFoundException fnfex){
          System.out.println("Incorrect file name, please try again.");
      }
    }
}
