package test.org.training.rschwietzke;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

import org.junit.Assert;
import org.junit.Test;
import org.training.rschwietzke.ArrayList;

/**
 * Test the implementation of the ArrayList
 * 
 * @author ReneSchwietzke
 */
public class ArrayListTest
{
	/**********************************************************************
	 * Constructor
	 *********************************************************************/

	/**
	 * Test the constructor
	 */
	@Test
	public void ctor()
	{
		final ArrayList list = new ArrayList();

		Assert.assertEquals(0, list.size());
	}

	/**********************************************************************
	 * ADD
	 *********************************************************************/

	/**
	 * Test a simple add
	 */
	@Test
	public void add()
	{
		final ArrayList list = new ArrayList();
		list.add("s1");

		Assert.assertEquals(1, list.size());
		Assert.assertEquals("s1", list.get(0));

		list.add("s2");

		Assert.assertEquals(2, list.size());
		Assert.assertEquals("s2", list.get(1));

		list.add("s3");

		Assert.assertEquals(3, list.size());
		Assert.assertEquals("s3", list.get(2));
	}

	/**
	 * Test adding the same data again
	 */
	@Test
	public void addHandlingDuplicateData()
	{
		final String A = "foo1";
		final String B = new String("foo1");

		// this is the string challenge :)
		Assert.assertNotSame(A, B);
		Assert.assertEquals(A, B);

		final ArrayList list = new ArrayList();
		Assert.assertEquals(0, list.size());

		list.add(A);

		Assert.assertEquals(1, list.size());
		Assert.assertSame(A, list.get(0));

		list.add(B);

		Assert.assertEquals(2, list.size());
		Assert.assertSame(A, list.get(0));
		Assert.assertSame(B, list.get(1));
	}

	/**
	 * Test error handling
	 */
	@Test(expected=NullPointerException.class)
	public void addErrorHandling()
	{
		final ArrayList list = new ArrayList();
		list.add((String)null);
	}

	/**
	 * Add to a list that has seen removal
	 */
	/**
	 * Add to a list that has seen clear
	 */
	@Test
	public void addAfterRemove()
	{
		final ArrayList list = new ArrayList();
		list.add("s1");
		list.add("s2");

		Assert.assertEquals(2, list.size());
		list.remove(1);
		Assert.assertEquals(1, list.size());

		list.add("s3");

		Assert.assertEquals(2, list.size());
		Assert.assertEquals("s1", list.get(0));
		Assert.assertEquals("s3", list.get(1));
	}


	/**
	 * Add to a list that has seen clear
	 */
	@Test
	public void addAfterClear()
	{
		final ArrayList list = new ArrayList();
		list.add("s1");
		list.add("s2");

		Assert.assertEquals(2, list.size());

		list.clear();

		Assert.assertEquals(0, list.size());

		list.add("s2");
		list.add("s1");
		list.add("s3");

		Assert.assertEquals(3, list.size());
		Assert.assertEquals("s2", list.get(0));
		Assert.assertEquals("s1", list.get(1));	
		Assert.assertEquals("s3", list.get(2));	
	}

	/**********************************************************************
	 * ADD at pos
	 *********************************************************************/	

	/**
	 * Add at the beginning
	 */
	@Test
	public void addAtPosAtBeginning()
	{
		final ArrayList list = new ArrayList();
		list.add("s1", 0);
		list.add("s2", 0);
		list.add("s3", 0);

		Assert.assertEquals(3, list.size());
		Assert.assertEquals("s3", list.get(0));
		Assert.assertEquals("s2", list.get(1));	
		Assert.assertEquals("s1", list.get(2));	
	}

	/**
	 * Add in the middle
	 */
	@Test
	public void addAtPosInTheMiddle()
	{
		// build a list
		final ArrayList list = new ArrayList();
		list.add("s1");
		list.add("s2");
		list.add("s3");

		// validate
		Assert.assertEquals(3, list.size());
		Assert.assertEquals("s1", list.get(0));
		Assert.assertEquals("s2", list.get(1));	
		Assert.assertEquals("s3", list.get(2));	

		list.add("n4", 1);

		Assert.assertEquals(4, list.size());
		Assert.assertEquals("s1", list.get(0));
		Assert.assertEquals("n4", list.get(1));	
		Assert.assertEquals("s2", list.get(2));	
		Assert.assertEquals("s3", list.get(3));	

		list.add("n5", 3);

		Assert.assertEquals(5, list.size());
		Assert.assertEquals("s1", list.get(0));
		Assert.assertEquals("n4", list.get(1));	
		Assert.assertEquals("s2", list.get(2));	
		Assert.assertEquals("n5", list.get(3));	
		Assert.assertEquals("s3", list.get(4));	
	}

