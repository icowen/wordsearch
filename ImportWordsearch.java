package wordsearch;

import java.io.*;
import java.util.*;

/*
 * Class for making wordsearch from a .txt file
 */
public class ImportWordsearch {
  char[][] letters;
  String[] words;
  String filename;

  /*
   * Constructor that sets filename, words, and letters for the board.
   * @param String is the name of the .txt file being imported.
   */
  public ImportWordsearch(String filename) {
    this.filename = filename;
    setWords();
  }

  /*
   * letters getter
   * @return char[][]
   */
  public char[][] getLetters() {
    return this.letters;
  }

  /*
   * words getter
   * @return String[]
   */
  public String[] getWords() {
    return this.words;
  }

  /*
   * Method that reads .txt file, and sets the variables words and letters
   */
  public void setWords() {
    File file = new File(this.filename);
    String line = "null";
    List<char[]> letters = new ArrayList<char[]>();
    List<String> words = new ArrayList<String>();
    /*
     * ArrayList instead of array for add() since there is
     * an unknown amount of words.
     */

    try {
        Scanner input = new Scanner(System.in);
        input = new Scanner(file);

        //Reading and saving the board
        while (input.hasNext()) {
          line = input.nextLine();

          /* Once the board is loaded the flag is "end"
           * to indicate the next Strings are words.
           */
          if(line.equals("end")) {
            break;
          } else {
            letters.add(line.toCharArray());
          }
        }

        //Reading and saving the words
        while (input.hasNext()) {
          line = input.nextLine();
          words.add(line);
        }

        //Convert ArrayList to array.
        this.words = new String[words.size()];
        this.letters = new char[letters.size()][];
        this.words = words.toArray(this.words);
        this.letters = letters.toArray(this.letters);
    }

    // If .txt file is not found
    catch (FileNotFoundException e) {
      System.out.println(e);
    }
  }
}
