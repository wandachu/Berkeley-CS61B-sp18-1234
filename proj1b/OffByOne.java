public class OffByOne implements CharacterComparator {

    /**
     * Returns true for characters that are different by exactly one.
     * @param x - the first character to check.
     * @param y - the second character to check.
     * @return true if x and y are off by one.
     */
    @Override
    public boolean equalChars(char x, char y) {
        return (x - y == 1 || x - y == -1);
    }
}
