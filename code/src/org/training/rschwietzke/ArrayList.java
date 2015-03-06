/**
 *  ArrayListComments.java v1.0
 *  
 *  MIT license
 */
package org.training.rschwietzke;

import java.text.MessageFormat;
import java.util.Arrays;

/**
 * ArrayList like implementation for a training. It does not support generics
 * and the common collection interfaces. It is a the result of a raw programming
 * approach.
 * 
 * The list behaves like an array but automatically growth of needed. The real
 * size if the list aka the raw size will be unknown to the world, only the 
 * size with elements will be made public. This implementation is not thread-safe.dis
 * 
 * @author Rene Schwietzke
 */
public class ArrayList
{
	/**
	 * A data structure
	 */
	private String[] array;

	/**
	 * This is used to keep our size aligned, because the real array size is not identical to the 
	 * fill size
	 */
	private int size = 0;

	/**
	 * Growth the list by this amount when it has to be enlarged
	 */
	private static final int GROWTH_AMOUNT = 5;

	/**
	 * The default constructor without parameters
	 */
	public ArrayList()
	{
		array = new String[0];
	}

	/**
	 * Adds the data at the end of the list. Growth the list if
	 * there is not enough room. Null values are not permitted.
	 * 
	 * @param s the string to add
	 * @exception NullPointerException 
	 * 				when the passed string is null
	 */
	public void add(final String s)
	{
		// check data
		if (s == null)
		{
			throw new NullPointerException("null data is not permitted");
		}

		// check if we still have enough space
		if (size + 1 > array.length)
		{
			enlarge();
		}

		// keep in mind, arrays are 0 based
		array[size] = s;

		size++;
	}

	/**
	 * Insert a string at a certain position. Does not permit holes
	 * in the list. The string currently at the position will be moved to the next position.
	 * Will grow the list if needed.
	 * 
	 * Invalid positions such as negative pos will be reported by an
	 * ArrayIndexOutOfBoundsException.
	 * 
	 * @param s the string to add
	 * @param pos the target positon of the string
	 * @exception NullPointerException 
	 * 				when the passed string is null
	 * @exception ArrayIndexOutOfBoundsException
	 * 				when the pos is invalid such as negative or too large
	 */
	public void add(final String s, final int pos)
	{
		// check data
		if (s == null)
		{
			throw new NullPointerException("null data is not permitted");
		}

		// is pos valid. Keep in mind I can add at the end, even though
		// this position does not yet exist!
		if (pos < 0 || pos > size)
		{
			throw new ArrayIndexOutOfBoundsException(MessageFormat.format("Positon {0} is not in range of size {1}", pos, size));
		}

		// check if we still have enough space
		if (size + 1 > array.length)
		{
			enlarge();
		}

		// we have enlarged the array, so we can safely move everything to the right
		for (int i = size - 1; i >= pos; i--)
		{
			array[i + 1] = array[i];
		}

		// insert our element
		array[pos] = s;

		size++;
	}

	/**
	 * Return a string at position pos. Throws an ArrayIndexOutOfBoundsException
	 * then the position does not exist.
	 * 
	 * @param pos the position to return
	 * @return the string at the given position
	 * @exception ArrayIndexOutOfBoundsException 
	 * 				thrown when the pos does not exist 
	 */
	public String get(final int pos)
	{
		if (pos < 0 || pos > size - 1)
		{
			throw new ArrayIndexOutOfBoundsException(MessageFormat.format("Positon {0} is not in range of size {1}", pos, size));
		}

		return array[pos];
	}

	/**
	 * Adds the data from the passed list to the current list. 
	 * Append only. Will grow this list if needed.
	 * A null value for the list is not permitted.
	 * 
	 * @param list a list of strings to append
	 * @exception NullPointerException 
	 * 				when the passed list is null
	 */
	public void add(final ArrayList list)
	{
		// make sure we got a list
		if (list == null)
		{
			throw new NullPointerException("list must not be empty");
		}

		// capture start size to permit to run add(this)
		final int originalSize = list.size();
		for (int i = 0; i < originalSize; i++)
		{
			add(list.get(i));
		}
	}

	/**
	 * Removes a string from the list by a given position. If the
	 * position does not exist, an ArrayIndexOutOfBoundsException is
	 * thrown.
	 * 
	 * @param pos the position to remove
	 * @exception ArrayIndexOutOfBoundsException
	 * 				when pos is invalid such as negative or too large
	 * @return the string that has been removed from the list
	 */
	public String remove(final int pos)
	{
		// is pos valid
		if (pos < 0 || pos > size - 1)
		{
			throw new ArrayIndexOutOfBoundsException(MessageFormat.format("Positon {0} is not in range of size {1}", pos, size));
		}	

		// get the result
		final String result = array[pos];

		// we have to close the hole
		for (int i = pos; i < size - 1; i++)
		{
			array[i] = array[i + 1];
		}

		// to be really great to GC, we have to make sure we do not hold a copy of the last 
		// element at the end, this position should be empty now. Make it so!
		array[size - 1] = null;

		// correct size
		size--;

		// return the result
		return result;
	}

	/**
	 * Clears the entire list. The reported size is 0 afterwards.
	 */
	public void clear()
	{
		// just drop it by creating a new array
		array = new String[1];

		// reset size 
		size = 0;
	}

	/**
	 * The current size of the list aka the fill part. Does not report the real size
	 * underneath.
	 * 
	 * @return the filled size of the list
	 */
	public int size()
	{
		return size;
	}

	/**
	 * Enlarge the array 
	 */
	private void enlarge()
	{
		// create a new list that is larger
		final String[] newArray = new String[array.length + GROWTH_AMOUNT];

		// move all the data over
		// version one: our own loop
		for (int i = 0; i < array.length; i++)
		{
			newArray[i] = array[i];
		}

		// version two: the fast system version
		// System.arraycopy(array, 0, newArray, 0, array.length);

		// bring it live
		array = newArray;
	}

	/**
	 * Returns a string representation of the list 
	 * 
	 * @return a string with information about this list, mostly for debugging
	 */	
	@Override
	public String toString()
	{
		return "ArrayList [array=" + Arrays.toString(array) + ", size=" + size + "]";
	}
}
