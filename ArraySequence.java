/**
 * This class implements the interfaces Sequence, List, and PositionalList
 *
 * @author Edson Zandamela
 * 
 * @version 1.4, 03/25/2018
 * @see IllegalArgumentException
 * @see IndexOutOfBoundsException
 * @see Sequence
 * @see List
 * @see PositionalList
 */

public class ArraySequence<E> implements Sequence<E> {
	//Fields
	private final int INITIAL_CAPACITY = 100;
	private PositionObject<E>[] S;
	private int size;

	//Default constructor
	ArraySequence() {
		size = 0;	//Stores the size
		S = (PositionObject<E>[]) new PositionObject[INITIAL_CAPACITY];	//Initialises an array of objects
	}

//<----BEGINING IMPLEMENTING SEQUENCE CLASS---->
	/**
	 * Returns the position containing the element at the given index.
	 * @param i the index of the element
	 * @return the position of the element at the specified index
	 * @throws IndexOutOfBoundsException if the index is negative or greater than size()-1
	 * 
	 */
	public Position<E> atIndex(int i) throws IndexOutOfBoundsException {
		if(i<0 || i>=size())				//If index is negative or > than 1
			new IndexOutOfBoundsException();	//Throws exception
		return S[i];					//Return the position of a given element's index
	}

	/**
	 * Returns the index of the element stored at the given Position.
	 * @param p the Position of the element
	 * @return the index of the element at the specified Position
	 * @throws IllegalArgumentException if p is not a valid position for this list                                 
	 */
	
	public int indexOf(Position<E> p) throws IllegalArgumentException {
		for (int i=0; i<size(); i++)		//search for the element
		{
			if(S[i].equals(p))		//if that element's position is equal to the index
				return i;		//return it
		}
		throw new IllegalArgumentException();  //if p is not a valid position throw...
	}
//<----END OF SEQUENCE CLASS IMPLEMENTATION---->

//<----IMPLEMENTING LIST AND POSITIONAL LIST CLASSES---->
	/**
	 * Returns the number of elements in the list.
	 * @return number of elements in the list
	 */
	public int size() {
		return size;
	}

	/**
	 * Tests whether the list is empty.
	 * @return true if the list is empty and false otherwise
	 */
	public boolean isEmpty() {
		return size()==0;
	}

	/**
	 * Returns the first Position in the list.
	 * @return the first Position in the list (or null, if empty)
	 */
	public Position<E> first() {
		if(isEmpty())
			return null;	//Return null if the list is empty
		return S[0];		//Otherwise return the position of the first element
	}

	/**
	 * Returns the last Position in the list.
	 * @return the last Position in the list (or null, if empty)
	 */
	public Position<E> last() {
		if(isEmpty())
			return null;	//Return null if the list is empty
		return S[size()-1];	//Otherwise return the position of the last element
	}

	/**
	 * Returns the Position immediately before Position p
	 * @param p a Position of the list
	 * @return the Position of the preceding element (or null, if p is first)
	 * @throws IllegalArgumentException if p is not a valid position for this list                                
	 */
	public Position<E> before(Position<E> p) throws IllegalArgumentException {
		int i = indexOf(p);		//Gets the position of the element
		if(i==0)			
			return null;		//Returns null, if p is at first position
		return S[i-1];			//Returns previous position
	}

	/**
	 * Returns the Position immediately after Position p
	 * @param p a Position of the list
	 * @return the Position of the following element (or null, if p is last)
	 * @throws IllegalArgumentException if p is not a valid position for this list                      
	 */
	public Position<E> after(Position<E> p) throws IllegalArgumentException {
		int i = indexOf(p);	//Gets the position p
		if(i==size()-1)
			return null;	//Returns null, if p is last position
		return S[i+1];		//return next position otherwise
	}

	/**
	 * Inserts an element at the front of the list.
	 * @param e the new element
	 * @return the Position representing the location of the new element
	 */
	public Position<E> addFirst(E e) {
		PositionObject<E> po = new PositionObject<>(e);		//Creates a position object
		addAt(0, po);		//Adds a new element at the front of the list
		return po;		//returns the position of the element 
	}

	/**
	 * Inserts an element at the back of the list.
	 * @param e the new element
	 * @return the Position representing the location of the new element
	 */
	public Position<E> addLast(E e) {
		if(size == S.length)
			resize();		//If full double it
		PositionObject<E> po = new PositionObject<>(e);		//create a position object
		S[size] = po;	//sets it to last element
		size++;		//Increase size
		return po;	//retuens position
	}

	/**
	 * Inserts an element immediately before the given Position.
	 * @param p the Position before which the insertion takes place
	 * @param e the new element
	 * @return the Position representing the location of the new element
	 * @throws IllegalArgumentException if p is not a valid position for this
	 * list
	 */
	public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException {
		int index = indexOf(p);
		PositionObject<E> po = new PositionObject<>(e);	//creates a new position object
		addAt(index, po);	//Adds new elem 
		return po;		//Returns pos elem
	}

