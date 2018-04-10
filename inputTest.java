package wordsearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;


public class inputTest{

  /**Datafield declarations.*/
  private static int width;
  private static int length;
  private static int wordCount;
  private static  ArrayList<String> words;
  private static  int[] positions;
  private static char [][] search;
  private  static String input;
  /**Int counter to count the errors. */
  private static int  errorcount= 0;
  private static String File;
  private static String nameOfFile;

  public static void printVertical(){
	    for(int i =0; i <width; i++){
	      for(int ind =0; ind<length; ind ++){
	        System.out.print(search[i][ind]+ " ");
	      }
	      System.out.println(" ");
	    }
	    System.out.println("done");
	    System.out.println( width + "\t Lines deep");
	    System.out.println( length +"\t lines wide");
	    System.out.println("Find these words");
	    for(int i =0; i<wordCount; i++){
	      System.out.println(words.get(i));
	    }
	  }


      public static char[][] getBoard() {
        return search;
      }

      public static String[] getWords() {
        String[] wordArr = new String[words.size()];
        for(int i = 0; i < wordArr.length; i++) {
          wordArr[i] = words.get(i);
        }
        return wordArr;
      }
      
	  /*
	  * @param void;
	  * @return void;
	  * Creates the wordsearch by calling other methods.
	  */
	  public static void run(){
	    System.out.println("Welcome to the word search generator");
	    System.out.println("This simple java program will create a word search based on words you choose");
	    takeInput();
	    measurements();
	    fill();
	  }
	   /*
	  * @param void;
	  * @return void;
	  *Fills up the search array, applies the inputted words,
	  * and randomly generates the rest of the characters.
	  */
	  public static void fill(){
	    int between,  strlen;
	    int x, y ;
	    positions = new int[wordCount];
	    for(int i =0; i < wordCount; i++){ //for each word in list
	      strlen = words.get(i).length();
	      between = width -strlen;
	      x = randomRange(0, between);  //make sure it isnt a y coord anyone else is using.
	      y = randomRange(0, length-5);
	      if(search(positions,  y) ){
	        y++;
	      }
	      positions[i] = y;
	      for(int ind =0; ind <strlen; ind++){   // for each letter in the word,
	        search[x][y] = words.get(i).charAt(ind); //put char into search array
	        x++;
	      }
	    }
	    // fill empty slots.
	    for (int i =0; i <length; i ++){
	      for (int ind =0; ind< width; ind++){
	        if(search[ind][i] == 0){
	          char t = (char) randomRange(97, 122);
	          search[ind][i] = t;
	        }
	      }
	    }

	  }//end method


	   /*
	  * @param  int array, int key.
	  * @return boolean.
	  * searches array for the key. returns true if key is in array.
	  */
	  public static boolean search(int [ ] numbers, int key) {
	    for (int index = 0; index < numbers.length; index++)    {
	      if ( numbers[index] == key ){
	        return true;  //We found it!!!
	      }
	    }
	    return false;
	  }

	  /**
	   * @param int low, int high : the bounding values for the range within which
	   * the generated number should fall.
	   * @returns int : the random number generated.
	   * random number genertator for values
	   */
	  public static int randomRange(int low, int high){
	    Random generator = new Random();
	    return generator.nextInt(high-low+1) + low;
	  } //end method

	   /*
	  * @param void;
	  * @return void;
	  * Takes user input, processes it and places it in relevate datafields.
	  */
	  public static void takeInput(){
	    Scanner scan = new Scanner(System.in);
	    Scanner fileScanner = null;
	    wordCount =0;
	    words =  new ArrayList<String>();
	    System.out.println("Would you like to type out your words (enter: word) or use a file (enter: file)?");
	    input = scan.next();

	    	if (input.equalsIgnoreCase("word")){

	    		System.out.println("What words do you want to look for? type each word on its own line." );
	    		System.out.println("Enter 'end' when your list of words is complete.");
	    		while (scan.hasNextLine()){
	    			input = scan.next();
	    			if(input.equals("end")){
	    				scan.close();
	    				break;
	    			}
	    			wordCount++;
	    			words.add(input);
	    		}
	    	}
	    	else if(input.equalsIgnoreCase("file")){
                System.out.println("Enter the path of the file you want to use.");
                try {
                    nameOfFile = scan.next();
                    List<String> lines = Collections.emptyList();
                    try {
                        lines = Files.readAllLines(Paths.get(nameOfFile), StandardCharsets.UTF_8);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    words.addAll(lines);
                    wordCount = words.size();
                    System.out.println(words);
                    System.out.println(wordCount);
                }

                catch(Exception e){
                    System.out.println("File doesn't exist, please respecify file name");
                }
                }
	    	else{
	    		System.out.println("Thats not file or word please use word or file.");
	    		takeInput();

	    	}
	    	}
	  //end method

	   /*
	  * @param void;
	  * @return void;
	  * Calculates size of the char array the word search uses.
	  */
	  public static void measurements(){
	    System.out.println("Generating word search");
	    int i;
	    for(i =0; i<words.size(); i++){
	      if(words.get(i).length() > width){
	        width = words.get(i).length();
	      }
	    }
	    width = width *2;
	    length = width + (width/3);
	    search = new char [width][length];
	  }
}
