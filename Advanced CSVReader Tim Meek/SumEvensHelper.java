public class SumEvensHelper {

    private double[] values;

    public SumEvensHelper(double[] myValues) {
        this.values = myValues;
    }

    public double compute() {
        double runningTotal = 0.0;

        for (int index = 0; index < this.values.length; ++index) {
            if (this.values[index] % 2 == 0) {
                runningTotal = runningTotal + this.values[index];   
            }
        }

        return runningTotal;
    }

}