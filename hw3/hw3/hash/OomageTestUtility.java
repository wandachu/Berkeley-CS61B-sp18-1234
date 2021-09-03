package hw3.hash;

import java.util.ArrayList;
import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /*
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        List<List<Oomage>> buckets = new ArrayList<>(M);
        for (int i = 0; i < M; i++) {
            List<Oomage> cell = new ArrayList<>();
            buckets.add(cell);
        }
        for (Oomage o : oomages) {
            int index = (o.hashCode() & 0x7FFFFFFF) % M;
            List<Oomage> currCell = buckets.get(index);
            currCell.add(o);
        }
        int N = oomages.size();
        for (List<Oomage> currCell : buckets) {
            int n = currCell.size();
            if (n < N / 50 || n > N / 2.5) {
                return false;
            }
        }
        return true;
    }
}
