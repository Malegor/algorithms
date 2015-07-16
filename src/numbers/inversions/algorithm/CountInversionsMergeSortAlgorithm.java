package numbers.inversions.algorithm;

import java.util.ArrayList;
import java.util.List;

public class CountInversionsMergeSortAlgorithm {
    public long execute(final List<Integer> numbers) {
	final long inversions = this.countInversions(numbers);
	return inversions;
    }

    private long countInversions(final List<Integer> numbers) {
	final int size = numbers.size();
	if (size < 2)
	    return 0;
	final List<Integer> firstPart = numbers.subList(0, size / 2);
	final List<Integer> secondPart = numbers.subList(size / 2, size);
	return this.countInversions(firstPart) + this.countInversions(secondPart)
		+ this.countSplitInversions(firstPart, secondPart);
    }

    private long countSplitInversions(final List<Integer> firstPart, final List<Integer> secondPart) {
	final int totalSize = firstPart.size() + secondPart.size();
	final List<Integer> mergedList = new ArrayList<Integer>(totalSize);
	int numberOfInversions = 0;
	int firstIndex = 0, secondIndex = 0;
	Integer firstElement = firstPart.get(firstIndex), secondElement = secondPart.get(secondIndex);
	while (mergedList.size() < totalSize)
	    if (firstElement.intValue() < secondElement.intValue()) {
		mergedList.add(firstElement);
		firstIndex++;
		firstElement = firstIndex >= firstPart.size() ? Integer.MAX_VALUE : firstPart.get(firstIndex);
	    } else {
		mergedList.add(secondElement);
		secondIndex++;
		numberOfInversions += firstPart.size() - firstIndex;
		secondElement = secondIndex >= secondPart.size() ? Integer.MAX_VALUE : secondPart.get(secondIndex);
	    }
	// The merged list has to be copied back into the two lists before returning the final result
	int i = 0;
	for (; i < firstPart.size(); i++)
	    firstPart.set(i, mergedList.get(i));
	for (; i < totalSize; i++)
	    secondPart.set(i - firstPart.size(), mergedList.get(i));
	return numberOfInversions;
    }
}
