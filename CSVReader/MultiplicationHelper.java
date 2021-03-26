public class MultiplicationHelper {

    private double[] values;

    public MultiplicationHelper(double[] myValues) {
        this.values = myValues;
    }

    public double computeRunningProduct() {
        double runningTotal = this.values[0];

        for (int index = 1; index < this.values.length; ++index) {
            runningTotal = runningTotal * this.values[index];
        }

        return runningTotal;
    }
}