	/**
	 * Add at the end
	 */
	@Test
	public void addAtPosAtEnd()
	{
		final ArrayList list = new ArrayList();
		list.add("s1", 0);

		Assert.assertEquals(1, list.size());
		Assert.assertEquals("s1", list.get(0));

		list.add("s2", 1);

		Assert.assertEquals(2, list.size());
		Assert.assertEquals("s1", list.get(0));
		Assert.assertEquals("s2", list.get(1));	

		list.add("s3", 2);

		Assert.assertEquals(3, list.size());
		Assert.assertEquals("s1", list.get(0));
		Assert.assertEquals("s2", list.get(1));	
		Assert.assertEquals("s3", list.get(2));	
	}

	/**
	 * Check error handling of the value
	 */
	@Test(expected = NullPointerException.class)
	public void addAtPosErrorHandling1()
	{
		final ArrayList list = new ArrayList();
		list.add(null, 0);
	}

	/**
	 * Check error handling of the pos
	 */
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void addAtPosErrorHandling2()
	{
		final ArrayList list = new ArrayList();
		list.add("s1", -1);
	}

	/**
	 * Check error handling of the pos
	 */
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void addAtPosErrorHandling3()
	{
		final ArrayList list = new ArrayList();
		list.add("s1");

		list.add("foo", 20);
	}

	/**
	 * Check error handling of both
	 */
	@Test(expected = NullPointerException.class)
	public void addAtPosErrorHandling4()
	{
		final ArrayList list = new ArrayList();
		list.add(null, -100);
	}

	/**********************************************************************
	 * ADD a list
	 *********************************************************************/	

	/**
	 * Add another list, both have data
	 */
	@Test
	public void addList()
	{
		final ArrayList list1 = new ArrayList();
		final ArrayList list2 = new ArrayList();

		list1.add("a1"); list1.add("a2");
		list2.add("b1"); list2.add("b2");

		Assert.assertEquals(2, list1.size());
		Assert.assertEquals(2, list2.size());

		list1.add(list2);

		// check size and ORDER!!!
		Assert.assertEquals(4, list1.size());
		Assert.assertEquals("a1", list1.get(0));
		Assert.assertEquals("a2", list1.get(1));
		Assert.assertEquals("b1", list1.get(2));
		Assert.assertEquals("b2", list1.get(3));

		// second list stays unchanged!!!!
		Assert.assertEquals(2, list2.size());
		Assert.assertEquals("b1", list2.get(0));
		Assert.assertEquals("b2", list2.get(1));
	}

	/**
	 * Add another list, both have data
	 */
	@Test
	public void addSameList()
	{
		final ArrayList list = new ArrayList();

		list.add("a1"); list.add("a2");

		Assert.assertEquals(2, list.size());

		list.add(list);

		// check size and ORDER!!!
		Assert.assertEquals(4, list.size());
		Assert.assertEquals("a1", list.get(0));
		Assert.assertEquals("a2", list.get(1));
		Assert.assertEquals("a1", list.get(2));
		Assert.assertEquals("a2", list.get(3));
	}

	/**
	 * Add no list
	 */
	@Test(expected = NullPointerException.class)
	public void addNullList()
	{
		final ArrayList list = new ArrayList();
		list.add((ArrayList) null);
	}

	/**
	 * Add empty list
	 */
	@Test
	public void addEmptyList()
	{
		final ArrayList list1 = new ArrayList();
		list1.add("a1"); 
		list1.add("a2");

		final ArrayList list2 = new ArrayList();
		list1.add(list2);

		Assert.assertEquals(2, list1.size());
		Assert.assertEquals("a1", list1.get(0));
		Assert.assertEquals("a2", list1.get(1));
	}

	/**
	 * Add to empty list
	 */
	@Test
	public void addToEmptyList()
	{
		final ArrayList list1 = new ArrayList();

		final ArrayList list2 = new ArrayList();
		list2.add("a1"); 
		list2.add("a2");

		list1.add(list2);

		Assert.assertEquals(2, list1.size());
		Assert.assertEquals("a1", list1.get(0));
		Assert.assertEquals("a2", list1.get(1));

		// other list is unmodified
		Assert.assertEquals(2, list2.size());
		Assert.assertEquals("a1", list1.get(0));
		Assert.assertEquals("a2", list1.get(1));
	}

