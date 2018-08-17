/**
 * This class implements the interface Position and its consistent with ArraySequence class
 *
 * @author Edson Zandamela
 * 
 * @version 1.4, 03/25/2018
 * @see IllegalStateException
 * @see Position Interface
 * @see ArraySequence
 */

import java.util.HashMap;

public class PositionObject<E> implements Position<E>{
	//instance Variables
	private E data;         //stores the data

	//default constructor
	public PositionObject() {
		data = null;
	}
	
	//constructor
	public PositionObject(E data) {
		this.data = data;
	}
	
	public E getElement() throws IllegalStateException {
		return data;
	}

	/**
	 * Indicates whether or ot some other object is "equal to" this one.
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof PositionObject)
		{
			return ((PositionObject)obj).getElement().equals(getElement());
		}
		else
			return false;
	}
}//End of PositionObject
