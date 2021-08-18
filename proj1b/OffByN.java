public class OffByN implements CharacterComparator {
    private int numberN;

    public OffByN(int N) {
        numberN = N;
    }

    /**
     * Returns true for characters that are different by exactly N.
     * @param x - the first character to check.
     * @param y - the second character to check.
     * @return true if x and y are off by N.
     */
    @Override
    public boolean equalChars(char x, char y) {
        return (Math.abs(x - y) == numberN);
    }
}
