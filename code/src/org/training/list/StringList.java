/**
 *  ArrayListComments.java v1.0
 *  
 *  MIT license
 */
package org.training.list;

/**
 * StringList like implementation for a training. It does not support generics
 * and the common collection interfaces. It is a the result of a raw programming
 * approach.
 * 
 * The list behaves like an array but automatically growth of needed. The real
 * size if the list aka the raw size will be unknown to the world, only the 
 * size with elements will be made public. This implementation is not thread-safe.
 * 
 * @author Rene Schwietzke
 */
public interface StringList
{
	/**
	 * Adds the data at the end of the list. Growth the list if
	 * there is not enough room. Null values are not permitted.
	 * 
	 * @param s the string to add
	 * @exception NullPointerException 
	 * 				when the passed string is null
	 */
	public void add(final String s);

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
	public void add(final String s, final int pos);

	/**
	 * Adds the data from the passed list to the current list. 
	 * Append only. Will grow this list if needed.
	 * A null value for the list is not permitted.
	 * 
	 * @param list a list of strings to append
	 * @exception NullPointerException 
	 * 				when the passed list is null
	 */
	public void add(final StringList list);

	/**
	 * Return a string at position pos. Throws an ArrayIndexOutOfBoundsException
	 * then the position does not exist.
	 * 
	 * @param pos the position to return
	 * @return the string at the given position
	 * @exception ArrayIndexOutOfBoundsException 
	 * 				thrown when the pos does not exist 
	 */
	public String get(final int pos);

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
	public String remove(final int pos);

	/**
	 * Clears the entire list. The reported size is 0 afterwards.
	 */
	public void clear();

	/**
	 * The current size of the list aka the fill part. Does not report the real size
	 * underneath.
	 * 
	 * @return the filled size of the list
	 */
	public int size();

	/**
	 * Returns a string representation of the list 
	 * 
	 * @return a string with information about this list, mostly for debugging
	 */
	@Override
	public String toString();
}
