/**
 * This is a test drive application to demonstrate correctness
 *
 * @author Edson Zandamela
 * 
 * @version 1.4, 03/25/2018
 * @see IndexOutOfBoundsException
 * @see SimpleTextEditor
 * @see ArraySequence
 * @see MyTextEditor
 * @see PositionObject
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Zandamela {

    public static void main(String[] args) throws FileNotFoundException {
        MyTextEditor editor = new MyTextEditor();

        Scanner reader;		//Calls the Scanner
        reader = new Scanner(new File("initial.txt"));	//Initializes the text given in initial.txt
        //Reading the file
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
//            System.out.println(lineNum+":"+line);
//            lineNum++;
            editor.insertAfterCursor(line);	
        }
        int prevLast = editor.cursorLineNum();
        //Printing the text.
        System.out.println("***********Initializing the text to be the same as the text given in the file initial.txt*************");
        editor.print();		//Prints the editor with MyTextEditor

        //THIS UPWARD IS WORKING FINE!

        //Using all methods in the MyTextEditor class but print()to convert this text to the one given in middle.txt
        reader = new Scanner(new File("middle.txt"));		
        //Converting the text
        int lineNum = 0;
        boolean isLast = false;
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            if (lineNum == 0) {
                editor.moveCursorToLine(0);
                editor.removeAtCursor();
                editor.insertBeforeCursor(line);
            } else if (lineNum == 1) {
                editor.cursorDown();
                editor.removeAtCursor();
                editor.cursorUp();
                editor.insertAfterCursor(line);
                editor.cursorDown();
            } else if (editor.isCursorAtLastLine()) {
                if (!isLast) {
                    editor.replaceAtCursor(line);
                    isLast = true;
                } else
                    editor.insertAfterCursor(line);
            } else {
                editor.replaceAtCursor(line);
                editor.cursorDown();
            }
            //editor.insertAfterCursor(line);
            int size = editor.size();
            lineNum++;
        }

        System.out.println("\n*********** Converting text into text given in middle.txt *************");
        editor.print();

        //Using as many additional methods in the MyTextEditor class as needed to convert this text into
        //the text given in final.txt
        reader = new Scanner(new File("final.txt"));
        //convert text
        lineNum = 0;
        isLast = false;
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            if (lineNum == 0) {
                editor.moveCursorToLine(0);

                editor.removeAtCursor();

                editor.insertBeforeCursor(line);
            } else if (lineNum == 1) {
                editor.cursorDown();
                editor.removeAtCursor();
                editor.cursorUp();
                editor.insertAfterCursor(line);
                editor.cursorDown();
            } else if (editor.isCursorAtLastLine()) {
                if (!isLast) {
                    editor.replaceAtCursor(line);
                    isLast = true;
                } else
                    editor.insertAfterCursor(line);
            } else {
                editor.replaceAtCursor(line);
                editor.cursorDown();
            }
            //editor.insertAfterCursor(line);
            int size = editor.size();
            lineNum++;
        }
        System.out.println("\n*********** After final.txt Contents *************");
        editor.print();
    }
}