	/**
	 * Make sure they do not share
	 */
	@Test
	public void addListDontShare()
	{
		final ArrayList list1 = new ArrayList();
		list1.add("a1"); 
		list1.add("a2");

		final ArrayList list2 = new ArrayList();
		list2.add("b1"); 
		list2.add("b2");

		list1.add(list2);

		// play with list2, nothing should happen to 1
		list2.add("foobar");
		Assert.assertEquals(4, list1.size());
		Assert.assertEquals("a1", list1.get(0));
		Assert.assertEquals("a2", list1.get(1));
		Assert.assertEquals("b1", list1.get(2));
		Assert.assertEquals("b2", list1.get(3));

		list2.remove(0);
		Assert.assertEquals(4, list1.size());
		Assert.assertEquals("a1", list1.get(0));
		Assert.assertEquals("a2", list1.get(1));
		Assert.assertEquals("b1", list1.get(2));
		Assert.assertEquals("b2", list1.get(3));

		list2.add(list2);
		Assert.assertEquals(4, list1.size());
		Assert.assertEquals("a1", list1.get(0));
		Assert.assertEquals("a2", list1.get(1));
		Assert.assertEquals("b1", list1.get(2));
		Assert.assertEquals("b2", list1.get(3));

		list2.clear();
		Assert.assertEquals(4, list1.size());
		Assert.assertEquals("a1", list1.get(0));
		Assert.assertEquals("a2", list1.get(1));
		Assert.assertEquals("b1", list1.get(2));
		Assert.assertEquals("b2", list1.get(3));
	}


	/**********************************************************************
	 * Remove
	 *********************************************************************/		

	/** 
	 * Remove at the beginning
	 */
	@Test
	public void removeAtTheBeginning()
	{
		final ArrayList list = new ArrayList();
		list.add("s1");
		list.add("s2");
		list.add("s3");

		final String r1 = list.remove(0);

		Assert.assertEquals(2, list.size());
		Assert.assertEquals("s2", list.get(0));
		Assert.assertEquals("s3", list.get(1));		
		Assert.assertEquals("s1", r1);

		final String r2 = list.remove(0);

		Assert.assertEquals(1, list.size());
		Assert.assertEquals("s3", list.get(0));
		Assert.assertEquals("s2", r2);

		final String r3 = list.remove(0);

		Assert.assertEquals(0, list.size());
		Assert.assertEquals("s3", r3);
	}

	/** 
	 * Remove in the middle
	 */
	@Test
	public void removeInTheMiddle()
	{
		final ArrayList list = new ArrayList();
		list.add("s1");
		list.add("s2");
		list.add("s3");
		list.add("s4");
		list.add("s5");

		Assert.assertEquals(5, list.size());
		Assert.assertEquals("s1", list.get(0));
		Assert.assertEquals("s2", list.get(1));		
		Assert.assertEquals("s3", list.get(2));			
		Assert.assertEquals("s4", list.get(3));			
		Assert.assertEquals("s5", list.get(4));			

		Assert.assertEquals("s4", list.remove(3));

		Assert.assertEquals(4, list.size());
		Assert.assertEquals("s1", list.get(0));
		Assert.assertEquals("s2", list.get(1));		
		Assert.assertEquals("s3", list.get(2));			
		Assert.assertEquals("s5", list.get(3));			

		Assert.assertEquals("s2", list.remove(1));

		Assert.assertEquals(3, list.size());
		Assert.assertEquals("s1", list.get(0));
		Assert.assertEquals("s3", list.get(1));			
		Assert.assertEquals("s5", list.get(2));			

		Assert.assertEquals("s3", list.remove(1));

		Assert.assertEquals(2, list.size());
		Assert.assertEquals("s1", list.get(0));
		Assert.assertEquals("s5", list.get(1));			
	}

