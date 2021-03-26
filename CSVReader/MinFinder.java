public class MinFinder {

    private double[] values;

    public MinFinder(double[] myValues) {
        this.values = myValues;
    }

    public double computeMin() {
        double smallestValueSoFar = values[0];
        for (int index = 1; index < values.length; ++index) {
            if (smallestValueSoFar > values[index]) {
                smallestValueSoFar = values[index];
            }
        }
        return smallestValueSoFar;
    }

}
