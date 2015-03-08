package test.org.training.live;

import org.junit.Assert;
import org.junit.Test;
import org.training.live.ArrayList;


public class ArrayListTest
{
	@Test
	public final void add()
	{
		final ArrayList list = new ArrayList();
		list.add("test");

		Assert.assertEquals("test", list.get(0));
	}

	@Test
	public final void addMore()
	{
		final ArrayList list = new ArrayList();
		list.add("test1");
		list.add("test2");
		list.add("test3");

		Assert.assertEquals("test1", list.get(0));		
		Assert.assertEquals("test2", list.get(1));		
		Assert.assertEquals("test3", list.get(2));		
	}

	@Test(expected = NullPointerException.class)
	public final void addWithNull()
	{
		final ArrayList list = new ArrayList();
		list.add((String) null);
	}

	@Test
	public final void sizeOnEmpty()
	{
		final ArrayList list = new ArrayList();
		Assert.assertEquals(0, list.size());		
	}

	@Test
	public final void sizeWithOneString()
	{
		final ArrayList list = new ArrayList();
		list.add("test1");
		Assert.assertEquals(1, list.size());		
	}

	@Test
	public final void get()
	{
		final ArrayList list = new ArrayList();
		list.add("test1");
		Assert.assertEquals("test1", list.get(0));

		list.add("test2");
		Assert.assertEquals("test1", list.get(0));		
		Assert.assertEquals("test2", list.get(1));

		list.add("test3");
		Assert.assertEquals("test1", list.get(0));		
		Assert.assertEquals("test2", list.get(1));
		Assert.assertEquals("test3", list.get(2));
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public final void getPosTooLarge()
	{
		final ArrayList list = new ArrayList();
		list.get(1);
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public final void getOnEmptyList()
	{
		final ArrayList list = new ArrayList();
		list.get(0);
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public final void getPosTooLittle()
	{
		final ArrayList list = new ArrayList();
		list.get(-1);
	}	

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public final void getPosTooLittleListFilled()
	{
		final ArrayList list = new ArrayList();
		list.add("test");
		list.get(-1);
	}	

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public final void getPosTooLargeListFilled()
	{
		final ArrayList list = new ArrayList();
		list.add("test");
		list.get(1);
	}	









	@Test(expected = NullPointerException.class)
	public final void addNullList()
	{
		final ArrayList list = new ArrayList();
		list.add((ArrayList)null);
	}



	@Test
	public final void addEmptyList()
	{
		final ArrayList list = new ArrayList();
		final ArrayList list2 = new ArrayList();

		list.add(list2);

		Assert.assertEquals(0, list.size());
		Assert.assertEquals(0, list2.size());
	}

	@Test
	public final void addListWithElements()
	{
		final ArrayList list = new ArrayList();
		final ArrayList list2 = new ArrayList();

		list2.add("foo");
		list2.add("bar");

		list.add(list2);

		Assert.assertEquals(2, list.size());
		Assert.assertEquals(2, list2.size());

		Assert.assertEquals("foo", list.get(0));		
		Assert.assertEquals("bar", list.get(1));

		Assert.assertEquals("foo", list2.get(0));		
		Assert.assertEquals("bar", list2.get(1));		
	}

	@Test
	public final void addListWithElementsToListWithOneElement()
	{
		final ArrayList list = new ArrayList();
		list.add("test");

		final ArrayList list2 = new ArrayList();

		list2.add("foo");
		list2.add("bar");

		list.add(list2);

		Assert.assertEquals(3, list.size());
		Assert.assertEquals(2, list2.size());

		Assert.assertEquals("test", list.get(0));		
		Assert.assertEquals("foo", list.get(1));		
		Assert.assertEquals("bar", list.get(2));

		Assert.assertEquals("foo", list2.get(0));		
		Assert.assertEquals("bar", list2.get(1));		
	}

	@Test
	public final void addEmptyListToListWithOneElement()
	{
		final ArrayList list = new ArrayList();
		list.add("test");

		final ArrayList list2 = new ArrayList();

		list.add(list2);

		Assert.assertEquals(1, list.size());
		Assert.assertEquals(0, list2.size());

		Assert.assertEquals("test", list.get(0));		
	}

	@Test
	public final void addAtLastPosWithEmptyList()
	{
		final ArrayList list = new ArrayList();
		list.add("test2", 0);

		Assert.assertEquals("test2", list.get(0));				
	}

	@Test
	public final void addAtLastPosWithSize1()
	{
		final ArrayList list = new ArrayList();
		list.add("test1");
		list.add("test2", 1);

		Assert.assertEquals("test1", list.get(0));				
		Assert.assertEquals("test2", list.get(1));				
	}

	@Test
	public final void addAtLastPosWithSize2()
	{
		final ArrayList list = new ArrayList();
		list.add("test1");
		list.add("test2");
		list.add("test3", 2);

		Assert.assertEquals("test1", list.get(0));				
		Assert.assertEquals("test2", list.get(1));				
		Assert.assertEquals("test3", list.get(2));				
	}

	@Test
	public final void addInTheMiddle()
	{
		final ArrayList list = new ArrayList();
		list.add("test1");
		list.add("test2");
		list.add("foo", 1);

		Assert.assertEquals("test1", list.get(0));				
		Assert.assertEquals("foo", list.get(1));				
		Assert.assertEquals("test2", list.get(2));				
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public final void addWithPosTooLarge()
	{
		final ArrayList list = new ArrayList();
		list.add("test1");
		list.add("test2");
		list.add("foo", 10);		
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public final void addWithPosTooSmall()
	{
		final ArrayList list = new ArrayList();
		list.add("test1");
		list.add("test2");
		list.add("foo", -10);		
	}

	@Test
	public final void removeLastElementEmptyAfterwards()
	{
		final ArrayList list = new ArrayList();
		list.add("test1");

		Assert.assertEquals("test1", list.remove(0));
		Assert.assertEquals(0, list.size());
	}

	@Test
	public final void removeLastElement()
	{
		final ArrayList list = new ArrayList();
		list.add("test1");
		list.add("test2");

		Assert.assertEquals("test2", list.remove(1));
		Assert.assertEquals(1, list.size());
		Assert.assertEquals("test1", list.get(0));
	}

	@Test
	public final void removeElementInTheMiddle()
	{
		final ArrayList list = new ArrayList();
		list.add("test1");
		list.add("test2");
		list.add("test3");

		Assert.assertEquals("test2", list.remove(1));

		Assert.assertEquals(2, list.size());
		Assert.assertEquals("test1", list.get(0));
		Assert.assertEquals("test3", list.get(1));
	}

	@Test
	public final void removeFirstElement()
	{
		final ArrayList list = new ArrayList();
		list.add("test1");
		list.add("test2");
		list.add("test3");

		Assert.assertEquals("test1", list.remove(0));

		Assert.assertEquals(2, list.size());
		Assert.assertEquals("test2", list.get(0));
		Assert.assertEquals("test3", list.get(1));
	}
	@Test
	public final void clearList() {

		ArrayList list = new ArrayList();
		list.add("test1");
		list.add("test2");
		list.add("test3");
		
		list.clear();

		
		Assert.assertEquals(0, list.size());
		

	}
}



