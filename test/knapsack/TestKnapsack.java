package knapsack;

import java.io.IOException;
import java.util.Set;

import knapsack.algorithm.DynamicProgramKnapsack;
import knapsack.algorithm.GreedyKnapsackAlgorithm;
import knapsack.data.KnapsackItem;
import knapsack.data.KnapsackReader;

import org.junit.Test;

public class TestKnapsack {

    @Test
    public void testDynamicAndGreedyAlgorithms() {
	final KnapsackReader reader = new KnapsackReader();
	try {
	    reader.read("knapsack_big.txt");
	} catch (final IOException e) {
	    e.printStackTrace();
	    return;
	}
	final Set<KnapsackItem> items = reader.getItems();
	final int capacity = reader.getKnapsackCapacity();
	final GreedyKnapsackAlgorithm greedyAlgorithm = new GreedyKnapsackAlgorithm(capacity);
	final Set<KnapsackItem> greedyResult = greedyAlgorithm.execute(items);
	System.out.println("GREEDY");
	System.out.println(greedyResult);
	System.out.println("Value: " + greedyAlgorithm.getTotalValue(greedyResult) + " ; weight : "
		+ greedyAlgorithm.getTotalWeight(greedyResult));

	final DynamicProgramKnapsack dpKnapsack = new DynamicProgramKnapsack(capacity);
	final Set<KnapsackItem> dpResult = dpKnapsack.execute(items);
	System.out.println("DYNAMIC PROGRAM");
	System.out.println(dpResult);
	System.out.println("Value: " + dpKnapsack.getTotalValue(dpResult) + " ; weight : "
		+ dpKnapsack.getTotalWeight(dpResult));
    }
}
