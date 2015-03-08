package org.training.live;

/**
 * This is another implementation of ArrayList that does not support
 * generics but has the same basic characteristics in terms of growth,
 * and removal.
 * 
 * It does not support the collection interface and is not a drop-in
 * replacement for java.util.ArrayList.
 * 
 * @author Rene Schwietzke
 *
 */
public class ArrayList
{	
	/**
	 * The array to store the information in
	 */
	private String[] list;

	/**
	 * Default constructor. Creates and empty array list.
	 */
	public ArrayList()
	{
		list = new String[0];
	}

	/**
	 * Adds a string to an list, appends it at the end. The string must not be null.
	 * 
	 * @param value the string to add
	 * @throws NullPointerException when the passed value is null
	 */
	public void add(final String value)
	{
		// check for passed null value, raise NPE if needed
		if (value == null)
		{
			throw new NullPointerException("passed values must not be null");
		}

		// no, create new array list with size + 1
		// create new list
		final String[] list2 = new String[list.length + 1];

		// copy old into new list
		for (int i = 0; i < list.length; i++)
		{
			list2[i] = list[i];
		}

		// set value in last field
		list2[list2.length - 1] = value;

		// make the new list the instance list
		list = list2;
	}

	/**
	 * Inserts a value at the given position. If the position does not exist, an 
	 * ArrayIndexOutOfBoundsException is thrown. The string must not be null. 
	 * All data right of that position is shifted to the right. 
	 * 
	 * @param value the data to insert
	 * @param pos the position to insert
	 * 
	 * @throws NullPointerException when the passed value is null
	 * @throws ArrayIndexOutOfBoundsException thrown when the pos does not exist
	 */
	public void add(final String value, final int pos)
	{
		// NPE if value == null
		// check for passed null value, raise NPE if needed
		if (value == null)
		{
			throw new NullPointerException("passed values must not be null");
		}		

		// is position greater than length, throw ArrayIndexBoundException
		if (pos > size() || pos < 0)
		{
			throw new ArrayIndexOutOfBoundsException("position is outside length");
		}

		// shift everything to the right from that pos
		final String[] list2 = new String[size() + 1];

		int offset = 0;
		for (int i = 0; i < this.size(); i++)
		{
			if (i == pos)
			{
				list2[i] = value;
				offset = 1;
				list2[i + offset] = list[i];
			}
			else
			{
				// insert value at pos
				list2[i + offset] = list[i];
			}
		}

		// never inserted
		if (offset == 0)
		{
			list2[list2.length - 1] = value;
		}

		list = list2;
	}

	/**
	 * Appends a complete list. A null list is not permitted.
	 * 
	 * @param value the list to add
	 * @throws NullPointerException when the passed value is null
	 */
	public void add(final ArrayList value)
	{
		// check for null as passed argument, raise NPE if needed
		if (value == null)
		{
			throw new NullPointerException("list must not be null");
		}

		// loop through value
		for (int i = 0; i < value.size(); i++)
		{
			// add each string of value to this list with add(string)
			add(value.get(i));
		}
	}

	/**
	 * Returns the current size of the list.
	 * @return current size of the list
	 */
	public int size()
	{
		return list.length;			
	}

	/**
	 * Returns the data at the given position, if the position does not
	 * exist, we throw an ArrayIndexOutOfBoundsException
	 * 
	 * @param pos the position to ask for
	 * @return the value found at the position
	 * @throw ArrayIndexOutOfBoundsException when the position does not exist
	 */
	public String get(final int pos)
	{
		// is position greater than length, throw ArrayIndexBoundException
		if (pos >= size() || pos < 0)
		{
			throw new ArrayIndexOutOfBoundsException("position is outside length");
		}

		// return value at pos
		return list[pos];
	}

	/**
	 * removes an element at a certain position, throws an 
	 * arrayindexoutofboundsexception in case pos does not exist.
	 * 
	 * @param pos the position to remove
	 * @return the string that was removed
	 */
	public String remove(final int pos)
	{
		// is position greater than length, throw ArrayIndexBoundException
		if (pos > size() || pos < 0)
		{
			throw new ArrayIndexOutOfBoundsException("position is outside length");
		}

		// shift everything to the left from that pos
		final String[] list2 = new String[size() - 1];

		int offset = 0;
		String oldValue = null;

		for (int i = 0; i < this.size(); i++)
		{
			if (i == pos)
			{
				oldValue = list[i];
				offset = -1;
			}
			else
			{
				// continue to move data to the new list
				list2[i + offset] = list[i];
			}
		}

		list = list2;

		return oldValue;
	}

		/**
	 * clears the Arraylist
	 */
	
	public void clear(){
		list = new String[0];
		
	}


}
