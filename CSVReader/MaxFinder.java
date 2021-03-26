public class MaxFinder {

    private double[] values;

    public MaxFinder(double[] myValues) {
        this.values = myValues;
    }

    public double computeMax() {
        double largestValueSoFar = values[0];
        for (int index = 1; index < values.length; ++index) {
            if (largestValueSoFar < values[index]) {
                largestValueSoFar = values[index];
            }
        }
        return largestValueSoFar;
    }

}