	/** 
	 * Remove at the end
	 */
	@Test
	public void removeAtEnd()
	{
		final ArrayList list = new ArrayList();
		list.add("s1");
		list.add("s2");
		list.add("s3");

		Assert.assertEquals(3, list.size());
		Assert.assertEquals("s1", list.get(0));
		Assert.assertEquals("s2", list.get(1));		
		Assert.assertEquals("s3", list.get(2));		

		final String r1 = list.remove(2); // no 3 ;)

		Assert.assertEquals(2, list.size());
		Assert.assertEquals("s1", list.get(0));
		Assert.assertEquals("s2", list.get(1));		
		Assert.assertEquals("s3", r1);

		final String r2 = list.remove(1);

		Assert.assertEquals(1, list.size());
		Assert.assertEquals("s1", list.get(0));	
		Assert.assertEquals("s2", r2);

		final String r3 = list.remove(0);

		Assert.assertEquals(0, list.size());
		Assert.assertEquals("s1", r3);
	}

	/** 
	 * Remove last element
	 */
	@Test
	public void removeLastListEntry()
	{
		final ArrayList list = new ArrayList();
		list.add("s1");

		final String r1 = list.remove(0);

		Assert.assertEquals(0, list.size());
		Assert.assertEquals("s1", r1);
	}

	/**
	 * Error handling, remove from empty
	 */
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void removeErrorHandling1()
	{
		final ArrayList list = new ArrayList();
		list.remove(0);
	}

	/**
	 * Error handling remove negative
	 */
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void removeErrorHandling2()
	{
		final ArrayList list = new ArrayList();
		list.add("s1");

		list.remove(-2);
	}

	/**
	 * Error handling remove too large
	 */
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void removeErrorHandling3()
	{
		final ArrayList list = new ArrayList();
		list.add("s1");

		list.remove(2);
	}	

	/** 
	 * Add and remove and over-remove
	 */
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void removeFlow()
	{
		final ArrayList list = new ArrayList();
		list.add("s1");	
		list.add("s2");	
		list.add("s3");	
		list.add("s4");	
		list.add("s5");

		Assert.assertEquals("s5", list.remove(4));
		Assert.assertEquals("s3", list.remove(2));
		Assert.assertEquals("s2", list.remove(1));
		Assert.assertEquals("s4", list.remove(1));
		Assert.assertEquals("s1", list.remove(0));

		// this should not be possible anymore
		list.remove(0);
	}

	/**
	 * Make sure that remove and compact is really clearing the last element
	 * so that we do not longer have a reference. Would cause a memory leak.
	 * @throws InterruptedException 
	 * @throws IllegalArgumentException 
	 */
	@Test
	public void removeCheckMemLeak() throws IllegalArgumentException, InterruptedException
	{
		String a = new String("A");
		final ReferenceQueue<String> refQueue = new ReferenceQueue<String>();
		final WeakReference<String> weakA = new WeakReference<String>(a, refQueue);

		final ArrayList list = new ArrayList();
		list.add("s1");
		list.add("s2");
		list.add(a);

		// get rid off reference, but it is still there
		a = null;
		Assert.assertNotNull(weakA.get());

		// list should be empty now as seen from outside, potentially,
		// the internal array might still look like that
		// ["A", "A", "A"], only the size indicator has been adjusted
		Assert.assertEquals("s1", list.remove(0));
		Assert.assertEquals("s2", list.remove(0));
		Assert.assertEquals("A", list.remove(0));

		Assert.assertEquals(0, list.size());

		// if we really remove the element, we do not have a hard reference 
		// anymore and the element went into the reference queue
		String s = "";
		final long start = System.currentTimeMillis();

		// max runtime 10s!
		while (start + 10000 > System.currentTimeMillis())
		{
			Runtime.getRuntime().gc();
			Thread.sleep(100);

			final Reference<?> ref = refQueue.remove(100);

			if (ref == weakA)
			{
				// reached the reference queue for cleaning
				// end loop
				break;
			}

			// force some mem usage
			s = s + s + System.currentTimeMillis();
		}

		// ref should be gone in case we stopped due to ref found, if the time limit was reached
		// we will fail here
		Assert.assertNull(weakA.get());
	}

	/** 
	 * Clear with data
	 */
	@Test
	public void clearList()
	{
		final ArrayList list = new ArrayList();
		list.add("s1");
		list.add("s2");

		Assert.assertEquals(2, list.size());

		list.clear();

		Assert.assertEquals(0, list.size());
	}	

	/**********************************************************************
	 * Clear
	 *********************************************************************/		
	/** 
	 * Clear an empty map, nothing to see at all!
	 */
	@Test
	public void clearEmptyList()
	{
		final ArrayList list = new ArrayList();
		Assert.assertEquals(0, list.size());

		list.clear();

		Assert.assertEquals(0, list.size());
	}

