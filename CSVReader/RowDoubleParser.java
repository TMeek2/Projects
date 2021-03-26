public class RowDoubleParser {

    private String[][] csv;

    public RowDoubleParser(String[][] csv) {
        this.csv = csv;
    }

    public double[] parseRow(int rowIndex) {
        double[] myValues = new double[this.csv[0].length];

        for (int col = 0; col < this.csv[0].length; ++col) {
            myValues[col] = Double.parseDouble(this.csv[rowIndex][col]);
        }

        return myValues;
    }

}
