import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class BST_DupTree_Test {

	static DupTree dtr;
	static List<Integer> al = new ArrayList<Integer>();
	static Random r = new Random();
	

	@BeforeAll
	public static void setup() {
	 	// code to be filled in by you
		int j = 0;
		for (int i =0; i<25; i++)
		{
			j = r.nextInt(25);
			if(dtr == null)
			{
				dtr = new DupTree(j);
			
			}
			else 
			{
				dtr.insert(j);
			
				
			}
			al.add(j);
			
		}
		Iterator<Integer> it = dtr.iterator();
		
		System.out.println("DupTree created in Setup: ");
		while(it.hasNext())
		{
			System.out.print(it.next()+" ");
		}
		
		
		al.sort(null);
		
		System.out.println();
		System.out.println("Sorted ArrayList created in Setup:");
		
		for(int l = 0; l <25; l++)
		{
			System.out.print(al.get(l)+" ");
		}
		System.out.println();
		System.out.println("----------------------------");
	}

	@AfterEach
	void check_invariant() {
		// code to be filled in by you
		assertTrue (ordered(dtr));
		System.out.println("DupTree invariant maintained");
		System.out.println("----------------------------");
	}
	
	@Test
	void test_insert() {
		// code to be filled in by you 
		System.out.println("Testing DupTree insert ...");
		System.out.println("Creating ArrayList iterator and Comparing elements pair-wise ...");
		Iterator<Integer> dtri = dtr.iterator();
		Iterator<Integer> ali = al.iterator();

		while(dtri.hasNext() && ali.hasNext())
		{
			assertEquals (dtri.next(),ali.next());
			
		}
		System.out.println("... DupTree insert test passed");
		

	}
	
	@Test
	void test_delete() {
		// code to be filled in by you
		
		
		int  before_delete = 0;
		int  after_delete = 0;
		
		int k =r.nextInt(25);
		
		
		dtr.insert(k);
		
		
		
		before_delete = get_count(dtr,k);
		System.out.println("Testing DupTree delete: inserted value = "+k+" with count = "+before_delete);
		dtr.delete(k);
		after_delete = get_count(dtr,k);
		System.out.println("After DupTree delete: value = "+k+", count = "+after_delete);
		assertEquals(before_delete - 1,after_delete);
		
		
	}		

	public int get_count(DupTree tr, int v) {
		// code to be filled in by you 
		Tree tr1 =  tr.find(v);
		if (tr1 != null)
		{
			return tr1.get_count();	
		}
		
		else 
			return 0;
		
	}

	public boolean ordered(Tree tr) {
		// code to be filled in by you
		
		
		return (tr.left == null || tr.value > tr.left.max().value && ordered(tr.left))
				&& 
			(tr.right == null || tr.value < tr.right.min().value && ordered(tr.right));
		
	}
}