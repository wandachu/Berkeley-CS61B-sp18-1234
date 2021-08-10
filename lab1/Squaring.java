public class Squaring {

    /** Make all values squared
     *
     * @param L IntList whose values will be squared
     * @return new IntList with integer values squared
     */
    public static IntList square(IntList L) {
        //does this non-mutatively with recursion by creating new IntLists
//        IntList N = null;
//        for (int i = 0; i < L.size(); i++) {
//            int currValue = L.get(L.size() - 1 - i);
//            N = new IntList(currValue * currValue, N);
//        }
//        return N;

        if (L == null) {
            return null;
        }
        return new IntList(L.first * L.first, square(L.rest));
    }

    public static IntList squareMutative(IntList L) {
        //uses a recursive approach to change the instance variables of the input IntList L
//        IntList curr = L;
//        while (curr != null) {
//            curr.first *= curr.first;
//            curr = curr.rest;
//        }
//        return L;

        if (L == null) {
            return null;
        }
        L.first *= L.first;
        L.rest = squareMutative(L.rest);
        return L;
    }

    public static void main(String[] args) {
        IntList L = new IntList(9, null);
        L = new IntList(7, L);
        L = new IntList(5, L);
        IntList N = squareMutative(L);
        System.out.println(N.size());
        System.out.println(N.get(0));
        System.out.println(N.get(1));
        System.out.println(N.get(2));
    }
}
