// Tim Meek II
//
// This application reads a CSV text file and creats a 2D array of the components within the file. Then commands within the text file
// are processed and the answers to these commands are printed.
// 
// Last updated: 12/4/2020

import java.util.Scanner;
import java.io.File;

public class CSVReader {
    public static void main(String[] args) throws Exception {

        Scanner s = new Scanner(new File("CSVInput.txt")); // Read the file


        // Step 1: Create your CSV 2D String Array using data from section 1 of text file

        String[] csvSize = s.nextLine().split("x");
        // Get the number of rows and columns from the above variable
        // Use those variables to create the 2D String array for your CSV data. 

        int ROWS = Integer.parseInt(csvSize[0]); // convert string to integer
        int COLUMNS = Integer.parseInt(csvSize[1]);
        int rowCount = 0; // keep count of row of the array you are on

        String[][] myCSV = new String[ROWS][COLUMNS]; // create 2D array

        boolean endOfCSV = false; // variable determines when to stop parsing the the CSV table

        while (s.hasNextLine()) { // Read each line of the file
            String line = s.nextLine(); // Read the next line from file
            
            if (line.contains("---")) // tests to make sure we haven't reached the end of the CSV table
                endOfCSV = true;

            while (endOfCSV == false) { // until the "---" is reached, the program continues to create the table
                for (int colCount = 0; colCount < COLUMNS; colCount++)
                {
                    myCSV[rowCount][colCount] = line.split(",")[colCount]; // add each line as an array to the overall CSV array
                }
                rowCount++;  // increase row count by 1
                break; // breaks inner while loop and goes to outer while loop
            }

        // Step 2: Answer each question in section 2 of the text file

            // Finds the sum of all the values in a given column
            if (line.indexOf("SUM_COL:") >= 0) {
                String colName = line.split(":")[1];
                int colIndex = -1;

                // Step 1: Find the column index
                ColumnIndexFinder columnFinder = new ColumnIndexFinder(myCSV[0]);
                colIndex = columnFinder.findIndex(colName);
                double[] myValues = (new ColumnDoubleParse(myCSV)).parseColumn(colIndex);

                // Step 2: Build up an array of double values to send to my SumHelper object
                SumHelper sumHelper = new SumHelper(myValues);
                System.out.println(sumHelper.compute());
            }
            
            else if (line.indexOf("MIN_COL:") >= 0) {
                String colName = line.split(":")[1];
                int colIndex = -1;

                // Step 1: Find the column index
                ColumnIndexFinder columnFinder = new ColumnIndexFinder(myCSV[0]);
                colIndex = columnFinder.findIndex(colName);

                // Step 2: Find the smallest value in that column's index
                double[] myValues = (new ColumnDoubleParse(myCSV)).parseColumn(colIndex);
                MinFinder minFinder = new MinFinder(myValues);
                System.out.println(minFinder.computeMin());
            }
            
            else if (line.indexOf("SUM_ROW:") >= 0) {
                String rowIndex = line.split(":")[1];
                double[] rowValues = (new RowDoubleParser(myCSV)).parseRow(Integer.parseInt(rowIndex) + 1);
                System.out.println((new SumHelper(rowValues)).compute());
            }

            else if (line.indexOf("DIVIDE_ROW:") >= 0) {
                String rowIndex = line.split(":")[1];
                RowDoubleParser doubleParser = new RowDoubleParser(myCSV);
                double[] rowValues = doubleParser.parseRow(Integer.parseInt(rowIndex) + 1);
                DivisionHelper divisionHelper = new DivisionHelper(rowValues);
                System.out.println(divisionHelper.computeRunningDivision());
            }

            else if (line.indexOf("MULT_ROW:") >= 0) {
                String rowIndex = line.split(":")[1];
                RowDoubleParser doubleParser = new RowDoubleParser(myCSV);
                double[] rowValues = doubleParser.parseRow(Integer.parseInt(rowIndex) + 1);
                MultiplicationHelper multiplicationHelper = new MultiplicationHelper(rowValues);
                System.out.println(multiplicationHelper.computeRunningProduct());
            }

            else if (line.indexOf("MIN_ROW:") >= 0) {
                String rowIndex = line.split(":")[1];
                double[] rowValues = (new RowDoubleParser(myCSV)).parseRow(Integer.parseInt(rowIndex) + 1);
                MinFinder minFinder = new MinFinder(rowValues);
                System.out.println(minFinder.computeMin());
            }

            else if (line.indexOf("MAX_ROW:") >= 0) {
                String rowIndex = line.split(":")[1];
                double[] rowValues = (new RowDoubleParser(myCSV)).parseRow(Integer.parseInt(rowIndex) + 1);
                MaxFinder maxFinder = new MaxFinder(rowValues);
                System.out.println(maxFinder.computeMax());
            }

            else if (line.indexOf("SUM_ODDS_IN_ROW:") >= 0) {
                String rowIndex = line.split(":")[1];
                double[] rowValues = (new RowDoubleParser(myCSV)).parseRow(Integer.parseInt(rowIndex) + 1);
                System.out.println((new SumOddsHelper(rowValues)).compute());
            }

            else if (line.indexOf("SUM_EVENS_IN_ROW:") >= 0) {
                String rowIndex = line.split(":")[1];
                double[] rowValues = (new RowDoubleParser(myCSV)).parseRow(Integer.parseInt(rowIndex) + 1);
                System.out.println((new SumEvensHelper(rowValues)).compute());
            }
        }
    }
}   