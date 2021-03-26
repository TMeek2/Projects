public class ColumnDoubleParse {

    private String[][] csv;

    public ColumnDoubleParse(String[][] csv) {
        this.csv = csv;
    }

    public double[] parseColumn(int colIndex) {
        double[] myValues = new double[this.csv.length - 1];

        for (int row = 1; row < this.csv.length; ++row) {
            myValues[row - 1] = Double.parseDouble(this.csv[row][colIndex]);
        }

        return myValues;
    }

}
