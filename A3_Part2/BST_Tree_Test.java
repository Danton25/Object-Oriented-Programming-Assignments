import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.Random;
import java.util.TreeSet;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class BST_Tree_Test {

	static Tree tr;
	static TreeSet<Integer> ts = new TreeSet<Integer>();
	static Random r = new Random();

	@BeforeAll
	public static void setup() {
		// code to be filled in by you
		


		
		System.out.println("Tree created in Setup: ");
		
		int j = 0;
		for (int i =0; i<25; i++)
		{
			j = r.nextInt(25);
			if(tr == null)
			{
				tr = new Tree(j);
			}
			else 
			{
				tr.insert(j);
			}
			ts.add(j);
		}
		Iterator<Integer> is = ts.iterator();
		Iterator<Integer> it = tr.iterator();
		while(it.hasNext())
		{
			System.out.print(it.next()+" ");
		}
		System.out.println();
		System.out.println("TreeSet created in Setup:");
		while(is.hasNext())
		{
			System.out.print(is.next()+" ");
		}
		System.out.println();
		System.out.println("-------------------------");
	}		 

	@AfterEach
	void check_invariant() {
		// code to be filled in by you 
		assertTrue (ordered(tr));
		System.out.println("Tree invariant maintained");
		
	}
		
	@Test
	void test_insert() {
		// code to be filled in by you
		
		System.out.println("Testing Tree insert ...");
		System.out.println("Creating TreeSet iterator and Comparing elements pair-wise ...");
		Iterator<Integer> itr = tr.iterator();
		Iterator<Integer> its = ts.iterator();

		while(itr.hasNext() && its.hasNext())
		{

			assertEquals (itr.next(),its.next());

		}
		System.out.println("... Tree insert test passed");
	}
		
	public boolean ordered(Tree tr) {
		// code to be filled in by you	
		
		return (tr.left == null || tr.value > tr.left.max().value && ordered(tr.left))
				&& 
			(tr.right == null || tr.value < tr.right.min().value && ordered(tr.right));
		
	}

}