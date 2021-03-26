public class ColumnIndexFinder {

    private String[] haystack;

    public ColumnIndexFinder(String[] haystack) {
        this.haystack = haystack;
    }

    public int findIndex(String needle) {
        int returnIndex = -1;

        for (int index = 0; index < this.haystack.length; ++index) {
            if (needle.equals(haystack[index])) {
                returnIndex = index;
                break;
            }
        }

        return returnIndex;
    }

}
