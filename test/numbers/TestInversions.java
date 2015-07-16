package numbers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import numbers.data.IntegerReader;
import numbers.inversions.algorithm.CountInversionsMergeSortAlgorithm;

import org.junit.Test;

public class TestInversions {

    @Test
    public void testCountFewInversions() {
	final List<Integer> numbers = new ArrayList<Integer>(5);
	numbers.add(Integer.valueOf(3));
	numbers.add(Integer.valueOf(5));
	numbers.add(Integer.valueOf(2));
	numbers.add(Integer.valueOf(1));
	numbers.add(Integer.valueOf(8));
	//Collections.sort(numbers);
	final CountInversionsMergeSortAlgorithm algorithm = new CountInversionsMergeSortAlgorithm();
	final long numberOfInversions = algorithm.execute(numbers);
	System.out.println(numberOfInversions + " inversions!");
    }

    @Test
    public void testFile() throws IOException {
	final IntegerReader reader = new IntegerReader();
	reader.read("IntegerArray.txt");
	final List<Integer> numbers = reader.getNumbers();
	final CountInversionsMergeSortAlgorithm algorithm = new CountInversionsMergeSortAlgorithm();
	final long numberOfInversions = algorithm.execute(numbers);
	System.out.println(numberOfInversions + " inversions!");
    }
}
