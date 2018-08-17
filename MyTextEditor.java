/**
 * This class implements the interface SimpleTextEditor using ArraySequence class as an underlying construct
 *
 * @author Edson Zandamela
 * 
 * @version 1.4, 03/25/2018
 * @see IndexOutOfBoundsException
 * @see SimpleTextEditor
 * @see ArraySequence
 */

public class MyTextEditor implements SimpleTextEditor{
	//Instance variables
	private int cursor;		// Refers to the line number of some line in the tex
	//private String text;		//Keeps track of text in the TextEditor
    	private ArraySequence<String> lines; //stores lines

    public MyTextEditor()
    {
        cursor = -1; 
        lines = new ArraySequence<String>();
    }

    /**
     * Returns true if the text is completely empty (and cursor is at line -1).
     *
     * @return true if the text is empty and false otherwise
     */
    public boolean isEmpty() {
        return cursor==-1 && lines.isEmpty();
    }

    /**
     * Returns the current number of lines of text.
     *
     * @return the current number of lines
     */
    public int size() {
        return lines.size();
    }

    /** * @version 1.4, 03/25/2018
     * Returns true if the cursor is at the last line in the text or the text
     * is empty.
     *
     * @return true if the cursor is at the last line and false otherwise.
     */
    public boolean isCursorAtLastLine() {
        return lines.isEmpty() || cursor == lines.size()-1;
    }

    /**
     * Sets the cursor to be the text line after its current position.
     *
     * @throws IndexOutOfBoundsException if the current line is size()-1
     */
    public void cursorDown() throws IndexOutOfBoundsException {
        if(cursor == size()-1)
            throw new IndexOutOfBoundsException("Exception: Current line is size()-1!");
        //Setting the cursor to be the text line after its current position.
        cursor++;
    }

    /**
     * Sets the cursor to be the text line before its current position.
     *
     * @throws IndexOutOfBoundsException if the current line is 0
     */
    public void cursorUp() throws IndexOutOfBoundsException {
        if(cursor <= 0)
            throw new IndexOutOfBoundsException("Exception: Cursor is currently at line "+cursor);
        //Setting the cursor to be the text line before its current position.
        cursor--;
    }

    /**
     * Sets the cursor to be the line ranked i (the first line is line 0).
     *
     * @param i the target line number
     * @throws IndexOutOfBoundsException if the index is negative or greater
     *                                   than size()-1
     */
    public void moveCursorToLine(int i) throws IndexOutOfBoundsException {
        if(i < 0 || i > size()-1)
            throw new IndexOutOfBoundsException();
        cursor = i;
    }

    /**
     * Returns the line number (rank) of the current cursor line.
     */
    public int cursorLineNum() {
        return cursor;
    }

    /**
     * Inserts a string s in the line after the current cursor, moving the
     * cursor to the line inserted. * @version 1.4, 03/25/2018
     *
     * @param s the string to be inserted
     */
    public void insertAfterCursor(String s) {
        if(this.isEmpty())
        {
            lines.addFirst(s);
        }
        else
            lines.addAfter(new PositionObject<>(lines.get(cursor)), s);
        cursor++;
    }

    /**
     * Inserts a string s in the line before the current cursor, moving the
     * cursor to the line inserted.
     *
     * @param s the string to be inserted
     */
    public void insertBeforeCursor(String s) {
        if(this.isEmpty() || cursor==-1)
        {
            lines.addFirst(s);
        }
        else
            lines.addBefore(new PositionObject<>(lines.get(cursor)), s);
        //cursor--;
    }

    /**
     * Replaces the string at the current cursor with the String s, keeping
     * the cursor at this line.
     *
     * @param s the string to be inserted
     */
    public void replaceAtCursor(String s) {
        lines.set(cursor, s);
    }

    /**
     * Removes the entire line at the current cursor, setting the cursor to now
     * be the position of the next line, unless the cursor was the last line,
     * in which case the cursor should move to the new last line.
     */
    public void removeAtCursor() {
        lines.remove(cursor);
        if(cursor !=0 && cursor==lines.size())
            cursor--;
    }

    /**
     * Prints the entire text to the console.
     */
    public void print() {
        for (int i=0; i<lines.size(); i++)
        {
            System.out.println(lines.get(i));
        }
    }
}//End of MyTextEditor class
