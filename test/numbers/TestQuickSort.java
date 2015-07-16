package numbers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import numbers.data.IntegerReader;
import numbers.quicksort.QuickSortAlgorithm;
import numbers.quicksort.QuickSortFirstIndexAlgorithm;
import numbers.quicksort.QuickSortLastIndexAlgorithm;
import numbers.quicksort.QuickSortMedianIndexAlgorithm;

import org.junit.Test;

public class TestQuickSort {

    @Test
    public void testFirstPivot() {
	final List<Integer> numbers = this.getSmallData();
	final QuickSortAlgorithm algorithm = new QuickSortFirstIndexAlgorithm();
	final long numberOfComparisons = algorithm.execute(numbers);
	System.out.println(numberOfComparisons + " comparisons.");
	Assert.assertEquals(6, numberOfComparisons);
    }

    @Test
    public void testLastPivot() {
	final List<Integer> numbers = this.getSmallData();
	final QuickSortAlgorithm algorithm = new QuickSortLastIndexAlgorithm();
	final long numberOfComparisons = algorithm.execute(numbers);
	System.out.println(numberOfComparisons + " comparisons.");
	Assert.assertEquals(9, numberOfComparisons);
    }

    @Test
    public void testMedianPivot() {
	final List<Integer> numbers = this.getSmallData();
	final QuickSortAlgorithm algorithm = new QuickSortMedianIndexAlgorithm();
	final long numberOfComparisons = algorithm.execute(numbers);
	System.out.println(numberOfComparisons + " comparisons.");
	Assert.assertEquals(6, numberOfComparisons);
    }

    @Test
    public void testBigDataset() throws IOException {
	final IntegerReader reader = new IntegerReader();
	reader.read("QuickSort.txt");
	QuickSortAlgorithm algorithm = new QuickSortFirstIndexAlgorithm();
	long numberOfComparisons = algorithm.execute(new ArrayList<Integer>(reader.getNumbers()));
	System.out.println("First index algorithm: " + numberOfComparisons + " comparisons.");
	algorithm = new QuickSortLastIndexAlgorithm();
	numberOfComparisons = algorithm.execute(new ArrayList<Integer>(reader.getNumbers()));
	System.out.println("Last index algorithm: " + numberOfComparisons + " comparisons.");
	algorithm = new QuickSortMedianIndexAlgorithm();
	numberOfComparisons = algorithm.execute(new ArrayList<Integer>(reader.getNumbers()));
	System.out.println("Median index algorithm: " + numberOfComparisons + " comparisons.");
    }

    private List<Integer> getSmallData() {
	final List<Integer> numbers = new ArrayList<Integer>(5);
	numbers.add(Integer.valueOf(3));
	numbers.add(Integer.valueOf(5));
	numbers.add(Integer.valueOf(2));
	numbers.add(Integer.valueOf(1));
	numbers.add(Integer.valueOf(8));
	return numbers;
    }
}
