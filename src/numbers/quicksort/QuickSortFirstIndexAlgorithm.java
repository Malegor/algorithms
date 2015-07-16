package numbers.quicksort;

import java.util.List;

public class QuickSortFirstIndexAlgorithm extends QuickSortAlgorithm {

    @Override
    protected int choosePivotIndex(final List<Integer> numbers) {
	return 0;
    }
}