	/**
	 * Inserts an element immediately after the given Position.
	 *
	 * @param p the Position after which the insertion takes place
	 * @param e the new element
	 * @return the Position representing the location of the new element
	 * @throws IllegalArgumentException if p is not a valid position for this
	 *                                  list
	 */
	public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException {
		int index = indexOf(p);
		PositionObject<E> po = new PositionObject<>(e);		//Creates a position object
		addAt(index+1, po);	//Adds ele at the next index pos
		return po;		//returns pos elem
	}

	/**
	 * Replaces the element stored at the given Position and returns the
	 * replaced element.
	 *
	 * @param p the Position of the element to be replaced
	 * @param e the new element
	 * @return the replaced element
	 * @throws IllegalArgumentException if p is not a valid position for this
	 *                                  list
	 */
	public E set(Position<E> p, E e) throws IllegalArgumentException {
		int index = indexOf(p);		//Gets the element's position
		PositionObject<E> po = new PositionObject<>(e);	//create a position object
		S[index] = po;		//replaces the elem
		return p.getElement();	//return replaced element.
	}

	/**
	 * Removes the element stored at the given Position and returns it.
	 * The given position is invalidated as a result.
	 *
	 * @param p the Position of the element to be removed
	 * @return the removed element
	 * @throws IllegalArgumentException if p is not a valid position for this
	 *                                  list
	 */
	public E remove(Position<E> p) throws IllegalArgumentException {
		int index = indexOf(p);	//Gets the element's position
		S[index] = null;	//Invalidates position
/*		Position<E> temp;
		//shift elements
		for (int k=index+1; k < size(); k++)
		{
			S[k-1] = S[k];
		}
*/
		return p.getElement();
	}

	/**
	 * Returns (but does not remove) the element at index i.
	 *
	 * @param i the index of the element to return
	 * @return the element at the specified index
	 * @throws IndexOutOfBoundsException if the index is negative or greater
	 *                                   than size()-1
	 */
	public E get(int i) throws IndexOutOfBoundsException {
		if(i<0 || i>=size())
			throw new IndexOutOfBoundsException("java.lang.IndexOutOfBoundsException Index:"+i);
		PositionObject<E> po = S[i];	//Gets the elemnt's index
		return po.getElement();		//return the element's position
	}

	/**
	 * Replaces the element at the specified index, and returns the element
	 * previously stored.
	 *
	 * @param i the index of the element to replace
	 * @param e the new element to be stored
	 * @return the previously stored element
	 * @throws IndexOutOfBoundsException if the index is negative or greater
	 *                                   than size()-1
	 */
	public E set(int i, E e) throws IndexOutOfBoundsException {
		if(i<0 || i>=size())
			new IndexOutOfBoundsException();
		E prev =  S[i].getElement();		//Gets element
		S[i] = new PositionObject<E>(e);	//Replaces it
		return prev;				//returns it
	}

	/**
	 * Inserts the given element at the specified index of the list, shifting
	 * all subsequent elements in the list one position further to make room.
	 *
	 * @param i the index at which the new element should be stored
	 * @param e the new element to be stored
	 * @throws IndexOutOfBoundsException if the index is negative or greater
	 *                                   than size()
	 */
	public void add(int i, E e) throws IndexOutOfBoundsException {
		PositionObject<E> po = new PositionObject<>(e);		//creates a position object
		addAt(i, po);	//Adds i at position
	}

	/**
	 * Removes and returns the element at the given index, shifting all
	 * subsequent elements in the list one position closer to the front.
	 *
	 * @param i the index of the element to be removed
	 * @return the element that had be stored at the given index
	 * @throws IndexOutOfBoundsException if the index is negative or greater
	 *                                   than size()-1
	 */
	public E remove(int i) throws IndexOutOfBoundsException {
		E e = S[i].getElement();		//Assigns new to e position
		S[i] = null;				//Sets e to null
		for (int k=i+1; k < size(); k++)	//shifts elements
		{
			S[k-1] = S[k];
		}
		size--;
		return e;				//returns the elem
	}

	/** This is a helper method to add at a certain position and it assumes that the index is valid
	 */
	private void addAt(int index, PositionObject<E> obj)
	{
		if(size == S.length)
			resize();
		//adding to position
		PositionObject<E> prev = S[index];
		PositionObject<E> temp;
		S[index] = obj;
		//if the last elem
		if(index==size())
		{
			size++;
			return; //no shifting
		}

		//shift elements
		for (int k=index+1; k < size(); k++)
		{
			temp = S[k];
			S[k] = prev;
			prev = temp;
		}
		//put last elem
		S[size] = prev;
		//increment size
		size++;
	}

	/** Resizes internal array to double the capacity*/
	private void resize() {
		//double capacity
		int capacity = S.length * 2;
		PositionObject<E>[] temp = (PositionObject<E>[]) new PositionObject[capacity];
		for (int k=0; k < size(); k++)
			temp[k] = S[k];
		S = temp;                           // start using the new array
	}
}// End of ArraySequence
