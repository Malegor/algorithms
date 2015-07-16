package numbers.quicksort;

import java.util.List;

public class QuickSortLastIndexAlgorithm extends QuickSortAlgorithm {

    @Override
    protected int choosePivotIndex(final List<Integer> numbers) {
	return numbers.size() - 1;
    }
}
