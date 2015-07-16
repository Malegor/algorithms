package numbers.quicksort;

import java.util.List;

public class QuickSortMedianIndexAlgorithm extends QuickSortAlgorithm {

    @Override
    protected int choosePivotIndex(final List<Integer> numbers) {
	final int size = numbers.size();
	if (size <= 2)
	    return 0;
	final int firstValue = numbers.get(0);
	final int lastValue = numbers.get(size - 1);
	final int middleIndex = (size - 1) / 2;
	final int middleValue = numbers.get(middleIndex);
	final boolean isFirstBeforeMiddle = firstValue < middleValue;
	final boolean isLastBeforeMiddle = lastValue < middleValue;
	return isFirstBeforeMiddle != isLastBeforeMiddle ? middleIndex
		: isFirstBeforeMiddle == (firstValue < lastValue) ? size - 1 : 0;
    }
}
