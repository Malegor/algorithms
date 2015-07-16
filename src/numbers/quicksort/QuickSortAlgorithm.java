package numbers.quicksort;

import java.util.List;

public abstract class QuickSortAlgorithm {
    public long execute(final List<Integer> numbers) {
	return this.getNumberOfComparisonsSorting(numbers);
    }

    private long getNumberOfComparisonsSorting(final List<Integer> numbers) {
	final int size = numbers.size();
	if (size <= 1)
	    return 0;
	final int pivotIndex = this.choosePivotIndex(numbers);
	final int partitionIndex = this.getNewPivotIndexAfterPartition(numbers, pivotIndex);
	return size - 1 + this.getNumberOfComparisonsSorting(numbers.subList(0, partitionIndex))
		+ this.getNumberOfComparisonsSorting(numbers.subList(partitionIndex + 1, size));
    }

    private int getNewPivotIndexAfterPartition(final List<Integer> numbers, final int pivotIndex) {
	this.swap(numbers, 0, pivotIndex);
	final int pivotValue = numbers.get(0).intValue();
	int borderIndex = 1;
	for (int i = 1; i < numbers.size(); i++)
	    if (numbers.get(i) < pivotValue) {
		this.swap(numbers, i, borderIndex);
		borderIndex++;
	    }
	// Put the pivot in the right position
	this.swap(numbers, 0, borderIndex - 1);
	return borderIndex - 1;
    }

    private void swap(final List<Integer> numbers, final int index1, final int index2) {
	if (index1 == index2)
	    return;
	final Integer tmp = numbers.get(index1);
	numbers.set(index1, numbers.get(index2));
	numbers.set(index2, tmp);
    }

    protected abstract int choosePivotIndex(final List<Integer> numbers);
}