	/** 
	 * Clear and fill
	 */
	@Test
	public void clearListAndFillAgain()
	{
		final ArrayList list = new ArrayList();
		list.add("s1");
		list.add("s2");

		Assert.assertEquals(2, list.size());

		list.clear();

		Assert.assertEquals(0, list.size());

		list.add("s3");

		Assert.assertEquals(1, list.size());
		Assert.assertEquals("s3", list.get(0));

		list.add("s4");

		Assert.assertEquals(2, list.size());
		Assert.assertEquals("s3", list.get(0));
		Assert.assertEquals("s4", list.get(1));			
	}	

	/**********************************************************************
	 * Get
	 *********************************************************************/	

	/** 
	 * Plain get
	 */
	@Test
	public void get()
	{
		final String s1 = "s1";
		final String s2 = "s2";

		final ArrayList list = new ArrayList();
		list.add(s1);
		list.add(s2);

		Assert.assertEquals(new String("s1"), list.get(0));
		Assert.assertEquals(new String("s2"), list.get(1));

		// make sure we get the data we put in and not a copy that looks the same
		Assert.assertSame(s1, list.get(0));
		Assert.assertSame(s2, list.get(1));
	}

	/** 
	 * get on empty
	 */
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void getFromEmptyList()
	{
		final ArrayList list = new ArrayList();
		list.get(0);
	}

	/** 
	 * get with negative range
	 */
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void getOutOfRangeNegative()
	{
		final ArrayList list = new ArrayList();
		list.add("foo");

		list.get(-100);
	}	


	/** 
	 * get with too large
	 */
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void getOutOfRangeTooLarge()
	{
		final ArrayList list = new ArrayList();
		list.add("foo");

		list.get(100);
	}

	/**********************************************************************
	 * Size
	 * Most of size was already verified, this here is mostly a copy again
	 *********************************************************************/	
	/**
	 * Verify sizes
	 */
	public void size()
	{
		final ArrayList list = new ArrayList();
		Assert.assertEquals(0, list.size());

		list.add("foo");
		Assert.assertEquals(1, list.size());

		list.remove(0);
		Assert.assertEquals(0, list.size());

		list.add("fooaa");
		Assert.assertEquals(1, list.size());

		list.clear();
		Assert.assertEquals(0, list.size());
	}

	/**********************************************************************
	 * A flow of activity
	 * This is not really needed, but it 
	 *********************************************************************/

	/**
	 * Small helper to reduce code
	 * 
	 * @param list the list to check
	 * @param size the expected size
	 * @param data the data that should be in the list in that order
	 */
	private void validate(final ArrayList list, final int size, final String... data)
	{
		Assert.assertEquals(size, list.size());
		Assert.assertEquals(data.length, list.size());

		for (int i = 0; i < list.size(); i++)
		{
			Assert.assertEquals(data[i], list.get(i));
		}
	}

	/**
	 * Just working with the implementation
	 */
	@Test
	public void workWithListImplementation()
	{
		// create one
		final ArrayList list = new ArrayList();
		validate(list, 0, new String[0]);

		list.add(list);
		validate(list, 0, new String[0]);

		list.add("A");
		validate(list, 1, "A");

		list.add("A");
		validate(list, 2, "A", "A");

		list.add("B");
		validate(list, 3, "A", "A", "B");

		list.remove(1);
		validate(list, 2, "A", "B");

		list.add("C", 1);
		validate(list, 3, "A", "C", "B");

		list.add(list.remove(0), 2);
		validate(list, 3, "C", "B", "A");

		list.add(list.remove(1), 1);
		validate(list, 3, "C", "B", "A");

		// by nasty
		try {list.remove(-1);}catch(final ArrayIndexOutOfBoundsException e) {}
		try {list.remove(100);}catch(final ArrayIndexOutOfBoundsException e) {}
		try {list.add((String)null);}catch(final NullPointerException e) {}

		list.add(list);
		validate(list, 6, "C", "B", "A", "C", "B", "A");

		list.remove(5); 		
		validate(list, 5, "C", "B", "A", "C", "B");

		list.remove(1); 		
		validate(list, 4, "C", "A", "C", "B");

		list.remove(0); 		
		validate(list, 3, "A", "C", "B");

		list.add("D", 0); 		
		validate(list, 4, "D", "A", "C", "B");

		list.clear();
		validate(list, 0, new String[0]);

	}

}
