public class DivisionHelper {

    private double[] values;

    public DivisionHelper(double[] myValues) {
        this.values = myValues;
    }

    public double computeRunningDivision() {
        double runningTotal = this.values[0];

        for (int index = 1; index < this.values.length; ++index) {
            runningTotal = runningTotal / this.values[index];
        }

        return runningTotal;
    }
}