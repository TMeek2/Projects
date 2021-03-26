public class SumHelper {

    private double[] values;

    public SumHelper(double[] myValues) {
        this.values = myValues;
    }

    public double compute() {
        double runningTotal = 0.0;

        for (int index = 0; index < this.values.length; ++index) {
            runningTotal = runningTotal + this.values[index];
        }

        return runningTotal;
    }

}